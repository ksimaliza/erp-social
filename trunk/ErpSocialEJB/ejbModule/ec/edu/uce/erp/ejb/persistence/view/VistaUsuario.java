package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the segv_usuario database table.
 * 
 */
@Entity
@Table(name="segv_usuario")
@NamedQuery(name="VistaUsuario.findAll", query="SELECT v FROM VistaUsuario v")
public class VistaUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@Column(name="ci_usuario")
	private String ciUsuario;

	@Column(name="email_usuario")
	private String emailUsuario;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="emr_pk")
	private Integer emrPk;

	private String estado;

	@Column(name="fecha_ultimo_ingreso")
	private Timestamp fechaUltimoIngreso;
	
	@Column(name="login_usuario")
	private String loginUsuario;

	private String usuario;

	public VistaUsuario() {
	}

	public String getCiUsuario() {
		return this.ciUsuario;
	}

	public void setCiUsuario(String ciUsuario) {
		this.ciUsuario = ciUsuario;
	}

	public String getEmailUsuario() {
		return this.emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
	}

	public Integer getEmrPk() {
		return this.emrPk;
	}

	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaUltimoIngreso() {
		return this.fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Timestamp fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}