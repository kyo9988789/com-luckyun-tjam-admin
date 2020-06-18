package com.luckyun.tjam.prophaseMag.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;

@Service
public class AmArcClassService extends BaseService<AmArcClass>{

	@Autowired
	private AmArcClassMapper amArcClassMapper;

	@Override
	public BaseMapper<AmArcClass> getMapper() {
		return amArcClassMapper;
	}

	/**
	 * 查询分类树某个节点下面的所有节点
	 * @param condition
	 * @return
	 */
	public List<AmArcClass> findAllByIparentid(Long iparentid){
		List<AmArcClass> capArcClassList =this.amArcClassMapper.findAllByIparentid(iparentid);
		for(AmArcClass e : capArcClassList) {
			Integer hasChildren = this.amArcClassMapper.findSonByIndocno(e.getIndocno());
			e.setHasChildren(hasChildren == 0 ? 0 : 1);
		}
		return capArcClassList;
	}

	/**
	 * 	查询所有分类树节点
	 * 	@param condition
	 *	@return
	 */
	public List<AmArcClass> findAll(){
		List<AmArcClass> capArcClassList = this.amArcClassMapper.findAll();
		for(AmArcClass e : capArcClassList) {
			Integer hasChildren = this.amArcClassMapper.findSonByIndocno(e.getIndocno());
			e.setHasChildren(hasChildren == 0 ? 0 : 1);
		}
		return capArcClassList;
	}

	/**
	 *  生成父子节点关系：sidcc，方便通过节点过滤列表数据
	 */
	@TransactionalException
	public void generateSidcc() {
		List<AmArcClass> allData = this.amArcClassMapper.findAllData();
		int i = 0;
		for(AmArcClass e : allData) {
			if(null != e.getSparentno() && !"".equals(e.getSparentno())) {
				String sidcc = e.getSclassno();
				String finalSidcc = recursionCc(e.getSparentno(),sidcc);
				e.setSidcc(finalSidcc);
				super.update(e);
				System.out.println("-----------------当前执行次数：" + i++ + "--------------------" + finalSidcc);
			}
		}
	};

	/**
	 *  递归查询父类sclassno
	 */
	private String recursionCc(String supperno,String sidcc) {
		List<AmArcClass> list = this.amArcClassMapper.findBySclassno(supperno);
		if(null != list && !list.isEmpty()) {
			AmArcClass parentInfo = list.get(0);
			if(null != parentInfo && null != parentInfo.getSclassno() && !"".equals(parentInfo.getSclassno())) {
				sidcc = parentInfo.getSclassno() + "/" + sidcc;
				if(null != parentInfo.getSparentno() && !"".equals(parentInfo.getSparentno())) {
					sidcc = recursionCc(parentInfo.getSparentno(),sidcc);
				}
			}
		}
		return sidcc;
	}

	/**
	 * 	查询所有分类树节点
	 * 	@param condition
	 *	@return
	 */
	public List<AmArcClass> findAllDatas(){
		List<AmArcClass> capArcClassList = this.amArcClassMapper.findAllDatas();
		return capArcClassList;
	}

	/**
	 * 	通过iamarcclassid查询sidcc
	 * 	@param condition
	 *	@return
	 */
	public String findByIn(Long iamarcclassid){
		return this.amArcClassMapper.findByIn(iamarcclassid);
	}

}
