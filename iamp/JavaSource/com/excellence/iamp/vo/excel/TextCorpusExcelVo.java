package com.excellence.iamp.vo.excel;

import java.io.Serializable;

/**
 * �ı�Ԥ��
 * @author wangjg
 *
 */
public class TextCorpusExcelVo implements Serializable {
	
	/**
	 * ���ϱ���
	 */
	private String corpusTitle;
	
	/**
	 * ����������
	 */
	private String createDate;
	
	/**
	 * ������Դ
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
