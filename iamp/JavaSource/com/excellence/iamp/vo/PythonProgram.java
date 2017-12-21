package com.excellence.iamp.vo;

import java.util.Date;

public class PythonProgram {
	private String programId;
	private String programName;
	private String programCName;
	private String keyWord;
	private String pythonPath;
	private String programInput;
	private String remark;
	private String creatorId;
	private String creatorName;
	private String typeName;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	private Date createDate;
	private Date updateDate;
	public String getProgramCName() {
		return programCName;
	}
	public void setProgramCName(String programCName) {
		this.programCName = programCName;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getPythonPath() {
		return pythonPath;
	}
	public void setPythonPath(String pythonPath) {
		this.pythonPath = pythonPath;
	}
	public String getProgramInput() {
		return programInput;
	}
	public void setProgramInput(String programInput) {
		this.programInput = programInput;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
