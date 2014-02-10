package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the contrato_tbl database table.
 * 
 */
@Entity
@Table(name="contrato_tbl")
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTRATO_TBL_CONPK_GENERATOR", sequenceName="CONTRATO_TBL_CON_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTRATO_TBL_CONPK_GENERATOR")
	@Column(name="con_pk")
	private Integer conPk;

	@Column(name="con_anios_contratacion")
	private Integer conAniosContratacion;

	@Column(name="con_cos_sepultura")
	private BigDecimal conCosSepultura;

	@Column(name="con_estado")
	private String conEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="con_fecha_fin")
	private Date conFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="con_fecha_inicio")
	private Date conFechaInicio;

	@Column(name="con_observacion")
	private String conObservacion;

	//bi-directional many-to-one association to DetalleCementerio
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_cementerio_tip_con_fk", referencedColumnName="cab_cementerio_fk"),
		@JoinColumn(name="det_cementerio_tip_con_nivel1", referencedColumnName="det_cementerio_nivel1")
		})
	private DetalleCementerio detalleCementerioTbl;

	//bi-directional many-to-one association to Sepulcro
	@OneToMany(mappedBy="contratoTbl")
	private List<Sepulcro> sepulcroTbls;

	public Contrato() {
	}

	public Integer getConPk() {
		return this.conPk;
	}

	public void setConPk(Integer conPk) {
		this.conPk = conPk;
	}

	public Integer getConAniosContratacion() {
		return this.conAniosContratacion;
	}

	public void setConAniosContratacion(Integer conAniosContratacion) {
		this.conAniosContratacion = conAniosContratacion;
	}

	public BigDecimal getConCosSepultura() {
		return this.conCosSepultura;
	}

	public void setConCosSepultura(BigDecimal conCosSepultura) {
		this.conCosSepultura = conCosSepultura;
	}

	public String getConEstado() {
		return this.conEstado;
	}

	public void setConEstado(String conEstado) {
		this.conEstado = conEstado;
	}

	public Date getConFechaFin() {
		return this.conFechaFin;
	}

	public void setConFechaFin(Date conFechaFin) {
		this.conFechaFin = conFechaFin;
	}

	public Date getConFechaInicio() {
		return this.conFechaInicio;
	}

	public void setConFechaInicio(Date conFechaInicio) {
		this.conFechaInicio = conFechaInicio;
	}

	public String getConObservacion() {
		return this.conObservacion;
	}

	public void setConObservacion(String conObservacion) {
		this.conObservacion = conObservacion;
	}

	public DetalleCementerio getDetalleCementerioTbl() {
		return this.detalleCementerioTbl;
	}

	public void setDetalleCementerioTbl(DetalleCementerio detalleCementerioTbl) {
		this.detalleCementerioTbl = detalleCementerioTbl;
	}

	public List<Sepulcro> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(List<Sepulcro> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}

	public Sepulcro addSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().add(sepulcroTbl);
		sepulcroTbl.setContratoTbl(this);

		return sepulcroTbl;
	}

	public Sepulcro removeSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().remove(sepulcroTbl);
		sepulcroTbl.setContratoTbl(null);

		return sepulcroTbl;
	}

}