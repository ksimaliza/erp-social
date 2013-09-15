package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cupo_tbl database table.
 * 
 */
@Entity
@Table(name="cupo_tbl")
public class CupoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cup_pk")
	private Integer cupPk;

	@Column(name="cup_cantidad")
	private Integer cupCantidad;

	//bi-directional many-to-one association to CursoTbl
	@OneToMany(mappedBy="cupoTbl")
	private Set<CursoTbl> cursoTbls;

    public CupoTbl() {
    }

	public Integer getCupPk() {
		return this.cupPk;
	}

	public void setCupPk(Integer cupPk) {
		this.cupPk = cupPk;
	}

	public Integer getCupCantidad() {
		return this.cupCantidad;
	}

	public void setCupCantidad(Integer cupCantidad) {
		this.cupCantidad = cupCantidad;
	}

	public Set<CursoTbl> getCursoTbls() {
		return this.cursoTbls;
	}

	public void setCursoTbls(Set<CursoTbl> cursoTbls) {
		this.cursoTbls = cursoTbls;
	}
	
}