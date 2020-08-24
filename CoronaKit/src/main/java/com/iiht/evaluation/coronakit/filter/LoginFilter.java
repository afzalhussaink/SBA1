package com.iiht.evaluation.coronakit.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/admin")
public class LoginFilter implements Filter {

    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("****in filter***");
		HttpServletRequest req = (HttpServletRequest) request;
		String action = request.getParameter("action");
		if("login".equals(action)) {
			chain.doFilter(request, response);
		} else if(Objects.isNull(req.getSession().getAttribute("loginFlag"))){
			request.setAttribute("action", "nologin");
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		} else {
			boolean loginFlag = (boolean) req.getSession().getAttribute("loginFlag");
			System.out.println("loginFlag:"+loginFlag);
			if(loginFlag) {
				chain.doFilter(request, response);
			}else {
				
				RequestDispatcher dispatch = 
						request.getRequestDispatcher("index.jsp");
				dispatch.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
