package com.luckyun.tjam.mtAssetMag.assetDailyMag.service;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeAttMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeAttParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmAssetChangeAttService extends BaseService<AmAssetChangeAtt>{

    @Autowired
    private AmAssetChangeAttMapper amAssetChangeAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetChangeAtt> getMapper() {
        return amAssetChangeAttMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetChangeAtt> findAll(AmAssetChangeAttParam condition){
        List<AmAssetChangeAtt> all = amAssetChangeAttMapper.findAll(condition);
        return all;
    }


    /***
     *
     * 删除
     *
     */
    @TransactionalException
  /*  public AmTransferPlanDt delOpr(AmTransferPlanDt condition){
        Long indocno = condition.getIndocno();
        AmTransferPlanDt one = amTransferPlanDtMapper.findOne(indocno);
        if(!StringUtils.isEmpty(one)){
            amTransferPlanDtMapper.delete(one);
        }
        return one;
    }*/
    public void delOpr(AmAssetChangeAttParam condition){
        List<AmAssetChangeAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetChangeAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetChangeAtt findOne(Long indocno){
        AmAssetChangeAtt one = amAssetChangeAttMapper.findOne(indocno);
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
    public AmAssetChangeAtt add(AmAssetChangeAtt entity) {
    	//List<AmAssetChangeAtt> attList = entity.getAttList();
		/*
		 * if(!CollectionUtils.isEmpty(attList)) { attList.forEach(e->{ super.insert(e);
		 * }); } return entity;
		 */
    	super.insert(entity);
    	return entity;
    }
	/*
	 * public AmAssetChangeAttUtil add(AmAssetChangeAttUtil entity){
	 * List<AmAssetChangeAtt> attList = entity.getAttList(); if
	 * (!CollectionUtils.isEmpty(attList)){ attList.forEach(e->{
	 * e.setIlinkno(entity.getIndocno()); super.insert(e); }); } return entity; }
	 */

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetChangeAtt entity) {
        amAssetChangeAttMapper.update(entity);
    }
}
