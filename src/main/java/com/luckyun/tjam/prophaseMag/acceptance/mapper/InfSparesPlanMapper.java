package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesPlan;

@Repository
public interface InfSparesPlanMapper extends BaseMapper<InfSparesPlan>{
	
	/**
	 * 查询所有备品备件移交计划主表数据
	 * @return
	 */
	List<InfSparesPlan> findAll();
	
	/**
	 * 查询单条备品备件移交计划主表数据的明细
	 * @return
	 */
	InfSparesPlan findOne(@Param("indocno") Long indocno);

	List<InfSparesPlan> findByIsource(@Param("isource")Integer isource);
}
