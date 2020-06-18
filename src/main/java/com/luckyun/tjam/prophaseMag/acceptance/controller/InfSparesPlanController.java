package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesPlan;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfSparesPlanService;

@Api(name = "备品备件移交计划主数据控制层", author = "zrw")
@UrlByPkgController
public class InfSparesPlanController {

	@Autowired
	private InfSparesPlanService infSparesPlanService;
	
	@Api(name = "查询全部备品备件移交计划主数据", author = "zrw")
	@GetMapping("readAll")
	@Pageable
	public List<InfSparesPlan> findAll() {
		return this.infSparesPlanService.findAll();
	}

	/**
	 * @param：inf_indocno
	 */
	@Api(name = "查询备品备件移交计划信息", author = "zrw")
	@GetMapping("readOne")
	public InfSparesPlan getOne(@Param("备品备件移交计划对象") InfSparesPlan condition) {
		return this.infSparesPlanService.findOne(condition);
	}
	
}
