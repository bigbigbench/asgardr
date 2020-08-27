package org.cloud.hkvision.common.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelAnnotaionDemo {

	@SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallbaxk")
	public String hello(long s) {
		return String.format("Hello at %d", s);
	}
	
	/**
	 * Fallback函数，函数签名与原函数一致或加一个Throwblw类型参数
	 * @param s
	 * @return
	 */
	public String helloFallback(long s) {
		return String.format("Hal00000 %d", s);
	}
	
	/**
	 * Block异常处理函数， 参数最后多一个BlockException， 其余与原函数一致
	 * @param s
	 * @param ex
	 * @return
	 */
	public String exceptionHandler(long s, BlockException ex) {
		ex.printStackTrace();
		return "Oops, error occurred at "+s;
 	}
}

























