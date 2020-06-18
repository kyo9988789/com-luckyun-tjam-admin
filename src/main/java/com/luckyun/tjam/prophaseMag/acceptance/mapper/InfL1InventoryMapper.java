package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1Inventory;

@Repository
public interface InfL1InventoryMapper extends BaseMapper<InfL1Inventory>{

	/** 通过"资产编号"查询L1设备设施登记明细表数据 */
	List<InfL1Inventory> findAllByAssetnum(@Param("sfcode") String sfcode);

	/**
	 * 查询设备设施编号
	 * @return
	 */
	InfL1Inventory findByAssetnum(@Param("sfcode")String sfcode);

	/**
	 * 修改copy状态
	 * @param condition
	 * @return
	 */
	int updateIhascopied(@Param("condition")InfL1Inventory condition);
	
	/**
	  *  通过计划移交编号查询设备设施登记明细表数据
	 * @param stransferno
	 * @return
	 */
	List<InfL1Inventory> findAllByTransfernum(@Param("stransferno") String stransferno);
	
	/**
	  *  更新IupdateState为null
	 * @param indocno
	 * @return
	 */
	int updateIupdateState(@Param("indocno") Long indocno);
}
