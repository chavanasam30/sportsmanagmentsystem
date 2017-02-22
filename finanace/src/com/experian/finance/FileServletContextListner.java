package com.experian.finance;


import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FileServletContextListner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String rootPath = System.getProperty("catalina.home");
		ServletContext ctx = servletContextEvent.getServletContext();
		String relativePath = ctx.getInitParameter("tempdir");
		File file = new File(rootPath + File.separator + relativePath);
		System.out.println("File_path ******** "+(rootPath + File.separator + relativePath) );
		if(!file.exists()) 
			file.mkdirs();
		ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
	}

	
}
