package com.luckyun.tjam.prophaseMag.acceptance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1PlanDetailedMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1PlanDetailed;

@Service
public class InfL1PlanDetailedService extends BaseService<InfL1PlanDetailed>{

	@Autowired
	private InfL1PlanDetailedMapper infL1PlanDetailedMapper;
	
	@Override
	public BaseMapper<InfL1PlanDetailed> getMapper() {
		return infL1PlanDetailedMapper;
	}

	/**
	  *  如果数据是0，则：查询L1对应的两张明细表 
	  *  通过移交编号查询设备设施子表数据
	 * @param stransferno
	 * @return
	 */
	public List<InfL1PlanDetailed> findAllByTransfernum(String stransferno){
		List<InfL1PlanDetailed> l1PlanDetailedList = new ArrayList<InfL1PlanDetailed>();
		if(null != stransferno && !"".equals(stransferno)) {
			l1PlanDetailedList = this.infL1PlanDetailedMapper.findAllByTransfernum(stransferno);
		}
		return l1PlanDetailedList;
	}
	
}
