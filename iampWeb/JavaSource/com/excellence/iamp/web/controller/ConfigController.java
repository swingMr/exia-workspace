package com.excellence.iamp.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iacommon.common.util.Property;
import com.excellence.iacommon.common.util.PropertyUtil;

@Controller
@RequestMapping("/config")
public class ConfigController {
	private static String oaPath =System.getProperty("oapath");
	private static String propertiesPath= oaPath + File.separator + "exiaserver" + File.separator +"configration.properties";
	
	/**获取配置文件键值对
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 * @throws  
	 */
	@RequestMapping("/list")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("serviceConfig/config/list");
		List<Property> list = new ArrayList<Property>();
		try {
			Properties properties = PropertyUtil.loadProperties(propertiesPath);
			Iterator it=properties.entrySet().iterator();
			while(it.hasNext()){
			    Map.Entry entry=(Map.Entry)it.next();
			    Property obj = new Property();
			    obj.setKey(entry.getKey().toString());
			    obj.setValue(entry.getValue().toString());
			    list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("list", list);
		return mv;
	}
	
	/**保存配置文件键值对
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 * @throws  
	 */
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		try {
			Properties properties = new Properties();
			//先清空当前内容；
			File file = new File(propertiesPath);
			if(!file.exists()) {
				file.createNewFile();
			}
			OutputStreamWriter out;
			out = new OutputStreamWriter(new FileOutputStream(propertiesPath),"UTF-8");
			out.write("");
			out.flush();
			out.close();
			//获取key和value 并存入
			String arr = request.getParameter("arr");
			JSONArray jsonArr = new JSONArray(arr);
			for(int i=0;jsonArr.length()>i;i++){
				JSONObject obj = new JSONObject();
				obj = jsonArr.getJSONObject(i);
				properties.setProperty(obj.get("key").toString(), obj.get("value").toString());
			}
			PropertyUtil.storeProperties(propertiesPath, properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
