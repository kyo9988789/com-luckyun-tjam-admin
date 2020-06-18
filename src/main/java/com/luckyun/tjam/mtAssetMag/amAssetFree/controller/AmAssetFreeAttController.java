package com.luckyun.tjam.mtAssetMag.amAssetFree.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeAtt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeAttParam;
import com.luckyun.tjam.mtAssetMag.amAssetFree.service.AmAssetFreeAttService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(name = "附件制层", author = "cy")
@UrlByPkgController
public class AmAssetFreeAttController {

    @Autowired
    private AmAssetFreeAttService amAssetFreeAttService;

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetFreeAtt findOne(@Param("indocno") Long indocno) {
        return this.amAssetFreeAttService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetFreeAtt entity) {
        this.amAssetFreeAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetFreeAttParam condition) {
        this.amAssetFreeAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetFreeAtt add(@Param("新增数据") @RequestBody AmAssetFreeAtt condition){
        return amAssetFreeAttService.add(condition);
    }
}
