package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;


/**
 * The persistent class for the persona_tbl database table.
 * 
 */
@Entity
@Table(name="persona_tbl")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_TBL_PERPK_GENERATOR", sequenceName="PERSONA_TBL_PER_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_TBL_PERPK_GENERATOR")
	@Column(name="per_pk")
	private Integer perPk;

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

	@Column(name="per_foto_byte")
	private byte[] perFotoByte;
	
	@Column(name="per_nombres")
	private String perNombres;

	@Column(name="per_telefono")
	private String perTelefono;
	
	@Column(name="per_genero")
	private String perGenero;

	@Transient
	private Boolean perFotoVerificar;

	
	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="personaTbl")
	private List<Empleado> empleadoTbls;

	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="personaTbl")
	private List<Estudiante> estudianteTbls;

	//bi-directional many-to-one association to PadreRepresentante
	@OneToMany(mappedBy="personaTbl")
	private List<PadreRepresentante> padreRepresentanteTbls;

	//bi-directional many-to-one association to PersonaCertificado
	@OneToMany(mappedBy="personaTbl")
	private List<PersonaCertificado> personaCertificadoTbls;

	//bi-directional many-to-one association to PersonaEmpresa
	@OneToMany(mappedBy="personaTbl")
	private List<PersonaEmpresa> personaEmpresaTbls;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_gen_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_gen_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl1;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_nes_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_nes_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl2;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_pro_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_pro_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl3;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_fun_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_fun_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl4;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_tip_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_tip_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl5;

	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_est_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_est_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogo detalleCatalogoTbl6;

	//bi-directional many-to-one association to DetalleUbicacion
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_ubicacion_fk", referencedColumnName="cab_ubicacion_fk"),
		@JoinColumn(name="det_ubicacion_nivel1_fk", referencedColumnName="det_ubicacion_nivel1"),
		@JoinColumn(name="det_ubicacion_nivel2_fk", referencedColumnName="det_ubicacion_nivel2"),
		@JoinColumn(name="det_ubicacion_nivel3_fk", referencedColumnName="det_ubicacion_nivel3"),
		@JoinColumn(name="det_ubicacion_nivel4_fk", referencedColumnName="det_ubicacion_nivel4")
		})
	private DetalleUbicacion detalleUbicacionTbl;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario segtUsuario;

	//bi-directional many-to-one association to Profesor
	@OneToMany(mappedBy="personaTbl")
	private List<Profesor> profesorTbls;

	public Persona() {
	}

	public Integer getPerPk() {
		return this.perPk;
	}

	public void setPerPk(Integer perPk) {
		this.perPk = perPk;
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

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public Boolean getPerFotoVerificar() {
		UtilAplication.saveToDisk(this.perFotoByte, this.perFoto);
		return perFotoVerificar;
	}

	public void setPerFotoVerificar(Boolean perFotoVerificar) {
		this.perFotoVerificar = perFotoVerificar;
	}

	public byte[] getPerFotoByte() {
		return perFotoByte;
	}

	public void setPerFotoByte(byte[] perFotoByte) {
		this.perFotoByte = perFotoByte;
	}

	public List<Empleado> getEmpleadoTbls() {
		return this.empleadoTbls;
	}

	public void setEmpleadoTbls(List<Empleado> empleadoTbls) {
		this.empleadoTbls = empleadoTbls;
	}

	public Empleado addEmpleadoTbl(Empleado empleadoTbl) {
		getEmpleadoTbls().add(empleadoTbl);
		empleadoTbl.setPersonaTbl(this);

		return empleadoTbl;
	}

	public Empleado removeEmpleadoTbl(Empleado empleadoTbl) {
		getEmpleadoTbls().remove(empleadoTbl);
		empleadoTbl.setPersonaTbl(null);

		return empleadoTbl;
	}

	public List<Estudiante> getEstudianteTbls() {
		return this.estudianteTbls;
	}

	public void setEstudianteTbls(List<Estudiante> estudianteTbls) {
		this.estudianteTbls = estudianteTbls;
	}

	public Estudiante addEstudianteTbl(Estudiante estudianteTbl) {
		getEstudianteTbls().add(estudianteTbl);
		estudianteTbl.setPersonaTbl(this);

		return estudianteTbl;
	}

	public Estudiante removeEstudianteTbl(Estudiante estudianteTbl) {
		getEstudianteTbls().remove(estudianteTbl);
		estudianteTbl.setPersonaTbl(null);

		return estudianteTbl;
	}

	public List<PadreRepresentante> getPadreRepresentanteTbls() {
		return this.padreRepresentanteTbls;
	}

	public void setPadreRepresentanteTbls(List<PadreRepresentante> padreRepresentanteTbls) {
		this.padreRepresentanteTbls = padreRepresentanteTbls;
	}

	public PadreRepresentante addPadreRepresentanteTbl(PadreRepresentante padreRepresentanteTbl) {
		getPadreRepresentanteTbls().add(padreRepresentanteTbl);
		padreRepresentanteTbl.setPersonaTbl(this);

		return padreRepresentanteTbl;
	}

	public PadreRepresentante removePadreRepresentanteTbl(PadreRepresentante padreRepresentanteTbl) {
		getPadreRepresentanteTbls().remove(padreRepresentanteTbl);
		padreRepresentanteTbl.setPersonaTbl(null);

		return padreRepresentanteTbl;
	}

	public List<PersonaCertificado> getPersonaCertificadoTbls() {
		return this.personaCertificadoTbls;
	}

	public void setPersonaCertificadoTbls(List<PersonaCertificado> personaCertificadoTbls) {
		this.personaCertificadoTbls = personaCertificadoTbls;
	}

	public PersonaCertificado addPersonaCertificadoTbl(PersonaCertificado personaCertificadoTbl) {
		getPersonaCertificadoTbls().add(personaCertificadoTbl);
		personaCertificadoTbl.setPersonaTbl(this);

		return personaCertificadoTbl;
	}

	public PersonaCertificado removePersonaCertificadoTbl(PersonaCertificado personaCertificadoTbl) {
		getPersonaCertificadoTbls().remove(personaCertificadoTbl);
		personaCertificadoTbl.setPersonaTbl(null);

		return personaCertificadoTbl;
	}

	public List<PersonaEmpresa> getPersonaEmpresaTbls() {
		return this.personaEmpresaTbls;
	}

	public void setPersonaEmpresaTbls(List<PersonaEmpresa> personaEmpresaTbls) {
		this.personaEmpresaTbls = personaEmpresaTbls;
	}

	public PersonaEmpresa addPersonaEmpresaTbl(PersonaEmpresa personaEmpresaTbl) {
		getPersonaEmpresaTbls().add(personaEmpresaTbl);
		personaEmpresaTbl.setPersonaTbl(this);

		return personaEmpresaTbl;
	}

	public PersonaEmpresa removePersonaEmpresaTbl(PersonaEmpresa personaEmpresaTbl) {
		getPersonaEmpresaTbls().remove(personaEmpresaTbl);
		personaEmpresaTbl.setPersonaTbl(null);

		return personaEmpresaTbl;
	}

	public DetalleCatalogo getDetalleCatalogoTbl1() {
		return this.detalleCatalogoTbl1;
	}

	public void setDetalleCatalogoTbl1(DetalleCatalogo detalleCatalogoTbl1) {
		this.detalleCatalogoTbl1 = detalleCatalogoTbl1;
	}

	public DetalleCatalogo getDetalleCatalogoTbl2() {
		return this.detalleCatalogoTbl2;
	}

	public void setDetalleCatalogoTbl2(DetalleCatalogo detalleCatalogoTbl2) {
		this.detalleCatalogoTbl2 = detalleCatalogoTbl2;
	}

	public DetalleCatalogo getDetalleCatalogoTbl3() {
		return this.detalleCatalogoTbl3;
	}

	public void setDetalleCatalogoTbl3(DetalleCatalogo detalleCatalogoTbl3) {
		this.detalleCatalogoTbl3 = detalleCatalogoTbl3;
	}

	public DetalleCatalogo getDetalleCatalogoTbl4() {
		return this.detalleCatalogoTbl4;
	}

	public void setDetalleCatalogoTbl4(DetalleCatalogo detalleCatalogoTbl4) {
		this.detalleCatalogoTbl4 = detalleCatalogoTbl4;
	}

	public DetalleCatalogo getDetalleCatalogoTbl5() {
		return this.detalleCatalogoTbl5;
	}

	public void setDetalleCatalogoTbl5(DetalleCatalogo detalleCatalogoTbl5) {
		this.detalleCatalogoTbl5 = detalleCatalogoTbl5;
	}

	public DetalleCatalogo getDetalleCatalogoTbl6() {
		return this.detalleCatalogoTbl6;
	}

	public void setDetalleCatalogoTbl6(DetalleCatalogo detalleCatalogoTbl6) {
		this.detalleCatalogoTbl6 = detalleCatalogoTbl6;
	}

	public DetalleUbicacion getDetalleUbicacionTbl() {
		return this.detalleUbicacionTbl;
	}

	public void setDetalleUbicacionTbl(DetalleUbicacion detalleUbicacionTbl) {
		this.detalleUbicacionTbl = detalleUbicacionTbl;
	}

	public Usuario getSegtUsuario() {
		return this.segtUsuario;
	}

	public void setSegtUsuario(Usuario segtUsuario) {
		this.segtUsuario = segtUsuario;
	}

	public List<Profesor> getProfesorTbls() {
		return this.profesorTbls;
	}

	public void setProfesorTbls(List<Profesor> profesorTbls) {
		this.profesorTbls = profesorTbls;
	}

	public Profesor addProfesorTbl(Profesor profesorTbl) {
		getProfesorTbls().add(profesorTbl);
		profesorTbl.setPersonaTbl(this);

		return profesorTbl;
	}

	public Profesor removeProfesorTbl(Profesor profesorTbl) {
		getProfesorTbls().remove(profesorTbl);
		profesorTbl.setPersonaTbl(null);

		return profesorTbl;
	}

	public String getPerGenero() {
		return perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}
	
	

}