package com.excellence.iaserver.common.util;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.excellence.iamp.mongodb.service.UserOpLogService;
import com.excellence.iamp.mongodb.vo.UserOpLog;

/**
 * 行为日志工具类
 * @author wangjg
 * @date 2017-9-4
 * @2014-1015 Excellence
 */
@Component
public class UserOpLogUtil {
	
	@Autowired
	private UserOpLogService userOpLogService;
	/**
	 * @param context  上下文 格式如下：
	 * {
	    "user": {
	        "account": "当前用户账号",
	        "ip": "当前IP地址",
	        "name": "当前用户姓名"
	    },
	    "org": {
	        "unitName": "所在单位名称",
	        "unitCode": "所在单位组织代码",
	        "superiorUnitName": "上级单位名称"
	    },
	    "location": {
	        "countryName": "所在国家地区名称",
	        "provinceName": "所在省份洲名称",
	        "cityName": "所在城市名称"
	    },
	    "time": {
	        "year": "当前年份",
	        "month": "当前年月，格式：yyyy-MM",
	        "date": "当前日期，格式：yyyy-MM-dd"
	    }
		}
	 * @param appCode		app代码
	 * @param appName		app名字
	 * @param opCode		行为编号
	 * @param opName		行为名称
	 * @param text			输入的文本json格式字符串
	 * @param title			对应保存到日志中的restitle
	 * **/
	@Async
	public  void recordOpLog(String context,String appCode,String appName,String opCode,String opName,String text,String title){
		try {
			JSONObject obj = new JSONObject(context);
			UserOpLog log = new UserOpLog();
			if(obj.has("user")){
				JSONObject jsonUser = (JSONObject) obj.get("user");
				String userId = jsonUser.get("id")==null ? "" : (String)jsonUser.get("id");
				String userName = jsonUser.get("name")==null ? "" : (String)jsonUser.get("name");
				if(!StringUtils.equals(userName, "匿名")){
					String exploreName = jsonUser.get("exploreName")==null ? "" : (String)jsonUser.get("exploreName");
					String exploreVersion = jsonUser.get("exploreVersion")==null ? "" : (String)jsonUser.get("exploreVersion");
					String osName = jsonUser.get("osName")==null ? "" : (String)jsonUser.get("osName");
					String osVersion = jsonUser.get("osVersion")==null ? "" : (String)jsonUser.get("osVersion");
					String ip = jsonUser.get("ip")==null ? "" : (String)jsonUser.get("ip");
					log.setUserName(userName);
					log.setUserId(userId);
					log.setIpAddress(ip);
					log.setOpCode(opCode);
					log.setOpName(opName);
					log.setSearchText(text);
					log.setOpDate(new Date());
					log.setResTitle(title);
					log.setAppCode(appCode);
					log.setAppName(appName);
					log.setExploreName(exploreName);
					log.setExploreVersion(exploreVersion);
					log.setOsName(osName);
					log.setOsVersion(osVersion);
					userOpLogService.save(log);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
