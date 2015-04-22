package es.upm.dit.isst.factGest.dao;

import java.util.List;

import es.upm.dit.isst.factGest.model.Pais;


public interface PaisDAO {
		public Long add(String nombre, String codigo);

		public List<Pais> getPaises();

		public Pais getPaisByName(String nombre);

		public Pais getPaisByCode(String codigo);

		public Pais getPais(long id);
			
		public void remove(long id);
}
