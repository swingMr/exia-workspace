package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * App应用与会员管理
 * @author wangjg
 *
 */
public class AppMember implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6698932111223643110L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 应用id
	 */
	private String appId;
	/**
	 * 会员id
	 */
    private String memberId;
    /**
	 * 应用名字
	 */
    private String appName;
    /**
	 * 会员名字
	 */
    private String memberName;
    /**
	 * 分组
	 */
    private String appGroup;
    /**
	 * 关注时间
	 */
    private Date followTime;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }
    
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }


    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}
}