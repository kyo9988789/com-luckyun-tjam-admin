package com.luckyun.tjam.mtAssetMag.assetDailyMag.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChange;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeParam;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.service.AmAssetChangeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmAssetChangeController {

    @Autowired
    private AmAssetChangeService amAssetChangeService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping ("readAll")
    @Pageable
    public List<AmAssetChange> findAll(@Param("设备设施资产参数") AmAssetChangeParam condition) {
        return this.amAssetChangeService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetChange findOne(@Param("indocno") Long indocno) {
        return this.amAssetChangeService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmAssetChangeDt> findOnes(@Param("indocno") Long indocno) {
        return amAssetChangeService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmAssetChangeAtt> findOneas(@Param("indocno") Long indocno) {
        return amAssetChangeService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetChange entity) {
        this.amAssetChangeService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetChangeParam condition) {
        this.amAssetChangeService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetChange add(@Param("新增数据") @RequestBody AmAssetChange condition){
        return amAssetChangeService.add(condition);
    }
}
