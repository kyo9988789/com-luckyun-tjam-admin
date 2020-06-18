package com.luckyun.tjam.mtAssetMag.amTranPlan.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlan;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanParam;
import com.luckyun.tjam.mtAssetMag.amTranPlan.service.AmTransferPlanDtCompService;
import com.luckyun.tjam.mtAssetMag.amTranPlan.service.AmTransferPlanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "计划下发控制层", author = "cy")
@UrlByPkgController
public class AmTransferPlanController {

    @Autowired
    private AmTransferPlanService amTransferPlanService;

    @Autowired
    private AmTransferPlanDtCompService amTransferPlanDtCompService;


    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmTransferPlan> findAll(@Param("设备设施资产参数") AmTransferPlanParam condition) {
        return this.amTransferPlanService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmTransferPlan findOne(@Param("indocno") Long indocno) {
        return this.amTransferPlanService.findOne(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnes")
    public List<AmTransferPlanDt> findOnes(@Param("indocno") Long indocno) {
        return amTransferPlanService.findOnes(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmTransferPlan entity) {
        this.amTransferPlanService.updateBasic(entity);
    }

    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmTransferPlan add(@Param("新增数据") @RequestBody AmTransferPlan condition){
        return amTransferPlanService.add(condition);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmTransferPlanParam condition) {
        this.amTransferPlanService.delOpr(condition);
    }

    @Api(name = "工器具台账导出", author = "whs")
    @GetMapping("readAllForExport")
    @Pageable
    public List<AmTransferPlan> findAllForExport(@Param("设备设施资产参数") AmTransferPlanParam condition) {
        return this.amTransferPlanService.findAll(condition);
    }

    @GetMapping("readAllDtCompByIlinkno")
    public List<AmTransferPlanDtComp> readAllDtCompByIlinkno(String sfcode){
        return amTransferPlanDtCompService.findAllByIlinkno(sfcode);
    }
}
