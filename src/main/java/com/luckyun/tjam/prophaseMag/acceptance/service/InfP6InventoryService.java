package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfP6InventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfP6Inventory;

@Service
public class InfP6InventoryService extends BaseService<InfP6Inventory>{

	@Autowired
	private InfP6InventoryMapper infP6InventoryMapper;
	
	@Override
	public BaseMapper<InfP6Inventory> getMapper() {
		return infP6InventoryMapper;
	}

	/**
	 * 通过"移交计划编号"获取所有- P6设备设施初始清册数据
	 * @param stransferno
	 * @return
	 */
	public List<InfP6Inventory> findAllByTransfernum(String stransferno){
		List<InfP6Inventory> infP6InventoryList = new ArrayList<InfP6Inventory>();
		if(null != stransferno && !"".equals(stransferno)) {
			infP6InventoryList = this.infP6InventoryMapper.findAllByTransfernum(stransferno);
		}
		return infP6InventoryList;
	}
	
}
