package com.luckyun.tjam.mtAssetMag.amTranPlan.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtCompMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlan;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;

@Service
public class AmTransferPlanService extends BaseService<AmTransferPlan> {

    @Autowired
    private AmTransferPlanMapper amTransferPlanMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

    @Autowired
    private AmTransferPlanDtCompMapper amTransferPlanDtCompMapper;

    @Autowired
    private AmTransferPlanDtMapper amTransferPlanDtMapper;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Override
    public BaseMapper<AmTransferPlan> getMapper() {
        return amTransferPlanMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmTransferPlan> findAll(AmTransferPlanParam condition){
        if (null != condition && condition.getIndocnos() != null && condition.getIndocnos() != ""){
            String[] split = condition.getIndocnos().split(",");
            List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
            condition.setIndocnoList(collect);
        }
        List<AmTransferPlan> list = amTransferPlanMapper.findAll(condition);
        if (null != list && !list.isEmpty()){
            for (AmTransferPlan amTransferPlan:list){

                if (amTransferPlan.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(amTransferPlan.getSregid()) != null){
                    amTransferPlan.setSregnm(baseSysUserProvider.findFSysUserByIndocno(amTransferPlan.getSregid()).getSname());
                }

                // 创建部门
                if(null != amTransferPlan.getIcreatedeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amTransferPlan.getIcreatedeptid());
                    if(null != sysDept) {
                        amTransferPlan.setScreatedeptnm(sysDept.getSname());
                    }
                }

                // 所属线路
                if(null != amTransferPlan.getIlineid()) {
                    amTransferPlan.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            amTransferPlan.getIlineid() != null ? amTransferPlan.getIlineid() : ""));
                }
                //下发状态
                if(null != amTransferPlan.getItransferstate()) {
                    amTransferPlan.setStransferstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_ITRANSFERSTATE,
                            amTransferPlan.getItransferstate() != null ? amTransferPlan.getItransferstate() : ""));
                }
                //下发至
                if(null != amTransferPlan.getItransferto()) {
                    amTransferPlan.setStransfertonm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_ITRANSFERTO,
                            amTransferPlan.getItransferto() != null ? amTransferPlan.getItransferto() : ""));
                }
                /*List<AmTransferPlanDt> amTransferPlanDtList = amTransferPlanDtMapper.findAllByIlinkno(condition.getIndocno());
                List<AmTransferPlanDtComp> all = amTransferPlanDtCompMapper.findAllByIlinkno(condition.getIndocno());
                amTransferPlan.setAmTransferPlanDtList(amTransferPlanDtList);
                amTransferPlan.setAmTransferPlanDtCompList(all);*/
            }
        }
        return list;
    }

    /**
     *  查询明细表明细
     * @param condition
     * @return
     */
    public List<AmTransferPlanDt> findOnes(Long indocno){
        List<AmTransferPlanDt> amTransferPlanDtList = amTransferPlanDtMapper.findAllByIlinkno(indocno);
        if (!CollectionUtils.isEmpty(amTransferPlanDtList)){
            amTransferPlanDtList.forEach(e->{
                e.setIstoragestate(1);
                List<AmTransferPlanDtComp> all = amTransferPlanDtCompMapper.findSonByIparent(e.getSfcode());
                e.setAmTransferPlanDtCompList(all);
            });
        }
        return amTransferPlanDtList;
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmTransferPlan findOne(Long indocno){
        AmTransferPlan amTransferPlan = amTransferPlanMapper.findOne(indocno);
        if (null != amTransferPlan) {

            if (amTransferPlan.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(amTransferPlan.getSregid()) != null){
                amTransferPlan.setSregnm(baseSysUserProvider.findFSysUserByIndocno(amTransferPlan.getSregid()).getSname());
            }

            // 创建部门
            if(null != amTransferPlan.getIcreatedeptid()) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amTransferPlan.getIcreatedeptid());
                if(null != sysDept) {
                    amTransferPlan.setScreatedeptnm(sysDept.getSname());
                }
            }

            // 所属线路
            if(null != amTransferPlan.getIlineid()) {
                amTransferPlan.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        amTransferPlan.getIlineid() != null ? amTransferPlan.getIlineid() : ""));
            }
            //下发状态
            if(null != amTransferPlan.getItransferstate()) {
                amTransferPlan.setStransferstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_ITRANSFERSTATE,
                        amTransferPlan.getItransferstate() != null ? amTransferPlan.getItransferstate() : ""));
            }
            //下发至
            if(null != amTransferPlan.getItransferto()) {
                amTransferPlan.setStransfertonm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_ITRANSFERTO,
                        amTransferPlan.getItransferto() != null ? amTransferPlan.getItransferto() : ""));
            }
        }
        /*List<AmTransferPlanDt> amTransferPlanDtList = amTransferPlanDtMapper.findAllByIlinkno(indocno);
        amTransferPlan.setAmTransferPlanDtList(amTransferPlanDtList);
        LinkedList<AmTransferPlanDtComp> list = new LinkedList<>();
        if (!CollectionUtils.isEmpty(amTransferPlanDtList)){
            amTransferPlanDtList.forEach(e->{
                List<AmTransferPlanDtComp> all = amTransferPlanDtCompMapper.findAllByIlinkno(e.getIndocno());
                list.addAll(all);
            });
        }
        amTransferPlan.setAmTransferPlanDtCompList(list);*/
        return amTransferPlan;
    }

    /**
    * 添加
    *
     */
    @TransactionalException
    public AmTransferPlan add(AmTransferPlan entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());
        String s = generateSappmtno();
        entity.setSfcode(s);
        entity.setDregt(new Date());
        SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(authInfo.getIndocno());
        if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
            entity.setIcreatedeptid(sysAccount.getSysUserInfo().getIdeptid());		// 创建部门
        }
        super.insert(entity);
        //添加明细表
        /*handleAddDt(entity);*/
        return entity;
    }
    public void handleAddDt(AmTransferPlan entity){
        List<AmTransferPlanDt> amTransferPlanDtList = entity.getAmTransferPlanDtList();
        if (!CollectionUtils.isEmpty(amTransferPlanDtList)){
            amTransferPlanDtList.forEach(e->{
                AmAcceptanceFiasset one = amAcceptanceFiassetMapper.findOne(e.getIndocno());
                BeanUtils.copyProperties(one,e);
                e.setIlinkno(entity.getIndocno());
                e.setIndocno(null);
                amTransferPlanDtMapper.insert(e);
                if (one.getSfcode() != null){
                    List<AmAcceptanceFiasset> sonByIparent = amAcceptanceFiassetMapper.findSonByIparent(one.getSfcode());
                    if (!CollectionUtils.isEmpty(sonByIparent)){
                        sonByIparent.forEach(a->{
                            AmTransferPlanDtComp am = new AmTransferPlanDtComp();
                            BeanUtils.copyProperties(a,am);
                            //am.setIlinkno(e.getIndocno());
                            am.setIndocno(null);
                            amTransferPlanDtCompMapper.insert(am);
                        });
                    }
                }
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmTransferPlanParam condition){
        List<AmTransferPlan> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amTransferPlanMapper.delete(e);
            });
        }
    }

    /**
    *修改
    **/
    @TransactionalException
    public void updateBasic(AmTransferPlan entity){
        amTransferPlanMapper.update(entity);
        /*//先删除移交明细表
        List<AmTransferPlanDt> amTransferPlanDtList = amTransferPlanDtMapper.findAllByIlinkno(indocno);
        List<Long> old = amTransferPlanDtList.stream().map(e -> e.getIndocno()).collect(Collectors.toList());
        ArrayList<Object> list3 = new ArrayList<>();
        list3.addAll(old);
        List<AmTransferPlanDt> amTransferPlanDtList1 = entity.getAmTransferPlanDtList();
        if (!CollectionUtils.isEmpty(amTransferPlanDtList1)){
            List<Long> toAdd = amTransferPlanDtList1.stream().map(e -> e.getIndocno()).collect(Collectors.toList());
            LinkedList<Long> deleteList = new LinkedList<>();
            deleteList.addAll(toAdd);
            deleteList.removeAll(list3);
            old.removeAll(toAdd);
            if (!CollectionUtils.isEmpty(old)){
                old.forEach(e->{
                    List<AmTransferPlanDtComp> amTransferPlanDtCompList = amTransferPlanDtCompMapper.findAllByIlinkno(e);
                    if (!CollectionUtils.isEmpty(amTransferPlanDtCompList)){
                        amTransferPlanDtCompList.forEach(a->{
                            amTransferPlanDtCompMapper.delete(a);
                        });
                    }
                    AmTransferPlanDt de = new AmTransferPlanDt();
                    de.setIndocno(e);
                    amTransferPlanDtMapper.delete(de);
                });
            }
            //添加明细
            if (!CollectionUtils.isEmpty(deleteList)){
                deleteList.forEach(o->{
                    AmAcceptanceFiasset one = amAcceptanceFiassetMapper.findOne(o);
                    AmTransferPlanDt newOne = new AmTransferPlanDt();
                    BeanUtils.copyProperties(one,newOne);
                    newOne.setIlinkno(entity.getIndocno());
                    newOne.setIndocno(null);
                    amTransferPlanDtMapper.insert(newOne);
                    if (one.getSfcode() != null){
                        List<AmAcceptanceFiasset> sonByIparent = amAcceptanceFiassetMapper.findSonByIparent(one.getSfcode());
                        if (!CollectionUtils.isEmpty(sonByIparent)){
                            sonByIparent.forEach(a->{
                                AmTransferPlanDtComp am = new AmTransferPlanDtComp();
                                BeanUtils.copyProperties(a,am);
                                am.setIlinkno(o);
                                am.setIndocno(null);
                                amTransferPlanDtCompMapper.insert(am);
                            });
                        }
                    }
                });
            }
        }*/
    }

    /**
     *   生成编号
     *  @return
     */
    private String generateSappmtno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String sappmtno = "ZCXFJH-" + date;
        AmTransferPlanParam condition = new AmTransferPlanParam();
        condition.setSfcode(sappmtno);
        List<AmTransferPlan> list = this.amTransferPlanMapper.findMaxSappmtno(condition);
        if(null == list || list.size() == 0) {
            sappmtno = sappmtno + "-" + "01";
        }else if(null != list && list.size() > 0){
            AmTransferPlan maxPlanInfo = list.get(0);
            String maxSappmtno = maxPlanInfo.getSfcode();
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
