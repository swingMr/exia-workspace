package com.excellence.iamp.mongo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excellence.iamp.mongodb.dao.ResourceCatalogDao;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;

public class test {
	
	private ResourceCatalogDao resourceCatalogDao;
	@SuppressWarnings("resource")
	public void init(){
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		 resourceCatalogDao= (ResourceCatalogDao)ctx.getBean("resourceCatalogDao");
		
	}
	
	public void test() {
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setCatelogName("²âÊÔÄ¿Â¼");
		resourceCatalog.setCatelogNum("testCat");
		resourceCatalog.setDisplayMode(0);
		resourceCatalogDao.save(resourceCatalog);
	}
	
	public static void main(String[] args) {
		test t = new test();
		t.init();
		t.test();
	}

}
