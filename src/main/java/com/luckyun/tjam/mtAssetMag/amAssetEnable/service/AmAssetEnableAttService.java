package com.luckyun.tjam.mtAssetMag.amAssetEnable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper.AmAssetEnableAttMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableAtt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableAttParam;

@Service
public class AmAssetEnableAttService extends BaseService<AmAssetEnableAtt> {

    @Autowired
    private AmAssetEnableAttMapper amAssetEnableAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetEnableAtt> getMapper() {
        return amAssetEnableAttMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetEnableAtt> findAll(AmAssetEnableAttParam condition){
        List<AmAssetEnableAtt> all = amAssetEnableAttMapper.findAll(condition);
        return all;
    }


    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmAssetEnableAttParam condition){
        List<AmAssetEnableAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetEnableAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetEnableAtt findOne(Long indocno){
        AmAssetEnableAtt one = amAssetEnableAttMapper.findOne(indocno);
        AuthInfo authInfo = getAuthInfo();
        // 图片地址
        if (null != one.getSpath()) {
            one.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                    , "tjam", one.getSpath(), one.getSname()));
        }
        //上传人
        if(null != one.getSregid()) {
            SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(one.getSregid());
            one.setSregnm(sysAccount.getSname());
        }
        return one;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmAssetEnableAtt add(AmAssetEnableAtt entity) {
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetEnableAtt entity) {
        amAssetEnableAttMapper.update(entity);
    }


}
