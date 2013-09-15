package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the eucaristia_tbl database table.
 * 
 */
@Entity
@Table(name="eucaristia_tbl")
public class EucaristiaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="euc_pk")
	private Integer eucPk;

	@Column(name="euc_costo")
	private BigDecimal eucCosto;

	@Column(name="euc_fecha_hora")
	private Timestamp eucFechaHora;

	@Column(name="euc_motivo")
	private String eucMotivo;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

    public EucaristiaTbl() {
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

	public Timestamp getEucFechaHora() {
		return this.eucFechaHora;
	}

	public void setEucFechaHora(Timestamp eucFechaHora) {
		this.eucFechaHora = eucFechaHora;
	}

	public String getEucMotivo() {
		return this.eucMotivo;
	}

	public void setEucMotivo(String eucMotivo) {
		this.eucMotivo = eucMotivo;
	}

	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
}