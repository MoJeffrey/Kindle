package com.qq1025792161.CarriedOutKindlegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class CarriedOutKindlegen {
	String KindlegenCommand="kindlegen";
	String ComicsFileName;
	
	
	File ConversionFile;
	
	
	public CarriedOutKindlegen(File ConversionFile,String ComicsFileName) {
		this.ConversionFile=ConversionFile;
		this.ComicsFileName=ComicsFileName+".mobi";
		System.out.println(new Date()+"\r\n开始制作Mobi文档");
		RunKindlegenCommand();
		System.out.println(new Date()+"\r\n完成制作Mobi文档");
	}
	
	
	private void RunKindlegenCommand() {
		Runtime run = Runtime.getRuntime();     
	    try {
	    	String Com="cmd.exe /c " + KindlegenCommand+"\t"+ConversionFile+"\t-o\t"+ComicsFileName.replaceAll(" ","");
	        Process process = run.exec(Com);  
	        System.out.println(Com);
	        InputStream input = process.getInputStream();   
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input,"utf8"));  
	        String szline;  
	        while ((szline = reader.readLine())!= null) {     
	            System.out.println(new String(szline.getBytes("utf8"),"utf8"));     
	        }     
	        reader.close();     
	        process.waitFor();   
	        process.destroy();    
	    } catch (Exception e) {              
	        e.printStackTrace();     
	    }     
	}
}
