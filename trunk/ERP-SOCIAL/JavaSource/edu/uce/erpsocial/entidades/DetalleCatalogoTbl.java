package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the detalle_catalogo_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_catalogo_tbl")
public class DetalleCatalogoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCatalogoTblPK id;

	@Column(name="det_catalogo_descripcion")
	private String detCatalogoDescripcion;

	@Column(name="det_catalogo_estado")
	private String detCatalogoEstado;

	//bi-directional many-to-one association to CabeceraCatalogoTbl
    @ManyToOne
	@JoinColumn(name="cab_catalogo_fk")
	private CabeceraCatalogoTbl cabeceraCatalogoTbl;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl1")
	private Set<PersonaTbl> personaTbls1;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl2")
	private Set<PersonaTbl> personaTbls2;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl3")
	private Set<PersonaTbl> personaTbls3;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl4")
	private Set<PersonaTbl> personaTbls4;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl5")
	private Set<PersonaTbl> personaTbls5;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleCatalogoTbl6")
	private Set<PersonaTbl> personaTbls6;

    public DetalleCatalogoTbl() {
    }

	public DetalleCatalogoTblPK getId() {
		return this.id;
	}

	public void setId(DetalleCatalogoTblPK id) {
		this.id = id;
	}
	
	public String getDetCatalogoDescripcion() {
		return this.detCatalogoDescripcion;
	}

	public void setDetCatalogoDescripcion(String detCatalogoDescripcion) {
		this.detCatalogoDescripcion = detCatalogoDescripcion;
	}

	public String getDetCatalogoEstado() {
		return this.detCatalogoEstado;
	}

	public void setDetCatalogoEstado(String detCatalogoEstado) {
		this.detCatalogoEstado = detCatalogoEstado;
	}

	public CabeceraCatalogoTbl getCabeceraCatalogoTbl() {
		return this.cabeceraCatalogoTbl;
	}

	public void setCabeceraCatalogoTbl(CabeceraCatalogoTbl cabeceraCatalogoTbl) {
		this.cabeceraCatalogoTbl = cabeceraCatalogoTbl;
	}
	
	public Set<PersonaTbl> getPersonaTbls1() {
		return this.personaTbls1;
	}

	public void setPersonaTbls1(Set<PersonaTbl> personaTbls1) {
		this.personaTbls1 = personaTbls1;
	}
	
	public Set<PersonaTbl> getPersonaTbls2() {
		return this.personaTbls2;
	}

	public void setPersonaTbls2(Set<PersonaTbl> personaTbls2) {
		this.personaTbls2 = personaTbls2;
	}
	
	public Set<PersonaTbl> getPersonaTbls3() {
		return this.personaTbls3;
	}

	public void setPersonaTbls3(Set<PersonaTbl> personaTbls3) {
		this.personaTbls3 = personaTbls3;
	}
	
	public Set<PersonaTbl> getPersonaTbls4() {
		return this.personaTbls4;
	}

	public void setPersonaTbls4(Set<PersonaTbl> personaTbls4) {
		this.personaTbls4 = personaTbls4;
	}
	
	public Set<PersonaTbl> getPersonaTbls5() {
		return this.personaTbls5;
	}

	public void setPersonaTbls5(Set<PersonaTbl> personaTbls5) {
		this.personaTbls5 = personaTbls5;
	}
	
	public Set<PersonaTbl> getPersonaTbls6() {
		return this.personaTbls6;
	}

	public void setPersonaTbls6(Set<PersonaTbl> personaTbls6) {
		this.personaTbls6 = personaTbls6;
	}
	
}