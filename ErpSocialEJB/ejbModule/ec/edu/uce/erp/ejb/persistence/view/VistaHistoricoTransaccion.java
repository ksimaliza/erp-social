package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the segv_historico_transaccion database table.
 * 
 */
@Entity
@Table(name="segv_historico_transaccion")
@NamedQuery(name="VistaHistoricoTransaccion.findAll", query="SELECT v FROM VistaHistoricoTransaccion v")
public class VistaHistoricoTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_historico_transaccion")
	private Integer idHistoricoTransaccion;

	@Column(name="det_catalogo_descripcion")
	private String detCatalogoDescripcion;

	@Column(name="det_catalogo_tipo_transaccion")
	private String detCatalogoTipoTransaccion;
	
	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="fecha_transaccion")
	private Timestamp fechaTransaccion;

	@Column(name="id_usuario")
	private Integer idUsuario;

	@Column(name="nombre_transaccion")
	private String nombreTransaccion;
	
	@Column(name="ip_transaccion")
	private String ipTransaccion;

	private String usuario;

	public VistaHistoricoTransaccion() {
	}

	public String getDetCatalogoDescripcion() {
		return this.detCatalogoDescripcion;
	}

	public void setDetCatalogoDescripcion(String detCatalogoDescripcion) {
		this.detCatalogoDescripcion = detCatalogoDescripcion;
	}

	public String getDetCatalogoTipoTransaccion() {
		return this.detCatalogoTipoTransaccion;
	}

	public void setDetCatalogoTipoTransaccion(String detCatalogoTipoTransaccion) {
		this.detCatalogoTipoTransaccion = detCatalogoTipoTransaccion;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
	}

	public Timestamp getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Timestamp fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Integer getIdHistoricoTransaccion() {
		return this.idHistoricoTransaccion;
	}

	public void setIdHistoricoTransaccion(Integer idHistoricoTransaccion) {
		this.idHistoricoTransaccion = idHistoricoTransaccion;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreTransaccion() {
		return this.nombreTransaccion;
	}

	public void setNombreTransaccion(String nombreTransaccion) {
		this.nombreTransaccion = nombreTransaccion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the ipTransaccion
	 */
	public String getIpTransaccion() {
		return ipTransaccion;
	}

	/**
	 * @param ipTransaccion the ipTransaccion to set
	 */
	public void setIpTransaccion(String ipTransaccion) {
		this.ipTransaccion = ipTransaccion;
	}

	/**
	 * @return the emrPk
	 */
	public Integer getEmrPk() {
		return emrPk;
	}

	/**
	 * @param emrPk the emrPk to set
	 */
	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

}