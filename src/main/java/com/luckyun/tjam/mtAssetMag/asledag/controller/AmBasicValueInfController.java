package com.luckyun.tjam.mtAssetMag.asledag.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicValueInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicValueInfParam;
import com.luckyun.tjam.mtAssetMag.asledag.service.AmBasicValueInfService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(name = "资产卡片控制层", author = "cy")
@UrlByPkgController
public class AmBasicValueInfController {

    @Autowired
    private AmBasicValueInfService amBasicValueInfService;

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmBasicValueInfParam entity) {
        this.amBasicValueInfService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmBasicValueInfParam condition) {
        this.amBasicValueInfService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmBasicValueInf condition){
         amBasicValueInfService.add(condition);
    }
}
