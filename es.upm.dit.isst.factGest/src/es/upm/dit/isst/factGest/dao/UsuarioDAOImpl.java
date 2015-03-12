package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.dao.EMFService;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;



public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static UsuarioDAOImpl instance;
	private UsuarioDAOImpl() {
	}
	public static UsuarioDAOImpl getInstance(){
		if (instance == null)
			instance = new UsuarioDAOImpl();
		return instance;
	}
	
	@Override
	public void add(String name, String password, String CIF, String email) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = new Usuario(name, password, CIF, email);
		em.persist(usuario);
		em.close();
	}


	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
		} finally {
			em.close();
		}
	}

}
