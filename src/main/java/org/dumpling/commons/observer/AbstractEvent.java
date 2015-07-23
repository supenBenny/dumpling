package org.dumpling.commons.observer;

/**
 * 
 * @author rabbit
 * @date  2013-8-11
 *
 */
public abstract class AbstractEvent implements Event {

	/**
	 * 事件触发者,主题发布者
	 */
	private Object target = null;
	
	public AbstractEvent(Object target){
		this.target = target;
	}
	
	public Object getTarget(){
		return this.target;
	}
}

