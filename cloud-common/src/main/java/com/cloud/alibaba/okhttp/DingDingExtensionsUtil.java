package com.cloud.alibaba.okhttp;

import java.io.IOException;

public class DingDingExtensionsUtil {
	/**
	 * 根据corpId，corpSecret调用接口生成accessToken
	 * 
	 * @param corpId
	 * @param corpSecret
	 * @return
	 */
	public String getAccessToken(String corpId, String corpSecret) {
		// 返回的accessToken:String类型的Json串,需解析Json才能拿到里面的accessToken
		String accessToken = "";
		OkHttpRequest okHttpRequest = new OkHttpRequest();
		// 生成accessToken的接口URL
		String accessTokenUrl = "https://oapi.dingtalk.com/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;
		try {
			// 发送请求
			accessToken = okHttpRequest.get(accessTokenUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
}
