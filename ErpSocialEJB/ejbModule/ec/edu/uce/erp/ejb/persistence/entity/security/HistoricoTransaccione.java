package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;


/**
 * The persistent class for the segt_historico_transacciones database table.
 * 
 */
@Entity
@Table(name="segt_historico_transacciones")
@NamedQuery(name="HistoricoTransaccione.findAll", query="SELECT h FROM HistoricoTransaccione h")
public class HistoricoTransaccione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_HISTORICO_TRANSACCIONES_IDHISTORICOTRANSACCION_GENERATOR", sequenceName="SEGT_HISTORICO_TRANSACCIONES_ID_HISTORICO_TRANSACCION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_HISTORICO_TRANSACCIONES_IDHISTORICOTRANSACCION_GENERATOR")
	@Column(name="id_historico_transaccion")
	private Integer idHistoricoTransaccion;

	@Column(name="fecha_transaccion")
	private Timestamp fechaTransaccion;

	@Column(name="nombre_transaccion")
	private String nombreTransaccion;
	
	@Column(name="det_catalogo_tipo_transaccion")
	private String detCatalogoTipoTransaccion;
	
	@Column(name="cab_catalogo_tipo_transaccion")
	private String cabCatalogoTipoTransaccion;
	
	//bi-directional many-to-one association to DetalleCatalogo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cab_catalogo_tipo_transaccion", referencedColumnName="cab_catalogo_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_catalogo_tipo_transaccion", referencedColumnName="det_catalogo_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleCatalogo dcTipoTransaccion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario segtUsuario;

	public HistoricoTransaccione() {
	}

	public Integer getIdHistoricoTransaccion() {
		return this.idHistoricoTransaccion;
	}

	public void setIdHistoricoTransaccion(Integer idHistoricoTransaccion) {
		this.idHistoricoTransaccion = idHistoricoTransaccion;
	}

	public Timestamp getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Timestamp fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getNombreTransaccion() {
		return this.nombreTransaccion;
	}

	public void setNombreTransaccion(String nombreTransaccion) {
		this.nombreTransaccion = nombreTransaccion;
	}

	public Usuario getSegtUsuario() {
		return this.segtUsuario;
	}

	public void setSegtUsuario(Usuario segtUsuario) {
		this.segtUsuario = segtUsuario;
	}

	/**
	 * @return the dcTipoTransaccion
	 */
	public DetalleCatalogo getDcTipoTransaccion() {
		return dcTipoTransaccion;
	}

	/**
	 * @param dcTipoTransaccion the dcTipoTransaccion to set
	 */
	public void setDcTipoTransaccion(DetalleCatalogo dcTipoTransaccion) {
		this.dcTipoTransaccion = dcTipoTransaccion;
	}

	public String getDetCatalogoTipoTransaccion() {
		return detCatalogoTipoTransaccion;
	}

	public void setDetCatalogoTipoTransaccion(String detCatalogoTipoTransaccion) {
		this.detCatalogoTipoTransaccion = detCatalogoTipoTransaccion;
	}

	public String getCabCatalogoTipoTransaccion() {
		return cabCatalogoTipoTransaccion;
	}

	public void setCabCatalogoTipoTransaccion(String cabCatalogoTipoTransaccion) {
		this.cabCatalogoTipoTransaccion = cabCatalogoTipoTransaccion;
	}

}