package com.qq1025792161.OrganizePicturesAndProfiles;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.qq1025792161.ComicPicture.ComicPicture;
import com.qq1025792161.KindleGenHTML.KindleGenXHTML;
import com.qq1025792161.KindleGenOPF.KindleGenOPF;

public class PicturesAndProfiles {
	File Position;
	File ProfilesForOPFFile;
	File ProfilesForCssFile=new File("index_0.css");
	File ProfilesForTocFile=new File("toc.ncx");
	
	File[] ComicPictureFileList;
	ArrayList<File> ProfilesForXHTMLFileList;
	
	String Title;
	String Aut;
	String Date;
	
	
	public PicturesAndProfiles(File Position,String Title,String Aut) {
		this.Position=Position;
		this.Title=Title;
		this.Aut=Aut;
		this.Date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		ComicPictureFileList=ObtainComicPictureFileList();
		
		ProfilesForXHTMLFileList=GenerateProfilesForXHTMLFileList();
		
		ProfilesForOPFFile=GenerateProfilesForOPFFile();
		
		CopyCssProfiles();
		
		CopyTocProfiles();
		
		System.out.println(new Date()+"\r\n配置文档已经生成完毕");
		
	
	}
	public File GetProfilesForOPFFile() {
		return ProfilesForOPFFile;
	}
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public  boolean deletePosition(File Position) {
        if (Position.isDirectory()) {
            String[] children = Position.list();
            //递归删除文档
            for (int i=0; i<children.length; i++) {
                boolean success = deletePosition(new File(Position, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return Position.delete();
    }
	
	private File GenerateProfilesForOPFFile() {
		File ProfilesForOPFFile=new File(this.Position.getPath()+"/metadata.opf");
		
		KindleGenOPF KindleGenOPF=new KindleGenOPF(ProfilesForOPFFile,
				ComicPictureFileList,
				ProfilesForXHTMLFileList,
				Title,
				Aut,
				Date);
		return KindleGenOPF.GetSaveForNewOPFFile();
	}
	
	private File[] ObtainComicPictureFileList() {
		ComicPicture ComicPicture=new ComicPicture(this.Position);
		return ComicPicture.getComicPictureFileResourcesList();
	}
	
	private ArrayList<File> GenerateProfilesForXHTMLFileList() {
		
		int NumberOfDocuments=1;
		
		ArrayList<File> ProfilesForXHTMLFileList=new ArrayList<File>();
		
		for(File ComicPictureFile:ComicPictureFileList) {
			File ProfilesForXHTMLFile=new File(Position.getPath()+"/index_"+NumberOfDocuments+".xhtml");
			
			new KindleGenXHTML(ProfilesForXHTMLFile,Title,ComicPictureFile.getPath());
			
			ProfilesForXHTMLFileList.add(ProfilesForXHTMLFile);
			
			NumberOfDocuments++;
		}
		
		return ProfilesForXHTMLFileList;
	}
	
	private void CopyCssProfiles() {
		try {
			Files.copy(ProfilesForCssFile.toPath(),
					new File(Position.getPath()+"/Resources/"+ProfilesForCssFile).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void CopyTocProfiles() {
		try {
			Files.copy(ProfilesForCssFile.toPath(),
					new File(Position.getPath()+"/"+ProfilesForTocFile).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
