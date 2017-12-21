package com.excellence.iaserver.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.service.AnswerPartService;
import com.excellence.iaserver.common.util.SpringFactoryUtil;

/**
 * 精确匹配部件策略
 * @author liup
 */
@Service("ExactAnswerStrategy")
public class ExactAnswerStrategy implements AnswerStrategy{
	
	private AnswerPartService answerPartService = null;
	
	public ExactAnswerStrategy() {
		answerPartService = (AnswerPartService) SpringFactoryUtil.getObject("answerPartService");
	}
	
	@Override
	public List<FindAnswerPart> match(ChatContext context, ChatMsg msg) throws Exception {
		return findAllParts();
	}

	@Override
	public List<FindAnswerPart> findAllParts() throws Exception {
		String strategy = "ExactAnswerStrategy";
		List<FindAnswerPart> list = new ArrayList<FindAnswerPart>();
		List<AnswerPart> answerParts = answerPartService.getAnswerPartsByStrategy(strategy);
		if(answerParts!=null && answerParts.size()>0) {
			for (AnswerPart answerPart : answerParts) {
				String implClass = answerPart.getAnswerImplClass();
				Class<?> clazz=Class.forName(implClass);
				FindAnswerPart findAnswerPart = (FindAnswerPart)clazz.newInstance();
				BeanUtils.copyProperties(findAnswerPart, answerPart);
				list.add(findAnswerPart);
			}
		}
		return list;
	}
	
}
