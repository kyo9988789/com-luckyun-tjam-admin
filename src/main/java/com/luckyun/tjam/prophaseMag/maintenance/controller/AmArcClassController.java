
package com.luckyun.tjam.prophaseMag.maintenance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmArcClassService;

@Api(name = "资产分类树控制层", author = "zrw")
@UrlByPkgController
public class AmArcClassController {
	
	@Autowired
	private AmArcClassService amArcClassService;
	
	@Api(name = "查询资产分类树一级和二级节点", author = "zrw")
	@GetMapping("readAll")
	public List<AmArcClass> findAll() {
		return this.amArcClassService.findAll();
	}

	@Api(name = "通过iparent查询分类树某个节点下面的所有节点", author = "zrw")
	@GetMapping("findAllByIparentid")
	public List<AmArcClass> getOne(@Param("父节点主键") Long iparentid) {
		return this.amArcClassService.findAllByIparentid(iparentid);
	}
	
	//generateSidcc
	@Api(name = "获取除当前条及子资产以外的所有资产编号", author = "zrw")
	@GetMapping("generateSidcc")
	public void generateSidcc() {
		this.amArcClassService.generateSidcc();
	}
	
	@Api(name = "查询全部资产分类树数据", author = "zrw")
	@GetMapping("findAllDatas")
	public List<AmArcClass> findAllDatas() {
		return this.amArcClassService.findAllDatas();
	}

}

