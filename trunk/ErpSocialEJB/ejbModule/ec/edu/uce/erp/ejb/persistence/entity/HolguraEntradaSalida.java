package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the holgura_entrada_salida_tbl database table.
 * 
 */
@Entity
@Table(name="holgura_entrada_salida_tbl")
@NamedQuery(name="HolguraEntradaSalida.findAll", query="SELECT h FROM HolguraEntradaSalida h")
public class HolguraEntradaSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOLGURA_ENTRADA_SALIDA_TBL_HOLENTSALPK_GENERATOR", sequenceName="HOLGURA_ENTRADA_SALIDA_TBL_HOL_ENT_SAL_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOLGURA_ENTRADA_SALIDA_TBL_HOLENTSALPK_GENERATOR")
	@Column(name="hol_ent_sal_pk")
	private Integer holEntSalPk;

	@Temporal(TemporalType.DATE)
	@Column(name="hol_ent_sal_hora_ingreso")
	private Date holEntSalHoraIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="hol_ent_sal_hora_salida")
	private Date holEntSalHoraSalida;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="holguraEntradaSalidaTbl")
	private List<Empresa> empresaTbls;

	public HolguraEntradaSalida() {
	}

	public Integer getHolEntSalPk() {
		return this.holEntSalPk;
	}

	public void setHolEntSalPk(Integer holEntSalPk) {
		this.holEntSalPk = holEntSalPk;
	}

	public Date getHolEntSalHoraIngreso() {
		return this.holEntSalHoraIngreso;
	}

	public void setHolEntSalHoraIngreso(Date holEntSalHoraIngreso) {
		this.holEntSalHoraIngreso = holEntSalHoraIngreso;
	}

	public Date getHolEntSalHoraSalida() {
		return this.holEntSalHoraSalida;
	}

	public void setHolEntSalHoraSalida(Date holEntSalHoraSalida) {
		this.holEntSalHoraSalida = holEntSalHoraSalida;
	}

	public List<Empresa> getEmpresaTbls() {
		return this.empresaTbls;
	}

	public void setEmpresaTbls(List<Empresa> empresaTbls) {
		this.empresaTbls = empresaTbls;
	}

	public Empresa addEmpresaTbl(Empresa empresaTbl) {
		getEmpresaTbls().add(empresaTbl);
		empresaTbl.setHolguraEntradaSalidaTbl(this);

		return empresaTbl;
	}

	public Empresa removeEmpresaTbl(Empresa empresaTbl) {
		getEmpresaTbls().remove(empresaTbl);
		empresaTbl.setHolguraEntradaSalidaTbl(null);

		return empresaTbl;
	}

}