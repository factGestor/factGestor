package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;

public class CambiarEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		
		HttpSession misession= (HttpSession) req.getSession();
		Usuario usuario = (Usuario) misession.getAttribute("u");
		String correo = req.getParameter("correo");
		String info = "";
		
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		List<Usuario> usersForEmail = daoUser.getUsuariosBy("email", correo);
		if (!usersForEmail.isEmpty()) {
			info = info + " Email ya registrado.";
		}
		else{
			daoUser.cambiar("email", correo, usuario.getId());
			info = info + " Email cambiado.";
		}	
		//tal vez sea que el cambio no se produce aun
		while(daoUser.getUsuario(usuario.getId()).getEmail().compareTo(correo)!=0){
			//espera a que cambie el mail
			/*	TRAZAS
			 * 	System.out.println("dao " + daoUser.getUsuario(usuario.getId()).getEmail());
				System.out.println(correo);
				System.out.println("_____________________________");
			 */
		}
		//misession.invalidate();//cerramos la session
		//misession = req.getSession(true);//volvemos a crearla
		req.getSession().setAttribute("info", info);
		misession.setAttribute("u", daoUser.getUsuario(usuario.getId()));
		RequestDispatcher view = req.getRequestDispatcher("logueado.jsp");
		view.forward(req, resp);
		
	}
}
