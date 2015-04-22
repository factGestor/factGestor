package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Pais;

public class PaisDAOImpl implements PaisDAO {
	
	private static PaisDAOImpl instance;

	private PaisDAOImpl() {
	}

	public static PaisDAOImpl getInstance() {
		if (instance == null)
			instance = new PaisDAOImpl();
		return instance;
	}

	@Override
	public Long add(String nombre, String codigo) {
		EntityManager em = EMFService.get().createEntityManager();

		Pais pais = new Pais(nombre, codigo);
		em.persist(pais);
		em.close();
		return pais.getId();
	}

	@Override
	public List<Pais> getPaises() {
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("select p from Pais p");
		
		List<Pais> paises = q.getResultList();
		
		return paises;
	}

	@Override
	public Pais getPaisByName(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("SELECT p FROM Pais p "
				+ "WHERE p.nombre = :nombre ", Pais.class);
		q.setParameter("nombre", nombre);
		List<Pais> paises = q.getResultList();
		if(paises.isEmpty()){
			return null;
		}
		return paises.get(0);
	}

	@Override
	public Pais getPaisByCode(String codigo) {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("SELECT p FROM Pais p "
				+ "WHERE p.codigo = :codigo ", Pais.class);
		q.setParameter("codigo", codigo);
		List<Pais> paises = q.getResultList();
		if(paises.isEmpty()){
			return null;
		}
		return paises.get(0);
	}

	@Override
	public Pais getPais(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Pais pais = em.find(Pais.class, id);
		return pais;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Pais pais = em.find(Pais.class, id);
			em.remove(pais);
		} finally {
			em.close();
		}

	}

}
