package com.luckyun.tjam.prophaseMag.verification.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWaitAppmt;
import com.luckyun.tjam.prophaseMag.verification.param.AmWaitAppmtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmWaitAppmtMapper extends BaseMapper<AmWaitAppmt> {

	/**
	 * 查询所有
	 * @param condition
	 * @return
	 */
	List<AmWaitAppmt> findAll(@Param("condition") AmWaitAppmtParam condition);

	/**
	 * 查询未被资产价值分摊选中的待分摊项：idatastate != 1
	 */
	List<AmWaitAppmt> readAllHasNotChoiced();

	/**
	 * 查询明细
	 * @param condition
	 * @return
	 */
	AmWaitAppmt findOne(@Param("indocno") Long indocno);

	/**
	 * 查询当前天最大计划编号
	 * @param splannm
	 * @return
	 */
	List<AmWaitAppmt> findMaxSappmtno(@Param("condition") AmWaitAppmtParam condition);
	
	/**
	 * 更新是否被引用字段
	 * @return
	 */
	void updateIquotestate(@Param("indocno") Long indocno);
}
