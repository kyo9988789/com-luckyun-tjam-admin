package com.luckyun.tjam.mtAssetMag.mtExcelUtil.service;

import java.util.HashMap;

import com.luckyun.report.entity.ExcelObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;
import com.luckyun.tjam.mtAssetMag.amToolsag.param.AmToolsParam;
import com.luckyun.tjam.prophaseMag.excelUtil.util.Util;

@Configuration
@Service
public class AmToolsExcelOprService {

    @Autowired
    private Util util;

    private static String gateWayUrl;
    private String url = "/tjam/mtAssetMag/amToolsag/amTools/readAllForExport";
    private static final String labels = "物品名称,物品编号,所属线路,品牌名称,规格型号,计量单位,数量,单价,专业,存放区域,存放位置,存放房间,备注一,备注二,分类,权属单位,管理单位";
    private static final String keys = "sfname,sfcode,slinenm,sbrand,sspec,sunit,iqty,iprice,smajornm,sinsarea,sinspos,sinsroom,snoteone,snotetwo,iamarcclassid,sownernuit,smanagenuit";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        AmToolsExcelOprService.gateWayUrl = gateWayUrl;
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
	public JSONObject exportData(AmToolsParam amToolsParam) {
        HashMap<String, Object> map = null;
        try {
            map = util.convertToMap(amToolsParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String docNm = "工器具台账表";
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
