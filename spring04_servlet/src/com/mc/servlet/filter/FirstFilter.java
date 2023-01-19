package com.mc.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FirstFilter
 */
public class FirstFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		// place your code here
		System.out.println("FirstFilter 호출");
		System.out.println(req.getSession().getAttribute("user"));
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("FirstFilter 파괴한다.");
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(filterConfig.getFilterName());
	}

}
