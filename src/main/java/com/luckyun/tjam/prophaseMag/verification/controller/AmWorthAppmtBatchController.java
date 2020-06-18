package com.luckyun.tjam.prophaseMag.verification.controller;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtFiasset;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtBatchParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmWorthAppmtBatchService;
import com.luckyun.tjam.prophaseMag.verification.service.AmWorthAppmtFiassetService;
import com.luckyun.tjam.prophaseMag.verification.service.AmWorthAppmtProService;

@Api(name = "资产价值分摊模块控制层", author = "cy")
@UrlByPkgController
public class AmWorthAppmtBatchController {

    @Autowired
    private AmWorthAppmtBatchService amWorthAppmtBatchService;
    
    @Autowired
    private AmWorthAppmtFiassetService amWorthAppmtFiassetService;
    
    @Autowired
    private AmWorthAppmtProService amWorthAppmtProService;

    @Api(name = "查询全部资产价值分摊项", author = "cy")
    @PostMapping("readAll")
    @Pageable
    public List<AmWorthAppmtBatch> findAll(@Param("参数")AmWorthAppmtBatchParam condition){
        return amWorthAppmtBatchService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmWorthAppmtBatch findOne(@Param("indocno") Long indocno){
        return amWorthAppmtBatchService.findOne(indocno);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@org.apache.ibatis.annotations.Param("核验计划主数据") @RequestBody AmWorthAppmtBatchParam condition) {
        this.amWorthAppmtBatchService.del(condition);
    }

    @Api(name = "修改", author = "cy")
    @PostMapping("update")
    public AmWorthAppmtBatch update(@Param("修改数据") @RequestBody AmWorthAppmtBatch condition){
        return amWorthAppmtBatchService.updateAm(condition);
    }

    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmWorthAppmtBatch add(@Param("新增数据") @RequestBody AmWorthAppmtBatch condition){
        return amWorthAppmtBatchService.add(condition);
    }

    @Api(name="分摊计算",author = "cy")
    @RequestMapping("compute")
    public AmWorthAppmtBatch compute(@Param("参数")@RequestBody AmWorthAppmtBatch condition){
        return amWorthAppmtBatchService.compute(condition);
    }

    @Api(name = "通过主表查询待分摊项", author = "cy")
    @GetMapping("readAllFiassetByIlinkno")
    public List<AmWorthAppmtFiasset> readAllFiassetByIlinkno(@Param("ilinkno") Long ilinkno){
        return amWorthAppmtFiassetService.readAllByIlinkno(ilinkno);
    }
    
    @Api(name = "查询主表查询全部待分摊项", author = "cy")
    @GetMapping("readAllProByIlinkno")
    public List<AmWorthAppmtPro> readAllProByIlinkno(@Param("ilinkno") Long ilinkno){
       return amWorthAppmtProService.readAllByIlinkno(ilinkno);
    }
    
}
