package com.luckyun.tjam.prophaseMag.acceptance.helper;

import java.util.List;

import com.google.common.collect.Lists;
import com.luckyun.tjam.prophaseMag.acceptance.common.BaseCommonConfig;

public class OssFileHelper {
	
	public static String downloadFilesPath(String relationPath) {
		String pre = "/oss/download/file?filepath=";
		return generateAllUrl(pre + relationPath);
	}
	
	public static String showImagePath(String relationPath) {
		String pre = "/oss/show/img?filepath=";
		return generateAllUrl(pre + relationPath);
	}

	/**
	 *将相对地址转换为绝对完整地址
	 * @param relationUrl 相对地址集合
	 * @param spKey 分隔符
	 * @return 完整地址集合
	 */
	public static List<String> generateAllUrl(String relationUrl,String spKey){
		String[] reLists = relationUrl.split(spKey);
		return generateAllUrl(reLists);
	}
	
	private static List<String> generateAllUrl(String[] reLists){
		List<String> resultLists = Lists.newArrayListWithCapacity(reLists.length);
		for(String rUrl : reLists) {
			resultLists.add(rUrl);
		}
		return generateAllUrl(resultLists);
	}
	/**
	 * 填充完整的url地址
	 * @param reLists
	 */
	private static List<String> generateAllUrl(List<String> reLists){
		List<String> resultLists = Lists.newArrayListWithCapacity(reLists.size());
		for(String rUrl : reLists) {
			resultLists.add(generateAllUrl(rUrl));
		}
		return resultLists;
	}
	/**
	 * 返回完整地址
	 * @param reStr 相对地址
	 * @return 完整附件地址
	 */
	private static String generateAllUrl (String reStr) {
		if(reStr.indexOf("http") < 0) {
			if(reStr.startsWith("/")) {
				reStr = BaseCommonConfig.preOssUrl + reStr;
			}else {
				reStr = BaseCommonConfig.preOssUrl + "/" + reStr;
			}
		}
		return reStr;
	}
}
