package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_tbl database table.
 * 
 */
@Entity
@Table(name="usuario_tbl")
public class UsuarioTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usu_pk")
	private Integer usuPk;

	@Column(name="usu_contrasena")
	private String usuContrasena;

	@Column(name="usu_nombre")
	private String usuNombre;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

	//bi-directional many-to-one association to RolTbl
    @ManyToOne
	@JoinColumn(name="rol_fk")
	private RolTbl rolTbl;

    public UsuarioTbl() {
    }

	public Integer getUsuPk() {
		return this.usuPk;
	}

	public void setUsuPk(Integer usuPk) {
		this.usuPk = usuPk;
	}

	public String getUsuContrasena() {
		return this.usuContrasena;
	}

	public void setUsuContrasena(String usuContrasena) {
		this.usuContrasena = usuContrasena;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
	public RolTbl getRolTbl() {
		return this.rolTbl;
	}

	public void setRolTbl(RolTbl rolTbl) {
		this.rolTbl = rolTbl;
	}
	
}