package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sesion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private String uuid;
	@Column(name = "userId", nullable = false)
	private Long userId; // FOREIGN KEY

	public Sesion(String uuid, Long userId) {
		this.uuid = uuid;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getUUID() {
		return uuid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
}
