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
 * The persistent class for the cementerio_tbl database table.
 * 
 */
@Entity
@Table(name="cementerio_tbl")
@NamedQuery(name="Cementerio.findAll", query="SELECT c FROM Cementerio c")
public class Cementerio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CEMENTERIO_TBL_CEMPK_GENERATOR", sequenceName="CEMENTERIO_TBL_CEM_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEMENTERIO_TBL_CEMPK_GENERATOR")
	@Column(name="cem_pk")
	private Integer cemPk;

	@Column(name="cem_estado")
	private String cemEstado;

	@Column(name="cem_numero_nichos")
	private Integer cemNumeroNichos;

	@Column(name="cem_numero_sepulturas")
	private Integer cemNumeroSepulturas;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emp_fk")
	private Empresa empresaTbl;

	//bi-directional many-to-one association to Sepulcro
	@OneToMany(mappedBy="cementerioTbl")
	private List<Sepulcro> sepulcroTbls;

	public Cementerio() {
	}

	public Integer getCemPk() {
		return this.cemPk;
	}

	public void setCemPk(Integer cemPk) {
		this.cemPk = cemPk;
	}

	public String getCemEstado() {
		return this.cemEstado;
	}

	public void setCemEstado(String cemEstado) {
		this.cemEstado = cemEstado;
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

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	public List<Sepulcro> getSepulcroTbls() {
		return this.sepulcroTbls;
	}

	public void setSepulcroTbls(List<Sepulcro> sepulcroTbls) {
		this.sepulcroTbls = sepulcroTbls;
	}

	public Sepulcro addSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().add(sepulcroTbl);
		sepulcroTbl.setCementerioTbl(this);

		return sepulcroTbl;
	}

	public Sepulcro removeSepulcroTbl(Sepulcro sepulcroTbl) {
		getSepulcroTbls().remove(sepulcroTbl);
		sepulcroTbl.setCementerioTbl(null);

		return sepulcroTbl;
	}

}