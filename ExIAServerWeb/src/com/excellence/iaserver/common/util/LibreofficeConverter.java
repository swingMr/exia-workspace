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
 * officeתhtml��
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
    	    logger.info("׼����������....");  
    	    try {  
    	        logger.info("���������������ķ���...");  
    	        externalProcessOfficeManager = new ExternalOfficeManagerConfiguration();  
    	        externalProcessOfficeManager.setConnectOnStart(true);  
    	        externalProcessOfficeManager.setPortNumber(8100);  
    	        officeManager = externalProcessOfficeManager.buildOfficeManager();  
    	        officeManager.start();  
    	        logger.info("officeת�����������ɹ�!");
    	        return;
    	    } catch (Exception ex) {  
    	        ex.printStackTrace();  
    	        logger.info("û���������ķ���...");  
    	    }  
    	    logger.info("�����������·���...");  
    	    configuration.setOfficeHome(this.getOfficeHome());// ����OpenOffice.org��װĿ¼
            configuration.setPortNumbers(Constant.OPEN_OFFICE_PORT); //���ö˿�  
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);  
            configuration.setTaskQueueTimeout(1000 * 60 * 5L);  
    	    configuration.setTaskExecutionTimeout(1000 * 60 * 5L);  
    	    officeManager = configuration.buildOfficeManager();  
    	    officeManager.start();  
    	    logger.info("officeת�����������ɹ�!");  
    	} catch (Exception ce) {  
    	    ce.printStackTrace();  
    	    logger.error("officeת����������ʧ��!��ϸ��Ϣ:" + ce);  
    	}
    }
    
    /**
     * ��������
     */
    /*private void openOffice() {
    	logger.warn("libreoffice������������....");
    	long begin = System.currentTimeMillis();
        try {
            officeManager.start();
            long end = System.currentTimeMillis();
            logger.warn("libreoffice�����Ѿ�����,��ʱ"+(end-begin)/1000 + "��");
        } catch (Exception e) {
        	logger.error("libreofficeת����������ʧ��!��ϸ��Ϣ:" + e.getMessage());
            e.printStackTrace();
        }
    }*/
    
    /**
     * �ر�libreoffice
     */
    private void closeOffice() {
        logger.info("�ر�libreofficeת������....");
        if (officeManager != null) {
            try {
                officeManager.stop();
                logger.info("libreofficeת�������ѹر�");
            } catch (Exception e) {
                logger.error("libreofficeת������ر�ʧ��!��ϸ��Ϣ:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
   /* public static File officeToHtml(String inputFilePath,String htmlFilePath) {
    	File htmlFile = new File(htmlFilePath);
    	try {
    		long startTime = System.currentTimeMillis();  
    		System.out.println("+++++++++++++"+officeManager);
            //�򿪷���  
    		if(officeManager==null){
    			startService();
    		}
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);  
            //��ʼת��  
            File file = new File(inputFilePath);
            if(file.exists()) {
            	converter.convert(file,htmlFile);
            		
            }
            long endTime = System.currentTimeMillis();
            System.out.println("ת���ɹ�����ʱ��" + (endTime - startTime));  
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
     * ���һЩ����Ҫ��html���
     * @param htmlStr ���и���html��ǵ�html���
     * @param docImgPath
     * @return ȥ���˲���Ҫhtml��ǵ����
     * @author huangjinyuan 
     * @created 2017-3-21
     * @copyright Excellence co.
     */
    protected static String clearFormat(String htmlStr, String docImgPath) {
        // ��ȡbody���ݵ�����
        String bodyReg = "<body .*</body>";
        Pattern bodyPattern = Pattern.compile(bodyReg);
        Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
        if (bodyMatcher.find()) {
            // ��ȡBODY���ݣ���ת��BODY��ǩΪDIV
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
        	 // ����ͼƬ��ַ
            htmlStr = htmlStr.replaceAll("<img src=\"", "<img src=\"" + docImgPath
                + "/");
            htmlStr = htmlStr.replaceAll("<img src=\"", "<img src=\"" + docImgPath
                    + "/");
        }
        // ��<P></P>ת����</div></div>������ʽ
        // content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)",
        // "<div$2</div>");
        // ��<P></P>ת����</div></div>��ɾ����ʽ
        //htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
        // ɾ������Ҫ�ı�ǩ
        //htmlStr = htmlStr.replaceAll("<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>","");
        // ɾ������Ҫ������
        //htmlStr = htmlStr.replaceAll("<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>","<$1$2>");
        return htmlStr;
        }
    
    /**
     * ת����html�ı�
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
        // ת��word�ĵ�
    	File htmlFile = new File(htmlFilePath);
    	if(!htmlFile.exists()) {
    		htmlFile = officeToHtml(inputFilePath, htmlFilePath);
    	}
        if(htmlFile.exists()) {
        	// ��ȡhtml�ļ���
            StringBuffer htmlSb = new StringBuffer();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(htmlFile)));
                while (br.ready()) {
                htmlSb.append(br.readLine());
                }
                br.close();
                // ɾ����ʱ�ļ�
                //htmlFile.delete();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // HTML�ļ��ַ���
            String htmlStr = htmlSb.toString();
            // ���ؾ�������html�ı�
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
     * �����І���openOffice
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
     * ʵ�ֹر�soffice����
     */
    public static void distorySoffice() {
        try {
        	Process process = null;
        	String osName = System.getProperties().getProperty("os.name");
        	if(osName.contains("Linux")){
        		String cmd = "ps -ef|grep libreoffice | awk '{print $2}' | xargs kill -9";
        		process=Runtime.getRuntime().exec(cmd);
        		System.out.println("Linux������openoffice�����ر�.......");
        	}else if(osName.contains("Windows")){
        		//��ʾ����
            	/*process=Runtime.getRuntime().exec("tasklist");
                Scanner in=new Scanner(process.getInputStream());
                while (in.hasNextLine()) {
                    String processString=in.nextLine();
                    if (processString.contains("soffice.bin")) {
                        //�ر�soffice���̵�����
                        String cmd="taskkill /f /im soffice.bin";
                        process=Runtime.getRuntime().exec(cmd);
                        System.out.println("Windows������openoffice�����ر�.......");
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
