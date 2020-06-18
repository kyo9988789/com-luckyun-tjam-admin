package com.luckyun.tjam.prophaseMag.maintenance.mapper;

import java.util.List;

import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceFiassetParam;

@Repository
public interface AmAcceptanceFiassetMapper extends BaseMapper<AmAcceptanceFiasset>{

	/**
	 * 查询所有设备设施资产
	 * @param condition
	 * @return
	 */
	List<AmAcceptanceFiasset> findAll(@Param("condition") AmAcceptanceFiassetParam condition);

	/**
	 * 查询未被引用的主资产数据
	 * @param condition
	 * @return
	 */
	List<AmAcceptanceFiasset> readAllHasNotQuoted();
	
	/**
	 * 通过主键查询详情
	 * @param indocno
	 * @return
	 */
	AmAcceptanceFiasset findOne(@Param("indocno") Long indocno);

	/**
	 * 查找组成部分(son.sparent = sassetnum)
	 * @param sassetnum
	 * @return
	 */
	List<AmAcceptanceFiasset> findSonByIparent(@Param("sfcode") String sfcode);

	/**
	 * 获取除当前条及子资产以外的所有资产编号
	 * @param sassetnum
	 * @return
	 */
	List<AmAcceptanceFiasset> findParentAssetnumIgnoreNeed(@Param("sfcode") String sfcode);

	/**
	 * 查找是否存在外部系统主键
	 * @param indocno
	 * @return
	 */
	AmAcceptanceFiasset findPkeyValue(@Param("indocno") Long indocno);

	/**
	 * 通过资产编号查询数据
	 * @param sassetnum
	 * @return
	 */
	List<AmAcceptanceFiasset> findBySassetnum(@Param("sassetnum") String sfcode);

	/**
	 * 查询所有可生成核验计划的设备设施资产
	 * @param condition
	 * @return
	 */
	List<AmAcceptanceFiasset> findAllHasNotGreVerification(@Param("condition") AmAcceptanceFiassetParam condition);

	/**
	 * 查询同意或拒绝的实物资产
	 *
	 * */
	List<AmAcceptanceFiasset> findAllDealtWith();

	/**
	 * 查询同意或拒绝的实物资产(可能需修改)
	 *
	 * */
	List<AmAcceptanceFiasset> findUpAllDealtWith();
	
	/**
	 * 通过接口数据主键查询数据
	 * @param isourceid
	 * @return
	 */
	AmAcceptanceFiasset findByIsourceid(@Param("isourceid") Long isourceid,@Param("isource") int isource);
	
	void updateIupdateState(@Param("indocno") Long indocno);
}
