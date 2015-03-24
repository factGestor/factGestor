package es.upm.dit.isst.factGest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAO;
import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAOImpl;
import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.Usuario;

public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String errores = "";
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();

		String nombre = req.getParameter("name");
		String correo = req.getParameter("correo");
		String cif = req.getParameter("cif");

		String dominios = req.getParameter("dominio2");
		List<String> dominiosLista = new ArrayList<String>();

		if (dominios != "") {
			dominiosLista = sacarDominios(dominios);
		} else {
			dominiosLista.add("");
		}

		if (dominiosLista == null) {
			errores = errores + " Dominios con formato erroneo.";
		}
		List<Usuario> usersForName = daoUser.getUsuariosBy("name", nombre);
		if (!usersForName.isEmpty()) {
			errores = errores + "Nombre de usuario en uso.";
		}
		List<Usuario> usersForCIF = daoUser.getUsuariosBy("CIF", cif);
		if (!usersForCIF.isEmpty()) {
			errores = errores + " CIF ya registrado.";
		}
		List<Usuario> usersForEmail = daoUser.getUsuariosBy("email", correo);
		if (!usersForEmail.isEmpty()) {
			errores = errores + " Email ya registrado.";
		}

		if (errores == "") {
			String password = req.getParameter("password");

			String passCifrada = Seguridad.hashPass(password);

			Long userId = daoUser.add(nombre, passCifrada, cif, correo, false);
			// sacar dominios
			DominioDAO daoDominio = DominioDAOImpl.getInstance();

			while (userId == null) {
			}

			Iterator<String> it = dominiosLista.iterator();
			while (it.hasNext()) {
				String[] aux1 = it.next().split("http://www.");
				String[] auxDominio = aux1[aux1.length - 1]
						.split("https://www.");
				daoDominio.add(auxDominio[auxDominio.length - 1], userId);
			}

			CuentasARegistrarDAO daoCuentaARegistrar = CuentasARegistrarDAOImpl
					.getInstance();
			Long id = daoCuentaARegistrar.add(userId);

			req.setAttribute("codigo", id);
			req.setAttribute("email", correo);
			req.setAttribute("nombre", nombre);
			RequestDispatcher rd = req.getRequestDispatcher("/emailconfirm");
			rd.forward(req, resp);
			return;
		} else {
			req.getSession().setAttribute("error", errores);
			RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
			view.forward(req, resp);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		RequestDispatcher view = req.getRequestDispatcher("registro.jsp");
		view.forward(req, resp);
	}

	private List<String> sacarDominios(String dominios) {
		List<String> listaDominios = new ArrayList<String>();
		String dominioAnadir;
		String dominiosRestantes = dominios;
		int a;
		if (dominiosRestantes.indexOf(";") == -1) {
			listaDominios.add(dominios);
		}
		while (true) {
			a = dominiosRestantes.indexOf(";");

			if (a != -1) {
				dominioAnadir = dominiosRestantes.substring(0, a);
				if (!checkURI(dominioAnadir))
					return null;
				dominiosRestantes = dominios.substring(a + 1,
						dominiosRestantes.length());
				listaDominios.add(dominioAnadir);
			} else {
				if (dominiosRestantes.length() > 0)
					listaDominios.add(dominiosRestantes);
				break;
			}
		}

		System.out.println(listaDominios);
		return listaDominios;
	}

	private Boolean checkURI(String uri) {
		try {
			URL url = new URL(uri);
			url.toURI();
			if ((uri.indexOf("http://www.") == -1)
					&& (uri.indexOf("https://www.") == -1)) {
				return null;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
