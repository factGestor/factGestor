package es.upm.dit.isst.factGest.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.model.Usuario.Tarifas;

public interface UsuarioDAO {

	public Long add(String name, String password, String CIF, String email,String cuentaBancaria, 
			Tarifas tarifa, boolean confirmado, int consultasActuales, int consultasDisponibles,
			Date fechaRegistro, Date fechaSuscripcion);

	// solo para el administrador (admin/admin)
	public List<Usuario> getUsuarios();

	public Usuario getUsuario(Long id);

	public Long getId(String name);

	public boolean comprobarLogin(String name, String password);

	public void remove(long id);

	public void verificar(long id);

	public List<Usuario> getUsuariosBy(String campo, String dato);
	
	public void cambiar(String nombre, String dato, Long userId);
	
	List<Usuario> getToDelete();
	
	public void descontarConsulta(long id);

	void anadirConsultas(int consultas, Long userId);

	void anadirSuscripcion(int meses, Long userId);

}
