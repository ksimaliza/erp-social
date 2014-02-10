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
 * The persistent class for the validacion_documento_tbl database table.
 * 
 */
@Entity
@Table(name="validacion_documento_tbl")
@NamedQuery(name="ValidacionDocumento.findAll", query="SELECT v FROM ValidacionDocumento v")
public class ValidacionDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VALIDACION_DOCUMENTO_TBL_VALDOCUMENTOPK_GENERATOR", sequenceName="VALIDACION_DOCUMENTO_TBL_VAL_DOCUMENTO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VALIDACION_DOCUMENTO_TBL_VALDOCUMENTOPK_GENERATOR")
	@Column(name="val_documento_pk")
	private Integer valDocumentoPk;

	@Column(name="val_documento_es_ultimo")
	private Boolean valDocumentoEsUltimo;

	@Column(name="val_documento_estado")
	private String valDocumentoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="val_documento_fecha_ingreso")
	private Date valDocumentoFechaIngreso;

	@Column(name="val_documento_ingresado_por")
	private String valDocumentoIngresadoPor;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tip_documento_fk")
	private TipoDocumento tipoDocumentoTbl;

	public ValidacionDocumento() {
	}

	public Integer getValDocumentoPk() {
		return this.valDocumentoPk;
	}

	public void setValDocumentoPk(Integer valDocumentoPk) {
		this.valDocumentoPk = valDocumentoPk;
	}

	public Boolean getValDocumentoEsUltimo() {
		return this.valDocumentoEsUltimo;
	}

	public void setValDocumentoEsUltimo(Boolean valDocumentoEsUltimo) {
		this.valDocumentoEsUltimo = valDocumentoEsUltimo;
	}

	public String getValDocumentoEstado() {
		return this.valDocumentoEstado;
	}

	public void setValDocumentoEstado(String valDocumentoEstado) {
		this.valDocumentoEstado = valDocumentoEstado;
	}

	public Date getValDocumentoFechaIngreso() {
		return this.valDocumentoFechaIngreso;
	}

	public void setValDocumentoFechaIngreso(Date valDocumentoFechaIngreso) {
		this.valDocumentoFechaIngreso = valDocumentoFechaIngreso;
	}

	public String getValDocumentoIngresadoPor() {
		return this.valDocumentoIngresadoPor;
	}

	public void setValDocumentoIngresadoPor(String valDocumentoIngresadoPor) {
		this.valDocumentoIngresadoPor = valDocumentoIngresadoPor;
	}

	public TipoDocumento getTipoDocumentoTbl() {
		return this.tipoDocumentoTbl;
	}

	public void setTipoDocumentoTbl(TipoDocumento tipoDocumentoTbl) {
		this.tipoDocumentoTbl = tipoDocumentoTbl;
	}

}