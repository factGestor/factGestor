package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Usuario;

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
	public Long add(String nombre, Long userId) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();

		// public Dominio(String domain, Long userId)
		Dominio dominio = new Dominio(nombre, userId);
		em.persist(dominio);
		em.close();
		return dominio.getId();

	}

	@Override
	//DOMINIOS DE UN USUARIO
	public List<Dominio> getDominios(Long userId) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select d from Dominio d WHERE d.userId = :userId");
		q.setParameter("userId", userId);
		
		List<Dominio> dominios = q.getResultList();
		/*TRAZAS
		System.out.println(dominios.get(0).getDomain());
		System.out.println(dominios.get(0).getUserId());
		*/
		return dominios;

	}
	
	@Override
	public Dominio getDominioByName(String domain ) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("SELECT d FROM Dominio d "
				+ "WHERE d.domain = :domain ", Dominio.class);
		q.setParameter("domain", domain);
		List<Dominio> dominios = q.getResultList();
		if(dominios.isEmpty()){
			return null;
		}
		return dominios.get(0);
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

	@Override
	public Dominio getDominio(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Dominio dominio = em.find(Dominio.class, id);
		return dominio;
	}

}
