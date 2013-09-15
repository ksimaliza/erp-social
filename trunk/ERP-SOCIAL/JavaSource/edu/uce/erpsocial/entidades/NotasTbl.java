package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the notas_tbl database table.
 * 
 */
@Entity
@Table(name="notas_tbl")
public class NotasTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="not_pk")
	private Integer notPk;

	@Column(name="not_estado_final_quimestre")
	private String notEstadoFinalQuimestre;

	@Column(name="not_examen_final")
	private BigDecimal notExamenFinal;

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

	//bi-directional many-to-one association to NotMateriaTbl
	@OneToMany(mappedBy="notasTbl")
	private Set<NotMateriaTbl> notMateriaTbls;

	//bi-directional many-to-one association to EstudianteTbl
    @ManyToOne
	@JoinColumn(name="est_fk")
	private EstudianteTbl estudianteTbl;

    public NotasTbl() {
    }

	public Integer getNotPk() {
		return this.notPk;
	}

	public void setNotPk(Integer notPk) {
		this.notPk = notPk;
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

	public Set<NotMateriaTbl> getNotMateriaTbls() {
		return this.notMateriaTbls;
	}

	public void setNotMateriaTbls(Set<NotMateriaTbl> notMateriaTbls) {
		this.notMateriaTbls = notMateriaTbls;
	}
	
	public EstudianteTbl getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(EstudianteTbl estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}
	
}