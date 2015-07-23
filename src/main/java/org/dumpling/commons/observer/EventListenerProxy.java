package org.dumpling.commons.observer;

import org.dumpling.lang.L;

public class EventListenerProxy implements EventListener {

	private EventListener target;
	
	/**
	 * 观察的事件类型
	 */
	private Object[] eventTypes = null;

	/**
	 * 事件名称
	 */
	private String[] events = null;

	private boolean syn = true;

	public EventListenerProxy(EventListener target,boolean syn) {
		this.target = target;
		this.syn = syn;
	}

//	public EventListenerProxy(EventListener target,
//			Object[] targets, String[] events, boolean syn) {
//		this.target = target;
//		this.events = events;
//		this.syn = syn;
//	}

	@Override
	public void listen(Event event) {
		//同步操作
		if(this.syn){
			this.target.listen(event);
		}else {
			
		}
	}

	/**
	 * 是否即根据事件监听又根据事件名称监听
	 * @return
	 */
	public boolean isMixListener(){
		return (L.notEmpty(this.eventTypes) && L.notEmpty(this.events));
	}
	
	public EventListener getTarget() {
		return target;
	}

	public Object[] getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(Object[] eventTypes) {
		this.eventTypes = eventTypes;
	}

	public void setTarget(EventListener target) {
		this.target = target;
	}

	public String[] getEvents() {
		return events;
	}

	public void setEvents(String[] events) {
		this.events = events;
	}

	public boolean isSyn() {
		return syn;
	}

	public void setSyn(boolean syn) {
		this.syn = syn;
	}

}
