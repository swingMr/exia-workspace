package com.excellence.iaserver.common.util;

import java.awt.AlphaComposite;  
import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
import java.awt.image.BufferedImage;  
import java.awt.image.RenderedImage;  
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.icepdf.core.pobjects.Document;  
import org.icepdf.core.pobjects.Page;  
import org.icepdf.core.util.GraphicsRenderingHints;  

  
/** 
 * ��pdf ת���� ͼƬ 
 * @author chenghq 
 * ����ʱ�䣺2017��4��18�� 
 */  
public class PDFToImageUtil {  
      
    // ˮӡ͸����  
    private static float alpha = 0.5f;  
    // ˮӡ����λ��  
    private static int positionWidth = 150;  
    // ˮӡ����λ��  
    private static int positionHeight = 300;  
    // ˮӡ��������  
    private static Font font = new Font("����", Font.BOLD, 26);  
    // ˮӡ������ɫ  
    private static Color color = Color.GRAY;  
    
    private static PDFToImageUtil pDFToImageUtil = null;
    
    public static PDFToImageUtil getInstance() {
    	if(pDFToImageUtil == null)
    		pDFToImageUtil = new PDFToImageUtil();
    	return pDFToImageUtil;
    }
    
    //Ĭ��ֻת��1ҳ
    public List<String> pdf2Image(float zoom,String inputFile,int startNum, int pageSize) {
    	List<String> list = null;  
        Document document = null;
        //zoom = 0.28f;
        try {  
            list = new ArrayList<String>();  
            document = new Document();  
            document.setFile(inputFile);
            String outputFile = StringUtils.substring(inputFile, 0, inputFile.lastIndexOf(".pdf"));
            float rotation = 0;  
            int maxPages = document.getPageTree().getNumberOfPages();
            int start = startNum - 1;
            if(start > maxPages -1) {
            	start = maxPages -1;
            }
            int end = maxPages;
            if(start + pageSize < maxPages) {
            	end = start + pageSize;
            }
            for (int i = start; i < end; i++) {  
                BufferedImage bfimage = (BufferedImage) document.getPageImage(i,  GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, zoom);  
                RenderedImage rendImage = bfimage;  
                ImageIO.write(rendImage, "jpg", new File(outputFile+"_"+i+".jpg"));  
                bfimage.flush();  
                list.add(outputFile+"_"+i+".jpg");  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        if(document!=null){  
            document.dispose();  
        }  
        return list;  
    }
    
  
    /** 
     * ����pdf������ͼ 
     * @param zoom  ����ͼ��ʾ������1��ʾ�����ţ�0.5��ʾ��С��50% 
     * @param inputFile        ��Ҫ��������ͼ���鼮������·�� 
     * @param outputFile    ��������ͼ�ķ���·�� 
     */  
    public List<String> pdf2Image(float zoom,String inputFile, String outputFile) {  
        List<String> list = null;  
        Document document = null;
        //zoom = 0.28f;
        try {  
            list = new ArrayList(0);  
            document = new Document();  
            document.setFile(inputFile);  
            float rotation = 0;  
            int maxPages = document.getPageTree().getNumberOfPages();  
            for (int i = 0; i < 1; i++) {  
                BufferedImage bfimage = (BufferedImage) document.getPageImage(i,  GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, zoom);  
                  
                //bfimage = setGraphics(bfimage);  
                  
                RenderedImage rendImage = bfimage;  
                ImageIO.write(rendImage, "jpg", new File(outputFile+".jpg"));  
                bfimage.flush();  
                list.add(outputFile+".jpg");  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        if(document!=null){  
            document.dispose();  
        }  
        return list;  
    }  
      
      
      
    public BufferedImage setGraphics(BufferedImage bfimage){  
        Graphics2D g = bfimage.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        // 5������ˮӡ������ɫ  
        g.setColor(color);  
        // 6������ˮӡ����Font  
        g.setFont(font);  
        // 7������ˮӡ����͸����  
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));  
        //������ת  
        g.rotate(-Math.PI/6);  
        g.drawString("������Ϣ", 0, (bfimage.getHeight()/2)*1);  
        // 9���ͷ���Դ  
        g.dispose();  
        return bfimage;  
    }  
    
    public static void main(String[] args) {  
 	   //���� pdf ���� ͼƬ ����ˮӡ��  
 	  PDFToImageUtil pdf = new PDFToImageUtil();  
 	  pdf.pdf2Image(0.38f,"D:/Documents/Downloads/����������Ϣ��������������ָ���˵��.pdf", "E:/");  
 	} 
      
}  
