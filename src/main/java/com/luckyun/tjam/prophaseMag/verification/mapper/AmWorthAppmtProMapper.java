package com.luckyun.tjam.prophaseMag.verification.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtProParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmWorthAppmtProMapper extends BaseMapper<AmWorthAppmtPro> {

	/**
	 * 查询所有
	 * @param condition
	 * @return
	 */
	List<AmWorthAppmtPro> findAll(@Param("condition") AmWorthAppmtProParam condition);

	/**
	 * 查询明细
	 * @param condition
	 * @return
	 */
	AmWorthAppmtPro findOne(@Param("indocno") Long indocno);

	/**
	 * 通过外键查询所有明细
	 * @param ibillid
	 * @return
	 */
	List<AmWorthAppmtPro> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

}
