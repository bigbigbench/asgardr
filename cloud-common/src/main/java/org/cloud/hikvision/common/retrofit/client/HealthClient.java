package org.cloud.hikvision.common.retrofit.client;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloud.hikvision.common.log.RegisterLog;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class HealthClient {

	private Retrofit retrofit;

	public HealthClient(String baseUrl) {
		retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				// 增加返回字符串支持
				.addConverterFactory(ScalarsConverterFactory.create())
				// 增加返回值为Gson的支持
				.addConverterFactory(GsonConverterFactory.create()).build();
	}

	/**
	 * 根据节点获取服务状态
	 * @param node
	 * @return
	 */
	public String getNodeChecks(String node) {
		API api = retrofit.create(API.class);
		Call<String> call = api.getNodeChecks(node);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 根据服务状态获取服务列表
	 * @param state
	 * @return
	 */
	public String getChecksByState(String state) {
		API api = retrofit.create(API.class);
		Call<String> call = api.getChecksByState(state);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 获取可用服务列表
	 * @param service
	 * @return
	 */
	public String getServiceInstances(String service) {
		API api = retrofit.create(API.class);
		Call<String> call = api.getServiceInstances(service);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	/**
	 * 检查服务健康状态
	 * @param service
	 * @return
	 */
	public String getServiceChecks(String service) {
		API api = retrofit.create(API.class);
		Call<String> call = api.getServiceChecks(service);
		try {
			Response<String> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	interface API {

		/**
		 * 根据节点获取服务状态
		 * @param node
		 * @return
		 */
		@GET("health/node/{node}")
		Call<String> getNodeChecks(@Path("node") String node);

		/**
		 * 检查服务健康状态
		 * @param service
		 * @return
		 */
		@GET("health/checks/{service}")
		Call<String> getServiceChecks(@Path("service") String service);

		/**
		 * 根据状态信息获取服务信息
		 * @param state
		 * @return
		 */
		@GET("health/state/{state}")
		Call<String> getChecksByState(@Path("state") String state);

		/**
		 * 获取可用的服务
		 * @param service
		 * @return
		 */
		@GET("health/service/{service}")
		Call<String> getServiceInstances(@Path("service") String service);
	}

	public static void main(String[] args) {
		HealthClient h = new HealthClient("http://127.0.0.1:8500/v1/");
		System.out.println("------------------------ 查询节点的服务 ---------------------------");
		System.out.println(h.getNodeChecks("fzzdeMacBook-Pro.local"));
		System.out.println("--------------------------根据服务名称查询-------------------------");
		System.out.println(h.getServiceChecks("consul"));
		System.out.println("---------------------------- 根据状态查询-----------------------");
		System.out.println(h.getChecksByState("passing"));
		System.out.println("---------------------------------获取健康服务列表------------------");
		System.out.println(h.getServiceInstances("consul"));
	}
}
