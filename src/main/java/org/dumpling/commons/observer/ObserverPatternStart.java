package org.dumpling.commons.observer;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dumpling.commons.bootstrap.Lifecycle;
import org.dumpling.commons.listeners.ApplicationContextListener;
import org.dumpling.commons.observer.annotation.Observer;
import org.dumpling.lang.L;
import org.springframework.context.ApplicationContext;

public class ObserverPatternStart implements Lifecycle {

	
	@Override
	public void start() {
		ApplicationContext cxt = ApplicationContextListener.getApplicationContext();
		
		Map<String, Object> m = cxt.getBeansWithAnnotation(Observer.class);
		Iterator<Entry<String, Object>> iterator = m.entrySet().iterator();
		
		while(iterator.hasNext()){
			Entry<String, Object> e = iterator.next();
			L.P("{0}={1}",e.getKey(),e.getValue());
		}
		
		Map<String, EventListener> listeners = cxt.getBeansOfType(EventListener.class);
		
		Iterator<Entry<String, EventListener>> it = listeners.entrySet().iterator();
		
		while(it.hasNext()){
			Entry<String, EventListener> e = it.next();
			register(e.getValue());
		}
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	//注册
	private void register(EventListener l){
		if(L.isNULL(l)){
			return ;
		}
		
		Observer ob = l.getClass().getAnnotation(Observer.class);
		if(ob == null){
			return ;
		}
		boolean syn = ob.syn();
		
		String[] eventNames = ob.eventNames();
		
		EventManager eventManager = EventManagerFactory.getDefaultManager();
		if(L.notEmpty(eventNames)){
			for(String eventName : eventNames){
				eventManager.bind(l, eventName, null, syn, null);
			}
		}
		
		Class<? extends Event>[] eventTypes = ob.eventTypes();
		
		if(L.notEmpty(eventTypes)){
			for(Class<? extends Event> eventType : eventTypes) {
				eventManager.bind(l, null, eventType, syn, null);
			}
		}
	}
}
