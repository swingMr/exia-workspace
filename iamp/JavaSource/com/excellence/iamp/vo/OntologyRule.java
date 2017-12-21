package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * ����������
 * @author chenghq
 *
 */
public class OntologyRule implements Serializable {

	/**
	 * ����
	 */
	private String ruleId;
	
	/**
	 * ������
	 */
	private String conceptName;
	
	/**
	 * ����id
	 */
	private String conceptId;
	
	/**
	 * ��������
	 */
	private String ruleContent;
	
	/**
	 * ��ע
	 */
	private String remark;
	
	/**
	 * ״̬
	 */
	private int status;
	
	/**
	 * ��������
	 */
	private Date createDate;
	
	/**
	 * ������id
	 */
	private String creatorId;
	
	/**
	 * ����������
	 */
	private String creatorName;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public String getConceptId() {
		return conceptId;
	}

	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	
}
