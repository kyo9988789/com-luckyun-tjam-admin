
package com.luckyun.tjam.prophaseMag.verification.mapper;

import java.util.List;

import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtSpareParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;

@Repository
public interface AmVrfPlanDtSpareMapper extends BaseMapper<AmVrfPlanDtSpare> {

	/**
	 * 查询所有
	 * 
	 * @param condition
	 * @return
	 */
	List<AmVrfPlanDtSpare> findAll(@Param("condition") AmVrfPlanDtSpareParam condition);

	/**
	 * 查询明细
	 * 
	 * @param indocno
	 * @return
	 */
	AmVrfPlanDtSpare findOne(@Param("indocno") Long indocno);

	/**
	 * 通过外键查询所有明细
	 * 
	 * @param ibillid
	 * @return
	 */
	List<AmVrfPlanDtSpare> findAllByIbillid(@Param("ilinkno") Long ilinkno);

	/**
	 * 查询计划下明细数量
	 * 
	 * @param indocno
	 * @return
	 */
	Integer findDetailCountByIndocno(@Param("ilinkno") Long ilinkno);

}
