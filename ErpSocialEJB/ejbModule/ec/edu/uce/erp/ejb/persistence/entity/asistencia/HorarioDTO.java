package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the asi_horario database table.
 * 
 */
@Entity
@Table(name="asi_horario")
@NamedQuery(name="HorarioDTO.findAll", query="SELECT h FROM HorarioDTO h")
public class HorarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_HORARIO_HORCODIGO_GENERATOR", sequenceName="ASI_HORARIO_HOR_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_HORARIO_HORCODIGO_GENERATOR")
	@Column(name="hor_codigo")
	private Integer horCodigo;

	@Column(name="hor_hora_inicio")
	private Timestamp horHoraInicio;

	@Column(name="hot_hora_fin")
	private Timestamp hotHoraFin;

	//bi-directional many-to-one association to DiaDTO
	@ManyToOne
	@JoinColumn(name="hor_dia")
	private DiaDTO asiDia;

	//bi-directional many-to-one association to TipoDTO
	@ManyToOne
	@JoinColumn(name="hor_tipo")
	private TipoDTO asiTipo;

	public HorarioDTO() {
	}

	public Integer getHorCodigo() {
		return this.horCodigo;
	}

	public void setHorCodigo(Integer horCodigo) {
		this.horCodigo = horCodigo;
	}

	public Timestamp getHorHoraInicio() {
		return this.horHoraInicio;
	}

	public void setHorHoraInicio(Timestamp horHoraInicio) {
		this.horHoraInicio = horHoraInicio;
	}

	public Timestamp getHotHoraFin() {
		return this.hotHoraFin;
	}

	public void setHotHoraFin(Timestamp hotHoraFin) {
		this.hotHoraFin = hotHoraFin;
	}

	public DiaDTO getAsiDia() {
		return this.asiDia;
	}

	public void setAsiDia(DiaDTO asiDia) {
		this.asiDia = asiDia;
	}

	public TipoDTO getAsiTipo() {
		return this.asiTipo;
	}

	public void setAsiTipo(TipoDTO asiTipo) {
		this.asiTipo = asiTipo;
	}

}