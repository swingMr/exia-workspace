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
 * 将pdf 转化成 图片 
 * @author chenghq 
 * 创建时间：2017年4月18日 
 */  
public class PDFToImageUtil {  
      
    // 水印透明度  
    private static float alpha = 0.5f;  
    // 水印横向位置  
    private static int positionWidth = 150;  
    // 水印纵向位置  
    private static int positionHeight = 300;  
    // 水印文字字体  
    private static Font font = new Font("仿宋", Font.BOLD, 26);  
    // 水印文字颜色  
    private static Color color = Color.GRAY;  
    
    private static PDFToImageUtil pDFToImageUtil = null;
    
    public static PDFToImageUtil getInstance() {
    	if(pDFToImageUtil == null)
    		pDFToImageUtil = new PDFToImageUtil();
    	return pDFToImageUtil;
    }
    
    //默认只转第1页
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
     * 生成pdf的缩略图 
     * @param zoom  缩略图显示倍数，1表示不缩放，0.5表示缩小到50% 
     * @param inputFile        需要生成缩略图的书籍的完整路径 
     * @param outputFile    生成缩略图的放置路径 
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
        // 5、设置水印文字颜色  
        g.setColor(color);  
        // 6、设置水印文字Font  
        g.setFont(font);  
        // 7、设置水印文字透明度  
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));  
        //设置旋转  
        g.rotate(-Math.PI/6);  
        g.drawString("京华信息", 0, (bfimage.getHeight()/2)*1);  
        // 9、释放资源  
        g.dispose();  
        return bfimage;  
    }  
    
    public static void main(String[] args) {  
 	   //测试 pdf 生成 图片 （加水印）  
 	  PDFToImageUtil pdf = new PDFToImageUtil();  
 	  pdf.pdf2Image(0.38f,"D:/Documents/Downloads/关于政府信息公开第三方评估指标的说明.pdf", "E:/");  
 	} 
      
}  
