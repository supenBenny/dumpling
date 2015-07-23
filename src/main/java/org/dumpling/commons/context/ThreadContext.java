package org.dumpling.commons.context;

import java.util.HashMap;
import java.util.Map;

public class ThreadContext {
	
	private ThreadLocal<Map<String,Object>> local = new ThreadLocal<Map<String,Object>>();
	
	private static ThreadContext instance;
	
	private ThreadContext(){
		local.set(new HashMap<String,Object>());
	}
	
	public static ThreadContext getCurrentContext(){
		if(instance == null){
			synchronized (ThreadContext.class) {
				if(instance == null){
					instance = new ThreadContext();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 向当前线程设置线程级变量
	 * @param key
	 * @param obj
	 * @return
	 */
	public Object set(String key,Object value) {
		return local.get().put(key, value);
	}
	
	/**
	 * 获取当前线程变量
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return local.get().get(key);
	}
	
	/**
	 * 移除当前环境变量
	 * @param key
	 * @return
	 */
	public Object remove(String key) {
		return local.get().remove(key);
	}
	
//	void clean(){
//		
//	}
	/**
	 * 
	 */
	void destory(){
		//local.remove();
		local.get().clear();
	}

}
