package com.luckyun.tjam.mtAssetMag.amAssetEnable.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableDtParam;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.service.AmAssetEnableDtService;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmAssetEnableDtController {

    @Autowired
    private AmAssetEnableDtService amAssetEnableDtService;

    @Api(name = "查询全部", author = "cy")
    @PostMapping("readAll")
    @Pageable
    public List<AmAssetEnableDt> findAll(@Param("设备设施资产参数") AmAssetEnableDtParam condition) {
        return this.amAssetEnableDtService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetEnableDt findOne(@Param("indocno") Long indocno) {
        return this.amAssetEnableDtService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetEnableDtParam entity) {
        this.amAssetEnableDtService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetEnableDtParam condition) {
        this.amAssetEnableDtService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmAssetEnableDtParam condition) {
         amAssetEnableDtService.add(condition);
    }
}
