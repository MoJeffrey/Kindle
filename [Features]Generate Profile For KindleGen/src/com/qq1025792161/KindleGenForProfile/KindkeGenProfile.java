package com.qq1025792161.KindleGenForProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class KindkeGenProfile {
	private File SaveForNewFile;
	
	private String TemplateFileURL;
	private String TemplateString;
	
	public KindkeGenProfile(String TemplateFileURL,File SaveForNewFile) {
		this.TemplateFileURL=TemplateFileURL;
		this.SaveForNewFile=SaveForNewFile;
		
		TemplateString=ReadTemplateForXHTML();
		
		CreateFile();
	}
	public void ReWriteTemplateString(String Element,String Content) {
		
		new StringBuilder(TemplateString)
		
		.insert(
				
				TemplateString.lastIndexOf(Element), Content);
		
		return;
	}
	
	
	public void WriteForReWriteXHTMLString() {
		byte[] ReWriteXHTMLByte;
		try {
			ReWriteXHTMLByte =TemplateString.getBytes("utf8");
			if(null != ReWriteXHTMLByte){
		        FileOutputStream outStream = new FileOutputStream(SaveForNewFile);    //文件输出流用于将数据写入文件  
		        outStream.write(ReWriteXHTMLByte);  
		        outStream.close();  //关闭文件输出流  
			}  
		} catch (Exception e1) {
			e1.printStackTrace();
		}  
		
	}
	
	private void CreateFile() {
		if (!SaveForNewFile.exists()) {   
            File dir = new File(SaveForNewFile.getParent());  
            dir.mkdirs();  
            try {
				SaveForNewFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }  
	}
	
	private String ReadTemplateForXHTML() {
        Reader reader = null;
        String TemplateString="";
        try {
            reader = new InputStreamReader(this.getClass().getResourceAsStream(TemplateFileURL),"utf8");
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
