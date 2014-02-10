package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the carpeta_tbl database table.
 * 
 */
@Entity
@Table(name="carpeta_tbl")
@NamedQuery(name="Carpeta.findAll", query="SELECT c FROM Carpeta c")
public class Carpeta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARPETA_TBL_CARPPK_GENERATOR", sequenceName="CARPETA_TBL_CARP_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARPETA_TBL_CARPPK_GENERATOR")
	@Column(name="carp_pk")
	private Integer carpPk;

	@Column(name="carp_direccion")
	private String carpDireccion;

	@Column(name="carp_estado")
	private String carpEstado;

	@Column(name="carp_nombre")
	private String carpNombre;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emr_fk")
	private Empresa empresaTbl;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="carpetaTbl")
	private List<Documento> documentoTbls;

	public Carpeta() {
	}

	public Integer getCarpPk() {
		return this.carpPk;
	}

	public void setCarpPk(Integer carpPk) {
		this.carpPk = carpPk;
	}

	public String getCarpDireccion() {
		return this.carpDireccion;
	}

	public void setCarpDireccion(String carpDireccion) {
		this.carpDireccion = carpDireccion;
	}

	public String getCarpEstado() {
		return this.carpEstado;
	}

	public void setCarpEstado(String carpEstado) {
		this.carpEstado = carpEstado;
	}

	public String getCarpNombre() {
		return this.carpNombre;
	}

	public void setCarpNombre(String carpNombre) {
		this.carpNombre = carpNombre;
	}

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	public List<Documento> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(List<Documento> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}

	public Documento addDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().add(documentoTbl);
		documentoTbl.setCarpetaTbl(this);

		return documentoTbl;
	}

	public Documento removeDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().remove(documentoTbl);
		documentoTbl.setCarpetaTbl(null);

		return documentoTbl;
	}

}