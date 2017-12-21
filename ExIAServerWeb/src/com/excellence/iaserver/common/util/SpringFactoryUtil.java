package com.excellence.iaserver.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获得Spring容器内的对象
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
	 * 根据 ID 获得某个Spring容器对象
	 * @param id 对象ID
	 * @return Spring容器中的对象
	 */
	public synchronized static Object getObject(String id) {
		Object object = null;
		object = context.getBean(id);
		return object;
	}
}