package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the notas_tbl database table.
 * 
 */
@Entity
@Table(name="notas_tbl")
@NamedQuery(name="Notas.findAll", query="SELECT n FROM Notas n")
public class Notas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOTAS_TBL_NOTPK_GENERATOR", sequenceName="NOTAS_TBL_NOT_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTAS_TBL_NOTPK_GENERATOR")
	@Column(name="not_pk")
	private Integer notPk;

	@Column(name="not_asistencia_final")
	private String notAsistenciaFinal;

	@Column(name="not_asistencia_primer_bloque")
	private String notAsistenciaPrimerBloque;

	@Column(name="not_asistencia_segundo_bloque")
	private String notAsistenciaSegundoBloque;

	@Column(name="not_asistencia_tercer_bloque")
	private String notAsistenciaTercerBloque;

	@Column(name="not_estado_final_quimestre")
	private String notEstadoFinalQuimestre;

	@Column(name="not_examen_final")
	private BigDecimal notExamenFinal;

	@Column(name="not_nota_final_anio")
	private BigDecimal notNotaFinalAnio;

	@Column(name="not_nota_final_quimestre")
	private BigDecimal notNotaFinalQuimestre;

	@Column(name="not_primer_bloque")
	private BigDecimal notPrimerBloque;

	@Column(name="not_segundo_bloque")
	private BigDecimal notSegundoBloque;

	@Column(name="not_tercer_bloque")
	private BigDecimal notTercerBloque;

	@Column(name="num_quimestre")
	private String numQuimestre;

	//bi-directional many-to-one association to NotMateria
	@OneToMany(mappedBy="notasTbl")
	private List<NotMateria> notMateriaTbls;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="est_fk")
	private Estudiante estudianteTbl;

	public Notas() {
	}

	public Integer getNotPk() {
		return this.notPk;
	}

	public void setNotPk(Integer notPk) {
		this.notPk = notPk;
	}

	public String getNotAsistenciaFinal() {
		return this.notAsistenciaFinal;
	}

	public void setNotAsistenciaFinal(String notAsistenciaFinal) {
		this.notAsistenciaFinal = notAsistenciaFinal;
	}

	public String getNotAsistenciaPrimerBloque() {
		return this.notAsistenciaPrimerBloque;
	}

	public void setNotAsistenciaPrimerBloque(String notAsistenciaPrimerBloque) {
		this.notAsistenciaPrimerBloque = notAsistenciaPrimerBloque;
	}

	public String getNotAsistenciaSegundoBloque() {
		return this.notAsistenciaSegundoBloque;
	}

	public void setNotAsistenciaSegundoBloque(String notAsistenciaSegundoBloque) {
		this.notAsistenciaSegundoBloque = notAsistenciaSegundoBloque;
	}

	public String getNotAsistenciaTercerBloque() {
		return this.notAsistenciaTercerBloque;
	}

	public void setNotAsistenciaTercerBloque(String notAsistenciaTercerBloque) {
		this.notAsistenciaTercerBloque = notAsistenciaTercerBloque;
	}

	public String getNotEstadoFinalQuimestre() {
		return this.notEstadoFinalQuimestre;
	}

	public void setNotEstadoFinalQuimestre(String notEstadoFinalQuimestre) {
		this.notEstadoFinalQuimestre = notEstadoFinalQuimestre;
	}

	public BigDecimal getNotExamenFinal() {
		return this.notExamenFinal;
	}

	public void setNotExamenFinal(BigDecimal notExamenFinal) {
		this.notExamenFinal = notExamenFinal;
	}

	public BigDecimal getNotNotaFinalAnio() {
		return this.notNotaFinalAnio;
	}

	public void setNotNotaFinalAnio(BigDecimal notNotaFinalAnio) {
		this.notNotaFinalAnio = notNotaFinalAnio;
	}

	public BigDecimal getNotNotaFinalQuimestre() {
		return this.notNotaFinalQuimestre;
	}

	public void setNotNotaFinalQuimestre(BigDecimal notNotaFinalQuimestre) {
		this.notNotaFinalQuimestre = notNotaFinalQuimestre;
	}

	public BigDecimal getNotPrimerBloque() {
		return this.notPrimerBloque;
	}

	public void setNotPrimerBloque(BigDecimal notPrimerBloque) {
		this.notPrimerBloque = notPrimerBloque;
	}

	public BigDecimal getNotSegundoBloque() {
		return this.notSegundoBloque;
	}

	public void setNotSegundoBloque(BigDecimal notSegundoBloque) {
		this.notSegundoBloque = notSegundoBloque;
	}

	public BigDecimal getNotTercerBloque() {
		return this.notTercerBloque;
	}

	public void setNotTercerBloque(BigDecimal notTercerBloque) {
		this.notTercerBloque = notTercerBloque;
	}

	public String getNumQuimestre() {
		return this.numQuimestre;
	}

	public void setNumQuimestre(String numQuimestre) {
		this.numQuimestre = numQuimestre;
	}

	public List<NotMateria> getNotMateriaTbls() {
		return this.notMateriaTbls;
	}

	public void setNotMateriaTbls(List<NotMateria> notMateriaTbls) {
		this.notMateriaTbls = notMateriaTbls;
	}

	public NotMateria addNotMateriaTbl(NotMateria notMateriaTbl) {
		getNotMateriaTbls().add(notMateriaTbl);
		notMateriaTbl.setNotasTbl(this);

		return notMateriaTbl;
	}

	public NotMateria removeNotMateriaTbl(NotMateria notMateriaTbl) {
		getNotMateriaTbls().remove(notMateriaTbl);
		notMateriaTbl.setNotasTbl(null);

		return notMateriaTbl;
	}

	public Estudiante getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(Estudiante estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}

}