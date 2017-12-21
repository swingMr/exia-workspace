package com.excellence.iaserver.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.excellence.exke.common.util.XMLUtil;
import com.excellence.exke.resource.adapter.ResourceAdapterFactory;
import com.excellence.exke.resource.adapter.exck.ExckAdapter;
import com.excellence.exke.resource.web.servlet.InitializationServlet;
import com.sun.xml.internal.ws.transport.http.DeploymentDescriptorParser.AdapterFactory;
import com.trs.web2frame.ServiceConfig;

/**
 * 配置文件
 * @author huangjinyuan
 *
 */
public abstract class Constant {
	
	public static String OA_DOC_PATH = System.getProperty("oapath");
	
	public static String SCRIPTS_PATH = "./scripts";
	
	public static String CONFIG_PROPERTIES_FILE = "classpath:configration.properties";
	
	// TRS CKM连接配置信息
	public static String CKM_HOST = "10.1.7.213";
	public static int    CKM_PORT = 8000;
	public static String CKM_ACCOUNT = "admin";
	public static String CKM_PASSWORD = "trsadmin";
	
	// ExKE连接配置信息
	public static String EXKE_HOST = "127.0.0.1";
	public static int    EXKE_PORT = 8001;
	public static String EXKE_ACCOUNT = "root";
	public static String EXKE_PASSWORD = "123456";
	
	// TRS数据库连接配置信息
	public static String TRSSERVER_HOST = "172.3.4.240";
	public static int    TRSSERVER_PORT = 8888;
	public static String TRSSERVER_ACCOUNT = "system";
	public static String TRSSERVER_PASSWORD = "manager";
	public static String TRSSERVER_DATABASE = "KNOWLEDGEBASE";
	
	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_PAGE_SIZE = 15;

	public static final String ANSWER_STRATEGY_CLASS = "com.excellence.iaserver.robot.ExactAnswerStrategy"
			+";com.excellence.iaserver.robot.PatternAnswerStrategy";

	public static final String BTXXJS = "BTXXJS";
	public static final String BTJSZHZS = "BTJSZHZS";
	public static final String STSXJS = "STSXJS";
	public static final String XGSTJS = "XGSTJS";
	public static final String BDZDJS = "BDZDJS";
	public static final String ZHZSJS = "ZHZSJS";

	public static String ELESTICSEARCH_HOST = "10.2.0.211";
	public static String ELESTICSEARCH_PORT = "9200";
	public static String ELESTICSEARCH_DATABASE_NAME = "213trswcmv7";
	
	public static String OPEN_OFFICE_HOME = "C:/Program Files (x86)/LibreOffice 5/"; 
	//服务端口
	public static int OPEN_OFFICE_PORT[] = {8100};
	
	public static String EXOI_HOME_NAME = "EXOI_HOME";
	
	public static Properties properties;
	
	public static String[] TRSZYKNAMES = {"法律法规库","党的文献库","技术标准库","专项政策库","科学研究库","媒体资讯库","领导指示库","典型事件库","国外资源库"};
	public static String[] TRSZYKCODES = {"wcmmetatableflfg","wcmmetatablepartyliterature","wcmmetatabletechnicalstandard",
			"wcmmetatablespecialpolicy","wcmmetatablescientificresearch","wcmmetatablepublicopinion","wcmmetatableleadershipinstruction",
			"wcmmetatabletypicalevents","wcmmetatableforeignresources"};
	
	public static String ADAPTER_CLASS = "com.excellence.exke.resource.adapter.trs.WCMAdapter;com.excellence.exke.resource.adapter.trs.WCMSearchAdapter;com.excellence.exke.resource.adapter.trs.WCMAttrAdapter;com.excellence.exke.resource.adapter.knowledgeDoc.KnowledgeDocAttrAdapter;com.excellence.exke.resource.adapter.trs.TRSServerAdapter;com.excellence.exke.resource.adapter.ontologyConfirm.OntologyConfirmAdapter;com.excellence.exke.resource.adapter.exck.ExckAdapter";
	
	static {
		init();
	}
	
	public static void init() {
		File file = new File(System.getProperty("oapath") + File.separator + "exiaserver" + File.separator + "configration.properties");
		InputStream inputFile = null;
		try {
			inputFile =  new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties = new Properties();
			properties.load(inputFile);
			inputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CKM_HOST = getProperty("CKM.host",CKM_HOST);
		CKM_PORT = Integer.parseInt(getProperty("CKM.port",CKM_PORT+""));
		CKM_ACCOUNT = getProperty("CKM.account",CKM_ACCOUNT);
		CKM_PASSWORD = getProperty("CKM.password",CKM_PASSWORD);
		EXKE_HOST = getProperty("ExKE.host",EXKE_HOST);
		EXKE_PORT = Integer.parseInt(getProperty("ExKE.port",EXKE_PORT+""));
		EXKE_ACCOUNT = getProperty("ExKE.account",EXKE_ACCOUNT);
		EXKE_PASSWORD = getProperty("ExKE.password",EXKE_PASSWORD);
		ELESTICSEARCH_HOST = getProperty("elasticsearch.host",ELESTICSEARCH_HOST);
		ELESTICSEARCH_PORT = getProperty("elasticsearch.port",ELESTICSEARCH_PORT);
		ELESTICSEARCH_DATABASE_NAME = getProperty("elasticsearch.databasename",ELESTICSEARCH_DATABASE_NAME);
		OPEN_OFFICE_HOME = getProperty("OPEN.office.home",OPEN_OFFICE_HOME);
		
		ADAPTER_CLASS = getProperty("adapter.class");
		// 修改京华综合知识库ip和port
		String exckIp = properties.getProperty("exck.ip");
		String exckPort = properties.getProperty("exck.port");
		String xmlPath = System.getProperty("oapath") + File.separator + "adapterConfig" 
				 + File.separator + ExckAdapter.KEY + File.separator + "config.xml";
		XMLUtil.readAllConfig(xmlPath, false);
		XMLUtil.set(xmlPath, ExckAdapter.KEY + ".ip", exckIp);
		XMLUtil.set(xmlPath, ExckAdapter.KEY + ".port", exckPort);
		// 设置wcm地址
		ServiceConfig.init("http://10.1.6.63:8088/wcm");
	}
	
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue) {
		return properties.getProperty(key,defaultValue);
	}
	
}
