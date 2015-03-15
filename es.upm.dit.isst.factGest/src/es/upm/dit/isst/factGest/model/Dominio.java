package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dominio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "domain", nullable = false)
	private String domain;
	@Column(name = "userId", nullable = false)
	private Long userId; // FOREIGN KEY

	public Dominio(String domain, Long userId) {
		this.domain = domain;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getDomain() {
		return domain;
	}

	public Long getUserId() {
		return userId;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	// HACE FALTA PONERLO SI ES FOREIGN KEY?
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
