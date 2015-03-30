package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Usuario;

public class ConsultaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// Hay que comprobar si el get viene de un dominio autorizado.

		Boolean permitido = false;
		String dominio = req.getParameter("domain");

		if (dominio != null) {
			DominioDAO daoDominio = DominioDAOImpl.getInstance();
			Dominio dom = daoDominio.getDominioByName(dominio);
			if (dom != null) {
				UsuarioDAO userDominio = UsuarioDAOImpl.getInstance();
				Usuario user = userDominio.getUsuario(dom.getUserId());

				if (user.getPuedePeticiones())
					permitido = true;
			}
		}
		JSONObject json = new JSONObject();
		try {
			json.put("permitido", permitido);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		resp.getWriter().write(json.toString());
	}
}
