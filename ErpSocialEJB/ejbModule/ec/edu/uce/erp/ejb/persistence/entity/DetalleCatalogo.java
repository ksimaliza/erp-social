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
 * The persistent class for the detalle_catalogo_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_catalogo_tbl")
@NamedQuery(name="DetalleCatalogo.findAll", query="SELECT d FROM DetalleCatalogo d")
public class DetalleCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCatalogoPK id;

	@Column(name="det_catalogo_descripcion")
	private String detCatalogoDescripcion;

	@Column(name="det_catalogo_estado")
	private String detCatalogoEstado;

	//bi-directional many-to-one association to CabeceraCatalogo
	@ManyToOne
	@JoinColumn(name="cab_catalogo_fk", referencedColumnName="cab_catalogo_pk", insertable=false, updatable=false)
	private CabeceraCatalogo cabeceraCatalogoTbl;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl1")
	private List<Persona> personaTbls1;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl2")
	private List<Persona> personaTbls2;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl3")
	private List<Persona> personaTbls3;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl4")
	private List<Persona> personaTbls4;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl5")
	private List<Persona> personaTbls5;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleCatalogoTbl6")
	private List<Persona> personaTbls6;

//	//bi-directional many-to-one association to HistoricoTransaccione
//	@OneToMany(mappedBy="dcTipoTransaccion")
//	private List<HistoricoTransaccione> segtHistoricoTransacciones;

	public DetalleCatalogo() {
	}

	public DetalleCatalogoPK getId() {
		return this.id;
	}

	public void setId(DetalleCatalogoPK id) {
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

	public CabeceraCatalogo getCabeceraCatalogoTbl() {
		return this.cabeceraCatalogoTbl;
	}

	public void setCabeceraCatalogoTbl(CabeceraCatalogo cabeceraCatalogoTbl) {
		this.cabeceraCatalogoTbl = cabeceraCatalogoTbl;
	}

	public List<Persona> getPersonaTbls1() {
		return this.personaTbls1;
	}

	public void setPersonaTbls1(List<Persona> personaTbls1) {
		this.personaTbls1 = personaTbls1;
	}

	public Persona addPersonaTbls1(Persona personaTbls1) {
		getPersonaTbls1().add(personaTbls1);
		personaTbls1.setDetalleCatalogoTbl1(this);

		return personaTbls1;
	}

	public Persona removePersonaTbls1(Persona personaTbls1) {
		getPersonaTbls1().remove(personaTbls1);
		personaTbls1.setDetalleCatalogoTbl1(null);

		return personaTbls1;
	}

	public List<Persona> getPersonaTbls2() {
		return this.personaTbls2;
	}

	public void setPersonaTbls2(List<Persona> personaTbls2) {
		this.personaTbls2 = personaTbls2;
	}

	public Persona addPersonaTbls2(Persona personaTbls2) {
		getPersonaTbls2().add(personaTbls2);
		personaTbls2.setDetalleCatalogoTbl2(this);

		return personaTbls2;
	}

	public Persona removePersonaTbls2(Persona personaTbls2) {
		getPersonaTbls2().remove(personaTbls2);
		personaTbls2.setDetalleCatalogoTbl2(null);

		return personaTbls2;
	}

	public List<Persona> getPersonaTbls3() {
		return this.personaTbls3;
	}

	public void setPersonaTbls3(List<Persona> personaTbls3) {
		this.personaTbls3 = personaTbls3;
	}

	public Persona addPersonaTbls3(Persona personaTbls3) {
		getPersonaTbls3().add(personaTbls3);
		personaTbls3.setDetalleCatalogoTbl3(this);

		return personaTbls3;
	}

	public Persona removePersonaTbls3(Persona personaTbls3) {
		getPersonaTbls3().remove(personaTbls3);
		personaTbls3.setDetalleCatalogoTbl3(null);

		return personaTbls3;
	}

	public List<Persona> getPersonaTbls4() {
		return this.personaTbls4;
	}

	public void setPersonaTbls4(List<Persona> personaTbls4) {
		this.personaTbls4 = personaTbls4;
	}

	public Persona addPersonaTbls4(Persona personaTbls4) {
		getPersonaTbls4().add(personaTbls4);
		personaTbls4.setDetalleCatalogoTbl4(this);

		return personaTbls4;
	}

	public Persona removePersonaTbls4(Persona personaTbls4) {
		getPersonaTbls4().remove(personaTbls4);
		personaTbls4.setDetalleCatalogoTbl4(null);

		return personaTbls4;
	}

	public List<Persona> getPersonaTbls5() {
		return this.personaTbls5;
	}

	public void setPersonaTbls5(List<Persona> personaTbls5) {
		this.personaTbls5 = personaTbls5;
	}

	public Persona addPersonaTbls5(Persona personaTbls5) {
		getPersonaTbls5().add(personaTbls5);
		personaTbls5.setDetalleCatalogoTbl5(this);

		return personaTbls5;
	}

	public Persona removePersonaTbls5(Persona personaTbls5) {
		getPersonaTbls5().remove(personaTbls5);
		personaTbls5.setDetalleCatalogoTbl5(null);

		return personaTbls5;
	}

	public List<Persona> getPersonaTbls6() {
		return this.personaTbls6;
	}

	public void setPersonaTbls6(List<Persona> personaTbls6) {
		this.personaTbls6 = personaTbls6;
	}

	public Persona addPersonaTbls6(Persona personaTbls6) {
		getPersonaTbls6().add(personaTbls6);
		personaTbls6.setDetalleCatalogoTbl6(this);

		return personaTbls6;
	}

	public Persona removePersonaTbls6(Persona personaTbls6) {
		getPersonaTbls6().remove(personaTbls6);
		personaTbls6.setDetalleCatalogoTbl6(null);

		return personaTbls6;
	}

//	public List<HistoricoTransaccione> getSegtHistoricoTransacciones() {
//		return this.segtHistoricoTransacciones;
//	}
//
//	public void setSegtHistoricoTransacciones(List<HistoricoTransaccione> segtHistoricoTransacciones) {
//		this.segtHistoricoTransacciones = segtHistoricoTransacciones;
//	}

//	public HistoricoTransaccione addSegtHistoricoTransaccione(HistoricoTransaccione segtHistoricoTransaccione) {
//		getSegtHistoricoTransacciones().add(segtHistoricoTransaccione);
//		segtHistoricoTransaccione.setDcTipoTransaccion(this);
//
//		return segtHistoricoTransaccione;
//	}
//
//	public HistoricoTransaccione removeSegtHistoricoTransaccione(HistoricoTransaccione segtHistoricoTransaccione) {
//		getSegtHistoricoTransacciones().remove(segtHistoricoTransaccione);
//		segtHistoricoTransaccione.setDcTipoTransaccion(null);
//
//		return segtHistoricoTransaccione;
//	}

}