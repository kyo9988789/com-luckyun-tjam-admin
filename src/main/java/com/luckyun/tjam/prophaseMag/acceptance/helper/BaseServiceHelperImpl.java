package com.luckyun.tjam.prophaseMag.acceptance.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luckyun.base.provider.cache.datadic.SysDatadicCacheHelper;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.redis.RedisOperationDao;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.data.BaseEntity;
import com.luckyun.model.datadic.SysDatadic;

@Component
public class BaseServiceHelperImpl extends BaseService<BaseEntity>{
	
	@Autowired
	SysDatadicCacheHelper sysDatadicCacheHelper;

	@Autowired
	private RedisOperationDao  redisOperationDao;

	@Autowired
	private OssPathHelperUtils ossPathHelperUtils;

	public final static List<String> IMG_LIST = Arrays.asList("jpg","png","bpm","gif","JPG","PNG","BPM","GIF");



	/**
	 *
	 * <p>Title: getDatadicName</p>  
	 * <p>Description: 获取数字字典对应的别名</p>  
	 * @param ddField 主键值,即存在数据库的值
	 * @param source 缓存的数据字典源
	 * @return 数字字典名称
	 */
	public String getDatadicName(String ddField,List<JSONObject> source) {
		if(ddField != null && !ddField.equals("")) {
			return rtnLabel(ddField,source);
		}else {
			return "";
		}
	}
	/**
	 *
	 * <p>Title: getDatadicName</p>  
	 * <p>Description: 获取数据字典名称</p>  
	 * @param ddKey 数据字典类别别名
	 * @param ddField 主键值,数据库保存的内容
	 * @return 数据字典名称
	 */
	public String getDatadicName(String ddField, Object ddKey) {
		String ddKeyReal = ddKey != null ? ddKey.toString(): "";
	    if(!StringUtils.isEmpty(ddField)) {
	      List<SysDatadic> datadics = sysDatadicCacheHelper.getObjByField(ddField);
	      if(datadics!=null) {
	        Optional<SysDatadic> result = datadics.stream().filter(e -> ddKeyReal.equals(e.getSfactvalue())).findFirst();
	        if(result.isPresent()) {
	          return result.get().getSname();
	        }else {
	          datadics = sysDatadicCacheHelper.updateObj(ddField);
	          result = datadics.stream().filter(e -> ddKeyReal.equals(e.getSfactvalue())).findFirst();
	          if(result.isPresent()) {
	            return result.get().getSname();
	          }
	        }
	      }
	    }
	    return "";
	}
	/**
	 *
	 * <p>Title: rtnDatadicJson</p>  
	 * <p>Description:根据字典别名获取字典列表 </p>  
	 * @param ddfield 字典别名
	 * @return 字典列表
	 */
	protected List<JSONObject> rtnDatadicJson(String ddfield){
		String ddFieldReal = ddfield != null ? ddfield.toString(): "";
		List<SysDatadic> datadics = sysDatadicCacheHelper.getObjByField(ddFieldReal);
		String result = JSONObject.toJSONString(datadics);
		return JSON.parseArray(result, JSONObject.class);
	}

	protected List<JSONObject> redisDataJson(String ddfield){
		String stateuserStr = this.redisOperationDao.hMget(
				"base_datadic_list_form_classify", ddfield);
		List<JSONObject> jsonLists = JSON.parseArray(stateuserStr, JSONObject.class);
		return jsonLists;
	}

	private String rtnLabel(String ddId,List<JSONObject> source) {
		if(source == null) {
			return "";
		}
		for(JSONObject jsonObject : source) {
			if(ddId.equals(jsonObject.getString("sfactvalue"))) {
				return jsonObject.getString("sname");
			}
		}
		return "";
	}

	/***
	 * 根据当前用户id  获取用户名
	 * @param iuserid
	 * @return
	 */
	protected String getUserNm(Long iuserid) {
		String suserid = iuserid != null ? iuserid.toString():"";
		if(suserid != "") {
			String stateuserStr = this.redisOperationDao.hMget(
					"base_init_user_info_list", suserid);
			if(!StringUtils.isEmpty(stateuserStr)) {
				JSONObject result= JSON.parseObject(stateuserStr);
				if(result != null) {
					return result.getString("sname");
				}
			}
		}
		return "";
	}

	protected String getClassifyNameAlias(Object iclassifyId) {
		String sclassifyId = iclassifyId != null ? iclassifyId.toString() : "";
		if(!sclassifyId.isEmpty()) {
			String classifyObj = redisOperationDao.hMget("base_datadic_classify_list"
					, sclassifyId);
			if(!StringUtils.isEmpty(classifyObj)) {
				JSONObject result= JSON.parseObject(classifyObj);
				if(result != null) {
					return result.getString("snamealias");
				}
			}
		}
		return "";
	}

	protected String getClassifyName(Object iclassifyId) {
		String sclassifyId = iclassifyId != null ? iclassifyId.toString() : "";
		if(!sclassifyId.isEmpty()) {
			String classifyObj = redisOperationDao.hMget("base_datadic_classify_list"
					, sclassifyId);
			if(!StringUtils.isEmpty(classifyObj)) {
				JSONObject result= JSON.parseObject(classifyObj);
				if(result != null) {
					return result.getString("sname");
				}
			}
		}
		return "";
	}

	protected JSONObject getClassifyObj(Object iclassifyId) {
		String sclassifyId = iclassifyId != null ? iclassifyId.toString() : "";
		if(!sclassifyId.isEmpty()) {
			String classifyObj = redisOperationDao.hMget("base_datadic_classify_list"
					, sclassifyId);
			if(!StringUtils.isEmpty(classifyObj)) {
				JSONObject result= JSON.parseObject(classifyObj);
				if(result != null) {
					return result;
				}
			}
		}
		return new JSONObject();
	}

	protected JSONObject getDept(Object ideptid) {
		String sdeptid = ideptid != null ? ideptid.toString():"";
		if(sdeptid != "") {
			String statedeptrStr = this.redisOperationDao.hMget(
					"base_dept_list", sdeptid);
			if(!StringUtils.isEmpty(statedeptrStr)) {
				JSONObject result= JSON.parseObject(statedeptrStr);
				return result;
			}
		}
		return null;
	}

	protected String getDeptNm(Object ideptid) {
		String sdeptid = ideptid != null ? ideptid.toString():"";
		if(sdeptid != "") {
			String statedeptrStr = this.redisOperationDao.hMget(
					"base_dept_list", sdeptid);
			if(!StringUtils.isEmpty(statedeptrStr)) {
				JSONObject result= JSON.parseObject(statedeptrStr);
				if(result != null) {
					return result.getString("sname");
				}
			}
		}
		return "";
	}

	protected Long getIcompanyId(Object userId) {
		String suserid = userId != null ? userId.toString():"";
		if(!suserid.isEmpty()) {
			String scompanyId = redisOperationDao.get("base_auth_user_by_company_" + suserid);
			if(scompanyId != null) {
				return Long.valueOf(scompanyId);
			}
		}
		return 0L;
	}

	/**
	 * 获取附件预览地址
	 * @param stype
	 * @param filename
	 * @param path
	 * @return
	 */
	public String generatePreviewPath(String stype,String filename,String path) {
		String sloginid = super.getAuthInfo().getSloginid();
		return ossPathHelperUtils.generatePdfPreview(sloginid, "njmis", path, filename);
	}
	/**
	 * 获取图片图文混排地址
	 * @param stype
	 * @param filename
	 * @param path
	 * @return
	 */
	public String generateImgPath(String stype,String filename,String path) {
		String sloginid = super.getAuthInfo().getSloginid();
		if(IMG_LIST.contains(stype)) {
			return ossPathHelperUtils.generateShowImg(sloginid, "njmis", path);
		}
		return null;
	}
	/**
	 * 获取下载地址
	 * @param stype
	 * @param filename
	 * @param path
	 * @return
	 */
	public String generateDownPath(String stype,String filename,String path) {
		String sloginid = super.getAuthInfo().getSloginid();
			return ossPathHelperUtils.generateDownloadFile(sloginid, "njmis", path,filename);
	}

	@Override
	public BaseMapper<BaseEntity> getMapper() {
		return null;
	}
}
