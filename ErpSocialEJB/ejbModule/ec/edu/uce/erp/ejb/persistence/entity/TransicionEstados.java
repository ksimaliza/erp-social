package ec.edu.uce.erp.ejb.persistence.entity;

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
 * The persistent class for the transicion_estados_tbl database table.
 * 
 */
@Entity
@Table(name="transicion_estados_tbl")
@NamedQuery(name="TransicionEstados.findAll", query="SELECT t FROM TransicionEstados t")
public class TransicionEstados implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANSICION_ESTADOS_TBL_TRAESTADOSPK_GENERATOR", sequenceName="TRANSICION_ESTADOS_TBL_TRA_ESTADOS_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSICION_ESTADOS_TBL_TRAESTADOSPK_GENERATOR")
	@Column(name="tra_estados_pk")
	private Integer traEstadosPk;

	@Column(name="tra_estados_anterior")
	private String traEstadosAnterior;

	@Column(name="tra_estados_nuevo")
	private String traEstadosNuevo;

	@Column(name="tra_estados_observacion")
	private String traEstadosObservacion;

	//bi-directional many-to-one association to EstadoDocumento
	@ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumento estadoDocumentoTbl;

	public TransicionEstados() {
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

	public EstadoDocumento getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumento estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}

}