package org.cloud.hikvision.common.retrofit.result;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyValueFuture<T>{

	private final Logger log = LoggerFactory.getLogger(KeyValueFuture.class);
	private final CountDownLatch latch = new CountDownLatch(1);
	private T response;
	
	public KeyValueFuture() {}

	public T get() throws InterruptedException{
		latch.await();
		return this.response;
	}
	
	public T get(long timeout, TimeUnit timeUnit) throws InterruptedException{
		try {
			if(latch.await(timeout, timeUnit)) {
				return this.response;
			}
		}catch(InterruptedException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	public void setResponse(T response) {
		this.response = response;
		latch.countDown();
	}
	
	
	                                                                                                                                                                                                                                                
}












































