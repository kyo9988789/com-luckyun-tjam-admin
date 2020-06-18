
package com.luckyun.tjam.prophaseMag.maintenance.controller;

import java.util.List;

import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceSpareParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmAcceptanceSpareService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "备品备件资产控制层", author = "zrw")
@UrlByPkgController
public class AmAcceptanceSpareController {

	@Autowired
	private AmAcceptanceSpareService amAcceptanceSpareService;
	
	@Api(name = "查询全部备品备件资产", author = "zrw")
	@RequestMapping("readAll")
	@Pageable
	public List<AmAcceptanceSpare> findAll(@Param("备品备件资产参数") AmAcceptanceSpareParam condition) {
		return this.amAcceptanceSpareService.findAll(condition);
	}


	@Api(name = "查询全部备品备件资产导出", author = "whs")
	@GetMapping("readAllforExport")
	@Pageable
	public List<AmAcceptanceSpare> readAllforExport(@Param("备品备件资产参数") AmAcceptanceSpareParam condition) {
		return this.amAcceptanceSpareService.findAll(condition);
	}

	@Api(name = "查询备品备件资产明细", author = "zrw")
	@GetMapping("readOne")
	public AmAcceptanceSpare findOne(@Param("备品备件资产参数") AmAcceptanceSpareParam condition) {
		return this.amAcceptanceSpareService.findOne(condition);
	}
	
	@Api(name = "分配责任人", author = "zrw")
	@PostMapping("distResponsibility")
	public List<AmAcceptanceSpare> distResponsibility(@Param("备品备件资产参数") @RequestBody AmAcceptanceSpareParam condition) {
		return this.amAcceptanceSpareService.distResponsibility(condition);
	}
	
	@Api(name = "更新备品备件资产明细", author = "zrw")
	@PostMapping("updateSpare")
	public AmAcceptanceSpare updateOne(@Param("备品备件资产对象") @RequestBody AmAcceptanceSpare entity) {
		return this.amAcceptanceSpareService.updateSpare(entity);
	}
	
	@Api(name = "修改移交状态", author = "zrw")
	@PostMapping("updateStatus")
	public AmAcceptanceSpare updateTransferStatus(@Param("备品备件资产对象") @RequestBody AmAcceptanceSpareParam entity) {
		return this.amAcceptanceSpareService.updateTransferStatus(entity);
	}
	
	@Api(name = "责任人同意或拒绝", author = "zrw")
	@PostMapping("agreeOrRefuse")
	public void agreeOrRefuse(@Param("备品备件资产对象") @RequestBody AmAcceptanceSpareParam entity) {
		this.amAcceptanceSpareService.agreeOrRefuse(entity);
	}
	
	@Api(name = "查询所有'未生成核验计划明细'的数据", author = "zrw")
	@PostMapping("readAllHasNotGreVerification")
	@Pageable
	public List<AmAcceptanceSpare> readAllHasNotGreVerification(@Param("备品备件资产参数") AmAcceptanceSpareParam condition) {
		return this.amAcceptanceSpareService.findAllHasNotGreVerification(condition);
	}
	
	@Api(name = "生成备品备件核验计划", author = "zrw")
	@PostMapping("generateVerification")
	public String generateVerification(@Param("备品备件资产对象") @RequestBody AmAcceptanceSpareParam entity) {
		String res = this.amAcceptanceSpareService.generateVerification(entity);
		return res;
	}

	@Api(name = "实物资产备品备件导入",author = "whs")
	@PostMapping("import")
	public ExcelBatchSender importSendCheck(@RequestBody ExcelBatchReceiver<AmAcceptanceSpare> entity){
		return amAcceptanceSpareService.importSendCheck(entity);
	}

}

