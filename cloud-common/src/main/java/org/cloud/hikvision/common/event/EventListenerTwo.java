package org.cloud.hikvision.common.event;

import java.time.Instant;

import com.google.common.eventbus.Subscribe;

public class EventListenerTwo {

	@Subscribe
	public void subcribe(CustomEvent event) {
		System.out.println(Instant.now() + "监听者2 ———— > 订阅1，收到事件："+event.getAge() +"线程编号："+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
