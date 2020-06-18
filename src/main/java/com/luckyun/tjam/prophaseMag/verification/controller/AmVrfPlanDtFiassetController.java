
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
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtFiassetParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanDtFiassetService;

@Api(name = "设备设施核验计划明细控制层", author = "zrw")
@UrlByPkgController
public class AmVrfPlanDtFiassetController {

	@Autowired
	private AmVrfPlanDtFiassetService amVrfPlanDtFiassetService;
	
	@Api(name = "查询全部设备设施核验计划明细", author = "zrw")
	@GetMapping("readAll")
	@Pageable
	public List<AmVrfPlanDtFiasset> findAll(@Param("设备设施核验计划参数") AmVrfPlanDtFiassetParam condition) {
		return this.amVrfPlanDtFiassetService.findAll(condition);
	}

	@Api(name = "查询单条设备设施核验计划明细", author = "zrw")
	@GetMapping("readOne")
	public AmVrfPlanDtFiasset findOne(@Param("indocno") Long indocno) {
		return this.amVrfPlanDtFiassetService.findOne(indocno);
	}
	
	@Api(name = "查找单条核验计划明细的组成部分", author = "zrw")
	@GetMapping("findAllBySparent")
	@Pageable
	public List<AmVrfPlanDtFiasset> findAllBySparent(@Param("资产编号") String sparentno) {
		return this.amVrfPlanDtFiassetService.findAllBySparent(sparentno);
	}
	
	@Api(name = "查询实物资产核验计划明细 + 组成部分",author = "zrw")
	@GetMapping("readAmVrfPlanDtFiasset")
	public List<AmVrfPlanDtFiasset> readAmVrfPlanDtFiasset(@Param("ilinkno") Long ilinkno) {
		return this.amVrfPlanDtFiassetService.readAmVrfPlanDtFiasset(ilinkno);
	}
	
	@Api(name = "删除核验计划明细 + 关联的组成部分", author = "zrw")
	@PostMapping("delDtFiasset")
	public int delDtFiasset(@Param("待删除集合") @RequestBody AmVrfPlanDtFiassetParam condition) {
		return this.amVrfPlanDtFiassetService.delDtFiasset(condition);
	}
}

