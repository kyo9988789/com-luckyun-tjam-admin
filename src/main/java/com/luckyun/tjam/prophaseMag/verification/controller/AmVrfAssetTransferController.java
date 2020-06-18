package com.luckyun.tjam.prophaseMag.verification.controller;


import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfAssetTransferService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UrlByPkgController
public class AmVrfAssetTransferController {

    @Autowired
    private AmVrfAssetTransferService amvrfAssetTransferService;

    @Api(name = "实物资产核验计划推送运营", author = "cy")
    @PostMapping("transfer")
    public Integer amTransfer( @Param("实物核验计划数据") @RequestBody AmVrfPlanParam condition){
        return amvrfAssetTransferService.amvrfAssetTransfer(condition);
    }

    @Api(name = "备品备件核验计划推送运营", author = "cy")
    @PostMapping("transferspare")
    public Integer transferspare(@Param("备品核验计划") @RequestBody AmVrfPlanParam condition){
        return amvrfAssetTransferService.amvrfAssetTransferSpare(condition);
    }

}
