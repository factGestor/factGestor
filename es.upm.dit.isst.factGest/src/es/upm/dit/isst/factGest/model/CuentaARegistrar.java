package es.upm.dit.isst.factGest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentaARegistrar")
public class CuentaARegistrar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userId", nullable = false)
	private Long userId; // FOREIGN KEY
	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public CuentaARegistrar(Long userId) {
		this.userId = userId;
		this.fechaRegistro = new Date();
		System.out.println(fechaRegistro.toString());
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
