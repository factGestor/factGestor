package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Dominio;

public class DominioDAOImpl implements DominioDAO {

	private static DominioDAOImpl instance;

	private DominioDAOImpl() {
	}

	public static DominioDAOImpl getInstance() {
		if (instance == null)
			instance = new DominioDAOImpl();
		return instance;
	}

	@Override
	public void add(String nombre, Long userId) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();

		// public Dominio(String domain, Long userId)
		Dominio dominio = new Dominio(nombre, userId);
		em.persist(dominio);
		em.close();

	}

	@Override
	public List<Dominio> getDominios(String userId) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select d from Dominio d");
		List<Dominio> todos = q.getResultList();
		return todos;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Dominio dominio = em.find(Dominio.class, id);
			em.remove(dominio);
		} finally {
			em.close();
		}
	}

}
