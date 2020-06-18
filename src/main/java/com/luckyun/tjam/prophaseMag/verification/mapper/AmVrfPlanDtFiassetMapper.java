
package com.luckyun.tjam.prophaseMag.verification.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtFiassetParam;

@Repository
public interface AmVrfPlanDtFiassetMapper extends BaseMapper<AmVrfPlanDtFiasset>{


	/**
	  *  查询所有
	 * @param condition
	 * @return
	 */
	List<AmVrfPlanDtFiasset> findAll(@Param("condition") AmVrfPlanDtFiassetParam condition);

	/*查询所有
	*
	* */
	List<AmVrfPlanDtFiasset> findAlls();
	

	/**
	  *  查询明细
	 * @param indocno
	 * @return
	 */
	AmVrfPlanDtFiasset findOne(@Param("indocno") Long indocno);
	

	/**
	  *  查询计划下明细数量
	 * @param indocno
	 * @return
	 */
	Integer findDetailCountByIndocno(@Param("ilinkno") Long ilinkno);
	

	/**
	  *  通过外键查询所有明细
	 * @param ibillid
	 * @return
	 */
	List<AmVrfPlanDtFiasset> findAllByIbillid(@Param("ilinkno") Long ilinkno);
	

	/**
	  *  通过资产编号查询所有组成部分
	 * @param sparent
	 * @return
	 */
	List<AmVrfPlanDtFiasset> findAllBySparent(@Param("sparentno") String sparentno);
}

