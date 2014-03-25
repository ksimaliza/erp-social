package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the detalle_bien_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_bien_tbl")
@NamedQuery(name="DetalleBien.findAll", query="SELECT d FROM DetalleBien d")
public class DetalleBien extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleBienPK id;

	@Column(name="det_bien_descripcion")
	private String detBienDescripcion;

	@Column(name="det_bien_estado")
	private String detBienEstado;

//	//bi-directional many-to-one association to Bien
//	@OneToMany(mappedBy="dcTipoBien")
//	private List<Bien> bienTbls1;

//	//bi-directional many-to-one association to Bien
//	@OneToMany(mappedBy="dcEstadoBien")
//	private List<Bien> bienTbls2;

//	//bi-directional many-to-one association to Bien
//	@OneToMany(mappedBy="dcEstadoConservacion")
//	private List<Bien> bienTbls3;

	//bi-directional many-to-one association to CabeceraBien
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cab_bien_fk", referencedColumnName="cab_bien_pk", insertable=false, updatable=false)
	private CabeceraBien cabeceraBienTbl;

//	//bi-directional many-to-one association to Ingreso
//	@OneToMany(mappedBy="detalleBienTbl")
//	private List<Ingreso> ingresoTbls;
//
//	//bi-directional many-to-one association to Transaccion
//	@OneToMany(mappedBy="detalleBienTbl")
//	private List<Transaccion> transaccionTbls;

	public DetalleBien() {
	}

	public DetalleBienPK getId() {
		return this.id;
	}

	public void setId(DetalleBienPK id) {
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

//	public List<Bien> getBienTbls1() {
//		return this.bienTbls1;
//	}
//
//	public void setBienTbls1(List<Bien> bienTbls1) {
//		this.bienTbls1 = bienTbls1;
//	}

//	public Bien addBienTbls1(Bien bienTbls1) {
//		getBienTbls1().add(bienTbls1);
//		bienTbls1.setDetalleBienTbl1(this);
//
//		return bienTbls1;
//	}
//
//	public Bien removeBienTbls1(Bien bienTbls1) {
//		getBienTbls1().remove(bienTbls1);
//		bienTbls1.setDetalleBienTbl1(null);
//
//		return bienTbls1;
//	}

//	public List<Bien> getBienTbls2() {
//		return this.bienTbls2;
//	}
//
//	public void setBienTbls2(List<Bien> bienTbls2) {
//		this.bienTbls2 = bienTbls2;
//	}

//	public Bien addBienTbls2(Bien bienTbls2) {
//		getBienTbls2().add(bienTbls2);
//		bienTbls2.setDetalleBienTbl2(this);
//
//		return bienTbls2;
//	}
//
//	public Bien removeBienTbls2(Bien bienTbls2) {
//		getBienTbls2().remove(bienTbls2);
//		bienTbls2.setDetalleBienTbl2(null);
//
//		return bienTbls2;
//	}

//	public List<Bien> getBienTbls3() {
//		return this.bienTbls3;
//	}
//
//	public void setBienTbls3(List<Bien> bienTbls3) {
//		this.bienTbls3 = bienTbls3;
//	}

//	public Bien addBienTbls3(Bien bienTbls3) {
//		getBienTbls3().add(bienTbls3);
//		bienTbls3.setDetalleBienTbl3(this);
//
//		return bienTbls3;
//	}
//
//	public Bien removeBienTbls3(Bien bienTbls3) {
//		getBienTbls3().remove(bienTbls3);
//		bienTbls3.setDetalleBienTbl3(null);
//
//		return bienTbls3;
//	}

	public CabeceraBien getCabeceraBienTbl() {
		return this.cabeceraBienTbl;
	}

	public void setCabeceraBienTbl(CabeceraBien cabeceraBienTbl) {
		this.cabeceraBienTbl = cabeceraBienTbl;
	}

//	public List<Ingreso> getIngresoTbls() {
//		return this.ingresoTbls;
//	}
//
//	public void setIngresoTbls(List<Ingreso> ingresoTbls) {
//		this.ingresoTbls = ingresoTbls;
//	}

//	public Ingreso addIngresoTbl(Ingreso ingresoTbl) {
//		getIngresoTbls().add(ingresoTbl);
//		ingresoTbl.setDetalleBienTbl(this);
//
//		return ingresoTbl;
//	}
//
//	public Ingreso removeIngresoTbl(Ingreso ingresoTbl) {
//		getIngresoTbls().remove(ingresoTbl);
//		ingresoTbl.setDetalleBienTbl(null);
//
//		return ingresoTbl;
//	}
//
//	public List<Transaccion> getTransaccionTbls() {
//		return this.transaccionTbls;
//	}
//
//	public void setTransaccionTbls(List<Transaccion> transaccionTbls) {
//		this.transaccionTbls = transaccionTbls;
//	}

//	public Transaccion addTransaccionTbl(Transaccion transaccionTbl) {
//		getTransaccionTbls().add(transaccionTbl);
//		transaccionTbl.setDetalleBienTbl(this);
//
//		return transaccionTbl;
//	}
//
//	public Transaccion removeTransaccionTbl(Transaccion transaccionTbl) {
//		getTransaccionTbls().remove(transaccionTbl);
//		transaccionTbl.setDetalleBienTbl(null);
//
//		return transaccionTbl;
//	}

}