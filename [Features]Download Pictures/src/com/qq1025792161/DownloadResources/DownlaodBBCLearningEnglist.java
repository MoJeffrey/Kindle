package com.qq1025792161.DownloadResources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.qq1025792161.CarriedOutKindlegen.CarriedOutKindlegen;

public class DownlaodBBCLearningEnglist extends WebsiteHTML{
	private String FindUrlTag="<h2><a  href=";
	private String FindContentOfArticleTag="BBCText";

	private String HTMLHeadString="<html>\r\n" + 
			"<head>\r\n" + 
			"<meta http-equiv=\"book-Type\" content=\"text/html; charset=utf-8\"/>\r\n" + 
			"</head>\r\n" + 
			"<body style=\"font-family:PrimaryFont\">";
	
	private String HTMLEndString="</body>\r\n" + 
			"</html>";
	
	private ArrayList<String> ResourcesURLAndTitleStringArrayList;
	private ArrayList<String> ResourcesURLStringArrayList;
	private ArrayList<String> ResourcesTitleStringArrayList;
	
	private static String WebUrlString="http://www.bbc.co.uk/learningenglish/chinese/features/media-english";
	
	private String SaveFileURL="BBCLearningEnglish/BBCLearningEnglish.html";
	
	private StringBuilder ContentOfArticle;
	
	private int ArticleIndex=0;
	
	public DownlaodBBCLearningEnglist() {
		super(WebUrlString);
		
		this.ResourcesURLAndTitleStringArrayList=this.ObtainResourcesGetArrayList(FindUrlTag);
		
		SegmentationURLAndTitle();
		
		
//		String FileName=URLToFileName(ResourcesURLStringArrayList.get(ArticleIndex));
//		
//		ObtainContentOfArticle();
//		
//		WriteFile();
//		
//		new CarriedOutKindlegen(new File(SaveFileURL),FileName);
//		
//		System.out.println("完成"+FileName);
		for(String ResourcesURLString:ResourcesURLStringArrayList) {
			String FileName=URLToFileName(ResourcesURLString);
			
			System.out.println("开始"+FileName);
			
			if(!new File("BBCLearningEnglish/"+FileName+".mobi").exists()) {
				
				ObtainContentOfArticle();
				
				WriteFile();
				
				new CarriedOutKindlegen(new File(SaveFileURL),FileName);
				
				
				ArticleIndex++;
				
				System.out.println("完成"+FileName);
				
				
			}else {
				System.out.println(FileName+"已经存在");
			}
			
			
		}
		
		
	}
	
	private void ObtainContentOfArticle() {
		WebsiteHTML ContentOfArticleBBCWebsiteHTML
			=new WebsiteHTML(ResourcesURLStringArrayList.get(ArticleIndex));
		ContentOfArticle=ObtainResourcesGetStringBuilder(FindContentOfArticleTag,ContentOfArticleBBCWebsiteHTML.ObtainWebsiteHTMLBufferedReader());
	
		FilterContentOfArticle();
	
	}
	private String URLToFileName(String BBCWebsiteURL) {
		String[] URLSplit=BBCWebsiteURL.split("/");
		return "BBC媒体英文"+URLSplit[URLSplit.length-1];
	}
	
	public StringBuilder ObtainResourcesGetStringBuilder(String FindUrlTag,BufferedReader WebBufferedReader){
		StringBuilder ResourcesStringStringBuilder=new StringBuilder();
		
		ResourcesStringStringBuilder.append(HTMLHeadString);
		
		ResourcesStringStringBuilder.append("<h1>"+ResourcesTitleStringArrayList.get(ArticleIndex)+"</h1>");
		ResourcesStringStringBuilder.append("\r\n");
		
		ResourcesStringStringBuilder.append("<h2>Source:BBCLearningEnglish媒体英文</h2>");
		ResourcesStringStringBuilder.append("\r\n");
		
		BufferedReader HTMLBufferedReader=WebBufferedReader;
		String HTMLLine=null;
		try {
			HTMLLine=HTMLBufferedReader.readLine();
			while(HTMLLine!=null) {
				
				if(HTMLLine.contains("<div class=\"widget widget-bbcle-featuresubheader\" >")) {
					HTMLLine=HTMLBufferedReader.readLine();
					HTMLLine=HTMLBufferedReader.readLine();
					HTMLLine=HTMLBufferedReader.readLine();
					HTMLLine=HTMLBufferedReader.readLine();
					HTMLLine=HTMLBufferedReader.readLine();
					HTMLLine=HTMLBufferedReader.readLine();
					ResourcesStringStringBuilder.append("<h2>"+HTMLLine+"</h2>");
					ResourcesStringStringBuilder.append("\r\n");
					HTMLLine=HTMLBufferedReader.readLine();
					
				}
				
				if(HTMLLine.contains(FindUrlTag)) {
					ResourcesStringStringBuilder.append(HTMLLine);
					ResourcesStringStringBuilder.append("\r\n");
					
					HTMLLine=HTMLBufferedReader.readLine();
					if(HTMLLine.contains("<h3>")) {
						ResourcesStringStringBuilder.append(HTMLLine);
						ResourcesStringStringBuilder.append("\r\n");
						HTMLLine=HTMLBufferedReader.readLine();
						
						ResourcesStringStringBuilder.append(HTMLLine);
						ResourcesStringStringBuilder.append("\r\n");
						HTMLLine=HTMLBufferedReader.readLine();
					}
				}else{
					HTMLLine=HTMLBufferedReader.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResourcesStringStringBuilder;
	}
	
	private void SegmentationURLAndTitle() {
		ResourcesURLStringArrayList=new ArrayList<>();
		ResourcesTitleStringArrayList=new ArrayList<>();
		
		for(String URLAndTitle:ResourcesURLAndTitleStringArrayList) {
			String[] URL=URLAndTitle.split("<h2><a  href=\"");
			String[] Name=URL[1].split("\">");
			ResourcesURLStringArrayList.add("http://www.bbc.co.uk"+Name[0]);
			
			String test=Name[1].replace("</a></h2>","");			
			ResourcesTitleStringArrayList.add(test);
		}
		return;
	}
	
	private void FilterContentOfArticle() {
		String FilterContentOfArticle=ContentOfArticle.toString();
		
		if(FilterContentOfArticle.contains("class=\"BBCText\"")){
			FilterContentOfArticle=FilterContentOfArticle.replace("class=\"BBCText\"","");
		}
		
		if(FilterContentOfArticle.contains("</div>")){
			String BBCtest[]=FilterContentOfArticle.split("</div>");
			FilterContentOfArticle=BBCtest[0];
		}
		
		ContentOfArticle=new StringBuilder();
		ContentOfArticle.append(FilterContentOfArticle);
	}
	
	public void WriteFile() {
		ContentOfArticle.append(HTMLEndString);
		File SaveFile=new File(SaveFileURL); 
		if(!SaveFile.getParentFile().exists())SaveFile.getParentFile().mkdirs();
		try {
			OutputStream os = new FileOutputStream(SaveFileURL);
			try {
				os.write(ContentOfArticle.toString().getBytes("utf8"));
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> GetArrayList(){
		return ResourcesURLAndTitleStringArrayList;
	}
	
	public StringBuilder GetStringBuilder() {
		return ContentOfArticle;
	}
}
