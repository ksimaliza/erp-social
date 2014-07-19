/**
 * 
 */
package ec.edu.uce.erp.common.enums;

import ec.edu.uce.erp.common.util.MessagesApplicacion;

/**
 * @author wilmerPC
 *
 */
public enum EnumTipoBien {
	
	INGRESADO(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBien.ingresado")), 
	ASIGNADO(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBien.asignado")), 
	REASIGNADO(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBien.reasignado")), 
	DEVUELTO(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBien.devuelto")),
	BAJA(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBien.baja"));

	private final String id;
	
	EnumTipoBien(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el identificador
	 * @return
	 */
	public String getId() {
		return id;
	}

}
