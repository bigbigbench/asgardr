package org.cloud.hikvision.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共日志类
 * @author fzz
 *
 */
public class CommonLog extends BaseLog implements ISingleton {

	/**
	 * 构造函数
	 * @param logger
	 */
	protected CommonLog(Logger logger) {
		super(logger);
	}
	
	private final static CommonLog commonLog = new CommonLog(LoggerFactory.getLogger(CommonLog.class));

	/**
	 * 采用单例的模式来获取
	 * @return
	 */
	public static CommonLog getInstance() {
		return commonLog;
	}
}
