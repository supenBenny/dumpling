package org.dumpling.commons.observer;

import java.util.ArrayList;
import java.util.List;

import org.dumpling.lang.L;

/**
 * 事件唯一标识
 * 
 * @author rabbit
 * @date 2013-8-11
 * 
 */
public class EventKey {

	private String eventName;

	private Class<? extends Event> eventType;
	
	private List<EventListener> listeners ;

	public EventKey() {
		
	}

	public EventKey(String eventName, Class<? extends Event> eventType) {
		this.eventName = eventName;
		this.eventType = eventType;
		this.listeners = new ArrayList<EventListener>(); 
	}
	
	/**
	 * 添加一个listener
	 * @param listener
	 */
	public void addListener(EventListener listener) {
		if(L.isNULL(listener)){
			return;
		}
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}
	
	/**
	 * 添加该类型监听者
	 * @param listeners
	 */
	public void addListener(List<EventListener> listeners) {
		if(L.notEmpty(listeners)){
			for(EventListener l : listeners){
				this.addListener(l);
			}
		}
	}

	/**
	 * 创建一个eventKey对象
	 * @param eventName 触发事件名称
	 * @param eventType 触发事件类型
	 * @return
	 */
	public static EventKey create(String eventName,
			Class<? extends Event> eventType) {
		return new EventKey(eventName, eventType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventKey other = (EventKey) obj;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		return true;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Class<? extends Event> getEventType() {
		return eventType;
	}

	public void setEventType(Class<? extends Event> eventType) {
		this.eventType = eventType;
	}

	public List<EventListener> getListeners() {
		return listeners;
	}
	
	

}
