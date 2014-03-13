package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asi_tipo database table.
 * 
 */
@Entity
@Table(name="asi_tipo")
@NamedQuery(name="TipoDTO.findAll", query="SELECT t FROM TipoDTO t")
public class TipoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_TIPO_TIPCODIGO_GENERATOR", sequenceName="ASI_TIPO_TIP_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_TIPO_TIPCODIGO_GENERATOR")
	@Column(name="tip_codigo")
	private Integer tipCodigo;

	@Column(name="tip_descripcion")
	private String tipDescripcion;

	//bi-directional many-to-one association to DiaDTO
	@OneToMany(mappedBy="asiTipo")
	private List<DiaDTO> asiDias;

	//bi-directional many-to-one association to HorarioEmpleadoDTO
	@OneToMany(mappedBy="asiTipo")
	private List<HorarioEmpleadoDTO> asiHorarioEmpleados;

	public TipoDTO() {
	}

	public Integer getTipCodigo() {
		return this.tipCodigo;
	}

	public void setTipCodigo(Integer tipCodigo) {
		this.tipCodigo = tipCodigo;
	}

	public String getTipDescripcion() {
		return this.tipDescripcion;
	}

	public void setTipDescripcion(String tipDescripcion) {
		this.tipDescripcion = tipDescripcion;
	}

	public List<DiaDTO> getAsiDias() {
		return this.asiDias;
	}

	public void setAsiDias(List<DiaDTO> asiDias) {
		this.asiDias = asiDias;
	}

	public DiaDTO addAsiDia(DiaDTO asiDia) {
		getAsiDias().add(asiDia);
		asiDia.setAsiTipo(this);

		return asiDia;
	}

	public DiaDTO removeAsiDia(DiaDTO asiDia) {
		getAsiDias().remove(asiDia);
		asiDia.setAsiTipo(null);

		return asiDia;
	}

	public List<HorarioEmpleadoDTO> getAsiHorarioEmpleados() {
		return this.asiHorarioEmpleados;
	}

	public void setAsiHorarioEmpleados(List<HorarioEmpleadoDTO> asiHorarioEmpleados) {
		this.asiHorarioEmpleados = asiHorarioEmpleados;
	}

	public HorarioEmpleadoDTO addAsiHorarioEmpleado(HorarioEmpleadoDTO asiHorarioEmpleado) {
		getAsiHorarioEmpleados().add(asiHorarioEmpleado);
		asiHorarioEmpleado.setAsiTipo(this);

		return asiHorarioEmpleado;
	}

	public HorarioEmpleadoDTO removeAsiHorarioEmpleado(HorarioEmpleadoDTO asiHorarioEmpleado) {
		getAsiHorarioEmpleados().remove(asiHorarioEmpleado);
		asiHorarioEmpleado.setAsiTipo(null);

		return asiHorarioEmpleado;
	}

}