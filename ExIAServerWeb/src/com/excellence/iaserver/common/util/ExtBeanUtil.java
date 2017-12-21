package com.excellence.iaserver.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.json.JSONArray;

/**
 * À©Õ¹org.apache.commons.beanutils¹¤¾ß
 * @author Huangyb
 * @date 2016-6-16
 * @2014-2016 Excellence
 */
public abstract class ExtBeanUtil {
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
        Object obj = beanClass.newInstance();  
        BeanUtils.populate(obj, map);
        return obj;  
    }
	
    public static Map<?, ?> objectToMap(Object obj) {  
        if(obj == null)  
            return null;
        return new BeanMap(obj);
    }
    
    public static List<String> jsonToList(JSONArray jsonArray){
    	if(jsonArray==null || jsonArray.length()==0)return null;
    	List<String> list = new ArrayList<String>();
    	for(int i = 0 ; i < jsonArray.length() ; i++){
    		list.add(jsonArray.getString(i));
    	}
    	return list;
    }
}
