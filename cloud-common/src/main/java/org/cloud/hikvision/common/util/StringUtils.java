package org.cloud.hikvision.common.util;

public class StringUtils {

	public static boolean isEmpty(String message) {
		return message != null && message.equals("");
	}
	
	public static boolean isNotNUllOrEmpty(String msg) {
		return msg != null && !msg.equals("");
	}
}
