package com.qq1025792161.DownloadResources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class DownloadPictures {
	 String FileName;
	 String ComicsName;
	 String Aut;
	 String URLString;
	 
	 File ComicsFile;
		 
	 BufferedReader WebBufferedReader;
	public DownloadPictures(String URLString){
		this.URLString=URLString;
		
		WebBufferedReader=new WebsiteHTML(URLString).ObtainWebsiteHTMLString();
		
		try {
			String[] PictureURLs=ObtainStringBuilderForPictureURLs().toString().split("\n");
			int PictureURLsIndex=PictureURLs.length;
			int CurrentPictureURLIndex=1;
			System.out.println("开始下载漫画");
	        for(String PictureURL:PictureURLs) {
	        	ComicsFile=download(PictureURL,".//"+FileName);
	        	System.out.println(DownloadProgress(PictureURLsIndex,CurrentPictureURLIndex));
	        	CurrentPictureURLIndex++;
	        }
	        System.out.println("Download completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}
	private String DownloadProgress(int PictureURLsIndex,int CurrentPictureURLIndex) {
		   String ProgressIndex="";//接受百分比的值
		   double baiy=CurrentPictureURLIndex*1.0;
		   double baiz=PictureURLsIndex*1.0;
		   double fen=baiy/baiz;
		   DecimalFormat df1 = new DecimalFormat("##.00%");    //##.00%   百分比格式，后面不足2位的用0补齐 
		   ProgressIndex= df1.format(fen);  
		   return ProgressIndex;
	}
	
	
	
	private StringBuilder ObtainStringBuilderForPictureURLs() throws UnsupportedEncodingException, IOException {
		StringBuilder StringBuilderForPictureURLs= new StringBuilder();
		String WebLineString= null;
		ObtainPictureURL(WebBufferedReader,StringBuilderForPictureURLs,WebLineString);
		return StringBuilderForPictureURLs;
	}
	
	private void ObtainPictureURL(BufferedReader WebBufferedReader,StringBuilder StringBuilderForPictureURLs,String WebLineString) throws IOException {
		int PictureIndex=1;
		while((WebLineString= WebBufferedReader.readLine()) != null){
			ReadComicsName(WebLineString);
			if(WebLineString.contains("Large_cgurl["+PictureIndex+"]")) {
				PictureIndex++;
				String[] PictureURL=WebLineString.split("\"");
				StringBuilderForPictureURLs.append(PictureURL[1]+"\n");
			}
		}
	}
	
	public String GetFileName() {
		return FileName;
	}
	public String GetComicsName() {
		return ComicsName;
	}
	public String GetAut() {
		return Aut;
	}
	public File GetComicsFile() {
		return ComicsFile;
	}
	
	private void ReadComicsName(String s) {
		if(s.contains("<H1>")) {
    		String []ss=s.split("<H1>");
    		String []ss2=ss[1].split("</H1>");
    		System.out.println(ss2[0]);
    		FileName=ss2[0].replace(" ","");
    		String []ss3=FileName.split("]");
    		Aut=ss3[0]+"]";
    		System.out.println(Aut);
    		ComicsName=ss3[1]+"]";
    		System.out.println(ComicsName);
    	}
	}
	
	public static File download(String urlString,String savePath) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(5*1000);
		InputStream is = con.getInputStream();
	 
	// 1K�����ݻ���
	byte[] bs = new byte[1024];
	// ��ȡ�������ݳ���
	int len;
	// ������ļ���
	File sf=new File(savePath);
	if(!sf.exists()){
	sf.mkdirs();
	}
	OutputStream os = new FileOutputStream(sf.getPath()+"/"+GetLastURL(urlString));
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
		return sf;
	}
	
	public static String GetLastURL(String s) {
		String []ss=s.split("/");
		return ss[ss.length-1];
	}
}
