package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the not_nota database table.
 * 
 */
@Entity
@Table(name = "not_nota")
@NamedQuery(name = "NotaDTO.findAll", query = "SELECT n FROM NotaDTO n")
public class NotaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NOT_NOTA_NOTCODIGO_GENERATOR", sequenceName = "NOT_NOTA_NOT_CODIGO_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOT_NOTA_NOTCODIGO_GENERATOR")
	@Column(name = "not_codigo")
	private Integer notCodigo;

	@Column(name = "not_observacion")
	private String notObservacion;

	@Column(name = "not_valor")
	private Float notValor;

	// bi-directional many-to-one association to ParcialDTO
	@ManyToOne
	@JoinColumn(name = "not_tipo_nota")
	private TipoNotaDTO tipoNotaBean;

	@ManyToOne
	@JoinColumn(name = "not_matricula")
	private MatriculaDetalleDTO matMatriculaDetalleBean;

	// para relacion recursiva de NotaDTO

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "notaPadreBean", cascade = CascadeType.ALL)
	@OrderBy("tipoNotaBean ASC")
	private List<NotaDTO> notasDTOComponentes = new ArrayList<NotaDTO>();

	@ManyToOne
	@JoinColumn(name = "not_codigo_nota_padre", updatable = true, insertable = true)
	private NotaDTO notaPadreBean;

	//

	public NotaDTO() {
	}

	public Integer getNotCodigo() {
		return this.notCodigo;
	}

	public void setNotCodigo(Integer notCodigo) {
		this.notCodigo = notCodigo;
	}

	public String getNotObservacion() {
		return this.notObservacion;
	}

	public void setNotObservacion(String notObservacion) {
		this.notObservacion = notObservacion;
	}

	public Float getNotValor() {
		return this.notValor;
	}

	public void setNotValor(Float notValor) {
		this.notValor = notValor;
	}

	public TipoNotaDTO getTipoNotaBean() {
		return tipoNotaBean;
	}

	public void setTipoNotaBean(TipoNotaDTO tipoNotaBean) {
		this.tipoNotaBean = tipoNotaBean;
	}

	public MatriculaDetalleDTO getMatMatriculaDetalleBean() {
		return matMatriculaDetalleBean;
	}

	public void setMatMatriculaDetalleBean(MatriculaDetalleDTO matMatriculaDetalleBean) {
		this.matMatriculaDetalleBean = matMatriculaDetalleBean;
	}

	public List<NotaDTO> getNotasDTOComponentes() {
		return notasDTOComponentes;
	}

	public void setNotasDTOComponentes(List<NotaDTO> notasDTOComponentes) {
		this.notasDTOComponentes = notasDTOComponentes;
	}

	public NotaDTO getNotaPadreBean() {
		return notaPadreBean;
	}

	public void setNotaPadreBean(NotaDTO notaPadreBean) {
		this.notaPadreBean = notaPadreBean;
	}

	public NotaDTO addNotaDTO(NotaDTO notaDTO) {
		getNotasDTOComponentes().add(notaDTO);
		notaDTO.setNotaPadreBean(this);

		return notaDTO;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNotValor().toString();
	}

}