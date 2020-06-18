package com.luckyun.tjam.prophaseMag.excelUtil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.tjam.prophaseMag.excelUtil.service.ExcelOprService;
import com.luckyun.tjam.prophaseMag.excelUtil.service.ExcelSpareOprService;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceFiassetParam;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceSpareParam;

/**
 * Created by whs on 2020/4/2.
 */

@UrlByPkgController
public class ExcelOprController {

    @Autowired
    private ExcelOprService excelOprService;

    @Autowired
    private ExcelSpareOprService excelSpareOprService;

    //实物资产管理员版导出
    @RequestMapping("exportExcel")
    public JSONObject exportData(@RequestBody AmAcceptanceFiassetParam map){
        JSONObject jsonObject = excelOprService.exportData(map);
        return jsonObject;
    }

    //实物备品备件导出
    @RequestMapping("exportSpareExcel")
    public JSONObject exportReponsibleExcel(@RequestBody AmAcceptanceSpareParam map){
        JSONObject jsonObject = excelSpareOprService.exportData(map);
        return jsonObject;
    }


}
