package es.upm.dit.isst.factGest.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.CuentaARegistrar;

public class CuentasARegistrarDAOImpl implements CuentasARegistrarDAO {

	private static CuentasARegistrarDAOImpl instance;

	private CuentasARegistrarDAOImpl() {
	}

	public static CuentasARegistrarDAOImpl getInstance() {
		if (instance == null)
			instance = new CuentasARegistrarDAOImpl();

		return instance;
	}

	@Override
	public Long add(Long userId) {
		// TODO Auto-generated method stub
		System.out.println("UserId = " + userId);
		EntityManager em = EMFService.get().createEntityManager();
		CuentaARegistrar cuentaARegistrar = new CuentaARegistrar(userId);
		System.out.println("TRAZA 2 id = " + cuentaARegistrar.getId()
				+ " codigoRegistro = " + cuentaARegistrar.getUserId()); // TRAZA
		em.persist(cuentaARegistrar);
		em.close();
		return cuentaARegistrar.getId();
	}

	@Override
	public CuentaARegistrar getCuentaARegistrarUser(Long userId) {

		EntityManager em = EMFService.get().createEntityManager();
		System.out.println("Hemos entrado a getCuentaARegistrar(id)");
		Query q = em.createQuery("SELECT c FROM CuentaARegistrar c "+ "WHERE c.userId = :userId ", CuentaARegistrar.class);

		q.setParameter("userId", userId);
		List<CuentaARegistrar> cuentasARegistrar = q.getResultList();
		for (CuentaARegistrar cuentaARegistrar : cuentasARegistrar) {
			return cuentaARegistrar;
		}
		return null;
	}

	@Override
	public CuentaARegistrar getCuentaARegistrar(Long id) {

		EntityManager em = EMFService.get().createEntityManager();
		System.out.println("Hemos entrado a getCuentaARegistrar(id)");
		Query q = em.createQuery("SELECT c FROM CuentaARegistrar c "
				+ "WHERE c.id = :id ", CuentaARegistrar.class);

		q.setParameter("id", id);
		List<CuentaARegistrar> cuentasARegistrar = q.getResultList();
		for (CuentaARegistrar cuentaARegistrar : cuentasARegistrar) {
			return cuentaARegistrar;
		}
		return null;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			CuentaARegistrar cuentaARegistrar = em.find(CuentaARegistrar.class,
					id);
			em.remove(cuentaARegistrar);
		} finally {
			em.close();
		}
	}


	@Override
	public List<CuentaARegistrar> getToDelete() {
		// TODO Auto-generated method stub
		List<CuentaARegistrar> cuentas = new ArrayList<CuentaARegistrar>();
		Date fecha=new Date();
		Long time = fecha.getTime();
		//si han pasado 2 dias = 172.800.000 milisegundos
		Long dosDiasmilisegundos = (long) 172800000;
		EntityManager em = EMFService.get().createEntityManager();
		System.out.println("Hemos entrado a getCuentaARegistrar(id)");
		Query q = em.createQuery("SELECT c FROM CuentaARegistrar c ", CuentaARegistrar.class);
		List<CuentaARegistrar> cuentasARegistrar = q.getResultList();
		for (CuentaARegistrar cuentaARegistrar : cuentasARegistrar) {
			if(cuentaARegistrar.getFechaRegistro().getTime()-time>=dosDiasmilisegundos){
				cuentas.add(cuentaARegistrar);
			}
		}
		return cuentas;

	}

}
