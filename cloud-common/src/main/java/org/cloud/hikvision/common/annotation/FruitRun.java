package org.cloud.hikvision.common.annotation;

public class FruitRun {

    public static void main(String[] args) throws InterruptedException {
		FruiInfoUtil.getFruitInfo(Apple.class);
		while(true) {
			Thread.sleep(3000);
		}
	}
}
