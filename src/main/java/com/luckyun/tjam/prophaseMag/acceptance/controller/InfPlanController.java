package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfPlan;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfPlanService;

@Api(name = "设备设施移交计划控制层", author = "zrw")
@UrlByPkgController
public class InfPlanController {
	
	@Autowired
	private InfPlanService infPlanService;
	
	@Api(name = "查询全部设备设施移交计划", author = "zrw")
	@GetMapping("readAll")
	@Pageable
	public List<InfPlan> findAll() {
		return this.infPlanService.findAll();
	}

	@Api(name = "查询单条设备设施移交计划信息", author = "zrw")
	@GetMapping("readOne")
	public InfPlan getOne(@Param("设备台账对象") InfPlan condition) {
		return infPlanService.findOne(condition);
	}
	
}
