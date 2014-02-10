package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the segt_historial_clave database table.
 * 
 */
@Entity
@Table(name="segt_historial_clave")
@NamedQuery(name="HistorialClave.findAll", query="SELECT h FROM HistorialClave h")
public class HistorialClave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_HISTORIAL_CLAVE_IDHISTORIALCLAVE_GENERATOR", sequenceName="SEGT_HISTORIAL_CLAVE_ID_HISTORIAL_CLAVE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_HISTORIAL_CLAVE_IDHISTORIALCLAVE_GENERATOR")
	@Column(name="id_historial_clave")
	private Integer idHistorialClave;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="pass_usuario")
	private String passUsuario;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario segtUsuario;

	public HistorialClave() {
	}

	public Integer getIdHistorialClave() {
		return this.idHistorialClave;
	}

	public void setIdHistorialClave(Integer idHistorialClave) {
		this.idHistorialClave = idHistorialClave;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getPassUsuario() {
		return this.passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public Usuario getSegtUsuario() {
		return this.segtUsuario;
	}

	public void setSegtUsuario(Usuario segtUsuario) {
		this.segtUsuario = segtUsuario;
	}

}