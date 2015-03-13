package es.upm.dit.isst.factGest.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cuentaARegistrar")
public class CuentaARegistrar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "userId", nullable = false)
	private Long userId;	//FOREIGN KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoRegistro;
	private Calendar fechaRegistro;
	/*
	 * public Usuario(String name, String password, String CIF, String email){
		this.name = name;
		this.password = password;
		this.CIF = CIF;
		this.email = email;
		this.confirmado = false;
	}
	 * */
	public CuentaARegistrar(Long userId){
		this.userId = userId;
		this.fechaRegistro = Calendar.getInstance();
	}
	
	public Long getId(){
		return id;
	}
	public Long getUserId(){
		return userId;
	}
	public Long getCodigoRegistro(){
		return codigoRegistro;
	}
	public Calendar getFechaRegistro(){
		return fechaRegistro;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public void setCodigoRegistro(Long codigoRegistro){
		this.codigoRegistro = codigoRegistro;
	}
	public void setFechaRegistro(Calendar fechaRegistro){
		this.fechaRegistro = fechaRegistro;
	}
}
