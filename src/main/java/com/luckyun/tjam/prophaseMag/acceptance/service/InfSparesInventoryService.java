package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfSparesInventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesInventory;

@Service
public class InfSparesInventoryService extends BaseService<InfSparesInventory> {

	@Autowired
	private InfSparesInventoryMapper infSparesInventoryMapper;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;
	
	@Override
	public BaseMapper<InfSparesInventory> getMapper() {
		return infSparesInventoryMapper;
	}

	/** 通过外键获取所有备品备件明细：inf_ibillid */
	public List<InfSparesInventory> findAllByInfibillid(Long indocno) {
		List<InfSparesInventory> infSparesInventoryList = this.infSparesInventoryMapper.findAllByInfibillid(indocno);
		infSparesInventoryList.forEach(e -> {
			// 所属线路
			if (null != e.getIlineid()) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
		});
		return infSparesInventoryList;
	}

}
