package es.upm.dit.isst.factGest;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.Properties;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;

public class OlvidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		UsuarioDAO userDao = UsuarioDAOImpl.getInstance();
		String nombreUser = req.getParameter("nombre");
		
		if(userDao.getId(nombreUser)!=1L){
		//Primero recuperamos los datos del usuario según el nombre introducido
		Usuario user = userDao.getUsuario(userDao.getId(nombreUser));
		String emailUser = user.getEmail();
		
		//Ahora creamos el correo que le mandaremos y se lo enviamos
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(
						"noreply@taxy-gest.appspotmail.com",
						"TAXY Gestion de facturas"));
				System.out.println(emailUser);
				System.out.println(nombreUser);
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						emailUser, nombreUser));
				msg.setSubject("Recuperacion de password en TAXY GEST");
				String msgBody = "Estimado "
						+ nombreUser
						+ ", para cambiar su contraseña acceda al siguiente enlace: "
						+ "" + System.getProperty("line.separator")
						+ "http://taxy-gest.appspot.com/recuperacion?codigo="
						+ user.getId();
				// DIRECCION A LA QUE TIENE QUE ACCEDER
				msgBody += System.getProperty("line.separator")
					+ "Atentamente un saludo,"
					+ System.getProperty("line.separator")
					+ "Equipo de Gestion de facturas";
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("solicitado.html");
	} else {
		req.setAttribute("error",
				"Usuario inexistente.");
		RequestDispatcher view = req.getRequestDispatcher("restaurar.jsp");
		view.forward(req, resp);
	}
	}
}