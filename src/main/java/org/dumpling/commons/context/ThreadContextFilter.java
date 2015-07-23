package org.dumpling.commons.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class ThreadContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);
		//销毁当前线程内所有线程变量
		ThreadContext.getCurrentContext().destory();
	}

	@Override
	public void destroy() {
		ThreadContext.getCurrentContext().destory();
	}

}
