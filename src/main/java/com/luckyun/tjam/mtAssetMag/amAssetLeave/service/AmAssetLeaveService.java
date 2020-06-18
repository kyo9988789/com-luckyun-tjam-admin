package com.luckyun.tjam.mtAssetMag.amAssetLeave.service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveAttMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeave;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveAtt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class AmAssetLeaveService extends BaseService<AmAssetLeave> {

    @Autowired
    private AmAssetLeaveMapper amAssetLeaveMapper;

    @Autowired
    private AmAssetLeaveDtMapper amAssetLeaveDtMapper;

    @Autowired
    private AmAssetLeaveAttMapper amAssetLeaveAttMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;
    @Override
    public BaseMapper<AmAssetLeave> getMapper() {
        return amAssetLeaveMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetLeave> findAll(AmAssetLeaveParam condition){
        List<AmAssetLeave> all = amAssetLeaveMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
               // int count = amAssetLeaveDtMapper.findCount();
               /* e.setIqty(count);
                if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                    e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
                }*/
                // 所属线路
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 管理部门
                if(null != e.getIdeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                    if(null != sysDept) {
                        e.setSdeptsm(sysDept.getSname());
                    }
                }
                if(null != e.getIleavetypeid()) {
                    e.setSleavetypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IILEAVETYPEID,
                            e.getIleavetypeid() != null ? e.getIleavetypeid() : ""));
                }
            });
        }
        return all;
    }



    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmAssetLeaveParam condition){
        List<AmAssetLeave> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetLeaveMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细表明细
     * @param condition
     * @return
     */
    public List<AmAssetLeaveDt> findOnes(Long indocno){
        List<AmAssetLeaveDt> amTransferPlanDtList = amAssetLeaveDtMapper.findAllByIlinkno(indocno);
        if (!CollectionUtils.isEmpty(amTransferPlanDtList)){
            amTransferPlanDtList.forEach(e->{
                if (e.getIdutyid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid()) != null){
                    e.setSdutynm(baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid()).getSname());
                }
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
            });
        }
        return amTransferPlanDtList;
    }

    /**
     *  查询附件表明细
     * @param condition
     * @return
     */
    public List<AmAssetLeaveAtt> findOneas(Long indocno){
        List<AmAssetLeaveAtt> attList = amAssetLeaveAttMapper.findAllByIlinkno(indocno);
        if(!CollectionUtils.isEmpty(attList)){
            attList.forEach(e->{
                AuthInfo authInfo = getAuthInfo();
                // 图片地址
                if (null != e.getSpath()) {
                    e.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                            , "tjam", e.getSpath(), e.getSname()));
                }
                //上传人
                if(null != e.getSregid()) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
                    e.setSregnm(sysAccount.getSname());
                }
            });
        }
        return attList;
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetLeave findOne(Long indocno){
        AmAssetLeave e = amAssetLeaveMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
          /*  int count = amAssetLeaveDtMapper.findCount();
            e.setIqty(count);
            if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
            }*/
            // 所属线路
            if(null != e.getIlineid()) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 管理部门
            if(null != e.getIdeptid()) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                if(null != sysDept) {
                    e.setSdeptsm(sysDept.getSname());
                }
            }
            if(null != e.getIleavetypeid()) {
                e.setSleavetypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IILEAVETYPEID,
                        e.getIleavetypeid() != null ? e.getIleavetypeid() : ""));
            }
        }
        return e;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmAssetLeave add(AmAssetLeave entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());
        entity.setDregt(new Date());
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetLeave entity) {
        amAssetLeaveMapper.update(entity);
    }

}
