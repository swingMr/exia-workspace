package com.excellence.iamp.vo.excel;

import java.io.Serializable;

/**
 * 文本预料
 * @author wangjg
 *
 */
public class TextCorpusExcelVo implements Serializable {
	
	/**
	 * 语料标题
	 */
	private String corpusTitle;
	
	/**
	 * 创建人日期
	 */
	private String createDate;
	
	/**
	 * 语料来源
	 */
	private String corpusSource;
	
	

	public String getCorpusTitle() {
		return corpusTitle;
	}

	public void setCorpusTitle(String corpusTitle) {
		this.corpusTitle = corpusTitle;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCorpusSource() {
		return corpusSource;
	}

	public void setCorpusSource(String corpusSource) {
		this.corpusSource = corpusSource;
	}

}
