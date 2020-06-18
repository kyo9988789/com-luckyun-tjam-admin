package com.luckyun.tjam.mtAssetMag.amAssetLeave.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeave;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveAtt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveParam;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.service.AmAssetLeaveService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "资产转移控制层", author = "cy")
@UrlByPkgController
public class AmAssetLeaveController {

    @Autowired
    private AmAssetLeaveService amAssetLeaveService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmAssetLeave> findAll(@Param("设备设施资产参数") AmAssetLeaveParam condition) {
        return this.amAssetLeaveService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetLeave findOne(@Param("indocno") Long indocno) {
        return this.amAssetLeaveService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmAssetLeaveDt> findOnes(@Param("indocno") Long indocno) {
        return amAssetLeaveService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmAssetLeaveAtt> findOneas(@Param("indocno") Long indocno) {
        return amAssetLeaveService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetLeave entity) {
        this.amAssetLeaveService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetLeaveParam condition) {
        this.amAssetLeaveService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetLeave add(@Param("新增数据") @RequestBody AmAssetLeave condition){
        return amAssetLeaveService.add(condition);
    }
}
