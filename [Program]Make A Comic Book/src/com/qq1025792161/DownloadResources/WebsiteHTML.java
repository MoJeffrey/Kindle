package com.qq1025792161.DownloadResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class WebsiteHTML {
	private String WebUrlString;
	
	private URLConnection ComicsURLConnection;
	
	private BufferedReader WebBufferedReader;
	
	public WebsiteHTML(String WebUrlString) {
		
		this.WebUrlString=WebUrlString;
		
		
		ObtainURLConnection();
		
		ObtainWebsiteHTMLBufferedReader();
		
	}
	
	private void ObtainURLConnection() {
		try {
			ComicsURLConnection = new URL(WebUrlString).openConnection();
			ComicsURLConnection.addRequestProperty("User-Agent",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			ComicsURLConnection.setDoInput(true);
			ComicsURLConnection.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ;
	}
	
	public BufferedReader ObtainWebsiteHTMLBufferedReader(){
		
		try {
			WebBufferedReader = 
					new BufferedReader(
							new InputStreamReader(
									ComicsURLConnection.getInputStream(),
									"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return WebBufferedReader;
	}
	
	public ArrayList<String> ObtainResourcesGetArrayList(String FindUrlTag){
		ArrayList<String> ResourcesStringArrayList=new ArrayList<>();
		BufferedReader BBCBufferedReader=WebBufferedReader;
		String BBCHTMLLine=null;
		try {
			while((BBCHTMLLine=BBCBufferedReader.readLine())!=null) {
				if(BBCHTMLLine.contains(FindUrlTag)) {
					ResourcesStringArrayList.add(BBCHTMLLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResourcesStringArrayList;
	}
	
	public StringBuilder ObtainResourcesGetStringBuilder(String FindUrlTag){
		StringBuilder ResourcesStringStringBuilder=new StringBuilder();
		BufferedReader HTMLBufferedReader=WebBufferedReader;
		String HTMLLine=null;
		try {
			while((HTMLLine=HTMLBufferedReader.readLine())!=null) {
				if(HTMLLine.contains(FindUrlTag)) {
					ResourcesStringStringBuilder.append(HTMLLine);
					ResourcesStringStringBuilder.append("\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResourcesStringStringBuilder;
	}
	
	public String GetWebUrlString() {
		return WebUrlString;
	}

}
