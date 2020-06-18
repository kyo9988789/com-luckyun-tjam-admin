package com.luckyun.tjam.prophaseMag.formfiasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetAttachmentParam;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetAttachmentService;

/**
 * Created by whs on 2020/5/25.
 */
@UrlByPkgController
public class AmFormFiassetAttachmentController {

    @Autowired
    private AmFormFiassetAttachmentService amFormFiassetAttachmentService;

    @PostMapping("add")
    public void handle(@RequestBody AmFormFiassetAttachmentParam condition){
        amFormFiassetAttachmentService.handle(condition);
    }

    @PostMapping("delete")
    public void delete(@RequestBody AmFormFiassetAttachmentParam condition){
        amFormFiassetAttachmentService.handle(condition);
    }

    @PostMapping("update")
    public void update(@RequestBody AmFormFiassetAttachmentParam condition){
        amFormFiassetAttachmentService.handle(condition);
    }
}
