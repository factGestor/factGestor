package es.upm.dit.isst.factGest.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Sesion;

public class SesionDAOImpl implements SesionDAO {

	private static SesionDAOImpl instance;

	private SesionDAOImpl() {
	}

	public static SesionDAOImpl getInstance() {
		if (instance == null)
			instance = new SesionDAOImpl();
		return instance;
	}

	@Override
	public Long add(String uuid, Long userID) {
		EntityManager em = EMFService.get().createEntityManager();

		Sesion sesion = new Sesion(uuid, userID);
		em.persist(sesion);
		em.close();
		return sesion.getId();
	}

	@Override
	public Sesion getSesion(String uuid) {
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("SELECT s FROM Sesion s "
				+ "WHERE s.uuid = :uuid ", Sesion.class);
		q.setParameter("uuid", uuid);
		List<Sesion> sesiones = q.getResultList();
		if (sesiones.isEmpty()) {
			return null;
		}
		return sesiones.get(0);
	}

	@Override
	public void remove(Long userID) {
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("SELECT s FROM Sesion s "
				+ "WHERE s.userId = :userId ", Sesion.class);
		q.setParameter("userId", userID);
		List<Sesion> sesiones = q.getResultList();
		if (!sesiones.isEmpty()) {
			Iterator<Sesion> it = sesiones.iterator();
			while (it.hasNext()) {
				em.remove(it.next());
			}
		}
	}

}
