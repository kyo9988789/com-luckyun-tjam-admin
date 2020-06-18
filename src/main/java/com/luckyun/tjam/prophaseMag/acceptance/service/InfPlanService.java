package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.ArrayList;
import java.util.List;

import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1InventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1PlanDetailedMapper;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfPlanMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1Inventory;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1PlanDetailed;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfP6Inventory;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfPlan;

@Service
public class InfPlanService extends BaseService<InfPlan> {

	@Autowired
	private InfPlanMapper infPlanMapper;

	@Override
	public BaseMapper<InfPlan> getMapper() {
		return infPlanMapper;
	}

	@Autowired
	private InfL1PlanDetailedMapper infL1PlanDetailedMapper;

	@Autowired
	private InfL1InventoryMapper infL1InventoryMapper;

	@Autowired
	private InfP6InventoryService infP6InventoryService;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;
	
	/**
	 * 查询所有设备设施数据("导出含明细"功能暂不适用这个方法，后面开发"导出"功能时，问下吴宇博导出模板做成什么样)
	 * 
	 * @param condition
	 * @return
	 */
	public List<InfPlan> findAll() {
		List<InfPlan> infPlanList = this.infPlanMapper.findAll(null);
		for(InfPlan e : infPlanList) {
			if(null != e.getIlineid()) {
			  e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.
					  CAP_ACCEPTANCE_EQUIP_ILINEID,e.getIlineid() != null ? e.getIlineid() : ""));
			}

		}
		return infPlanList;
	}

	/**
	 * 查找计划明细(L1、P6区分)
	 * 
	 * @param condition
	 * @return
	 */
	public InfPlan findOne(InfPlan condition) {
		InfPlan infPlan = this.infPlanMapper.findOne(condition.getIndocno());
		/** 0:L1 - 1:P6 */
		if (null != infPlan.getIsource() && 0 == infPlan.getIsource()) { // L1
			List<InfL1Inventory> infL1InvertoryList1 = new ArrayList<InfL1Inventory>();
			// 所有L1设备设施登记明细表(tab2)
			/** tab1↓ */
			List<InfL1PlanDetailed> infPlanDetailList = this.infL1PlanDetailedMapper.findAllByTransfernum(infPlan.getStransferno());
			for (InfL1PlanDetailed e1 : infPlanDetailList) {
				List<InfL1Inventory> infL1InvertoryList2 = this.infL1InventoryMapper.findAllByAssetnum(e1.getSfcode());
				if (null != infL1InvertoryList2 && infL1InvertoryList2.size() > 0) {
					//e1.setInfL1Inventory(infL1InvertoryList2.get(0));	每一个计划子表对应的登记明细：1 V 1
					infL1InvertoryList1.add(infL1InvertoryList2.get(0));
				}
			}
				infPlan.setInfL1PlanDetailedList(infPlanDetailList);
				infPlan.setInfL1InvertoryList(infL1InvertoryList1);
		} else if (null != infPlan.getIsource() && 1 == infPlan.getIsource()) { // P6
			List<InfP6Inventory> infP6InventoryList = this.infP6InventoryService.findAllByTransfernum(infPlan.getStransferno());
			infPlan.setInfP6InventoryList(infP6InventoryList);
		}

		return infPlan;
	}

}
