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

public class CambiarCondicionesContratacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		System.out.println("ESTAMOS EN EL METODO DOPOST DE CambiarCondicionesContratacionServlet");
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		String cuentaBancaria = req.getParameter("cuentaBancaria");
		String tarifaString = req.getParameter("tarifa");
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		String info = "";
		
		daoUser.cambiar("condicionesContratacion", tarifaString, usuario.getId());
		
		info = info + " Condiciones de contratacion cambiadas.";		
		req.getSession().setAttribute("info", info);
		
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
		
	}
}
