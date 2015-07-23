package org.dumpling.commons.observer;

/**
 * 事件管理者工厂
 * @author rabbit
 * @date  2013-8-16
 *
 */
public class EventManagerFactory {
	
	private static EventManager def = new EventManager();
	
	/**
	 * 获取系统默认事件管理者实例
	 * @return
	 */
	public static EventManager getDefaultManager(){
		return def;
	}

}
