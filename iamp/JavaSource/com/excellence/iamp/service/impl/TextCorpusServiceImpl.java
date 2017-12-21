package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.MD5Util;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.TextCorpusDao;
import com.excellence.iamp.service.TextCorpusService;
import com.excellence.iamp.vo.TextCorpus;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TextCorpusServiceImpl implements TextCorpusService {
	
	@Autowired
	private TextCorpusDao textCorpusDao;
	
	/**
	 * 创建
	 */
	public void create(TextCorpus textCorpus) {
		if(StringUtils.isEmpty(textCorpus.getCorpusId())) {
			textCorpus.setCorpusId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		textCorpusDao.insert(textCorpus);
	}
	
	/**
	 * 批量插入
	 */
	public void batchInsert(List<TextCorpus> list) {
		textCorpusDao.batchInsert(list);
	}
	
	/**
	 * 通过id查找
	 */
	public TextCorpus findById(String corpusId) {
		return textCorpusDao.findById(corpusId);
	}
	
	/**
	 * 更新
	 */
	public void update(TextCorpus textCorpus) {
		textCorpusDao.update(textCorpus);
	}
	
	/**
	 * 删除
	 */
	public void delete(TextCorpus textCorpus) {
		textCorpus.setStatus(EStatus.delete.getIndex());
		update(textCorpus);
	}
	
	/**
	 * 物理删除
	 */
	public void psyDelete(String corpusId) {
		textCorpusDao.delete(corpusId);
	}
	
	/**
	 * 通过条件查询
	 */
	@SuppressWarnings("rawtypes")
	public List<TextCorpus> getByCondition(Map paramMap) {
		return textCorpusDao.getByCondition(paramMap);
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<TextCorpus> page(Map paramMap, int pageNo, int pageSize) {
		int count = textCorpusDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<TextCorpus> list = textCorpusDao.getByCondition(paramMap);
		PageInfo<TextCorpus> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

}
