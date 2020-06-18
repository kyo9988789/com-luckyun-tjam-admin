package com.luckyun.tjam.mtAssetMag.assetTranMag.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferAttParam;
import com.luckyun.tjam.mtAssetMag.assetTranMag.service.AmAssetTransferAttService;

@Api(name = "附件制层", author = "cy")
@UrlByPkgController
public class AmAssetTransferAttController {

    @Autowired
    private AmAssetTransferAttService amAssetTransferAttService;


    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetTransferAtt entity) {
        this.amAssetTransferAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetTransferAttParam condition) {
        this.amAssetTransferAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetTransferAtt add(@Param("新增数据") @RequestBody AmAssetTransferAtt condition){
        return amAssetTransferAttService.add(condition);
    }
}
