package com.luckyun.tjam.prophaseMag.verification.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtBatchParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmWorthAppmtBatchMapper extends BaseMapper<AmWorthAppmtBatch> {

	/**
	 * 查询所有
	 * 
	 * @param condition
	 * @return
	 */
	List<AmWorthAppmtBatch> findAll(@Param("condition") AmWorthAppmtBatchParam condition);

	/**
	 * 查询明细
	 * 
	 * @param condition
	 * @return
	 */
	AmWorthAppmtBatch findOne(@Param("indocno") Long indocno);

	/**
	 * 查询当前天最大计划编号
	 * 
	 * @param splannm
	 * @return
	 */
	List<AmWorthAppmtBatch> findMaxSappmtno(@Param("condition") AmWorthAppmtBatchParam condition);

}
