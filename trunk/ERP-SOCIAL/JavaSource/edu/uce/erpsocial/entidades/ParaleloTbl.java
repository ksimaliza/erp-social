package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the paralelo_tbl database table.
 * 
 */
@Entity
@Table(name="paralelo_tbl")
public class ParaleloTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_pk")
	private Integer parPk;

	@Column(name="par_nombre")
	private String parNombre;

	//bi-directional many-to-one association to CursoTbl
	@OneToMany(mappedBy="paraleloTbl")
	private Set<CursoTbl> cursoTbls;

    public ParaleloTbl() {
    }

	public Integer getParPk() {
		return this.parPk;
	}

	public void setParPk(Integer parPk) {
		this.parPk = parPk;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public Set<CursoTbl> getCursoTbls() {
		return this.cursoTbls;
	}

	public void setCursoTbls(Set<CursoTbl> cursoTbls) {
		this.cursoTbls = cursoTbls;
	}
	
}