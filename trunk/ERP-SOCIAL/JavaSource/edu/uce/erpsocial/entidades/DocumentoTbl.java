package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the documento_tbl database table.
 * 
 */
@Entity
@Table(name="documento_tbl")
public class DocumentoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="doc_pk")
	private Integer docPk;

	@Column(name="doc_direccion")
	private String docDireccion;

    @Temporal( TemporalType.DATE)
	@Column(name="doc_fecha_creacion")
	private Date docFechaCreacion;

    @Temporal( TemporalType.DATE)
	@Column(name="doc_fecha_modificacion")
	private Date docFechaModificacion;

	@Column(name="doc_nombre")
	private String docNombre;

	//bi-directional many-to-one association to CarpetaTbl
    @ManyToOne
	@JoinColumn(name="carp_fk")
	private CarpetaTbl carpetaTbl;

	//bi-directional many-to-one association to EstadoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumentoTbl estadoDocumentoTbl;

	//bi-directional many-to-one association to FormatoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="for_documento_fk")
	private FormatoDocumentoTbl formatoDocumentoTbl;

	//bi-directional many-to-one association to TipoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="tip_documento_fk")
	private TipoDocumentoTbl tipoDocumentoTbl;

    public DocumentoTbl() {
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

	public CarpetaTbl getCarpetaTbl() {
		return this.carpetaTbl;
	}

	public void setCarpetaTbl(CarpetaTbl carpetaTbl) {
		this.carpetaTbl = carpetaTbl;
	}
	
	public EstadoDocumentoTbl getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumentoTbl estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}
	
	public FormatoDocumentoTbl getFormatoDocumentoTbl() {
		return this.formatoDocumentoTbl;
	}

	public void setFormatoDocumentoTbl(FormatoDocumentoTbl formatoDocumentoTbl) {
		this.formatoDocumentoTbl = formatoDocumentoTbl;
	}
	
	public TipoDocumentoTbl getTipoDocumentoTbl() {
		return this.tipoDocumentoTbl;
	}

	public void setTipoDocumentoTbl(TipoDocumentoTbl tipoDocumentoTbl) {
		this.tipoDocumentoTbl = tipoDocumentoTbl;
	}
	
}