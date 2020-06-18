package com.luckyun.tjam.mtAssetMag.amDurableLowValue.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumable;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.param.AmDurLvConsumableParam;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.service.AmDurLvConsumableService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "耐用低值易耗品台账控制层", author = "cy")
@UrlByPkgController
public class AmDurLvConsumableController {

    @Autowired
    private AmDurLvConsumableService amDurLvConsumableService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmDurLvConsumable> findAll(@Param("设备设施资产参数") AmDurLvConsumableParam condition) {
        return this.amDurLvConsumableService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmDurLvConsumable findOne(@Param("indocno") Long indocno) {
        return this.amDurLvConsumableService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmDurLvConsumable entity) {
        this.amDurLvConsumableService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmDurLvConsumableParam condition) {
        this.amDurLvConsumableService.delOpr(condition);
    }

    @Api(name = "备品备件台账导入",author = "cy")
    @PostMapping("import")
    public ExcelBatchSender importSendCheck(@RequestBody ExcelBatchReceiver<AmDurLvConsumable> entity){
        return amDurLvConsumableService.importSendCheck(entity);
    }
    
    @Api(name = "备品备件导出", author = "cy")
    @GetMapping("readAllForExport")
    @Pageable
    public List<AmDurLvConsumable> findAllForExport(@Param("设备设施资产参数") AmDurLvConsumableParam condition) {
        return this.amDurLvConsumableService.findAll(condition);
    }
}
