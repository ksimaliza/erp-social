package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.StringUtils;

import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;
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
	@SequenceGenerator(name="PROVEEDOR_PROVPK_GENERATOR", sequenceName="PROVEEDOR_PROV_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVEEDOR_PROVPK_GENERATOR")
	@Column(name="prov_pk")
	private Integer provPk;

	@Column(name="prov_apellido")
	private String provApellido;

	@Column(name="prov_direccion")
	private String provDireccion;

//	@NotNull(message = "El estado del proveedor no puede ser nulo")
	@Column(name="prov_estado")
	private String provEstado;

	@Column(name="prov_nombre")
	private String provNombre;

	@Column(name="prov_telefono")
	private String provTelefono;
	
	@Column(name="prov_email")
	private String provEmail;
	
	@Column(name="prov_documento_identificacion")
	private String provDocumentoIdentificacion;
	
	@Column(name="prov_notas")
	private String provNotas;
	
	@Column(name="det_catalogo_pais_cuidad")
	private String detCatalogoPaisCiudad;
	
	@Column(name="cab_catalogo_pais_cuidad")
	private String cabCatalogoPaisCiudad;
	
	//bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy="proveedor")
	private List<Ingreso> ingresoTbls;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_pais_cuidad", referencedColumnName="cab_catalogo_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_catalogo_pais_cuidad", referencedColumnName="det_catalogo_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleCatalogo dcCiudadPais;

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

	/**
	 * @return the provEmail
	 */
	public String getProvEmail() {
		return provEmail;
	}

	/**
	 * @param provEmail the provEmail to set
	 */
	public void setProvEmail(String provEmail) {
		this.provEmail = provEmail;
	}

	/**
	 * @return the provDocumentoIdentificacion
	 */
	public String getProvDocumentoIdentificacion() {
		return provDocumentoIdentificacion;
	}

	/**
	 * @param provDocumentoIdentificacion the provDocumentoIdentificacion to set
	 */
	public void setProvDocumentoIdentificacion(String provDocumentoIdentificacion) {
		this.provDocumentoIdentificacion = provDocumentoIdentificacion;
	}

	/**
	 * @return the provNotas
	 */
	public String getProvNotas() {
		return provNotas;
	}

	/**
	 * @param provNotas the provNotas to set
	 */
	public void setProvNotas(String provNotas) {
		this.provNotas = provNotas;
	}

	/**
	 * @return the detCatalogoPaisCiudad
	 */
	public String getDetCatalogoPaisCiudad() {
		return detCatalogoPaisCiudad;
	}

	/**
	 * @param detCatalogoPaisCiudad the detCatalogoPaisCiudad to set
	 */
	public void setDetCatalogoPaisCiudad(String detCatalogoPaisCiudad) {
		this.detCatalogoPaisCiudad = detCatalogoPaisCiudad;
	}

	/**
	 * @return the cabCatalogoPaisCiudad
	 */
	public String getCabCatalogoPaisCiudad() {
		return cabCatalogoPaisCiudad;
	}

	/**
	 * @param cabCatalogoPaisCiudad the cabCatalogoPaisCiudad to set
	 */
	public void setCabCatalogoPaisCiudad(String cabCatalogoPaisCiudad) {
		this.cabCatalogoPaisCiudad = cabCatalogoPaisCiudad;
	}

	/**
	 * @return the dcCiudadPais
	 */
	public DetalleCatalogo getDcCiudadPais() {
		return dcCiudadPais;
	}

	/**
	 * @param dcCiudadPais the dcCiudadPais to set
	 */
	public void setDcCiudadPais(DetalleCatalogo dcCiudadPais) {
		this.dcCiudadPais = dcCiudadPais;
	}
	
	public String getNpNombresCompletos () {
		if (StringUtils.isNotBlank(this.provNombre) && StringUtils.isNotBlank(this.provApellido)) {
			return UtilAplication.appendStringBuilder(this.provNombre, " ", this.provApellido).toString();
		}
		return null;
	}

}