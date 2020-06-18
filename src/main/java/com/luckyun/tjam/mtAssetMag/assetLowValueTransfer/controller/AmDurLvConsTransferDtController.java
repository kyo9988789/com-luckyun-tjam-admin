package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferDtParam;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.service.AmDurLvConsTransferDtService;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsTransferDtController {

    @Autowired
    private AmDurLvConsTransferDtService amDurLvConsTransferDtService;


    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsTransferDtParam entity) {
        this.amDurLvConsTransferDtService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsTransferDtParam condition) {
        this.amDurLvConsTransferDtService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmDurLvConsTransferDtParam condition){
         amDurLvConsTransferDtService.add(condition);
    }
}
