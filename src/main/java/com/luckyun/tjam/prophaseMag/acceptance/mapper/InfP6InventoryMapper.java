package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfP6Inventory;

@Repository
public interface InfP6InventoryMapper extends BaseMapper<InfP6Inventory>{

	/** 通过"移交编号"查询 - P6设备设施初始清册数据 */
	List<InfP6Inventory> findAllByTransfernum(@Param("stransferno") String stransferno);

	/**
	 * 查询待copy到业务表的数据
	 * @return
	 */
	List<InfP6Inventory> findAllByIhascopied();

	/**
	 * 修改copy状态
	 * @param condition
	 * @return
	 */
	int updateIhascopied(@Param("condition")InfP6Inventory condition);
	
	/**
	 * 查询已更新到业务表但是发生了数据变更的接口数据
	 * @return
	 */
	List<InfP6Inventory> findAllByIhascopiedAndIupdateState();
	
	/**
	  *  更新IupdateState为null
	 * @param indocno
	 * @return
	 */
	int updateIupdateState(@Param("indocno") Long indocno);
}
