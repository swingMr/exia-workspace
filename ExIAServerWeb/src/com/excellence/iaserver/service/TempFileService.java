package com.excellence.iaserver.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


/**
 * ְ��:��ʱ�ļ��ϴ����ط���ӿڶ���
 * @author LJM
 *
 */
public interface TempFileService
{
	/**
	 * ����:��ĳ����ʱ�ļ�����д�뵽��������
	 * @param fileId			��ʱ�ļ�Ψһ��ʶ
	 * @param os				���������
	 * @throws Exception
	 */
	public void writeFile2Stream(String fileId,OutputStream os) throws Exception;
	
	/**
	 * ����:����һ����ʱ�ļ�
	 * @param fileTitle			��ʱ�ļ��ı���
	 * @param fileExt			�ļ���չ��
	 * @param file				����������
	 * @return					��ʱ�ļ���Ψһ��ʶ
	 * @throws Exception
	 */
	public String createFile(String fileTitle,String fileExt,MultipartFile file) throws Exception;
	
	/**
	 * ����:����һ����ʱ�ļ�
	 * @author LiuZeHang
	 * @param file				��ʱ�ļ�
	 * @param user				�û���Ϣ
	 * @return					��ʱ�ļ���Ψһ��ʶ
	 * @throws Exception
	 */
	public String createFile(MultipartFile file) throws Exception;
	
	/**
	 * ת���ļ���ʽ
	 * @param fileId
	 * @param targetExt
	 * @return
	 */
	public String convert(String fileId, String targetExt);
	
	/**
	 * ת��ΪͼƬ
	 * @param fileId
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public List<String> convert2Jpg(String fileId, int startPage, int pageSize);
	
	/**
	 * ת����txt
	 * @param fileId
	 * @return
	 */
	public String convert2Txt(String fileId);
	
}
