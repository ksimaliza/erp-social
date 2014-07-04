package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asi_empleado database table.
 * 
 */
@Entity
@Table(name="asi_empleado")
@NamedQuery(name="EmpleadoDTO.findAll", query="SELECT e FROM EmpleadoDTO e")
public class EmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_EMPLEADO_AEMCODIGO_GENERATOR", sequenceName="ASI_EMPLEADO_AEM_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_EMPLEADO_AEMCODIGO_GENERATOR")
	@Column(name="aem_codigo")
	private Integer aemCodigo;

	@Column(name="aem_clave")
	private String aemClave;

	@Column(name="aem_codigo_registro")
	private String aemCodigoRegistro;

	@Column(name="aem_estado")
	private Boolean aemEstado=true;

	@Column(name="aem_usuario")
	private String aemUsuario;
	
	@Column(name="aem_empleado")
	private Integer aemEmpleado;

	@Column(name="aem_empresa")
	private Integer aemEmpresa;
	
	//bi-directional many-to-one association to FaltaDTO
	@OneToMany(mappedBy="asiEmpleado")
	private List<FaltaDTO> asiFaltas;

	//bi-directional many-to-one association to HorarioEmpleadoDTO
	@OneToMany(mappedBy="asiEmpleado")
	private List<HorarioEmpleadoDTO> asiHorarioEmpleados;

	//bi-directional many-to-one association to PermisoDTO
	@OneToMany(mappedBy="asiEmpleado")
	private List<PermisoDTO> asiPermisos;

	//bi-directional many-to-one association to RegistroDTO
	@OneToMany(mappedBy="asiEmpleado")
	private List<RegistroDTO> asiRegistros;

	public EmpleadoDTO() {
	}

	public Integer getAemCodigo() {
		return this.aemCodigo;
	}

	public void setAemCodigo(Integer aemCodigo) {
		this.aemCodigo = aemCodigo;
	}

	public String getAemClave() {
		return this.aemClave;
	}

	public void setAemClave(String aemClave) {
		this.aemClave = aemClave;
	}

	public String getAemCodigoRegistro() {
		return this.aemCodigoRegistro;
	}

	public void setAemCodigoRegistro(String aemCodigoRegistro) {
		this.aemCodigoRegistro = aemCodigoRegistro;
	}

	public Boolean getAemEstado() {
		return this.aemEstado;
	}

	public void setAemEstado(Boolean aemEstado) {
		this.aemEstado = aemEstado;
	}

	public String getAemUsuario() {
		return this.aemUsuario;
	}

	public void setAemUsuario(String aemUsuario) {
		this.aemUsuario = aemUsuario;
	}

	public Integer getAemEmpresa() {
		return aemEmpresa;
	}

	public void setAemEmpresa(Integer aemEmpresa) {
		this.aemEmpresa = aemEmpresa;
	}

	public List<FaltaDTO> getAsiFaltas() {
		return this.asiFaltas;
	}

	public void setAsiFaltas(List<FaltaDTO> asiFaltas) {
		this.asiFaltas = asiFaltas;
	}

	public FaltaDTO addAsiFalta(FaltaDTO asiFalta) {
		getAsiFaltas().add(asiFalta);
		asiFalta.setAsiEmpleado(this);

		return asiFalta;
	}

	public FaltaDTO removeAsiFalta(FaltaDTO asiFalta) {
		getAsiFaltas().remove(asiFalta);
		asiFalta.setAsiEmpleado(null);

		return asiFalta;
	}

	public List<HorarioEmpleadoDTO> getAsiHorarioEmpleados() {
		return this.asiHorarioEmpleados;
	}

	public void setAsiHorarioEmpleados(List<HorarioEmpleadoDTO> asiHorarioEmpleados) {
		this.asiHorarioEmpleados = asiHorarioEmpleados;
	}

	public Integer getAemEmpleado() {
		return aemEmpleado;
	}

	public void setAemEmpleado(Integer aemEmpleado) {
		this.aemEmpleado = aemEmpleado;
	}

	public HorarioEmpleadoDTO addAsiHorarioEmpleado(HorarioEmpleadoDTO asiHorarioEmpleado) {
		getAsiHorarioEmpleados().add(asiHorarioEmpleado);
		asiHorarioEmpleado.setAsiEmpleado(this);

		return asiHorarioEmpleado;
	}

	public HorarioEmpleadoDTO removeAsiHorarioEmpleado(HorarioEmpleadoDTO asiHorarioEmpleado) {
		getAsiHorarioEmpleados().remove(asiHorarioEmpleado);
		asiHorarioEmpleado.setAsiEmpleado(null);

		return asiHorarioEmpleado;
	}

	public List<PermisoDTO> getAsiPermisos() {
		return this.asiPermisos;
	}

	public void setAsiPermisos(List<PermisoDTO> asiPermisos) {
		this.asiPermisos = asiPermisos;
	}

	public PermisoDTO addAsiPermiso(PermisoDTO asiPermiso) {
		getAsiPermisos().add(asiPermiso);
		asiPermiso.setAsiEmpleado(this);

		return asiPermiso;
	}

	public PermisoDTO removeAsiPermiso(PermisoDTO asiPermiso) {
		getAsiPermisos().remove(asiPermiso);
		asiPermiso.setAsiEmpleado(null);

		return asiPermiso;
	}

	public List<RegistroDTO> getAsiRegistros() {
		return this.asiRegistros;
	}

	public void setAsiRegistros(List<RegistroDTO> asiRegistros) {
		this.asiRegistros = asiRegistros;
	}

	public RegistroDTO addAsiRegistro(RegistroDTO asiRegistro) {
		getAsiRegistros().add(asiRegistro);
		asiRegistro.setAsiEmpleado(this);

		return asiRegistro;
	}

	public RegistroDTO removeAsiRegistro(RegistroDTO asiRegistro) {
		getAsiRegistros().remove(asiRegistro);
		asiRegistro.setAsiEmpleado(null);

		return asiRegistro;
	}

}