package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;

public class LogueadoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Llamada tipo POST  ");
		Usuario u = null;
		String name = checkNull(req.getParameter("login"));
		String password = checkNull(req.getParameter("password"));
		
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		if (daoUser.comprobarLogin(name, password)){
			//logueo correcto
			//sacamos los atributos del usuario (id, name, CIF y mail)
			Long id=daoUser.getId(name);
			System.out.println("TRAZA 1; ID = "+ id);
			u = daoUser.getUsuario(id);
			/*
			 * Busqueda de los dominios por el userId
			DominioDAO daoDominio = DominioDAOImpl.getInstance();
			daoDominio.getDominios(id);
			*/
			//pasamos los datos a la pagina
			req.getSession().setAttribute("u", u);
			/*
			req.getSession().setAttribute("usuario", u.getName());
			req.getSession().setAttribute("CIF", u.getCIF());
			req.getSession().setAttribute("email", u.getEmail());
			*/
			RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
			view.forward(req, resp);
			/*
			 * req.getSession().setAttribute("user", user);
			 * req.getSession().setAttribute("todos", new ArrayList<Todo>(todos));
			 * req.getSession().setAttribute("url", url);
			 * req.getSession().setAttribute("urlLinktext", urlLinktext);
			 * RequestDispatcher view = req.getRequestDispatcher("TodoApplication.jsp");
			 * view.forward(req, resp);
			 */
			
		}
		else{
			//logueo incorrecto
		}
		
		
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
