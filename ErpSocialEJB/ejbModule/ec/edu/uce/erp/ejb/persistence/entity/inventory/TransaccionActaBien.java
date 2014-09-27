package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the transaccion_acta_bien database table.
 * 
 */
@Entity
@Table(name="transaccion_acta_bien")
@NamedQuery(name="TransaccionActaBien.findAll", query="SELECT t FROM TransaccionActaBien t")
public class TransaccionActaBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANSACCION_ACTA_BIEN_TRAACTBIE_GENERATOR", sequenceName="TRANSACCION_ACTA_BIEN_TRA_ACT_BIE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACCION_ACTA_BIEN_TRAACTBIE_GENERATOR")
	@Column(name="tra_act_bie")
	private Integer traActBie;

	@Column(name="tra_pk")
	private Integer traPk;
	
	@Column(name="act_bie_pk")
	private Integer actBiePk;

	//bi-directional many-to-one association to ActaBien
	@ManyToOne
	@JoinColumn(name="act_bie_pk", referencedColumnName="act_bie_pk", unique=false, nullable=true, insertable=false, updatable=false)
	private ActaBien actaBienTbl;
	
	@ManyToOne
	@JoinColumn(name="tra_pk", referencedColumnName="tra_pk", unique=false, nullable=true, insertable=false, updatable=false)
	private Transaccion transaccionTbl;

	public TransaccionActaBien() {
	}

	public Integer getTraActBie() {
		return this.traActBie;
	}

	public void setTraActBie(Integer traActBie) {
		this.traActBie = traActBie;
	}

	public Integer getTraPk() {
		return this.traPk;
	}

	public void setTraPk(Integer traPk) {
		this.traPk = traPk;
	}

	public ActaBien getActaBienTbl() {
		return this.actaBienTbl;
	}

	public void setActaBienTbl(ActaBien actaBienTbl) {
		this.actaBienTbl = actaBienTbl;
	}

	/**
	 * @return the actBiePk
	 */
	public Integer getActBiePk() {
		return actBiePk;
	}

	/**
	 * @param actBiePk the actBiePk to set
	 */
	public void setActBiePk(Integer actBiePk) {
		this.actBiePk = actBiePk;
	}

	/**
	 * @return the transaccionTbl
	 */
	public Transaccion getTransaccionTbl() {
		return transaccionTbl;
	}

	/**
	 * @param transaccionTbl the transaccionTbl to set
	 */
	public void setTransaccionTbl(Transaccion transaccionTbl) {
		this.transaccionTbl = transaccionTbl;
	}

}