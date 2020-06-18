package com.luckyun.tjam.prophaseMag.verification.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtProParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmWorthAppmtProService;

@Api(name = "资产价值分摊项信息控制层", author = "cy")
@UrlByPkgController
public class AmWorthAppmtProController {

    @Autowired
    private AmWorthAppmtProService amWorthAppmtProService;
    
    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmWorthAppmtProParam condition) {
        this.amWorthAppmtProService.del(condition);
    }

    @Api(name = "修改分摊方式",author = "cy")
    @PostMapping("update")
    public AmWorthAppmtPro updateIappmttype(@Param("修改数据") @RequestBody AmWorthAppmtProParam condition){
        return amWorthAppmtProService.updateIappmttype(condition);
    }

    @Api(name = "新增子表明细", author = "cy")
    @PostMapping("add")
    public void addAwaPro(@Param("新增数据") @RequestBody AmWorthAppmtBatch entity){
        amWorthAppmtProService.addAwaPro(entity);
    }
}
