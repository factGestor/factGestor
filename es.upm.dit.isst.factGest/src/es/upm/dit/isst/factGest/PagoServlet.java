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

public class PagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String cantidadS = req.getParameter("amt");
		String id = req.getParameter("tx");//es el id de la transacion
		String moneda = req.getParameter("cc");
		String estado = req.getParameter("st");
		String importe=cantidadS+" "+moneda;
		String contratado=getInfo(cantidadS);
		String tarifa=getTarifa(cantidadS);
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		
		//si tiene suscripcion de tipo y aun le queda tiempo vigente, no tenemos que cambiar
		if(usuario.getTarifa().toString().compareTo("Suscripcion")!=0 && (tarifa.compareTo("Pago")!=0 || tarifa.compareTo("Free")!=0)){
			
		}
		daoUser.cambiar("condicionesContratacion", tarifa, usuario.getId());
		while(daoUser.getUsuario(usuario.getId()).getTarifa().toString().compareTo(tarifa)!=0){
			//esperamos que se cambie la tarifa en la base de datos
		}	
		
		misession.setAttribute("u", daoUser.getUsuario(usuario.getId()));
		//cambiamos el usuario que se muestra con la linea anterior.
		req.setAttribute("id", id);
		req.setAttribute("estado", estado);
		req.setAttribute("importe", importe);
		req.setAttribute("contratado", contratado);
		RequestDispatcher view = req.getRequestDispatcher("pagado.jsp");
		view.forward(req, resp);
	}
	
	private String getInfo(String cantidadS){
		Double cantidad = Double.parseDouble(cantidadS);
		if(cantidad==9.99){
			return "Suscripción mensual";
		}
		else if(cantidad==49.99){
			return "Suscripción de medio año";
		}
		else if(cantidad==79.99){
			return "Suscripción anual";
		}
		else{
			Integer numCons=Integer.parseInt(cantidadS);
			return "Pago por "+ numCons +" consultas";
		}
	}
	
	private String getTarifa(String cantidadS){
		Double cantidad = Double.parseDouble(cantidadS);
		if(cantidad==9.99||cantidad==49.99||cantidad==79.99){
			return "Suscripcion";
		}
		else{
			return "Pago";
		}
	}
}



