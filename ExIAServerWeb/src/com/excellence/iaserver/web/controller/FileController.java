package com.excellence.iaserver.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iamp.service.IFileService;
import com.excellence.iamp.vo.IFile;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.FileHandlerUtil;
import com.excellence.iaserver.common.util.PDFToImageUtil;
import com.excellence.iaserver.common.util.T;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.DocConvertService;
import com.excellence.iaserver.service.TempFileService;

/**
 * �ļ�����
 * @author huangjinyuan
 *
 */
@Controller
@RequestMapping("/services/file")
public class FileController {
	@Autowired
	private IFileService iFileService;
	
	@Autowired
	private TempFileService tempFileService;
	
	/**
	 * �ϴ��ļ�
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/ud/upload")
	public ModelAndView upload(MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String fileTitle = MapUtils.getString(map,"fileTitle");
		String fileExt = MapUtils.getString(map,"fileExt");
		JSONObject obj = new JSONObject();
		//����һ����ʱ�ļ���
		String fileId = tempFileService.createFile(fileTitle,fileExt,file);
        obj.put("status", "1");
        obj.put("fileId", fileId);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
      

    /**  
     * �ļ����ع���  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping(value="/ud/download/{fileId}")  
    public void down(@PathVariable("fileId") String fileId,HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	IFile ifile = new IFile();
    	if(StringUtils.isNotEmpty(fileId)){
    		ifile = iFileService.findById(fileId);
        }
    	//�ļ�·��
        String accessPath = ifile.getAccessPath();
        String oapath = System.getProperty("oapath");
        accessPath = accessPath.replaceFirst("file://", "").replace("{oapath}", oapath);
        //��ȡ������  
        File file = new File(accessPath);
        if(!file.exists())
        	throw new RuntimeException("�ļ���ʧЧ!");
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //���������������صĻ�  
        String filename = ifile.getFileName()+"."+ifile.getFileExt();
        //ת�룬����ļ�����������  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //�����ļ�����ͷ  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }
    
    /**
     * ͼƬת������
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/convert/pic/upload")
    public ModelAndView convertPicByFile(MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long startTime = System.currentTimeMillis();
    	Map map = WebUtil.requestToMap(request);
    	JSONArray arr = new JSONArray();
    	JSONObject obj = new JSONObject();
    	int status = 1;
    	String msg = "�ϴ��ɹ�";
    	String fileTitle = MapUtils.getString(map,"fileTitle");
		String fileExt = MapUtils.getString(map,"fileExt");
		int startPage = MapUtils.getIntValue(map, "startPage",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",1);
		if(file.getOriginalFilename().contains(".jpg")||fileExt.contains("jpg")) {
			status = -99;
			msg = "���ļ�����Ҫ����ת��";
		} else {
			String tempId = tempFileService.createFile(fileTitle,fileExt,file);
			try {
				List<String> ids = tempFileService.convert2Jpg(tempId,startPage,pageSize);
				if(ids != null && ids.size() > 0) {
	        		arr = new JSONArray(ids);
	        	}
			} catch (Exception e) {
				status = -99;
	        	msg = e.getMessage();
	        	e.printStackTrace();
			}
		}
		//����һ����ʱ�ļ���
		obj.put("data", arr);
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
    /**
     * ͨ���ļ�idת��ͼƬ
     * @param fileId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/convert/pic/upload/{fileId}")  
    public ModelAndView convertPicById(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long startTime = System.currentTimeMillis();
    	Map map = WebUtil.requestToMap(request);
    	JSONObject obj = new JSONObject();
    	JSONArray arr = new JSONArray();
		//����һ����ʱ�ļ���
        String msg = "ת���ɹ�!";
        int status = 1;
        int startPage = MapUtils.getIntValue(map, "startPage",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",1);
        try {
        	List<String> ids = tempFileService.convert2Jpg(fileId,startPage,pageSize);
        	if(ids != null && ids.size() > 0) {
        		arr = new JSONArray(ids);
        	}
        } catch (Exception e) {
        	status = -99;
        	msg = e.getMessage();
        	e.printStackTrace();
        }
        obj.put("data", arr);
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/convert/txt/upload")
    public ModelAndView convertTxtByFile(MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long startTime = System.currentTimeMillis();
    	Map map = WebUtil.requestToMap(request);
    	JSONObject obj = new JSONObject();
    	int status = 1;
    	String msg = "�ϴ��ɹ�";
    	String fileTitle = MapUtils.getString(map,"fileTitle");
		String fileExt = MapUtils.getString(map,"fileExt");
		if(file.getOriginalFilename().contains(".txt")||fileExt.contains("txt")) {
			status = -99;
			msg = "���ļ�����Ҫ����ת��";
		} else {
			String tempId = tempFileService.createFile(fileTitle,fileExt,file);
			
			String fileId = null;
			try {
				fileId = tempFileService.convert2Txt(tempId);
				obj.put("data", fileId);
			} catch (Exception e) {
				status = -99;
	        	msg = e.getMessage();
	        	e.printStackTrace();
			}
		}
		//����һ����ʱ�ļ���
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
    
    @RequestMapping(value="/convert/txt/upload/{fileId}")  
    public ModelAndView convertTxtById(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long startTime = System.currentTimeMillis();
		JSONObject obj = new JSONObject();
		//����һ����ʱ�ļ���
        String msg = "ת���ɹ�!";
        int status = 1;
        String tempId = null;
        try {
        	tempId = tempFileService.convert2Txt(fileId);
        	obj.put("data", tempId);
        } catch (Exception e) {
        	status = -99;
        	msg = e.getMessage();
        	e.printStackTrace();
        }
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
}
