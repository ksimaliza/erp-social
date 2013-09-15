package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the rol_tbl database table.
 * 
 */
@Entity
@Table(name="rol_tbl")
public class RolTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rol_pk")
	private Integer rolPk;

	@Column(name="rol_nombre")
	private String rolNombre;

	//bi-directional many-to-one association to UsuarioTbl
	@OneToMany(mappedBy="rolTbl")
	private Set<UsuarioTbl> usuarioTbls;

    public RolTbl() {
    }

	public Integer getRolPk() {
		return this.rolPk;
	}

	public void setRolPk(Integer rolPk) {
		this.rolPk = rolPk;
	}

	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Set<UsuarioTbl> getUsuarioTbls() {
		return this.usuarioTbls;
	}

	public void setUsuarioTbls(Set<UsuarioTbl> usuarioTbls) {
		this.usuarioTbls = usuarioTbls;
	}
	
}