package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Tarifas{Free, Pago, Suscripcion};
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "CIF", nullable = false)
	private String CIF;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "tarifa", nullable = false)
	private Tarifas tarifa;
	@Column(name = "cuentaBancaria", nullable = true)
	private String cuentaBancaria;
	private boolean corrienteDePago;
	private boolean confirmado;

	public Usuario(String name, String password, String CIF, String email, Tarifas tarifa, String cuentaBancaria) {
		this.name = name;
		this.password = password;
		this.CIF = CIF;
		this.email = email;
		this.tarifa = tarifa;
		this.cuentaBancaria = cuentaBancaria;
		this.corrienteDePago = true;
		this.confirmado = true;//cambiar a false
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getCIF() {
		return CIF;
	}
	public String getEmail() {
		return email;
	}
	public Tarifas getTarifa() {
		return tarifa;
	}
	public boolean getCorrienteDePago() {
		return corrienteDePago;
	}
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}
	public boolean getConfirmado() {
		return confirmado;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCIF(String CIF) {
		this.CIF = CIF;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTarifa(Tarifas tarifa) {
		this.tarifa = tarifa;
	}
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public void setCorrienteDePago(boolean corrienteDePago) {
		this.corrienteDePago = corrienteDePago;
	}
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

}
