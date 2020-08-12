package org.cloud.hikvision.common.retrofit.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloud.hikvision.common.log.RegisterLog;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class KeyValueClient {

	private Retrofit retrofit;

	public KeyValueClient(String baseUrl) {
		retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				// 增加返回字符串支持
				.addConverterFactory(ScalarsConverterFactory.create())
				// 增加返回值为Gson的支持
				.addConverterFactory(GsonConverterFactory.create()).build();
	}

	/**
	 * 根据key获取对应的value值
	 * 
	 * @param key
	 * @return
	 */
	private String getValueAsString(String key) {
		String res = null;
		API api = retrofit.create(API.class);
		Call<String> call = api.getValueAsString(key);
		try {
			Response<String> response = call.execute();
			res = response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return res;
	}

	/**
	 * 添加
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putValue(String key, String value) {
		API api = retrofit.create(API.class);
		RequestBody body = RequestBody.create(MediaType.parse("utf-8"), value);
		Call<Boolean> call = api.putValue(key, body);
		try {
			Response<Boolean> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return false;
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param map
	 * @return
	 */
	public boolean putValue(String key, String value, Map<String,Object> map) {
		API api = retrofit.create(API.class);
		RequestBody body = RequestBody.create(MediaType.parse("utf-8"), value);
		Call<Boolean> call = api.putValue(key, body, map);
		try {
			Response<Boolean> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	/**
	 * 删除对应的key
	 * 
	 * @param key
	 */
	public void delete(String key) {
		API api = retrofit.create(API.class);
		Call<Void> call = api.deleteValues(key);
		try {
			call.execute();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
	}

	interface API {

		@GET("kv/{key}")
		Call<String> getValueAsString(@Path("key") String key);

		@GET("kv/{key}")
		Call<List<String>> getKeys(@Path("key") String key
				, @QueryMap Map<String, Object> query);

		@PUT("kv/{key}")
		Call<Boolean> putValue(@Path("key") String key
				, @Body RequestBody data);

		@PUT("kv/{key}")
		Call<Boolean> putValue(@Path("key") String key
				, @Body RequestBody data
				, @QueryMap Map<String, Object> query);

		@DELETE("kv/{key}")
		Call<Void> deleteValues(@Path("key") String key);
	}

}
