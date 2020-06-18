package com.luckyun.tjam.mtAssetMag.amAssetFree.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeDtParam;
import com.luckyun.tjam.mtAssetMag.amAssetFree.service.AmAssetFreeDtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(name = "控制层", author = "cy")
@UrlByPkgController
public class AmAssetFreeDtController {

    @Autowired
    private AmAssetFreeDtService amAssetFreeDtService;

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetFreeDt findOne(@Param("indocno") Long indocno) {
        return this.amAssetFreeDtService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetFreeDtParam entity) {
        this.amAssetFreeDtService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetFreeDtParam condition) {
        this.amAssetFreeDtService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmAssetFreeDtParam condition){
         amAssetFreeDtService.add(condition);
    }
}
