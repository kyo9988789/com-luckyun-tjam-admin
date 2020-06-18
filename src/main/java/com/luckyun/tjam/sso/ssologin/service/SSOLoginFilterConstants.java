package com.luckyun.tjam.sso.ssologin.service;

/**
 * 相关常量类
 *
 *
 */
public interface SSOLoginFilterConstants {

    // SSO令牌校验成功
    public static final int SSO_VERIFY_OK = 0;

    // 需要登录
    public static final int SSO_VERIFY_NEED_LOGIN = 1;

    // 没有权限访问本应用
    public static final int SSO_VERIFY_NO_ACCESS = 2;

    // 找不到用户
    public static final int SSO_VERIFY_NO_USER = 3;

    // 验证服务器返回信息错误
    public static final int SSO_VERIFY_RETURN_ERROR = 4;

    // 验证服务器没法返回信息
    public static final int SSO_VERIFY_RETURN_NULL = 5;

    // Filter初始化参数名 用于指定服务器协议、域名、端口
    public static final String FILTER_HOST = "Host";

    // Filter初始化参数名 用于校验SSO令牌的Domino IIOP连接地址
    public static final String FILTER_DIIOP_CONNECTION = "DIIOPConnection";

    // Filter初始化参数名 用户获取临时用户Token
    public static final String FILTER_TEMP_CONNECTION = "TempUserConnection";

    // Filter初始化参数名 该参数指定SSO登录页地址
    public static final String FILTER_LOGIN_CONTEXT = "LoginContext";

    //所属网络域
    public static final String FILTER_DOMAIN = "Domain";

    // Filter初始化参数名 用于指定本应用的SSO应用标识
    public static final String FILTER_APP_CODE = "AppCode";

    // Filter初始化参数名 该参数指定SSO登录页参数
    public static final String FILTER_LOGIN_CONTEXT_PARAM = "LoginContextParam";

    // 子系统登录地址
    public static final String FILTER_LOCAL_LOGIN_PATH = "LocalLoginPath";

    // Filter初始化参数名 用于指定非授权用户页面参数
    public static final String FILTER_UNAUTHORIZED_CONTEXT = "UnAuthorizedContext";

    // Filter初始化参数名 用于指定服务器返回错误页面参数
    public static final String FILTER_SSO_SERVER_ERROR_CONTEXT = "SSOServerError";

    // Filter初始化参数名 用于指定服务器访问超时页面参数
    public static final String FILTER_TIME_OUT_CONTEXT = "TimeOut";

    // Filter初始化参数名 配置是否显示SSO登录调试信息
    public static final String FILTER_LOGIN_DEBUG = "LoginDebug";

    // 错误消息
    public static final String MSG_LOCAL_LOGIN_PATH_NOT_DEFINED = "web.xml中缺少Filter初始化参数 - " + FILTER_LOCAL_LOGIN_PATH;
    public static final String MSG_UNAUTHORIZED_CONTEXT_NOT_DEFINED = "web.xml中缺少Filter初始化参数 - " + FILTER_UNAUTHORIZED_CONTEXT;
    public static final String MSG_SSO_SERVER_ERROR_CONTEXT_NOT_DEFINED = "web.xml中缺少Filter初始化参数 - " + FILTER_SSO_SERVER_ERROR_CONTEXT;
    public static final String MSG_TIME_OUT_CONTEXT_NOT_DEFINED = "web.xml中缺少Filter初始化参数 - " + FILTER_TIME_OUT_CONTEXT;
    //	public static final String MSG_UNAUTHORIZED_CONTEXT_NOT_DEFINED = "web.xml中缺少Filter初始化参数 - " + FILTER_UNAUTHORIZED_CONTEXT;

}