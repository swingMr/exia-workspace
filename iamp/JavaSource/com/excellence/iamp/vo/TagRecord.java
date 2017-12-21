package com.excellence.iamp.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 职责:用户行为痕迹数据实体VO类 
 * 持久化表:IA_Tag_Record
 * @author haiqian
 */
public class TagRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4184968780772832491L;

	private String recordId;

    private String subjectName;

    private String subjectId;
    
    private String subjectAccount;
    
    private String tagCode;

    private String tagType;

    private String tagName;

    private Integer num;

    private Date createDate;

    private Date updateDate;

    private String updateDateStr;
    
    public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subject) {
        this.subjectName = subject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        if(this.updateDate!=null)
        {
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	this.updateDateStr = df.format(this.updateDate);
        }
    }

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public String getSubjectAccount() {
		return subjectAccount;
	}

	public void setSubjectAccount(String subjectAccount) {
		this.subjectAccount = subjectAccount;
	}
	
}