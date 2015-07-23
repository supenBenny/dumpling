package org.dumpling.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class LoggingAspectTest extends AbstractJUnit4SpringContextTests{

	@Test
	public void test(){
//		LoggingService ls = this.applicationContext.getBean(LoggingService.class);
//		
//		ls.log();
//		
//		ls.log("s11");
//		
//		ls.log("s22", 2);
	}

}
