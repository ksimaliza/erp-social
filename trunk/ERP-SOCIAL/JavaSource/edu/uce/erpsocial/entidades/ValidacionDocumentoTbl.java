package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the validacion_documento_tbl database table.
 * 
 */
@Entity
@Table(name="validacion_documento_tbl")
public class ValidacionDocumentoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="val_documento_pk")
	private Integer valDocumentoPk;

	@Column(name="val_documento_es_ultimo")
	private Integer valDocumentoEsUltimo;

	@Column(name="val_documento_estado")
	private String valDocumentoEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="val_documento_fecha_ingreso")
	private Date valDocumentoFechaIngreso;

	@Column(name="val_documento_ingresado_por")
	private Integer valDocumentoIngresadoPor;

	//bi-directional many-to-one association to TipoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="tip_documento_pk")
	private TipoDocumentoTbl tipoDocumentoTbl;

    public ValidacionDocumentoTbl() {
    }

	public Integer getValDocumentoPk() {
		return this.valDocumentoPk;
	}

	public void setValDocumentoPk(Integer valDocumentoPk) {
		this.valDocumentoPk = valDocumentoPk;
	}

	public Integer getValDocumentoEsUltimo() {
		return this.valDocumentoEsUltimo;
	}

	public void setValDocumentoEsUltimo(Integer valDocumentoEsUltimo) {
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

	public Integer getValDocumentoIngresadoPor() {
		return this.valDocumentoIngresadoPor;
	}

	public void setValDocumentoIngresadoPor(Integer valDocumentoIngresadoPor) {
		this.valDocumentoIngresadoPor = valDocumentoIngresadoPor;
	}

	public TipoDocumentoTbl getTipoDocumentoTbl() {
		return this.tipoDocumentoTbl;
	}

	public void setTipoDocumentoTbl(TipoDocumentoTbl tipoDocumentoTbl) {
		this.tipoDocumentoTbl = tipoDocumentoTbl;
	}
	
}