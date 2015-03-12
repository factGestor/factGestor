package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CuentaARegistrar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "userId", nullable = false)
	private Long userId;	//FOREIGN KEY
	private String codigoRegistro;
	
	public Long getId(){
		return id;
	}
	public Long getUserId(){
		return userId;
	}
	public String getCodigoRegistro(){
		return codigoRegistro;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public void setCodigoRegistro(String codigoRegistro){
		this.codigoRegistro = codigoRegistro;
	}
}
