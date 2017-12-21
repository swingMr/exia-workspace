package com.excellence.iaserver.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

  
/**
 * ����Python��util
 * @author Administrator
 *
 */
public class JavaHandlePython {
	public static String HandlePython(String pythonFile,List<String> parameter) {
		String[] str1 = new String[] {"python",pythonFile};
        //���������д������
		String[] par = new String[parameter.size()];
		if(parameter.size()>0){
			for(int i=0;i<parameter.size();i++){
				par[i]=parameter.get(i);
			}
		}
		String[] str = (String[]) ArrayUtils.addAll(str1, par);
        String resultStr="";
        try {
			Process pr = Runtime.getRuntime().exec(str);
			
			InputStream in = pr.getInputStream();
			InputStream err = pr.getErrorStream();
			
			BufferedReader errReader = new BufferedReader(new InputStreamReader(err));
			String line = null;      
			try {      
				/*while ((line = errReader.readLine()) != null) {      
					System.out.println(line + "\n");
				}*/
			}catch(Exception ex){}
			
			BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
			try {      
				while ((line = inReader.readLine()) != null) {      
					resultStr+=line;
				}
			}catch(Exception ex){}
			
			in.close();
			err.close();
//			System.out.println(resultStr);
			pr.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return resultStr;
	}
	
	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		strList.add("����2011����������Ƹĸ����滮");
		strList.add("0.0002");
		strList.add("0.0001");
		String resultStr = HandlePython("F:/EXKEWorkSpace/ExOIApp/oadoc/exoi/python/AssociationRuleWords.py",strList);
		System.out.println(resultStr);
	}
}  





