package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.model.Usuario.Tarifas;

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
				if(puedePeticiones(user))
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
	
	public boolean puedePeticiones(Usuario usuario){
		if(usuario.getConfirmado()){
			if(usuario.getTarifa()==Tarifas.Suscripcion){
				Date fechaSuscrito = usuario.getFechaSuscripcion();
				Date fechaActual = new Date();
				if(fechaSuscrito.getTime()-fechaActual.getTime()>=0)
					return true;
				else{
					if(usuario.getConsultasDisponibles()>50){
						usuario.setTarifa(Tarifas.Pago);
					}
					else{
						usuario.setTarifa(Tarifas.Free);
					}
					return false;
				}
			}
			//casos de Pago y de Free
			else
				if(usuario.getConsultasDisponibles()>0){
					//descontar 1
					UsuarioDAO userDominio = UsuarioDAOImpl.getInstance();
					userDominio.descontarConsulta(usuario.getId());
					return true;
				}
				else
					return false;
		}
		else
			return false;
	}
}
