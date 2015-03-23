package es.upm.dit.isst.factGest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAO;
import es.upm.dit.isst.factGest.dao.CuentasARegistrarDAOImpl;
import es.upm.dit.isst.factGest.dao.DominioDAO;
import es.upm.dit.isst.factGest.dao.DominioDAOImpl;
import es.upm.dit.isst.factGest.dao.UsuarioDAO;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;
import es.upm.dit.isst.factGest.model.CuentaARegistrar;
import es.upm.dit.isst.factGest.model.Dominio;


public class BorradoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UsuarioDAO usuarioDao = UsuarioDAOImpl.getInstance();
		CuentasARegistrarDAO cuentasARegistrarDao = CuentasARegistrarDAOImpl.getInstance();
		DominioDAO dominioDao = DominioDAOImpl.getInstance();
		List<CuentaARegistrar> cuentasBorrar = cuentasARegistrarDao.getToDelete();
		for (CuentaARegistrar cuentaBorradoEnCurso : cuentasBorrar) {
			Long userId = cuentaBorradoEnCurso.getUserId();
			//los borrados se realizan siempre por la primary key
			cuentasARegistrarDao.remove(cuentaBorradoEnCurso.getId());
			//List<Dominio> getDominios(String userId
			List<Dominio> dominios = dominioDao.getDominios(userId);
			for(Dominio dominio : dominios){
				dominioDao.remove(dominio.getId());
			}
			System.out.println(usuarioDao.getUsuario(userId).toString()+" ha sido borrado");
			usuarioDao.remove(userId);
		}
		
	}

}
