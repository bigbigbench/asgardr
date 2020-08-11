package org.cloud.hikviison.common;

import org.cloud.hikvision.common.event.CustomEvent;
import org.cloud.hikvision.common.event.EventBusUtil;
import org.cloud.hikvision.common.event.EventListenerOne;
import org.cloud.hikvision.common.event.EventListenerTwo;

public class Main {

	public static void main(String[] args) {
		EventListenerOne l1 = new EventListenerOne();
		EventListenerTwo l2 = new EventListenerTwo();
		CustomEvent customEvent = new CustomEvent(23);
		EventBusUtil.register(l1);
		EventBusUtil.register(l2);
		EventBusUtil.post(customEvent);
	}
}
