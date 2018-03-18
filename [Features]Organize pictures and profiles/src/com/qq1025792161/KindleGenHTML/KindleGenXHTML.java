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
		byte[] ReWriteXHTMLByte = ReWriteXHTMLString.getBytes();  
		if(null != ReWriteXHTMLByte){  
		    try {  
		        if (!SaveForNewXHTMLsFile.exists()) {   //文件不存在则创建文件，先创建目录  
		            File dir = new File(SaveForNewXHTMLsFile.getParent());  
		            dir.mkdirs();  
		            SaveForNewXHTMLsFile.createNewFile();  
		        }  
		        FileOutputStream outStream = new FileOutputStream(SaveForNewXHTMLsFile);    //文件输出流用于将数据写入文件  
		        outStream.write(ReWriteXHTMLByte);  
		        outStream.close();  //关闭文件输出流  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
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
	            // 一次读一个字符
	            reader = new InputStreamReader(new FileInputStream(TemplateForXHTML));
	            int tempchar;
	            while ((tempchar = reader.read()) != -1) {
	                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
	                // 但如果这两个字符分开显示时，会换两次行。
	                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
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
