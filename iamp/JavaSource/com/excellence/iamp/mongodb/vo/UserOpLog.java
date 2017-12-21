package com.excellence.iamp.mongodb.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "ia_user_op_log")
public class UserOpLog {
	//Ψһ��ʶ
	@Id
	private String id;
	//�û�ID
	private String userId;
	//�û���
	private String userName;
	//Ӧ�ô���
	private String appCode;
	//Ӧ������
	private String appName;
	//��Ϊ����
	private String opCode;
	//��Ϊ����
	private String opName;
	//����ʶ������
	private String searchText;
	//��Դ����
	private String resTitle;
	//��Դ����
	private String granularity;
	//ִ��ʱ��
	private Date opDate;
	//��Դ����·��
	private String resUrl;
	//��Դ��ʶ
	private String resId;
	//��Դ��Դ
	private String resSource;
	//��Դ���
	private String genre;
	//�û�Ip
	private String ipAddress;
	//���������
	private String exploreName;
	//������汾
	private String exploreVersion;
	//����ϵͳ����
	private String osName;
	//����ϵͳ�汾��
	private String osVersion;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getResTitle() {
		return resTitle;
	}
	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}
	public String getGranularity() {
		return granularity;
	}
	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResSource() {
		return resSource;
	}
	public void setResSource(String resSource) {
		this.resSource = resSource;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getExploreName() {
		return exploreName;
	}
	public void setExploreName(String exploreName) {
		this.exploreName = exploreName;
	}
	public String getExploreVersion() {
		return exploreVersion;
	}
	public void setExploreVersion(String exploreVersion) {
		this.exploreVersion = exploreVersion;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
