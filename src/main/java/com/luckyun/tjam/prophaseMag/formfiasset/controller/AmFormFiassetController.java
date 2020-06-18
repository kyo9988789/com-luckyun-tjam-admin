package com.luckyun.tjam.prophaseMag.formfiasset.controller;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiasset;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetAttachment;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDt;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetParam;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetAttachmentService;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetDtCompService;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetDtService;
import com.luckyun.tjam.prophaseMag.formfiasset.service.AmFormFiassetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@UrlByPkgController
public class AmFormFiassetController {

    @Autowired
    private AmFormFiassetService amFormFiassetService;

    @Autowired
    private AmFormFiassetDtService amFormFiassetDtService;

    @Autowired
    private AmFormFiassetDtCompService amFormFiassetDtCompService;

    @Autowired
    private AmFormFiassetAttachmentService amFormFiassetAttachmentService;

    @PostMapping("add")
    public AmFormFiasset add(@RequestBody AmFormFiasset condition){
        return amFormFiassetService.add(condition);
    }

    @PostMapping("update")
    public AmFormFiasset update(@RequestBody AmFormFiasset condition){
        return amFormFiassetService.updatet(condition);
    }

    @PostMapping("delete")
    public Integer delete(@RequestBody AmFormFiassetParam condition){
         amFormFiassetService.delete(condition);
        return 1;
    }

    @PostMapping("readAll")
    @Pageable
    public List<AmFormFiasset> readAll(AmFormFiassetParam condition){
        return amFormFiassetService.findAll(condition);
    }

    @GetMapping("readOne")
    public AmFormFiasset readOne(Long indocno){
        return amFormFiassetService.findOne(indocno);
    }


    @GetMapping("readAllDtByIlinkno")
    public List<AmFormFiassetDt> readAllByIlinkno(Long ilinkno){
        return amFormFiassetDtService.findByIlinkno(ilinkno);
    }

    @GetMapping("readAllDtCompByIlinkno")
    public List<AmFormFiassetDtComp> readAllDtCompByIlinkno(Long ilinkno){
        return amFormFiassetDtCompService.findAllByIlinkno(ilinkno);
    }

    @GetMapping("readAllAttByIlinkno")
    public List<AmFormFiassetAttachment> readAllAttByIlinkno(Long ilinkno){
        return amFormFiassetAttachmentService.findAllByIlinkno(ilinkno);
    }

}
