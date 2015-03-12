package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ConsultaPagada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;	//FOREIGN KEY
	private String domain;	//FOREIGN KEY
	private Long IVApagado;
	private Long paisId;	//FOREIGN KEY
	
	public ConsultaPagada(Long userId, String domain, Long IVApagado, Long paisId){
		this.userId = userId;
		this.domain = domain;
		this.IVApagado = IVApagado;
		this.paisId = paisId;
	}
	public Long getId(){
		return id;
	}
	public Long getUserId(){
		return userId;
	}
	public String getDomain(){
		return domain;
	}
	public Long getIVApagado(){
		return IVApagado;
	}
	public Long getPaisId(){
		return paisId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public void setDomain(String domain){
		this.domain = domain;
	}
	public void setIVApagado(Long IVApagado){
		this.IVApagado = IVApagado;
	}
	public void setPaisId(Long paisId){
		this.paisId = paisId;
	}
	
}
