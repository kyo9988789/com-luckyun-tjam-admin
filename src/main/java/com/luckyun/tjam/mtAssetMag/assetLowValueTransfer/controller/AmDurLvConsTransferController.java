package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransfer;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferDt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferParam;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.service.AmDurLvConsTransferService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsTransferController {

    @Autowired
    private AmDurLvConsTransferService amDurLvConsTransferService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmDurLvConsTransfer> findAll(@Param("设备设施资产参数") AmDurLvConsTransferParam condition) {
        return this.amDurLvConsTransferService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmDurLvConsTransfer findOne(@Param("indocno") Long indocno) {
        return this.amDurLvConsTransferService.findOne(indocno);
    }

    @Api(name = "查询单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnes")
    public List<AmDurLvConsTransferDt> findOnes(@Param("indocno") Long indocno) {
        return amDurLvConsTransferService.findOnes(indocno);
    }

    @Api(name = "查询附件单条明细(所有的是集合)", author = "cy")
    @GetMapping("readOnas")
    public List<AmDurLvConsTransferAtt> findOneas(@Param("indocno") Long indocno) {
        return amDurLvConsTransferService.findOneas(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsTransfer entity) {
        this.amDurLvConsTransferService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsTransferParam condition) {
        this.amDurLvConsTransferService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmDurLvConsTransfer add(@Param("新增数据") @RequestBody AmDurLvConsTransfer condition){
        return amDurLvConsTransferService.add(condition);
    }

}
