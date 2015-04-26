package es.upm.dit.isst.factGest.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facturacion")
public class Facturacion implements Serializable {

	public static enum TipoIVA {iva, reducedIva, superReducedIva};
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userid", nullable = false)
	private Long userId; // FOREIGN KEY
	@Column(name = "domain", nullable = false)
	private Long domainId; // FOREIGN KEY
	@Column(name = "ivapagado", nullable = false)
	private double ivaPagado;
	@Column(name = "paisid", nullable = false)
	private Long paisId; // FOREIGN KEY
	@Column(name = "numerofactura", nullable = false)
	private String numeroFactura;
	@Column(name = "tipoiva", nullable = false)
	private TipoIVA tipoIVA;
	@Column(name = "fecha", nullable = false)
	private String fecha;
	
	//Campos para representacion en informes
	
	private String dominio;
	private String pais;
	

	public Facturacion(Long userId, Long domainId, double IVApagado,
			Long paisId, String numeroFactura, TipoIVA tipo, Date fecha) {
		this.userId = userId;
		this.domainId = domainId;
		this.ivaPagado = IVApagado;
		this.paisId = paisId;
		this.numeroFactura = numeroFactura;
		this.tipoIVA = tipo;
		this.fecha = sdf.format(fecha);
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getDomain() {
		return domainId;
	}

	public double getivapagado() {
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
	
	public String getFecha(){
		return fecha;
	}
	
	public String getPais(){
		return pais;
	}
	
	public String getDominio(){
		return dominio;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDomain(Long domainId) {
		this.domainId = domainId;
	}

	public void setivapagado(double IVApagado) {
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
	
	public void setFecha(Date fecha){
		this.fecha = sdf.format(fecha);
	}
	
	public void setPais(String pais){
		this.pais = pais;
	}
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}

}
