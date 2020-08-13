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

/**
 * 状态获取客户端
 * @author fzz
 *
 */
public class StatuClient {

	private Retrofit retrofit;

	public StatuClient(String baseUrl) {
		retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				// 增加返回字符串支持
				.addConverterFactory(ScalarsConverterFactory.create())
				// 增加返回值为Gson的支持
				.addConverterFactory(GsonConverterFactory.create()).build();
	}
	
	/**
	 * 获取主节点信息
	 * @return
	 */
	public String getLeaders() {
		API api = retrofit.create(API.class);
		Call<String> call = api.getLeader();
		try {
			return call.execute().body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 返回所有的节点信息
	 * @return
	 */
	public List<String> getPeers(){
		API api = retrofit.create(API.class);
		Call<List<String>> call = api.getPeers();
		try {
			Response<List<String>> response = call.execute();
			return response.body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	interface API {
		
		/**
		 * 获取主节点
		 * @return
		 */
		@GET("status/leader")
		Call<String> getLeader();
		
		/**
		 * 获取所有节点
		 * @return
		 */
		@GET("status/peers")
		Call<List<String>> getPeers();
	}
	
	public static void main(String[] args) {
		StatuClient s = new StatuClient("http://127.0.0.1:8500/v1/");
		System.out.println(s.getLeaders());
		System.out.println(s.getPeers());
	}
}






































