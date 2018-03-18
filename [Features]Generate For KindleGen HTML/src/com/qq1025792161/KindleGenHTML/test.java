package com.qq1025792161.KindleGenHTML;

import java.awt.List;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f=new File("C:\\Users\\admin\\Desktop\\个人资料\\自写程序\\漫画制作\\[墓場][壊して下さい][214p]\\Resources");
		 java.util.List<File> files = Arrays.asList(f.listFiles());  
		    Collections.sort(files, new Comparator<File>() {  
		        @Override  
		        public int compare(File o1, File o2) {  
		            if (o1.isDirectory() && o2.isFile())  
		                return -1;  
		            if (o1.isFile() && o2.isDirectory())  
		                return 1;  
		            return o1.getName().compareTo(o2.getName());  
		        }  
		    });  
		    for (File f2 : files) {  
		        System.out.println(f2.getName());  
		    }  
//		new KindleGenXHTML(new File("./test.xhtml"),"我试试宏伟","坎坎坷坷扩扩扩");
	}

}
