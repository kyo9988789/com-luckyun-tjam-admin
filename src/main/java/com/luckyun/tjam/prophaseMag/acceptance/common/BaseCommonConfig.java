package com.luckyun.tjam.prophaseMag.acceptance.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BaseCommonConfig {
	
	public static String preOssUrl;
	
	@Value("${oss.prefix-url:/oss}")
	public void setPreOssUrl(String preOssUrl) {
		BaseCommonConfig.preOssUrl = preOssUrl;
	}
	 
}
