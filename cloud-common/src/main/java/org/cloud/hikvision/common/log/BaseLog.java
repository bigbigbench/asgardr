package org.cloud.hikvision.common.log;

import org.cloud.hikvision.common.util.StringUtils;
import org.slf4j.Logger;

public class BaseLog implements ILog {

	private Logger log;
	
	protected BaseLog(Logger logger) {
		this.log = logger;
	}

	/**
	 * 打印错误级别日志
	 */
	@Override
	public void error(String message) {
		if(log !=  null && !StringUtils.isEmpty(message)) {
			log.error(message);
		}
	}

	/**
	 * 打印info级别日志
	 */
	@Override
	public void info(String message) {
		if(log !=  null && !StringUtils.isEmpty(message)) {
			log.info(message);
		}
	}

	/**
	 * 打印debug级别日志
	 */
	@Override
	public void debug(String message) {
		if(log !=  null && !StringUtils.isEmpty(message)) {
			log.debug(message);
		}
	}
	
	/**
	 * 是否开启debug日志级别
	 * @return
	 */
	public boolean isDebug() {
		return log.isDebugEnabled();
	}
	
	/**
	 * 获取loggerName
	 * @return
	 */
	public String getName() {
		return log.getName();
	}
	
	public Logger getLogger() {
		return log;
	}
}
