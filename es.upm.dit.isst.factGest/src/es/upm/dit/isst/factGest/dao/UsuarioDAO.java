package es.upm.dit.isst.factGest.dao;

import java.util.List;

import es.upm.dit.isst.factGest.model.Usuario;

public interface UsuarioDAO {

	public Long add(String name, String password, String CIF, String email,
			boolean confirmado);

	// solo para el administrador (admin/admin)
	public List<Usuario> getUsuarios();

	public Usuario getUsuario(Long id);

	public Long getId(String name);

	public boolean comprobarLogin(String name, String password);

	public void remove(long id);

	public void verificar(long id);

	public List<Usuario> getUsuariosBy(String campo, String dato);

}
