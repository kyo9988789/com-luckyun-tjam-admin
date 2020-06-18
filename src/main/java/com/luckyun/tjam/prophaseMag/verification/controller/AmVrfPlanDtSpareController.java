
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
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtSpareParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanDtSpareService;

@Api(name = "备品备件验核计划明细控制层", author = "zrw")
@UrlByPkgController
public class AmVrfPlanDtSpareController {

	@Autowired
	private AmVrfPlanDtSpareService amVrfPlanDtSpareService;
	
	@Api(name = "查询全部备品备件核验计划明细", author = "zrw")
	@GetMapping("readAll")
	@Pageable
	public List<AmVrfPlanDtSpare> findAll(@Param("备品备件核验计划参数") AmVrfPlanDtSpareParam condition) {
		return this.amVrfPlanDtSpareService.findAll(condition);
	}

	@Api(name = "查询单条备品备件核验计划明细", author = "zrw")
	@GetMapping("readOne")
	public AmVrfPlanDtSpare findOne(@Param("indocno") Long indocno) {
		return this.amVrfPlanDtSpareService.findOne(indocno);
	}
	
	@Api(name = "删除核验计划明细", author = "zrw")
	@PostMapping("delDtSpare")
	public int delDtSpare(@Param("待删除集合") @RequestBody AmVrfPlanDtSpareParam condition) {
		return this.amVrfPlanDtSpareService.delDtSpare(condition);
	}
}

