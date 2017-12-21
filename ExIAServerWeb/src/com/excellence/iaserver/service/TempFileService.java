package com.excellence.iaserver.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


/**
 * 职责:临时文件上传下载服务接口定义
 * @author LJM
 *
 */
public interface TempFileService
{
	/**
	 * 功能:把某个临时文件内容写入到流对象中
	 * @param fileId			临时文件唯一标识
	 * @param os				输出流对象
	 * @throws Exception
	 */
	public void writeFile2Stream(String fileId,OutputStream os) throws Exception;
	
	/**
	 * 功能:创建一个临时文件
	 * @param fileTitle			临时文件的标题
	 * @param fileExt			文件扩展名
	 * @param file				输入流对象
	 * @return					临时文件的唯一标识
	 * @throws Exception
	 */
	public String createFile(String fileTitle,String fileExt,MultipartFile file) throws Exception;
	
	/**
	 * 功能:创建一个临时文件
	 * @author LiuZeHang
	 * @param file				临时文件
	 * @param user				用户信息
	 * @return					临时文件的唯一标识
	 * @throws Exception
	 */
	public String createFile(MultipartFile file) throws Exception;
	
	/**
	 * 转换文件格式
	 * @param fileId
	 * @param targetExt
	 * @return
	 */
	public String convert(String fileId, String targetExt);
	
	/**
	 * 转换为图片
	 * @param fileId
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public List<String> convert2Jpg(String fileId, int startPage, int pageSize);
	
	/**
	 * 转换成txt
	 * @param fileId
	 * @return
	 */
	public String convert2Txt(String fileId);
	
}
