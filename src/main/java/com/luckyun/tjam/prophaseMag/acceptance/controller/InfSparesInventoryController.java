package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesInventory;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfSparesInventoryService;

@Api(name = "备品备件数据控制层", author = "zrw")
@UrlByPkgController
public class InfSparesInventoryController {

	@Autowired
	private InfSparesInventoryService infSparesInventoryService;
	
	@Api(name = "查询一条备品备件数据对应的备品备件子表数据", author = "zrw")
	@GetMapping("readAllByIlinkno")
	@Pageable
	public List<InfSparesInventory> readAllByIlinkno(@Param("备品备件移交计划主数据") Long indocno) {
		return this.infSparesInventoryService.findAllByInfibillid(indocno);
	}
	
}
