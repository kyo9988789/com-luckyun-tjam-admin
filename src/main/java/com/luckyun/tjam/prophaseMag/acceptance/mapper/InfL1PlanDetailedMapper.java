package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1PlanDetailed;

@Repository
public interface InfL1PlanDetailedMapper extends BaseMapper<InfL1PlanDetailed>{

	/**
	 * 通过移交计划编号查询计划明细
	 * @return：List<InfL1PlanDetailed>
	 */
	List<InfL1PlanDetailed> findAllByTransfernum(@Param("stransferno") String stransferno);

	/**
	 * 查询待copy到业务表的数据
	 * @return
	 */
	List<InfL1PlanDetailed> findAllByIhascopied();

	/**
	 * 修改copy状态
	 * @param condition
	 * @return
	 */
	int updateIhascopied(@Param("condition")InfL1PlanDetailed condition);
	
	
	/**
	 * 查询已更新到业务表但是发生了数据变更的接口数据
	 * @return
	 */
	List<InfL1PlanDetailed> findAllByIhascopiedAndIupdateState();
	
	/**
	  *  更新IupdateState为null
	 * @param indocno
	 * @return
	 */
	int updateIupdateState(@Param("indocno") Long indocno);
}
