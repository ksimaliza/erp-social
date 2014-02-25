/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.ejb.persistence.entity.inventario.Proveedor;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;

/**
 * @author 
 *
 */
@Stateless
public class ServicioInventarioImpl implements ServicioInventario{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioInventarioImpl.class);
	
	@EJB
	private InventarioFactory inventarioFactory;
	
	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public Proveedor registrarProveedor(Proveedor proveedor) throws SeguridadesException {
		
		slf4jLogger.info("registrarProveedor");
		
		Proveedor proveedorNuevo = null;
		
		try {
			
			proveedorNuevo = inventarioFactory.getProveedorDAOImpl().create(proveedor);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(proveedor.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "registrarProveedor", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al registrarProveedor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return proveedorNuevo;
	}

}
