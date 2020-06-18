package com.luckyun.tjam.sso.ssologin.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luckyun.auth.provider.feign.AuthSysModuleProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.response.ApiResult;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.sso.ssologin.service.SSOLoginService;

@UrlByPkgController
public class SsoLoginController {

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private AuthSysModuleProvider authSysModuleProvider;


    @GetMapping("noGetway")
    public ApiResult login(String token){
        SSOLoginService s = new SSOLoginService();
        String tempUserInfo1 = s.verSSOToken(token);
        System.out.println("----------看tempUserInfo1="+tempUserInfo1);
       JSONObject objReturn = JSON.parseObject( tempUserInfo1);
        System.out.println("----------看objReturn="+objReturn);
     String sloginid = objReturn.getString("userid");
        System.out.println("----------看sloginid="+sloginid);
        SysAccount suserid1 = baseSysUserProvider.findAccoutContainerPwd(sloginid, null);
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

//    @RequestMapping("noGetway")
//    public ApiResult sso(String sloginid){
//
//        SysAccount suserid1 = baseSysUserProvider.findAccoutContainerPwd(sloginid, null);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username",suserid1.getSloginid());
//        map.put("password",suserid1.getSpwd());
//        JSONObject jsonObject = authSysModuleProvider.singleLogin(map);
//        ApiResult apiResult = JSONObject.parseObject(jsonObject.toJSONString(),ApiResult.class);
//        return apiResult;
//    }




}
