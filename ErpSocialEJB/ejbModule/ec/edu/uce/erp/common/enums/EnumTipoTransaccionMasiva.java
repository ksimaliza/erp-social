/**
 * 
 */
package ec.edu.uce.erp.common.enums;

import ec.edu.uce.erp.common.util.MessagesApplicacion;

/**
 * @author 
 *
 */
public enum EnumTipoTransaccionMasiva {
	
	TRASLADO(MessagesApplicacion.getString("erp.inventarios.transaccion.masiva.traslado.id"),
			MessagesApplicacion.getString("erp.inventarios.transaccion.masiva.traslado.label")),
	ASIGNAR(MessagesApplicacion.getString("erp.inventarios.transaccion.masiva.asignar.id"),
			MessagesApplicacion.getString("erp.inventarios.transaccion.masiva.asignar.label"));
	
	private final String id;
	private final String label;
	
	EnumTipoTransaccionMasiva(String id, String label) {
		this.id = id;
		this.label = label;
	}

	/**
	 * Obtiene el identificador
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

}
