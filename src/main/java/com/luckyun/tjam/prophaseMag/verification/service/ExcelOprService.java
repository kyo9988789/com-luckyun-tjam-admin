/*
package com.luckyun.tjam.prophaseMag.verification.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;

@Configuration
@Service
public class ExcelOprService {

    private static String gateWayUrl;
    private String url = "/qsmis/material/rawMaterialRegister/noGetway?tab=unit&pageno=1&pagesize=10";
    private static final String labels = "样品编号,报告编号,检测结果,检测日期";
    private static final String keys = "";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        ExcelOprService.gateWayUrl = gateWayUrl;
    }

    */
/**
     * 导出excel
     * @param gateWayUrl 网关地址
     * @param url 数据服务的地址,接口的返回对象需要是apiresult
     * @param labels excel第一行显示的表头 ,例如 : 姓名,年纪,公司部门
     * @param keys 返回数据源对象的key值,对应上面label顺序,例如: name,age,company
     * @param params 接口参数,一般是接口的条件参数,也可以在url后面跟着.
     * @param excelName 下载的excel名称
     * @param sheetName 存在excel中的sheet名称
     * @return excel的状态轮询地址,Object对象.非string对象
     *//*

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JSONObject exportData(Map map) {
        url = url + "&inodeid=99999";
        String docNm = "检测结果模板";
        JSONObject jsonObject = GenerateExcelHelper.createExcel(gateWayUrl, gateWayUrl + url, labels, keys, map, docNm, "sheet1");
        return jsonObject;
    }

}
*/
