package com.excellence.iamp.mongodb.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/**
 * 信息资源正文&附件信息
 * @author huangjinyuan
 *
 */
public class FileInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2309271599210604443L;
	
	private String id;
	
	/**
	 * b
	 */
	private Long fileSize;

	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 文件后缀
	 */
	private String fileExt;
	
	
	private String creatorName;
	
	
	private List<String> keywords;
	
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public JSONObject toFormJson() {
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DecimalFormat format = new DecimalFormat("###,###,###,###,###,###,###,###");
		json.put("id", id);
		json.put("fileSize", format.format((fileSize/1024)));
		json.put("title", title);
		json.put("fileExt", fileExt);
		json.put("keywords", StringUtils.join(keywords.toArray(),";"));
		json.put("creatorName", creatorName);
		json.put("createDate", sdf.format(createDate));
		return json;
	}
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 文件类型 1 正文 2附件
	 */
	private int fileType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
