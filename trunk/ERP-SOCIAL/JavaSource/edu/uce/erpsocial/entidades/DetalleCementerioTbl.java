package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the detalle_cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_cementerio_tbl")
public class DetalleCementerioTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCementerioTblPK id;

	@Column(name="det_cementerio_descripcion")
	private String detCementerioDescripcion;

	@Column(name="det_cementerio_estado")
	private String detCementerioEstado;

	//bi-directional many-to-one association to ContratoTbl
	@OneToMany(mappedBy="detalleCementerioTbl")
	private Set<ContratoTbl> contratoTbls;

	//bi-directional many-to-one association to CabeceraCementerioTbl
    @ManyToOne
	@JoinColumn(name="cab_cementerio_fk")
	private CabeceraCementerioTbl cabeceraCementerioTbl;

	//bi-directional many-to-one association to SepulcroTbl
	@OneToMany(mappedBy="detalleCementerioTbl")
	private Set<SepulcroTbl> sepulcroTbls;

    public DetalleCementerioTbl() {
    }

	public DetalleCementerioTblPK getId() {
		return this.id;
	}

	public void setId(DetalleCementerioTblPK id) {
		this.id = id;
	}
	
	public String getDetCementerioDescripcion() {
		return this.detCementerioDescripcion;
	}

	public void setDetCementerioDescripcion(String detCementerioDescripcion) {
		this.detCementerioDescripcion = detCementerioDescripcion;
	}

	public String getDetCementerioEstado() {
		return this.detCementerioEstado;
	}

	public void setDetCementerioEstado(String detCementerioEstado) {
		this.detCementerioEstado = detCementerioEstado;
	}

	public Set<ContratoTbl> getContratoTbls() {
		return this.contratoTbls;
	}

	public void setContratoTbls(Set<ContratoTbl> contratoTbls) {
		this.contratoTbls = contratoTbls;
	}
	
	public CabeceraCementerioTbl getCabeceraCementerioTbl() {
		return this.cabeceraCementerioTbl;
	}

	public void setCabeceraCementerioTbl(CabeceraCementerioTbl cabeceraCementerioTbl) {
		this.cabeceraCementerioTbl = cabeceraCementerioTbl;
	}
	
	public Set<SepulcroTbl> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(Set<SepulcroTbl> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}
	
}