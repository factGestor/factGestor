package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Usuario;

public class ListarDominiosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		System.out.println("hemos entrado en ListarDominiosServlet");
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario u = (Usuario) misession.getAttribute("u");
		List<Dominio> dominiosLista = new ArrayList<Dominio>();
		
		System.out.println(u.getName());
		
		DominioDAO daoDominio = DominioDAOImpl.getInstance();
		dominiosLista = daoDominio.getDominios(u.getId());
		
		/*TRAZAS
		System.out.println(dominiosLista.get(0).getDomain());
		System.out.println(dominiosLista.get(0).getUserId());
		*/
		
		//pasar parametros
		misession.setAttribute("u", u);
		misession.setAttribute("dominios", new ArrayList<Dominio>(dominiosLista));
		
		RequestDispatcher view = req.getRequestDispatcher("dominios.jsp");
		view.forward(req, resp);
		
	}
}
