package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the cabecera_cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_cementerio_tbl")
@NamedQuery(name="CabeceraCementerio.findAll", query="SELECT c FROM CabeceraCementerio c")
public class CabeceraCementerio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_cementerio_pk")
	private String cabCementerioPk;

	@Column(name="cab_cementerio_archivo")
	private String cabCementerioArchivo;

	@Column(name="cab_cementerio_campo")
	private String cabCementerioCampo;

	@Column(name="cab_cementerio_descripcion")
	private String cabCementerioDescripcion;

	@Column(name="cab_cementerio_niveles")
	private Integer cabCementerioNiveles;

	//bi-directional many-to-one association to DetalleCementerio
	@OneToMany(mappedBy="cabeceraCementerioTbl")
	private List<DetalleCementerio> detalleCementerioTbls;

	public CabeceraCementerio() {
	}

	public String getCabCementerioPk() {
		return this.cabCementerioPk;
	}

	public void setCabCementerioPk(String cabCementerioPk) {
		this.cabCementerioPk = cabCementerioPk;
	}

	public String getCabCementerioArchivo() {
		return this.cabCementerioArchivo;
	}

	public void setCabCementerioArchivo(String cabCementerioArchivo) {
		this.cabCementerioArchivo = cabCementerioArchivo;
	}

	public String getCabCementerioCampo() {
		return this.cabCementerioCampo;
	}

	public void setCabCementerioCampo(String cabCementerioCampo) {
		this.cabCementerioCampo = cabCementerioCampo;
	}

	public String getCabCementerioDescripcion() {
		return this.cabCementerioDescripcion;
	}

	public void setCabCementerioDescripcion(String cabCementerioDescripcion) {
		this.cabCementerioDescripcion = cabCementerioDescripcion;
	}

	public Integer getCabCementerioNiveles() {
		return this.cabCementerioNiveles;
	}

	public void setCabCementerioNiveles(Integer cabCementerioNiveles) {
		this.cabCementerioNiveles = cabCementerioNiveles;
	}

	public List<DetalleCementerio> getDetalleCementerioTbls() {
		return this.detalleCementerioTbls;
	}

	public void setDetalleCementerioTbls(List<DetalleCementerio> detalleCementerioTbls) {
		this.detalleCementerioTbls = detalleCementerioTbls;
	}

	public DetalleCementerio addDetalleCementerioTbl(DetalleCementerio detalleCementerioTbl) {
		getDetalleCementerioTbls().add(detalleCementerioTbl);
		detalleCementerioTbl.setCabeceraCementerioTbl(this);

		return detalleCementerioTbl;
	}

	public DetalleCementerio removeDetalleCementerioTbl(DetalleCementerio detalleCementerioTbl) {
		getDetalleCementerioTbls().remove(detalleCementerioTbl);
		detalleCementerioTbl.setCabeceraCementerioTbl(null);

		return detalleCementerioTbl;
	}

}