package com.luckyun.tjam.mtAssetMag.amAssetFree.service;

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
import com.luckyun.tjam.mtAssetMag.amAssetFree.mapper.AmAssetFreeAttMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeAtt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeAttParam;

@Service
public class AmAssetFreeAttService extends BaseService<AmAssetFreeAtt> {

    @Autowired
    private AmAssetFreeAttMapper amAssetFreeAttMapper;

    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetFreeAtt> getMapper() {
        return amAssetFreeAttMapper;
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmAssetFreeAttParam condition){
        List<AmAssetFreeAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetFreeAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetFreeAtt findOne(Long indocno){
        AmAssetFreeAtt one = amAssetFreeAttMapper.findOne(indocno);
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
    public AmAssetFreeAtt add(AmAssetFreeAtt entity) {
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetFreeAtt entity) {
        amAssetFreeAttMapper.update(entity);
    }

}
