package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * ְ�𣺴𰸲���ʵ����
 *
 */
public class AnswerPart {
	/*����id*/
	private String partId;
	/*�������*/
	private String partCode;
	/*������Ӧ����*/
	private String partStrategy;
	/*������Ӧ������*/
	private String answerImplClass;
	/*��������*/
	private String partTitle;
	/*��������*/
	private String partCategory;
	/*ƥ�����*/
	private String partRule;
	/*Զ��URL*/
	private String remoteUrl;
	/*��ʱʱ�䣬��λ������*/
	private Integer timeout;
	//������id
	private String creatorId;
	//����������
	private String creatorName;
	//����ʱ��
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
