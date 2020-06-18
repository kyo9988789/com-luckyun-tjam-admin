package com.luckyun.tjam.mtAssetMag.amToolsag.controller;

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
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmTools;
import com.luckyun.tjam.mtAssetMag.amToolsag.param.AmToolsParam;
import com.luckyun.tjam.mtAssetMag.amToolsag.service.AmToolsService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "工器具控制层", author = "cy")
@UrlByPkgController
public class AmToolsController {

    @Autowired
    private AmToolsService amToolsService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmTools> findAll(@Param("设备设施资产参数") AmToolsParam condition) {
        return this.amToolsService.findAll(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmTools findOne(@Param("indocno") Long indocno) {
        return this.amToolsService.findOne(indocno);
    }

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmTools entity) {
        this.amToolsService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmToolsParam condition) {
        this.amToolsService.delOpr(condition);
    }

    @Api(name = "工器具台账导入",author = "cy")
    @PostMapping("import")
    public ExcelBatchSender importSendCheck(@RequestBody ExcelBatchReceiver<AmTools> entity){
        return amToolsService.importSendCheck(entity);
    }

    @Api(name = "工器具台账导出", author = "whs")
    @GetMapping("readAllForExport")
    @Pageable
    public List<AmTools> findAllForExport(@Param("设备设施资产参数") AmToolsParam condition) {
        return this.amToolsService.findAll(condition);
    }
}
