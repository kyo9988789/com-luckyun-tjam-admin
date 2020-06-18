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
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnable;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableAtt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableParam;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.service.AmAssetEnableService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "计划下发控制层", author = "cy")
@UrlByPkgController
public class AmAssetEnableController {

    @Autowired
    private AmAssetEnableService amAssetEnableService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmAssetEnable> findAll(@Param("设备设施资产参数") AmAssetEnableParam condition) {
        return this.amAssetEnableService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetEnable findOne(@Param("indocno") Long indocno) {
        return this.amAssetEnableService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmAssetEnableDt> findOnes(@Param("indocno") Long indocno) {
        return amAssetEnableService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmAssetEnableAtt> findOneas(@Param("indocno") Long indocno) {
        return amAssetEnableService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetEnable entity) {
        this.amAssetEnableService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetEnableParam condition) {
        this.amAssetEnableService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetEnable add(@Param("新增数据") @RequestBody AmAssetEnable condition){
        return amAssetEnableService.add(condition);
    }
}
