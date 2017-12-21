package com.excellence.iaserver.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ClientInfo {
	
	/**
	 * ��ȡip��ַ
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //��������ȡ�������õ�IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
	
	/**
	 * ���������
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getExplorerName(HttpServletRequest request){   
		String info = request.getHeader("user-agent");
		String str = "δ֪";   
        if(info.indexOf("MSIE") != -1){   
             str = "MSIE"; //΢��IE   
        }else if(info.indexOf("Firefox") != -1){   
            str = "Firefox"; //���   
        }else if(info.indexOf("Chrome") != -1){   
             str = "Chrome"; //Google   
        }else if(info.indexOf("Opera") != -1){   
             str = "Opera"; //Opera   
        } else {
        	str = "MSIE";
        }
        return str;   
     }
	
	/**
	 * ������汾
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getExplorerVersion(HttpServletRequest request){  
		String version = "δ֪";
		String info = request.getHeader("user-agent");
    	String str = "";   
        Pattern pattern = Pattern.compile("");   
        Matcher matcher;   
        if(info.indexOf("MSIE") != -1){   
             str = "MSIE"; //΢��IE   
              pattern = Pattern.compile(str + "\\s([0-9.]+)");   
        }else if(info.indexOf("Firefox") != -1){   
            str = "Firefox"; //���   
              pattern = Pattern.compile(str + "\\/([0-9.]+)");   
        }else if(info.indexOf("Chrome") != -1){   
             str = "Chrome"; //Google   
            pattern = Pattern.compile(str + "\\/([0-9.]+)");   
        }else if(info.indexOf("Opera") != -1){   
             str = "Opera"; //Opera   
             pattern = Pattern.compile("Version\\/([0-9.]+)");   
        } 
        if(pattern.pattern() != "") {
        	matcher = pattern.matcher(info);   
        	if(matcher.find()) version = matcher.group(1);   
        }
        return version;   
     } 
	
	/**
	 * ��ȡ����ϵͳ����
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getOSName(HttpServletRequest request){  
		 String info = request.getHeader("user-agent");
         String str = "δ֪";   
         if(info.indexOf("Windows") != -1){   
             str = "Windows"; //Windows NT 6.1   
         }   
         return str;    
     }
	
	/**
	 * ��ȡ����ϵͳ�汾
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getOSVersion(HttpServletRequest request){  
		String info = request.getHeader("user-agent");
		String version = "δ֪";
        String str = "δ֪";   
        Pattern pattern = Pattern.compile("");   
        Matcher matcher;   
        if(info.indexOf("Windows") != -1){   
            str = "Windows"; //Windows NT 6.1   
            pattern = Pattern.compile(str + "\\s([a-zA-Z0-9]+\\s[0-9.]+)");   
            matcher = pattern.matcher(info);   
            if(matcher.find()) version = matcher.group(1);   
            return version;
        }   
        matcher = pattern.matcher(info);   
        if(matcher.find()) version = matcher.group();   
        return version;    
    }
}
