package es.upm.dit.isst.factGest;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import java.util.Properties;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(
					"noreply@taxy-gest.appspotmail.com", "TAXY Gestion de facturas"));
			System.out.println(req.getAttribute("email").toString());
			System.out.println(req.getAttribute("nombre").toString());
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(req.getAttribute("email").toString(),
							req.getAttribute("nombre").toString()));
			msg.setSubject("Validacion de registro en TAXY GEST");
			String msgBody = "Estimado " + req.getAttribute("nombre").toString()
					+ ", para verificar su cuenta acceda al siguiente enlace: "
					+ "" + System.getProperty("line.separator")
					+ "http://taxy-gest.appspot.com/confirmacion?codigo="
					+ req.getAttribute("codigo").toString();
			// DIRECCION A LA QUE TIENE QUE ACCEDER
			msgBody += System.getProperty("line.separator")
					+ "Atentamente un saludo,"
					+ System.getProperty("line.separator")
					+ "Equipo de Gestion de facturas TAXY";
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("registrado.html");
	}
}
