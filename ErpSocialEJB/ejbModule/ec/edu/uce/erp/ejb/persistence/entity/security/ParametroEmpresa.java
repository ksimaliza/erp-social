package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the erpt_parametro database table.
 * 
 */
@Entity
@Table(name="parametro_empresa_tbl")
@NamedQuery(name="ParametroEmpresa.findAll", query="SELECT p FROM ParametroEmpresa p")
public class ParametroEmpresa extends AuditoriaUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ERPT_PARAMETRO_EMPRESA_IDPARAMETRO_GENERATOR", sequenceName="ERPT_PARAMETRO_EMPRESA_ID_PARAMETRO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ERPT_PARAMETRO_EMPRESA_IDPARAMETRO_GENERATOR")
	@Column(name="id_parametro")
	private Integer idParametro;

	@Column(name="descripcion_parametro")
	private String descripcionParametro;

	private String estado;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="nombre_parametro")
	private String nombreParametro;

	@Column(name="valor_parametro")
	private String valorParametro;
	
	@Column(name="emr_pk")
	private Integer emrPk;
	
	@Column(name="catalogo_valor_id")
	private String cvTipoParametro;

	@Column(name="catalogo_tipo_id")
	private Integer ctTipoParametro;
	
	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="emr_pk", referencedColumnName="emr_pk", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private Empresa empresaTbl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="catalogo_tipo_id", referencedColumnName="catalogo_tipo_id", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="catalogo_valor_id", referencedColumnName="catalogo_valor_id", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private CatalogoValor catalogoValorTipoParametro;

	public ParametroEmpresa() {
	}

	public Integer getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDescripcionParametro() {
		return this.descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	/**
	 * @return the emrPk
	 */
	public Integer getEmrPk() {
		return emrPk;
	}

	/**
	 * @param emrPk the emrPk to set
	 */
	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	/**
	 * @return the empresaTbl
	 */
	public Empresa getEmpresaTbl() {
		return empresaTbl;
	}

	/**
	 * @param empresaTbl the empresaTbl to set
	 */
	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	/**
	 * @return the cvTipoParametro
	 */
	public String getCvTipoParametro() {
		return cvTipoParametro;
	}

	/**
	 * @param cvTipoParametro the cvTipoParametro to set
	 */
	public void setCvTipoParametro(String cvTipoParametro) {
		this.cvTipoParametro = cvTipoParametro;
	}

	/**
	 * @return the ctTipoParametro
	 */
	public Integer getCtTipoParametro() {
		return ctTipoParametro;
	}

	/**
	 * @param ctTipoParametro the ctTipoParametro to set
	 */
	public void setCtTipoParametro(Integer ctTipoParametro) {
		this.ctTipoParametro = ctTipoParametro;
	}

	/**
	 * @return the catalogoValorTipoParametro
	 */
	public CatalogoValor getCatalogoValorTipoParametro() {
		return catalogoValorTipoParametro;
	}

	/**
	 * @param catalogoValorTipoParametro the catalogoValorTipoParametro to set
	 */
	public void setCatalogoValorTipoParametro(
			CatalogoValor catalogoValorTipoParametro) {
		this.catalogoValorTipoParametro = catalogoValorTipoParametro;
	}


}