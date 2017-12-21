package com.excellence.iacommon.common.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.excellence.common.OAConstant;
import com.excellence.common.UserInfo;
import com.excellence.common.UserProxy;
import com.excellence.platform.um.dao.UserService;
import com.excellence.platform.um.exception.CommonAppException;
import com.excellence.platform.um.exception.PasswordNotSetException;
import com.excellence.platform.um.vo.Common;
import com.excellence.platform.um.vo.Organization;
import com.excellence.platform.um.vo.User;
/**
 * �û���ȡ����
 * 
 * @author huangjinyuan
 * @created 2017-3-22
 * @copyright Excellence co.
 */
public class UserUtil {
	
	public static UserInfo getCurrentUser(HttpServletRequest request) throws CommonAppException {
		UserInfo localUser = null;
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(OAConstant.SESSION_USER_KEY);
		if(obj != null) {
			localUser = (UserInfo) obj;
		}
		return localUser;
	}
	
	/**
	 * ��ȡ��λ�б�
	 * @param userId
	 * @return
	 * @throws CommonAppException
	 * @author huangjinyuan
	 * @created 2017-5-19
	 * @copyright Excellence co.
	 */
	@SuppressWarnings("rawtypes")
	public static List<Organization> getUnits(int userId) throws CommonAppException {
		UserService userService = UserService.getInstance();
		List<Organization> unitList = new ArrayList<Organization>();
		Collection orgs=userService.getRecursiveOrgsByUserId(userId);
		for(Iterator it = orgs.iterator();it.hasNext();){
			Organization org=(Organization)it.next();
			if(org.getIsDept() == 1) {
				unitList.add(org);
			}
		}
		return unitList;
	}
	
	/**
	 * ��¼
	 * @param userName
	 * @param password
	 * @return
	 * @throws CommonAppException
	 * @throws PasswordNotSetException
	 * @author huangjinyuan
	 * @created 2017��7��5��
	 * @copyright Excellence co.
	 */
	public static UserInfo login(HttpServletRequest request) throws CommonAppException, PasswordNotSetException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)) {
			UserService userService = UserService.getInstance();
			User actualUser = userService.login(userName, password);
			if(actualUser != null) {
				UserInfo userInfo = new UserProxy();
				userInfo.setId(""+actualUser.getId());
				userInfo.setUsername(actualUser.getName());
				userInfo.setAccount(actualUser.getAccount());
				userInfo.setActualUser(actualUser);
				userInfo.setLogintime(new Date());
				userInfo.setIp(request.getRemoteAddr());
				userInfo.setMachine(request.getRemoteHost());
				/** ��ȡ��ǰ�û����ڵ������� */
				Common org = null;
				Common orgUnit = null;

				org = userService.getMainOrganizationByUserId(actualUser.getId());
				if (org != null) {
					userInfo.setOrg(org.getId() + "");
					userInfo.setOrgName(org.getName());
				}
				
				/** ��ȡ��ǰ�û����ڵĵ�λʵ�� */
				orgUnit = userService.getUnit(actualUser.getId());
				if (orgUnit != null) {
					userInfo.setUnitId(orgUnit.getId());
					userInfo.setUnitName(orgUnit.getName());
				}
				
				/** �жϸ��û��Ƿ�Ϊϵͳ����Ա */
				userInfo.setSysAdmin(userService.isSystemAdmin(actualUser.getId()));
				request.getSession().setAttribute(OAConstant.SESSION_USER_KEY, userInfo);
				return userInfo;
			}
		}
		return null;
	}
	
	/**
	 * ��ȡֱ�ӵ�λ
	 * @param userId
	 * @return
	 * @throws CommonAppException
	 * @author huangjinyuan
	 * @created 2017-5-19
	 * @copyright Excellence co.
	 */
	public static Organization getDirectUnit(int userId) throws CommonAppException {
		List<Organization> unitList = getUnits(userId);
		if(unitList != null && unitList.size() > 0) {
			return unitList.get(unitList.size() - 1);
		}
		return null;
	}
	
	/**
	 * ��ȡֱ�Ӳ���
	 * @param userId
	 * @return
	 * @throws CommonAppException
	 * @author huangjinyuan
	 * @created 2017-5-19
	 * @copyright Excellence co.
	 */
	public static Organization getDirectDepartment(int userId) throws CommonAppException {
		List<Organization> deptList = getDepartments(userId);
		if(deptList != null && deptList.size() > 0) {
			return deptList.get(deptList.size() - 1);
		}
		return null;
	}
	
	/**
	 * ��ȡ�����б�
	 * @param userId
	 * @return
	 * @throws CommonAppException
	 * @author huangjinyuan
	 * @created 2017-5-19
	 * @copyright Excellence co.
	 */
	@SuppressWarnings("rawtypes")
	public static List<Organization> getDepartments(int userId) throws CommonAppException {
		UserService userService = UserService.getInstance();
		List<Organization> deptList = new ArrayList<Organization>();
		Collection orgs=userService.getRecursiveOrgsByUserId(userId);
		for(Iterator it = orgs.iterator();it.hasNext();){
			Organization org=(Organization)it.next();
			if(org.getIsDept() == 0) {
				deptList.add(org);
			}
		}
		return deptList;
	}
	
}
