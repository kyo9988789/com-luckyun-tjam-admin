package com.luckyun.tjam.prophaseMag.verification.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanDtFiassetService;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanDtSpareService;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanService;

@Api(name = "设备设施核验计划控制层", author = "zrw")
@UrlByPkgController
public class AmVrfPlanController {

	@Autowired
	private AmVrfPlanService amVrfPlanService;
	
	@Autowired
	private AmVrfPlanDtFiassetService amVrfPlanDtFiassetService;
	
	@Autowired
	private AmVrfPlanDtSpareService amVrfPlanDtSpareService;
	
	
	@Api(name = "查询全部设备设施核验计划", author = "zrw")
	@PostMapping("readAll")
	@Pageable
	public List<AmVrfPlan> findAll(@Param("设备设施资产参数") AmVrfPlanParam condition) {
		return this.amVrfPlanService.findAll(condition);
	}

	@Api(name = "查询单条设备设施核验计划", author = "zrw")
	@GetMapping("readOne")
	public AmVrfPlan findOne(@Param("indocno") Long indocno) {
		return this.amVrfPlanService.findOne(indocno);
	}
	
	@Api(name = "更新核验计划主数据", author = "zrw")
	@PostMapping("update")
	public void updateOpr(@Param("核验计划主数据") @RequestBody AmVrfPlan entity) {
		this.amVrfPlanService.updatePlan(entity);
	}
	
	@Api(name = "完结操作", author = "zrw")
	@PostMapping("finishOpr")
	public void finishOpr(@Param("核验计划主数据") @RequestBody AmVrfPlanParam condition) {
		this.amVrfPlanService.finishOpr(condition);
	}
	
	@Api(name = "批量删除操作", author = "zrw")
	@PostMapping("delete")
	public void delOpr(@Param("核验计划主数据") @RequestBody AmVrfPlanParam condition) {
		this.amVrfPlanService.delOpr(condition);
	}
	
	@Api(name = "新增操作", author = "zrw")
	@PostMapping("add")
	public AmVrfPlan add(@Param("新增主表数据") @RequestBody AmVrfPlan condition) {
		return this.amVrfPlanService.add(condition);
	}
	
	@Api(name = "新增实物资产核验计划明细数据", author = "zrw")
	@PostMapping("addAmVrfPlanDtFiasset")
	public void addAmVrfPlanDtFiasset(@Param("新增实物资产核验计划明细数据") @RequestBody AmVrfPlan condition) {
		this.amVrfPlanService.handleAddEquipLists(condition);
	}
	
	@Api(name = "新增备品备件核验计划明细数据", author = "zrw")
	@PostMapping("addAmVrfPlanDtSpare")
	public void addAmVrfPlanDtSpare(@Param("新增备品备件核验计划明细数据") @RequestBody AmVrfPlan condition) {
		this.amVrfPlanService.handleSpareDetail(condition);
	}
	
	@Api(name = "查询实物资产核验计划明细 + 组成部分",author = "zrw")
	@GetMapping("readAmVrfPlanDtFiasset")
	public List<AmVrfPlanDtFiasset> readAmVrfPlanDtFiasset(@Param("ilinkno") Long ilinkno) {
		return this.amVrfPlanDtFiassetService.readAmVrfPlanDtFiasset(ilinkno);
	}
	
	@Api(name = "查询备品备件核验计划明细 + 组成部分",author = "zrw")
	@GetMapping("readAmVrfPlanDtSpare")
	public List<AmVrfPlanDtSpare> readAmVrfPlanDtSpare(@Param("ilinkno") Long ilinkno) {
		return this.amVrfPlanDtSpareService.readAmVrfPlanDtSpare(ilinkno);
	}
}

