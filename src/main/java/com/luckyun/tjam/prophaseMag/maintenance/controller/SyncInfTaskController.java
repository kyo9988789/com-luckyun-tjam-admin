
package com.luckyun.tjam.prophaseMag.maintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.maintenance.siatask.InfTaskService;


/**
 * Created by whs on 2019/12/18.
 */

@UrlByPkgController
@Api(name = "定时更新业务表", author = "whs")
public class SyncInfTaskController {
    @Autowired
    private InfTaskService infTaskService;

    @PostMapping("syncCapAcceptance")
    @Api(name = "同步业务数据", author = "whs")
    public void syncCapAcceptance(){
        infTaskService.compareTo();
    }

    @PostMapping("test")
    public String sync(){
        return "+++++++++++++测试001++++++++++++";
    }

}

