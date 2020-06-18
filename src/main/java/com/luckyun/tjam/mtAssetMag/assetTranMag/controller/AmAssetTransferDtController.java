package com.luckyun.tjam.mtAssetMag.assetTranMag.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferDtParam;
import com.luckyun.tjam.mtAssetMag.assetTranMag.service.AmAssetTransferDtService;

@Api(name = "控制层", author = "cy")
@UrlByPkgController
public class AmAssetTransferDtController {

    @Autowired
    private AmAssetTransferDtService amAssetTransferDtService;


    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetTransferDtParam entity) {
        this.amAssetTransferDtService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetTransferDtParam condition) {
        this.amAssetTransferDtService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmAssetTransferDtParam condition){
         amAssetTransferDtService.add(condition);
    }
}
