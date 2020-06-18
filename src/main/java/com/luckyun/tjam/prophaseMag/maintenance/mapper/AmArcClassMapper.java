
package com.luckyun.tjam.prophaseMag.maintenance.mapper;

import java.util.List;

import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;

@Repository
public interface AmArcClassMapper extends BaseMapper<AmArcClass>{



	/**
	 * 通过id
	 * */

	AmArcClass findByIndocno(@Param("indocno") Long indocno);

/**
	 * 查询分类树某个节点下面的所有节点
	 * @param condition
	 * @return
	 */

	List<AmArcClass> findAllByIparentid(@Param("iparentid") Long iparentid);
	

/**
	  *  查询所有资产分类顶级节点 + 二级节点数据，做初始化
	 * @param condition
	 * @return
	 */

	List<AmArcClass> findAll();
	

/**
	 * 查询所有资产分类数数据
	 * @return
	 */

	List<AmArcClass> findAllDatas();
	

/**
	  *  查找当前节点是否有子节点
	 * @param indocno
	 * @return
	 */

	Integer findSonByIndocno(@Param("indocno") Long indocno);
	

/**
	  *  通过设备资产编号查找对应分类名称
	 * @param sclassno
	 * @return
	 */

	List<AmArcClass> findBySclassno(@Param("sclassno") String sclassno);
	

/**
	  * 通过上级分类编码查找上级数据 
	 * @param supperno
	 * @return
	 */

	AmArcClass findParentBySupperno(@Param("sparentno") String sparentno);
	

/**
	  *  查询所有节点
	 * @param condition
	 * @return
	 */

	List<AmArcClass> findAllData();

	/**
	 *  查询对应节点
	 * @param condition
	 * @return
	 */
	String findByIn(@Param("iamarcclassid") Long iamarcclassid);

	/**
	 *  查询对应分类编码
	 * @param condition
	 * @return
	 */
	String findSclassno(@Param("iamarcclassid") Long iamarcclassid);
}

