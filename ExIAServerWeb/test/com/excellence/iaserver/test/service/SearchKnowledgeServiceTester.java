package com.excellence.iaserver.test.service;

import org.junit.Before;
import org.junit.Test;

import com.excellence.exke.connector.ConnectorException;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.service.SearchKnowledgeService;

public class SearchKnowledgeServiceTester {
	@Before
	public void init(){
		Constant.OA_DOC_PATH = "D:\\workspace\\ExIAServerWeb\\oadoc";
	}
	
	@Test
	public void testGetWCMCatalogTree() {
		SearchKnowledgeService searchKnowledgeService = new SearchKnowledgeService();
		String url = "http://<host>/wcm/×ÊÔ´¿â";
		System.out.println(searchKnowledgeService.getWCMCatalogTree(url));
	}
	
	@Test
	public void testGetConcept() throws ConnectorException {
		SearchKnowledgeService searchKnowledgeService = new SearchKnowledgeService();
		String id = "concept_class/thing";
		System.out.println(searchKnowledgeService.getConcept(id).toString());
	}
}
