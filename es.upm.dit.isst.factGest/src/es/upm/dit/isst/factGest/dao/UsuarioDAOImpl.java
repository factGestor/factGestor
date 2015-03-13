package es.upm.dit.isst.factGest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



import es.upm.dit.isst.factGest.model.Usuario;
import es.upm.dit.isst.factGest.dao.EMFService;
import es.upm.dit.isst.factGest.dao.UsuarioDAOImpl;




public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static UsuarioDAOImpl instance;
	private UsuarioDAOImpl() {
	}
	public static UsuarioDAOImpl getInstance(){
		if (instance == null)
			instance = new UsuarioDAOImpl();
		return instance;
	}
	
	@Override
	public void add(String name, String password, String CIF, String email, boolean confirmado) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = new Usuario(name, password, CIF, email);
		em.persist(usuario);
		em.close();
	}
	
	//REVISAR solo para admin/admin
	@Override
	public List<Usuario> getUsuarios() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select u from Usuario u");
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}
	
	@Override
	public Long getId(String name){
		EntityManager em = EMFService.get().createEntityManager();
		//crear una consulta por el nombre
		System.out.println("Hemos entrado a getId");
		
		Query q = em.createQuery("SELECT u FROM Usuario u " + 
                "WHERE u.name = :name ", Usuario.class);
		
				q.setParameter("name", name);
		
		List<Usuario> usuarios = q.getResultList();
		System.out.print("getId encuentra: ");
		for(Usuario usuario : usuarios) {
            System.out.println(usuario.getId());
            return usuario.getId();
        }
		return 1L;

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
	public void verificar(long id){
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			
			em.getTransaction().begin();
			usuario.setConfirmado(true);
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
