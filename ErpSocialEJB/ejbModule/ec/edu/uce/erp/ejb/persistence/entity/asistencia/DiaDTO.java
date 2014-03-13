package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the asi_dia database table.
 * 
 */
@Entity
@Table(name="asi_dia")
@NamedQuery(name="DiaDTO.findAll", query="SELECT d FROM DiaDTO d")
public class DiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_DIA_DIACODIGO_GENERATOR", sequenceName="ASI_DIA_DIA_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_DIA_DIACODIGO_GENERATOR")
	@Column(name="dia_codigo")
	private Integer diaCodigo;

	@Column(name="dia_fecha")
	private Timestamp diaFecha;

	//bi-directional many-to-one association to HorarioDTO
	@OneToMany(mappedBy="asiDia")
	private List<HorarioDTO> asiHorarios;

	//bi-directional many-to-one association to TipoDTO
	@ManyToOne
	@JoinColumn(name="dia_tipo")
	private TipoDTO asiTipo;

	public DiaDTO() {
	}

	public Integer getDiaCodigo() {
		return this.diaCodigo;
	}

	public void setDiaCodigo(Integer diaCodigo) {
		this.diaCodigo = diaCodigo;
	}

	public Timestamp getDiaFecha() {
		return this.diaFecha;
	}

	public void setDiaFecha(Timestamp diaFecha) {
		this.diaFecha = diaFecha;
	}

	public List<HorarioDTO> getAsiHorarios() {
		return this.asiHorarios;
	}

	public void setAsiHorarios(List<HorarioDTO> asiHorarios) {
		this.asiHorarios = asiHorarios;
	}

	public HorarioDTO addAsiHorario(HorarioDTO asiHorario) {
		getAsiHorarios().add(asiHorario);
		asiHorario.setAsiDia(this);

		return asiHorario;
	}

	public HorarioDTO removeAsiHorario(HorarioDTO asiHorario) {
		getAsiHorarios().remove(asiHorario);
		asiHorario.setAsiDia(null);

		return asiHorario;
	}

	public TipoDTO getAsiTipo() {
		return this.asiTipo;
	}

	public void setAsiTipo(TipoDTO asiTipo) {
		this.asiTipo = asiTipo;
	}

}