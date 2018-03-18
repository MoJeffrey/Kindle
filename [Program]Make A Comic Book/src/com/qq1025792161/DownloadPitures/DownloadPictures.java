package com.qq1025792161.DownloadPitures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPictures {
	//��������
	 String FileName;
	 String ComicsName;
	 String Aut;
	 
	 File ComicsFile;
	
	public DownloadPictures(String URLString)  {
		//URL url =  new URL("http://18h.mm-cg.com/18H_4911.html");
		
		URL url;
		try {
			url = new URL(URLString);
			
			
			/**
			 * ���µķ������Խ��HTML 403
			 * ��Ϊ������������м���User-Agent�Ĳ������öԷ�����������Ϊ����ʵ�������
			 */
			URLConnection uc ;

			uc = url.openConnection();

			uc.addRequestProperty("User-Agent",

			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

			uc.setDoInput(true);//�O���Ƿ�Ҫ�� URL �B���xȡ����,Ĭ�J��true

			uc.connect();
			
			
			
			BufferedReader buff  = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
	        StringBuilder sb = new StringBuilder();
	        String s = null;
	        int x=1;
	            while((s = buff.readLine()) != null){
	            	ReadComicsName(s);
	            	if(s.contains("Large_cgurl["+x+"]")) {
	            		x++;
	            		String[] strs=s.split("\"");
	            		sb.append(strs[1]+"\n");
	            	}
	            }
	            
	            
	            write(sb);
	            
	            String [] sbs=sb.toString().split("\n");
	            for(String string:sbs) {
	            	System.out.println("开始下载"+string);
	            	ComicsFile=download(string,".//"+FileName);
	            	System.out.println(string+"下载完成");
	            }
	            System.out.println("Download completed!");


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        
        
        //System.out.println(sb);
       
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
	/**
	 * ��ȡ��������
	 * ��������
	 * ���ĵ�����
	 * @param s
	 */
	public void ReadComicsName(String s) {
		if(s.contains("<H1>")) {
    		String []ss=s.split("<H1>");
    		String []ss2=ss[1].split("</H1>");
    		System.out.println(ss2[0]);
    		FileName=ss2[0].replaceAll(" ","");
    		String []ss3=FileName.split("]");
    		Aut=ss3[0]+"]";
    		System.out.println(Aut);
    		ComicsName=ss3[1]+"]";
    		System.out.println(ComicsName);
    	}
	}
	
	/**
	 * ���ͼƬ��URL��
	 * @param s
	 * @throws IOException
	 */
	public static void write(StringBuilder s) throws IOException {
		/* д��Txt�ļ� */  
        File writename = new File(".\\output.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�  
        writename.createNewFile(); // �������ļ�  
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
        out.write(s.toString()); // 
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
	}
	/**
	* ����·�� ����ͼƬ Ȼ�� ���浽��Ӧ��Ŀ¼��
	* @param urlString ����Դ��ַ·��
	* @param filename �ļ���
	* @param savePath ����·�� /jdylog/pic/JDY000001
	* @return
	* @throws Exception
	*/
	public static File download(String urlString,String savePath) throws Exception {
	// ����URL
	URL url = new URL(urlString);
	// ������
	URLConnection con = url.openConnection();
	//���������·��
	con.setConnectTimeout(5*1000);
	// ������
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
	// ��ʼ��ȡ
	while ((len = is.read(bs)) != -1) {
	os.write(bs, 0, len);
	}
	// ��ϣ��ر���������
	os.close();
	 
	is.close();
	return sf;
	}
	/**
	 * ����URL�����ֶ�
	 * ��Ϊ�����ĵ�������
	 * @param s
	 * @return
	 */
	public static String GetLastURL(String s) {
		String []ss=s.split("/");
		return ss[ss.length-1];
	}
}
