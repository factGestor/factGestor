package es.upm.dit.isst.factGest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facturacion")
public class Facturacion implements Serializable {

	public enum TipoIVA {iva, reducedIva, superReducedIva};
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userid", nullable = false)
	private Long userId; // FOREIGN KEY
	@Column(name = "domain", nullable = false)
	private String domain; // FOREIGN KEY
	@Column(name = "ivapagado", nullable = false)
	private double ivaPagado;
	@Column(name = "paisid", nullable = false)
	private Long paisId; // FOREIGN KEY
	@Column(name = "numerofactura", nullable = false)
	private String numeroFactura;
	@Column(name = "tipoiva", nullable = false)
	private TipoIVA tipoIVA;

	public Facturacion(Long userId, String domain, double IVApagado,
			Long paisId, String numeroFactura, TipoIVA tipo) {
		this.userId = userId;
		this.domain = domain;
		this.ivaPagado = IVApagado;
		this.paisId = paisId;
		this.numeroFactura = numeroFactura;
		this.tipoIVA = tipo;
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
		return ivaPagado;
	}

	public Long getPaisId() {
		return paisId;
	}
	
	public String getNumeroFactura(){
		return numeroFactura;
	}
	
	public TipoIVA getTipoIVA(){
		return tipoIVA;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setIVApagado(double IVApagado) {
		this.ivaPagado = IVApagado;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
	
	public void setNumeroFactura(String numeroFactura){
		this.numeroFactura = numeroFactura;
	}
	
	public void setTipoIVA(TipoIVA tipo){
		this.tipoIVA = tipo;
	}

}
