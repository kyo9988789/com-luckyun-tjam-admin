package com.luckyun.tjam.sso.ssologin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luckyun.tjam.sso.ssologin.util.IndiUserProfile;
import com.luckyun.tjam.sso.ssologin.util.WebXml;

/*
 * @author 刘宏毅 2017-07-14
 */

public class SSOLoginService {

    private String strHost = WebXml.HOST;

    private String strDIIOPConnection = WebXml.DIIOPConnection;
    private String strTempConnection = WebXml.TempUserConnection;

    private String strLoginContext = WebXml.LoginContext;
    private String strDomain = WebXml.Domain;

    private String strAppCode = WebXml.AppCode;
    private String strLocalLoginPath = WebXml.LocalLoginPath;
    private String strFobiddenContext = WebXml.UnAuthorizedContext;
    private String strSSOServerError = WebXml.SSOServerError;
    private String strTimeOut = WebXml.TimeOut;
    private String strDebug = WebXml.LoginDebug;

    //检查初始化参数
    public String checkIniError() {

        String strReturn = "";
        if ( strLocalLoginPath.equals( "")) {//没有配置子系统登录页面错误返回页面

            logMessage( "MSG_LOCAL_LOGIN_PATH_NOT_DEFINED=" + SSOLoginFilterConstants.MSG_LOCAL_LOGIN_PATH_NOT_DEFINED);
            strReturn = SSOLoginFilterConstants.MSG_LOCAL_LOGIN_PATH_NOT_DEFINED;

        } else if ( strFobiddenContext.equals( "")) {//没有配置用户授权错误返回页面

            logMessage( "MSG_FOBIDDEN_CONTEXT_NOT_DEFINED=" + SSOLoginFilterConstants.MSG_UNAUTHORIZED_CONTEXT_NOT_DEFINED);
            strReturn = SSOLoginFilterConstants.MSG_UNAUTHORIZED_CONTEXT_NOT_DEFINED;

        } else if ( strSSOServerError.equals( "")) {//没有配置服务器返回值错误返回页面

            logMessage( "MSG_SSO_SERVER_ERROR_CONTEXT_NOT_DEFINED=" + SSOLoginFilterConstants.MSG_SSO_SERVER_ERROR_CONTEXT_NOT_DEFINED);
            strReturn = SSOLoginFilterConstants.MSG_SSO_SERVER_ERROR_CONTEXT_NOT_DEFINED;

        } else if ( strTimeOut.equals( "")) {//没有配置服务器访问超时错误返回页面

            logMessage( "MSG_TIME_OUT_CONTEXT_NOT_DEFINED=" + SSOLoginFilterConstants.MSG_TIME_OUT_CONTEXT_NOT_DEFINED);
            strReturn = SSOLoginFilterConstants.MSG_TIME_OUT_CONTEXT_NOT_DEFINED;

        }

        return strReturn;
    }

    public String verSSOToken(String strLtpaToken) {

        // 如果没有SSO令牌，判定为需要登录
        if ( strLtpaToken == null) {
            return null;
        }

        // 有SSO令牌，则连接到Domino端，使用SSO创建会话
        try {

            String strUrl = strHost + strDIIOPConnection + "&rnd=" + Double.toString( Math.random());
            System.out.println("看strHost="+strHost);
            System.out.println("看strDIIOPConnection"+strDIIOPConnection);
            System.out.println("看strUrl"+strUrl);
            String strCookie = "LtpaToken=" + strLtpaToken + "; domain=" + strDomain + "; path=/";
            System.out.println("看strCookie"+strCookie);

            //获取验证信息
            String strResult = getHttpResult( strUrl, strCookie);
            System.out.println("看strResult"+strResult);
            return strResult;
        } catch ( Exception e) {
            // 会话创建失败，则需要登录
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 校验用户的SSO令牌,以确定是否允许用户继续访问本应用
     *
     * @param
     *
     * @param request
     *            当前请求上下文
     * @return 0- 校验通过 1-需要登录 2-未授权访问本应用
     */
    public int verifySSOToken(IndiUserProfile dominoUserProfile, HttpServletRequest request) {

        // 获取存储在COOKIE中的SSO令牌
        String strLtpaToken = this.getCookieValue( request, "LtpaToken");

        // 如果没有SSO令牌，判定为需要登录
        if ( strLtpaToken == null) {
            return SSOLoginFilterConstants.SSO_VERIFY_NEED_LOGIN;
        }

        // 有SSO令牌，则连接到Domino端，使用SSO创建会话
        try {

            String strUrl = strHost + strDIIOPConnection + "&rnd=" + Double.toString( Math.random());
            String strCookie = "LtpaToken=" + strLtpaToken + "; domain=" + strDomain + "; path=/";

            //获取验证信息
            String strResult = getHttpResult( strUrl, strCookie);
            if ( strResult == null || strResult.equals( "") || strResult.toLowerCase().indexOf( "<!doctype html>") == 0 || strResult.toLowerCase().indexOf( "<html>") == 0) {
                strResult = "";
            }

            if ( !strResult.equals( "")) {

                JSONObject objReturn = JSON.parseObject( strResult);
                switch ( objReturn.getIntValue( "result")) {

                    case 1:

                        return SSOLoginFilterConstants.SSO_VERIFY_NEED_LOGIN;

                    case 3:

                        System.out.println( "认证服务器中未找到该用户！");
                        return SSOLoginFilterConstants.SSO_VERIFY_NO_USER;

                    case 0:

                        // 会话创建成功，则说明SSO令牌校验正确，进一步检查用户信息是否有范围本应用
                        if ( dominoUserProfile.getUserId() == null || dominoUserProfile.getUserId().length() == 0) {

                            return setUserProfile( request, dominoUserProfile, objReturn, strLtpaToken);

                        } else {

                            if ( dominoUserProfile.getUserId() != objReturn.getString( "userid")) {

                                return setUserProfile( request, dominoUserProfile, objReturn, strLtpaToken);

                            } else {

                                logMessage( "no look");
                                return SSOLoginFilterConstants.SSO_VERIFY_OK;
                            }

                        }
                    default:

                        System.out.println( "认证服务器返回状态值错误！");
                        return SSOLoginFilterConstants.SSO_VERIFY_RETURN_ERROR;

                }

            } else {

                System.out.println( "认证服务器没有返回信息，访问超时！");
                return SSOLoginFilterConstants.SSO_VERIFY_RETURN_NULL;

            }

        } catch ( Exception e) {

            // 会话创建失败，则需要登录
            e.printStackTrace();
            return SSOLoginFilterConstants.SSO_VERIFY_NEED_LOGIN;
        }

    }

    //	获取子系统临时用户令牌
    public String getTempUserInfo() {

        String strUrl = strHost + strTempConnection + "&appcode=" + strAppCode.toLowerCase() + "&rnd=" + Double.toString( Math.random());

        //获取验证信息
        String strResult = getHttpResult( strUrl, "");

        if ( strResult == null || strResult.equals( "") || strResult.toLowerCase().indexOf( "<!doctype html>") == 0 || strResult.toLowerCase().indexOf( "<html>") == 0) {
            strResult = "{\"result\":5}";
        }

        return strResult;
    }

    //http访问sso服务器
    private String getHttpResult( String strUrl, String strCookie) {

        String strResult = "";
        int iTimeOut = 6000;

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        clientBuilder.setDefaultCredentialsProvider( credsProvider);
        CloseableHttpClient httpclient = clientBuilder.build();

        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( iTimeOut).setConnectTimeout( iTimeOut).build();

        HttpGet httpget = new HttpGet( strUrl);
        httpget.setConfig( requestConfig);
        if ( !strCookie.equals( "")) {
            httpget.addHeader( new BasicHeader( "Cookie", strCookie));
        }

        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute( httpget);
            HttpEntity entity = response.getEntity();

            if ( entity != null) {

                strResult = EntityUtils.toString( entity, "GB18030");
            }

            response.close();
            httpclient.close();

        } catch ( ClientProtocolException e) {
            e.printStackTrace();

        } catch ( IOException e) {
            e.printStackTrace();
        }

        return strResult;

    }

    //设置返回数据
    public int setUserProfile( HttpServletRequest request, IndiUserProfile dominoUserProfile, JSONObject objReturn, String ltpaToken) {

        if ( dominoUserProfile == null) {
            dominoUserProfile = new IndiUserProfile();
        }

        dominoUserProfile.setLtpaToken( ltpaToken);
        dominoUserProfile.setDomain( strDomain);

        dominoUserProfile.setUserNameOfOA( objReturn.getString( "username"));
        dominoUserProfile.setUserId( objReturn.getString( "userid"));
        dominoUserProfile.setDisplayName( objReturn.getString( "displayname"));

        JSONArray arrAccountGroup = objReturn.getJSONArray( "accountgroup");
        dominoUserProfile.setAccountGroup( arrAccountGroup.toJSONString());

        request.getSession( true).setAttribute( "DominoUserProfile", dominoUserProfile);

        if ( isAuthorizedUser( arrAccountGroup)) {
            return SSOLoginFilterConstants.SSO_VERIFY_OK;
        } else {
            return SSOLoginFilterConstants.SSO_VERIFY_NO_ACCESS;
        }

    }

    //校验用户是否开通了本地应用授权
    //AccountGroup，用户所属的账户集合，包含了该用户具有的应用权限，如OA等
    //具有本应用权限返回true,否则返回false
    private boolean isAuthorizedUser( JSONArray arrAccountGroup) {

        if ( strAppCode == null || strAppCode.length() == 0)
            return true;

        if ( arrAccountGroup.contains( strAppCode)) {
            return true;

        } else {
            return false;
        }

    }

    // 获取浏览器的 cookie
    // @param cookieName， Cookie名.
    // @return Cokie的值
    private String getCookieValue( HttpServletRequest request, String cookieName) {

        try {

            Cookie[] cookies = request.getCookies();
            String strCookie = request.getHeader( "Cookie");

            // 如果cookie值里包含=号，那么采用request.getCookies()时，会取不完整。需要特殊处理
            String strCookieValue = null;
            if ( strCookie != null && strCookie.contains( ";")) {
                String[] cookieArray = strCookie.split( ";");
                for ( String cookieTemp : cookieArray) {
                    if ( cookieTemp.indexOf( cookieName + "=") > 0) {
                        strCookieValue = cookieTemp.substring( cookieTemp.indexOf( cookieName + "=") + ( cookieName.length() + 1), cookieTemp.length());
                        break;
                    }
                }
            }

            if ( cookies != null && strCookieValue == null) {
                for ( int iCookieCounter = 0; iCookieCounter < cookies.length; iCookieCounter++) {
                    if ( cookies[iCookieCounter].getName().toLowerCase().equals( cookieName.toLowerCase())) {
                        strCookieValue = cookies[iCookieCounter].getValue();
                        break;
                    }
                }
            }

            return strCookieValue;

        } catch ( Exception e) {
            return null;
        }

    }

    //获取登录页面
    public String getLoginURL( HttpServletRequest http) {

        String result;

        String strLoginUrl = strLoginContext;

        if ( strLoginUrl.indexOf( "?") > 0) {
            result = strLoginUrl + "&RedirectToSSO=yes&RedirectTo=" + getPageURL( http);
        } else {
            result = strLoginUrl + "?RedirectToSSO=yes&RedirectTo=" + getPageURL( http);
        }

        logMessage( "getLoginURL=" + result);
        return result;
    }

    //获取登录后返回地址
    private String getPageURL( HttpServletRequest http) {

        StringBuffer queryString = http.getRequestURL();
        String str = queryString.toString();
        String str2 = http.getQueryString();
        if ( str2 != null) {
            str += "?" + str2;
        }

        return getEncodeUTF8( str);

    }

    //转码
    private String getEncodeUTF8( String pageURL) {

        String result;

        try {
            result = URLEncoder.encode( pageURL, "UTF-8");
        } catch ( UnsupportedEncodingException e) {
            throw new RuntimeException( "UTF-8 not supported", e);
        }

        return result;
    }

    //打印调试信息
    public void logMessage( String message) {

    }

    public String getStrLocalLoginPath() {

        return strLocalLoginPath;
    }

    public String getStrFobiddenContext() {

        return strFobiddenContext;
    }

    public String getStrSSOServerError() {

        return strSSOServerError;
    }

    public String getStrTimeOut() {

        return strTimeOut;
    }

}
