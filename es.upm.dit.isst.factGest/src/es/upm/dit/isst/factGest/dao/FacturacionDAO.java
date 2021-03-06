package es.upm.dit.isst.factGest.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.factGest.model.Facturacion;
import es.upm.dit.isst.factGest.model.Facturacion.TipoIVA;

public interface FacturacionDAO {

	public Long add(Long userId, Long domain, double ivaPagado, Long paisID, String numeroFactura, TipoIVA tipo, Date fecha);
	
	public List<Facturacion> getFacturasUser(Long userId);
	
	public List<Facturacion> getFacturasPais(Long userId, Long paisID);
	
	public List<Facturacion> getFacturasDominio(Long userId, Long dominioID);
	
	public List<Facturacion> getFacturasFecha(Long userId, Date fecha);
	
	public Facturacion getFactura(long id);
	
	public void remove(long id);
}
