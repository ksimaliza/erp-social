package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="cementerio_tbl")
public class CementerioTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cem_pk")
	private Integer cemPk;

	@Column(name="cem_numero_nichos")
	private Integer cemNumeroNichos;

	@Column(name="cem_numero_sepulturas")
	private Integer cemNumeroSepulturas;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

	//bi-directional many-to-one association to SepulcroTbl
	@OneToMany(mappedBy="cementerioTbl")
	private Set<SepulcroTbl> sepulcroTbls;

    public CementerioTbl() {
    }

	public Integer getCemPk() {
		return this.cemPk;
	}

	public void setCemPk(Integer cemPk) {
		this.cemPk = cemPk;
	}

	public Integer getCemNumeroNichos() {
		return this.cemNumeroNichos;
	}

	public void setCemNumeroNichos(Integer cemNumeroNichos) {
		this.cemNumeroNichos = cemNumeroNichos;
	}

	public Integer getCemNumeroSepulturas() {
		return this.cemNumeroSepulturas;
	}

	public void setCemNumeroSepulturas(Integer cemNumeroSepulturas) {
		this.cemNumeroSepulturas = cemNumeroSepulturas;
	}

	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
	public Set<SepulcroTbl> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(Set<SepulcroTbl> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}
	
}