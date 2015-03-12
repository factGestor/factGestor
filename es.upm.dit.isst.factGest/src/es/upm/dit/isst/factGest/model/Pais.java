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
public class Pais implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	//AÑADIMOS ACRONIMO?
	//private String acronimo; 
	
	public Pais(String name){
		this.name = name;
	}
	public Long getId(){
		return id;
	}
	public String getName(){
		return name;
	} 
	
	public void setName(String name){
		this.name = name;
	} 
}
