package es.upm.dit.isst.factGest.dao;

import es.upm.dit.isst.factGest.model.Sesion;

public interface SesionDAO {
	public Long add(String uuid, Long userID);
	
	public Sesion getSesion(String uuid);
	
	public void remove(Long userID);
}
