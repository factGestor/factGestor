package es.upm.dit.isst.factGest.dao;

import java.util.List;

import es.upm.dit.isst.factGest.model.CuentaARegistrar;

public interface CuentasARegistrarDAO {
	//meter cuenta
	public void add (Long userId);
	
	//sacar dominios de un usuario
	public CuentaARegistrar getCuentaARegistrar(String userId);
	
	//eliminar del registro (cuenta registrada o pasado tiempo de registro)
	public void remove (long id);
}
