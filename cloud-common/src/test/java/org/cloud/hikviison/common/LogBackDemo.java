package org.cloud.hikviison.common;

import org.cloud.hikvision.common.log.CommonLog;


public class LogBackDemo {

	public String toBase() {
		System.out.println(CommonLog.getInstance().getName());
        CommonLog.getInstance().info("this is base case..... sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");  
        return "logbackTest";
    }
	
	public static void main(String[] args) {
		LogBackDemo log = new LogBackDemo();
		long start = System.currentTimeMillis();
		for(int i = 0;i<10000;i++) {
			log.toBase();
		}
		System.out.println(System.currentTimeMillis() - start);
		long start1 = System.currentTimeMillis();
		for(int i = 0;i<100000;i++) {
			log.toBase();
		}
		System.out.println(System.currentTimeMillis() - start1);
	}
}
