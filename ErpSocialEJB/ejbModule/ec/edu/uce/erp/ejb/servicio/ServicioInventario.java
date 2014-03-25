/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;

/**
 * @author
 *
 */
@Local
public interface ServicioInventario {
	
	/*
	 * Servicio para administracion del catalogo usado en el modulo de inventario
	 */
	
	/**
	 * Buscar <code>DetalleBien</code> en la BD por diferentes criterios
	 * @param detalleBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<DetalleBien> buscarDetalleBienCriterios (DetalleBien detalleBien) throws SeguridadesException;
	
	/**
	 * Cargar todos los catalogos de la tabla <code>CabeceraBien</code> del modulo de inventario
	 * @return
	 * @throws SeguridadesException
	 */
	List<CabeceraBien> buscarCabeceraBien () throws SeguridadesException;
	
	/**
	 * Actualizar <code>DetalleBien</code> en la BD
	 * @param detalleBien
	 * @return
	 * @throws SeguridadesException
	 */
	DetalleBien actualizarDetalleBien (DetalleBien detalleBien) throws SeguridadesException;
	
	/*
	 * Servicio para administracion de proveedores
	 */
	
	/**
	 * Registrar un <code>Proveedor</code> en la BD
	 * @param proveedor
	 * @return
	 * @throws SeguridadesException
	 */
	Proveedor registrarProveedor (Proveedor proveedor) throws SeguridadesException;
	
	/**
	 * Actualizar un <code>Proveedor</code> en la BD
	 * @param proveedor
	 * @return
	 * @throws SeguridadesException
	 */
	Proveedor actualizarProveedor (Proveedor proveedor) throws SeguridadesException;
	
	/**
	 * Buscar un <code>Proveedor</code> en la BD por diferentes criterios
	 * @param proveedor
	 * @return
	 * @throws SeguridadesException
	 */
	List<Proveedor> buscarProveedorCriterios (Proveedor proveedor) throws SeguridadesException;
	
}
