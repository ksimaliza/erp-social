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
 * The persistent class for the cabecera_bien_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_bien_tbl")
@NamedQuery(name="CabeceraBien.findAll", query="SELECT c FROM CabeceraBien c")
public class CabeceraBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_bien_pk")
	private String cabBienPk;

	@Column(name="cab_bien_archivo")
	private String cabBienArchivo;

	@Column(name="cab_bien_campo")
	private String cabBienCampo;

	@Column(name="cab_bien_descripcion")
	private String cabBienDescripcion;

	@Column(name="cab_bien_niveles")
	private Integer cabBienNiveles;

	//bi-directional many-to-one association to DetalleBien
	@OneToMany(mappedBy="cabeceraBienTbl")
	private List<DetalleBien> detalleBienTbls;

	public CabeceraBien() {
	}

	public String getCabBienPk() {
		return this.cabBienPk;
	}

	public void setCabBienPk(String cabBienPk) {
		this.cabBienPk = cabBienPk;
	}

	public String getCabBienArchivo() {
		return this.cabBienArchivo;
	}

	public void setCabBienArchivo(String cabBienArchivo) {
		this.cabBienArchivo = cabBienArchivo;
	}

	public String getCabBienCampo() {
		return this.cabBienCampo;
	}

	public void setCabBienCampo(String cabBienCampo) {
		this.cabBienCampo = cabBienCampo;
	}

	public String getCabBienDescripcion() {
		return this.cabBienDescripcion;
	}

	public void setCabBienDescripcion(String cabBienDescripcion) {
		this.cabBienDescripcion = cabBienDescripcion;
	}

	public Integer getCabBienNiveles() {
		return this.cabBienNiveles;
	}

	public void setCabBienNiveles(Integer cabBienNiveles) {
		this.cabBienNiveles = cabBienNiveles;
	}

	public List<DetalleBien> getDetalleBienTbls() {
		return this.detalleBienTbls;
	}

	public void setDetalleBienTbls(List<DetalleBien> detalleBienTbls) {
		this.detalleBienTbls = detalleBienTbls;
	}

	public DetalleBien addDetalleBienTbl(DetalleBien detalleBienTbl) {
		getDetalleBienTbls().add(detalleBienTbl);
		detalleBienTbl.setCabeceraBienTbl(this);

		return detalleBienTbl;
	}

	public DetalleBien removeDetalleBienTbl(DetalleBien detalleBienTbl) {
		getDetalleBienTbls().remove(detalleBienTbl);
		detalleBienTbl.setCabeceraBienTbl(null);

		return detalleBienTbl;
	}

}