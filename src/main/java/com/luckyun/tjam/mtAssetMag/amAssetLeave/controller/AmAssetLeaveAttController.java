package com.luckyun.tjam.mtAssetMag.amAssetLeave.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveAtt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveAttParam;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.service.AmAssetLeaveAttService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UrlByPkgController
public class AmAssetLeaveAttController {

    @Autowired
    private AmAssetLeaveAttService amAssetLeaveAttService;

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmAssetLeaveAtt findOne(@Param("indocno") Long indocno) {
        return this.amAssetLeaveAttService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmAssetLeaveAtt entity) {
        this.amAssetLeaveAttService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmAssetLeaveAttParam condition) {
        this.amAssetLeaveAttService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmAssetLeaveAtt add(@Param("新增数据") @RequestBody AmAssetLeaveAtt condition){
        return amAssetLeaveAttService.add(condition);
    }
}
