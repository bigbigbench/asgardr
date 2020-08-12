package org.cloud.hikvision.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共日志类
 * @author fzz
 *
 */
public class RegisterLog extends BaseLog implements ISingleton {

	/**
	 * 构造函数
	 * @param logger
	 */
	protected RegisterLog(Logger logger) {
		super(logger);
	}
	
	private final static RegisterLog commonLog = new RegisterLog(LoggerFactory.getLogger(RegisterLog.class));

	/**
	 * 采用单例的模式来获取
	 * @return
	 */
	public static RegisterLog getInstance() {
		return commonLog;
	}
}
