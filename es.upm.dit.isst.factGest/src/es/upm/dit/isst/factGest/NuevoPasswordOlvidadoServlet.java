package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;

public class NuevoPasswordOlvidadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Long userId = Long.parseLong(checkNull(req.getParameter("userId").toString()));
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		//public void cambiar(String nombre, String dato, Long userId);
		daoUser.cambiar("password", req.getParameter("password"), userId);
		resp.sendRedirect("exito.html");

	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
