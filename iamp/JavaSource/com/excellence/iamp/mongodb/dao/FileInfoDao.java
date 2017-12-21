package com.excellence.iamp.mongodb.dao;

import java.io.File;
import java.io.InputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.iamp.mongodb.vo.FileInfo;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
public class FileInfoDao {
	
	private static String fileCollectionName = "ia_file"; 
	
	@Autowired
    protected MongoTemplate mongoTemplate;
	
	/**
	 * �����ļ�
	 * @param file
	 * @param type
	 * @param title
	 * @param fileExt
	 * @return
	 */
	public FileInfo SaveFile(File file, int type, String title, String fileExt) {
        try {
            DB db = mongoTemplate.getDb();
            // �洢fs�ĸ��ڵ�
            GridFS gridFS = new GridFS(db, fileCollectionName);
            GridFSInputFile gfs = gridFS.createFile(file);
            gfs.setFilename(file.getName());
            gfs.save();
            String id = gfs.getId().toString();
            System.out.println(id);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setTitle(title);
            fileInfo.setFileExt(fileExt);
            fileInfo.setId(id);
            fileInfo.setCreateDate(gfs.getUploadDate());
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�洢�ļ�ʱ�������󣡣���");
        }
        return null;
    }
	
	/**
	 * �����ļ����浽mongoDB
	 * @param file          //�ϴ��ļ�
	 * @param fileTitle		//�ļ�����
	 * @author wangjg
	 * @return
	 */
	public String saveFileToMongo(MultipartFile file, String fileTitle) {
        try {
            DB db = mongoTemplate.getDb();
            // �洢fs�ĸ��ڵ�
            GridFS gridFS = new GridFS(db, fileCollectionName);
            GridFSInputFile gfs = gridFS.createFile(file.getInputStream());
            gfs.setFilename(fileTitle);
            gfs.save();
            String FileId = gfs.getId().toString();
            System.out.println(FileId);
            return FileId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�洢�ļ�ʱ�������󣡣���");
        }
        return null;
    }
	
	/**
	 * ͨ��id�����ļ�
	 * @param id
	 * @return
	 */
	public GridFSDBFile getFileById(String id) {
		try {
            DB db = mongoTemplate.getDb();
            // ��ȡfs�ĸ��ڵ�
            GridFS gridFS = new GridFS(db, fileCollectionName);
            GridFSDBFile dbfile = gridFS.findOne(new ObjectId(id));
            if (dbfile != null) {
                return dbfile;
            }
        } catch (Exception e) {
        	 e.printStackTrace();
            // TODO: handle exception
        }
		return null;
	}
	
	/**
	 * ͨ��id������
	 * @param collectionName
	 * @param filename
	 * @return
	 */
	public InputStream getInputStreamById(String id) {
        try {
            DB db = mongoTemplate.getDb();
            // ��ȡfs�ĸ��ڵ�
            GridFS gridFS = new GridFS(db, fileCollectionName);
            GridFSDBFile dbfile = gridFS.findOne(new ObjectId(id));
            if (dbfile != null) {
                return dbfile.getInputStream();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * ɾ���ļ�
	 * @param id
	 */
	public void deleteFile(String id) {
		try {
            DB db = mongoTemplate.getDb();
            // ��ȡfs�ĸ��ڵ�
            GridFS gridFS = new GridFS(db, fileCollectionName);
            gridFS.remove(new ObjectId(id));
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
