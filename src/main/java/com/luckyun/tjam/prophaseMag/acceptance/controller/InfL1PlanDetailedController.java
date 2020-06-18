package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1PlanDetailed;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfL1PlanDetailedService;

@Api(name = "EAM移交计划子表数据控制层", author = "zrw")
@UrlByPkgController
public class InfL1PlanDetailedController {

	@Autowired
	private InfL1PlanDetailedService infL1PlanDetailedService;
	
	@Api(name = "查询EAM一条设备设施数据对应的移交计划子表数据", author = "zrw")
	@GetMapping("readAllByTransfernum")
	@Pageable
	public List<InfL1PlanDetailed> findAllByTransfernum(@Param("设备设施计划表移交编号") String stransferno) {
		return infL1PlanDetailedService.findAllByTransfernum(stransferno);
	}
	
}
