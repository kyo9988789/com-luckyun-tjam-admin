package com.luckyun.tjam.prophaseMag.excelUtil.service;

import com.alibaba.fastjson.JSONObject;
import com.luckyun.report.GenerateExcelHelper;
import com.luckyun.report.entity.ExcelObj;
import com.luckyun.tjam.prophaseMag.excelUtil.util.Util;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceFiassetParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.HashMap;

/**
 * Created by whs on 2020/4/2.
 */

@Configuration
@Service
public class ExcelOprService {

    @Autowired
    private Util util;

    private static String gateWayUrl;
    private String url = "/tjam/prophaseMag/maintenance/amAcceptanceFiasset/readAllForExport";
    private static final String labels = "资产名称,资产编号,核验状态,移交专业,所属线路,上级资产编号,实际品牌,实际安装位置,起始里程,终止里程,实际制造商,实际规格型号,实际数量,接收单位,移交条码";
    private static final String keys = "sfname,sfcode,sstrans,smajornm,slinenm,sparentno,sbrandAct,sinslocationnoAct,slinestartpost,slineendpost,sproductnmAct,sspecAct,iqtyAct,stranssite,sbarcode";

    @Value("${reporturl}")
    public void setPreOssUrl(String gateWayUrl) {
        ExcelOprService.gateWayUrl = gateWayUrl;
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
	public JSONObject exportData(AmAcceptanceFiassetParam amAcceptanceFiassetParam) {
        HashMap<String, Object> map = null;
        try {
            map = util.convertToMap(amAcceptanceFiassetParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String docNm = "实物资产信息表";
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
