package ec.edu.uce.erp.ejb.persistence.entity;

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
import javax.persistence.Table;


/**
 * The persistent class for the tipo_empresa_tbl database table.
 * 
 */
@Entity
@Table(name="tipo_empresa_tbl")
@NamedQuery(name="TipoEmpresa.findAll", query="SELECT t FROM TipoEmpresa t")
public class TipoEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_EMPRESA_TBL_TIPEMPRESAPK_GENERATOR", sequenceName="TIPO_EMPRESA_TBL_TIP_EMPRESA_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_EMPRESA_TBL_TIPEMPRESAPK_GENERATOR")
	@Column(name="tip_empresa_pk")
	private Integer tipEmpresaPk;

	@Column(name="tip_empresa_estado")
	private String tipEmpresaEstado;

	@Column(name="tip_empresa_nombre")
	private String tipEmpresaNombre;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="tipoEmpresaTbl")
	private List<Empresa> empresaTbls;

	public TipoEmpresa() {
	}

	public Integer getTipEmpresaPk() {
		return this.tipEmpresaPk;
	}

	public void setTipEmpresaPk(Integer tipEmpresaPk) {
		this.tipEmpresaPk = tipEmpresaPk;
	}

	public String getTipEmpresaEstado() {
		return this.tipEmpresaEstado;
	}

	public void setTipEmpresaEstado(String tipEmpresaEstado) {
		this.tipEmpresaEstado = tipEmpresaEstado;
	}

	public String getTipEmpresaNombre() {
		return this.tipEmpresaNombre;
	}

	public void setTipEmpresaNombre(String tipEmpresaNombre) {
		this.tipEmpresaNombre = tipEmpresaNombre;
	}

	public List<Empresa> getEmpresaTbls() {
		return this.empresaTbls;
	}

	public void setEmpresaTbls(List<Empresa> empresaTbls) {
		this.empresaTbls = empresaTbls;
	}

	public Empresa addEmpresaTbl(Empresa empresaTbl) {
		getEmpresaTbls().add(empresaTbl);
		empresaTbl.setTipoEmpresaTbl(this);

		return empresaTbl;
	}

	public Empresa removeEmpresaTbl(Empresa empresaTbl) {
		getEmpresaTbls().remove(empresaTbl);
		empresaTbl.setTipoEmpresaTbl(null);

		return empresaTbl;
	}

}