package com.luckyun.tjam.mtAssetMag.asledag.controller;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicCreateFincInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicCreateFincInfParam;
import com.luckyun.tjam.mtAssetMag.asledag.service.AmBasicCreateFincInfService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(name = "资产卡片控制层", author = "cy")
@UrlByPkgController
public class AmBasicCreateFincInfController {

    @Autowired
    private AmBasicCreateFincInfService amBasicCreateFincInfService;

    @Api(name = "更新数据", author = "cy")
    @PostMapping("update")
    public void updateOpr(@Param("主数据") @RequestBody AmBasicCreateFincInfParam entity) {
        this.amBasicCreateFincInfService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmBasicCreateFincInfParam condition) {
        this.amBasicCreateFincInfService.delOpr(condition);
    }


    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public void add(@Param("新增数据") @RequestBody AmBasicCreateFincInf condition){
         amBasicCreateFincInfService.add(condition);
    }
}
