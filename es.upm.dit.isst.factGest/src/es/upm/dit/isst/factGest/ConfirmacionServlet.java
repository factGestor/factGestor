package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;

public class ConfirmacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long codigo = Long.parseLong(checkNull(req.getParameter("codigo")));
	
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		daoUser.verificar(codigo);
		

		resp.sendRedirect("confirma.html");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
