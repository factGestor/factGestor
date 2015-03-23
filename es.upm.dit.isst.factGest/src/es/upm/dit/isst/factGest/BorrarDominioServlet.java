package es.upm.dit.isst.factGest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.model.Dominio;

public class BorrarDominioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		DominioDAO daoDominio = DominioDAOImpl.getInstance();
		Dominio dominio = daoDominio.getDominio(Long.parseLong(id));
		daoDominio.remove(Long.parseLong(id));
		
		while(daoDominio.getDominio(dominio.getId())!=null){
			//para evitar poner en la lista uno borrado
		}
		
		String info = "Dominio " + dominio.getDomain() + " ha sido borrado.";
		req.getSession().setAttribute("info", info);
		
		resp.sendRedirect("/listarDominios");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("BorrarDominioServlet DOPOST");
	}
}
