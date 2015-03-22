package es.upm.dit.isst.factGest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;

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
import es.upm.dit.isst.factGest.model.Usuario.Tarifas;

public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		
		String errores = "";
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();

		String nombre = req.getParameter("name");
		String correo = req.getParameter("correo");
		String cif = req.getParameter("cif");
		String tarifaString = req.getParameter("tarifa");
		String cuentaBancaria = "";
		Tarifas tarifa = Tarifas.Free;

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
		if(!tarifaString.equals("Free")){
			cuentaBancaria = req.getParameter("cuentaBancaria");
			if(cuentaBancaria.isEmpty()){
				errores = errores + " Para clientes de pago debe de introducir una cuenta bancaria.";
			}
		}
		if(tarifaString.equals("Pago")){
			tarifa = Tarifas.Pago;
		}
		if(tarifaString.equals("Suscripcion")){
			tarifa = Tarifas.Suscripcion;
		}

		if (errores == "") {
			String password = req.getParameter("password");
			String repassword = req.getParameter("repassword");
			String dominios = req.getParameter("dominio2");
			List<String> dominiosLista = new ArrayList<String>();

			if (password.equals(repassword)) {
				//poner el primer true a false, es para que no requiera confirmacion por email
				Long userId = daoUser.add(nombre, password, cif, correo, cuentaBancaria, tarifa, true, true);
				// sacar dominios
				if (dominios != "") {
					dominiosLista = sacarDominios(dominios);
				} else {
					dominiosLista.add("");
				}
				DominioDAO daoDominio = DominioDAOImpl.getInstance();

				while (userId == null) {
				}

				Iterator<String> it = dominiosLista.iterator();
				while (it.hasNext()) {
					String auxDominio = it.next();
					daoDominio.add(auxDominio, userId);
				}

				CuentasARegistrarDAO daoCuentaARegistrar = CuentasARegistrarDAOImpl
						.getInstance();
				Long id = daoCuentaARegistrar.add(userId);

				req.setAttribute("codigo", id);
				req.setAttribute("email", correo);
				req.setAttribute("nombre", nombre);
				RequestDispatcher rd = req
						.getRequestDispatcher("/emailconfirm");
				rd.forward(req, resp);
				return;
			}
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

}
