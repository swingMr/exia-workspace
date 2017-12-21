package com.excellence.iacommon.common.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


/**
 * 获取文本工具
 * 
 * @author wangjg
 * @created 2017年7月26日
 * @copyright Excellence co.
 */
public class FileHandlerUtil {
	
	private static String SUFFIX_DOC = ".doc";
	
	private static String SUFFIX_DOCX = ".docx";
	
	private static String SUFFIX_PDF = ".pdf";
	
	private static String SUFFIX_HTML = ".html";
	
	private static String SUFFIX_HTM = ".htm";
	
	private static String SUFFIX_TXT = ".txt";
	
	public static String getText(String suffix, InputStream in) throws IOException {
		if(suffix.equals(SUFFIX_DOC)) {
			return getDocText(in);
		} else if(suffix.equals(SUFFIX_DOCX)) {
			return getDocxText(in);
		} else if(suffix.equals(SUFFIX_PDF)) {
			return getPdfText(in);
		}else if(suffix.equals(SUFFIX_HTML) || suffix.equals(SUFFIX_HTM)) {
			return getHtmlText(in);
		}else if(suffix.equals(SUFFIX_TXT)) {
			return getTxtText(in);
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static String getDocText(InputStream in) throws IOException {
		WordExtractor ex = new WordExtractor(in);
		String text = ex.getText();
		//HWPFDocument doc = new HWPFDocument(in);
		//String text = doc.getDocumentText();
		return text;
	}
	
	@SuppressWarnings("resource")
	public static String getDocxText(InputStream in) throws IOException {
		XWPFDocument xdoc = new XWPFDocument(in);
        XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
        String text = extractor.getText();
		return text;
	}
	
	public static String getPdfText(InputStream in) throws IOException {
		PDDocument document = PDDocument.load(in);
		int pages = document.getNumberOfPages();
		PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        stripper.setStartPage(1);
        stripper.setEndPage(pages);
		String str = stripper.getText(document);
		return str;
	}
	
	 /**  
     * @param fileInput 文件输入流  
     * @return 获得html的全部内容  
     */  
    public static String readHtml(InputStream fileInput) {   
        BufferedReader br=null;   
        StringBuffer sb = new  StringBuffer();   
        try {   
            br=new BufferedReader(new InputStreamReader(fileInput,  "utf-8"));            
            String temp=null;          
            while((temp=br.readLine())!=null){   
                sb.append(temp);   
            }              
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        return sb.toString();   
    }   
    /**  
     * @param fileInput 文件输入流  
     * @return 获得的html文本内容  
     */  
    public static String getHtmlText(InputStream fileInput) {   
        //得到body标签中的内容   
        String str= readHtml(fileInput);   
        StringBuffer buff = new StringBuffer();   
        int maxindex = str.length() - 1;   
        int begin = 0;   
        int end;               
        //截取>和<之间的内容   
        while((begin = str.indexOf('>',begin)) < maxindex){              
            end = str.indexOf('<',begin);   
            if(end - begin > 1){
            	String text = str.substring(++begin, end);
            	//去掉js中的内容
            	if(StringUtils.isNotEmpty(text.trim()) && !StringUtils.contains(text, "function")){
            		buff.append(text);
            	}
            }              
            begin = end+1;   
        };         
        return buff.toString();   
    }   

    /**  
     * @param fileInput 文件输入流 
     * @return 读出的txt的内容  
     */  
    public static String getTxtText(InputStream fileInput){
    	BufferedReader br=null;
    	StringBuffer buff = new StringBuffer();  
    	try {   
    		byte[] head = new byte[3];  
    		fileInput.read(head);    
    	    String code = "GBK";  
	        if (head[0] == -1 && head[1] == -2 ){
	        	code = "UTF-16";
	        }else if (head[0] == -2 && head[1] == -1 ){
	        	code = "Unicode";
	        }else if(head[0]==-17 && head[1]==-69 && head[2] ==-65){
	        	code = "UTF-8";
	        }  
            br = new BufferedReader(new InputStreamReader(fileInput,  code));
	        String temp = null;   
	        while((temp = br.readLine()) != null){   
	            buff.append(temp + "\r\n");   
	        }  
    	}catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        return buff.toString();        
    }
}
