package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class LogoutServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		
		req.getSession().invalidate(); //borramos session
		System.out.println("Session borrada");
		Cookie[] cookies = req.getCookies(); //cogemos cookies
		if (cookies != null)
	        for (int i = 0; i < cookies.length; i++) {
	        	//borramos cookies
	            cookies[i].setValue("");
	            cookies[i].setPath("/");
	            cookies[i].setMaxAge(0);
	            resp.addCookie(cookies[i]);
	        }
		//cookies borradas
		System.out.println("Cookies borrada");
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req, resp);
	}
	
	
	
}
