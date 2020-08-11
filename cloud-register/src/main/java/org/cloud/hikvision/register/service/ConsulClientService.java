package org.cloud.hikvision.register.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.orbitz.consul.StatusClient;
import com.orbitz.consul.model.health.ServiceHealth;

public interface ConsulClientService extends IService{

	/**
	 * 服务注册
	 */
	public void registerService(String serviceId, String serviceName
			, int port, String tag, Map<String,String> meta);
	
	/**
	 * 发现可用服务
	 * @return
	 */
	public List<ServiceHealth> findHealthService(String service);
	
	/**
	 * 存储数据
	 */
	public void putValue(String key, String value);
	
	/**
	 * 获取key对应的value值
	 * @param key
	 * @return
	 */
	public Optional<String> getValue(String key);
	
	/**
	 * 根据key值订阅value变化通知
	 */
	public void subscribeKeyOvalue(String k, String val);
	
	/**
	 * 根据服务名称订阅服务变化通知
	 * @param serviceName
	 */
	public void subscribeService(String serviceName);
	
	/**
	 * 获取raft peers，获取所有状态信息
	 * @return
	 */
	public List<String> findPeers();
	
	/**
	 * 获取主节点信息
	 * @return
	 */
	public String findLeaderPeers();
}







































