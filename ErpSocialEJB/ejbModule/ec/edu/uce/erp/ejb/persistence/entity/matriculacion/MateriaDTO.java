package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_materia database table.
 * 
 */
@Entity
@Table(name="mat_materia")
@NamedQuery(name="MateriaDTO.findAll", query="SELECT m FROM MateriaDTO m")
public class MateriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_MATERIA_MTRCODIGO_GENERATOR", sequenceName="MAT_MATERIA_MTR_CODIGO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_MATERIA_MTRCODIGO_GENERATOR")
	@Column(name="mtr_codigo")
	private Integer mtrCodigo;

	@Column(name="mtr_nombe")
	private String mtrNombe;

	//bi-directional many-to-one association to AsinacionDTO
	@OneToMany(mappedBy="matMateria")
	private List<AsinacionDTO> matAsinacions;

	public MateriaDTO() {
	}

	public Integer getMtrCodigo() {
		return this.mtrCodigo;
	}

	public void setMtrCodigo(Integer mtrCodigo) {
		this.mtrCodigo = mtrCodigo;
	}

	public String getMtrNombe() {
		return this.mtrNombe;
	}

	public void setMtrNombe(String mtrNombe) {
		this.mtrNombe = mtrNombe;
	}

	public List<AsinacionDTO> getMatAsinacions() {
		return this.matAsinacions;
	}

	public void setMatAsinacions(List<AsinacionDTO> matAsinacions) {
		this.matAsinacions = matAsinacions;
	}

	public AsinacionDTO addMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().add(matAsinacion);
		matAsinacion.setMatMateria(this);

		return matAsinacion;
	}

	public AsinacionDTO removeMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().remove(matAsinacion);
		matAsinacion.setMatMateria(null);

		return matAsinacion;
	}

}