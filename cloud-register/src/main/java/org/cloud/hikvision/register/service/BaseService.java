package org.cloud.hikvision.register.service;

import com.orbitz.consul.Consul;

public class BaseService {

	public Consul client;
	
	public BaseService() {
		client = Consul.builder().build();
	}
	
	public Consul getClient() {
		return client;
	}
	
}
