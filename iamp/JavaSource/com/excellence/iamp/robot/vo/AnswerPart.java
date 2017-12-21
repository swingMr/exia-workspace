package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * 职责：答案部件实体类
 *
 */
public class AnswerPart {
	/*部件id*/
	private String partId;
	/*部件编号*/
	private String partCode;
	/*部件对应策略*/
	private String partStrategy;
	/*部件对应策略类*/
	private String answerImplClass;
	/*部件名称*/
	private String partTitle;
	/*归属分类*/
	private String partCategory;
	/*匹配规则*/
	private String partRule;
	/*远程URL*/
	private String remoteUrl;
	/*超时时间，单位：毫秒*/
	private Integer timeout;
	//创建人id
	private String creatorId;
	//创建人名称
	private String creatorName;
	//创建时间
	private Date createDate;
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getPartStrategy() {
		return partStrategy;
	}
	public void setPartStrategy(String partStrategy) {
		this.partStrategy = partStrategy;
	}
	public String getPartTitle() {
		return partTitle;
	}
	public void setPartTitle(String partTitle) {
		this.partTitle = partTitle;
	}
	public String getPartCategory() {
		return partCategory;
	}
	public void setPartCategory(String partCategory) {
		this.partCategory = partCategory;
	}
	public String getPartRule() {
		return partRule;
	}
	public void setPartRule(String partRule) {
		this.partRule = partRule;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAnswerImplClass() {
		return answerImplClass;
	}
	public void setAnswerImplClass(String answerImplClass) {
		this.answerImplClass = answerImplClass;
	}
	public String getRemoteUrl() {
		return remoteUrl;
	}
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
