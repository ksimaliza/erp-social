package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the persona_tbl database table.
 * 
 */
@Entity
@Table(name="persona_tbl")
public class PersonaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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

    @Temporal( TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_foto")
	private String perFoto;

	@Column(name="per_nombres")
	private String perNombres;

	@Column(name="per_telefono")
	private String perTelefono;

	//bi-directional many-to-one association to EmpleadoTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<EmpleadoTbl> empleadoTbls;

	//bi-directional many-to-one association to EstudianteTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<EstudianteTbl> estudianteTbls;

	//bi-directional many-to-one association to PadreRepresentanteTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<PadreRepresentanteTbl> padreRepresentanteTbls;

	//bi-directional many-to-one association to PersonaCertificadoTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<PersonaCertificadoTbl> personaCertificadoTbls;

	//bi-directional many-to-one association to PersonaInstitucionTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<PersonaInstitucionTbl> personaInstitucionTbls;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_nes_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_nes_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl1;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_est_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_est_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl2;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_pro_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_pro_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl3;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_fun_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_fun_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl4;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_tip_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_tip_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl5;

	//bi-directional many-to-one association to DetalleCatalogoTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_gen_fk", referencedColumnName="cab_catalogo_fk"),
		@JoinColumn(name="det_catalogo_gen_nivel1_fk", referencedColumnName="det_catalogo_nivel1")
		})
	private DetalleCatalogoTbl detalleCatalogoTbl6;

	//bi-directional many-to-one association to DetalleUbicacionTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_ubicacion_fk", referencedColumnName="cab_ubicacion_fk"),
		@JoinColumn(name="det_ubicacion_nivel1_fk", referencedColumnName="det_ubicacion_nivel1"),
		@JoinColumn(name="det_ubicacion_nivel2_fk", referencedColumnName="det_ubicacion_nivel2"),
		@JoinColumn(name="det_ubicacion_nivel3_fk", referencedColumnName="det_ubicacion_nivel3"),
		@JoinColumn(name="det_ubicacion_nivel4_fk", referencedColumnName="det_ubicacion_nivel4")
		})
	private DetalleUbicacionTbl detalleUbicacionTbl;

	//bi-directional many-to-one association to ProfesorTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<ProfesorTbl> profesorTbls;

	//bi-directional many-to-one association to UsuarioTbl
	@OneToMany(mappedBy="personaTbl")
	private Set<UsuarioTbl> usuarioTbls;

    public PersonaTbl() {
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

	public Set<EmpleadoTbl> getEmpleadoTbls() {
		return this.empleadoTbls;
	}

	public void setEmpleadoTbls(Set<EmpleadoTbl> empleadoTbls) {
		this.empleadoTbls = empleadoTbls;
	}
	
	public Set<EstudianteTbl> getEstudianteTbls() {
		return this.estudianteTbls;
	}

	public void setEstudianteTbls(Set<EstudianteTbl> estudianteTbls) {
		this.estudianteTbls = estudianteTbls;
	}
	
	public Set<PadreRepresentanteTbl> getPadreRepresentanteTbls() {
		return this.padreRepresentanteTbls;
	}

	public void setPadreRepresentanteTbls(Set<PadreRepresentanteTbl> padreRepresentanteTbls) {
		this.padreRepresentanteTbls = padreRepresentanteTbls;
	}
	
	public Set<PersonaCertificadoTbl> getPersonaCertificadoTbls() {
		return this.personaCertificadoTbls;
	}

	public void setPersonaCertificadoTbls(Set<PersonaCertificadoTbl> personaCertificadoTbls) {
		this.personaCertificadoTbls = personaCertificadoTbls;
	}
	
	public Set<PersonaInstitucionTbl> getPersonaInstitucionTbls() {
		return this.personaInstitucionTbls;
	}

	public void setPersonaInstitucionTbls(Set<PersonaInstitucionTbl> personaInstitucionTbls) {
		this.personaInstitucionTbls = personaInstitucionTbls;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl1() {
		return this.detalleCatalogoTbl1;
	}

	public void setDetalleCatalogoTbl1(DetalleCatalogoTbl detalleCatalogoTbl1) {
		this.detalleCatalogoTbl1 = detalleCatalogoTbl1;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl2() {
		return this.detalleCatalogoTbl2;
	}

	public void setDetalleCatalogoTbl2(DetalleCatalogoTbl detalleCatalogoTbl2) {
		this.detalleCatalogoTbl2 = detalleCatalogoTbl2;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl3() {
		return this.detalleCatalogoTbl3;
	}

	public void setDetalleCatalogoTbl3(DetalleCatalogoTbl detalleCatalogoTbl3) {
		this.detalleCatalogoTbl3 = detalleCatalogoTbl3;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl4() {
		return this.detalleCatalogoTbl4;
	}

	public void setDetalleCatalogoTbl4(DetalleCatalogoTbl detalleCatalogoTbl4) {
		this.detalleCatalogoTbl4 = detalleCatalogoTbl4;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl5() {
		return this.detalleCatalogoTbl5;
	}

	public void setDetalleCatalogoTbl5(DetalleCatalogoTbl detalleCatalogoTbl5) {
		this.detalleCatalogoTbl5 = detalleCatalogoTbl5;
	}
	
	public DetalleCatalogoTbl getDetalleCatalogoTbl6() {
		return this.detalleCatalogoTbl6;
	}

	public void setDetalleCatalogoTbl6(DetalleCatalogoTbl detalleCatalogoTbl6) {
		this.detalleCatalogoTbl6 = detalleCatalogoTbl6;
	}
	
	public DetalleUbicacionTbl getDetalleUbicacionTbl() {
		return this.detalleUbicacionTbl;
	}

	public void setDetalleUbicacionTbl(DetalleUbicacionTbl detalleUbicacionTbl) {
		this.detalleUbicacionTbl = detalleUbicacionTbl;
	}
	
	public Set<ProfesorTbl> getProfesorTbls() {
		return this.profesorTbls;
	}

	public void setProfesorTbls(Set<ProfesorTbl> profesorTbls) {
		this.profesorTbls = profesorTbls;
	}
	
	public Set<UsuarioTbl> getUsuarioTbls() {
		return this.usuarioTbls;
	}

	public void setUsuarioTbls(Set<UsuarioTbl> usuarioTbls) {
		this.usuarioTbls = usuarioTbls;
	}
	
}