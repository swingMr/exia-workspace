package com.excellence.iamp.web.servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.service.AnswerPartService;

/**
 * 
 * @author wangjg
 * @date 2017-09-26
 */
public class InitializationRobotPartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 9139752603665660685L;
	
	@Autowired
	private AnswerPartService answerPartService;
	
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext()); 
		try {
			String fileDirPath = config.getServletContext().getRealPath("/WEB-INF/");
			if(fileDirPath == null){
		        //如果返回为空，则表示服务器为weblogic，则需要使用另外的方法
				fileDirPath = config.getServletContext().getResource("/WEB-INF/").getFile();
		     }
			//1、创建一个DocumentBuilderFactory的对象
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//创建DocumentBuilder对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3、通过DocumentBuilder对象的parser方法加载part.xml文件到当前项目下
			/*注意导入Document对象时，要导入org.w3c.dom.Document包下的*/
			Document document = db.parse(fileDirPath+"\\part.xml");//传入文件名可以是相对路径也可以是绝对路径
			NodeList partList  = document.getDocumentElement().getChildNodes();
			//获取所有part节点的集合
			//NodeList partList = document.getElementsByTagName("part");
			for (int i = 0; i < partList.getLength(); i++) {
				Node part = partList.item(i);
				//获取part节点的所有属性集合
				NodeList attrs = part.getChildNodes();
				List<AnswerPart> answerPartList = new ArrayList<AnswerPart>();
				String partCode = "";
				//遍历part的属性
				for (int j = 0; j < attrs.getLength(); j++) {
					//通过item(index)方法获取part节点的某一个属性
					Node attr = attrs.item(j);
					//获取属性名
					System.out.println("属性名：" + attr.getNodeName());
					//获取属性值
					System.out.println("属性值:" + attr.getTextContent());
					if(StringUtils.equals(attr.getNodeName(), "code")){
						partCode = attr.getTextContent();
						break;
					}
				}
				if(StringUtils.isNotBlank(partCode)){
					Map params = new HashMap();
					params.put("partCode", partCode);
					answerPartList = answerPartService.pageAnswerPart(params, 1, 5).getList();
				}
				if(answerPartList.size() < 1){
					AnswerPart answerPart = new AnswerPart();
					for (int k = 0; k < attrs.getLength(); k++) {
						//通过item(index)方法获取part节点的某一个属性
						Node attr = attrs.item(k);
						if(StringUtils.equals(attr.getNodeName(), "id")){
							answerPart.setPartId(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "code")){
							answerPart.setPartCode(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "code")){
							answerPart.setPartCode(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "title")){
							answerPart.setPartTitle(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "type")){
							answerPart.setPartCategory(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "strategy")){
							answerPart.setPartStrategy(attr.getTextContent());
						}else if(StringUtils.equals(attr.getNodeName(), "implClass")){
							answerPart.setAnswerImplClass(attr.getTextContent());
						}
					}
					if(StringUtils.isNotBlank(answerPart.getPartCode())){
						answerPart.setCreateDate(new Date());
						answerPartService.create(answerPart);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
