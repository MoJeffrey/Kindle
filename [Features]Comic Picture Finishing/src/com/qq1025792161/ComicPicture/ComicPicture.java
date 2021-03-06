package com.qq1025792161.ComicPicture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class ComicPicture {
	File ComicPicturesFile;
	File ComicPictureFileResources;
	
	File[] ComicPictureFileList;
	File[] ComicPictureFileResourcesList;
	
	public ComicPicture(File ComicPicturesFile) {
		System.out.println(new Date()+"\r\n��ʼ����ͼƬ");
		this.ComicPicturesFile=ComicPicturesFile;
		this.ComicPictureFileResources=new File(ComicPicturesFile.getPath()+"/Resources");
		
		ComicPictureFileList=ReadComicPictureFile();
		ComicPictureFileCopyAndRename();
		
		ComicPictureFileResourcesList=ReadComicPictureFileResources();
	}
	
	public File[] getComicPictureFileList() {
		return ComicPictureFileList;
	}
	
	public File[] getComicPictureFileResourcesList() {
		return ComicPictureFileResourcesList;
	}
	
	private void ComicPictureFileCopyAndRename(){
		int PictureAmount=1;
		
		if(!ComicPictureFileResources.exists()) 
			ComicPictureFileResources.mkdirs();
		for(File ComicPictureFile:this.ComicPictureFileList) {
			try {
				Files.copy(ComicPictureFile.toPath(),new File(ComicPictureFileResources.getPath()+"/"+SortNaming(PictureAmount)+".jpg").toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			PictureAmount++;
		}
	}
	
	private File[] ReadComicPictureFile() {
		return ComicPicturesFile.listFiles();
	}
	
	private File[] ReadComicPictureFileResources() {
		return ComicPictureFileResources.listFiles();
	}
	private String SortNaming(int Number) {
		if(Number<10) {
			return "00"+Number;
		}else if(Number<100&&Number>9) {
			return "0"+Number;
		}else {
			return Number+"";
		}
	}
	
}
