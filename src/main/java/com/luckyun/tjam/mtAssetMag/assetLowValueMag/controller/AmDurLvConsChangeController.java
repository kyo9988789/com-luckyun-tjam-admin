package com.luckyun.tjam.mtAssetMag.assetLowValueMag.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChange;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeDt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeParam;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.service.AmDurLvConsChangeService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsChangeController {

    @Autowired
    private AmDurLvConsChangeService amDurLvConsChangeService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmDurLvConsChange> findAll(@Param("设备设施资产参数") AmDurLvConsChangeParam condition) {
        return this.amDurLvConsChangeService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmDurLvConsChange findOne(@Param("indocno") Long indocno) {
        return this.amDurLvConsChangeService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmDurLvConsChangeDt> findOnes(@Param("indocno") Long indocno) {
        return amDurLvConsChangeService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmDurLvConsChangeAtt> findOneas(@Param("indocno") Long indocno) {
        return amDurLvConsChangeService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsChange entity) {
        this.amDurLvConsChangeService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsChangeParam condition) {
        this.amDurLvConsChangeService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmDurLvConsChange add(@Param("新增数据") @RequestBody AmDurLvConsChange condition){
        return amDurLvConsChangeService.add(condition);
    }

}
