package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mat_representante_vie database table.
 * 
 */
@Entity
@Table(name="mat_representante_vie")
@NamedQuery(name="RepresentanteListDTO.findAll", query="SELECT m FROM RepresentanteListDTO m")
public class RepresentanteListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_celular")
	private String perCelular;

	@Column(name="per_ci")
	private String perCi;

	@Column(name="per_direccion")
	private String perDireccion;

	@Column(name="per_email")
	private String perEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_foto")
	private String perFoto;

	@Column(name="per_nombres")
	private String perNombres;

	@Id
	@Column(name="per_pk")
	private Integer perPk;

	@Column(name="per_telefono")
	private String perTelefono;

	@Column(name="rep_codigo")
	private Integer repCodigo;

	//@Column(name="rep_persona")
	//private Integer repPersona;
	
	//@Column(name="rep_empresa")
	//private Integer repEmpresa;

	public RepresentanteListDTO() {
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCelular() {
		return this.perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	public String getPerDireccion() {
		return this.perDireccion;
	}

	public void setPerDireccion(String perDireccion) {
		this.perDireccion = perDireccion;
	}

	public String getPerEmail() {
		return this.perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public Date getPerFechaNac() {
		return this.perFechaNac;
	}

	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
	}

	public String getPerFoto() {
		return this.perFoto;
	}

	public void setPerFoto(String perFoto) {
		this.perFoto = perFoto;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public Integer getPerPk() {
		return this.perPk;
	}

	public void setPerPk(Integer perPk) {
		this.perPk = perPk;
	}

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public Integer getRepCodigo() {
		return this.repCodigo;
	}

	public void setRepCodigo(Integer repCodigo) {
		this.repCodigo = repCodigo;
	}

	

}