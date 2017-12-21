package com.excellence.iamp.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.FileExtConfigService;
import com.excellence.iamp.vo.FileExtConfig;
import com.excellence.platform.um.exception.CommonAppException;

@Controller
@RequestMapping("/fileconfig")
public class FileExtConfigController {
	
	@Autowired
	private FileExtConfigService fileExtConfigService;
	
	@RequestMapping("/find.do")
	public String getConfig(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		FileExtConfig fileExtConfig = fileExtConfigService.getFileExtConfig();
		request.setAttribute("config", fileExtConfig);
		return "/serviceConfig/fileConfig/detail";
	}
	
	@RequestMapping("/save")
	public String saveConfig(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, CommonAppException {
		Map map  = WebUtil.requestToMap(request);
		String configId = MapUtils.getString(map, "configId");
		long spaceSize = MapUtils.getLongValue(map, "spaceSize");
		int fileCount = MapUtils.getIntValue(map, "fileCount");
		int expireType = MapUtils.getIntValue(map, "expireType");
		String txtExt = MapUtils.getString(map, "txtExt");
		String pdfExt = MapUtils.getString(map, "pdfExt");
		String htmlExt = MapUtils.getString(map, "htmlExt");
		String jpgExt = MapUtils.getString(map, "jpgExt");
		String wordExt = MapUtils.getString(map, "wordExt");
		UserInfo user = UserUtil.getCurrentUser(request);
		long expireTime;
		switch (expireType) {
		case 0://6小时
			expireTime = 3600000*6;
			break;
		case 1://1天
			expireTime = 3600000*24;		
					break;
		case 2://3天
			expireTime = 3600000*24*3;	
			break;
		case 3://7天
			expireTime = 3600000*24*7;	
			break;
		case 4://30天
			expireTime = 3600000*24*30;	
			break;
		default:
			expireTime = 3600000*6;
			break;
		}
		if(StringUtils.isNotEmpty(configId)) {
			FileExtConfig config = fileExtConfigService.findById(configId);
			config.setSpaceSize(spaceSize);
			config.setFileCount(fileCount);
			config.setExpireTime(expireTime);
			config.setHtmlExt(htmlExt);
			config.setJpgExt(jpgExt);
			config.setPdfExt(pdfExt);
			config.setTxtExt(txtExt);
			config.setWordExt(wordExt);
			config.setUpdateDate(T.getNow());
			fileExtConfigService.update(config);
		} else {
			FileExtConfig config = new FileExtConfig();
			config.setSpaceSize(spaceSize);
			config.setFileCount(fileCount);
			config.setExpireTime(expireTime);
			config.setUpdateDate(T.getNow());
			config.setHtmlExt(htmlExt);
			config.setJpgExt(jpgExt);
			config.setPdfExt(pdfExt);
			config.setTxtExt(txtExt);
			config.setWordExt(wordExt);
			config.setCreateDate(T.getNow());
			config.setUpdateDate(T.getNow());
			config.setCreatorId(user.getId());
			config.setCreatorName(user.getUsername());
			fileExtConfigService.create(config);
		}
		JSONObject json = new JSONObject();
		boolean result = true;
		String msg = "保存成功";
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
}
