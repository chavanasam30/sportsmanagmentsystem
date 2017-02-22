package com.experian.finance;


import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter
{
	
	private boolean internalflag=false;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 =(HttpServletResponse) response;
		HttpSession session=request2.getSession(false);
		
			String username = request2.getParameter("username");
			//System.out.println("username "+username);
			if(null != session && !session.isNew())
			{
				System.out.println("************ Session ID : "+session.getId());
				chain.doFilter(request, response);
			}else {
				session=request2.getSession();
				System.out.println(" $$$$$$$$$$ Session ID : "+session.getId());
				if(username == null){
					//internalflag=true;
					response2.sendRedirect("/Finance/login.jsp");
				}
				System.out.println("internalflag  "+internalflag);
				/*internalflag=true;
				//response2.sendRedirect("/Finance/login.jsp");
				request2.getRequestDispatcher("/login.jsp").forward(
						request, response);
				return;*/
			}
			
			
	    }
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter Service has started");
    
	}
	
}
