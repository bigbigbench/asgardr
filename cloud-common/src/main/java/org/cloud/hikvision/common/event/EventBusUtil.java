package org.cloud.hikvision.common.event;

import java.util.concurrent.Executor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class EventBusUtil {

	private static EventBus eventBus;
	private static AsyncEventBus ayncEventBus;
	private static Executor executor = new Executor() {

		@Override
		public void execute(Runnable command) {
			// TODO Auto-generated method stub
			new Thread(command).start();
		}
	};
	
	private static AsyncEventBus getAysncEventBus() {
		if(ayncEventBus == null) {
			ayncEventBus = new AsyncEventBus(executor);
		}
		return ayncEventBus;
	}
	
	private static EventBus getEventBus() {
		if(eventBus == null) {
			eventBus = new EventBus();
		}
		return eventBus;
	}
	
	public static void post(Object event) {
		getEventBus().post(event);
	}
	
	public static void asyncPost(Object event) {
		getAysncEventBus().post(event);
	}
	
	public static void register(Object obj) {
		getEventBus().register(obj);
		getAysncEventBus().register(obj);
	}
}








































