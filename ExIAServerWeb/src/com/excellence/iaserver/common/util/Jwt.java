package com.excellence.iaserver.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONObject;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.util.Base64;
/**
 * �ṩ����token��У��token��������
 * @author huangjinyuan
 *
 */
public class Jwt {
	/**
	 * ��Կ
	 */
	private static final String SECRET="3d990d2276917dfac04467df11fff26d";
	
	/**
	 * token����(tokenʧЧ��)
	 */
	public static Number EXPIRED=-1; 
	
	/**
	 * У��ʧ�ܣ�token��һ�£�
	 */
	public static Number FAIL=0;
	
	/**
	 * У��ɹ�
	 */
	public static Number SUCCESS=1;
	
	/**
	 * �������쳣��У��tokenʱ�������
	 */
	public static Number EXCEPT=2;
	
	/**
	 * ����token���÷���ֻ���û���¼�ɹ������
	 * @param Map���ϣ���Ҫ�洢�û�id��token����ʱ�䣬token����ʱ���
	 * @return token�ַ���
	 * @throws KeyLengthException 
	 */
	public static String createToken(Map<String, Object> playLoad) {
		///B
		JSONObject userInfo = new JSONObject(playLoad);
        Payload payload = new Payload(userInfo);
        
        ///A
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        // ����һ�� JWS object
        JWSObject jwsObject = new JWSObject(header, payload);
        //���� HMAC signer        
        JWSSigner signer = new MACSigner(SECRET.getBytes());
        try {
			jwsObject.sign(signer);
		} catch (JOSEException e) {
			 System.err.println("ǩ��ʧ��" + e.getMessage());
			e.printStackTrace();
		}
		return jwsObject.serialize();
	}
	
	/**
	 * У��token�Ƿ�Ϸ�������Map����,��������Ҫ����  isSuccess�Ƿ�ɹ�  status״̬��   data��Ȩ�ɹ����token����ȡ������
	 * �÷����ڹ������е��ã�ÿ������APIʱ��У��
	 * @param token
	 * @return
	 * @throws KeyLengthException
	 */
	public static Map<String, Object> validToken(String token) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		String[] tokenArr=token.split("\\.");
		///A
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
		Payload payload = null;
		JWSObject jwsObject = null;
		if(tokenArr.length > 1){
			payload = new Payload(new Base64(tokenArr[1]).decodeToString());
			// ����һ�� JWS object
	        jwsObject = new JWSObject(header, payload);
		}
		
        //���� HMAC signer        
        JWSSigner signer = new MACSigner(SECRET.getBytes());
        try {
			jwsObject.sign(signer);
			if( jwsObject.serialize().equals(token)){
	        	System.out.println("tokenУ��ɹ�");
	        	resultMap.put("isSuccess", true);
	        	resultMap.put("status", SUCCESS);
	        	resultMap.put("data", payload.toJSONObject());
	        }else{
	        	resultMap.put("isSuccess", false);
	        	resultMap.put("status", FAIL);
	        }
		} catch (Exception e) {
			 System.err.println("ǩ��ʧ��" + e.getMessage());
			 resultMap.put("isSuccess", false);
			 resultMap.put("status", EXCEPT);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	public static void main(String[] args) {
		Map<String , Object> payload=new HashMap<String, Object>();
        Date date=new Date();
        payload.put("uid", "291969452");//�û�id
        payload.put("iat", date.getTime());//����ʱ��
        payload.put("ext",date.getTime()+1000*60*60);//����ʱ��1Сʱ
        String token=null;
        token=Jwt.createToken(payload);
        System.out.println(token);
        
        Map<String, Object> result=Jwt.validToken(token);
        System.out.println(result.get("isSuccess"));
        
        //У��
	}
	
}
