package com.luckyun.tjam.mtAssetMag.asledag.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicManagementInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicManagementInfParam;
import com.luckyun.tjam.mtAssetMag.asledag.service.AmBasicManagementInfService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(name = "资产卡片控制层", author = "cy")
@UrlByPkgController
public class AmBasicManagementInfController {

    @Autowired
    private AmBasicManagementInfService amBasicManagementInfService;

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmBasicManagementInfParam entity) {
        this.amBasicManagementInfService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmBasicManagementInfParam condition) {
        this.amBasicManagementInfService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmBasicManagementInf condition){
         amBasicManagementInfService.add(condition);
    }
}
