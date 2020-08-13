package org.cloud.hikvision.common.retrofit;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloud.hikvision.common.log.RegisterLog;
import org.junit.jupiter.api.Test;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

/**
 * 状态获取客户端
 * @author fzz
 *
 */
public class StatuClientTest {

	private Retrofit retrofit;

	public StatuClientTest(String baseUrl) {
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
	@Test
	public String getLeaders() {
		API api = retrofit.create(API.class);
		Call<String> call = api.getLeader();
		try {
			String res = call.execute().body();
			System.out.println(res);
			return res;
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 返回所有的节点信息
	 * @return
	 */
	@Test
	public List<List<String>> getPeers(){
		API api = retrofit.create(API.class);
		Call<List<List<String>>> call = api.getPeers();
		try {
			return call.execute().body();
		} catch (IOException e) {
			RegisterLog.getInstance().error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	interface API {
		
		@GET("status/leader")
		Call<String> getLeader();
		
		@GET("status/peers")
		Call<List<List<String>>> getPeers();
	}
}






































