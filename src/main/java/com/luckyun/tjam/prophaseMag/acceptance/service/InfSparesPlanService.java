package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfSparesPlanMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesInventory;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesPlan;

@Service
public class InfSparesPlanService extends BaseService<InfSparesPlan>{

	@Autowired
	private InfSparesPlanMapper infSparesPlanMapper;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;
	
	@Override
	public BaseMapper<InfSparesPlan> getMapper() {
		return infSparesPlanMapper;
	}
	
	@Autowired
	private InfSparesInventoryService infSparesInventoryService;
	
	/**
	 * 查询所有备品备件移交计划主表数据
	 * @return
	 */
	public List<InfSparesPlan> findAll(){
		List<InfSparesPlan> infSparePlanList = this.infSparesPlanMapper.findAll();
		infSparePlanList.forEach(e -> {
			// 所属线路
			if (null != e.getIlineid()) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
		});
		return infSparePlanList;
	}

	/**
	 * 获取当前条备品备件移交计划数据的明细数据
	 * @param condition
	 * @return
	 */
	public InfSparesPlan findOne(InfSparesPlan condition) {
		InfSparesPlan infSparesPlan = this.infSparesPlanMapper.findOne(condition.getIndocno());
		if(null != infSparesPlan && null != infSparesPlan.getIndocno()) {
			List<InfSparesInventory> infSparesInventoryList = this.infSparesInventoryService.findAllByInfibillid(infSparesPlan.getIndocno());
			if(null != infSparesInventoryList && infSparesInventoryList.size() > 0) {
				infSparesPlan.setInfSparesInventoryList(infSparesInventoryList);
			}
		}
		return infSparesPlan;
	}
	
}
