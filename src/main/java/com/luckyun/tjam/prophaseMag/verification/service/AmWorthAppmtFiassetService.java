package com.luckyun.tjam.prophaseMag.verification.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.km.keyhelper.IDGenerate;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWorthAppmtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtFiassetParam;

@Service
public class AmWorthAppmtFiassetService extends BaseService<AmWorthAppmtFiasset> {

    @Autowired
    private AmWorthAppmtFiassetMapper amWorthAppmtFiassetMapper;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;
    
	@Autowired
	private AmArcClassMapper amArcClassMapper;
	
    @Override
    public BaseMapper<AmWorthAppmtFiasset> getMapper() {
        return amWorthAppmtFiassetMapper;
    }

    /**
     *  条件查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmWorthAppmtFiasset> findAll(AmWorthAppmtFiassetParam condition){
		List<AmWorthAppmtFiasset> list = amWorthAppmtFiassetMapper.findAll(condition);

		for (AmWorthAppmtFiasset e : list) {
			// 管理部门
			if (null != e.getImanagedeptid() && !"".equals(e.getImanagedeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
				if (null != sysDept) {
					e.setSmanagedeptnm(sysDept.getSname());
				}
			}
			// 责任人
			if (null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
				if (null != sysAccount1) {
					e.setSdutynm(sysAccount1.getSname());
				}
			}
			// 所属线路
			if (null != e.getIlineid() && !"".equals(e.getIlineid())) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
		}
		return list;
    }

    
    /**
     * 通过主表查询待分摊项
     */
    public List<AmWorthAppmtFiasset> readAllByIlinkno(Long ilinkno){
    	List<AmWorthAppmtFiasset> awaFiassetList = this.amWorthAppmtFiassetMapper.findAllByIlinkno(ilinkno);
    	awaFiassetList.forEach(e -> {
    		// 管理部门
			if(null != e.getImanagedeptid()) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
				if(null != sysDept) {
					e.setSmanagedeptnm(sysDept.getSname());
				}
			}

			// 责任人
			if(null != e.getIdutyid()) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
				if(null != sysAccount1) {
					e.setSdutynm(sysAccount1.getSname());
				}
			}

			// 所属线路
			if (null != e.getIlineid()) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
			
			// 分类名称
			if(null != e.getSeqclassno() && !"".equals(e.getSeqclassno())) {
				List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
				if(null != capArcClassList && !capArcClassList.isEmpty()) {
					AmArcClass capArcClass = capArcClassList.get(0);
					e.setSclassstructurenm(capArcClass.getSclassnm());
				}
			}
    	});
    	return awaFiassetList;
    }
    
    /**
     * 查询明细
     * */
    public AmWorthAppmtFiasset findOne(Long indocno){
        AmWorthAppmtFiasset e = amWorthAppmtFiassetMapper.findOne(indocno);
        return e;
    }

    /**
     * 批量删除
     * */
    @TransactionalException
    public void del(AmWorthAppmtFiassetParam condition){
        List<AmWorthAppmtFiasset> delList = condition.getDelList();
        if(null != delList && !delList.isEmpty()) {
            for(AmWorthAppmtFiasset e : delList){
            	if(null != e.getIsourceid()) {
					AmAcceptanceFiasset sourceInfo = this.amAcceptanceFiassetMapper.findOne(e.getIsourceid());
					if(null != sourceInfo) {
						sourceInfo.setIquotestate(0);
						this.amAcceptanceFiassetMapper.update(sourceInfo);
					}
				}
                super.delete(e);
            }
        }
    }

    /**
     * 修改
     * */
    @TransactionalException
    public void updateAm(AmWorthAppmtFiasset entity){
        super.update(entity);
    }

    /**
     * 修改分摊金额
     * */
    @TransactionalException
    public AmWorthAppmtFiasset updateIappmttype(AmWorthAppmtFiasset entity){
        super.update(entity);
        return entity;
    }


    /**
     * 新增
     * */
    @TransactionalException
    public void add(AmWorthAppmtFiasset entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());

        entity.setDregt(new Date());
        SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(authInfo.getIndocno());
        if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
            entity.setImanagedeptid(sysAccount.getSysUserInfo().getIdeptid());		// 创建部门
        }
        super.insert(entity);
    }

    /**
     * 新增子表明细
     * */
    @TransactionalException
    public void addAwaFiasset(AmWorthAppmtBatch entity){
    	
    	List<AmWorthAppmtFiasset> fiassetList = entity.getAddFiassetList();
    	fiassetList.forEach(e -> {
    		AuthInfo authInfo = super.getAuthInfo();
            e.setSregid(authInfo.getIndocno());

            e.setDregt(new Date());
            
            SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(authInfo.getIndocno());
            if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
                e.setImanagedeptid(sysAccount.getSysUserInfo().getIdeptid());		// 创建部门
            }
            e.setIsourceid(e.getIndocno());
            e.setIlinkno(entity.getIndocno());
            
            AmAcceptanceFiasset sourceInfo = this.amAcceptanceFiassetMapper.findOne(e.getIndocno());
            if(null != sourceInfo) {
            	sourceInfo.setIquotestate(1);
            	this.amAcceptanceFiassetMapper.update(sourceInfo);
            }
            Long indocno = IDGenerate.getKey(e);
            e.setIndocno(indocno);
            super.insert(e);
    	});
    }

    @Transactional
    public void updateFiasset(AmWorthAppmtFiassetParam entity) {
        List<AmWorthAppmtFiasset> updList = entity.getUpdList();
        updList.forEach(e->{
            if (e.getIappmtmoney() == null){
                amWorthAppmtFiassetMapper.updateAppMoney(e.getIndocno());
            }else {
                amWorthAppmtFiassetMapper.update(e);
            }
        });
    }
}
