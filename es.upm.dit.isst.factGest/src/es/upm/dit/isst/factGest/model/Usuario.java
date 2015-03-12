package es.upm.dit.isst.factGest.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	public Usuario(String name, String password, String CIF, String email){
		this.name = name;
		this.password = password;
		this.CIF = CIF;
		this.email = email;
	}
	
	public Long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public String getCIF(){
		return CIF;
	}
	public String getEmail(){
		return email;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setCIF(String CIF){
		this.CIF = CIF;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	
}
