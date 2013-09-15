package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the carpeta_tbl database table.
 * 
 */
@Entity
@Table(name="carpeta_tbl")
public class CarpetaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="carp_pk")
	private Integer carpPk;

	@Column(name="carp_direccion")
	private String carpDireccion;

	@Column(name="carp_nombre")
	private String carpNombre;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

	//bi-directional many-to-one association to DocumentoTbl
	@OneToMany(mappedBy="carpetaTbl")
	private Set<DocumentoTbl> documentoTbls;

    public CarpetaTbl() {
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

	public String getCarpNombre() {
		return this.carpNombre;
	}

	public void setCarpNombre(String carpNombre) {
		this.carpNombre = carpNombre;
	}

	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
	public Set<DocumentoTbl> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(Set<DocumentoTbl> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}
	
}