package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;



public class CambiarPasswordServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		String info  = "";
		
		System.out.println("ESTAMOS EN EL METODO DOPOST DE CAMBIARPASSWORDSERVLET");
		System.out.println(usuario.getName());
		
		if (password.equals(repassword)){
		
			UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
			String passCifrada = Seguridad.hashPass(password);
			
			daoUser.cambiar("password", passCifrada, usuario.getId());
			info = info + "Contraseña cambiada.";
		}
		req.getSession().setAttribute("info", info);
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		System.out.println("ESTAMOS EN EL METODO DOGET DE CAMBIARPASSWORDSERVLET");
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
	}
	
}
