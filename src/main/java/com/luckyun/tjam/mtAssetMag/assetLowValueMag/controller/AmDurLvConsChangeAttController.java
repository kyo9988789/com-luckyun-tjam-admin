package com.luckyun.tjam.mtAssetMag.assetLowValueMag.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeAttParam;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.service.AmDurLvConsChangeAttService;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsChangeAttController {

    @Autowired
    private AmDurLvConsChangeAttService amDurLvConsChangeAttService;


    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsChangeAtt entity) {
        this.amDurLvConsChangeAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsChangeAttParam condition) {
        this.amDurLvConsChangeAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmDurLvConsChangeAtt add(@Param("新增数据") @RequestBody AmDurLvConsChangeAtt condition){
        return amDurLvConsChangeAttService.add(condition);
    }

}
