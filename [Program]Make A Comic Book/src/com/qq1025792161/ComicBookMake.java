package com.qq1025792161;

public class ComicBookMake {

	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();   //获取开始时间  
		new ComicBook(args[0]);
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");   
		
		
	}

}
