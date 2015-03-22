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

public class CambiarCuentaBancariaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		System.out.println("ESTAMOS EN EL METODO DOPOST DE CambiarCuentaBancariaServlet");
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		String cuentaBancaria = req.getParameter("cuentaBancaria");
		String info = "";
		
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		
		daoUser.cambiar("cuentaBancaria", cuentaBancaria, usuario.getId());
		
		info = info + " Cuenta bancaria cambiada.";	
		req.getSession().setAttribute("info", info);
		
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
		
	}
}
