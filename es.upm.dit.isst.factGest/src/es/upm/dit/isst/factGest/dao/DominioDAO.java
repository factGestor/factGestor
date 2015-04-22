package es.upm.dit.isst.factGest.dao;

import java.util.List;
import es.upm.dit.isst.factGest.model.Dominio;

public interface DominioDAO {

	// meter dominio
	public Long add(String nombre, Long userId);

	// sacar dominios de un usuario

	public List<Dominio> getDominios(Long userId);

	
	// dominio por nombre
	public Dominio getDominioByName(String dominio);


	// sacar dominios de un usuario
	public Dominio getDominio(long id);
		
	// eliminar dominio
	public void remove(long id);

}
