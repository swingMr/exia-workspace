package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * App应用会员管理
 * @author wangjg
 *
 */
public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6865143485630675295L;
	/**
	 * 会员id
	 */
    private String memberId;
    /**
	 * 会员账号
	 */
    private String memberAccount;
    /**
	 * 会员名字
	 */
    private String memberName;
    /**
	 * 绑定qq
	 */
    private String qqNum;
    /**
	 * 绑定邮箱
	 */
    private String emailAddress;
    /**
	 * 会员密码
	 */
    private String memberPsw;
    /**
	 * 微信号
	 */
    private String wechatNum;
    /**
   	 * 电话号码
   	 */
    private String phoneNum;
    /**
	 * 关注应用
	 */
    private String remark;
    /**
   	 * 创建日期
   	 */
    private Date createDate;
    /**
	 * 创建人id
	 */
    private String creatorId;
    /**
	 * 创建人名字
	 */
    private String creatorName;
    /**
   	 * 其他属性
   	 */
    private String extendAttribute;
    /**
	 * 更新日期
	 */
    private Date updateDate;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount == null ? null : memberAccount.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum == null ? null : qqNum.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public String getMemberPsw() {
        return memberPsw;
    }

    public void setMemberPsw(String memberPsw) {
        this.memberPsw = memberPsw == null ? null : memberPsw.trim();
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum == null ? null : wechatNum.trim();
    }
    public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }	
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public String getExtendAttribute() {
		return extendAttribute;
	}

	public void setExtendAttribute(String extendAttribute) {
		this.extendAttribute = extendAttribute;
	}
}