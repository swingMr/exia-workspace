package com.excellence.iaserver.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.service.FileExtConfigService;
import com.excellence.iamp.service.IFileService;
import com.excellence.iamp.vo.FileExtConfig;
import com.excellence.iamp.vo.IFile;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iaserver.common.util.FileHandlerUtil;
import com.excellence.iaserver.common.util.PDFToImageUtil;
import com.excellence.iaserver.common.util.T;
import com.excellence.iaserver.service.DocConvertService;
import com.excellence.iaserver.service.TempFileService;
import com.sun.star.uno.RuntimeException;

@Service
public class TempFileServiceImpl implements TempFileService {

	@Autowired
	private IFileService iFileService;
	
	@Autowired
	private FileExtConfigService fileExtConfigService;
	
	@Autowired
	private DocConvertService docConvertService;
	
	public void writeFile2Stream(String fileId, OutputStream os)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public String createFile(String fileTitle, String fileExt, MultipartFile file)
			throws Exception {
		String oapath =System.getProperty("oapath");
		String date = T.format(T.getNow(), "yyyy-MM-dd");
		String[] time = StringUtils.split(date,"-");
		String relativePath = File.separator+"exiaserver"+File.separator+"temp"+File.separator+time[0] +File.separator + time[1] + File.separator + time[2] + File.separator ;
		String absolutePath = oapath + relativePath;
		File dir = new File(absolutePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		IFile ifile = new IFile();
        String fileName = file.getOriginalFilename();  
        int a= fileName.lastIndexOf(".");// aa.bb.xml
        if(StringUtils.isEmpty(fileExt)) {
        	fileExt = fileName.substring(a+1,fileName.length());
        }
        if(StringUtils.isEmpty(fileTitle)) {
        	fileTitle = fileName.substring(0,a);
        }
		String dname = T.format(T.getNow(), "yyyyMMddHHmmss") + "." + fileExt;
		String absoluteFilePath = absolutePath  + dname;
		File newFile=new File(absoluteFilePath);
		file.transferTo(newFile);
        ifile.setAccessPath("file://{oapath}" + relativePath + dname);
        String fileId = GUIDGenerator.getInstance().getGUID().replaceAll("-", "");
        FileExtConfig fileExtConfig = fileExtConfigService.getFileExtConfig();
        //过期时间
        if(fileExtConfig != null) {
        	long expireTime = fileExtConfig.getExpireTime();
        	ifile.setExpireDate(new Date(System.currentTimeMillis() + expireTime));
        }
        ifile.setFileId(fileId);
        ifile.setCreateDate(new Date());
        ifile.setStatus(EStatus.normal.getIndex());
        ifile.setIsTemp(EStatus.draft.getIndex());
        ifile.setFileExt(fileExt);
        ifile.setUpdateDate(new Date());
        ifile.setFileName(fileTitle);
        iFileService.create(ifile);
		return fileId;
	}
	
	public String createFile(MultipartFile file) throws Exception{
		return createFile(null,null,file);
	}

	public String convert(String fileId, String targetExt) {
		IFile file = iFileService.findById(fileId);
		if(file == null) throw new RuntimeException("该文件不存在!");
		FileExtConfig fileExtConfig = fileExtConfigService.getFileExtConfig();
		String fileExt = file.getFileExt();
		
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> convert2Jpg(String fileId,int startPage,int pageSize) {
		List<String> idList = new ArrayList<String>();
		IFile iFile = iFileService.findById(fileId);
		if(iFile == null) throw new RuntimeException("该文件不存在!");
		FileExtConfig fileExtConfig = fileExtConfigService.getFileExtConfig();
		String supportJpg = fileExtConfig.getJpgExt();
		if(StringUtils.isNotEmpty(supportJpg)) {
			//doc,docx,ppt,excel,pdf
			if(!supportJpg.contains(iFile.getFileExt())) {
				throw new RuntimeException("不支持该格式转换!");
			}
		}
		String accessPath = iFile.getAccessPath();
		String relativePath = StringUtils.substring(accessPath, 0, accessPath.lastIndexOf(File.separator));
        String oapath = System.getProperty("oapath");
        String absolutePath = accessPath.replaceFirst("file://", "").replace("{oapath}", oapath);
        File file = new File(absolutePath);
        if(!file.exists()) throw new RuntimeException("该文件不存在!");
		if(iFile.getFileExt().equals("pdf")) {
			List<String> list = PDFToImageUtil.getInstance().pdf2Image(1f, absolutePath,startPage,pageSize);
			if(list != null && list.size() > 0) {
				for(String path:list) {
					String picName = StringUtils.substring(path,path.lastIndexOf(File.separator)+1,path.length());
					path = relativePath + File.separator + picName;
					IFile ifile = new IFile();
					String fId = GUIDGenerator.getInstance().getGUID().replaceAll("-", "");
					ifile.setFileId(fId);
			        ifile.setCreateDate(new Date());
			        ifile.setStatus(EStatus.normal.getIndex());
			        ifile.setIsTemp(EStatus.draft.getIndex());
			        ifile.setFileExt("jpg");
			        ifile.setUpdateDate(new Date());
			        ifile.setAccessPath(path);
			        if(fileExtConfig != null) {
			        	long expireTime = fileExtConfig.getExpireTime();
			        	ifile.setExpireDate(new Date(System.currentTimeMillis() + expireTime));
			        }
			        ifile.setFileName(iFile.getFileName());
			        iFileService.create(ifile);
			        idList.add(fId);
				}
			}
		} else {//先转成pdf
			docConvertService.convertToPdf(absolutePath);
			String pdfPath = absolutePath.substring(0, absolutePath.lastIndexOf("."))+".pdf";
			List<String> list = PDFToImageUtil.getInstance().pdf2Image(1f, pdfPath,startPage,pageSize);
			//删除临时文件
			File pdf = new File(pdfPath);
			if(pdf.exists()) {
				pdf.delete();
			}
			if(list != null && list.size() > 0) {
				for(String path:list) {
					String picName = StringUtils.substring(path,path.lastIndexOf(File.separator)+1,path.length());
					path = relativePath + File.separator + picName;
					IFile ifile = new IFile();
					String fId = GUIDGenerator.getInstance().getGUID().replaceAll("-", "");
					ifile.setFileId(fId);
			        ifile.setCreateDate(new Date());
			        ifile.setStatus(EStatus.normal.getIndex());
			        ifile.setIsTemp(EStatus.draft.getIndex());
			        ifile.setFileExt("jpg");
			        ifile.setUpdateDate(new Date());
			        ifile.setAccessPath(path);
			        if(fileExtConfig != null) {
			        	long expireTime = fileExtConfig.getExpireTime();
			        	ifile.setExpireDate(new Date(System.currentTimeMillis() + expireTime));
			        }
			        ifile.setFileName(iFile.getFileName());
			        iFileService.create(ifile);
			        idList.add(fId);
				}
			}
		}
		return idList;
	}

	public String convert2Txt(String fileId) {
		IFile iFile = iFileService.findById(fileId);
		if(iFile == null) throw new RuntimeException("该文件不存在!");
		FileExtConfig fileExtConfig = fileExtConfigService.getFileExtConfig();
		String supportTxt = fileExtConfig.getTxtExt();
		if(StringUtils.isNotEmpty(supportTxt)) {
			if(!supportTxt.contains(iFile.getFileExt())) {
				throw new RuntimeException("不支持该格式转换!");
			}
		}
		String accessPath = iFile.getAccessPath();
		String relativePath = StringUtils.substring(accessPath, 0, accessPath.lastIndexOf(File.separator));
        String oapath = System.getProperty("oapath");
        String absolutePath = accessPath.replaceFirst("file://", "").replace("{oapath}", oapath);
        File file = new File(absolutePath);
        if(!file.exists()) throw new RuntimeException("该文件不存在!");
        try {
        	InputStream in = new FileInputStream(file);
            String content = FileHandlerUtil.getText("."+iFile.getFileExt(), in);
            String dname = T.format(T.getNow(), "yyyyMMddHHmmss") + ".txt";
            String targetPath = StringUtils.substring(absolutePath, 0, absolutePath.lastIndexOf(File.separator)) + File.separator + dname;
            FileWriter fw = new FileWriter(targetPath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            //写入文件结束
            String path = relativePath + File.separator + dname;
    		IFile ifile = new IFile();
    		String fId = GUIDGenerator.getInstance().getGUID().replaceAll("-", "");
    		ifile.setFileId(fId);
            ifile.setCreateDate(new Date());
            ifile.setStatus(EStatus.normal.getIndex());
            ifile.setIsTemp(EStatus.draft.getIndex());
            ifile.setFileExt("txt");
            ifile.setUpdateDate(new Date());
            ifile.setAccessPath(path);
            if(fileExtConfig != null) {
            	long expireTime = fileExtConfig.getExpireTime();
            	ifile.setExpireDate(new Date(System.currentTimeMillis() + expireTime));
            }
            ifile.setFileName(iFile.getFileName());
            iFileService.create(ifile);
            return fId;
        } catch (Exception e) {
        	e.printStackTrace();
		}
        return null;
	}

}
