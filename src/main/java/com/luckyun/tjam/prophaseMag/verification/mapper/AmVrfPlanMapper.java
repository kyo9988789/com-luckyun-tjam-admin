
package com.luckyun.tjam.prophaseMag.verification.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;

@Repository
public interface AmVrfPlanMapper extends BaseMapper<AmVrfPlan> {

	/**
	 * 查询所有
	 * @param condition
	 * @return
	 */
	List<AmVrfPlan> findAll(@Param("condition") AmVrfPlanParam condition);

	List<AmVrfPlan> findAlls();

	/**
	 * 查询明细
	 * @param indocno
	 * @return
	 */
	AmVrfPlan findOne(@Param("indocno") Long indocno);

	/**
	 * 计划类型
	 * @param indocno
	 * @return
	 */
	AmVrfPlan findIplantypeById(@Param("indocno") Long indocno);

	/**
	 * 查询当前天最大计划名称
	 * 
	 * @param splannm
	 * @return
	 */
	List<AmVrfPlan> findMaxSplannm(@Param("condition") AmVrfPlanParam condition);

	/**
	 * 查询当前天最大计划编号
	 * 
	 * @param splannm
	 * @return
	 */
	List<AmVrfPlan> findMaxSplanno(@Param("condition") AmVrfPlanParam condition);

}
