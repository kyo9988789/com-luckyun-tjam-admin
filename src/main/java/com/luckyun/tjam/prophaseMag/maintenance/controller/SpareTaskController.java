
package com.luckyun.tjam.prophaseMag.maintenance.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.maintenance.siatask.SpareTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Created by whs on 2019/12/18.
 */

@UrlByPkgController
@Api(name = "定时更新业务表", author = "whs")
public class SpareTaskController {

    @Autowired
    private SpareTaskService spareTaskService;

    @PostMapping("syncCapSpares")
    @Api(name = "同步业务数据", author = "whs")
    public void syncCapAcceptance(){
        spareTaskService.SparesCompareTo();
    }

    @PostMapping("test")
    public String sync(){
        return "++++++++++测试++++++++++++";
    }
}

