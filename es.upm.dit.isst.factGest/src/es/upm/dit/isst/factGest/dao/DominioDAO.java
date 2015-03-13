package es.upm.dit.isst.factGest.dao;

import java.util.List;
import es.upm.dit.isst.factGest.model.Dominio;

public interface DominioDAO {


	//meter dominio
	public void add (String nombre, Long userId);
	
	//sacar dominios de un usuario
	public List<Dominio> getDominios(String userId);
	
	//eliminar dominio
	public void remove (long id);
	

}
