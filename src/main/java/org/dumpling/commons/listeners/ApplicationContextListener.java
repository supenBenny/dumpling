package org.dumpling.commons.listeners;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dumpling.commons.bootstrap.Lifecycle;
import org.dumpling.commons.bootstrap.annotation.Bootstrap;
import org.dumpling.commons.observer.EventListener;
import org.dumpling.lang.L;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * web系统启动监听
 * @author rabbit
 * @date  2013-8-11
 *
 */
public class ApplicationContextListener implements ServletContextListener {

	public static ApplicationContext ctx = null;

	private Map<String, Object> bootstraps = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		Enumeration<String> names = sce.getServletContext().getAttributeNames();
//		while(names.hasMoreElements()){
//			String name = names.nextElement();
//			L.P("name:{0},value:{1}", name,sce.getServletContext().getAttribute(name));
//		}
		
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sce
				.getServletContext());

		Map<String, EventListener> map = ctx
				.getBeansOfType(EventListener.class);
		L.P("EventListener:getBeansOfType");
		Iterator<Entry<String, EventListener>> itLt = map.entrySet().iterator();
		
		while (itLt.hasNext()) {
			Entry<String, EventListener> e = itLt.next();
			L.P("name:{0},type:{1}", e.getKey(), e.getValue());
		}

		// 启动服务
		this.bootstraps = ctx.getBeansWithAnnotation(Bootstrap.class);

		if (L.notEmpty(this.bootstraps)) {
			Iterator<Object> it = this.bootstraps.values().iterator();
			while (it.hasNext()) {
				Object e = it.next();
				if (e instanceof Lifecycle) {
					((Lifecycle) e).start();
				}
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 停止服务
		if (L.notEmpty(this.bootstraps)) {
			Iterator<Object> it = this.bootstraps.values().iterator();
			while (it.hasNext()) {
				Object e = it.next();
				if (e instanceof Lifecycle) {
					((Lifecycle) e).stop();
				}
			}
		}
	}

	public static ApplicationContext getApplicationContext(){
		return ctx;
	}
	
}
