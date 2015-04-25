package es.upm.dit.isst.factGest.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.model.Usuario.Tarifas;
import es.upm.dit.isst.factGest.dao.EMFService;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;

public class UsuarioDAOImpl implements UsuarioDAO {

	private static UsuarioDAOImpl instance;

	private UsuarioDAOImpl() {
	}

	public static UsuarioDAOImpl getInstance() {
		if (instance == null)
			instance = new UsuarioDAOImpl();
		return instance;
	}
	
	
	@Override
	public Long add(String name, String password, String CIF, String email,String cuentaBancaria, 
			Tarifas tarifa, boolean confirmado, int consultasActuales, int consultasDisponibles,
			Date fechaRegistro, Date fechaSuscripcion){
		// TODO Auto-generated method stub
		
		
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = new Usuario(name, password, CIF, email, tarifa, confirmado,
				consultasActuales, consultasDisponibles, fechaRegistro, fechaSuscripcion);
		em.persist(usuario);
		em.close();
		return usuario.getId();
	}

	@Override
	public Usuario getUsuario(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u "
				+ "WHERE u.id = :id ", Usuario.class);
		q.setParameter("id", id);

		List<Usuario> usuarios = q.getResultList();
		for (Usuario usuario : usuarios) {
			return usuario;
		}
		return null;
	}

	// REVISAR solo para admin/admin
	@Override
	public List<Usuario> getUsuarios() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select u from Usuario u");
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}

	@Override
	public Long getId(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		// crear una consulta por el nombre
		System.out.println("Hemos entrado a getId(NAME)");

		Query q = em.createQuery("SELECT u FROM Usuario u "
				+ "WHERE u.name = :name ", Usuario.class);

		q.setParameter("name", name);

		List<Usuario> usuarios = q.getResultList();
		System.out.print("getId encuentra: ");
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getId());
			return usuario.getId();
		}
		// si no se encuentra el usuario
		return 1L;
	}

	@Override
	public boolean comprobarLogin(String name, String password) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u "
				+ "WHERE u.name = :name " + "AND u.password = :password",
				Usuario.class);

		q.setParameter("name", name);
		q.setParameter("password", password);
		List<Usuario> usuarios = q.getResultList();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getId());
			return true;
		}
		return false;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
		} finally {
			em.close();
		}
	}

	@Override
	public void verificar(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);

			em.getTransaction().begin();
			usuario.setConfirmado(true);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Usuario> getUsuariosBy(String campo, String dato) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u " + "WHERE u."
				+ campo + " = :dato ", Usuario.class);
		q.setParameter("dato", dato);
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}


	@Override
	public void cambiar(String nombre, String dato, Long userId) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, userId);
		
		em.getTransaction().begin();
		if(nombre.equals("password")){
			usuario.setPassword(dato);
		}
		if(nombre.equals("email")){
			usuario.setEmail(dato);
		}
		if (nombre.equals("condicionesContratacion")){
			Tarifas tarifa = Tarifas.Free;
			if(dato.equals("Pago")){
				tarifa = Tarifas.Pago;
			}
			if(dato.equals("Suscripcion")){
				tarifa = Tarifas.Suscripcion;
			}
			usuario.setTarifa(tarifa);
		} 
		
		em.getTransaction().commit();
		
	}

	@Override
	public List<Usuario> getToDelete() {
		// TODO Auto-generated method stub
		List<Usuario> cuentas = new ArrayList<Usuario>();
		Date fecha=new Date();
		Long time = fecha.getTime();
		//si han pasado 2 dias = 172.800.000 milisegundos
		Long dosDiasmilisegundos = (long) 172800000;
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario c ", Usuario.class);
		List<Usuario> usuarios = q.getResultList();
		for (Usuario usuario : usuarios) {
			if(usuario.getFechaRegistro().getTime()-time>=dosDiasmilisegundos){
				cuentas.add(usuario);
			}
		}
		return cuentas;

	}

	@Override
	public void descontarConsulta(long id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);

			em.getTransaction().begin();
			usuario.setConsultasActuales(usuario.getConsultasActuales() + 1);
			usuario.setConsultasDisponibles(usuario.getConsultasDisponibles()-1);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}
	@Override
	public void anadirConsultas(int consultas, Long userId) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, userId);
		em.getTransaction().begin();
		
		//añadir a consultas disponibles
		usuario.setConsultasDisponibles(usuario.getConsultasDisponibles()+consultas);
		System.out.println("Añadidas "+consultas+" al usuario "+usuario.getName());
		//ponemos consultasActuales al mismo punto
		usuario.setConsultasActuales(usuario.getConsultasDisponibles()+consultas);
		
		em.getTransaction().commit();
		
	}
	
	@Override
	public void anadirSuscripcion(int meses, Long userId) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, userId);
		em.getTransaction().begin();
		
		Calendar ahoraCal = Calendar.getInstance();
		Date fecha = ahoraCal.getTime();
		Date fechaSuscripcion = usuario.getFechaSuscripcion();
		//Caso de que la Fecha de suscripcion ya ha pasado
		//fecha>fechaSuscripcion
		if (fecha.getTime()-fechaSuscripcion.getTime()>=0){
			ahoraCal.add(Calendar.MONTH, meses);
			fecha = ahoraCal.getTime();
			usuario.setFechaSuscripcion(fecha);
		}
		//si no es asi
		else{
			ahoraCal.setTime(fechaSuscripcion);
			ahoraCal.add(Calendar.MONTH, meses);
			fecha = ahoraCal.getTime();
			usuario.setFechaSuscripcion(fecha);
		}
		
		em.getTransaction().commit();
		
	}

}
