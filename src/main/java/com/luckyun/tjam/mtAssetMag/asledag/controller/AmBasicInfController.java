package com.luckyun.tjam.mtAssetMag.asledag.controller;


import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.mtAssetMag.asledag.model.*;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicInfParam;
import com.luckyun.tjam.mtAssetMag.asledag.service.AmBasicInfService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(name = "资产卡片控制层", author = "cy")
@UrlByPkgController
public class AmBasicInfController {

    @Autowired
    private AmBasicInfService amBasicInfService;

    @Api(name = "查询全部", author = "cy")
    @RequestMapping("readAll")
    @Pageable
    public List<AmBasicInf> findAll(@Param("设备设施资产参数") AmBasicInfParam condition) {
        return this.amBasicInfService.findAll(condition);
    }

    @Api(name = "查询全部", author = "cy")
    @PostMapping("readAllHasNotQuoted")
    @Pageable
    public List<AmBasicInf> findAllHasNotQuoted() {
        return this.amBasicInfService.findAllHasNotQuoted();
    }

    @Api(name = "查询资产组成", author = "cy")
    @RequestMapping(value = "query")
    @Pageable
    public List<AmBasicComponentInf> findAlls(@Param("设备设施资产参数") AmBasicInfParam condition) {
        return this.amBasicInfService.findAlls(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOne")
    public AmBasicInf findOne(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOne(indocno);
    }

    @Api(name = "新增", author = "cy")
    @PostMapping("add")
    public AmBasicInf add(@Param("新增数据") @RequestBody AmBasicInf condition){
        return amBasicInfService.add(condition);
    }

    @Api(name = "更新核验计划主数据", author = "cy")
    @PostMapping("update")
    public AmBasicInf updateOpr(@Param("主数据") @RequestBody AmBasicInf entity) {
        return amBasicInfService.updateBasic(entity);
    }

    @Api(name = "批量删除操作", author = "cy")
    @PostMapping("delete")
    public void delOpr(@Param("核验计划主数据") @RequestBody AmBasicInfParam condition) {
        this.amBasicInfService.delOpr(condition);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnes")
    public List<AmBasicValueInf> findOnes(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOnes(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnet")
    public List<AmBasicTenureInf> findOnet(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOnet(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnem")
    public List<AmBasicManagementInf> findOnem(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOnem(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnel")
    public List<AmBasicLocArea> findOnel(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOnel(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOned")
    public List<AmBasicDepreciation> findOned(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOned(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOnec")
    public List<AmBasicCreateFincInf> findOnec(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOnec(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOneCo")
    public List<AmBasicContractInf> findOneCo(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOneCo(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOneCom")
    public List<AmBasicComponentInf> findOneCom(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOneCom(indocno);
    }

    @Api(name = "查询单条", author = "cy")
    @GetMapping("readOneA")
    public List<AmBasicAnnex> findOneA(@Param("indocno") Long indocno) {
        return this.amBasicInfService.findOneA(indocno);
    }

    @Api(name = "批量变更", author = "cy")
    @PostMapping("generateVerification")
    public String generateVerification(@Param("设备设施资产对象") @RequestBody AmBasicInfParam entity) {
        String res = this.amBasicInfService.generateVerification(entity);
        return res;
    }

    @Api(name = "批量调出", author = "cy")
    @PostMapping("generateVerificationes")
    public String generateVerificationes(@Param("设备设施资产对象") @RequestBody AmBasicInfParam entity) {
        String res = this.amBasicInfService.generateVerificationes(entity);
        return res;
    }

    @Api(name = "批量闲置", author = "cy")
    @PostMapping("generateVerificationas")
    public String generateVerificationas(@Param("设备设施资产对象") @RequestBody AmBasicInfParam entity) {
        String res = this.amBasicInfService.generateVerificationas(entity);
        return res;
    }

    @Api(name = "批量下发", author = "cy")
    @PostMapping("generateVerificationXf")
    public String generateVerificationXf(@Param("设备设施资产对象") @RequestBody AmBasicInfParam entity) {
        String res = this.amBasicInfService.generateVerificationXf(entity);
        return res;
    }
}
