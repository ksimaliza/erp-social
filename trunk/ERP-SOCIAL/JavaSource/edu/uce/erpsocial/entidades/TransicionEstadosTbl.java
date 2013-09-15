package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transicion_estados_tbl database table.
 * 
 */
@Entity
@Table(name="transicion_estados_tbl")
public class TransicionEstadosTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tra_estados_pk")
	private Integer traEstadosPk;

	@Column(name="tra_estados_anterior")
	private String traEstadosAnterior;

	@Column(name="tra_estados_nuevo")
	private String traEstadosNuevo;

	@Column(name="tra_estados_observacion")
	private String traEstadosObservacion;

	//bi-directional many-to-one association to EstadoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumentoTbl estadoDocumentoTbl;

    public TransicionEstadosTbl() {
    }

	public Integer getTraEstadosPk() {
		return this.traEstadosPk;
	}

	public void setTraEstadosPk(Integer traEstadosPk) {
		this.traEstadosPk = traEstadosPk;
	}

	public String getTraEstadosAnterior() {
		return this.traEstadosAnterior;
	}

	public void setTraEstadosAnterior(String traEstadosAnterior) {
		this.traEstadosAnterior = traEstadosAnterior;
	}

	public String getTraEstadosNuevo() {
		return this.traEstadosNuevo;
	}

	public void setTraEstadosNuevo(String traEstadosNuevo) {
		this.traEstadosNuevo = traEstadosNuevo;
	}

	public String getTraEstadosObservacion() {
		return this.traEstadosObservacion;
	}

	public void setTraEstadosObservacion(String traEstadosObservacion) {
		this.traEstadosObservacion = traEstadosObservacion;
	}

	public EstadoDocumentoTbl getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumentoTbl estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}
	
}