package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the contrato_tbl database table.
 * 
 */
@Entity
@Table(name="contrato_tbl")
public class ContratoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="con_pk")
	private Integer conPk;

	@Column(name="con_anios_contratacion")
	private Integer conAniosContratacion;

    @Temporal( TemporalType.DATE)
	@Column(name="con_fecha_fin")
	private Date conFechaFin;

    @Temporal( TemporalType.DATE)
	@Column(name="con_fecha_inicio")
	private Date conFechaInicio;

	@Column(name="con_observacion")
	private String conObservacion;

	//bi-directional many-to-one association to DetalleCementerioTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_cementerio_tip_con_fk", referencedColumnName="cab_cementerio_fk"),
		@JoinColumn(name="det_cementerio_tip_con_nivel1", referencedColumnName="det_cementerio_nivel1")
		})
	private DetalleCementerioTbl detalleCementerioTbl;

	//bi-directional many-to-one association to SepulcroTbl
	@OneToMany(mappedBy="contratoTbl")
	private Set<SepulcroTbl> sepulcroTbls;

    public ContratoTbl() {
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

	public DetalleCementerioTbl getDetalleCementerioTbl() {
		return this.detalleCementerioTbl;
	}

	public void setDetalleCementerioTbl(DetalleCementerioTbl detalleCementerioTbl) {
		this.detalleCementerioTbl = detalleCementerioTbl;
	}
	
	public Set<SepulcroTbl> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(Set<SepulcroTbl> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}
	
}