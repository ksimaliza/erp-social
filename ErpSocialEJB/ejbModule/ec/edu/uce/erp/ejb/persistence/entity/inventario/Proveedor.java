package ec.edu.uce.erp.ejb.persistence.entity.inventario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import ec.edu.uce.erp.ejb.persistence.entity.Ingreso;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROVEEDOR_PROVPK_GENERATOR", sequenceName="PROVEEDOR_PROV_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVEEDOR_PROVPK_GENERATOR")
	@Column(name="prov_pk")
	private Integer provPk;

	@Column(name="prov_apellido")
	private String provApellido;

	@Column(name="prov_direccion")
	private String provDireccion;

	@Column(name="prov_estado")
	private String provEstado;

	@Column(name="prov_nombre")
	private String provNombre;

	@Column(name="prov_telefono")
	private String provTelefono;

	//bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy="proveedor")
	private List<Ingreso> ingresoTbls;

	public Proveedor() {
	}

	public Integer getProvPk() {
		return this.provPk;
	}

	public void setProvPk(Integer provPk) {
		this.provPk = provPk;
	}

	public String getProvApellido() {
		return this.provApellido;
	}

	public void setProvApellido(String provApellido) {
		this.provApellido = provApellido;
	}

	public String getProvDireccion() {
		return this.provDireccion;
	}

	public void setProvDireccion(String provDireccion) {
		this.provDireccion = provDireccion;
	}

	public String getProvEstado() {
		return this.provEstado;
	}

	public void setProvEstado(String provEstado) {
		this.provEstado = provEstado;
	}

	public String getProvNombre() {
		return this.provNombre;
	}

	public void setProvNombre(String provNombre) {
		this.provNombre = provNombre;
	}

	public String getProvTelefono() {
		return this.provTelefono;
	}

	public void setProvTelefono(String provTelefono) {
		this.provTelefono = provTelefono;
	}

	public List<Ingreso> getIngresoTbls() {
		return this.ingresoTbls;
	}

	public void setIngresoTbls(List<Ingreso> ingresoTbls) {
		this.ingresoTbls = ingresoTbls;
	}

	public Ingreso addIngresoTbl(Ingreso ingresoTbl) {
		getIngresoTbls().add(ingresoTbl);
		ingresoTbl.setProveedor(this);

		return ingresoTbl;
	}

	public Ingreso removeIngresoTbl(Ingreso ingresoTbl) {
		getIngresoTbls().remove(ingresoTbl);
		ingresoTbl.setProveedor(null);

		return ingresoTbl;
	}

}