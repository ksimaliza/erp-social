package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the eucaristia_tbl database table.
 * 
 */
@Entity
@Table(name="eucaristia_tbl")
@NamedQuery(name="Eucaristia.findAll", query="SELECT e FROM Eucaristia e")
public class Eucaristia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUCARISTIA_TBL_EUCPK_GENERATOR", sequenceName="EUCARISTIA_TBL_EUC_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUCARISTIA_TBL_EUCPK_GENERATOR")
	@Column(name="euc_pk")
	private Integer eucPk;

	@Column(name="euc_costo")
	private BigDecimal eucCosto;

	@Temporal(TemporalType.DATE)
	@Column(name="euc_fecha_hora")
	private Date eucFechaHora;

	@Column(name="euc_motivo")
	private String eucMotivo;

	@Column(name="euc_nombre_sacerdote")
	private String eucNombreSacerdote;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emr_fk")
	private Empresa empresaTbl;

	public Eucaristia() {
	}

	public Integer getEucPk() {
		return this.eucPk;
	}

	public void setEucPk(Integer eucPk) {
		this.eucPk = eucPk;
	}

	public BigDecimal getEucCosto() {
		return this.eucCosto;
	}

	public void setEucCosto(BigDecimal eucCosto) {
		this.eucCosto = eucCosto;
	}

	public Date getEucFechaHora() {
		return this.eucFechaHora;
	}

	public void setEucFechaHora(Date eucFechaHora) {
		this.eucFechaHora = eucFechaHora;
	}

	public String getEucMotivo() {
		return this.eucMotivo;
	}

	public void setEucMotivo(String eucMotivo) {
		this.eucMotivo = eucMotivo;
	}

	public String getEucNombreSacerdote() {
		return this.eucNombreSacerdote;
	}

	public void setEucNombreSacerdote(String eucNombreSacerdote) {
		this.eucNombreSacerdote = eucNombreSacerdote;
	}

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

}