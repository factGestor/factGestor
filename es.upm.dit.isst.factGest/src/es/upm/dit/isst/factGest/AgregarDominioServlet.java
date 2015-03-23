package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;

public class AgregarDominioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("AgregarDominioServlet DOPOST");
		String dominio = req.getParameter("agregarDominio");
		HttpSession misession= (HttpSession) req.getSession();
		Usuario u = (Usuario) misession.getAttribute("u");
		
		
		
		DominioDAO daoDominio = DominioDAOImpl.getInstance();
		Long dominioId = daoDominio.add(dominio, u.getId());
		
		
		while(daoDominio.getDominio(dominioId)==null){
			//para evitar poner en la lista uno borrado
		}
		
		String info = "Dominio " + dominio + " ha sido agregado.";
		req.getSession().setAttribute("info", info);
		
		resp.sendRedirect("/listarDominios");
		
	}
}
