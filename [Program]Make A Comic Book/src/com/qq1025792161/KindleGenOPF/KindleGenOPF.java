package com.qq1025792161.KindleGenOPF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class KindleGenOPF {
	private String TemplateForOPFULR="/metadata.opf";
	
	File SaveForNewOPFFile;
	
	File[] ComicPictureFileList;
	ArrayList<File> ProfilesForXHTMLFileList;
	
	String TemplateString;
	String ReWriteOPFString;
	
	String OPFForTitle="</dc:title>";
	String OPFForaut="</dc:creator>";
	String OPFForDate="</dc:date>";
	String OPFForManifest="</manifest>";
	String OPFForSpine="</spine>";
	
	public KindleGenOPF(File SaveForNewOPFFile,File[] ComicPictureFileList,ArrayList<File> ProfilesForXHTMLFileList,String Title,String Aut,String Date) {
		this.SaveForNewOPFFile=SaveForNewOPFFile;
		this.ComicPictureFileList=ComicPictureFileList;
		this.ProfilesForXHTMLFileList=ProfilesForXHTMLFileList;
		
		TemplateString=ReadTemplateForXHTML();
		ReWriteOPFString=addData(Title, Aut, Date);
		ReWriteOPFString=addContent(ComicPictureFileList,ProfilesForXHTMLFileList);
		
		CreateFileAndWriteForReWriteXHTMLString();
		
	}
	
	public String getReWriteOPFString() {
		return ReWriteOPFString;
	}
	
	
	public File GetSaveForNewOPFFile() {
		return SaveForNewOPFFile;
	}
	
	
	private void CreateFileAndWriteForReWriteXHTMLString() {
		byte[] ReWriteOPFByte;  
		try {  
			ReWriteOPFByte = ReWriteOPFString.getBytes("utf8");
			if(null != ReWriteOPFByte){  
		        if (!SaveForNewOPFFile.exists()) {   
		            File dir = new File(SaveForNewOPFFile.getParent());  
		            dir.mkdirs();  
		            SaveForNewOPFFile.createNewFile();  
		        }  
		        FileOutputStream outStream = new FileOutputStream(SaveForNewOPFFile);    
		        outStream.write(ReWriteOPFByte);  
		        outStream.close(); 
			} 
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		 
	}
	
	private String addContent(File[] ComicPictureFileList,ArrayList<File> ProfilesForXHTMLFileList) {
		int PageInt=0;
		int id=1;
		StringBuilder SBInsertContent=new StringBuilder(ReWriteOPFString);
		
		for(int x=0;x<ComicPictureFileList.length;x++){
			if(PageInt==0) {
				int ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
				SBInsertContent.insert(ContentIndex,
						"<item id=\""+"id"+(id++)+"\" href=\"Resources/"+ComicPictureFileList[x].getName()+"\" media-type=\"image/jpeg\" properties=\"cover-image\"/>"+"\r\n");
				
				ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
				SBInsertContent.insert(ContentIndex,
						"<item id=\""+"id"+(id++)+"\" href=\""+ProfilesForXHTMLFileList.get(x).getName()+"\" media-type=\"application/xhtml+xml\"/>"+"\r\n");
				
				PageInt++;
			}else{
				int ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
				SBInsertContent.insert(ContentIndex,
						"<item id=\""+"id"+(id++)+"\" href=\"Resources/"+ComicPictureFileList[x].getName()+"\" media-type=\"image/jpeg\"/>"+"\r\n");
				
				ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
				SBInsertContent.insert(ContentIndex,
						"<item id=\""+"id"+(id++)+"\" href=\""+ProfilesForXHTMLFileList.get(x).getName()+"\" media-type=\"application/xhtml+xml\"/>"+"\r\n");
				PageInt++;
			}
		}
		int ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
		SBInsertContent.insert(ContentIndex,
				"<item id=\""+"id"+(id++)+"\" href=\"resources/index_0.css\" media-type=\"text/css\"/>"+"\r\n");
		
		ContentIndex=SBInsertContent.lastIndexOf(OPFForManifest);
		SBInsertContent.insert(ContentIndex,
				"<item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\"/>");
		
		for(int x=1;x<=PageInt;x++) {
			ContentIndex=SBInsertContent.lastIndexOf(OPFForSpine);
			SBInsertContent.insert(ContentIndex,
					"<itemref idref=\"id"+(x*2)+"\"/>"+"\r\n");
		}
		
		
		
		return SBInsertContent.toString();
	}
	
	
	private String addData(String Title,String Aut,String Date) {
		
		StringBuilder SBInsertContent=new StringBuilder(TemplateString);
		
		int OPFForTitleIndex=TemplateString.lastIndexOf(OPFForTitle);
		SBInsertContent.insert(OPFForTitleIndex,Title);
		
		int OPFForautIndex=TemplateString.lastIndexOf(OPFForaut)+Title.length()-2;
		SBInsertContent.insert(OPFForautIndex,Aut);
		SBInsertContent.insert(OPFForautIndex+2+Aut.length(),Aut);
		
		int OPFForDateIndex=TemplateString.lastIndexOf(OPFForDate)+Aut.length()+Title.length()+Aut.length();
		SBInsertContent.insert(OPFForDateIndex,Date);
		
		return SBInsertContent.toString();
		
	}
	
	
	private String ReadTemplateForXHTML() {
        Reader reader = null;
        String TemplateString="";
        try {
           
            reader = new InputStreamReader(this.getClass().getResourceAsStream(TemplateForOPFULR),"utf8");
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                
                if (((char) tempchar) != '\r') {
                	TemplateString+=(char) tempchar;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TemplateString;
	}
}
