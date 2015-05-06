package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
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
	
	@Column(name="per_calle_principal")
	private String perCallePrincipal;
	
	@Column(name="per_calle_secundaria")
	private String perCalleSecundaria;
	
	@Column(name="per_vivienda_es")
	private String perViviendaEs;
	
	@Column(name="per_numero_vivienda")
	private String perNumeroVivienda;
	
	@Column(name="per_lugar_ocupa")
	private String perLugarOcupa;
	
	@Column(name="per_cuantos_hermanos")
	private Integer perCuantosHermanos;
	
	
	@Column(name="per_alergias")
	private String perAlergias;
	
	@Column(name="per_presenta_dificultad")
	private String perPresentaDificultad;
	
	
	@Column(name="per_nume_hist_clinica")
	private String perNumeHistClinica;
	
	
	@Column(name="per_des_med")
	private String perDesMed;
	
	
	
	@Column(name="per_observaciones")
	private String per_observaciones;
	
	
	@Column(name="per_titulo")
	private String perTitulo;
	
	
	@Column(name="per_ocupacion")
	private String perOcupacion;
	
	
	@Column(name="per_direccion_trabajo")
	private String perDireccionTrabajo;
	
	

	@Transient
	private Boolean perFotoVerificar;

	
	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="personaTbl")
	private List<Empleado> empleadoTbls;



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

	public String getPerGenero() {
		return perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}
	
	
	 public String getPerCallePrincipal() {
		return perCallePrincipal;
	}

	public void setPerCallePrincipal(String perCallePrincipal) {
		this.perCallePrincipal = perCallePrincipal;
	}

	public String getPerCalleSecundaria() {
		return perCalleSecundaria;
	}

	public void setPerCalleSecundaria(String perCalleSecundaria) {
		this.perCalleSecundaria = perCalleSecundaria;
	}

	public String getPerViviendaEs() {
		return perViviendaEs;
	}

	public void setPerViviendaEs(String perViviendaEs) {
		this.perViviendaEs = perViviendaEs;
	}

	public String getPerNumeroVivienda() {
		return perNumeroVivienda;
	}

	public void setPerNumeroVivienda(String perNumeroVivienda) {
		this.perNumeroVivienda = perNumeroVivienda;
	}
	
	

	public String getPerLugarOcupa() {
		return perLugarOcupa;
	}

	public void setPerLugarOcupa(String perLugarOcupa) {
		this.perLugarOcupa = perLugarOcupa;
	}

	public Integer getPerCuantosHermanos() {
		return perCuantosHermanos;
	}

	public void setPerCuantosHermanos(Integer perCuantosHermanos) {
		this.perCuantosHermanos = perCuantosHermanos;
	}

	public String getPerAlergias() {
		return perAlergias;
	}

	public void setPerAlergias(String perAlergias) {
		this.perAlergias = perAlergias;
	}

	public String getPerPresentaDificultad() {
		return perPresentaDificultad;
	}

	public void setPerPresentaDificultad(String perPresentaDificultad) {
		this.perPresentaDificultad = perPresentaDificultad;
	}

	public String getPerNumeHistClinica() {
		return perNumeHistClinica;
	}

	public void setPerNumeHistClinica(String perNumeHistClinica) {
		this.perNumeHistClinica = perNumeHistClinica;
	}

	public String getPerDesMed() {
		return perDesMed;
	}

	public void setPerDesMed(String perDesMed) {
		this.perDesMed = perDesMed;
	}

	public String getPer_observaciones() {
		return per_observaciones;
	}

	public void setPer_observaciones(String per_observaciones) {
		this.per_observaciones = per_observaciones;
	}

	
	public String getPerTitulo() {
		return perTitulo;
	}

	public void setPerTitulo(String perTitulo) {
		this.perTitulo = perTitulo;
	}

	public String getPerOcupacion() {
		return perOcupacion;
	}

	public void setPerOcupacion(String perOcupacion) {
		this.perOcupacion = perOcupacion;
	}

	public String getPerDireccionTrabajo() {
		return perDireccionTrabajo;
	}

	public void setPerDireccionTrabajo(String perDireccionTrabajo) {
		this.perDireccionTrabajo = perDireccionTrabajo;
	}

	//Metodo para calcular la Edad de la Persona
	public Short getEdad() {
	    	
	    	if (this.getPerFechaNac() == null) return 0;
	    	
			Calendar fechaActual = Calendar.getInstance();
			Calendar fechaNacPersona = Calendar.getInstance();
			fechaNacPersona.setTime(this.getPerFechaNac());
			
			int edadPersona = fechaActual.get(Calendar.YEAR) - fechaNacPersona.get(Calendar.YEAR);
			if(fechaActual.get(Calendar.MONTH) < fechaNacPersona.get(Calendar.MONTH)){
		    	edadPersona = edadPersona-1; 
		    }else{
		    	if(fechaActual.get(Calendar.MONTH) == fechaNacPersona.get(Calendar.MONTH)){
		    		if(fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNacPersona.get(Calendar.DAY_OF_MONTH))
		    			edadPersona = edadPersona-1; 
		    	}
		    }
			
			return new Short(String.valueOf(edadPersona));
	    }    
	
	

}