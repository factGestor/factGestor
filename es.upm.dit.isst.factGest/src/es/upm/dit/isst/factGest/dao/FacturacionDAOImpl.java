package es.upm.dit.isst.factGest.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Dominio;
import es.upm.dit.isst.factGest.model.Facturacion;
import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.model.Facturacion.TipoIVA;

public class FacturacionDAOImpl implements FacturacionDAO {

	private static FacturacionDAOImpl instance;

	private FacturacionDAOImpl() {
	}

	public static FacturacionDAOImpl getInstance() {
		if (instance == null)
			instance = new FacturacionDAOImpl();
		return instance;
	}

	@Override
	public Long add(Long userId, Long domain, double ivaPagado, Long paisID,
			String numeroFactura, TipoIVA tipo, Date fecha) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();

		Facturacion factura = new Facturacion(userId, domain, ivaPagado,
				paisID, numeroFactura, tipo, fecha);
		em.persist(factura);
		em.close();
		return factura.getId();
	}

	@Override
	public List<Facturacion> getFacturasUser(Long userId) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT f FROM Facturacion f "
				+ "WHERE f.userId = :userid ", Facturacion.class);
		q.setParameter("domain", userId);
		List<Facturacion> facturas = q.getResultList();

		return facturas;
	}

	@Override
	public List<Facturacion> getFacturasPais(Long userId, Long paisID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT f FROM Facturacion f "
				+ "WHERE f.userId = :id " + "AND f.paisId = :pid",
				Usuario.class);
		q.setParameter("id", userId);
		q.setParameter("pid", paisID);
		List<Facturacion> facturas = q.getResultList();
		return facturas;
	}

	@Override
	public List<Facturacion> getFacturasDominio(Long userId, Long dominioID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT f FROM Facturacion f "
				+ "WHERE f.userId = :id " + "AND f.domainId = :pid",
				Usuario.class);
		q.setParameter("id", userId);
		q.setParameter("pid", dominioID);
		List<Facturacion> facturas = q.getResultList();
		return facturas;
	}

	@Override
	public List<Facturacion> getFacturasFecha(Long userId, Date fecha) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("SELECT f FROM Facturacion f "
						+ "WHERE f.userId = :id " + "AND f.fecha = :pid",
						Usuario.class);
		q.setParameter("id", userId);
		q.setParameter("pid", Facturacion.sdf.format(fecha));
		List<Facturacion> facturas = q.getResultList();
		return facturas;
	}

	@Override
	public Facturacion getFactura(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Facturacion factura = em.find(Facturacion.class, id);
		return factura;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Facturacion factura = em.find(Facturacion.class, id);
			em.remove(factura);
		} finally {
			em.close();
		}
	}

}
