/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;

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
	
	/*
	 * Servicio para administracion de linea bien
	 */
	
	/**
	 * Registrar <code>LineaBien</code> en la BD
	 * @param lineaBien
	 * @return
	 * @throws SeguridadesException
	 */
	LineaBien registrarLineaBien (LineaBien lineaBien) throws SeguridadesException;
	
	/**
	 * Actualizar <code>LineaBien</code> en la BD
	 * @param lineaBien
	 * @return
	 * @throws SeguridadesException
	 */
	LineaBien actualizarLineaBien (LineaBien lineaBien) throws SeguridadesException;
	
	/**
	 * Buscar <code>LineaBien</code> en la BD por diferentes criterios
	 * @param lineaBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<LineaBien> buscarLineaBienCriterios (LineaBien lineaBien) throws SeguridadesException;
	
	/*
	 * Servicio para administracion de categoria bien
	 */
	
	/**
	 *  Registrar <code>CategoriaBien</code> en la BD
	 * @param categoriaBien
	 * @return
	 * @throws SeguridadesException
	 */
	CategoriaBien registrarCategoriaBien (CategoriaBien categoriaBien) throws SeguridadesException;
	
	/**
	 * Actualizar <code>CategoriaBien</code> en la BD
	 * @param categoriaBien
	 * @return
	 * @throws SeguridadesException
	 */
	CategoriaBien actualizarCategoriaBien (CategoriaBien categoriaBien) throws SeguridadesException;
	
	/**
	 * Buscar <code>CategoriaBien</code> en la BD por diferentes criterios
	 * @param categoriaBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<CategoriaBien> buscarCategoriaBienCriterios (CategoriaBien categoriaBien) throws SeguridadesException;
	
	/*
	 * Servicio para administracion de marca bien
	 */
	
	/**
	 * Registrar <code>MarcaBien</code> en la BD
	 * @param marcaBien
	 * @return
	 * @throws SeguridadesException
	 */
	MarcaBien registrarMarcaBien (MarcaBien marcaBien) throws SeguridadesException;
	
	/**
	 * Actualizar <code>MarcaBien</code> en la BD
	 * @param marcaBien
	 * @return
	 * @throws SeguridadesException
	 */
	MarcaBien actualizarMarcaBien (MarcaBien marcaBien) throws SeguridadesException;
	
	/**
	 * Buscar <code>MarcaBien</code> en la BD por diferentes criterios
	 * @param marcaBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<MarcaBien> buscarMarcaBienCriterios (MarcaBien marcaBien) throws SeguridadesException;
	
	/*
	 * Servicio para administracion de bien
	 */
	
	/**
	 * Registrar <code>Bien</code> en la BD
	 * @param bien
	 * @return
	 * @throws SeguridadesException
	 */
	VistaBien registrarBien (Bien bien) throws SeguridadesException;
	
	/**
	 * Actualizar <code>Bien</code> en la BD
	 * @param bien
	 * @return
	 * @throws SeguridadesException
	 */
	Bien actualizarBien (Bien bien) throws SeguridadesException;
	
	/**
	 * Buscar <code>Bien</code> en la BD por diferentes criterios
	 * @param bien
	 * @return
	 * @throws SeguridadesException
	 */
	List<Bien> buscarBienCriterios (Bien bien) throws SeguridadesException;
	
	/**
	 * Buscar <code>VistaBien</code> en la BD por diferentes criterios
	 * @param vistaBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaBien> buscarVistaBienCriterios (VistaBien vistaBien) throws SeguridadesException;
}
