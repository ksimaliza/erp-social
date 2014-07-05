/**
 * 
 */
package ec.edu.uce.erp.common.enums;

import ec.edu.uce.erp.common.util.MessagesApplicacion;

/**
 * @author wilmerPC
 *
 */
public enum EnumTipoBaja {
	
	PERDIDA(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBaja.perdida")), 
	ROBO(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBaja.robo")), 
	TOTAL(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBaja.total")), 
	DONACION(MessagesApplicacion.getString("erp.inventarios.detalle.catalogo.tipoBaja.donacion"));

	private final String id;
	
	EnumTipoBaja(String id) {
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
