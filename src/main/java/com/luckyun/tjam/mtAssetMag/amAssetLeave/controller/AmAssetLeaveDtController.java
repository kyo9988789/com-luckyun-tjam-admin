package com.luckyun.tjam.mtAssetMag.amAssetLeave.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveDtParam;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.service.AmAssetLeaveDtService;

@UrlByPkgController
public class AmAssetLeaveDtController {

    @Autowired
    private AmAssetLeaveDtService amAssetLeaveDtService;

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetLeaveDt findOne(@Param("indocno") Long indocno) {
        return this.amAssetLeaveDtService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetLeaveDtParam entity) {
        this.amAssetLeaveDtService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetLeaveDtParam condition) {
        this.amAssetLeaveDtService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmAssetLeaveDtParam condition){
         amAssetLeaveDtService.add(condition);
    }

}
