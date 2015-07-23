package org.dumpling.commons.bootstrap;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dumpling.lang.L;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BootstrapListener implements ServletContextListener{
	
	public static ApplicationContext ctx = null;
	
	private Map<String, Object> bootstraps = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		
	/*	Map<String, EventListener> map = this.ctx.getBeansOfType(EventListener.class);
		L.P("EventListener:getBeansOfType");
		Iterator<Entry<String, EventListener>> itLt = map.entrySet().iterator();
		
		while(itLt.hasNext()){
			Entry<String, EventListener> e = itLt.next();
			L.P("name:{0},type:{1}", e.getKey(),e.getValue());
		}
		
		//启动服务
		this.bootstraps = this.ctx.getBeansWithAnnotation(Bootstrap.class);
		
		if(L.notEmpty(this.bootstraps)) {
			Iterator<Object> it = this.bootstraps.values().iterator();
			while(it.hasNext()){
				Object e = it.next();
				if(e instanceof Lifecycle){
					((Lifecycle)e).start();
				}
			}
		}*/
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//停止服务
		if(L.notEmpty(this.bootstraps)) {
			Iterator<Object> it = this.bootstraps.values().iterator();
			while(it.hasNext()){
				Object e = it.next();
				if(e instanceof Lifecycle){
					((Lifecycle)e).stop();
				}
			}
		}
	}

}
