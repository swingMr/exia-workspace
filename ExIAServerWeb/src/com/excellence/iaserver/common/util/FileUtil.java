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
 * 文件操作工具类
 * @author Huangyb
 * @date 2014-7-15 下午2:02:48
 * @2014 Excellence
 */
public abstract class FileUtil {
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * 删除文件
	 * @param sPath
	 * 		文件路径
	 * @return
	 * 		是否删除成功
	 */
	public static Boolean deleteFile(String path) {
		// 路径为文件且不为空则进行删除
		try{
			new File(path).delete();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * 删除文件
	 * @param sPath
	 * 		文件路径
	 * @return
	 * 		是否删除成功
	 */
	public static Boolean deleteFile(File file) {
		// 路径为文件且不为空则进行删除
		try{
			file.delete();
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public static Boolean deleteDirectory(String path) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!path.endsWith(File.separator))
			path = path + File.separator;
		return FileUtil.deleteDirectory(new File(path));
	}
	
	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param sPath
	 * 		被删除目录的文件路径
	 * @return 
	 * 		目录删除成功返回true，否则返回false
	 */
	public static Boolean deleteDirectory(File dirFile) {
		if(dirFile==null)return false;
		
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory())
			return false;

		boolean flag = true;

		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; files!=null && i < files.length; i++) {
			if (files[i].isFile()) { // 删除子文件
				if (!(flag = deleteFile(files[i].getAbsolutePath())))
					break;
			} else { // 删除子目录
				if (!(flag = deleteDirectory(files[i].getAbsolutePath())))
					break;
			}
		}
		if (!flag)
			return false;

		// 删除当前目录
		return dirFile.delete();
	}
	
	/**
	 * 修改文件夹名称
	 * @param oldDir
	 * 		旧文件夹路径
	 * @param newDir
	 * 	        新文件夹路径
	 * @return
	 * 		是否创建成功
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
	 * 根据文件名创建路径
	 * @param fileName
	 * 		文件名
	 * @return
	 * 		是否创建成功
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
	 * 创建空文件
	 * @param fileName 
	 * 		文件名
	 * @return 
	 * 		是否创建成功
	 * @throws IOException 
	 */
	public static Boolean createFile(String fileName) throws IOException{
		if(FileUtil.isExists(fileName))return true;
		File file = new File(fileName);
		file.createNewFile();
		return file.isFile();
	}
	
	/**
	 * 获取文件大小
	 * @param fileName
	 * 		文件名
	 * @return
	 * 		文件大小
	 * @throws Exception
	 * 		文件不存在异常
	 */
	public static Long getFileSizes(String fileName) throws Exception {
		return getFileSizes(new File(fileName));
	}
	
	/**
	 * 获取文件大小
	 * @param file
	 * 		文件
	 * @return
	 * 		文件大小
	 * @throws Exception
	 * 		文件不存在异常
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
			throw new IOException("文件不存在");
		}
		
		return s;
	}
	
	/**
	 * 获取当前目录下的子目录个数
	 * @param dir
	 * 		查询的目录
	 * @return
	 * 		子目录个数
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
	 * 获取所有的子目录
	 * @param path
	 * 		父目录
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
	 * 查找目录下的所有文件
	 * @param path
	 * 		待查找的目录
	 * @return
	 * 		查找文件列表
	 */
	public static List<File> getFiles(String path){
		return getFiles(path, null);
	}
	
	/**
	 * 查找目录下的所有文件
	 * @param path
	 * 		待查找的目录
	 * @return
	 * 		查找文件列表
	 */
	public static List<File> getFiles(String path, FileFilter filter){
		return FileUtil.getFiles(new File(path), filter, true);
	}
	
	/**
	 * 查找目录下的所有文件
	 * @param isRecursion
	 * 		是否递归查找所有子目录及其文件
	 * @param root
	 * 		待查找的目录
	 * @param filter
	 * 		文件过滤器，用于过滤需要查找的文件
	   FileFilter filter = new FileFilter(){
			public boolean accept(File file) {
				String name = file.getName();
				return name.endsWith(".txt"); 
			}
	   };
	 * @return
	 * 		查找文件列表
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
	 * 追加方式写入文件
	 * @param fileName
	 * 		写入的文件路径
	 * @param stream
	 * 		需写入的二进制流
	 * @return
	 * 		是否写入成功
	 */
	public static Long appendFileByStream(String fileName, InputStream stream) {
		if (!mkdir(fileName))return -1L;
		return writeFileByStream(new File(fileName), stream, true);
	}
	
	/**
	 * 追加方式写入文件
	 * @param fileName
	 * 		写入的文件路径
	 * @param stream
	 * 		需写入的二进制流
	 * @return
	 * 		是否写入成功
	 */
	public static Long appendFileByStream(File file, InputStream stream) {
		return writeFileByStream(file, stream, true);
	}
	
	/**
	 * 写入文件
	 * @param fileName
	 * 		写入的文件路径
	 * @param stream
	 * 		需写入的二进制流
	 * @return
	 * 		是否写入成功
	 */
	public static Long writeFileByStream(String fileName, InputStream stream) {
		if (!mkdir(fileName))return -1L;
		return writeFileByStream(new File(fileName), stream, false);
	}
	
	/**
	 * 写入文件
	 * @param file
	 * 		写入的文件
	 * @param stream
	 * 		需写入的二进制流
	 * @return
	 * 		是否写入成功
	 */
	public static Long writeFileByStream(File file, InputStream stream) {
		return writeFileByStream(file, stream, false);
	}
	
	/**
	 * 把二进制流写入文件中
	 * @param file
	 * 		写入的文件
	 * @param stream
	 * 		需写入的二进制流
	 * @param append
	 * 		是否追加
	 * @return
	 * 		是否写入成功
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
	 * 把内容写入文件
	 * @param fileName
	 * 		文件路径
	 * @param context
	 * 		需写入的内容
	 * @return
	 * 		是否写入成功
	 */
	public static Boolean writeFileByString(String fileName, String context) {
		return writeFileByString(new File(fileName), context);
	}
	
	/**
	 * 把内容写入文件
	 * @param file
	 * 		文件
	 * @param context
	 * 		需写入的内容
	 * @return
	 * 		是否写入成功
	 */
	public static Boolean writeFileByString(File file, String context) {
		return writeFileByString(file, context, DEFAULT_ENCODING, false);
	}
	
	/**
	 * 把内容写入文件
	 * @param fileName
	 * 		文件路径
	 * @param context
	 * 		需写入的内容
	 * @param charset
	 * 		写入内容的编码
	 * @return
	 * 		是否写入成功
	 */
	public static Boolean writeFileByString(String fileName, String context, String charset) {
		return writeFileByString(new File(fileName), context, charset, false);
	}
	
	/**
	 * 把内容写入文件
	 * @param file
	 * 		文件
	 * @param context
	 * 		需写入的内容
	 * @param charset
	 * 		写入内容的编码
	 * @return
	 * 		需写入的内容
	 */
	public static Boolean writeFileByString(File file, String context, String charset){
		return writeFileByString(file, context, charset, false);
	}
	
	/**
	 * 把内容写入文件
	 * @param file
	 * 		文件
	 * @param context
	 * 		需写入的内容
	 * @param charset
	 * 		写入内容的编码
	 * @param append
	 * 		是否追加内容
	 * @return
	 * 		需写入的内容
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
	 * 序列化对象，把对象写入文件中
	 * @param fileName
	 * 		写入的文件路径
	 * @param serializable
	 * 		需要序列化的对象
	 * @return
	 * 		是否写入成功
	 */
	public static Boolean writeFileByObject(String fileName,
			Serializable serializable) {
		if (!mkdir(fileName))return false;
		return writeFileByObject(new File(fileName), serializable);
	}
	
	/**
	 * 序列化对象，把对象写入文件中
	 * @param file
	 * 		写入的文件
	 * @param serializable
	 * 		需要序列化的对象
	 * @return
	 * 		是否写入成功
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
	 * 读取文件到Bytes
	 * @param fileName
	 * 		文件名
	 * @param pos
	 * 		起始位置
	 * @param len
	 * 		读取大小
	 * @return
	 * 		bytes内容
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(String fileName, long pos, int len) throws IOException{
		return readFileToBytes(new File(fileName), pos, len);
	}
	
	/**
	 * 读取文件到Bytes
	 * @param file
	 * 		文件
	 * @param pos
	 * 		起始位置
	 * @param len
	 * 		读取大小
	 * @return
	 * 		bytes内容
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
	 * 读取文件内容
	 * @param fileName
	 * 		文件路径
	 * @return
	 * 		所读取的内容
	 */
	public static String readFileToString(String fileName) {
		return readFileToString(new File(fileName));
	}
	
	/**
	 * 读取文件内容
	 * @param file
	 * 		文件
	 * @return
	 * 		所读取的内容
	 */
	public static String readFileToString(File file){
		return readFileToString(file, DEFAULT_ENCODING);
	}
	
	/**
	 * 读取文件内容
	 * @param fileName
	 * 		文件路径
	 * @param charset
	 * 		内容字符编码
	 * @return
	 * 		所读取的内容
	 */
	public static String readFileToString(String fileName, String charset) {
		return readFileToString(new File(fileName), charset);
	}
	
	/**
	 * 读取文件内容
	 * @param file
	 * 		文件
	 * @param charset
	 * 		内容字符编码
	 * @return
	 * 		所读取的内容
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
	 * 从文件中读取序列化对象
	 * @param fileName
	 * 		所读取得文件路径
	 * @return
	 * 		反序列化所得对象
	 */
	public static Object readFileToObject(String fileName) {
		return readFileToObject(new File(fileName));
	}
	
	/**
	 * 从文件中读取序列化对象
	 * @param file
	 * 		所读取得文件
	 * @return
	 * 		反序列化所得对象
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
	 * 指定后缀名查找匹配的文件
	 * @param dirPath
	 * 		需查找的目录
	 * @param suffix
	 * 		后缀名
	 * @return
	 * 		文件列表
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
	 * 指定后缀名查找文件
	 * @param dirPath
	 * 		需查找的目录
	 * @param suffix
	 * 		后缀名
	 * @return
	 * 		文件
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
	 * 按照文件大小排序
	 * @param files
	 * 		文件队列
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
	 * 按照文件名称排序
	 * @param files
	 * 		文件队列
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
	 *  按日期排序
	 * @param files
	 * 		文件队列
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
	 * 判断fileName是否为有效路径
	 * @param fileName
	 * @return
	 */
	public static Boolean isValidFileName(String fileName){
		return fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
	}
	
	/**
	 * 判断文件是否存在
	 * @param filePath
	 * @return
	 */
	public static Boolean isExists(String filePath){
		File file = new File(filePath);
		return (file!=null && file.exists());
	}
	
	/**
	 * 复制文件到新的路径中
	 * @param oldPath
	 * @param newPath
	 */
	public static Boolean copy(String srcFilePath, String copyFilePath) {
		return FileUtil.copy(new File(srcFilePath), new File(copyFilePath));
	}

	/**
	 * 复制文件到新的路径中
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
	 * 将srcPath下的所有内容移到destPath下，
	 * 但不包括srcPath，并且不覆盖destPath已有的内容
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	public static Boolean cut(String srcPath, String destPath){
		return FileUtil.cut(new File(srcPath), new File(destPath));
	}
	
	/**
	 * 将srcPath下的所有内容移到destPath下，
	 * 但不包括srcPath，并且不覆盖destPath已有的内容
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
