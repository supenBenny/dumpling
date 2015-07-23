package org.dumpling.commons.observer;

/**
 * 观察者
 * @author rabbit
 * @date  2013-7-16
 *
 */
public interface EventListener {
	
	/**
	 * monitor
	 * @param event
	 */
	public void listen(Event event);

}
