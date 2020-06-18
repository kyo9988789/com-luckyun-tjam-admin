package com.luckyun.tjam.mtAssetMag.mtExcelUtil.controller;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.param.AmSpareParam;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.param.AmDurLvConsumableParam;
import com.luckyun.tjam.mtAssetMag.amToolsag.param.AmToolsParam;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanParam;
import com.luckyun.tjam.mtAssetMag.mtExcelUtil.service.AmDurLvOprService;
import com.luckyun.tjam.mtAssetMag.mtExcelUtil.service.AmExcelOprService;
import com.luckyun.tjam.mtAssetMag.mtExcelUtil.service.AmPlanExcelOprService;
import com.luckyun.tjam.mtAssetMag.mtExcelUtil.service.AmToolsExcelOprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cy on 2020/4/10.
 */

@UrlByPkgController
public class AmExcelOprController {

    @Autowired
    private AmExcelOprService amExcelOprService;

    @Autowired
    private AmToolsExcelOprService amToolsExcelOprService;

    @Autowired
    private AmDurLvOprService amDurLvOprService;
    
    @Autowired
    private AmPlanExcelOprService amPlanExcelOprService;

    //备品备件台账导出
    @RequestMapping("exportExcel")
    public JSONObject exportData(@RequestBody AmSpareParam map){
        JSONObject jsonObject = amExcelOprService.exportData(map);
        return jsonObject;
    }

    //工器具台账导出
    @RequestMapping("toolsExportExcel")
    public JSONObject toolsExportData(@RequestBody AmToolsParam map){
        JSONObject jsonObject = amToolsExcelOprService.exportData(map);
        return jsonObject;
    }

    //耐用低值导出
    @RequestMapping("amDurLvExportExcel")
    public JSONObject amDurLvExportExcel(@RequestBody AmDurLvConsumableParam map){
        JSONObject jsonObject = amDurLvOprService.exportData(map);
        return  jsonObject;
    }

    //计划下发导出
    @RequestMapping("amPlanExportExcel")
    public JSONObject amPlanExportExcel(@RequestBody AmTransferPlanParam map){
        JSONObject jsonObject = amPlanExcelOprService.exportData(map);
        return jsonObject;
    }

}
