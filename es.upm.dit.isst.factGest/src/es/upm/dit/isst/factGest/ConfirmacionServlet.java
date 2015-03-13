package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAO;
import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAOImpl;
import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.EMFService;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.CuentaARegistrar;
import es.upm.dit.isst.factGest.model.Usuario;

public class ConfirmacionServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		String codigo = checkNull(req.getParameter("codigo"));
		CuentasARegistrarDAO daoSinConfirmar = CuentasARegistrarDAOImpl.getInstance();
		Long userID = daoSinConfirmar.getCuentaARegistrar(codigo).getUserId();
		UsuarioDAO daoUser = UsuarioDAOImpl.getInstance();
		daoUser.verificar(userID);
		
		resp.sendRedirect("/");
}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}

