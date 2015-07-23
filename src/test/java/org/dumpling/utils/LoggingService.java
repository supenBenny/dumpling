package org.dumpling.utils;

import org.dumpling.lang.L;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
	
	public void log(){
		L.P("0");
	}
	
	public void log(String s){
		L.P("一个参数");
	}
	
	public void log(String s,int i){
		L.P("两个参数");
	}

}
