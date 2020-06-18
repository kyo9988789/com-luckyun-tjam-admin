package com.luckyun.tjam.mtAssetMag.amTranPlan.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanDtParam;
import com.luckyun.tjam.mtAssetMag.amTranPlan.service.AmTransferPlanDtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(name = "计划下发明细控制层", author = "cy")
@UrlByPkgController
public class AmTransferPlanDtController {

    @Autowired
    private AmTransferPlanDtService amTransferPlanDtService;

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmTransferPlanDt findOne(@Param("indocno") Long indocno) {
        return this.amTransferPlanDtService.findOne(indocno);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划明细数据") @RequestBody AmTransferPlanDtParam condition) {
         this.amTransferPlanDtService.delOpr(condition);
    }

    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmTransferPlanDtParam condition){
          amTransferPlanDtService.add(condition);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmTransferPlanDt entity) {
        this.amTransferPlanDtService.updateBasic(entity);
    }
}
