
package com.luckyun.tjam.prophaseMag.maintenance.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luckyun.core.annotation.Pageable;
import com.luckyun.core.annotation.UrlByPkgController;
import com.luckyun.core.api.annotation.Api;
import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceFiassetParam;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmAcceptanceFiassetService;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(name = "设备设施资产控制层", author = "zrw")
@UrlByPkgController
public class AmAcceptanceFiassetController {

	@Autowired
	private AmAcceptanceFiassetService amAcceptanceFiassetService;

	@Api(name = "设备设施资产导出", author = "whs")
	@GetMapping("readAllForExport")
	@Pageable
	public List<AmAcceptanceFiasset> findAllForExport(@Param("设备设施资产参数") AmAcceptanceFiassetParam condition) {
		return this.amAcceptanceFiassetService.findAll(condition);
	}

	@Api(name = "查询全部设备设施资产", author = "zrw")
	@RequestMapping("readAll")
	@Pageable
	public List<AmAcceptanceFiasset> findAll(@Param("设备设施资产参数") AmAcceptanceFiassetParam condition) {
		return this.amAcceptanceFiassetService.findAll(condition);
	}

	@Api(name = "查询设备设施资产明细", author = "zrw")
	@GetMapping("readOne")
	public AmAcceptanceFiasset findOne(@Param("设备设施资产参数") AmAcceptanceFiassetParam condition) {
		return this.amAcceptanceFiassetService.findOne(condition);
	}
	
	@Api(name = "分配责任人", author = "zrw")
	@PostMapping("distResponsibility")
	public void distResponsibility(@Param("设备设施资产参数") @RequestBody AmAcceptanceFiassetParam condition) {
		this.amAcceptanceFiassetService.distResponsibility(condition);
	}
	
	@Api(name = "获取除当前条及子资产以外的所有资产编号", author = "zrw")
	@GetMapping("findParent")
	public List<AmAcceptanceFiasset> findParentAssetnumIgnoreNeed(@Param("资产编号") String sfcode ) {
		return this.amAcceptanceFiassetService.findParentAssetnumIgnoreNeed(sfcode );
	}
	
	@Api(name = "更新设备设施资产明细", author = "zrw")
	@PostMapping("updateEquip")
	public AmAcceptanceFiasset updateOne(@Param("设备设施资产对象") @RequestBody AmAcceptanceFiasset entity) {
		return this.amAcceptanceFiassetService.updateEquip(entity);
	}
	
	@Api(name = "修改核验状态", author = "zrw")
	@PostMapping("updateStatus")
	public AmAcceptanceFiasset updateTransferStatus(@Param("设备设施资产对象") @RequestBody AmAcceptanceFiassetParam entity) {
		return this.amAcceptanceFiassetService.updateTransferStatus(entity);
	}
	
	@Api(name = "责任人同意或拒绝", author = "zrw")
	@PostMapping("agreeOrRefuse")
	public void agreeOrRefuse(@Param("设备设施资产对象") @RequestBody AmAcceptanceFiassetParam entity) {
		this.amAcceptanceFiassetService.agreeOrRefuse(entity);
	}
	
	@Api(name = "生成设备设施核验计划", author = "zrw")
	@PostMapping("generateVerification")
	public String generateVerification(@Param("设备设施资产对象") @RequestBody AmAcceptanceFiassetParam entity) {
		String res = this.amAcceptanceFiassetService.generateVerification(entity);
		return res;
	}
	
	@Api(name = "查询所有'未生成核验计划明细'且'不是组成部分'的数据", author = "zrw")
	@PostMapping("readAllHasNotGreVerification")
	@Pageable
	public List<AmAcceptanceFiasset> readAllHasNotGreVerification(@Param("设备设施资产参数") AmAcceptanceFiassetParam condition) {
		return this.amAcceptanceFiassetService.findAllHasNotGreVerification(condition);
	}

	@PostMapping("test")
	public String sync(){
		return "+++++++++++++测试001++++++++++++";
	}
	
	@Api(name = "查找组成部分", author = "zrw")
	@GetMapping("findSonByIparent")
	@Pageable
	public List<AmAcceptanceFiasset> findSonByIparent(@Param("资产编号") String sassetnum) {
		return this.amAcceptanceFiassetService.findSonByIparent(sassetnum);
	}

	@PostMapping("uploadData")
	public Integer insertAllDealtWith(){
		return this.amAcceptanceFiassetService.syncInfTran();
	}

	@Api(name = "实物资产导入",author = "whs")
	@PostMapping("import")
	public ExcelBatchSender importSendCheck(@RequestBody ExcelBatchReceiver<AmAcceptanceFiasset> entity){
		return amAcceptanceFiassetService.importSendCheck(entity);
	}
	
	@Api(name = "查询未被引用的主资产数据", author = "zrw")
	@PostMapping("readAllHasNotQuoted")
	@Pageable
	public List<AmAcceptanceFiasset> readAllHasNotQuoted() {
		return this.amAcceptanceFiassetService.readAllHasNotQuoted();
	}
}

