package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the documento_tbl database table.
 * 
 */
@Entity
@Table(name="documento_tbl")
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCUMENTO_TBL_DOCPK_GENERATOR", sequenceName="DOCUMENTO_TBL_DOC_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCUMENTO_TBL_DOCPK_GENERATOR")
	@Column(name="doc_pk")
	private Integer docPk;

	@Column(name="doc_direccion")
	private String docDireccion;

	@Column(name="doc_estado")
	private String docEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="doc_fecha_creacion")
	private Date docFechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="doc_fecha_modificacion")
	private Date docFechaModificacion;

	@Column(name="doc_nombre")
	private String docNombre;

	//bi-directional many-to-one association to Carpeta
	@ManyToOne
	@JoinColumn(name="carp_fk")
	private Carpeta carpetaTbl;

	//bi-directional many-to-one association to EstadoDocumento
	@ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumento estadoDocumentoTbl;

	//bi-directional many-to-one association to FormatoDocumento
	@ManyToOne
	@JoinColumn(name="for_documento_fk")
	private FormatoDocumento formatoDocumentoTbl;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tip_documento_fk")
	private TipoDocumento tipoDocumentoTbl;

	public Documento() {
	}

	public Integer getDocPk() {
		return this.docPk;
	}

	public void setDocPk(Integer docPk) {
		this.docPk = docPk;
	}

	public String getDocDireccion() {
		return this.docDireccion;
	}

	public void setDocDireccion(String docDireccion) {
		this.docDireccion = docDireccion;
	}

	public String getDocEstado() {
		return this.docEstado;
	}

	public void setDocEstado(String docEstado) {
		this.docEstado = docEstado;
	}

	public Date getDocFechaCreacion() {
		return this.docFechaCreacion;
	}

	public void setDocFechaCreacion(Date docFechaCreacion) {
		this.docFechaCreacion = docFechaCreacion;
	}

	public Date getDocFechaModificacion() {
		return this.docFechaModificacion;
	}

	public void setDocFechaModificacion(Date docFechaModificacion) {
		this.docFechaModificacion = docFechaModificacion;
	}

	public String getDocNombre() {
		return this.docNombre;
	}

	public void setDocNombre(String docNombre) {
		this.docNombre = docNombre;
	}

	public Carpeta getCarpetaTbl() {
		return this.carpetaTbl;
	}

	public void setCarpetaTbl(Carpeta carpetaTbl) {
		this.carpetaTbl = carpetaTbl;
	}

	public EstadoDocumento getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumento estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}

	public FormatoDocumento getFormatoDocumentoTbl() {
		return this.formatoDocumentoTbl;
	}

	public void setFormatoDocumentoTbl(FormatoDocumento formatoDocumentoTbl) {
		this.formatoDocumentoTbl = formatoDocumentoTbl;
	}

	public TipoDocumento getTipoDocumentoTbl() {
		return this.tipoDocumentoTbl;
	}

	public void setTipoDocumentoTbl(TipoDocumento tipoDocumentoTbl) {
		this.tipoDocumentoTbl = tipoDocumentoTbl;
	}

}