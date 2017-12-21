package com.excellence.iaserver.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ClientInfo {
	
	/**
	 * 获取ip地址
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
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
	
	/**
	 * 浏览器名称
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getExplorerName(HttpServletRequest request){   
		String info = request.getHeader("user-agent");
		String str = "未知";   
        if(info.indexOf("MSIE") != -1){   
             str = "MSIE"; //微软IE   
        }else if(info.indexOf("Firefox") != -1){   
            str = "Firefox"; //火狐   
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
	 * 浏览器版本
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getExplorerVersion(HttpServletRequest request){  
		String version = "未知";
		String info = request.getHeader("user-agent");
    	String str = "";   
        Pattern pattern = Pattern.compile("");   
        Matcher matcher;   
        if(info.indexOf("MSIE") != -1){   
             str = "MSIE"; //微软IE   
              pattern = Pattern.compile(str + "\\s([0-9.]+)");   
        }else if(info.indexOf("Firefox") != -1){   
            str = "Firefox"; //火狐   
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
	 * 获取操作系统名称
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getOSName(HttpServletRequest request){  
		 String info = request.getHeader("user-agent");
         String str = "未知";   
         if(info.indexOf("Windows") != -1){   
             str = "Windows"; //Windows NT 6.1   
         }   
         return str;    
     }
	
	/**
	 * 获取操作系统版本
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-5-26
	 * @copyright Excellence co.
	 */
	public static String getOSVersion(HttpServletRequest request){  
		String info = request.getHeader("user-agent");
		String version = "未知";
        String str = "未知";   
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
