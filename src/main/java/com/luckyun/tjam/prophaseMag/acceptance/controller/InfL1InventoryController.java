package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1Inventory;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfL1InventoryService;

@Api(name = "L1设备设施登记明细表控制层", author = "zrw")
@UrlByPkgController
public class InfL1InventoryController {

	@Autowired
	private InfL1InventoryService infL1InventoryService;
	
	@Api(name = "查询EAM一条设备设施数据对应的登记明细数据", author = "zrw")
	@GetMapping("readAllByTransfernum")
	@Pageable
	public List<InfL1Inventory> findAllByTransfernum(@Param("设备设施计划表移交编号") String stransferno) {
		return infL1InventoryService.findAllByTransfernum(stransferno);
	}
	
}
