package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfPlan;
import com.luckyun.tjam.prophaseMag.acceptance.param.InfPlanParam;

/**
 * 
 * 设备设施移交计划持久层
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 13:42:38
 */
@Repository
public interface InfPlanMapper extends BaseMapper<InfPlan>{

	/**
	 * 查询所有移交计划数据
	 * @param condition
	 * @return
	 */
	List<InfPlan> findAll(@Param("condition") InfPlanParam condition);
	
	/**
	 * 查询单条移交计划的明细
	 * @param condition
	 * @return
	 */
	InfPlan findOne(@Param("indocno") Long indocno);
}
