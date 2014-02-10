package ec.edu.uce.erp.common.enums;

import ec.edu.uce.erp.common.util.MessagesApplicacion;

/**
 * Enum para identificar las operaciones CRUD del usuario
 * @author 
 *
 */
public enum EnumTipoTransaccion {
	
	CREATE(MessagesApplicacion.getString("erp.seguridades.detalle.catalogo.id.tipoTransaccion.create"), "CREATE"), 
	READ(MessagesApplicacion.getString("erp.seguridades.detalle.catalogo.id.tipoTransaccion.read"), "READ"), 
	UPDATE(MessagesApplicacion.getString("erp.seguridades.detalle.catalogo.id.tipoTransaccion.update"), "UPDATE"), 
	DELETE(MessagesApplicacion.getString("erp.seguridades.detalle.catalogo.id.tipoTransaccion.delete"), "DELETE"),
	LOGIN(MessagesApplicacion.getString("erp.seguridades.detalle.catalogo.id.tipoTransaccion.login"), "LOGIN");

	private final String id;
	private final String descripcion;
	
	EnumTipoTransaccion(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el identificador
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Obtiene la descripcion del catalogo
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
