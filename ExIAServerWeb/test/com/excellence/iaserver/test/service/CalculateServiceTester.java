package com.excellence.iaserver.test.service;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.FileUtil;
import com.excellence.iaserver.service.CalculateService;
import com.excellence.iaserver.service.impl.CalculateServiceImpl;


public class CalculateServiceTester {

	private CalculateService service;
	private String context;
	private String title;
	@Before
	public void init(){
		Constant.OA_DOC_PATH = "F:\\workspace\\ExIA\\ExIAServerWeb\\oadoc";
		String fileName = "F:\\resource\\国务院关于强化实施创新驱动发展战略.txt";
		this.title = "国务院关于强化实施创新驱动发展战略";
		this.context = FileUtil.readFileToString(fileName, "GBK");
		this.service = new CalculateServiceImpl();
	}
	
	@Test
	public void testParticiple() throws FileNotFoundException{
		System.out.println("================== Begin::Participle ==================");
		//System.out.println(this.service.participle(this.context, false));
		System.out.println("================== End::Participle ==================");
	}
	
	@Test
	public void testAbs() throws FileNotFoundException{
		System.out.println("================== Begin::Abs ==================");
		System.out.println(this.service.abs(this.title, this.context, 20, 200));
		System.out.println("================== End::Abs ==================");
		//System.out.println(this.service.abs(null, text, 20, 200));
		//System.out.println(this.service.abs(null, text, 0, 0));
	}
}
