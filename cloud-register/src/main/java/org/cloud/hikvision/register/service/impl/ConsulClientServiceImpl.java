package org.cloud.hikvision.register.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.cloud.hikvision.register.service.BaseService;
import org.cloud.hikvision.register.service.ConsulClientService;

//import com.orbitz.consul.AgentClient;
//import com.orbitz.consul.HealthClient;
//import com.orbitz.consul.KeyValueClient;
//import com.orbitz.consul.StatusClient;
//import com.orbitz.consul.cache.KVCache;
//import com.orbitz.consul.cache.ServiceHealthCache;
//import com.orbitz.consul.cache.ServiceHealthKey;
//import com.orbitz.consul.model.agent.ImmutableRegistration;
//import com.orbitz.consul.model.agent.Registration;
//import com.orbitz.consul.model.health.ServiceHealth;
//import com.orbitz.consul.model.kv.Value;

public class ConsulClientServiceImpl extends BaseService implements ConsulClientService {

	/**
	 * 注册服务
	 */
	@Override
	public void registerService(String serviceId, String serviceName
			, int port, String tag, Map<String,String> meta) {
		AgentClient agentClient = client.agentClient();
		Registration service = ImmutableRegistration.builder()
				.id(serviceId)
				.name(serviceName)
				.port(port)
				.check(Registration.RegCheck.ttl(3L))
		        .tags(Collections.singletonList(tag))
		        .meta(meta)
		        .build();
		agentClient.register(service);
	}

	/**
	 * 获取健康服务
	 */
	@Override
	public List<ServiceHealth> findHealthService(String service) {
		HealthClient healthClient = client.healthClient();
		List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances(service).getResponse();
		return nodes;
	}

	/**
	 * 存储信息
	 */
	@Override
	public void putValue(String key, String value) {
		KeyValueClient kvClient = client.keyValueClient();
		kvClient.putValue(key,value);
	}

	/**
	 * 根据key值获取信息
	 */
	@Override
	public Optional<String> getValue(String key) {
		KeyValueClient kvClient = client.keyValueClient();
		Optional<String> value = kvClient.getValueAsString(key);
		return value;
	}

	/**
	 * 根据key订阅对应value改变通知
	 */
	@Override
	public void subscribeKeyOvalue(String k, String val) {
		final KeyValueClient kvClient = client.keyValueClient();
		kvClient.putValue(k,val);
		KVCache cache = KVCache.newCache(kvClient, k);
		cache.addListener(newValues->{
			Optional<Value> newValue = newValues.values().stream()
					.filter(value -> value.getKey().equals(k))
					.findAny();
			newValue.ifPresent(value -> {
				Optional<String> decodedValue = newValue.get().getValueAsString();
			});
		});
		cache.start();
		//....
		cache.stop();
		
	}

	//订阅服务名称对应的服务变化通知
	@Override
	public void subscribeService(String serviceName) {
		HealthClient healthClient = client.healthClient();
		ServiceHealthCache svHealth = ServiceHealthCache.newCache(healthClient, serviceName);
		svHealth.addListener((Map<ServiceHealthKey, ServiceHealth> newValues) -> {
			//do something
		});
		
		svHealth.start();
		//... do something
		svHealth.stop();
	}

	/**
	 * 获取所有节点信息
	 */
	@Override
	public List<String> findPeers() {
		StatusClient statusClient = client.statusClient();
		List<String> peers = statusClient.getPeers();
		return peers;
	}

	/**
	 * 获取主节点信息
	 */
	@Override
	public String findLeaderPeers() {
		StatusClient statusClient = client.statusClient();
		String leaders = statusClient.getLeader();
		return leaders;
	}

	
}























