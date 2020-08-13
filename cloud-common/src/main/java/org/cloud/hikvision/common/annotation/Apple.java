package org.cloud.hikvision.common.annotation;

import org.cloud.hikvision.common.annotation.FruitColor.Color;

public class Apple {

	@FruitName("Apple")
	private String appleName;
	
	@FruitColor(fruitColor=Color.RED)
	private String appleColor;
	
	@FruitProcider(id=1,name="陕西红富士",address="陕西")
	private String appleProcider;

	public String getAppleName() {
		return appleName;
	}

	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}

	public String getAppleColor() {
		return appleColor;
	}

	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}

	public String getAppleProcider() {
		return appleProcider;
	}

	public void setAppleProcider(String appleProcider) {
		this.appleProcider = appleProcider;
	}
}














































