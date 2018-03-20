package com.qq1025792161.DownloadResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebsiteHTML {
	private String WebUrlString;
	
	private URLConnection ComicsURLConnection;
	
	public WebsiteHTML(String WebUrlString) {
		
		this.WebUrlString=WebUrlString;
		
		
		this.ComicsURLConnection=ObtainURLConnection();
		
	}
	
	private URLConnection ObtainURLConnection() {
		URLConnection ComicsURLConnection = null;
		
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
		
		
		return ComicsURLConnection;
	}
	
	public BufferedReader ObtainWebsiteHTMLString(){
		BufferedReader WebBufferedReader = null;
		
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
	
	public String GetWebUrlString() {
		return WebUrlString;
	}

}
