package com.luckyun.tjam.mtAssetMag.assetTranMag.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransfer;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferDt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferParam;
import com.luckyun.tjam.mtAssetMag.assetTranMag.service.AmAssetTransferService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "资产转移控制层", author = "cy")
@UrlByPkgController
public class AmAssetTransferController {

    @Autowired
    private AmAssetTransferService amAssetTransferService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmAssetTransfer> findAll(@Param("设备设施资产参数") AmAssetTransferParam condition) {
        return this.amAssetTransferService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetTransfer findOne(@Param("indocno") Long indocno) {
        return this.amAssetTransferService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmAssetTransferDt> findOnes(@Param("indocno") Long indocno) {
        return amAssetTransferService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmAssetTransferAtt> findOneas(@Param("indocno") Long indocno) {
        return amAssetTransferService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetTransfer entity) {
        this.amAssetTransferService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetTransferParam condition) {
        this.amAssetTransferService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetTransfer add(@Param("新增数据") @RequestBody AmAssetTransfer condition){
        return amAssetTransferService.add(condition);
    }
}
