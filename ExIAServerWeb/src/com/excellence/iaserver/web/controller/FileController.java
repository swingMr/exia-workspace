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
 * 文件服务
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
	 * 上传文件
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
		//创建一个临时文件。
		String fileId = tempFileService.createFile(fileTitle,fileExt,file);
        obj.put("status", "1");
        obj.put("fileId", fileId);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
      

    /**  
     * 文件下载功能  
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
    	//文件路径
        String accessPath = ifile.getAccessPath();
        String oapath = System.getProperty("oapath");
        accessPath = accessPath.replaceFirst("file://", "").replace("{oapath}", oapath);
        //获取输入流  
        File file = new File(accessPath);
        if(!file.exists())
        	throw new RuntimeException("文件已失效!");
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //假如以中文名下载的话  
        String filename = ifile.getFileName()+"."+ifile.getFileExt();
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
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
     * 图片转换服务
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
    	String msg = "上传成功";
    	String fileTitle = MapUtils.getString(map,"fileTitle");
		String fileExt = MapUtils.getString(map,"fileExt");
		int startPage = MapUtils.getIntValue(map, "startPage",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",1);
		if(file.getOriginalFilename().contains(".jpg")||fileExt.contains("jpg")) {
			status = -99;
			msg = "该文件不需要进行转换";
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
		//创建一个临时文件。
		obj.put("data", arr);
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
    /**
     * 通过文件id转换图片
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
		//创建一个临时文件。
        String msg = "转换成功!";
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
        obj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
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
    	String msg = "上传成功";
    	String fileTitle = MapUtils.getString(map,"fileTitle");
		String fileExt = MapUtils.getString(map,"fileExt");
		if(file.getOriginalFilename().contains(".txt")||fileExt.contains("txt")) {
			status = -99;
			msg = "该文件不需要进行转换";
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
		//创建一个临时文件。
        obj.put("status", status);
        obj.put("msg", msg);
        obj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
    
    @RequestMapping(value="/convert/txt/upload/{fileId}")  
    public ModelAndView convertTxtById(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long startTime = System.currentTimeMillis();
		JSONObject obj = new JSONObject();
		//创建一个临时文件。
        String msg = "转换成功!";
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
        obj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
    
}
