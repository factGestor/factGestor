package es.upm.dit.isst.factGest;

import javax.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;


public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.print("Llamada tipo POST  ");
		System.out.println("Creacion de nuevo usuario");
		//PARTE QUE MIRA SI ESTAS LOGUEADO EN GOOGLE
		/*User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}*/
		String name = checkNull(req.getParameter("name"));
		String correo = checkNull(req.getParameter("correo"));
		String cif = checkNull(req.getParameter("cif"));
		String password = checkNull(req.getParameter("password"));
		String repassword = checkNull(req.getParameter("repassword"));
		String dominios = checkNull(req.getParameter("dominio2"));
		List<String> dominiosLista = null;
		
		if(password.equals(repassword)){
			UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
			//public void add(String name, String password, String CIF, String email)
			daoUser.add(name, password, cif, correo, false);
			//sacar dominios 
			//si es dominios distinto de vacio entonces tendremos que mirar cuantos tenemos
			if (dominios!=""){
				dominiosLista = sacarDominios(dominios);
			}
			else{
				dominiosLista.add("");
			}
			DominioDAO daoDominio = DominioDAOImpl.getInstance();
			System.out.println("TRAZA 1"); //TRAZA
			//busqueda del Id del usuario
			//da problemas pues se tarda en crear unos milisegundos, por eso ponemos el esperar
			esperar(1000);
			Long userID = daoUser.getId(name);
			System.out.println("TRAZA 2"); //TRAZA
			Iterator<String> it = dominiosLista.iterator();
			 
			while (it.hasNext()) {
			 
				String auxDominio = it.next();
				daoDominio.add(auxDominio, userID);
			}
			
		}
		resp.sendRedirect("/");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.print("Llamada tipo GET  ");
		
		resp.sendRedirect("/");
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	private List<String> sacarDominios (String dominios){
		List<String> listaDominios = new ArrayList<String>();
		String dominioAnadir;
		String dominiosRestantes = dominios;
		int a;
		System.out.println("TRAZA DE sacarDominios "); //TRAZA
		//si no hay un ; sera que solo hay uno
		if (dominiosRestantes.indexOf(";")==-1){
			listaDominios.add(dominios);
		}
		while(true){
			a = dominiosRestantes.indexOf(";");
			if (a!=-1){
				dominioAnadir = dominiosRestantes.substring(0, a);
				dominiosRestantes = dominios.substring(a+1, dominiosRestantes.length());
				listaDominios.add(dominioAnadir);
			}
			else{
				break;
			}
		}
		
		System.out.println(listaDominios);
		return listaDominios;
	}
	private void esperar (int milisegundos) {
		try {
		Thread.sleep (milisegundos);
		} catch (Exception e) {
		// Mensaje en caso de que falle
		}
		}

}
