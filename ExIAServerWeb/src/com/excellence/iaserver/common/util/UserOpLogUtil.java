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
 * ��Ϊ��־������
 * @author wangjg
 * @date 2017-9-4
 * @2014-1015 Excellence
 */
@Component
public class UserOpLogUtil {
	
	@Autowired
	private UserOpLogService userOpLogService;
	/**
	 * @param context  ������ ��ʽ���£�
	 * {
	    "user": {
	        "account": "��ǰ�û��˺�",
	        "ip": "��ǰIP��ַ",
	        "name": "��ǰ�û�����"
	    },
	    "org": {
	        "unitName": "���ڵ�λ����",
	        "unitCode": "���ڵ�λ��֯����",
	        "superiorUnitName": "�ϼ���λ����"
	    },
	    "location": {
	        "countryName": "���ڹ��ҵ�������",
	        "provinceName": "����ʡ��������",
	        "cityName": "���ڳ�������"
	    },
	    "time": {
	        "year": "��ǰ���",
	        "month": "��ǰ���£���ʽ��yyyy-MM",
	        "date": "��ǰ���ڣ���ʽ��yyyy-MM-dd"
	    }
		}
	 * @param appCode		app����
	 * @param appName		app����
	 * @param opCode		��Ϊ���
	 * @param opName		��Ϊ����
	 * @param text			������ı�json��ʽ�ַ���
	 * @param title			��Ӧ���浽��־�е�restitle
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
				if(!StringUtils.equals(userName, "����")){
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
