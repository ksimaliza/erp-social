package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mat_nivel_paralelo_vie database table.
 * 
 */
@Entity
@Table(name="mat_nivel_paralelo_vie")
@NamedQuery(name="NivelParaleloListDTO.findAll", query="SELECT m FROM NivelParaleloListDTO m")
public class NivelParaleloListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="niv_descaripcion")
	private String nivDescaripcion;
@Id
	@Column(name="npa_codigo")
	private Integer npaCodigo;

	@Column(name="npa_nivel")
	private Integer npaNivel;

	@Column(name="npa_paralelo")
	private Integer npaParalelo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	public NivelParaleloListDTO() {
	}

	public String getNivDescaripcion() {
		return this.nivDescaripcion;
	}

	public void setNivDescaripcion(String nivDescaripcion) {
		this.nivDescaripcion = nivDescaripcion;
	}

	public Integer getNpaCodigo() {
		return this.npaCodigo;
	}

	public void setNpaCodigo(Integer npaCodigo) {
		this.npaCodigo = npaCodigo;
	}

	public Integer getNpaNivel() {
		return this.npaNivel;
	}

	public void setNpaNivel(Integer npaNivel) {
		this.npaNivel = npaNivel;
	}

	public Integer getNpaParalelo() {
		return this.npaParalelo;
	}

	public void setNpaParalelo(Integer npaParalelo) {
		this.npaParalelo = npaParalelo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

}