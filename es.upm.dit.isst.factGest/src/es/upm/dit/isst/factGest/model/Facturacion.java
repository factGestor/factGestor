package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Facturacion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId; // FOREIGN KEY
	private String domain; // FOREIGN KEY
	private double IVApagado;
	private Long paisId; // FOREIGN KEY
	private int numeroFactura;

	public Facturacion(Long userId, String domain, double IVApagado,
			Long paisId, int numeroFactura) {
		this.userId = userId;
		this.domain = domain;
		this.IVApagado = IVApagado;
		this.paisId = paisId;
		this.numeroFactura = numeroFactura;
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public String getDomain() {
		return domain;
	}

	public double getIVApagado() {
		return IVApagado;
	}

	public Long getPaisId() {
		return paisId;
	}
	
	public int getNumeroFactura(){
		return numeroFactura;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setIVApagado(double IVApagado) {
		this.IVApagado = IVApagado;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
	
	public void setNumeroFactura(int numeroFactura){
		this.numeroFactura = numeroFactura;
	}

}
