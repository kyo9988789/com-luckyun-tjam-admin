package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1InventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1Inventory;

@Service
public class InfL1InventoryService extends BaseService<InfL1Inventory>{

	@Autowired
	private InfL1InventoryMapper infL1InventoryMapper;
	
	@Override
	public BaseMapper<InfL1Inventory> getMapper() {
		return infL1InventoryMapper;
	}

	/**
	  *  如果数据是0，则：查询L1对应的两张明细表 
	  *  通过移交编号查询设备设施登记明细数据
	 * @param stransferno
	 * @return
	 */
	public List<InfL1Inventory> findAllByTransfernum(String stransferno){
		List<InfL1Inventory> infL1InvertoryList1 = this.infL1InventoryMapper.findAllByTransfernum(stransferno);
		return infL1InvertoryList1;
	}
	
}
