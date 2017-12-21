package com.excellence.iaserver.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.ExternalOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;




/**
 * office转html类
 * 
 * @author huangjinyuan
 * @created 2017-3-21
 * @copyright Excellence co.
 */
public class LibreofficeConverter {
	
	private static final Log logger = LogFactory.getLog(LibreofficeConverter.class);
	
	private final DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
    
    private static OfficeManager officeManager = null;
    
    private OfficeDocumentConverter converter = null;
    
    private ExternalOfficeManagerConfiguration externalProcessOfficeManager = null;
    
    public void init() {
    	try {  
    	    logger.info("准备启动服务....");  
    	    try {  
    	        logger.info("尝试连接已启动的服务...");  
    	        externalProcessOfficeManager = new ExternalOfficeManagerConfiguration();  
    	        externalProcessOfficeManager.setConnectOnStart(true);  
    	        externalProcessOfficeManager.setPortNumber(8100);  
    	        officeManager = externalProcessOfficeManager.buildOfficeManager();  
    	        officeManager.start();  
    	        logger.info("office转换服务启动成功!");
    	        return;
    	    } catch (Exception ex) {  
    	        ex.printStackTrace();  
    	        logger.info("没有已启动的服务...");  
    	    }  
    	    logger.info("创建并连接新服务...");  
    	    configuration.setOfficeHome(this.getOfficeHome());// 设置OpenOffice.org安装目录
            configuration.setPortNumbers(Constant.OPEN_OFFICE_PORT); //设置端口  
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);  
            configuration.setTaskQueueTimeout(1000 * 60 * 5L);  
    	    configuration.setTaskExecutionTimeout(1000 * 60 * 5L);  
    	    officeManager = configuration.buildOfficeManager();  
    	    officeManager.start();  
    	    logger.info("office转换服务启动成功!");  
    	} catch (Exception ce) {  
    	    ce.printStackTrace();  
    	    logger.error("office转换服务启动失败!详细信息:" + ce);  
    	}
    }
    
    /**
     * 开启服务
     */
    /*private void openOffice() {
    	logger.warn("libreoffice服务正在启动....");
    	long begin = System.currentTimeMillis();
        try {
            officeManager.start();
            long end = System.currentTimeMillis();
            logger.warn("libreoffice服务已经启动,耗时"+(end-begin)/1000 + "秒");
        } catch (Exception e) {
        	logger.error("libreoffice转换服务启动失败!详细信息:" + e.getMessage());
            e.printStackTrace();
        }
    }*/
    
    /**
     * 关闭libreoffice
     */
    private void closeOffice() {
        logger.info("关闭libreoffice转换服务....");
        if (officeManager != null) {
            try {
                officeManager.stop();
                logger.info("libreoffice转换服务已关闭");
            } catch (Exception e) {
                logger.error("libreoffice转换服务关闭失败!详细信息:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
   /* public static File officeToHtml(String inputFilePath,String htmlFilePath) {
    	File htmlFile = new File(htmlFilePath);
    	try {
    		long startTime = System.currentTimeMillis();  
    		System.out.println("+++++++++++++"+officeManager);
            //打开服务  
    		if(officeManager==null){
    			startService();
    		}
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);  
            //开始转换  
            File file = new File(inputFilePath);
            if(file.exists()) {
            	converter.convert(file,htmlFile);
            		
            }
            long endTime = System.currentTimeMillis();
            System.out.println("转换成功，耗时：" + (endTime - startTime));  
            return htmlFile;
    	} catch (Exception e) {  
            e.printStackTrace();  
        } finally {
//        	stopService();
        }
    	return null;
    }*/
    
    
    public void destroy() {
		closeOffice();
    }
    
    public void doConvert(File srcFile, File destFile) throws Exception {
    	if(converter == null) converter = new OfficeDocumentConverter(officeManager);
		converter.convert(srcFile, destFile);
	}
    
    
    
    /**
     * 清除一些不需要的html标记
     * @param htmlStr 带有复杂html标记的html语句
     * @param docImgPath
     * @return 去除了不需要html标记的语句
     * @author huangjinyuan 
     * @created 2017-3-21
     * @copyright Excellence co.
     */
    protected static String clearFormat(String htmlStr, String docImgPath) {
        // 获取body内容的正则
        String bodyReg = "<body .*</body>";
        Pattern bodyPattern = Pattern.compile(bodyReg);
        Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
        if (bodyMatcher.find()) {
            // 获取BODY内容，并转化BODY标签为DIV
            htmlStr = bodyMatcher.group().replaceFirst("<body", "<div")
                .replaceAll("</body>", "</div>");
        }
        if(StringUtils.isEmpty(docImgPath)) {
        	Matcher ma = Pattern.compile("<\\s*IMG\\s+([^>]+)\\s*>").matcher(htmlStr);
        	if(ma.find()) {
        		ma.replaceAll("");
        	}
        	Matcher ma1 = Pattern.compile("<\\s*img\\s+([^>]+)\\s*>").matcher(htmlStr);
        	htmlStr = ma1.replaceAll("");
        } else {
        	 // 调整图片地址
            htmlStr = htmlStr.replaceAll("<img src=\"", "<img src=\"" + docImgPath
                + "/");
            htmlStr = htmlStr.replaceAll("<img src=\"", "<img src=\"" + docImgPath
                    + "/");
        }
        // 把<P></P>转换成</div></div>保留样式
        // content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)",
        // "<div$2</div>");
        // 把<P></P>转换成</div></div>并删除样式
        //htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
        // 删除不需要的标签
        //htmlStr = htmlStr.replaceAll("<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>","");
        // 删除不需要的属性
        //htmlStr = htmlStr.replaceAll("<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>","<$1$2>");
        return htmlStr;
        }
    
    /**
     * 转换成html文本
     * @param inputFilePath
     * @param htmlFilePath
     * @param imgPath
     * @return
     * @author huangjinyuan
     * @created 2017-3-21
     * @copyright Excellence co.
     */
    /*public static String toHtmlString(String inputFilePath,String htmlFilePath,String imgPath) {
//    	try{htmlFilePath = new String(htmlFilePath.getBytes(),"GBK");}catch(Exception ex){}
        // 转换word文档
    	File htmlFile = new File(htmlFilePath);
    	if(!htmlFile.exists()) {
    		htmlFile = officeToHtml(inputFilePath, htmlFilePath);
    	}
        if(htmlFile.exists()) {
        	// 获取html文件流
            StringBuffer htmlSb = new StringBuffer();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(htmlFile)));
                while (br.ready()) {
                htmlSb.append(br.readLine());
                }
                br.close();
                // 删除临时文件
                //htmlFile.delete();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // HTML文件字符串
            String htmlStr = htmlSb.toString();
            // 返回经过清洁的html文本
            htmlStr = clearFormat(htmlStr, imgPath);
            return htmlStr;
        }
        return null;
        }*/
    
    public static void main(String args[]) {
//    	String inputFilePath = "E:/newFile.docx";
//    	String outputFilePath = "E:/newFile.html";
//    	LibreofficeUtil.officeToHtml(inputFilePath, outputFilePath);
//    	System.out.println("===========os.name:"+System.getProperties().getProperty("os.name")); 
//    	exeCmd();
    }
    
    /**
     * 命令行啟動openOffice
     */
    public static void exeCmd() {  
        BufferedReader br = null;  
        try {  
        	String[] cmd = {Constant.OPEN_OFFICE_HOME+"program/soffice.exe",
        			"--invisible",
        			"--headless",
        			"--accept=\"socket,host=127.0.0.1,port="+Constant.OPEN_OFFICE_PORT+";urp\"",
        			"--nofirststartwizard"};
        	Process p = Runtime.getRuntime().exec(cmd);
        	
        	br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
    
    /**
     * 实现关闭soffice进程
     */
    public static void distorySoffice() {
        try {
        	Process process = null;
        	String osName = System.getProperties().getProperty("os.name");
        	if(osName.contains("Linux")){
        		String cmd = "ps -ef|grep libreoffice | awk '{print $2}' | xargs kill -9";
        		process=Runtime.getRuntime().exec(cmd);
        		System.out.println("Linux服务器openoffice正常关闭.......");
        	}else if(osName.contains("Windows")){
        		//显示进程
            	/*process=Runtime.getRuntime().exec("tasklist");
                Scanner in=new Scanner(process.getInputStream());
                while (in.hasNextLine()) {
                    String processString=in.nextLine();
                    if (processString.contains("soffice.bin")) {
                        //关闭soffice进程的命令
                        String cmd="taskkill /f /im soffice.bin";
                        process=Runtime.getRuntime().exec(cmd);
                        System.out.println("Windows服务器openoffice正常关闭.......");
                    }
                }*/
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String[] getSupportedDestTypes() {
		return "pdf,html".split(",");
	}
    
    public String[] getSupportedSrcTypes() {
		return "doc,dot,docx,xls,xlsx,ppt,pptx,rtf".split(",");
	}
    
    private String getOfficeHome() {
		String officeHome = Constant.getProperty("OPEN.office.home");
		return officeHome;
	}
    
    
}
