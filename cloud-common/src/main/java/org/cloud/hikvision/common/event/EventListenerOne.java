package org.cloud.hikvision.common.event;

import java.time.Instant;

import com.google.common.eventbus.Subscribe;

public class EventListenerOne {


	@Subscribe
	public void subcribe(CustomEvent event) {
		System.out.println(Instant.now() + "监听者1 ———— > 订阅1，收到事件："+event.getAge() +"线程编号："+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void subcribe2(CustomEvent event) {
		System.out.println(Instant.now() + "监听者1 ———— > 订阅2，收到事件："+event.getAge() +"线程编号："+Thread.currentThread().getName());
	}
}
