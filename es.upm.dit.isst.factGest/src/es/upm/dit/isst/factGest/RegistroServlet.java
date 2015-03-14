package es.upm.dit.isst.factGest;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAO;
import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAOImpl;
import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.CuentaARegistrar;
import es.upm.dit.isst.factGest.model.Usuario;



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
			
			//busqueda del Id del usuario
			//da problemas pues se tarda en crear unos milisegundos, por eso ponemos el esperar
			while(daoUser.getId(name)==1){
			}
			Long userID = daoUser.getId(name);
			
			Iterator<String> it = dominiosLista.iterator();
			System.out.println("Lista dominios  "); //TRAZA 
			while (it.hasNext()) {
				
				String auxDominio = it.next();
				System.out.println(auxDominio);
				daoDominio.add(auxDominio, userID);
			}
			
			//construccion del objeto cuentaARegistrar
			CuentasARegistrarDAO daoCuentaARegistrar = CuentasARegistrarDAOImpl.getInstance();
			daoCuentaARegistrar.add(userID);
			
			//envio de correo con el id
			CuentaARegistrar cuentaARegistrar = daoCuentaARegistrar.getCuentaARegistrarUser(userID);
			Usuario usuario = daoUser.getUsuario(userID);
			try {
				envioCorreo(cuentaARegistrar, usuario);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		int b = 0;
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
				if(dominiosRestantes.length()>0)
					listaDominios.add(dominiosRestantes);
				break;
			}
		}
		
		System.out.println(listaDominios);
		return listaDominios;
	}

	
	private void envioCorreo(CuentaARegistrar cuentaARegistrar, Usuario usuario) throws UnsupportedEncodingException, MessagingException{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props,	null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("noreply@fact-gest.appspotmail.com", "Gestion de facturas"));
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(usuario.getEmail(),usuario.getName()));
				msg.setSubject("Validaci�n de registro en FACT GEST");
				String msgBody = "Para verificar su cuenta acceda al siguiente enlace: "
						+ "" + System.getProperty("line.separator") + 
						"http://fact-gest.appspot.com/confirmacion?codigo="+ cuentaARegistrar.getId();
						//DIRECCION A LA QUE TIENE QUE ACCEDER
				msgBody += System.getProperty("line.separator") +
						"Atentamente un saludo," + System.getProperty("line.separator") + "Equipo de Gesti�n de facturas";
				msg.setText(msgBody);
				Transport.send(msg);
	}
	
}
