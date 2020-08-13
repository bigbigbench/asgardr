package org.cloud.hikvision.common.retrofit;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloud.hikvision.common.log.RegisterLog;
import org.cloud.hikvision.common.retrofit.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class KeyValueClientTest {

	private Retrofit retrofit;

	public KeyValueClientTest(String baseUrl) {
		retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create())
				// 增加返回值为Gson的支持
				.addConverterFactory(GsonConverterFactory.create()).build();
	}

	/**
	 * 根据key获取对应的value值
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
	 * 根据key获取对应的value
	 * @param key
	 * @return
	 */
	public Value getValue(String key) {
		API api = retrofit.create(API.class);
		Call<Value> call = api.getValue(key);
		Value value = null;
		Response<Value> response;
		try {
			response = call.execute();
			value = response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return value;
	}
	
	@SuppressWarnings("unused")
	private String getValueAsStringSync(String key) throws IOException {

		API api = retrofit.create(API.class);
		Call<String> call = api.getValueAsString(key);
		call.execute();
		call.cancel();
		// 发送请求
		call.enqueue(new Callback<String>() {
			// 响应回调
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				
			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {
				
			}
		});
		return null;
	}


	interface API {
		@GET("kv/{key}")
		Call<Value> getValue(@Path("key") String key);

		@GET("kv/{key}")
		Call<String> getValueAsString(@Path("key") String key);

		@PUT("kv/{key}")
		Call<Boolean> putValue(@Path("key") String key);
	}
	

}
