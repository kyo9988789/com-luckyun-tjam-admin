package com.luckyun.tjam.prophaseMag.maintenance.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAcceptanceSpareParam extends AmAcceptanceSpare {

	@Describe("分配责任人数据集合")
	List<AmAcceptanceSpare> distList;
	
	@Describe("待修改移交状态数据集合")
	List<AmAcceptanceSpare> transferList;
	
	@Describe("高级检索")
	private String sname;
	
	@Describe("责任人同意或者拒绝的数据集")
	List<AmAcceptanceSpare> oprDataList;
	
	@Describe("移交计划")
	private String description;
	
	@Describe("生成核验计划数据集")
	List<AmAcceptanceSpare> generateDataList;

	@Describe("主键集合，以”,“分开")
	private String indocnos;

	@Describe("主键集合list")
	private List<Long> indocnoList;
}

