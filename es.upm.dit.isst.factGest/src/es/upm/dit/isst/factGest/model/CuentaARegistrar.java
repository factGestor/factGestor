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
	@Column(name = "diaRegistro", nullable = false)
	private int diaRegistro;
	@Column(name = "mesRegistro", nullable = false)
	private int mesRegistro;
	
	public CuentaARegistrar(Long userId){
		this.userId = userId;
		this.diaRegistro = Calendar.getInstance().DATE+1;
		this.mesRegistro = Calendar.getInstance().MONTH+1;
		System.out.println("diaRegistro = " + diaRegistro+ " mesRegistro = " + mesRegistro);
	}
	
	public Long getId(){
		return id;
	}
	public Long getUserId(){
		return userId;
	}
	public int getDiaRegistro(){
		return diaRegistro;
	}
	public int getMesRegistro(){
		return mesRegistro;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public void setDiaRegistro(int diaRegistro){
		this.diaRegistro = diaRegistro;
	}
	public void setMesRegistro(int mesRegistro){
		this.mesRegistro = mesRegistro;
	}
}
