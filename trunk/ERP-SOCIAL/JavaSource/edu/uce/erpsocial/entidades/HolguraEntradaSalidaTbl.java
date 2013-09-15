package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the holgura_entrada_salida_tbl database table.
 * 
 */
@Entity
@Table(name="holgura_entrada_salida_tbl")
public class HolguraEntradaSalidaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hol_ent_sal_pk")
	private Integer holEntSalPk;

	@Column(name="hol_ent_sal_hora_ingreso")
	private Timestamp holEntSalHoraIngreso;

	@Column(name="hol_ent_sal_hora_salida")
	private Timestamp holEntSalHoraSalida;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

    public HolguraEntradaSalidaTbl() {
    }

	public Integer getHolEntSalPk() {
		return this.holEntSalPk;
	}

	public void setHolEntSalPk(Integer holEntSalPk) {
		this.holEntSalPk = holEntSalPk;
	}

	public Timestamp getHolEntSalHoraIngreso() {
		return this.holEntSalHoraIngreso;
	}

	public void setHolEntSalHoraIngreso(Timestamp holEntSalHoraIngreso) {
		this.holEntSalHoraIngreso = holEntSalHoraIngreso;
	}

	public Timestamp getHolEntSalHoraSalida() {
		return this.holEntSalHoraSalida;
	}

	public void setHolEntSalHoraSalida(Timestamp holEntSalHoraSalida) {
		this.holEntSalHoraSalida = holEntSalHoraSalida;
	}

	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
}