package com.luckyun.tjam.mtAssetMag.mtExcelUtil.service;

import java.util.HashMap;

import com.luckyun.report.entity.ExcelObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.param.AmSpareParam;
import com.luckyun.tjam.prophaseMag.excelUtil.util.Util;

/**
 * Created by cy on 2020/4/10.
 */
@Configuration
@Service
public class AmExcelOprService {

    @Autowired
    private Util util;

    private static String gateWayUrl;
    private String url = "/tjam/mtAssetMag/amAcceSpare/amSpare/readAllForExport";
    private static final String labels = "存货名称,存货编号,专业,分类,所属线路,规格型号,计量单位,供应商,单价,数量,金额,管理部门,管理单位,权属单位,使用单位,责任人";
    private static final String keys = "sfname,sfcode,smajornm,samarcclassnm,slinenm,sspec,sunit,ssuppliernm,iprice,iqty,imoney,smanagedeptnm,smanagenuit,sownernuit,susenuit,sdutynm";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        AmExcelOprService.gateWayUrl = gateWayUrl;
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
	public JSONObject exportData(AmSpareParam amSpareParam) {
        HashMap<String, Object> map = null;
        try {
            map = util.convertToMap(amSpareParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String docNm = "备品备件台账表";
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
