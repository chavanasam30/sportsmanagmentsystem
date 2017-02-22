package com.experian.finance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginModel extends HttpServlet {

	private boolean flag = false;

	private String userName1 = "User1";
	private String password1 = "User1";

	private String userName2 = "User2";
	private String password2 = "User2";

	private TreeSet<String> closedUserGroup = new TreeSet<String>();
	static Properties closeGroupProps = new Properties();
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			closeGroupProps.load(new FileReader(new File(System.getenv("EXP_ROOT")+File.separator+"Finance"+File.separator+"resources"+File.separator+"closed_user_group.properties")));
			for (Object closed1 : closeGroupProps.keySet()) {
				closedUserGroup.add(closed1.toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			File fi = new File(".");
//			System.out.println(fi.getAbsolutePath());
//			BufferedReader reader = new BufferedReader(new FileReader("closed_user_group.properties"));			String line = null;
//			while (null != (line=reader.readLine()))
//				closedUserGroup.add(line.toLowerCase());
//			reader.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		/*closedUserGroup.add("c02809a");
		closedUserGroup.add("c02887a");
		closedUserGroup.add("rathipa");*/
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uName = request.getParameter("username");
		String password = request.getParameter("password");
		
		//System.out.println("uName :" + uName);
		if(null != uName){
		/*if (checkStaticLogin(uName, password)) {
			System.out.println("Came here inside login model");
			getServletContext().getRequestDispatcher("/HomePage.jsp").forward(
					request, response);
			return;
		}else {*/
			checkActiveDirectory(request, response, uName, password);
		//}
		}else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(
					request, response);
			return;
		}
			
		//System.out.println("########### "+closedUserGroup.toString());
	}

	private boolean checkStaticLogin(String uName, String password) {
		return (uName.equalsIgnoreCase(userName1) && password
				.equalsIgnoreCase(password1))
				|| (uName.equalsIgnoreCase(userName2) && password
						.equalsIgnoreCase(password2));
	}

	private void checkActiveDirectory(HttpServletRequest request,
			HttpServletResponse response, String uName, String password) {
		try {
			try {
				String userprincipalname = uName + "@uk.experian.local";
				getActiveDirectoryContext(userprincipalname, password);

			} catch (NamingException e) {
				//PrintWriter writer = response.getWriter();
				//writer.print("<h3 font= red>Wrong username or password</h3>");
				request.setAttribute("flag", "false");
				request.setAttribute("Error", "Wrong username or password.");
				request.getRequestDispatcher("/login.jsp").include(
						request, response);
				return;
			}
			
			/**
			 * Check against Close User Group
			 */
			if (!closedUserGroup.contains(uName.toLowerCase())){
				// Not in Closed User Group		
				request.setAttribute("flag", "false");
				request.setAttribute("Error", "User does not have access to this function");
				//PrintWriter writer = response.getWriter();
				//writer.print("<h3 font= red>User does not have access to this function</h3>");
				request.getRequestDispatcher("/login.jsp").include(
						request, response);
				return;
			}
			
			System.out.println("Came here inside login model");
			getServletContext().getRequestDispatcher("/HomePage.jsp").forward(
					request, response);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private DirContext getActiveDirectoryContext(String securityPrincipal,
			String password) throws NamingException {
		Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
		ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		ldapEnv.put(Context.PROVIDER_URL,
				"ldap://ldapnottingham.uk.experian.local:389");
		ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		ldapEnv.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
		DirContext ldapContext = new InitialDirContext(ldapEnv);
		return ldapContext;
	}

}
