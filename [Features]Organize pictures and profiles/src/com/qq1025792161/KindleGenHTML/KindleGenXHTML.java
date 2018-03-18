package com.qq1025792161.KindleGenHTML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * ����HTML�ĵ�������KindleGen����Moid
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
		        if (!SaveForNewXHTMLsFile.exists()) {   //�ļ��������򴴽��ļ����ȴ���Ŀ¼  
		            File dir = new File(SaveForNewXHTMLsFile.getParent());  
		            dir.mkdirs();  
		            SaveForNewXHTMLsFile.createNewFile();  
		        }  
		        FileOutputStream outStream = new FileOutputStream(SaveForNewXHTMLsFile);    //�ļ���������ڽ�����д���ļ�  
		        outStream.write(ReWriteXHTMLByte);  
		        outStream.close();  //�ر��ļ������  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		}  
	}
	
	private String ReWriteXHTMLString(String Title,String Src) {
		int XHTMLForTitleIndex=TemplateString.lastIndexOf(XHTMLForTitle);
		
		int XHTMLForSrcIndex=TemplateString.lastIndexOf(XHTMLForSrc)+Title.length()+5;//���� 
		
		StringBuilder SBInsertContent=new StringBuilder(TemplateString);
		
		SBInsertContent.insert(XHTMLForTitleIndex,Title);
		SBInsertContent.insert(XHTMLForSrcIndex, Src);
		
		return SBInsertContent.toString();
	}

	private String ReadTemplateForXHTML() {
	        Reader reader = null;
	        String TemplateString="";
	        try {
	            // һ�ζ�һ���ַ�
	            reader = new InputStreamReader(new FileInputStream(TemplateForXHTML));
	            int tempchar;
	            while ((tempchar = reader.read()) != -1) {
	                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
	                // ������������ַ��ֿ���ʾʱ���ỻ�����С�
	                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
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
