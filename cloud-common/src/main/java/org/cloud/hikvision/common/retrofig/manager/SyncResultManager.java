package org.cloud.hikvision.common.retrofig.manager;

import java.util.concurrent.ConcurrentHashMap;

import org.cloud.hikvision.common.retrofit.result.KeyValueFuture;

@SuppressWarnings("rawtypes")
public class SyncResultManager {

	private ConcurrentHashMap<String, KeyValueFuture> results; 
	
	static class HolderSinglton{
		private final static SyncResultManager instance = new SyncResultManager();
	}
	
	/**
	 * 私有构造函数，避免外部创建对象
	 */
	private SyncResultManager() {
		results = new ConcurrentHashMap<>();
	}
	
	public SyncResultManager getInstance() {
		return HolderSinglton.instance;
	}
	
	public void put(String key, KeyValueFuture value) {
		results.put(key, value);
	}
	
}








































