package com.excellence.iamp.mongo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excellence.iamp.mongodb.dao.ResourceCatalogDao;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;

public class mongoTest {
	
	private ResourceCatalogDao resourceCatalogDao;
	
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		resourceCatalogDao= (ResourceCatalogDao)ctx.getBean("resourceCatalogDao");
	}
	
	@Test
	public void test() {
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setCatelogName("²âÊÔÄ¿Â¼");
		resourceCatalog.setCatelogNum("testCat");
		resourceCatalog.setDisplayMode(0);
		resourceCatalogDao.save(resourceCatalog);
		
		
	}

}
