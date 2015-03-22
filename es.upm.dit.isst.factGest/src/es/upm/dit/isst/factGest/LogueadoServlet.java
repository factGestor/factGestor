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

public class LogueadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("Llamada tipo POST  ");
		Usuario u = null;
		String name = req.getParameter("login");
		String password = req.getParameter("password");
		HttpSession misession = req.getSession(true);
		
		
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		if (daoUser.comprobarLogin(name, password)) {
			// logueo correcto
			// sacamos los atributos del usuario (id, name, CIF y mail)
			Long id = daoUser.getId(name);
			u = daoUser.getUsuario(id);
			misession.setAttribute("u", u);
			RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
			view.forward(req, resp);
		} else {
			req.getSession().setAttribute("error",
					"Usuario o Password incorrectos");
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			view.forward(req, resp);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		req.getSession().setAttribute("errores", "");
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req, resp);
	}
}
