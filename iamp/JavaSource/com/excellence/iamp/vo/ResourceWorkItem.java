package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * ��Դ��������
 * @author huangjinyuan
 *
 */
public class ResourceWorkItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2541599345623945791L;

	/**
	 * ������id
	 */
	private String workItemId;
	
	/**
	 * ��Դid
	 */
	private String resId;
	
	/**
	 * ��Դ��Ŀid
	 */
	private String channelId;
	
	/**
	 * ��Դ����
	 */
	private String resTitle;
	
	/**
	 * ��Դ���ԣ�json�ַ���
	 */
	private String resProp;
	
	/**
	 * ��Դ����
	 */
	private String resContent;
	
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	
	/**
	 * ��Դ�����
	 * wcmmetatableflfg--���ɷ����
	   wcmmetatablepartyliterature--�������׿�
	   wcmmetatabletechnicalstandard--������׼��
	   wcmmetatablespecialpolicy--ר�����߿�
	   wcmmetatablescientificresearch--��ѧ�о���
	   wcmmetatablepublicopinion--ý����Ѷ��
	   wcmmetatableleadershipinstruction--�쵼ָʾ��
	   wcmmetatabletypicalevents--�����¼���
	   wcmmetatableforeignresources--������Դ��
	 */
	private String dbCode;
	
	/**
	 * ����״̬ 1 ������ 9�����
	 */
	private int workStatus;
	
	/**
	 * ��Ϊ����
	 */
	private String actCode;
	
	/**
	 * ��������
	 */
	private String belongDomain;
	
	/**
	 * �ؼ���
	 */
	private String keyWord;
	
	/**
	 * ���ʱ��
	 */
	private Date completeDate;
	
	/**
	 * ִ����id
	 */
	private String actUserId;
	
	/**
	 * ִ��������
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
