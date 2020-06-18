package com.luckyun.tjam.mtAssetMag.amAcceSpare.controller;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpare;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.param.AmSpareParam;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.service.AmSpareService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "备品备件台账控制层", author = "cy")
@UrlByPkgController
public class AmSpareController {

    @Autowired
    private AmSpareService amSpareService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmSpare> findAll(@Param("设备设施资产参数") AmSpareParam condition) {
        return this.amSpareService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmSpare findOne(@Param("indocno") Long indocno) {
        return this.amSpareService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmSpare entity) {
        this.amSpareService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmSpareParam condition) {
        this.amSpareService.delOpr(condition);
    }

    @Api(name = "备品备件导出", author = "cy")
    @GetMapping("readAllForExport")
    @Pageable
    public List<AmSpare> findAllForExport(@Param("设备设施资产参数") AmSpareParam condition) {
        return this.amSpareService.findAll(condition);
    }

    @Api(name = "备品备件台账导入",author = "cy")
    @PostMapping("import")
    public ExcelBatchSender importSendCheck(@RequestBody ExcelBatchReceiver<AmSpare> entity){
        return amSpareService.importSendCheck(entity);
    }
}
