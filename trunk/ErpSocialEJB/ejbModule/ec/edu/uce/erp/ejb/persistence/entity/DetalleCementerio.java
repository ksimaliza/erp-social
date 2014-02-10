package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the detalle_cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_cementerio_tbl")
@NamedQuery(name="DetalleCementerio.findAll", query="SELECT d FROM DetalleCementerio d")
public class DetalleCementerio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCementerioPK id;

	@Column(name="det_cementerio_descripcion")
	private String detCementerioDescripcion;

	@Column(name="det_cementerio_estado")
	private String detCementerioEstado;

	//bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy="detalleCementerioTbl")
	private List<Contrato> contratoTbls;

	//bi-directional many-to-one association to CabeceraCementerio
	@ManyToOne
	@JoinColumn(name="cab_cementerio_fk", insertable=false, updatable=false)
	private CabeceraCementerio cabeceraCementerioTbl;

	//bi-directional many-to-one association to Sepulcro
	@OneToMany(mappedBy="detalleCementerioTbl")
	private List<Sepulcro> sepulcroTbls;

	public DetalleCementerio() {
	}

	public DetalleCementerioPK getId() {
		return this.id;
	}

	public void setId(DetalleCementerioPK id) {
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

	public List<Contrato> getContratoTbls() {
		return this.contratoTbls;
	}

	public void setContratoTbls(List<Contrato> contratoTbls) {
		this.contratoTbls = contratoTbls;
	}

	public Contrato addContratoTbl(Contrato contratoTbl) {
		getContratoTbls().add(contratoTbl);
		contratoTbl.setDetalleCementerioTbl(this);

		return contratoTbl;
	}

	public Contrato removeContratoTbl(Contrato contratoTbl) {
		getContratoTbls().remove(contratoTbl);
		contratoTbl.setDetalleCementerioTbl(null);

		return contratoTbl;
	}

	public CabeceraCementerio getCabeceraCementerioTbl() {
		return this.cabeceraCementerioTbl;
	}

	public void setCabeceraCementerioTbl(CabeceraCementerio cabeceraCementerioTbl) {
		this.cabeceraCementerioTbl = cabeceraCementerioTbl;
	}

	public List<Sepulcro> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(List<Sepulcro> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}

	public Sepulcro addSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().add(sepulcroTbl);
		sepulcroTbl.setDetalleCementerioTbl(this);

		return sepulcroTbl;
	}

	public Sepulcro removeSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().remove(sepulcroTbl);
		sepulcroTbl.setDetalleCementerioTbl(null);

		return sepulcroTbl;
	}

}