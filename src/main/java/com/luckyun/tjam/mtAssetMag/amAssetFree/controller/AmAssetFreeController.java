package com.luckyun.tjam.mtAssetMag.amAssetFree.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFree;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeAtt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeParam;
import com.luckyun.tjam.mtAssetMag.amAssetFree.service.AmAssetFreeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "资产转移控制层", author = "cy")
@UrlByPkgController
public class AmAssetFreeController {

    @Autowired
    private AmAssetFreeService amAssetFreeService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmAssetFree> findAll(@Param("设备设施资产参数") AmAssetFreeParam condition) {
        return this.amAssetFreeService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetFree findOne(@Param("indocno") Long indocno) {
        return this.amAssetFreeService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmAssetFreeDt> findOnes(@Param("indocno") Long indocno) {
        return amAssetFreeService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmAssetFreeAtt> findOneas(@Param("indocno") Long indocno) {
        return amAssetFreeService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetFree entity) {
        this.amAssetFreeService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetFreeParam condition) {
        this.amAssetFreeService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetFree add(@Param("新增数据") @RequestBody AmAssetFree condition){
        return amAssetFreeService.add(condition);
    }
}
