package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the euc_sepultura_vie database table.
 * 
 */
@Entity
@Table(name="euc_sepultura_vie")
@NamedQuery(name="SepulturaListDTO.findAll", query="SELECT s FROM SepulturaListDTO s")
public class SepulturaListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="nic_codigo")
	private Integer nicCodigo;

	@Column(name="nic_descripcion")
	private String nicDescripcion;

	@Column(name="nic_seccion")
	private Integer nicSeccion;

	@Column(name="nic_tipo")
	private Integer nicTipo;

	@Column(name="nni_descripcion")
	private String nniDescripcion;

	@Column(name="nni_nivel")
	private Integer nniNivel;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_ci")
	private String perCi;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_nombres")
	private String perNombres;

	private String seccion;

	@Id
	@Column(name="sep_codigo")
	private Integer sepCodigo;

	@Column(name="sep_difunto")
	private Integer sepDifunto;

	@Column(name="sep_nicho")
	private Integer sepNicho;

	@Column(name="sep_observacion")
	private String sepObservacion;
	
	@Column(name="sep_empresa")
	private Integer sepEmpresa;

	@Column(name="tni_descripcion")
	private String tniDescripcion;

	@Transient
	private Date fechaDesde;
	
	@Transient
	private Date fechaHasta;
	
	public SepulturaListDTO() {
	}
	
	public SepulturaListDTO(
			String perCi,
			String perApellidos,
			String perNombres,
			String seccion,
			String nniDescripcion,
			String tniDescripcion,
			String nicDescripcion
			) {
			super();
			this.perCi=perCi;
			this.perApellidos=perApellidos;
			this.perNombres=perNombres;
			this.seccion = seccion;
			this.tniDescripcion = tniDescripcion;
			this.nniDescripcion = nniDescripcion;
			this.nicDescripcion = nicDescripcion;;
		}
	
	
	
	public Integer getNicCodigo() {
		return this.nicCodigo;
	}

	public void setNicCodigo(Integer nicCodigo) {
		this.nicCodigo = nicCodigo;
	}

	public String getNicDescripcion() {
		return this.nicDescripcion;
	}

	public void setNicDescripcion(String nicDescripcion) {
		this.nicDescripcion = nicDescripcion;
	}

	public Integer getNicSeccion() {
		return this.nicSeccion;
	}

	public void setNicSeccion(Integer nicSeccion) {
		this.nicSeccion = nicSeccion;
	}

	public Integer getNicTipo() {
		return this.nicTipo;
	}

	public void setNicTipo(Integer nicTipo) {
		this.nicTipo = nicTipo;
	}

	public String getNniDescripcion() {
		return this.nniDescripcion;
	}

	public void setNniDescripcion(String nniDescripcion) {
		this.nniDescripcion = nniDescripcion;
	}

	public Integer getNniNivel() {
		return this.nniNivel;
	}

	public void setNniNivel(Integer nniNivel) {
		this.nniNivel = nniNivel;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	public Date getPerFechaNac() {
		return this.perFechaNac;
	}

	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public Integer getSepCodigo() {
		return this.sepCodigo;
	}

	public void setSepCodigo(Integer sepCodigo) {
		this.sepCodigo = sepCodigo;
	}

	public Integer getSepDifunto() {
		return this.sepDifunto;
	}

	public void setSepDifunto(Integer sepDifunto) {
		this.sepDifunto = sepDifunto;
	}

	public Integer getSepNicho() {
		return this.sepNicho;
	}

	public void setSepNicho(Integer sepNicho) {
		this.sepNicho = sepNicho;
	}

	public String getSepObservacion() {
		return this.sepObservacion;
	}

	public void setSepObservacion(String sepObservacion) {
		this.sepObservacion = sepObservacion;
	}

	public String getTniDescripcion() {
		return this.tniDescripcion;
	}

	public void setTniDescripcion(String tniDescripcion) {
		this.tniDescripcion = tniDescripcion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getSepEmpresa() {
		return sepEmpresa;
	}

	public void setSepEmpresa(Integer sepEmpresa) {
		this.sepEmpresa = sepEmpresa;
	}
 
}