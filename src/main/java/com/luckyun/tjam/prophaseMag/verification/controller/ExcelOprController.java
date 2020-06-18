/*
package com.luckyun.tjam.prophaseMag.verification.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.service.ExcelOprService;


@UrlByPkgController
public class ExcelOprController {

    @Autowired
    private ExcelOprService excelOprService;

    @SuppressWarnings("rawtypes")
    @Api(name = "导出原材料登记信息", author = "zrw")
    @Pageable
    @RequestMapping("exportData")
    public JSONObject exportData(@RequestBody Map map){
        JSONObject jsonObject = this.excelOprService.exportData(map);
        return jsonObject;
    }

}
*/
