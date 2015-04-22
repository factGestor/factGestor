package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "codigo", nullable = false)
	private String codigo;

	public Pais(String name, String codigo) {
		this.nombre = name;
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setName(String name) {
		this.nombre = name;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
