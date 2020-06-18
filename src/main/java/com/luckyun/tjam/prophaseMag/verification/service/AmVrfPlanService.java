
package com.luckyun.tjam.prophaseMag.verification.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.km.keyhelper.IDGenerate;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceSpareMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmAcceptanceFiassetService;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmAcceptanceSpareService;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtSpareMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;

@Service
public class AmVrfPlanService extends BaseService<AmVrfPlan>{

	@Autowired
	private AmVrfPlanMapper amVrfPlanMapper;
	
	@Override
	public BaseMapper<AmVrfPlan> getMapper() {
		return amVrfPlanMapper;
	}

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;
	
	@Autowired
	private BaseSysDeptProvider baseSysDeptProvider;
	
	@Autowired
	private AmVrfPlanDtFiassetMapper amVrfPlanDtFiassetMapper;
	
	@Autowired
	private AmVrfPlanDtSpareMapper amVrfPlanDtSpareMapper;
	
	@Autowired
	private BaseSysUserProvider baseSysUserProvider;
	
	@Autowired
	private AmAcceptanceFiassetService amAcceptanceFiassetService;

	@Autowired
	private AmAcceptanceSpareMapper amAcceptanceSpareMapper;
	
	@Autowired
	private AmAcceptanceSpareService amAcceptanceSpareService;

	@Autowired
	private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

	/**
	 * 条件查询所有
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmVrfPlan> findAll(AmVrfPlanParam condition){
		List<AmVrfPlan> list = this.amVrfPlanMapper.findAll(condition);
		for(AmVrfPlan e : list) {
			
			// 计划状态
			if(null != e.getIplanstate() && !"".equals(e.getIplanstate())) {
				e.setSreleasetype(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IRELEASETYPE,
						e.getIplanstate() != null ? e.getIplanstate() : ""));
			}
			
			// 核验对象
			if(null != e.getIvrfobjid() && !"".equals(e.getIvrfobjid())) {
				e.setSverificationobj(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IVERIFICATIONOBJID,
						e.getIvrfobjid() != null ? e.getIvrfobjid() : ""));
			}
			
			// 所属线路
			if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
			
			// 核验部门
			if(null != e.getIvrfdeptid() && !"".equals(e.getIvrfdeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIvrfdeptid());
				if(null != sysDept) {
					e.setSdeptnm(sysDept.getSname());
				}
			}
			
			// 资产数量
			// 如果为0，取设备设施，如果为1，取备品备件
			if(0 == e.getIplantype()) {
				Integer detailCount = this.amVrfPlanDtFiassetMapper.findDetailCountByIndocno(e.getIndocno());
				if(null != detailCount && 0 != detailCount) {
					e.setIqty(detailCount);
				}
			}else if(1 == e.getIplantype()) {
				  Integer detailCount = this.amVrfPlanDtSpareMapper.findDetailCountByIndocno(e.getIndocno());
				  if(null != detailCount && 0 != detailCount) {
					  e.setIqty(detailCount);
				  }
			}
			
			// 创建部门
			if(null != e.getIcreatedeptid() && !"".equals(e.getIcreatedeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIcreatedeptid());
				if(null != sysDept) {
					e.setScreatedeptnm(sysDept.getSname());
				}
			}
			
			// 创建人
			if(null != e.getSregid() && !"".equals(e.getSregid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
				e.setSregnm(sysAccount.getSname());
			}
		}
		return list;
	}
	

	/**
	 * 查询明细
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public AmVrfPlan findOne(Long indocno) {
		AmVrfPlan info = this.amVrfPlanMapper.findOne(indocno);
		
		// 计划状态
		if (null != info.getIplanstate() && !"".equals(info.getIplanstate())) {
			info.setSreleasetype(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IRELEASETYPE,
					info.getIplanstate() != null ? info.getIplanstate() : ""));
		}

		// 核验对象
		if (null != info.getIvrfobjid() && !"".equals(info.getIvrfobjid())) {
			info.setSverificationobj(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IVERIFICATIONOBJID,
							info.getIvrfobjid() != null ? info.getIvrfobjid() : ""));
		}

		// 所属线路
		if (null != info.getIlineid() && !"".equals(info.getIlineid())) {
			info.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
					info.getIlineid() != null ? info.getIlineid() : ""));
		}
		
		// 核验部门
		if (null != info.getIvrfdeptid() && !"".equals(info.getIvrfdeptid())) {
			SysDept sysDept = this.baseSysDeptProvider.findByIndocno(info.getIvrfdeptid());
			if (null != sysDept) {
				info.setSdeptnm(sysDept.getSname());
			}
		}
		
		// 创建部门
		if(null != info.getIcreatedeptid() && !"".equals(info.getIcreatedeptid())) {
			SysDept sysDept = this.baseSysDeptProvider.findByIndocno(info.getIcreatedeptid());
			if(null != sysDept) {
				info.setScreatedeptnm(sysDept.getSname());
			}
		}
		
		// 创建人
		if(null != info.getSregid() && !"".equals(info.getSregid())) {
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(info.getSregid());
			if(null != sysAccount) {
				info.setSregnm(sysAccount.getSname());
			}
		}
		
		// 如果为0，取设备设施，如果为1，取备品备件
		/*if(0 == info.getIplantype()) {
			List<AmVrfPlanDtFiasset> equipList = this.amVrfPlanDtFiassetMapper.findAllByIbillid(info.getIndocno());
			if(null != equipList && !equipList.isEmpty()) {
				List<AmVrfPlanDtFiasset> sonEquipList = new ArrayList<AmVrfPlanDtFiasset>();
				for(AmVrfPlanDtFiasset e : equipList) {
					List<AmVrfPlanDtFiasset> sonList = this.amVrfPlanDtFiassetMapper.findAllBySparent(e.getSfcode());
					if(null != sonList && !sonList.isEmpty()) {
						sonEquipList.addAll(sonList);
					}
				}
				translateEquip(sonEquipList);
				info.setSonEquipList(sonEquipList);
			}
			translateEquip(equipList);
			info.setEquipList(equipList);
		}else if(1 == info.getIplantype()) {
			List<AmVrfPlanDtSpare> spareList = this.amVrfPlanDtSpareMapper.findAllByIbillid(info.getIndocno());
			if(null != spareList && !spareList.isEmpty()) {
				translateSpare(spareList);
				info.setSpareList(spareList);
			}
		}*/
		
		return info;
	}
	

	/**
	  *  设备设施数据字典翻译
	 * @param equipList
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void translateEquip(List<AmVrfPlanDtFiasset> equipList) {
		if(null != equipList && !equipList.isEmpty()) {
			for(AmVrfPlanDtFiasset e : equipList) {
				//  接收状态
				if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
					e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
							e.getIstrans() != null ? e.getIstrans() : ""));
				}
			}
		}
	}
	

	/**
	  *  备品备件数据字典翻译
	 * @param equipList
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void translateSpare(List<AmVrfPlanDtSpare> spareList) {
		if(null != spareList && !spareList.isEmpty()) {
			for(AmVrfPlanDtSpare e : spareList) {
				//  接收状态
				if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
					e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
							e.getIstrans() != null ? e.getIstrans() : ""));
				}
			}
		}
	}
	

	/**
	 * 修改
	 * @param entity
	 */
	@TransactionalException
	public void updatePlan(AmVrfPlan entity) {
		
		super.update(entity);
		
		/*Integer iplantype = entity.getIplantype();
		if(null != iplantype && !"".equals(iplantype)) {
			if(0 == iplantype) {	// 操作设备设施核验计划明细
				if(null != entity.getDelEquipLists() && !entity.getDelEquipLists().isEmpty()) {
					handleDelEquipLists(entity);
				}
				if(null != entity.getAddEquipLists() && !entity.getAddEquipLists().isEmpty()) {
					handleAddEquipLists(entity);
				}
			}else if(1 == iplantype) {	// 操作备品备件核验计划明细
				if(null != entity.getDelSpareLists() && !entity.getDelSpareLists().isEmpty()) {
					handleDelSpareLists(entity);
				}
				if(null != entity.getAddSpareLists() && !entity.getAddSpareLists().isEmpty()) {
					handleAddSpareLists(entity);
				}
			}
		}*/
	}
	

	/**
	 * 新增设备设施核验计划明细(生成计划明细，包括组成部分一并带到核验计划明细表中)
	 * @param addEquipLists
	 */
	@SuppressWarnings("unlikely-arg-type")
	@TransactionalException
	public void handleAddEquipLists(AmVrfPlan entity) {
		List<AmAcceptanceFiasset> addEquipLists = entity.getAddEquipLists();
		if(null != addEquipLists && !addEquipLists.isEmpty()) {
			for(AmAcceptanceFiasset e : addEquipLists) {
				AmAcceptanceFiasset info = this.amAcceptanceFiassetMapper.findOne(e.getIndocno());
				if(null != info) {
					List<AmAcceptanceFiasset> sonEquipList = this.amAcceptanceFiassetMapper.findSonByIparent(info.getSfcode());
					if(null != sonEquipList && !sonEquipList.isEmpty()) {
						if(null != sonEquipList && !sonEquipList.isEmpty()) {
							for(AmAcceptanceFiasset sonInfo : sonEquipList) {

								if(null != sonInfo.getIdatastate() && !"".equals(sonInfo.getIdatastate()) && 1 == sonInfo.getIdatastate()) {
									continue;
								}

								this.amAcceptanceFiassetService.generateSonData(sonInfo,null);
							}
						}
					}
					if(null != info.getIdatastate() && !"".equals(info.getIdatastate()) && 1 == info.getIdatastate()) {
						continue;
					}
					this.amAcceptanceFiassetService.generateSonData(info,entity.getIndocno());
				}
			}
		}
	}
	

	/**
	 * 删除设备设施核验计划明细(包括组成部分一并删除)
	 * @param addEquipLists
	 */
	public void handleDelEquipLists(AmVrfPlan entity) {
		List<AmVrfPlanDtFiasset> delEquipLists = entity.getDelEquipLists();
		if(null != delEquipLists && !delEquipLists.isEmpty()) {
			for(AmVrfPlanDtFiasset e : delEquipLists) {
				AmVrfPlanDtFiasset info = this.amVrfPlanDtFiassetMapper.findOne(e.getIndocno());
				if(null != info) {
					List<AmVrfPlanDtFiasset> sonEquipList = this.amVrfPlanDtFiassetMapper.findAllBySparent(info.getSfcode());
					if(null != sonEquipList && !sonEquipList.isEmpty()) {
						delSonEquip(sonEquipList);
						AmAcceptanceFiasset equipInfo = this.amAcceptanceFiassetMapper.findOne(info.getIamaccfiassetid());
						if(null != equipInfo) {
							updateOriginalDate(equipInfo);
						}
					}
					
					AmAcceptanceFiasset equipInfo = this.amAcceptanceFiassetMapper.findOne(info.getIamaccfiassetid());
					
					if(null != equipInfo) {
						equipInfo.setIdatastate(0);
						this.amAcceptanceFiassetMapper.update(equipInfo);
					}
					this.amVrfPlanDtFiassetMapper.delete(e);
				}
			}
		}
	}
	

	/**
	 * 删除备品备件核验计划明细
	 * @param 
	 */
	public void handleDelSpareLists(AmVrfPlan entity) {
		List<AmVrfPlanDtSpare> delSpareLists = entity.getDelSpareLists();
		if(null != delSpareLists && !delSpareLists.isEmpty()) {
			for(AmVrfPlanDtSpare e : delSpareLists) {
				AmVrfPlanDtSpare info = this.amVrfPlanDtSpareMapper.findOne(e.getIndocno());
				if(null != info.getSspareno() && !"".equals(info.getSspareno())) {
					
					AmAcceptanceSpare originalInfo = this.amAcceptanceSpareMapper.findOne(info.getIamaccspareid());
					
					if(null != originalInfo) {
						originalInfo.setIdatastate(0);
						this.amAcceptanceSpareMapper.update(originalInfo);
					}
				}
				this.amVrfPlanDtSpareMapper.delete(e);
			}
		}
	}
	

	/**
	 * 新增设备设施核验计划明细
	 * @param addEquipLists
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void handleAddSpareLists(AmVrfPlan entity) {
		List<AmAcceptanceSpare> addSpareLists = entity.getAddSpareLists();
		if(null != addSpareLists && !addSpareLists.isEmpty()) {
			for(AmAcceptanceSpare e : addSpareLists) {
				AmAcceptanceSpare info = this.amAcceptanceSpareMapper.findOne(e.getIndocno());
				if(null != info) {
					if(null != info.getIdatastate() && !"".equals(info.getIdatastate()) && 1 == info.getIdatastate()) {
						continue;
					}
				}
				this.amAcceptanceSpareService.generateSonData(info, entity.getIndocno());
			}
		}
	}
	

	/**
	 * 批量完结操作
	 * 除了需要修改核验计划的状态以外，
	 * 	同时需要将计划下面的核验计划明细对应的实物资产的状态 + 移交描述(移交描述字段仅更新‘实物资产维护数据’即可)更新掉(与对应的核验计划明细的“核验状态”保持一致即可)
	 * @param condition
	 */
	@SuppressWarnings("unlikely-arg-type")
	@TransactionalException
	public void finishOpr(AmVrfPlanParam condition) {
		List<AmVrfPlan> finishList = condition.getFinishList();
		if(null != finishList && !finishList.isEmpty()) {
			for(AmVrfPlan e : finishList) {
				e.setIplanstate(2);
				this.update(e);
				
				// 更新资产维护数据的"核验状态"
				AmVrfPlan info = this.amVrfPlanMapper.findIplantypeById(e.getIndocno());
				if(null != info && null != info.getIplantype() && !"".equals(info.getIplantype())) {
					if(0 == info.getIplantype()) {
						List<AmVrfPlanDtFiasset> detailList = this.amVrfPlanDtFiassetMapper.findAllByIbillid(e.getIndocno());
						if(null != detailList && !detailList.isEmpty()) {
							for(AmVrfPlanDtFiasset e1 : detailList) {
								if(null != e1.getSfcode() && !"".equals(e1.getSfcode())) {
									AmAcceptanceFiasset originalInfo = this.amAcceptanceFiassetMapper.findOne(e1.getIamaccfiassetid());
									if(null != originalInfo) {
										originalInfo.setIstrans(e1.getIstrans());
										originalInfo.setSdescribe(e1.getSdescribe());
										this.amAcceptanceFiassetMapper.update(originalInfo);
									}
									List<AmVrfPlanDtFiasset> sonEquipList = this.amVrfPlanDtFiassetMapper.findAllBySparent(e1.getSfcode());
									if(null != sonEquipList && !sonEquipList.isEmpty()) {
										for(AmVrfPlanDtFiasset e2 : sonEquipList) {
											AmAcceptanceFiasset sonOriginalInfo = this.amAcceptanceFiassetMapper.findOne(e1.getIamaccfiassetid());
											if(null != sonOriginalInfo) {
												sonOriginalInfo.setIstrans(e2.getIstrans());
												sonOriginalInfo.setSdescribe(e2.getSdescribe());
												this.amAcceptanceFiassetMapper.update(sonOriginalInfo);
											}
										}
									}
								}
							}
						}
					}else if(1 == info.getIplantype()) {
						List<AmVrfPlanDtSpare> spareList = this.amVrfPlanDtSpareMapper.findAllByIbillid(e.getIndocno());
						if(null != spareList && !spareList.isEmpty()) {
							for(AmVrfPlanDtSpare e1 : spareList) {
								
								AmAcceptanceSpare originalInfo = this.amAcceptanceSpareMapper.findOne(e1.getIamaccspareid());
								
								if(null != originalInfo) {
									originalInfo.setIstrans(e1.getIstrans());
									this.amAcceptanceSpareMapper.update(originalInfo);
								}
							}
						}
					}
				}
			}
		}
	}
	

	/**
	 * 批量删除操作
	 * @param condition
	 */
	@SuppressWarnings("unlikely-arg-type")
	@TransactionalException
	public void delOpr(AmVrfPlanParam condition) {
		List<AmVrfPlan> delList = condition.getDelList();
		if(null != delList && !delList.isEmpty()) {
			if(null != condition.getIplantype() && !"".equals(condition.getIplantype())) {
				if(0 == condition.getIplantype()) {
					for(AmVrfPlan e : delList) {
						List<AmVrfPlanDtFiasset> equipList = this.amVrfPlanDtFiassetMapper.findAllByIbillid(e.getIndocno());
						if(null != equipList && !equipList.isEmpty()) {
							delEquip(equipList);
						}
						super.delete(e);
					}
				}else if(1 == condition.getIplantype()) {
					for(AmVrfPlan e : delList) {
						List<AmVrfPlanDtSpare> spareList = this.amVrfPlanDtSpareMapper.findAllByIbillid(e.getIndocno());
						if(null != spareList && !spareList.isEmpty()) {
							delSpare(spareList);
						}
						super.delete(e);
					}
				}
			}
		}
	}
	

	/**
	  *  删除备品备件核验计划明细
	 * @param spareList
	 */
	private void delSpare(List<AmVrfPlanDtSpare> spareList) {
		for(AmVrfPlanDtSpare e : spareList) {
			AmVrfPlanDtSpare info = this.amVrfPlanDtSpareMapper.findOne(e.getIndocno());
			if(null != info.getSspareno() && !"".equals(info.getSspareno())) {

				AmAcceptanceSpare originalInfo = this.amAcceptanceSpareMapper.findOne(info.getIamaccspareid());
				
				if(null != originalInfo) {
					originalInfo.setIdatastate(0);
					this.amAcceptanceSpareMapper.update(originalInfo);
				}
			}
			this.amVrfPlanDtSpareMapper.delete(e);
		}
	}
	

	/**
	 * 删除设备设施明细
	 * @param equipList
	 */
	private void delEquip(List<AmVrfPlanDtFiasset> equipList) {
		for(AmVrfPlanDtFiasset e : equipList) {
			List<AmVrfPlanDtFiasset> sonEquipList = this.amVrfPlanDtFiassetMapper.findAllBySparent(e.getSfcode());
			if(null != sonEquipList && !sonEquipList.isEmpty()) {
				delSonEquip(sonEquipList);
				AmAcceptanceFiasset equipInfo = this.amAcceptanceFiassetMapper.findOne(e.getIamaccfiassetid());
				
				if(null != equipInfo) {
					updateOriginalDate(equipInfo);
				}
			}
			AmAcceptanceFiasset originalInfo = this.amAcceptanceFiassetMapper.findOne(e.getIamaccfiassetid());
			
			if(null != originalInfo) {
				originalInfo.setIdatastate(0);
				this.amAcceptanceFiassetMapper.update(originalInfo);
			}
			this.amVrfPlanDtFiassetMapper.delete(e);
		}
	}
	

	/**
	 * 删除设备设施明细的组成部分
	 * @param equipList
	 */
	private void delSonEquip(List<AmVrfPlanDtFiasset> sonEquipList) {
		for (AmVrfPlanDtFiasset e : sonEquipList) {
			AmAcceptanceFiasset equipInfo = this.amAcceptanceFiassetMapper.findOne(e.getIamaccfiassetid());
			if(null != equipInfo) {
				updateOriginalDate(equipInfo);
			}
			
			this.amVrfPlanDtFiassetMapper.delete(e);
		}
	}
	

	/**
	 * 反更新来源数据(设备设施、备品备件资产维护数据 = 更新为未生成核验计划)
	 * @param 
	 */
	private void updateOriginalDate(AmAcceptanceFiasset equipInfo) {
			equipInfo.setIdatastate(0);
			this.amAcceptanceFiassetMapper.update(equipInfo);
	}
	

	/**
	 * 新增
	 * @param entity
	 */
	@TransactionalException
	public AmVrfPlan add(AmVrfPlan entity) {
		
		Long indocno = IDGenerate.getKey(entity);
		entity.setIndocno(indocno);
		entity.setIgeneratetype(1);	// 核验计划生成方式 - 1：自增
		entity.setIplantype(entity.getIplantype());		// 计划类型(0.设备设施计划;1.备品备件计划)
		entity.setIdel(0);
		entity.setSregid(getAuthInfo().getIndocno());
		entity.setDregt(new Date());
		
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		entity.setIvrfyear(curYear);
		
		SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno());
		if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
			entity.setIcreatedeptid(sysAccount.getSysUserInfo().getIdeptid());		// 创建部门
		}
		
		super.insert(entity);
		
		// 新增核验计划明细
		// handleDetail(entity);
		return entity;
	}
	

/**
	 * 新增明细
	 * @param entity
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void handleDetail(AmVrfPlan entity) {
		if(null != entity.getIplantype() && !"".equals(entity.getIplantype())) {
			if(0 == entity.getIplantype()) handleAddEquipLists(entity);
				
			if(1 == entity.getIplantype()) handleSpareDetail(entity);
		}
	}
	

	/**
	 * 新增备品备件明细
	 * @param entity
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void handleSpareDetail(AmVrfPlan entity) {
		
		List<AmAcceptanceSpare> addSpareLists = entity.getAddSpareLists();
		if(null != addSpareLists && !addSpareLists.isEmpty()) {
			for(AmAcceptanceSpare e : addSpareLists) {
				AmAcceptanceSpare info = this.amAcceptanceSpareMapper.findOne(e.getIndocno());
				if(null != info) {
					if(null != info.getIdatastate() && !"".equals(info.getIdatastate()) && 1 == info.getIdatastate()) {
						continue;
					}
				}
				this.amAcceptanceSpareService.generateSonData(info, entity.getIndocno());
			}
		}
	}

	@TransactionalException
	public void alreadyissued(AmVrfPlanParam condition) {
		List<AmVrfPlan> finishList = condition.getDelList();
		if(null != finishList && !finishList.isEmpty()) {
			for(AmVrfPlan e : finishList) {
				e.setIplanstate(1);
				this.update(e);

				// 更新资产维护数据的"核验状态"
				AmVrfPlan info = this.amVrfPlanMapper.findOne(e.getIndocno());
				if(null != info && null != info.getIplantype() && !"".equals(info.getIplantype())) {
					if(0 == info.getIplantype()) {
						List<AmVrfPlanDtFiasset> detailList = this.amVrfPlanDtFiassetMapper.findAllByIbillid(e.getIndocno());
						if(null != detailList && !detailList.isEmpty()) {
							for(AmVrfPlanDtFiasset e1 : detailList) {
								if(null != e1.getSfcode() && !"".equals(e1.getSfcode())) {
									AmAcceptanceFiasset originalInfo = this.amAcceptanceFiassetMapper.findOne(e1.getIamaccfiassetid());
									if(null != originalInfo) {
										originalInfo.setIstrans(e1.getIstrans());
										originalInfo.setSdescribe(e1.getSdescribe());
										this.amAcceptanceFiassetMapper.update(originalInfo);
									}
									List<AmVrfPlanDtFiasset> sonEquipList = this.amVrfPlanDtFiassetMapper.findAllBySparent(e1.getSfcode());
									if(null != sonEquipList && !sonEquipList.isEmpty()) {
										for(AmVrfPlanDtFiasset e2 : sonEquipList) {
											AmAcceptanceFiasset sonOriginalInfo = this.amAcceptanceFiassetMapper.findOne(e1.getIamaccfiassetid());
											if(null != sonOriginalInfo) {
												sonOriginalInfo.setIstrans(e2.getIstrans());
												sonOriginalInfo.setSdescribe(e2.getSdescribe());
												this.amAcceptanceFiassetMapper.update(sonOriginalInfo);
											}
										}
									}
								}
							}
						}
					}else if(1 == info.getIplantype()) {
						if(1 == info.getIplantype()) {
							List<AmVrfPlanDtSpare> spareList = this.amVrfPlanDtSpareMapper.findAllByIbillid(e.getIndocno());
							if(null != spareList && !spareList.isEmpty()) {
								for(AmVrfPlanDtSpare e1 : spareList) {

									AmAcceptanceSpare originalInfo = this.amAcceptanceSpareMapper.findOne(e1.getIamaccspareid());

									if(null != originalInfo) {
										originalInfo.setIstrans(e1.getIstrans());
										this.amAcceptanceSpareMapper.update(originalInfo);
									}
								}
							}
						}
					}
				}
			}
		}
	}


}

