package com.qq1025792161.KindleGenHTML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 生成HTML文档用做给KindleGen生成Moid
 * @author MoJeffry
 *
 */
public class KindleGenXHTML {
	private File TemplateForXHTML=new File("index.xhtml");
	
	File SaveForNewXHTMLsFile;
	
	String TemplateString;
	String ReWriteXHTMLString;
	
	String XHTMLForTitle="</title>";
	String XHTMLForSrc="src";
	public KindleGenXHTML(File SaveForNewXHTMLsFile,String Title,String Src){
		this.SaveForNewXHTMLsFile=SaveForNewXHTMLsFile;
		TemplateString=ReadTemplateForXHTML();
		ReWriteXHTMLString=ReWriteXHTMLString(Title,Src);
		
		CreateFileAndWriteForReWriteXHTMLString();
	}
	
	public String GetTemplateString() {
		return TemplateString;
	}
	public String GetReWriteXHTMLString() {
		return ReWriteXHTMLString;
	}
	public File GetSaveForNewXHTMLsFile() {
		return SaveForNewXHTMLsFile;
	}
	
	private void CreateFileAndWriteForReWriteXHTMLString() {
		byte[] ReWriteXHTMLByte;
		try {
			ReWriteXHTMLByte =ReWriteXHTMLString.getBytes("utf8");
			if(null != ReWriteXHTMLByte){  
		        if (!SaveForNewXHTMLsFile.exists()) {   //文件不存在则创建文件，先创建目录  
		            File dir = new File(SaveForNewXHTMLsFile.getParent());  
		            dir.mkdirs();  
		            SaveForNewXHTMLsFile.createNewFile();  
		        }  
		        FileOutputStream outStream = new FileOutputStream(SaveForNewXHTMLsFile);    //文件输出流用于将数据写入文件  
		        outStream.write(ReWriteXHTMLByte);  
		        outStream.close();  //关闭文件输出流  
			}  
		} catch (Exception e1) {
			e1.printStackTrace();
		}  
		
	}
	
	private String ReWriteXHTMLString(String Title,String Src) {
		int XHTMLForTitleIndex=TemplateString.lastIndexOf(XHTMLForTitle);
		
		int XHTMLForSrcIndex=TemplateString.lastIndexOf(XHTMLForSrc)+Title.length()+5;//距离 
		
		StringBuilder SBInsertContent=new StringBuilder(TemplateString);
		
		SBInsertContent.insert(XHTMLForTitleIndex,Title);
		SBInsertContent.insert(XHTMLForSrcIndex, Src);
		
		return SBInsertContent.toString();
	}

	private String ReadTemplateForXHTML() {
	        Reader reader = null;
	        String TemplateString="";
	        try {
	            reader = new InputStreamReader(new FileInputStream(TemplateForXHTML),"utf8");
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
