package es.upm.dit.isst.factGest;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;


public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.print("Llamada tipo POST  ");
		System.out.println("Creacion de nuevo usuario");
		//PARTE QUE MIRA SI ESTAS LOGUEADO EN GOOGLE
		/*User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}*/
		String name = checkNull(req.getParameter("name"));
		String correo = checkNull(req.getParameter("correo"));
		String cif = checkNull(req.getParameter("cif"));
		String password = checkNull(req.getParameter("password"));
		String repassword = checkNull(req.getParameter("repassword"));
		
		if(password.equals(repassword)){
			UsuarioDAO dao = UsuarioDAOImpl.getInstance();
			//public void add(String name, String password, String CIF, String email)
			dao.add(name, password, cif, correo);
		}
		resp.sendRedirect("/");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.print("Llamada tipo GET  ");
		System.out.println("Creacion de nuevo usuario");
		//PARTE QUE MIRA SI ESTAS LOGUEADO EN GOOGLE
		/*User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}*/
		String name = checkNull(req.getParameter("name"));
		String correo = checkNull(req.getParameter("correo"));
		String cif = checkNull(req.getParameter("cif"));
		String password = checkNull(req.getParameter("password"));
		String repassword = checkNull(req.getParameter("repassword"));
		
		if(password.equals(repassword)){
			UsuarioDAO dao = UsuarioDAOImpl.getInstance();
			//public void add(String name, String password, String CIF, String email)
			dao.add(name, password, cif, correo);
		}
		resp.sendRedirect("/");
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

}
