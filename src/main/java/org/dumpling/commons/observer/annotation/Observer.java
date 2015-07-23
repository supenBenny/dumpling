package org.dumpling.commons.observer.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dumpling.commons.observer.Event;

/**
 * <p>标记为观察者,对象需实现EventListener接口</p>
 * <p>发布的事件只需满足eventTypes或eventName之一,且仅触发一次</p>
 * @author rabbit
 * @date  2013-7-16
 *
 */
@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Observer {
	
	/**
	 * 监听事件类型，具体实现类型
	 * @return
	 */
	Class<? extends Event>[] eventTypes();
	
	/**
	 * 事件名称
	 * @return
	 */
	String[] eventNames();
	
	/**
	 * 是否同步触发
	 * @return
	 */
	boolean syn() default true;

}
