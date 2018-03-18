package com.qq1205792161.FileChange;

import java.io.File;

public class HTML_To_PDF {
	 //wkhtmltopdf��ϵͳ�е�·��  
    private static final String toPdfTool = "wkhtmltopdf";  
      
    /** 
     * htmlתpdf 
     * @param srcPath html·����������Ӳ���ϵ�·����Ҳ����������·�� 
     * @param destPath pdf����·�� 
     * @return ת���ɹ�����true 
     */  
    public static boolean convert(String srcPath, String destPath){  
        File file = new File(destPath);  
        File parent = file.getParentFile();  
        //���pdf����·�������ڣ��򴴽�·��  
        if(!parent.exists()){  
            parent.mkdirs();  
        }  
          
        StringBuilder cmd = new StringBuilder();  
        cmd.append(toPdfTool);  
        cmd.append(" ");  
        cmd.append(srcPath);  
        cmd.append(" ");  
        cmd.append(destPath);  
          
        boolean result = true;  
        try{  
            Process proc = Runtime.getRuntime().exec(cmd.toString());  
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());  
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());  
            error.start();  
            output.start();  
            proc.waitFor();  
        }catch(Exception e){  
            result = false;  
            e.printStackTrace();  
        }  
          
        return result;  
    } 
}

