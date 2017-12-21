package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源处理工作项
 * @author huangjinyuan
 *
 */
public class ResourceWorkItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2541599345623945791L;

	/**
	 * 工作项id
	 */
	private String workItemId;
	
	/**
	 * 资源id
	 */
	private String resId;
	
	/**
	 * 资源栏目id
	 */
	private String channelId;
	
	/**
	 * 资源标题
	 */
	private String resTitle;
	
	/**
	 * 资源属性，json字符串
	 */
	private String resProp;
	
	/**
	 * 资源内容
	 */
	private String resContent;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 资源库代码
	 * wcmmetatableflfg--法律法规库
	   wcmmetatablepartyliterature--党的文献库
	   wcmmetatabletechnicalstandard--技术标准库
	   wcmmetatablespecialpolicy--专项政策库
	   wcmmetatablescientificresearch--科学研究库
	   wcmmetatablepublicopinion--媒体资讯库
	   wcmmetatableleadershipinstruction--领导指示库
	   wcmmetatabletypicalevents--典型事件库
	   wcmmetatableforeignresources--国外资源库
	 */
	private String dbCode;
	
	/**
	 * 工作状态 1 待处理 9已完成
	 */
	private int workStatus;
	
	/**
	 * 行为代码
	 */
	private String actCode;
	
	/**
	 * 归属领域
	 */
	private String belongDomain;
	
	/**
	 * 关键词
	 */
	private String keyWord;
	
	/**
	 * 完成时间
	 */
	private Date completeDate;
	
	/**
	 * 执行人id
	 */
	private String actUserId;
	
	/**
	 * 执行人姓名
	 */
	private String actUserName;

	public String getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}

	public String getResProp() {
		return resProp;
	}

	public void setResProp(String resProp) {
		this.resProp = resProp;
	}

	public String getResContent() {
		return resContent;
	}

	public void setResContent(String resContent) {
		this.resContent = resContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDbCode() {
		return dbCode;
	}

	public void setDbCode(String dbCode) {
		this.dbCode = dbCode;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getBelongDomain() {
		return belongDomain;
	}

	public void setBelongDomain(String belongDomain) {
		this.belongDomain = belongDomain;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getActUserId() {
		return actUserId;
	}

	public void setActUserId(String actUserId) {
		this.actUserId = actUserId;
	}

	public String getActUserName() {
		return actUserName;
	}

	public void setActUserName(String actUserName) {
		this.actUserName = actUserName;
	}
	
	
}
