package com.luckyun.tjam.mtAssetMag.mtExcelUtil.service;

import java.util.HashMap;

import com.luckyun.report.entity.ExcelObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.param.AmDurLvConsumableParam;
import com.luckyun.tjam.prophaseMag.excelUtil.util.Util;

/**
 * Created by cy on 2020/4/10.
 */
@Configuration
@Service
public class AmDurLvOprService {

    @Autowired
    private Util util;

    private static String gateWayUrl;
    private String url = "/tjam/mtAssetMag/amDurableLowValue/amDurLvConsumable/readAllForExport";
    private static final String labels = "名称,编码,所属线路,品牌名称,规格型号,分类,核算部门,单价,存放区域,存放位置,存放房间,购置日期,使用人,费用来源,管理单位,权属单位,使用单位";
    private static final String keys = "sfname,sfcode,slinenm,sbrand,sspec,samarcclassnm,saccountdeptnm,iprice,sinsarea,sinspos,sposroom,dpurchase,susernm,scostsource,smanagenuit,sownernuit,susenuit";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        AmDurLvOprService.gateWayUrl = gateWayUrl;
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
     * @param amSpareParam
     * @return excel的状态轮询地址,Object对象.非string对象
     */
    @SuppressWarnings("static-access")
	public JSONObject exportData(AmDurLvConsumableParam amDurLvConsumableParam) {
        HashMap<String, Object> map = null;
        try {
            map = util.convertToMap(amDurLvConsumableParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String docNm = "耐用低值表";
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
