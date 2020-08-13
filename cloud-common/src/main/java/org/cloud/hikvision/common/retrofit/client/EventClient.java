package org.cloud.hikvision.common.retrofit.client;

import java.io.IOException;
import java.util.HashMap;
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
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class EventClient {

	private Retrofit retrofit;

	public EventClient(String baseUrl) {
		retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				// 增加返回字符串支持
				.addConverterFactory(ScalarsConverterFactory.create())
				// 增加返回值为Gson的支持
				.addConverterFactory(GsonConverterFactory.create()).build();
	}
	
	/**
	 * 注册事件
	 * @param name
	 * @param body
	 * @return
	 */
	public String fireEvent(String name, RequestBody body) {
		Api api = retrofit.create(Api.class);
		Call<String> call = api.fireEvent(name, body);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 注册事件
	 * @param name
	 * @return
	 */
	public String fireEvent(String name) {
		Api api = retrofit.create(Api.class);
		Call<String> call = api.fireEvent(name);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	public String listEvents(Map<String, Object> query) {
		Api api = retrofit.create(Api.class);
		Call<String> call = api.listEvents(query);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	interface Api{
		@PUT("event/fire/{name}")
		Call<String> fireEvent(@Path("name") String name
				, @Body RequestBody body);
		
		@PUT("event/fire/{name}")
		Call<String> fireEvent(@Path("name") String name);
		
		@GET("event/list")
		Call<String> listEvents(@QueryMap Map<String, Object> query);
	}
	
	public static void main(String[] args) {
		EventClient e = new EventClient("http://localhost:8500/v1/");
		System.out.println(e.fireEvent("first-event", RequestBody.create(MediaType.parse("utf-8"), "this is first event")));
		System.out.println("-------------注册事件-------------");
		System.out.println(e.fireEvent("first-event"));
		System.out.println("-------------查询所有事件-----------");
		Map<String, Object> query = new HashMap<>();
//		query.put("name", "first-event");
		System.out.println(e.listEvents(query));
	}
}











































