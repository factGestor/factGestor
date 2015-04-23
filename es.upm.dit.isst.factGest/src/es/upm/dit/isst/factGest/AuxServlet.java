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

public class AuxServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		
		
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		
		//vamos a añadir 1 consulta a las disponibles del usuario (funciona)
		//daoUser.anadirConsultas(1, usuario.getId());
		
		//vamos a añadir 1 mes a la fecha de suscripcion
		System.out.println("ANTES DE MODIFICARLO : "+usuario.getFechaSuscripcion());
		daoUser.anadirSuscripcion(1, usuario.getId());
		System.out.println("ANTES DE MODIFICARLO : "+daoUser.getUsuario(usuario.getId()).getFechaSuscripcion());
		
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
		
		
	}
	
}
