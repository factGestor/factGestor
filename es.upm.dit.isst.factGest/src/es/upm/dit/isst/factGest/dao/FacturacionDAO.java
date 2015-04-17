package es.upm.dit.isst.factGest.dao;

import java.util.List;

import es.upm.dit.isst.factGest.model.Facturacion;
import es.upm.dit.isst.factGest.model.Facturacion.TipoIVA;

public interface FacturacionDAO {

	public Long add(Long userId, String domain, double ivaPagado, Long paisID, String numeroFactura, TipoIVA tipo);
	
	public List<Facturacion> getFacturasUser(Long userId);
	
	public List<Facturacion> getFacturasPais(Long userId, Long paisID);
	
	public Facturacion getFactura(long id);
	
	public void remove(long id);
}
