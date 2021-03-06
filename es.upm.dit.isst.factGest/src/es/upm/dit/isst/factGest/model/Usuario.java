package es.upm.dit.isst.factGest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

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
	
	//indica si se ha confirmado la cuenta
	private boolean confirmado;
	
	private int consultasActuales;
	private int consultasDisponibles;

	//dia en el que se ha registrado
	private Date fechaRegistro;
	//dia hasta el que dura la suscripcion
	private Date fechaSuscripcion;
	
	public Usuario(String name, String password, String CIF, String email, Tarifas tarifa, Boolean confirmado, int consultasActuales, int consultasDisponibles,
			Date fechaRegistro, Date fechaSuscripcion) {
		this.name = name;
		this.password = password;
		this.CIF = CIF;
		this.email = email;
		this.tarifa = tarifa;
		this.confirmado = confirmado;
		
		this.consultasActuales = consultasActuales;
		this.consultasDisponibles = consultasDisponibles;
		this.fechaRegistro = fechaRegistro;
		this.fechaSuscripcion = fechaSuscripcion;
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

	public boolean getConfirmado() {
		return confirmado;
	}
	public boolean getPuedePeticiones() {
			return confirmado;
	}
	public int getConsultasActuales(){
		return consultasActuales;
	}
	public int getConsultasDisponibles(){
		return consultasDisponibles;
	}
	public Date getFechaRegistro(){
		return fechaRegistro;
	}
	public Date getFechaSuscripcion(){
		return fechaSuscripcion;
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
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	public void setConsultasActuales(int consultasActuales){
		this.consultasActuales = consultasActuales;
	}
	public void setConsultasDisponibles(int consultasDisponibles){
		this.consultasDisponibles = consultasDisponibles;
	}
	public void setFechaRegistro(Date fechaRegistro){
		this.fechaRegistro = fechaRegistro;
	}
	public void setFechaSuscripcion(Date fechaSuscripcion){
		this.fechaSuscripcion = fechaSuscripcion;
	}

}
