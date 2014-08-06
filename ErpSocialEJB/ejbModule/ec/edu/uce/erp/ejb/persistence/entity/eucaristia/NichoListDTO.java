package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the euc_nicho_vie database table.
 * 
 */
@Entity
@Table(name="euc_nicho_vie")
@NamedQuery(name="NichoListDTO.findAll", query="SELECT n FROM NichoListDTO n")
public class NichoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_codigo")
	private Integer catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="cat_predecesor")
	private Integer catPredecesor;

	@Id
	@Column(name="nic_codigo")
	private Integer nicCodigo;

	@Column(name="nic_descripcion")
	private String nicDescripcion;

	@Column(name="nic_seccion")
	private Integer nicSeccion;

	@Column(name="nic_tipo")
	private Integer nicTipo;

	@Column(name="nni_codigo")
	private Integer nniCodigo;

	@Column(name="nni_descripcion")
	private String nniDescripcion;

	@Column(name="nni_nivel")
	private Integer nniNivel;

	@Column(name="tni_codigo")
	private Integer tniCodigo;

	@Column(name="tni_descripcion")
	private String tniDescripcion;

	public NichoListDTO() {
	}

	public Integer getCatCodigo() {
		return this.catCodigo;
	}

	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescripcion() {
		return this.catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public Integer getCatPredecesor() {
		return this.catPredecesor;
	}

	public void setCatPredecesor(Integer catPredecesor) {
		this.catPredecesor = catPredecesor;
	}

	public Integer getNicCodigo() {
		return this.nicCodigo;
	}

	public void setNicCodigo(Integer nicCodigo) {
		this.nicCodigo = nicCodigo;
	}

	public String getNicDescripcion() {
		return this.nicDescripcion;
	}

	public void setNicDescripcion(String nicDescripcion) {
		this.nicDescripcion = nicDescripcion;
	}

	public Integer getNicSeccion() {
		return this.nicSeccion;
	}

	public void setNicSeccion(Integer nicSeccion) {
		this.nicSeccion = nicSeccion;
	}

	public Integer getNicTipo() {
		return this.nicTipo;
	}

	public void setNicTipo(Integer nicTipo) {
		this.nicTipo = nicTipo;
	}

	public Integer getNniCodigo() {
		return this.nniCodigo;
	}

	public void setNniCodigo(Integer nniCodigo) {
		this.nniCodigo = nniCodigo;
	}

	public String getNniDescripcion() {
		return this.nniDescripcion;
	}

	public void setNniDescripcion(String nniDescripcion) {
		this.nniDescripcion = nniDescripcion;
	}

	public Integer getNniNivel() {
		return this.nniNivel;
	}

	public void setNniNivel(Integer nniNivel) {
		this.nniNivel = nniNivel;
	}

	public Integer getTniCodigo() {
		return this.tniCodigo;
	}

	public void setTniCodigo(Integer tniCodigo) {
		this.tniCodigo = tniCodigo;
	}

	public String getTniDescripcion() {
		return this.tniDescripcion;
	}

	public void setTniDescripcion(String tniDescripcion) {
		this.tniDescripcion = tniDescripcion;
	}

}