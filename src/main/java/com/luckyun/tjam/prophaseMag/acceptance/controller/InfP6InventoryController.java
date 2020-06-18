package com.luckyun.tjam.prophaseMag.acceptance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfP6Inventory;
import com.luckyun.tjam.prophaseMag.acceptance.service.InfP6InventoryService;

@Api(name = "EAM移交计划子表数据控制层", author = "zrw")
@UrlByPkgController
public class InfP6InventoryController {

	@Autowired
	private InfP6InventoryService infP6InventoryService;
	
	@Api(name = "查询P6一条设备设施数据对应的登记明细数据", author = "zrw")
	@GetMapping("readAllByTransfernum")
	@Pageable
	public List<InfP6Inventory> findAllByTransfernum(@Param("设备设施计划表移交编号") String stransferno) {
		return infP6InventoryService.findAllByTransfernum(stransferno);
	}
	
}
