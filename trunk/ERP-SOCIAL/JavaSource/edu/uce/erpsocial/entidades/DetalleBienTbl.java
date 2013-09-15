package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the detalle_bien_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_bien_tbl")
public class DetalleBienTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleBienTblPK id;

	@Column(name="det_bien_descripcion")
	private String detBienDescripcion;

	@Column(name="det_bien_estado")
	private String detBienEstado;

	//bi-directional many-to-one association to BienTbl
	@OneToMany(mappedBy="detalleBienTbl1")
	private Set<BienTbl> bienTbls1;

	//bi-directional many-to-one association to BienTbl
	@OneToMany(mappedBy="detalleBienTbl2")
	private Set<BienTbl> bienTbls2;

	//bi-directional many-to-one association to CabeceraBienTbl
    @ManyToOne
	@JoinColumn(name="cab_bien_fk")
	private CabeceraBienTbl cabeceraBienTbl;

	//bi-directional many-to-one association to IngresoTbl
	@OneToMany(mappedBy="detalleBienTbl")
	private Set<IngresoTbl> ingresoTbls;

    public DetalleBienTbl() {
    }

	public DetalleBienTblPK getId() {
		return this.id;
	}

	public void setId(DetalleBienTblPK id) {
		this.id = id;
	}
	
	public String getDetBienDescripcion() {
		return this.detBienDescripcion;
	}

	public void setDetBienDescripcion(String detBienDescripcion) {
		this.detBienDescripcion = detBienDescripcion;
	}

	public String getDetBienEstado() {
		return this.detBienEstado;
	}

	public void setDetBienEstado(String detBienEstado) {
		this.detBienEstado = detBienEstado;
	}

	public Set<BienTbl> getBienTbls1() {
		return this.bienTbls1;
	}

	public void setBienTbls1(Set<BienTbl> bienTbls1) {
		this.bienTbls1 = bienTbls1;
	}
	
	public Set<BienTbl> getBienTbls2() {
		return this.bienTbls2;
	}

	public void setBienTbls2(Set<BienTbl> bienTbls2) {
		this.bienTbls2 = bienTbls2;
	}
	
	public CabeceraBienTbl getCabeceraBienTbl() {
		return this.cabeceraBienTbl;
	}

	public void setCabeceraBienTbl(CabeceraBienTbl cabeceraBienTbl) {
		this.cabeceraBienTbl = cabeceraBienTbl;
	}
	
	public Set<IngresoTbl> getIngresoTbls() {
		return this.ingresoTbls;
	}

	public void setIngresoTbls(Set<IngresoTbl> ingresoTbls) {
		this.ingresoTbls = ingresoTbls;
	}
	
}