package es.upm.dit.isst.factGest.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Usuario;



public interface UsuarioDAO {
	
	public void add (String name, String password, String CIF, String email, boolean confirmado);
	
	//solo para el administrador (admin/admin)
	public List<Usuario> getUsuarios();
	
	public Long getId(String name);
	
	public void remove (long id);
	
	public void verificar(long id);

}
