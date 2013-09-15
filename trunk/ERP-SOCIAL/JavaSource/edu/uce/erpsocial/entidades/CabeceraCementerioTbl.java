package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cabecera_cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_cementerio_tbl")
public class CabeceraCementerioTbl implements Serializable {
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

	//bi-directional many-to-one association to DetalleCementerioTbl
	@OneToMany(mappedBy="cabeceraCementerioTbl")
	private Set<DetalleCementerioTbl> detalleCementerioTbls;

    public CabeceraCementerioTbl() {
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

	public Set<DetalleCementerioTbl> getDetalleCementerioTbls() {
		return this.detalleCementerioTbls;
	}

	public void setDetalleCementerioTbls(Set<DetalleCementerioTbl> detalleCementerioTbls) {
		this.detalleCementerioTbls = detalleCementerioTbls;
	}
	
}