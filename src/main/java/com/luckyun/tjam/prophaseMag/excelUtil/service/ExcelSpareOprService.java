package com.luckyun.tjam.prophaseMag.excelUtil.service;

import java.util.HashMap;

import com.luckyun.report.entity.ExcelObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;
import com.luckyun.tjam.prophaseMag.excelUtil.util.Util;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceSpareParam;

/**
 * Created by Administrator on 2020/4/10.
 */
@Configuration
@Service
public class ExcelSpareOprService {

    @Autowired
    private Util util;

    private static String gateWayUrl;
    private String url = "/tjam/prophaseMag/maintenance/amAcceptanceSpare/readAllforExport";
    private static final String labels = "存货名称,存货编号,核验状态,专业,所属资产,规格型号,计量单位,厂商/供应商,单价,数量,金额";
    private static final String keys = "ssparenm,sspareno,sstrans,smajornm,sassetno,sspec,sunit,ssuppliernm,iprice,iqty,imoney";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        ExcelSpareOprService.gateWayUrl = gateWayUrl;
    }

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
     */
    @SuppressWarnings("static-access")
	public JSONObject exportData(AmAcceptanceSpareParam amAcceptanceSpareParam) {
        HashMap<String, Object> map = null;
        try {
            map = util.convertToMap(amAcceptanceSpareParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String docNm = "实物资产备品备件信息表";
//        JSONObject jsonObject = GenerateExcelHelper.createExcel(gateWayUrl, gateWayUrl+url, labels, keys, map, docNm, "sheet1");

        ExcelObj excelObj = new ExcelObj();
        excelObj.setExcelName(docNm);
        excelObj.setIslock(false);
        excelObj.setLabels(labels);
        excelObj.setKeys(keys);
        excelObj.setSheetName("sheet1");
        JSONObject jsonObject = GenerateExcelHelper.createExcel(gateWayUrl, gateWayUrl + url, excelObj, map);
        return jsonObject;
    }
}
