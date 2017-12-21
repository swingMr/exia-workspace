package com.excellence.iaserver.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ���Spring�����ڵĶ���
 * @author lishuowen
 */
@Component
public class SpringFactoryUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
	
	/**
	 * ���� ID ���ĳ��Spring��������
	 * @param id ����ID
	 * @return Spring�����еĶ���
	 */
	public synchronized static Object getObject(String id) {
		Object object = null;
		object = context.getBean(id);
		return object;
	}
}