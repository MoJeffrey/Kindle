package com.qq1025792161;

import com.qq1025792161.DownloadResources.DownlaodBBCLearningEnglist;

public class ComicBookMake {

	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();   //获取开始时间  
		if(args.length==0) {
			System.out.println("开始制作BBC学习文档");
			
			new DownlaodBBCLearningEnglist();
			
		}else {
			System.out.println("开始漫画制作");
			new ComicBook(args[0]);
		}
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("程序运行时间： "+(endTime-startTime)/60000+"min");   
		
		
	}

}
