package com.luckyun.tjam.sso.ssologin.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luckyun.auth.provider.feign.AuthSysModuleProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.response.ApiResult;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.sso.ssologin.service.SSOLoginService;

@UrlByPkgController
public class TestSsoController {

    @Autowired
    private AuthSysModuleProvider authSysModuleProvider;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @RequestMapping("noGetway")
    public ApiResult sso( String token){
        System.out.println("----------token="+token);
        SSOLoginService s = new SSOLoginService();
        String tempUserInfo1 = s.verSSOToken(token);
        System.out.println("----------看tempUserInfo1="+tempUserInfo1);
        JSONObject objReturn = JSON.parseObject( tempUserInfo1);
        System.out.println("----------看objReturn="+objReturn);
        String userid = objReturn.getString("userid");
        System.out.println("----------看userid="+userid);
        SysAccount suserid1 = baseSysUserProvider.findAccoutContainerPwd(userid, null);
        System.out.println("----------看suserid1="+suserid1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",suserid1.getSloginid());
        map.put("password",suserid1.getSpwd());
        JSONObject jsonObject = authSysModuleProvider.singleLogin(map);
        System.out.println("----------看jsonObject="+jsonObject);
        ApiResult apiResult = JSONObject.parseObject(jsonObject.toJSONString(),ApiResult.class);
        System.out.println("----------看apiResult="+apiResult);
        return apiResult;
    }

//    @RequestMapping("ssoLogin")
//    public String ssoLogin(String token){
//        FilterConfig filterConfig = null;
//       SSOLoginService s = new SSOLoginService();
//       String tempUserInfo1 = s.verSSOToken(token);
//        System.out.println(tempUserInfo1);
////        JSONObject objReturn = JSON.parseObject( tempUserInfo1);
//      String sloginid = objReturn.getString("userid");
//        //ApiResult sloginid = JSONObject.parseObject(tempUserInfo1.toJSONString(),ApiResult.class);
//       return tempUserInfo1;
//    }

}
