package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the not_parcial database table.
 * 
 */
@Entity
@Table(name="not_tipo_nota")
@NamedQuery(name="TipoNotaDTO.findAll", query="SELECT p FROM TipoNotaDTO p")
public class TipoNotaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOT_TIPO_NOTA_PARCODIGO_GENERATOR", sequenceName="NOT_TIPO_NOTA_PAR_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOT_TIPO_NOTA_PARCODIGO_GENERATOR")
	@Column(name="par_codigo")
	private Integer parCodigo;

	@Column(name="par_nombre")
	private String parNombre;

	//bi-directional many-to-one association to NotaDTO
	@OneToMany(mappedBy="tipoNotaBean")
	private List<NotaDTO> notNotas;

	public TipoNotaDTO() {
	}

	public Integer getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(Integer parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public List<NotaDTO> getNotNotas() {
		return this.notNotas;
	}

	public void setNotNotas(List<NotaDTO> notNotas) {
		this.notNotas = notNotas;
	}

}