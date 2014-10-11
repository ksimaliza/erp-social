package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;


/**
 * The persistent class for the acta_bien_tbl database table.
 * 
 */
@Entity
@Table(name="acta_bien_tbl")
@NamedQuery(name="ActaBien.findAll", query="SELECT a FROM ActaBien a")
public class ActaBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACTA_BIEN_TBL_ACTBIEPK_GENERATOR", sequenceName="ACTA_BIEN_TBL_ACT_BIE_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACTA_BIEN_TBL_ACTBIEPK_GENERATOR")
	@Column(name="act_bie_pk")
	private Integer actBiePk;

	@Temporal(TemporalType.DATE)
	@Column(name="act_bie_fecha_gen")
	private Date actBieFechaGen;

	@Column(name="act_bie_num")
	private String actBieNum;

	//bi-directional many-to-one association to TransaccionActaBien
	@OneToMany(mappedBy="actaBienTbl", cascade = CascadeType.ALL)
	private List<TransaccionActaBien> transaccionActaBiens;
	
	@Transient
	private Integer npBiePk;
	
	@Transient
	private Integer npEmrPk;

	public ActaBien() {
	}

	public Integer getActBiePk() {
		return this.actBiePk;
	}

	public void setActBiePk(Integer actBiePk) {
		this.actBiePk = actBiePk;
	}

	public Date getActBieFechaGen() {
		return this.actBieFechaGen;
	}

	public void setActBieFechaGen(Date actBieFechaGen) {
		this.actBieFechaGen = actBieFechaGen;
	}

	public String getActBieNum() {
		return this.actBieNum;
	}

	public void setActBieNum(String actBieNum) {
		this.actBieNum = actBieNum;
	}

	public List<TransaccionActaBien> getTransaccionActaBiens() {
		return this.transaccionActaBiens;
	}

	public void setTransaccionActaBiens(List<TransaccionActaBien> transaccionActaBiens) {
		this.transaccionActaBiens = transaccionActaBiens;
	}

	public TransaccionActaBien addTransaccionActaBien(TransaccionActaBien transaccionActaBien) {
		getTransaccionActaBiens().add(transaccionActaBien);
		transaccionActaBien.setActaBienTbl(this);

		return transaccionActaBien;
	}

	public TransaccionActaBien removeTransaccionActaBien(TransaccionActaBien transaccionActaBien) {
		getTransaccionActaBiens().remove(transaccionActaBien);
		transaccionActaBien.setActaBienTbl(null);

		return transaccionActaBien;
	}

	/**
	 * @return the npBiePk
	 */
	public Integer getNpBiePk() {
		return npBiePk;
	}

	/**
	 * @param npBiePk the npBiePk to set
	 */
	public void setNpBiePk(Integer npBiePk) {
		this.npBiePk = npBiePk;
	}

	/**
	 * @return the npEmrPk
	 */
	public Integer getNpEmrPk() {
		return npEmrPk;
	}

	/**
	 * @param npEmrPk the npEmrPk to set
	 */
	public void setNpEmrPk(Integer npEmrPk) {
		this.npEmrPk = npEmrPk;
	}

}