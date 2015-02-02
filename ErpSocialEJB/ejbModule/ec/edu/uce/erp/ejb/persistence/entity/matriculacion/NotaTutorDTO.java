package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the not_parcial database table.
 * 
 */
@Entity
@Table(name = "not_nota_tutor")
@NamedQuery(name = "NotaTutorDTO.findAll", query = "SELECT p FROM NotaTutorDTO p")
public class NotaTutorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NOT_NOTA_TUTOR_NOT_NOTA_TUTOR_CODIGO_GENERATOR", sequenceName = "NOT_NOTA_TUTOR_NOT_NOTA_TUTOR_CODIGO_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOT_NOTA_TUTOR_NOT_NOTA_TUTOR_CODIGO_GENERATOR")
	@Column(name = "not_nota_tutor_codigo")
	private Integer codigoNotaTutor;

	@Column(name = "not_faltas_justificadas")
	private Integer faltasJustificadas;

	@Column(name = "not_faltas_injustificadas")
	private Integer faltaInjustificadas;

	@Column(name = "not_dias_laborados")
	private Integer diasLaborados;

	@Column(name = "not_total_dias_laborables")
	private Integer totalDiasLaborados;

	@Column(name = "not_comportamiento")
	private String comportamiento;

	@Column(name = "not_ciclo")
	private Integer ciclo;

	// bi-directional many-to-one association to ParcialDTO
	@ManyToOne
	@JoinColumn(name = "not_matricula")
	private MatriculaDTO matriculaBean;

	public NotaTutorDTO() {
	}

	public Integer getCodigoNotaTutor() {
		return codigoNotaTutor;
	}

	public void setCodigoNotaTutor(Integer codigoNotaTutor) {
		this.codigoNotaTutor = codigoNotaTutor;
	}

	public Integer getFaltasJustificadas() {
		return faltasJustificadas;
	}

	public void setFaltasJustificadas(Integer faltasJustificadas) {
		this.faltasJustificadas = faltasJustificadas;
	}

	public Integer getFaltaInjustificadas() {
		return faltaInjustificadas;
	}

	public void setFaltaInjustificadas(Integer faltaInjustificadas) {
		this.faltaInjustificadas = faltaInjustificadas;
	}

	public Integer getDiasLaborados() {
		return diasLaborados;
	}

	public void setDiasLaborados(Integer diasLaborados) {
		this.diasLaborados = diasLaborados;
	}

	public Integer getTotalDiasLaborados() {
		return totalDiasLaborados;
	}

	public void setTotalDiasLaborados(Integer totalDiasLaborados) {
		this.totalDiasLaborados = totalDiasLaborados;
	}

	public String getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public MatriculaDTO getMatriculaBean() {
		return matriculaBean;
	}

	public void setMatriculaBean(MatriculaDTO matriculaBean) {
		this.matriculaBean = matriculaBean;
	}

}