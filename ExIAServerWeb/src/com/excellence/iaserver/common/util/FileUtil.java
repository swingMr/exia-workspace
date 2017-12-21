package com.excellence.iaserver.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * �ļ�����������
 * @author Huangyb
 * @date 2014-7-15 ����2:02:48
 * @2014 Excellence
 */
public abstract class FileUtil {
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * ɾ���ļ�
	 * @param sPath
	 * 		�ļ�·��
	 * @return
	 * 		�Ƿ�ɾ���ɹ�
	 */
	public static Boolean deleteFile(String path) {
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		try{
			new File(path).delete();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * ɾ���ļ�
	 * @param sPath
	 * 		�ļ�·��
	 * @return
	 * 		�Ƿ�ɾ���ɹ�
	 */
	public static Boolean deleteFile(File file) {
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		try{
			file.delete();
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public static Boolean deleteDirectory(String path) {
		// ���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���
		if (!path.endsWith(File.separator))
			path = path + File.separator;
		return FileUtil.deleteDirectory(new File(path));
	}
	
	/**
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
	 * @param sPath
	 * 		��ɾ��Ŀ¼���ļ�·��
	 * @return 
	 * 		Ŀ¼ɾ���ɹ�����true�����򷵻�false
	 */
	public static Boolean deleteDirectory(File dirFile) {
		if(dirFile==null)return false;
		
		// ���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
		if (!dirFile.exists() || !dirFile.isDirectory())
			return false;

		boolean flag = true;

		// ɾ���ļ����µ������ļ�(������Ŀ¼)
		File[] files = dirFile.listFiles();
		for (int i = 0; files!=null && i < files.length; i++) {
			if (files[i].isFile()) { // ɾ�����ļ�
				if (!(flag = deleteFile(files[i].getAbsolutePath())))
					break;
			} else { // ɾ����Ŀ¼
				if (!(flag = deleteDirectory(files[i].getAbsolutePath())))
					break;
			}
		}
		if (!flag)
			return false;

		// ɾ����ǰĿ¼
		return dirFile.delete();
	}
	
	/**
	 * �޸��ļ�������
	 * @param oldDir
	 * 		���ļ���·��
	 * @param newDir
	 * 	        ���ļ���·��
	 * @return
	 * 		�Ƿ񴴽��ɹ�
	 */
	public static Boolean renameToNewDir(String oldDir, String newDir) {  
		oldDir = oldDir.replaceAll("\\\\", "/");
		oldDir = oldDir.substring(0, oldDir.lastIndexOf("/") + 1);
	    File old = new File(oldDir); 
	    newDir = newDir.replaceAll("\\\\", "/");
	    newDir = newDir.substring(0, oldDir.lastIndexOf("/") + 1);
	    return old.renameTo(new File(newDir));
	}  
	
	/**
	 * �����ļ�������·��
	 * @param fileName
	 * 		�ļ���
	 * @return
	 * 		�Ƿ񴴽��ɹ�
	 */
	public static Boolean mkdir(String filePath) {
		if(FileUtil.isExists(filePath))return true;
		
		filePath = filePath.replaceAll("\\\\", "/");
		filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		File path = new File(filePath);
		if(path.isDirectory())return true;
		return path.mkdirs();
	}
	
	/**
	 * �������ļ�
	 * @param fileName 
	 * 		�ļ���
	 * @return 
	 * 		�Ƿ񴴽��ɹ�
	 * @throws IOException 
	 */
	public static Boolean createFile(String fileName) throws IOException{
		if(FileUtil.isExists(fileName))return true;
		File file = new File(fileName);
		file.createNewFile();
		return file.isFile();
	}
	
	/**
	 * ��ȡ�ļ���С
	 * @param fileName
	 * 		�ļ���
	 * @return
	 * 		�ļ���С
	 * @throws Exception
	 * 		�ļ��������쳣
	 */
	public static Long getFileSizes(String fileName) throws Exception {
		return getFileSizes(new File(fileName));
	}
	
	/**
	 * ��ȡ�ļ���С
	 * @param file
	 * 		�ļ�
	 * @return
	 * 		�ļ���С
	 * @throws Exception
	 * 		�ļ��������쳣
	 */
	public static Long getFileSizes(File file) throws Exception {
		
		long s = 0;
		if (file.exists()) {
			InputStream inputStream = null;
			try{
				inputStream = new FileInputStream(file);
				s = inputStream.available();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if(inputStream!=null){
					try{ inputStream.close(); }catch(Exception ex){ ex.printStackTrace(); }
				}
			}
		} else {
			throw new IOException("�ļ�������");
		}
		
		return s;
	}
	
	/**
	 * ��ȡ��ǰĿ¼�µ���Ŀ¼����
	 * @param dir
	 * 		��ѯ��Ŀ¼
	 * @return
	 * 		��Ŀ¼����
	 */
	public static Integer getDirCount(String dir) {
		
		File f = new File(dir);
		int count = 0;
		File flist[] = f.listFiles();
		if(flist==null) return 0;
		count = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (!flist[i].isDirectory())count--;
		}
		return count;
	}
	
	public static String getDirectory(String fileName){
		
		File file = new File(fileName);
		if(!file.exists())return "";
		if(file.isDirectory())return file.getPath();
		return file.getParent();
	}
	
	/**
	 * ��ȡ���е���Ŀ¼
	 * @param path
	 * 		��Ŀ¼
	 * @return
	 */
	public static List<File> getDirs(String path){
		
		File root = new File(path);
		if(!root.isDirectory())return null;
		List<File> dirs = new ArrayList<File>();
		
		File[] fs = root.listFiles();
		if(fs==null) return null;
		for(File file : fs){
			if(file.isDirectory())dirs.add(file);
		}
		return dirs;
	}

	/**
	 * ����Ŀ¼�µ������ļ�
	 * @param path
	 * 		�����ҵ�Ŀ¼
	 * @return
	 * 		�����ļ��б�
	 */
	public static List<File> getFiles(String path){
		return getFiles(path, null);
	}
	
	/**
	 * ����Ŀ¼�µ������ļ�
	 * @param path
	 * 		�����ҵ�Ŀ¼
	 * @return
	 * 		�����ļ��б�
	 */
	public static List<File> getFiles(String path, FileFilter filter){
		return FileUtil.getFiles(new File(path), filter, true);
	}
	
	/**
	 * ����Ŀ¼�µ������ļ�
	 * @param isRecursion
	 * 		�Ƿ�ݹ����������Ŀ¼�����ļ�
	 * @param root
	 * 		�����ҵ�Ŀ¼
	 * @param filter
	 * 		�ļ������������ڹ�����Ҫ���ҵ��ļ�
	   FileFilter filter = new FileFilter(){
			public boolean accept(File file) {
				String name = file.getName();
				return name.endsWith(".txt"); 
			}
	   };
	 * @return
	 * 		�����ļ��б�
	 */
	public static List<File> getFiles(File root, FileFilter filter, boolean isRecursion){
		if(root==null)return null;
		
		List<File> files = new ArrayList<File>();
		if(!root.isDirectory()){
			files.add(root);
			return files;
		}
		File[] fs = root.listFiles(filter);
		if(fs==null) return null;
		for(File file : fs){
			files.add(file);
			if(file.isDirectory() && isRecursion){
				files.addAll(getFiles(file.getAbsolutePath(), filter));
			}
		}
		return files;
	}
	
	/**
	 * ׷�ӷ�ʽд���ļ�
	 * @param fileName
	 * 		д����ļ�·��
	 * @param stream
	 * 		��д��Ķ�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Long appendFileByStream(String fileName, InputStream stream) {
		if (!mkdir(fileName))return -1L;
		return writeFileByStream(new File(fileName), stream, true);
	}
	
	/**
	 * ׷�ӷ�ʽд���ļ�
	 * @param fileName
	 * 		д����ļ�·��
	 * @param stream
	 * 		��д��Ķ�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Long appendFileByStream(File file, InputStream stream) {
		return writeFileByStream(file, stream, true);
	}
	
	/**
	 * д���ļ�
	 * @param fileName
	 * 		д����ļ�·��
	 * @param stream
	 * 		��д��Ķ�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Long writeFileByStream(String fileName, InputStream stream) {
		if (!mkdir(fileName))return -1L;
		return writeFileByStream(new File(fileName), stream, false);
	}
	
	/**
	 * д���ļ�
	 * @param file
	 * 		д����ļ�
	 * @param stream
	 * 		��д��Ķ�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Long writeFileByStream(File file, InputStream stream) {
		return writeFileByStream(file, stream, false);
	}
	
	/**
	 * �Ѷ�������д���ļ���
	 * @param file
	 * 		д����ļ�
	 * @param stream
	 * 		��д��Ķ�������
	 * @param append
	 * 		�Ƿ�׷��
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	private static Long writeFileByStream(File file, InputStream stream, boolean append) {
		OutputStream out = null;
		try {
			if (!file.exists() && !file.createNewFile())return -1L;
			out = new BufferedOutputStream(new FileOutputStream(file, append));
			byte[] bytes = new byte[2048]; 
			int len = 0;  
	        while((len = stream.read(bytes))!=-1){  
	        	out.write(bytes, 0, len);  
	        }
			return file.length();
		} catch (IOException ex) {
			return -1L;
		} finally {
			if (out != null)
				try { out.flush(); out.close(); } catch (IOException e) {}
		}
	}
	
	/**
	 * ������д���ļ�
	 * @param fileName
	 * 		�ļ�·��
	 * @param context
	 * 		��д�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Boolean writeFileByString(String fileName, String context) {
		return writeFileByString(new File(fileName), context);
	}
	
	/**
	 * ������д���ļ�
	 * @param file
	 * 		�ļ�
	 * @param context
	 * 		��д�������
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Boolean writeFileByString(File file, String context) {
		return writeFileByString(file, context, DEFAULT_ENCODING, false);
	}
	
	/**
	 * ������д���ļ�
	 * @param fileName
	 * 		�ļ�·��
	 * @param context
	 * 		��д�������
	 * @param charset
	 * 		д�����ݵı���
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Boolean writeFileByString(String fileName, String context, String charset) {
		return writeFileByString(new File(fileName), context, charset, false);
	}
	
	/**
	 * ������д���ļ�
	 * @param file
	 * 		�ļ�
	 * @param context
	 * 		��д�������
	 * @param charset
	 * 		д�����ݵı���
	 * @return
	 * 		��д�������
	 */
	public static Boolean writeFileByString(File file, String context, String charset){
		return writeFileByString(file, context, charset, false);
	}
	
	/**
	 * ������д���ļ�
	 * @param file
	 * 		�ļ�
	 * @param context
	 * 		��д�������
	 * @param charset
	 * 		д�����ݵı���
	 * @param append
	 * 		�Ƿ�׷������
	 * @return
	 * 		��д�������
	 */
	public static Boolean writeFileByString(File file, String context, String charset, boolean append) {
		if (!mkdir(file.getAbsolutePath()))return false;
		BufferedOutputStream buf = null;
		try {
			if (!file.exists() && !file.createNewFile())return false;
			buf = new BufferedOutputStream(new FileOutputStream(file, append));
			byte[] bytes = context.getBytes(charset);
			buf.write(bytes, 0, bytes.length);
			return true;
		} catch (IOException ex) {
			return false;
		} finally {
			if(buf != null){
				try{ buf.flush(); buf.close(); }catch(IOException e){}
			}
		}
	}
	
	/**
	 * ���л����󣬰Ѷ���д���ļ���
	 * @param fileName
	 * 		д����ļ�·��
	 * @param serializable
	 * 		��Ҫ���л��Ķ���
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Boolean writeFileByObject(String fileName,
			Serializable serializable) {
		if (!mkdir(fileName))return false;
		return writeFileByObject(new File(fileName), serializable);
	}
	
	/**
	 * ���л����󣬰Ѷ���д���ļ���
	 * @param file
	 * 		д����ļ�
	 * @param serializable
	 * 		��Ҫ���л��Ķ���
	 * @return
	 * 		�Ƿ�д��ɹ�
	 */
	public static Boolean writeFileByObject(File file,
			Serializable serializable) {
		OutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			if (!file.exists() && !file.createNewFile())
				return false;
			out = new FileOutputStream(file);
			oos = new ObjectOutputStream(out);
			oos.writeObject(serializable);
			oos.flush();
			return true;
		} catch (IOException ex) {
			return false;
		} finally {
			if (out != null)
				try { out.close(); } catch (IOException e) {}
			if (oos != null)
				try { oos.close(); } catch (Exception e) {}
		}
	}
	
	public static Boolean newWriteFileByObject(String fileName,
			Serializable serializable) {
		if (!mkdir(fileName))
			return false;
		return newWriteFileByObject(new File(fileName), serializable);
	}
	
	public static Boolean newWriteFileByObject(File file,
			Serializable serializable){
		ObjectOutputStream oos = null;
		try {
			if (!file.exists() && !file.createNewFile())
				return false;
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			oos.writeObject(serializable);
			return true;
		} catch (IOException ex) {
			return writeFileByObject(file, serializable);
		} finally {
			if (oos != null){
				try { oos.flush(); oos.close(); } 
				catch (Exception e) {}
			}
		}
	}
	
	/**
	 * ��ȡ�ļ���Bytes
	 * @param fileName
	 * 		�ļ���
	 * @param pos
	 * 		��ʼλ��
	 * @param len
	 * 		��ȡ��С
	 * @return
	 * 		bytes����
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(String fileName, long pos, int len) throws IOException{
		return readFileToBytes(new File(fileName), pos, len);
	}
	
	/**
	 * ��ȡ�ļ���Bytes
	 * @param file
	 * 		�ļ�
	 * @param pos
	 * 		��ʼλ��
	 * @param len
	 * 		��ȡ��С
	 * @return
	 * 		bytes����
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(File file, long pos, int len) throws IOException{
		RandomAccessFile raf = null;
		try{
			raf = new RandomAccessFile(file, "r");
			if(pos >= raf.length())return new byte[0];
			raf.seek(pos);
			if((raf.length()-pos)< len)
				len = (int)(raf.length()-pos);
			byte[] bytes = new byte[len];
			raf.read(bytes);
			return bytes;
		}catch(IOException ex){
			throw ex;
		}finally{
			if(raf!=null){ raf.close(); }
		}
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @param fileName
	 * 		�ļ�·��
	 * @return
	 * 		����ȡ������
	 */
	public static String readFileToString(String fileName) {
		return readFileToString(new File(fileName));
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @param file
	 * 		�ļ�
	 * @return
	 * 		����ȡ������
	 */
	public static String readFileToString(File file){
		return readFileToString(file, DEFAULT_ENCODING);
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @param fileName
	 * 		�ļ�·��
	 * @param charset
	 * 		�����ַ�����
	 * @return
	 * 		����ȡ������
	 */
	public static String readFileToString(String fileName, String charset) {
		return readFileToString(new File(fileName), charset);
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @param file
	 * 		�ļ�
	 * @param charset
	 * 		�����ַ�����
	 * @return
	 * 		����ȡ������
	 */
	public static String readFileToString(File file, String charset) {
		if (!file.exists())return null;
		StringBuffer fileContent = new StringBuffer();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(inputStream , charset);
			BufferedReader reader=new BufferedReader(read);
			String line;
		    while ((line = reader.readLine()) != null) { 
		    	fileContent.append(line + "\n"); 
		    }
		    read.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(inputStream!=null){
				try{inputStream.close();}catch(Exception ex){}
			}
		}
		return fileContent.toString(); 
	}
	
	/**
	 * ���ļ��ж�ȡ���л�����
	 * @param fileName
	 * 		����ȡ���ļ�·��
	 * @return
	 * 		�����л����ö���
	 */
	public static Object readFileToObject(String fileName) {
		return readFileToObject(new File(fileName));
	}
	
	/**
	 * ���ļ��ж�ȡ���л�����
	 * @param file
	 * 		����ȡ���ļ�
	 * @return
	 * 		�����л����ö���
	 */
	public static Object readFileToObject(File file) {
		if (!file.exists())
			return null;
		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(file);
			ois = new ObjectInputStream(in);
			return ois.readObject();
		} catch (Exception ex) {
			return null;
		} finally {
			if (in != null)
				try { in.close(); } catch (IOException e) {}
			if (ois != null)
				try { ois.close(); } catch (Exception e) {}
		}
	}
	
	
	/**
	 * ָ����׺������ƥ����ļ�
	 * @param dirPath
	 * 		����ҵ�Ŀ¼
	 * @param suffix
	 * 		��׺��
	 * @return
	 * 		�ļ��б�
	 */
	public static List<File> findFilesBySuffix(String dirPath, String suffix){
		List<File> fileList = new ArrayList<File>();
		File dirFile = new File(dirPath);
		if(dirFile.isDirectory()){
			File[] files = dirFile.listFiles();
			if(files!=null && files.length>0){
				for(int i = 0 ; i < files.length ; i++){
					if(files[i].isDirectory()){
						fileList.addAll(findFilesBySuffix(files[i].getAbsolutePath(),suffix));
					}else{
						String fileName = files[i].getName();
						if(fileName.toLowerCase().endsWith(suffix.toLowerCase())){
							fileList.add(files[i]);
						}
					}
				}
			}
		}else{
			String fileName = dirFile.getName();
			if(fileName.toLowerCase().endsWith(suffix.toLowerCase())){
				fileList.add(dirFile);
			}
		}
		return fileList;
	}
	
	/**
	 * ָ����׺�������ļ�
	 * @param dirPath
	 * 		����ҵ�Ŀ¼
	 * @param suffix
	 * 		��׺��
	 * @return
	 * 		�ļ�
	 */
	public static File findFileBySuffix(String dirPath,String suffix){
		File dirFile = new File(dirPath);
		if(dirFile.isDirectory()){
			File[] files = dirFile.listFiles();
			if(files!=null && files.length>0){
				for(int i=0;i<files.length;i++){
					if(files[i].isDirectory()){
						return findFileBySuffix(files[i].getAbsolutePath(),suffix);
					}else{
						String fileName = files[i].getName();
						if(fileName.toLowerCase().endsWith(suffix.toLowerCase())){
							return files[i];
						}
					}
				}
			}
		}else{
			String fileName = dirFile.getName();
			if(fileName.toLowerCase().endsWith(suffix.toLowerCase())){
				return dirFile;
			}
		}
		return null;
	}
	
	/**
	 * �����ļ���С����
	 * @param files
	 * 		�ļ�����
	 */
	public static void orderByLength(List<File> files) {
		if(files==null)return;
		Collections.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.length() - f2.length();
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
		});
	}
	
	/**
	 * �����ļ���������
	 * @param files
	 * 		�ļ�����
	 */
	public static void orderByName(List<File> files) {
		if(files==null)return;
		Collections.sort(files, new Comparator<File>() {
			public int compare(File o1, File o2) {
				if (o1.isDirectory() && o2.isFile())
					return -1;
				if (o1.isFile() && o2.isDirectory())
					return 1;
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	
	/**
	 *  ����������
	 * @param files
	 * 		�ļ�����
	 */
	public static void orderByDate(List<File> files) {
		Collections.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
		});
	}
	
	/**
	 * �ж�fileName�Ƿ�Ϊ��Ч·��
	 * @param fileName
	 * @return
	 */
	public static Boolean isValidFileName(String fileName){
		return fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
	}
	
	/**
	 * �ж��ļ��Ƿ����
	 * @param filePath
	 * @return
	 */
	public static Boolean isExists(String filePath){
		File file = new File(filePath);
		return (file!=null && file.exists());
	}
	
	/**
	 * �����ļ����µ�·����
	 * @param oldPath
	 * @param newPath
	 */
	public static Boolean copy(String srcFilePath, String copyFilePath) {
		return FileUtil.copy(new File(srcFilePath), new File(copyFilePath));
	}

	/**
	 * �����ļ����µ�·����
	 * @param oldPath
	 * @param newPath
	 */
	public static Boolean copy(File srcFilePath, File copyFilePath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			
			if(!copyFilePath.exists())
				copyFilePath.getParentFile().mkdir();
			if (srcFilePath.exists()) {
				InputStream inStream = new FileInputStream(srcFilePath);
				FileOutputStream fs = new FileOutputStream(copyFilePath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * ��srcPath�µ����������Ƶ�destPath�£�
	 * ��������srcPath�����Ҳ�����destPath���е�����
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	public static Boolean cut(String srcPath, String destPath){
		return FileUtil.cut(new File(srcPath), new File(destPath));
	}
	
	/**
	 * ��srcPath�µ����������Ƶ�destPath�£�
	 * ��������srcPath�����Ҳ�����destPath���е�����
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	public static Boolean cut(File srcPath, File destPath){
		if(!srcPath.isDirectory() || !destPath.isDirectory())return false;
		List<File> files = FileUtil.getFiles(srcPath, null, true);
		if(files==null)return false;
		boolean isSuccess = false;
		for(File file : files){
			if(file.isDirectory())continue;
			String filePath = file.getAbsolutePath();
			String last = filePath.substring(filePath.indexOf(srcPath.getAbsolutePath())
					+ srcPath.getAbsolutePath().length());
			File destFile = new File(destPath.getAbsoluteFile() + last);
			isSuccess = FileUtil.copy(file, destFile) && file.delete();
			if(!isSuccess)break;
		}
		if(isSuccess)FileUtil.deleteDirectory(srcPath);
		return isSuccess;
	}
}
