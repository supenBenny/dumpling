package org.dumpling.utils;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dumpling.lang.L;
import org.springframework.stereotype.Component;

/**
 * 监控方法处理时长
 * @author rabbit
 * @since  2013-2-15
 *
 */
@Component
@Aspect
public class ProcessTimeAspect {
	
	private Map<String,Method> timer = new HashMap<String,Method>();
	
	@Around("execution(* org.dumpling.utils.*.*(..))")
	public Object process(ProceedingJoinPoint pjp) throws Throwable{

		Stopwatch sw = Stopwatch.begin();
		Object o = pjp.proceed();
		sw.stop();
		//L.P("执行方法(" + pjp.getSignature().getName()+")耗时:"+sw.getDuration()+ "毫秒");
		//L.P(sw);
		
		String id = pjp.getSignature().toLongString();
		Method m = timer.get(id);
		if(m == null){
			m = new Method(id);
			timer.put(id, m);
		}
		m.increase();
		m.addDuration(sw.getDuration());
		return o;
	
	}
	
	
	class Method {
		
		private int i = 0;
		
		private long total = 0L;
		
		private String id = null;
		
		public Method(){
			
		}
		
		public Method(String id){
			this.id = id;
		}
		
		public Method(String id,int time){
			this.increase();
			this.addDuration(time);
		}
		
		/**
		 * 方法执行一次耗时时间
		 * @param time
		 * @return
		 */
		public long addDuration(long time){
			total = total + time;
			return total;
		}
		
		public int increase(){
			return ++i;
		}
		
		public long getAverage(){
			if(i !=0){
				return total/i;
			}
			return 0;
		}
	}

}
