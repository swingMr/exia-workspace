package com.excellence.iamp.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.exke.connector.ConnectorException;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.WebUtil;

@Controller
public class MenuController {
	
	private CookieStore cookieStore = null; 	
	/**获取知识本体菜单
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 * @throws ConnectorException 
	 */
	@RequestMapping("/menu")
	public ModelAndView getKePage(HttpServletRequest request, HttpServletResponse response) {
		HttpPost httpPost = null;
		try{
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("account", Constant.EXKE_ACCOUNT));
			parameters.add(new BasicNameValuePair("password", Constant.EXKE_PASSWORD));
			parameters.add(new BasicNameValuePair("mode", "client"));
			
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
			httpPost = new HttpPost("http://" + Constant.EXKE_HOST + ":" + Constant.EXKE_PORT
					+"/ExKE/login.do?action=run");
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "GBK"));
	        CloseableHttpResponse responses = httpClient.execute(httpPost, context);
	        HttpEntity entity = responses.getEntity();
			String result = EntityUtils.toString(entity);
			
			if(result==null || "".equals(result.trim())) 
				throw new ConnectorException("Time out!!");
			
			JSONObject jsonObject = new JSONObject(result);
			if(!jsonObject.getBoolean("status")){
				throw new ConnectorException(jsonObject.getString("msg"));
			}
			//登录成功 并创建token
			String token ="";
			if(jsonObject.has("token") && StringUtils.isNotEmpty((String) jsonObject.get("token"))){
				token = (String)jsonObject.get("token");
			}
			System.out.println("token:!!!!!"+token);
			Map params = WebUtil.requestToMap(request);
			String targetMenu = MapUtils.getString(params, "targetMenu", null);
			response.sendRedirect("http://" + Constant.EXKE_HOST
					+ ":" + Constant.EXKE_PORT
					+ targetMenu+"?token="+token);			
/*			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setDefaultCookieStore(this.cookieStore).build();
			HttpPost httpPost = new HttpPost("http://" + Constant.EXKE_HOST
					+ ":" + Constant.EXKE_PORT
					+ targetMenu+"?token="+token);
	        CloseableHttpResponse rep = httpClient.execute(httpPost, context);
	        HttpEntity entity = rep.getEntity();
			String result = EntityUtils.toString(entity);
			result = result.replaceAll("/ExKECommon", "http://" + Constant.EXKE_HOST + ":" + Constant.EXKE_PORT
					+"/ExKECommon").replaceAll("/ExKEGov", "http://" + Constant.EXKE_HOST + ":" + Constant.EXKE_PORT
					+"/ExKEGov");
			System.out.println(result);
			WebUtil.outputHtml(response, result);*/
		}catch(Exception ex){
			
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
        return null;
    }
	
	@RequestMapping("/menu/address")
	public ModelAndView getAddress(HttpServletRequest request, HttpServletResponse response) {
		String targetMenu = request.getParameter("targetMenu");
		try {
			response.sendRedirect("http://" + Constant.Address_HOST
					+ ":" + Constant.Address_PORT
					+ targetMenu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
