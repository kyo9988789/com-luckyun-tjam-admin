package com.luckyun.tjam.prophaseMag.verification.controller;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.model.AmWaitAppmt;
import com.luckyun.tjam.prophaseMag.verification.param.AmWaitAppmtParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmWaitAppmtService;


@Api(name = "待分摊项维护模块控制层", author = "cy")
@UrlByPkgController
public class AmWaitAppmtController {

    @Autowired
    private AmWaitAppmtService amWaitAppmtService;

    @Api(name = "查询全部待分摊项维护", author = "cy")
    @PostMapping("readAll")
    @Pageable
    public List<AmWaitAppmt> findAll(@Param("设备设施资产参数") AmWaitAppmtParam condition){
        return amWaitAppmtService.findAll(condition);
    }
    
    @Api(name = "查询未被资产价值分摊选中的待分摊项", author = "cy")
    @PostMapping("readAllHasNotChoiced")
    @Pageable
    public List<AmWaitAppmt> readAllHasNotChoiced(){
        return amWaitAppmtService.readAllHasNotChoiced();
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmWaitAppmtParam condition) {
        this.amWaitAppmtService.delOpr(condition);
    }

    @Api(name = "查询单条设备设施核验计划", author = "cy")
    @GetMapping("readOne")
    public AmWaitAppmt findOne(@Param("indocno") Long indocno){
        return amWaitAppmtService.findOne(indocno);
    }

    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmWaitAppmt condition){
        amWaitAppmtService.add(condition);
    }

    @Api(name = "修改", author = "cy")
    @PostMapping("update")
    public void update(@Param("修改数据") @RequestBody AmWaitAppmt condition){
        amWaitAppmtService.updateAm(condition);
    }
}
