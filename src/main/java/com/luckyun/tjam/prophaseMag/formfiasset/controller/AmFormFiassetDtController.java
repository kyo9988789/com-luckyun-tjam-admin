package com.luckyun.tjam.prophaseMag.formfiasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetDtParam;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetDtService;

/**
 * Created by whs on 2020/5/25.
 */
@UrlByPkgController
public class AmFormFiassetDtController {

    @Autowired
    private AmFormFiassetDtService amFormFiassetDtService;

    @PostMapping("add")
    public void add(@RequestBody AmFormFiassetDtParam condition){
        amFormFiassetDtService.handle(condition);
    }

    @PostMapping("delete")
    public void delete(@RequestBody AmFormFiassetDtParam condition){
        amFormFiassetDtService.handle(condition);
    }

    @PostMapping("update")
    public void update(@RequestBody AmFormFiassetDtParam condition){
        amFormFiassetDtService.handle(condition);
    }

}
