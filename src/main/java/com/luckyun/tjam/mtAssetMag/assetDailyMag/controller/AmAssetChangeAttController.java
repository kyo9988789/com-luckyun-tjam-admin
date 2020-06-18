package com.luckyun.tjam.mtAssetMag.assetDailyMag.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeAttParam;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.service.AmAssetChangeAttService;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmAssetChangeAttController {

    @Autowired
    private AmAssetChangeAttService amAssetChangeAttService;

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetChangeAtt entity) {
        this.amAssetChangeAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetChangeAttParam condition) {
        this.amAssetChangeAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetChangeAtt add(@Param("新增数据") @RequestBody AmAssetChangeAtt condition){
        return amAssetChangeAttService.add(condition);
    }
}
