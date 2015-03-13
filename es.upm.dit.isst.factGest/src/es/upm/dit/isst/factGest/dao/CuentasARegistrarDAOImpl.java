package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.CuentaARegistrar;

public class CuentasARegistrarDAOImpl implements CuentasARegistrarDAO {

	@Override
	public void add(Long userId) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		//public CuentaARegistrar(Long userId)
		CuentaARegistrar cuentaARegistrar = new CuentaARegistrar(userId);
		em.persist(cuentaARegistrar);
		em.close();

	}

	@Override
	public CuentaARegistrar getCuentaARegistrar(String userId) {
		
		EntityManager em = EMFService.get().createEntityManager();
		System.out.println("Hemos entrado a getCuentaARegistrar(id)");
		Query q = em.createQuery("SELECT c FROM CuentaARegistrar c " + 
                "WHERE c.userId = :userId ", CuentaARegistrar.class);
		
				q.setParameter("userId", userId);
		List<CuentaARegistrar> cuentasARegistrar = q.getResultList();
		for(CuentaARegistrar cuentaARegistrar : cuentasARegistrar) {
			return cuentaARegistrar;
		}
		return null;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			CuentaARegistrar cuentaARegistrar = em.find(CuentaARegistrar.class, id);
			em.remove(cuentaARegistrar);
		} finally {
			em.close();
		}
	}

}
