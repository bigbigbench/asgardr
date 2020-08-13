package org.cloud.hikvision.common.retrofit.example;

import java.util.List;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.HealthCheck;
import com.orbitz.consul.model.health.ServiceHealth;

public class ConsulClient {

	Consul client = Consul.builder().build(); // connect on localhost
	
	public List<ServiceHealth> getService(){
		HealthClient healthClient = client.healthClient();

		// Discover only "passing" nodes
		List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("consul").getResponse();
		return nodes;
	}
	
	public static void main(String[] args) {
		ConsulClient c = new ConsulClient();
		c.getService();
	}
}
