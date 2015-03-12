package es.upm.dit.isst.factGest.dao;


import es.upm.dit.isst.factGest.model.Usuario;


public interface UsuarioDAO {
	
	public void add (String name, String password, String CIF, String email);
	//no va el identificador
	

	
	public void remove (long id);

}
