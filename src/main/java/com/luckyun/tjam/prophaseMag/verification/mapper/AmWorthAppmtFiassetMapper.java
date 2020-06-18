package com.luckyun.tjam.prophaseMag.verification.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtFiassetParam;

@Repository
public interface AmWorthAppmtFiassetMapper extends BaseMapper<AmWorthAppmtFiasset> {

	/**
	 * 查询所有
	 * 
	 * @param condition
	 * @return
	 */
	List<AmWorthAppmtFiasset> findAll(@Param("condition") AmWorthAppmtFiassetParam condition);

	/**
	 * 查询明细
	 * 
	 * @param condition
	 * @return
	 */
	AmWorthAppmtFiasset findOne(@Param("indocno") Long indocno);

	/**
	 * 通过外键查询所有明细
	 * 
	 * @param ibillid
	 * @return
	 */
	List<AmWorthAppmtFiasset> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    void updateAppMoney(Long indocno);
}
