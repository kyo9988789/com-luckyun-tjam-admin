package com.luckyun.tjam.prophaseMag.verification.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWaitAppmtMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWaitAppmt;
import com.luckyun.tjam.prophaseMag.verification.param.AmWaitAppmtParam;

@Service
public class AmWaitAppmtService extends BaseService<AmWaitAppmt> {

    @Autowired
    private AmWaitAppmtMapper amWaitAppmtMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Override
    public BaseMapper<AmWaitAppmt> getMapper() {
        return amWaitAppmtMapper;
    }

    /**
     *  条件查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmWaitAppmt> findAll (AmWaitAppmtParam condition){
        List<AmWaitAppmt> list = amWaitAppmtMapper.findAll(condition);
        for (AmWaitAppmt e:list){
            // 处理状态(就是分摊状态)
            if(null != e.getIdealstatus() && !"".equals(e.getIdealstatus())) {
                e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_WAIT_APPMT_IDEALSTATUS,
                        e.getIdealstatus() != null ? e.getIdealstatus() : ""));
            }
            // 所属线路
            if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            //来源
            if(null !=e.getIsource() && !"".equals(e.getIsource())) {
                e.setSsourcenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_ISOURCE,
                        e.getIsource() !=null? e.getIsource() : ""));
            }
            // 创建部门
            if(null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                if(null != sysDept) {
                    e.setSdeptnm(sysDept.getSname());
                }
            }
            // 创建人
 			if(null != e.getSregid() && !"".equals(e.getSregid())) {
 				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
 				if(null != sysAccount) e.setSregnm(sysAccount.getSname());
 			}
        }
        return list;
    }

    /**
     * 查询未被资产价值分摊选中的待分摊项：idatastate != 1
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmWaitAppmt> readAllHasNotChoiced(){
    	List<AmWaitAppmt> list = this.amWaitAppmtMapper.readAllHasNotChoiced();
    	for (AmWaitAppmt e : list){
            // 处理状态(就是分摊状态)
            if(null != e.getIdealstatus() && !"".equals(e.getIdealstatus())) {
                e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_WAIT_APPMT_IDEALSTATUS,
                        e.getIdealstatus() != null ? e.getIdealstatus() : ""));
            }
            // 所属线路
            if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            //来源
            if(null !=e.getIsource() && !"".equals(e.getIsource())) {
                e.setSsourcenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_ISOURCE,
                        e.getIsource() !=null? e.getIsource() : ""));
            }
            // 创建部门
            if(null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                if(null != sysDept) {
                    e.setSdeptnm(sysDept.getSname());
                }
            }
            // 创建人
 			if(null != e.getSregid() && !"".equals(e.getSregid())) {
 				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
 				if(null != sysAccount) e.setSregnm(sysAccount.getSname());
 			}
        }
    	return list;
    }
    
    //批量删除
    @TransactionalException
    public void delOpr(AmWaitAppmtParam condition){
        List<AmWaitAppmt> delList = condition.getDelList();
        if(null != delList && !delList.isEmpty()) {
            for (AmWaitAppmt e:delList){
                super.delete(e);
            }
        }
    }

    /**
     * 查询明细
     * */
    @SuppressWarnings("unlikely-arg-type")
	public AmWaitAppmt findOne(Long indocno){
        AmWaitAppmt e = amWaitAppmtMapper.findOne(indocno);
        // 处理状态(就是分摊状态)
        if(null != e.getIdealstatus() && !"".equals(e.getIdealstatus())) {
            e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_WAIT_APPMT_IDEALSTATUS,
                    e.getIdealstatus() != null ? e.getIdealstatus() : ""));
        }

        // 所属线路
        if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
            e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                    e.getIlineid() != null ? e.getIlineid() : ""));
        }

        //来源
        if(null !=e.getIsource() && !"".equals(e.getIsource())) {
            e.setSsourcenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_ISOURCE,
                    e.getIsource() !=null? e.getIsource() : ""));
        }

        // 创建部门
        if(null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
            SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
            if(null != sysDept) {
                e.setSdeptnm(sysDept.getSname());
            }
        }
        return e;
    }

    /**
     * 新增
     * @param entity
     */

    @TransactionalException
    public void add(AmWaitAppmt entity){
        AuthInfo authInfo = super.getAuthInfo();
        String sappmtno = generateSappmtno();
        entity.setSappmtno(sappmtno);
        Long indocno = IDGenerate.getKey(entity);
        entity.setIndocno(indocno);
        entity.setIdealstatus(0);
        entity.setIdel(0);
        entity.setSregid(authInfo.getIndocno());
        entity.setDregt(new Date());
        SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(authInfo.getIndocno());
        if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
            entity.setIdeptid(sysAccount.getSysUserInfo().getIdeptid());		// 创建部门
        }
        super.insert(entity);
    }

    /**
     * 修改
     * */

    @TransactionalException
    public void updateAm(AmWaitAppmt entity){
        super.update(entity);
    }

    /**
     *   生成编号
     *  @return
     */
    private String generateSappmtno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String sappmtno = "DFT-" + date;
        AmWaitAppmtParam condition = new AmWaitAppmtParam();
        condition.setSappmtno(sappmtno);
        List<AmWaitAppmt> list = this.amWaitAppmtMapper.findMaxSappmtno(condition);
        if(null == list || list.size() == 0) {
            sappmtno = sappmtno + "-" + "001";
        }else if(null != list && list.size() > 0){
            AmWaitAppmt maxPlanInfo = list.get(0);
            String maxSappmtno = maxPlanInfo.getSappmtno();
            String lastString = maxSappmtno.substring(maxSappmtno.lastIndexOf("-")+1,maxSappmtno.length());
            Integer lastInteger = Integer.valueOf(lastString) + 1;
            if(lastInteger.toString().length() == 1) {
                sappmtno = sappmtno + "-00" + lastInteger;
            }else if(lastInteger.toString().length() == 2) {
                sappmtno = sappmtno + "-0" + lastInteger;
            }else if(lastInteger.toString().length() == 3) {
                sappmtno = sappmtno + "-" + lastInteger;
            }
        }
        return sappmtno;
    }
}
