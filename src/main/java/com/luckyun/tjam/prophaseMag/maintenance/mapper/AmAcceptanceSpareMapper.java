
package com.luckyun.tjam.prophaseMag.maintenance.mapper;

import java.util.List;

import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceSpareParam;

@Repository
public interface AmAcceptanceSpareMapper extends BaseMapper<AmAcceptanceSpare>{

	/**
	  *  查询所有备品备件资产
	 * @param condition
	 * @return
	 */
	List<AmAcceptanceSpare> findAll(@Param("condition") AmAcceptanceSpareParam condition);
	

	/**
	  *  通过主键查询详情
	 * @param indocno
	 * @return
	 */
	AmAcceptanceSpare findOne(@Param("indocno") Long indocno);
	
	/**
	  *  查询所有可生成核验计划的备品备件资产
	 * @param condition
	 * @return
	 */
	List<AmAcceptanceSpare> findAllHasNotGreVerification(@Param("condition") AmAcceptanceSpareParam condition);
	

	/**
 	 * 通过备品备件编号查询数据
	 * @param sassetnum
	 * @return
	 */
	List<AmAcceptanceSpare> findBySspareno(@Param("sspareno") String sspareno);

	Long findByDregt();
	
	/**
	  *  通过接口主键查询备品备件数据
	 * @param isourceid
	 * @return
	 */
	AmAcceptanceSpare findByIsourceid(@Param("isourceid") Long isourceid);
	
	void updateIupdateState(@Param("indocno") Long indocno);
}

