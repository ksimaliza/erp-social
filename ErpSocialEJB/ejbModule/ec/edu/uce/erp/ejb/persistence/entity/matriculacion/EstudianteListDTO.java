package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the mat_estudiante_vie database table.
 * 
 */
@Entity
@Table(name="mat_estudiante_vie")
@NamedQuery(name="EstudianteListDTO.findAll", query="SELECT m FROM EstudianteListDTO m")
public class EstudianteListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="est_codigo")
	private Integer estCodigo;

	@Column(name="est_estado")
	private String estEstado;

		
	@Column(name="est_persona")
	private Integer estPersona;
	
	@Column(name="est_empresa")
	private Integer estEmpresa;

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
	

	@Column(name="reg_codigo")
	private Integer regCodigo;
	
	@Column(name="est_madre")
	private Integer estMadre;

	@Column(name="est_padre")
	private Integer estPadre;
	
	@Column(name="est_representante")
	private Integer estRepresentante;
	
	
	
	public EstudianteListDTO() {
	}

	public Integer getEstCodigo() {
		return this.estCodigo;
	}

	public void setEstCodigo(Integer estCodigo) {
		this.estCodigo = estCodigo;
	}

	public String getEstEstado() {
		return this.estEstado;
	}

	public void setEstEstado(String estEstado) {
		this.estEstado = estEstado;
	}

	public Integer getEstPersona() {
		return this.estPersona;
	}

	public void setEstPersona(Integer estPersona) {
		this.estPersona = estPersona;
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

	public Integer getEstEmpresa() {
		return estEmpresa;
	}

	public void setEstEmpresa(Integer estEmpresa) {
		this.estEmpresa = estEmpresa;
	}

	public Integer getRegCodigo() {
		return regCodigo;
	}

	public void setRegCodigo(Integer regCodigo) {
		this.regCodigo = regCodigo;
	}

	public Integer getEstMadre() {
		return estMadre;
	}

	public void setEstMadre(Integer estMadre) {
		this.estMadre = estMadre;
	}

	public Integer getEstPadre() {
		return estPadre;
	}

	public void setEstPadre(Integer estPadre) {
		this.estPadre = estPadre;
	}

	public Integer getEstRepresentante() {
		return estRepresentante;
	}

	public void setEstRepresentante(Integer estRepresentante) {
		this.estRepresentante = estRepresentante;
	}
	

}