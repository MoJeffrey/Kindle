package com.qq1205792161.DownloadResources;

import java.util.ArrayList;

import com.qq1025792161.DownloadResources.WebsiteHTML;

import junit.framework.TestCase;

public class WebsiteHTMLTest extends TestCase{
	WebsiteHTML BBCWebsiteHTML;
	
	@Override
	protected void setUp() throws Exception {
		
		BBCWebsiteHTML=new WebsiteHTML("http://www.bbc.co.uk/learningenglish/chinese/features/media-english");
	
	}
	
//	public void testObtainWebsiteHTMLBufferedReader() {
//		BufferedReader BBCBufferedReader=BBCWebsiteHTML.ObtainWebsiteHTMLBufferedReader();
//		String BBCHTMLLine=null;
//		try {
//			while((BBCHTMLLine=BBCBufferedReader.readLine())!=null) {
//					if(BBCHTMLLine.contains("BBCText")) {
//						if(BBCHTMLLine.contains("</div>")){
//							
//							String BBCtest[]=BBCHTMLLine.split("</div>");
//							BBCHTMLLine=BBCtest[0];
//						}
//							
//						System.out.println(BBCHTMLLine);
//						
//					}
//				
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void testObtainResourcesGetStringBuilder() {
		ArrayList<String> AR=BBCWebsiteHTML.ObtainResourcesGetArrayList("<h2><a  href=");
		for(String s:AR) {
			System.out.println(s);
		}
		
		
	}
	
	public static void main(String[] ages) {
		junit.textui.TestRunner.run(WebsiteHTMLTest.class);
	}

}
