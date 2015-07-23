package org.dumpling.commons.observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dumpling.commons.context.ThreadContext;
import org.dumpling.lang.L;

/**
 * 事件管理者,承载事件发布操作
 * 
 * @author rabbit
 * @date 2013-7-18
 * 
 */
public class EventManager {

	private final static String DUMPLING_EVENTS = "DUMPLING_EVENTS";

	/**
	 * 根据主题(目标对象)的事件队列
	 */
	private Map<Object, EventManager> eventManagers = new HashMap<Object, EventManager>();

	/**
	 * 根据事件类型注册的事件监听器
	 */
	private Map<Class<? extends Event>, List<EventListener>> eventTypeListeners = new HashMap<Class<? extends Event>, List<EventListener>>();

	/**
	 * 根据事件名称注册的事件监听器
	 */
	private Map<String, List<EventListener>> eventNameListeners = new HashMap<String,List<EventListener>>();

	
	private Map<EventKey,EventKey> caches = new HashMap<EventKey,EventKey>();  
	
	/*private static EventManager instance;
	
	private EventManager(){
		
	}
	
	public static EventManager getInstance(){
		if(instance == null){
			synchronized (EventManager.class) {
				if(instance == null){
					instance = new EventManager();
				}
			}
		}
		return instance;
	}
	*/
	/**
	 * 获取系统当前上下文中触发的最新事件
	 * <p>
	 * web系统,通常是一次request请求
	 * </p>
	 * 
	 * @return
	 */
	public Event getCurrentEvent() {
		List<Event> l = this.getContextEvents();
		if (L.notEmpty(l)) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 获取系统上下文中所有触发的事件
	 * <p>
	 * web系统,通常是一次request请求
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Event> getContextEvents() {
		return (List<Event>) ThreadContext.getCurrentContext().get(
				DUMPLING_EVENTS);
	}

	/**
	 * 根据事件名称标识触发
	 * 
	 * @param event
	 * @param eventKey
	 */
	public void fireEvent(Event event,String eventName) {
		this.fireEvent(event,eventName,null);
	}
	
	/**
	 * 根据事件类型触发
	 * 
	 * @param event
	 * @param target
	 */
	public void fireEvent(Event event) {
		this.fireEvent(event,null,null);
	}

	/**
	 * 根据时间名称与类型触发事件
	 *
	 * @param event
	 * @param eventName
	 * @param target
	 */
	public void fireEvent(Event event,String eventName, Object target) {
		
		if(target != null){
			EventManager m = this.eventManagers.get(target);
			m.fireEvent(event, eventName, null);
		}
		
		this._fireEvent(event, eventName);
		
		//当前上下文添加事件
		@SuppressWarnings("unchecked")
		List<Event> events = ((List<Event>)ThreadContext.getCurrentContext().get(DUMPLING_EVENTS));
		if(events == null){
			events = new LinkedList<Event>();
			ThreadContext.getCurrentContext().set(DUMPLING_EVENTS, events);
		}
		events.add(event);
	}
	
	private void _fireEvent(Event event,String eventName){
		EventKey key = EventKey.create(eventName, event.getClass());
		EventKey value = this.caches.get(key);
		
		if(null == value){
			key.addListener(eventTypeListeners.get(event.getClass()));
			if(L.hasText(eventName)){
				key.addListener(eventNameListeners.get(eventName));
			}
			value = key;
			this.caches.put(key, value);
		}
		
		List<EventListener> l = value.getListeners();
		
		for(EventListener el : l){
			el.listen(event);
		}
	}

	/**
	 * 事件绑定
	 * @param observer
	 * @param eventName
	 * @param eventType
	 * @param syn
	 * @param target
	 */
	public void bind(EventListener observer, String eventName,
			Class<? extends Event> eventType,boolean syn, Object target) {
		//observer 或 监听方式为空 视为无效注册
		if(observer == null || (L.isEmpty(eventName) && eventType == null)){
			return;
		}
		
		if(target != null){
			 EventManager m = this.eventManagers.get(target);
			 if(m == null){
				 m = new EventManager();
				 this.eventManagers.put(target, m);
			 }
			 m.bind(observer, eventName, eventType, syn, null);
			 return;
		}
		
		EventListenerProxy proxy = new EventListenerProxy(observer,syn);
		
		EventKey key = EventKey.create(eventName, eventType);
		this.caches.remove(key);
		
		if(L.hasText(eventName)) {
			List<EventListener> v = this.eventNameListeners.get(eventName);
			if(v == null){
				v = new LinkedList<EventListener>();
				this.eventNameListeners.put(eventName, v);
			}
			v.add(proxy);
		}
		
		if(eventType != null){
			List<EventListener> v = this.eventTypeListeners.get(eventType);
			if(v == null){
				v = new LinkedList<EventListener>();
				this.eventNameListeners.put(eventName, v);
			}
			v.add(proxy);
		}
		
	}
}
