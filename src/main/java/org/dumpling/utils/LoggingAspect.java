package org.dumpling.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.dumpling.lang.L;


public class LoggingAspect {
	
	   public void beforeAdvice(){
	      System.out.println("beforeAdvice");
	   }

	   
	   public void afterAdvice(){
	      System.out.println("afterAdvice");
	   }

	
	   public void afterReturningAdvice(Object retVal){
	      System.out.println("afterReturningAdvice Returning:" + retVal.toString() );
	   }

	  
	   public void AfterThrowingAdvice(IllegalArgumentException ex){
	      System.out.println("There has been an exception: " + ex.toString());   
	   }
	   
	   /**
		 * aop around 通知
		 * @param pjp
		 * @return
		 * @throws Throwable
		 */
		public Object runTime(ProceedingJoinPoint pjp) throws Throwable{
			Stopwatch sw = Stopwatch.begin();
			
			Object o = pjp.proceed();
			sw.stop();
			L.P("执行方法(" + pjp.getSignature().getName()+")耗时:"+sw.getDuration()+ "毫秒");
			L.P(sw);
			return o;
		}

}
