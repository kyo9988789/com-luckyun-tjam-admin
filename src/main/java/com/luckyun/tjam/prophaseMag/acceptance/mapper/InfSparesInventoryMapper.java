package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesInventory;

@Repository
public interface InfSparesInventoryMapper extends BaseMapper<InfSparesInventory>{

	/** 通过外键获取所有备品备件明细：ilinkno */
	public List<InfSparesInventory> findAllByInfibillid(@Param("ilinkno") Long ilinkno);

	//待同步数据
	List<InfSparesInventory> findAllByIhascopied();

	/**
	 * 修改copy状态
	 * @param condition
	 * @return
	 */
	int updateIhascopied(@Param("condition")InfSparesInventory condition);
	
	/**
	 * 查询已更新到业务表但是发生了数据变更的接口数据
	 * @return
	 */
	List<InfSparesInventory> findAllByIhascopiedAndIupdateState();
	
	/**
	  *  更新IupdateState为null
	 * @param indocno
	 * @return
	 */
	int updateIupdateState(@Param("indocno") Long indocno);
}
