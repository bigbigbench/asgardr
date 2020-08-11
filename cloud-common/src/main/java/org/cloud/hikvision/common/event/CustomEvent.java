package org.cloud.hikvision.common.event;

public class CustomEvent implements IEvent{

	private int age;
	
	public CustomEvent(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
}
