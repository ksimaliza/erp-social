package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ec.edu.uce.erp.common.util.UtilAplication;


/**
 * The persistent class for the mat_matricula_vie database table.
 * 
 */
@Entity
@Table(name="mat_matricula_vie")
@NamedQuery(name="MatriculaVieDTO.findAll", query="SELECT m FROM MatriculaVieDTO m")
public class MatriculaVieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="asi_codigo")
	private Integer asiCodigo;

	@Column(name="asi_empresa")
	private Integer asiEmpresa;

	@Column(name="asi_materia")
	private Integer asiMateria;

	@Column(name="asi_nivel_paralelo")
	private Integer asiNivelParalelo;

	@Column(name="asi_periodo")
	private Integer asiPeriodo;

	@Column(name="asi_profesor")
	private Integer asiProfesor;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="est_codigo")
	private Integer estCodigo;

	@Column(name="est_empresa")
	private Integer estEmpresa;

	@Column(name="est_estado")
	private String estEstado;

	@Column(name="est_persona")
	private Integer estPersona;

	@Column(name="mat_asignacion")
	private Integer matAsignacion;

	@Id
	@Column(name="mat_codigo")
	private Integer matCodigo=null;

	@Column(name="mat_empresa")
	private Integer matEmpresa;

	@Column(name="mat_matricula")
	private Integer matMatricula;

	@Column(name="mtr_codigo")
	private Integer mtrCodigo;

	@Column(name="mtr_empresa")
	private Integer mtrEmpresa;

	@Column(name="mtr_nombe")
	private String mtrNombe;

	@Column(name="niv_codigo")
	private Integer nivCodigo;

	@Column(name="niv_descaripcion")
	private String nivDescaripcion;

	@Column(name="niv_empresa")
	private Integer nivEmpresa;

	@Column(name="npa_codigo")
	private Integer npaCodigo;

	@Column(name="npa_empresa")
	private Integer npaEmpresa;

	@Column(name="npa_nivel")
	private Integer npaNivel;

	@Column(name="npa_paralelo")
	private Integer npaParalelo;

	@Column(name="par_codigo")
	private Integer parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_empresa")
	private Integer parEmpresa;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_celular")
	private String perCelular;

	@Column(name="per_ci")
	private String perCi;

	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_descripcion")
	private String perDescripcion;

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

	@Column(name="per_pk")
	private Integer perPk;

	@Column(name="per_telefono")
	private String perTelefono;

	@Column(name="reg_codigo")
	private Integer regCodigo;

	@Column(name="reg_empresa")
	private Integer regEmpresa;

	@Column(name="reg_estudiante")
	private Integer regEstudiante;

	@Temporal(TemporalType.DATE)
	@Column(name="reg_fecha")
	private Date regFecha;

	@Column(name="reg_foto")
	private String regFoto;
	@Column(name="emr_direccion")
	private String emrDireccion;

	@Column(name="reg_foto_byte")
	private byte[] regFotoByte;
	
	@Column(name="emr_foto")
	private byte[] emrFoto;
	
	@Column(name="per_edad")
	private String perEdad;
	
	@Column(name="per_genero")
	private String perGenero;
	
	
//-->23-01-2015
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
	private String perObservaciones;
	
	//---Ficha 24-01-2015
	//---Ficha 22-01-2015 KS
		@Column(name="per_ci_padre")
		private String perCiPadre;
		@Column(name="per_nombres_padre")
		private String perNombresPadre;
		
		@Column(name="per_apellidos_padre")
		private String perApellidosPadre;
		
		@Temporal(TemporalType.DATE)
		@Column(name="per_fecha_nac_padre")
		private Date perFechaNacPadre;
		
		@Column(name="per_email_padre")
		private String perEmailPadre;
		
		@Column(name="per_telefono_padre")
		private String perTelefonoPadre;
		
		@Column(name="per_celular_padre")
		private String perCelularPadre;
		
		@Column(name="per_direccion_padre")
		private String perDireccionPadre;
		
		@Column(name="per_titulo_padre")
		private String perTituloPadre;
		
		@Column(name="per_ocupacion_padre")
		private String perOcupacionPadre;
		
		@Column(name="per_direccion_trabajo_padre")
		private String perDireccionTrabajoPadre;
		
		@Column(name="per_nume_hist_clinica_padre")
		private String perNumeHistClinicaPadre;
		
		
		
	
	
	
	
	//---Ficha 22-01-2015(KS) Informacion Madre
		@Column(name="per_ci_madre")
		private String perCiMadre;
		@Column(name="per_nombres_madre")
		private String perNombresMadre;
		
		@Column(name="per_apellidos_madre")
		private String perApellidosMadre;
		
		@Temporal(TemporalType.DATE)
		@Column(name="per_fecha_nac_madre")
		private Date perFechaNacMadre;
		
		@Column(name="per_email_madre")
		private String perEmailMadre;
		
		@Column(name="per_telefono_madre")
		private String perTelefonoMadre;
		
		@Column(name="per_celular_Madre")
		private String perCelularMadre;
		
		@Column(name="per_direccion_Madre")
		private String perDireccionMadre;
		
		@Column(name="per_titulo_Madre")
		private String perTituloMadre;
		
		@Column(name="per_ocupacion_Madre")
		private String perOcupacionMadre;
		
		@Column(name="per_direccion_trabajo_Madre")
		private String perDireccionTrabajoMadre;
		
		@Column(name="per_nume_hist_clinica_Madre")
		private String perNumeHistClinicaMadre;
		
		
		//---Ficha 22-01-2015(KS) Informacion Representante
		@Column(name="per_ci_representante")
		private String perCiRepresentante;
		@Column(name="per_nombres_representante")
		private String perNombresRepresentante;
		
		@Column(name="per_apellidos_representante")
		private String perApellidosRepresentante;
		
		@Temporal(TemporalType.DATE)
		@Column(name="per_fecha_nac_representante")
		private Date perFechaNacRepresentante;
		
		@Column(name="per_email_representante")
		private String perEmailRepresentante;
		
		@Column(name="per_telefono_representante")
		private String perTelefonoRepresentante;
		
		@Column(name="per_celular_representante")
		private String perCelularRepresentante;
		
		@Column(name="per_direccion_representante")
		private String perDireccionRepresentante;
		
		@Column(name="per_titulo_representante")
		private String perTituloRepresentante;
		
		@Column(name="per_ocupacion_representante")
		private String perOcupacionRepresentante;
		
		@Column(name="per_direccion_trabajo_representante")
		private String perDireccionTrabajoRepresentante;
		
		@Column(name="per_nume_hist_clinica_representante")
		private String perNumeHistClinicaRepresentante;
	
	
	
	
	
	@Transient
	private Boolean regVerificarFoto;
	
	@Transient
	private InputStream fotoStream;
	
	@Transient
	private InputStream fotoEmpStream;


	public MatriculaVieDTO() {
	}

	public MatriculaVieDTO(String perCi,
			String perApellidos, 
			String perNombres,
			String nivDescaripcion, 
			String parDescripcion, 
			String perDescripcion, 
			Date perFechaNac,
			String emrNombre,
			String emrDireccion,
			String estEstado,
			byte[] regFotoByte,
			byte[] emrFoto,
			Integer regCodigo
			
			) {
		super();//reporte carnet y certificado
		this.emrNombre = emrNombre;
		this.nivDescaripcion = nivDescaripcion;
		this.parDescripcion = parDescripcion;
		this.perApellidos = perApellidos;
		this.perCi = perCi;
		this.perDescripcion = perDescripcion;
		this.perFechaNac = perFechaNac;
		this.perNombres = perNombres;
		this.emrDireccion=emrDireccion;
		this.estEstado=estEstado;
		this.regFotoByte = regFotoByte;
		this.emrFoto=emrFoto;
	    this.regCodigo=regCodigo;
	   
	}

	public MatriculaVieDTO(Integer regCodigo,
			String perCi,
			String perApellidos, 
			String perNombres,
			String nivDescaripcion, 
			String parDescripcion, 
			Date regFecha,
			String emrNombre,
			String regFoto,
			byte[] regFotoByte,
			Date perFechaNac,
			 String perEdad, 
			 String perGenero,
			 byte[] emrFoto,
			 String perDescripcion,
			 String emrDireccion
			
			
			) {//lista curso
		super();
		this.regCodigo=regCodigo;
		this.perCi = perCi;
		this.perApellidos = perApellidos;
		this.perNombres = perNombres;
		this.nivDescaripcion = nivDescaripcion;
		this.parDescripcion = parDescripcion;
		this.regFecha=regFecha;
		this.emrNombre = emrNombre;
		this.regFoto=regFoto;
		this.regFotoByte = regFotoByte;
		this.perFechaNac=perFechaNac;
		this.perEdad=perEdad;
		this.perGenero=perGenero;
		this.emrFoto=emrFoto;
		this.perDescripcion=perDescripcion;
		this.emrDireccion=emrDireccion;
		
	}
	
	
	public MatriculaVieDTO(String perCi,
			String perApellidos, 
			String perNombres,
			String nivDescaripcion, 
			String parDescripcion, 
			String perDescripcion, 
			Date perFechaNac,
			String emrNombre,
			String emrDireccion,
			String estEstado,
			byte[] regFotoByte,
			byte[] emrFoto,
			Integer regCodigo,
			String perGenero,
			String perTelefono,
			String perCallePrincipal,
			String perCalleSecundaria,
			String perViviendaEs,
			String perNumeroVivienda,
			String perLugarOcupa,
			Integer perCuantosHermanos,
			String perAlergias,
			String perPresentaDificultad,
			String perNumeHistClinica,
			String perDesMed,
			String perObservaciones,
			String perCiPadre,
			String perNombresPadre,
			String perApellidosPadre,
			Date perFechaNacPadre,
			String perEmailPadre,
			String perTelefonoPadre,
			String perCelularPadre,
			String perDireccionPadre,
			String perTituloPadre,
			String perOcupacionPadre,
			String perDireccionTrabajoPadre,
			String perNumeHistClinicaPadre,
			
			String perCiMadre,
			String perNombresMadre,
			String perApellidosMadre,
			Date perFechaNacMadre,
			String perEmailMadre,
			String perTelefonoMadre,
			String perCelularMadre,
			String perDireccionMadre,
			String perTituloMadre,
			String perOcupacionMadre,
			String perDireccionTrabajoMadre,
			String perNumeHistClinicaMadre,
			
			String perCiRepresentante,
			String perNombresRepresentante,
			String perApellidosRepresentante,
			Date perFechaNacRepresentante,
			String perEmailRepresentante,
			String perTelefonoRepresentante,
			String perCelularRepresentante,
			String perDireccionRepresentante,
			String perTituloRepresentante,
			String perOcupacionRepresentante,
			String perDireccionTrabajoRepresentante,
			String perNumeHistClinicaRepresentante
			
			) {
		super();//reporte ficha
		this.emrNombre = emrNombre;
		this.nivDescaripcion = nivDescaripcion;
		this.parDescripcion = parDescripcion;
		this.perApellidos = perApellidos;
		this.perCi = perCi;
		this.perDescripcion = perDescripcion;
		this.perFechaNac = perFechaNac;
		this.perNombres = perNombres;
		this.emrDireccion=emrDireccion;
		this.estEstado=estEstado;
		this.regFotoByte = regFotoByte;
		this.emrFoto=emrFoto;
	    this.regCodigo=regCodigo;
	    this.perCiMadre=perCiMadre;
	    this.perGenero=perGenero;
	    this.perTelefono=perTelefono;
	    this.perCallePrincipal=perCallePrincipal;
	    this.perCalleSecundaria=perCalleSecundaria;
	    this.perViviendaEs=perViviendaEs;
	    this.perNumeroVivienda=perNumeroVivienda;
	    this.perLugarOcupa=perLugarOcupa;
	    this.perCuantosHermanos=perCuantosHermanos;
	    this.perAlergias=perAlergias;
	    this.perPresentaDificultad=perPresentaDificultad;
	    this.perNumeHistClinica=perNumeHistClinica;
	    this.perDesMed=perDesMed;
	    this.perObservaciones=perObservaciones;
	    this.perCiPadre=perCiPadre;
	    this.perNombresPadre=perNombresPadre;
	    this.perApellidosPadre=perApellidosPadre;
	    this.perFechaNacPadre=perFechaNacPadre;
	    this.perEmailPadre=perEmailPadre;
	    this.perTelefonoPadre=perTelefonoPadre;
	    this.perCelularPadre=perCelularPadre;
	    this.perDireccionPadre=perDireccionPadre;
	    this.perTituloPadre=perTituloPadre;
	    this.perOcupacionPadre=perOcupacionPadre;
	    this.perDireccionTrabajoPadre=perDireccionTrabajoPadre;
	    this.perNumeHistClinicaPadre=perNumeHistClinicaPadre;
	    
	    this.perCiMadre=perCiMadre;
	    this.perNombresMadre=perNombresMadre;
	    this.perApellidosMadre=perApellidosMadre;
	    this.perFechaNacMadre=perFechaNacMadre;
	    this.perEmailMadre=perEmailMadre;
	    this.perTelefonoMadre=perTelefonoMadre;
	    this.perCelularMadre=perCelularMadre;
	    this.perDireccionMadre=perDireccionMadre;
	    this.perTituloMadre=perTituloMadre;
	    this.perOcupacionMadre=perOcupacionMadre;
	    this.perDireccionTrabajoMadre=perDireccionTrabajoMadre;
	    this.perNumeHistClinicaMadre=perNumeHistClinicaMadre;
	    
	    this.perCiRepresentante=perCiRepresentante;
	    this.perNombresRepresentante=perNombresRepresentante;
	    this.perApellidosRepresentante=perApellidosRepresentante;
	    this.perFechaNacRepresentante=perFechaNacRepresentante;
	    this.perEmailRepresentante=perEmailRepresentante;
	    this.perTelefonoRepresentante=perTelefonoRepresentante;
	    this.perCelularRepresentante=perCelularRepresentante;
	    this.perDireccionRepresentante=perDireccionRepresentante;
	    this.perTituloRepresentante=perTituloRepresentante;
	    this.perOcupacionRepresentante=perOcupacionRepresentante;
	    this.perDireccionTrabajoRepresentante=perDireccionTrabajoRepresentante;
	    this.perNumeHistClinicaRepresentante=perNumeHistClinicaRepresentante;
	   
	   
	    
	}
	
	
	
	public Integer getAsiCodigo() {
		return this.asiCodigo;
	}

	public void setAsiCodigo(Integer asiCodigo) {
		this.asiCodigo = asiCodigo;
	}

	public Integer getAsiEmpresa() {
		return this.asiEmpresa;
	}

	public void setAsiEmpresa(Integer asiEmpresa) {
		this.asiEmpresa = asiEmpresa;
	}

	public Integer getAsiMateria() {
		return this.asiMateria;
	}

	public void setAsiMateria(Integer asiMateria) {
		this.asiMateria = asiMateria;
	}

	public Integer getAsiNivelParalelo() {
		return this.asiNivelParalelo;
	}

	public void setAsiNivelParalelo(Integer asiNivelParalelo) {
		this.asiNivelParalelo = asiNivelParalelo;
	}

	public Integer getAsiPeriodo() {
		return this.asiPeriodo;
	}

	public void setAsiPeriodo(Integer asiPeriodo) {
		this.asiPeriodo = asiPeriodo;
	}

	public Integer getAsiProfesor() {
		return this.asiProfesor;
	}

	public void setAsiProfesor(Integer asiProfesor) {
		this.asiProfesor = asiProfesor;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
	}

	public Integer getEstCodigo() {
		return this.estCodigo;
	}

	public void setEstCodigo(Integer estCodigo) {
		this.estCodigo = estCodigo;
	}

	public Integer getEstEmpresa() {
		return this.estEmpresa;
	}

	public void setEstEmpresa(Integer estEmpresa) {
		this.estEmpresa = estEmpresa;
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

	public Integer getMatAsignacion() {
		return this.matAsignacion;
	}

	public void setMatAsignacion(Integer matAsignacion) {
		this.matAsignacion = matAsignacion;
	}

	public Integer getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(Integer matCodigo) {
		this.matCodigo = matCodigo;
	}

	public Integer getMatEmpresa() {
		return this.matEmpresa;
	}

	public void setMatEmpresa(Integer matEmpresa) {
		this.matEmpresa = matEmpresa;
	}

	public Integer getMatMatricula() {
		return this.matMatricula;
	}

	public void setMatMatricula(Integer matMatricula) {
		this.matMatricula = matMatricula;
	}

	public Integer getMtrCodigo() {
		return this.mtrCodigo;
	}

	public void setMtrCodigo(Integer mtrCodigo) {
		this.mtrCodigo = mtrCodigo;
	}

	public Integer getMtrEmpresa() {
		return this.mtrEmpresa;
	}

	public void setMtrEmpresa(Integer mtrEmpresa) {
		this.mtrEmpresa = mtrEmpresa;
	}

	public String getMtrNombe() {
		return this.mtrNombe;
	}

	public void setMtrNombe(String mtrNombe) {
		this.mtrNombe = mtrNombe;
	}

	public Integer getNivCodigo() {
		return this.nivCodigo;
	}

	public void setNivCodigo(Integer nivCodigo) {
		this.nivCodigo = nivCodigo;
	}

	public String getNivDescaripcion() {
		return this.nivDescaripcion;
	}

	public void setNivDescaripcion(String nivDescaripcion) {
		this.nivDescaripcion = nivDescaripcion;
	}

	public Integer getNivEmpresa() {
		return this.nivEmpresa;
	}

	public void setNivEmpresa(Integer nivEmpresa) {
		this.nivEmpresa = nivEmpresa;
	}

	public Integer getNpaCodigo() {
		return this.npaCodigo;
	}

	public void setNpaCodigo(Integer npaCodigo) {
		this.npaCodigo = npaCodigo;
	}

	public Integer getNpaEmpresa() {
		return this.npaEmpresa;
	}

	public void setNpaEmpresa(Integer npaEmpresa) {
		this.npaEmpresa = npaEmpresa;
	}

	public Integer getNpaNivel() {
		return this.npaNivel;
	}

	public void setNpaNivel(Integer npaNivel) {
		this.npaNivel = npaNivel;
	}

	public Integer getNpaParalelo() {
		return this.npaParalelo;
	}

	public void setNpaParalelo(Integer npaParalelo) {
		this.npaParalelo = npaParalelo;
	}

	public Integer getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(Integer parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public Integer getParEmpresa() {
		return this.parEmpresa;
	}

	public void setParEmpresa(Integer parEmpresa) {
		this.parEmpresa = parEmpresa;
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

	public Integer getPerCodigo() {
		return this.perCodigo;
	}

	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerDescripcion() {
		return this.perDescripcion;
	}

	public void setPerDescripcion(String perDescripcion) {
		this.perDescripcion = perDescripcion;
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

	public Integer getRegCodigo() {
		return this.regCodigo;
	}

	public void setRegCodigo(Integer regCodigo) {
		this.regCodigo = regCodigo;
	}

	public Integer getRegEmpresa() {
		return this.regEmpresa;
	}

	public void setRegEmpresa(Integer regEmpresa) {
		this.regEmpresa = regEmpresa;
	}

	public Integer getRegEstudiante() {
		return this.regEstudiante;
	}

	public void setRegEstudiante(Integer regEstudiante) {
		this.regEstudiante = regEstudiante;
	}

	public Date getRegFecha() {
		return this.regFecha;
	}

	public void setRegFecha(Date regFecha) {
		this.regFecha = regFecha;
	}

	public String getRegFoto() {
		return this.regFoto;
	}

	public void setRegFoto(String regFoto) {
		this.regFoto = regFoto;
	}

	public byte[] getRegFotoByte() {
		return this.regFotoByte;
	}

	public void setRegFotoByte(byte[] regFotoByte) {
		this.regFotoByte = regFotoByte;
	}

	public Boolean getRegVerificarFoto() {
		UtilAplication.saveToDisk(this.regFotoByte, this.regFoto);
		return regVerificarFoto;
	}

	public void setRegVerificarFoto(Boolean regVerificarFoto) {
		this.regVerificarFoto = regVerificarFoto;
	}

	public InputStream getFotoStream() {
		if(this.getRegFotoByte()!=null)
			this.fotoStream=new ByteArrayInputStream(this.getRegFotoByte());
		return fotoStream;
	}

	public void setFotoStream(InputStream fotoStream) {
		this.fotoStream = fotoStream;
	}

	public String getEmrDireccion() {
		return emrDireccion;
	}

	public void setEmrDireccion(String emrDireccion) {
		this.emrDireccion = emrDireccion;
	}

	public byte[] getEmrFoto() {
		return emrFoto;
	}

	public void setEmrFoto(byte[] emrFoto) {
		this.emrFoto = emrFoto;
	}

	public InputStream getFotoEmpStream() {
		if(this.getEmrFoto()!=null)
			this.fotoEmpStream=new ByteArrayInputStream(this.getEmrFoto());
		return fotoEmpStream;
	}

	public void setFotoEmpStream(InputStream fotoEmpStream) {
		this.fotoEmpStream = fotoEmpStream;
	}

	public String getPerEdad() {
		return perEdad;
	}

	public void setPerEdad(String perEdad) {
		this.perEdad = perEdad;
	}

	public String getPerGenero() {
		return perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}
	//->>>22-01-15
	public String getPerCiMadre() {
		return perCiMadre;
	}

	public void setPerCiMadre(String perCiMadre) {
		this.perCiMadre = perCiMadre;
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

	public String getPerObservaciones() {
		return perObservaciones;
	}

	public void setPerObservaciones(String perObservaciones) {
		this.perObservaciones = perObservaciones;
	}

	public String getPerCiPadre() {
		return perCiPadre;
	}

	public void setPerCiPadre(String perCiPadre) {
		this.perCiPadre = perCiPadre;
	}

	public String getPerNombresPadre() {
		return perNombresPadre;
	}

	public void setPerNombresPadre(String perNombresPadre) {
		this.perNombresPadre = perNombresPadre;
	}

	public String getPerApellidosPadre() {
		return perApellidosPadre;
	}

	public void setPerApellidosPadre(String perApellidosPadre) {
		this.perApellidosPadre = perApellidosPadre;
	}

	public Date getPerFechaNacPadre() {
		return perFechaNacPadre;
	}

	public void setPerFechaNacPadre(Date perFechaNacPadre) {
		this.perFechaNacPadre = perFechaNacPadre;
	}

	public String getPerEmailPadre() {
		return perEmailPadre;
	}

	public void setPerEmailPadre(String perEmailPadre) {
		this.perEmailPadre = perEmailPadre;
	}

	public String getPerTelefonoPadre() {
		return perTelefonoPadre;
	}

	public void setPerTelefonoPadre(String perTelefonoPadre) {
		this.perTelefonoPadre = perTelefonoPadre;
	}

	public String getPerCelularPadre() {
		return perCelularPadre;
	}

	public void setPerCelularPadre(String perCelularPadre) {
		this.perCelularPadre = perCelularPadre;
	}

	public String getPerDireccionPadre() {
		return perDireccionPadre;
	}

	public void setPerDireccionPadre(String perDireccionPadre) {
		this.perDireccionPadre = perDireccionPadre;
	}

	public String getPerTituloPadre() {
		return perTituloPadre;
	}

	public void setPerTituloPadre(String perTituloPadre) {
		this.perTituloPadre = perTituloPadre;
	}

	

	

	public String getPerOcupacionPadre() {
		return perOcupacionPadre;
	}

	public void setPerOcupacionPadre(String perOcupacionPadre) {
		this.perOcupacionPadre = perOcupacionPadre;
	}

	public String getPerDireccionTrabajoPadre() {
		return perDireccionTrabajoPadre;
	}

	public void setPerDireccionTrabajoPadre(String perDireccionTrabajoPadre) {
		this.perDireccionTrabajoPadre = perDireccionTrabajoPadre;
	}

	public String getPerNumeHistClinicaPadre() {
		return perNumeHistClinicaPadre;
	}

	public void setPerNumeHistClinicaPadre(String perNumeHistClinicaPadre) {
		this.perNumeHistClinicaPadre = perNumeHistClinicaPadre;
	}

	public String getPerNombresMadre() {
		return perNombresMadre;
	}

	public void setPerNombresMadre(String perNombresMadre) {
		this.perNombresMadre = perNombresMadre;
	}

	public String getPerApellidosMadre() {
		return perApellidosMadre;
	}

	public void setPerApellidosMadre(String perApellidosMadre) {
		this.perApellidosMadre = perApellidosMadre;
	}

	public Date getPerFechaNacMadre() {
		return perFechaNacMadre;
	}

	public void setPerFechaNacMadre(Date perFechaNacMadre) {
		this.perFechaNacMadre = perFechaNacMadre;
	}

	public String getPerEmailMadre() {
		return perEmailMadre;
	}

	public void setPerEmailMadre(String perEmailMadre) {
		this.perEmailMadre = perEmailMadre;
	}

	public String getPerTelefonoMadre() {
		return perTelefonoMadre;
	}

	public void setPerTelefonoMadre(String perTelefonoMadre) {
		this.perTelefonoMadre = perTelefonoMadre;
	}

	public String getPerCelularMadre() {
		return perCelularMadre;
	}

	public void setPerCelularMadre(String perCelularMadre) {
		this.perCelularMadre = perCelularMadre;
	}

	public String getPerDireccionMadre() {
		return perDireccionMadre;
	}

	public void setPerDireccionMadre(String perDireccionMadre) {
		this.perDireccionMadre = perDireccionMadre;
	}

	public String getPerTituloMadre() {
		return perTituloMadre;
	}

	public void setPerTituloMadre(String perTituloMadre) {
		this.perTituloMadre = perTituloMadre;
	}

	public String getPerOcupacionMadre() {
		return perOcupacionMadre;
	}

	public void setPerOcupacionMadre(String perOcupacionMadre) {
		this.perOcupacionMadre = perOcupacionMadre;
	}

	public String getPerDireccionTrabajoMadre() {
		return perDireccionTrabajoMadre;
	}

	public void setPerDireccionTrabajoMadre(String perDireccionTrabajoMadre) {
		this.perDireccionTrabajoMadre = perDireccionTrabajoMadre;
	}

	public String getPerNumeHistClinicaMadre() {
		return perNumeHistClinicaMadre;
	}

	public void setPerNumeHistClinicaMadre(String perNumeHistClinicaMadre) {
		this.perNumeHistClinicaMadre = perNumeHistClinicaMadre;
	}

	public String getPerCiRepresentante() {
		return perCiRepresentante;
	}

	public void setPerCiRepresentante(String perCiRepresentante) {
		this.perCiRepresentante = perCiRepresentante;
	}

	public String getPerNombresRepresentante() {
		return perNombresRepresentante;
	}

	public void setPerNombresRepresentante(String perNombresRepresentante) {
		this.perNombresRepresentante = perNombresRepresentante;
	}

	public String getPerApellidosRepresentante() {
		return perApellidosRepresentante;
	}

	public void setPerApellidosRepresentante(String perApellidosRepresentante) {
		this.perApellidosRepresentante = perApellidosRepresentante;
	}

	public Date getPerFechaNacRepresentante() {
		return perFechaNacRepresentante;
	}

	public void setPerFechaNacRepresentante(Date perFechaNacRepresentante) {
		this.perFechaNacRepresentante = perFechaNacRepresentante;
	}

	public String getPerEmailRepresentante() {
		return perEmailRepresentante;
	}

	public void setPerEmailRepresentante(String perEmailRepresentante) {
		this.perEmailRepresentante = perEmailRepresentante;
	}

	public String getPerTelefonoRepresentante() {
		return perTelefonoRepresentante;
	}

	public void setPerTelefonoRepresentante(String perTelefonoRepresentante) {
		this.perTelefonoRepresentante = perTelefonoRepresentante;
	}

	public String getPerCelularRepresentante() {
		return perCelularRepresentante;
	}

	public void setPerCelularRepresentante(String perCelularRepresentante) {
		this.perCelularRepresentante = perCelularRepresentante;
	}

	public String getPerDireccionRepresentante() {
		return perDireccionRepresentante;
	}

	public void setPerDireccionRepresentante(String perDireccionRepresentante) {
		this.perDireccionRepresentante = perDireccionRepresentante;
	}

	public String getPerTituloRepresentante() {
		return perTituloRepresentante;
	}

	public void setPerTituloRepresentante(String perTituloRepresentante) {
		this.perTituloRepresentante = perTituloRepresentante;
	}

	public String getPerOcupacionRepresentante() {
		return perOcupacionRepresentante;
	}

	public void setPerOcupacionRepresentante(String perOcupacionRepresentante) {
		this.perOcupacionRepresentante = perOcupacionRepresentante;
	}

	public String getPerDireccionTrabajoRepresentante() {
		return perDireccionTrabajoRepresentante;
	}

	public void setPerDireccionTrabajoRepresentante(
			String perDireccionTrabajoRepresentante) {
		this.perDireccionTrabajoRepresentante = perDireccionTrabajoRepresentante;
	}

	public String getPerNumeHistClinicaRepresentante() {
		return perNumeHistClinicaRepresentante;
	}

	public void setPerNumeHistClinicaRepresentante(
			String perNumeHistClinicaRepresentante) {
		this.perNumeHistClinicaRepresentante = perNumeHistClinicaRepresentante;
	}
	
	
	
	

}