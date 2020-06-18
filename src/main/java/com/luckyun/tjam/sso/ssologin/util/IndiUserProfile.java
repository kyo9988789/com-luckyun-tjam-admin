package com.luckyun.tjam.sso.ssologin.util;

/**
 * 通过SSO验证的用户，会在Session中保存此用户信息对象
 *
 * @author 王德勇
 */
public class IndiUserProfile {

    // OA用户名
    private String userNameOfOA;

    // 统一身份ID
    private String userId;

    // 真实姓名
    private String displayName;

    // 用户账号列表
    private String accountGroup;

    // SSO令牌
    private String ltpaToken;

    //sso域
    private String domain;

    public String getUserNameOfOA() {

        return userNameOfOA;
    }

    public void setUserNameOfOA( String userNameOfOA) {

        this.userNameOfOA = userNameOfOA;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId( String userId) {

        this.userId = userId;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName( String displayName) {

        this.displayName = displayName;
    }

    public String getAccountGroup() {

        return accountGroup;
    }

    public void setAccountGroup( String accountGroup) {

        this.accountGroup = accountGroup;
    }

    public String getLtpaToken() {

        return ltpaToken;
    }

    public void setLtpaToken( String ltpaToken) {

        this.ltpaToken = ltpaToken;
    }

    public String getDomain() {

        return domain;
    }

    public void setDomain( String domain) {

        this.domain = domain;
    }

}
