package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferAttParam;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.service.AmDurLvConsTransferAttService;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsTransferAttController {

    @Autowired
    private AmDurLvConsTransferAttService amDurLvConsTransferAttService;


    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsTransferAtt entity) {
        this.amDurLvConsTransferAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsTransferAttParam condition) {
        this.amDurLvConsTransferAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmDurLvConsTransferAtt add(@Param("新增数据") @RequestBody AmDurLvConsTransferAtt condition){
        return amDurLvConsTransferAttService.add(condition);
    }
}
