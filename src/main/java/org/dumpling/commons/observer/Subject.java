package org.dumpling.commons.observer;

/**
 * 可观察的对象,事件发布者
 * @author rabbit
 * @date 2013-7-24
 *
 */
public interface Subject {
	
	public void addEventListener(EventListener observer);
	
	public void removeEventListener(EventListener observer);
	
	public void notifyListener(); 

}
