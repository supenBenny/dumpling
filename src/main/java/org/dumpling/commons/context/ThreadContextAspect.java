package org.dumpling.commons.context;

/**
 * 使用切面方式移除环境变量
 * @author rabbit
 * @date  2013-6-23
 *
 */
public class ThreadContextAspect {
		
	public void after(){
		ThreadContext.getCurrentContext().destory();
	}
}
