package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_profesor database table.
 * 
 */
@Entity
@Table(name="mat_profesor")
@NamedQuery(name="ProfesorDTO.findAll", query="SELECT p FROM ProfesorDTO p")
public class ProfesorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_PROFESOR_PROCODIGO_GENERATOR", sequenceName="MAT_PROFESOR_PRO_CODIGO_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_PROFESOR_PROCODIGO_GENERATOR")
	@Column(name="pro_codigo")
	private Integer proCodigo;

	@Column(name="pro_persona")
	private Integer proPersona;

	//bi-directional many-to-one association to AsinacionDTO
	@OneToMany(mappedBy="matProfesor")
	private List<AsinacionDTO> matAsinacions;

	public ProfesorDTO() {
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Integer getProPersona() {
		return this.proPersona;
	}

	public void setProPersona(Integer proPersona) {
		this.proPersona = proPersona;
	}

	public List<AsinacionDTO> getMatAsinacions() {
		return this.matAsinacions;
	}

	public void setMatAsinacions(List<AsinacionDTO> matAsinacions) {
		this.matAsinacions = matAsinacions;
	}

	public AsinacionDTO addMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().add(matAsinacion);
		matAsinacion.setMatProfesor(this);

		return matAsinacion;
	}

	public AsinacionDTO removeMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().remove(matAsinacion);
		matAsinacion.setMatProfesor(null);

		return matAsinacion;
	}

}