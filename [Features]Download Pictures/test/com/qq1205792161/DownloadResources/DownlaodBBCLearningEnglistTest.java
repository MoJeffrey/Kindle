package com.qq1205792161.DownloadResources;

import com.qq1025792161.DownloadResources.DownlaodBBCLearningEnglist;

import junit.framework.TestCase;

public class DownlaodBBCLearningEnglistTest extends TestCase{
	DownlaodBBCLearningEnglist DownlaodBBCLearningEnglist;
	@Override
	protected void setUp() throws Exception {
		DownlaodBBCLearningEnglist=new DownlaodBBCLearningEnglist();
		super.setUp();
	}
	
	
	public void testObtainResourcesURL() {
		
	}
	
	public void testObtainArticleAndURL() {
//		ArrayList<String> AL=DownlaodBBCLearningEnglist.GetArrayList();
//		for(String s:AL) {
//			System.out.println(s);
//		}
	}
	
	public void testObtainContentOfArticle() {
		//System.out.println(DownlaodBBCLearningEnglist.GetStringBuilder());
	}
	
	public void testWriteFile() {
		//DownlaodBBCLearningEnglist.WriteFile();
	}
	
	
	public static void main(String[] ages) {
		junit.textui.TestRunner.run(DownlaodBBCLearningEnglistTest.class);
	}
}
