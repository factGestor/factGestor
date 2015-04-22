package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.SesionDAO;
import es.upm.dit.isst.factGest.dao.SesionDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Sesion;
import es.upm.dit.isst.factGest.model.Usuario;

public class LogueadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		Usuario u = null;
		String name = req.getParameter("login");
		String password = req.getParameter("password");
		SesionDAO daoSesion = SesionDAOImpl.getInstance();
		HttpSession misession = req.getSession();

		String pass = Seguridad.hashPass(password);

		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		if (daoUser.comprobarLogin(name, pass)) {
			// logueo correcto
			// sacamos los atributos del usuario (id, name, CIF y mail)
			Long id = daoUser.getId(name);
			u = daoUser.getUsuario(id);
			daoSesion.remove(id);
			misession.setAttribute("u", u);
			RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
			addCookie(resp,UUID.randomUUID().toString(),600);
			System.out.println("cookie agregada");
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
		HttpSession misession = req.getSession();
		Usuario u = (Usuario) misession.getAttribute("u");
		System.out.println("getA");
		if (u == null) {
			System.out.println("getB");
			String uuid = getCookieValue(req);
			if (uuid == null) {
				req.getSession().setAttribute("errores", "");
				RequestDispatcher view = req.getRequestDispatcher("index.jsp");
				view.forward(req, resp);
				return;
			} else {
				SesionDAO daoSesion = SesionDAOImpl.getInstance();
				Sesion s = daoSesion.getSesion(uuid);
				if (s != null) {
					UsuarioDAO daoUsuario = UsuarioDAOImpl.getInstance();
					u = daoUsuario.getUsuario(s.getUserId());
				} else {
					addCookie(resp, null, 0);
					req.getSession().setAttribute("errores",
							"Su sesion ha expirado");
					RequestDispatcher view = req
							.getRequestDispatcher("index.jsp");
					view.forward(req, resp);
					return;
				}
			}
		}
		misession.setAttribute("u", u);
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
	}

	public static String getCookieValue(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("taxysesion")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void addCookie(HttpServletResponse response, String valor,
			int caduca) {
		Cookie cookie = new Cookie("taxysesion", valor);
		cookie.setPath("/");
		cookie.setMaxAge(caduca);
		response.addCookie(cookie);
	}
}
