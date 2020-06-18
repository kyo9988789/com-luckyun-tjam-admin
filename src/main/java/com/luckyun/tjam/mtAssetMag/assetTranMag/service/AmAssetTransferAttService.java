package com.luckyun.tjam.mtAssetMag.assetTranMag.service;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetTranMag.mapper.AmAssetTransferAttMapper;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferAttParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmAssetTransferAttService extends BaseService<AmAssetTransferAtt> {

    @Autowired
    private AmAssetTransferAttMapper amAssetTransferAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetTransferAtt> getMapper() {
        return amAssetTransferAttMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetTransferAtt> findAll(AmAssetTransferAttParam condition){
        List<AmAssetTransferAtt> all = amAssetTransferAttMapper.findAll(condition);
        return all;
    }


    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmAssetTransferAttParam condition){
        List<AmAssetTransferAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetTransferAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetTransferAtt findOne(Long indocno){
        AmAssetTransferAtt one = amAssetTransferAttMapper.findOne(indocno);
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
    public AmAssetTransferAtt add(AmAssetTransferAtt entity) {
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetTransferAtt entity) {
        amAssetTransferAttMapper.update(entity);
    }

}
