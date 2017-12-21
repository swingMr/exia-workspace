package com.excellence.iaserver.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.service.AnswerPartService;
import com.excellence.iaserver.common.util.SpringFactoryUtil;

/**
 * ����ƥ��Ѱ�Ҳ�������
 * @author liup
 *
 */
@Service("PatternAnswerStrategy")
public class PatternAnswerStrategy implements AnswerStrategy{
	private AnswerPartService answerPartService;
	
	public PatternAnswerStrategy() {
		answerPartService = (AnswerPartService) SpringFactoryUtil.getObject("answerPartService");
	}

	@Override
	public List<FindAnswerPart> match(ChatContext context, ChatMsg msg) throws Exception {
		List<FindAnswerPart> allPartList = findAllParts();
		List<FindAnswerPart> list = new ArrayList<FindAnswerPart>();
		
		if(allPartList!=null && allPartList.size()>0) {
			for (int i = 0; i < allPartList.size(); i++) {
				FindAnswerPart findAnswerPart = allPartList.get(i);
				boolean isMatch = false;
				String ruleStr = findAnswerPart.getPartRule();
				if(StringUtils.isNotEmpty(ruleStr)) {
					JSONArray arr = new JSONArray(ruleStr);
					if(arr!=null && arr.length()>0) {
						for (int j = 0; j < arr.length(); j++) {
							if(!isMatch) {
								JSONObject obj = arr.getJSONObject(j);
								JSONArray ruleArr = obj.getJSONArray("ruleArr");
								if(ruleArr!=null && ruleArr.length()>0) {
									for(int k=0; k<ruleArr.length(); k++) {
										String rule = ruleArr.getString(k);
										isMatch = msg.getTextContent().matches(rule);
										if(isMatch) {
											break;
										}
									}
	 							}
							} else {
								break;
							}
						}
					}
				}
				if(isMatch) {
					list.add(findAnswerPart);
				}
			}
		}
		return list;
	}

	@Override
	public List<FindAnswerPart> findAllParts() throws Exception {
		String strategy = "PatternAnswerStrategy";
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
	
	public static void main(String[] args) {
		String rule = ".*����Ҫ�쵼.*";
		String text = "����Ժ�칫����Ҫ�쵼����Щ";
		System.out.println(text.matches(rule));
//		String str="����Ժ�칫������Ҫ�쵼����Щ";  //���жϵ��ַ���
//        String reg=".*����Ҫ�쵼.*";  //�ж��ַ������Ƿ����ض��ַ���ll
//        System.out.println(str.matches(reg));
	}
}
