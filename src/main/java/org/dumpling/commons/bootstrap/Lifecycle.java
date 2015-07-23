package org.dumpling.commons.bootstrap;

/**
 * 具有生命周期的对象
 * @author rabbit
 * @date  2013-7-16
 *
 */
public interface Lifecycle {
	
	/**
	 * 开始
	 */
	public void start();
	
	/**
	 * 结束
	 */
	public void stop();
	
	/**
	 * 重启,重置
	 */
	public void reset();
}
