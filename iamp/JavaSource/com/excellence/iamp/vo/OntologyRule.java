package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 本体分类规则
 * @author chenghq
 *
 */
public class OntologyRule implements Serializable {

	/**
	 * 主键
	 */
	private String ruleId;
	
	/**
	 * 概念名
	 */
	private String conceptName;
	
	/**
	 * 概念id
	 */
	private String conceptId;
	
	/**
	 * 规则内容
	 */
	private String ruleContent;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 创建人id
	 */
	private String creatorId;
	
	/**
	 * 创建人名字
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
