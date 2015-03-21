package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecuperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Long codigo = Long.parseLong(checkNull(req.getParameter("codigo")));
		req.setAttribute("id", codigo);
		RequestDispatcher view = req.getRequestDispatcher("nuevopass.jsp");
		view.forward(req, resp);
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}