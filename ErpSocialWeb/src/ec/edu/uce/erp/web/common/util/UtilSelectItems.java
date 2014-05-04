/**
 * 
 */
package ec.edu.uce.erp.web.common.util;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilReflection;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogoPK;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBienPK;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;

/**
 * @author
 *
 */
public final class UtilSelectItems {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UtilSelectItems.class);
	
	private static final UtilSelectItems INSTANCIA = new UtilSelectItems();
	
	/**
	 * Constructor privado
	 */
	private UtilSelectItems() {}
	
	/**
	 * Devolvemos la instancia de la clase
	 * @return INSTANCIA
	 */
	public static UtilSelectItems getInstancia(){
		return INSTANCIA;
	}
	
	public <T> List<SelectItem> cargarSelectItemsGenerico (List<T> listData, final String idPrimaryKey, final String name) {
		
		slf4jLogger.info("cargarSelectItemsGenerico");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		CollectionUtils.collect(listData, new Transformer() {
			@Override
			public Object transform(final Object arg0) {
				return new SelectItem(UtilReflection.invocarGet(arg0, idPrimaryKey), UtilReflection.invocarGet(arg0, name));
			}
		}, listSelectItem);
		
		return listSelectItem;
	}
	
	/**
	 * Cargar <code>SelectItem</code> desde la tabla <code>DetalleCatalogo</code>
	 * @param idCabCatalogo
	 * @param servicioAdministracion
	 * @return
	 * @throws SeguridadesException
	 */
	public List<SelectItem> cargarSelectItemsDetCatalogo (String idCabCatalogo, ServicioAdministracion servicioAdministracion) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemsDetCatalogo");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		DetalleCatalogo detalleCatalogo = new DetalleCatalogo();
		detalleCatalogo.setId(new DetalleCatalogoPK());
		detalleCatalogo.getId().setCabCatalogoFk(idCabCatalogo);
		detalleCatalogo.setDetCatalogoEstado(ESTADO_ACTIVO);
		List<DetalleCatalogo> listDetalleCatalogo = servicioAdministracion.buscarDetalleCatalogoCriterios(detalleCatalogo);
		
		if (CollectionUtils.isNotEmpty(listDetalleCatalogo)) {
//			CollectionUtils.collect(listDetalleCatalogo, new Transformer() {
//				@Override
//				public Object transform(final Object arg0) {
//					final DetalleCatalogo detalleCatalogo = (DetalleCatalogo)arg0;
//					return new SelectItem(detalleCatalogo.getId().getDetCatalogoNivel1(), detalleCatalogo.getDetCatalogoDescripcion());
//				}
//			}, listSelectItem);
			
			listSelectItem = this.cargarSelectItemsGenerico(listDetalleCatalogo, "id.detCatalogoNivel1", "detCatalogoDescripcion");
		}
		
		return listSelectItem;
		
	}
	
	/**
	 * Cargar <code>SelectItem</code> desde la tabla <code>DetalleBien</code>
	 * @param idCabCatalogo
	 * @param servicioInventario
	 * @return
	 * @throws SeguridadesException
	 */
	public List<SelectItem> cargarSelectItemsDetBien (String idCabCatalogo, ServicioInventario servicioInventario) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemsDetBien");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		DetalleBien detalleBien = new DetalleBien();
		detalleBien.setId(new DetalleBienPK());
		detalleBien.getId().setCabBienFk(idCabCatalogo);
		detalleBien.setDetBienEstado(ESTADO_ACTIVO);
		List<DetalleBien> listDetalleBien = servicioInventario.buscarDetalleBienCriterios(detalleBien);
		
		if (CollectionUtils.isNotEmpty(listDetalleBien)) {
			CollectionUtils.collect(listDetalleBien, new Transformer() {
				@Override
				public Object transform(final Object arg0) {
					final DetalleBien detalleBien = (DetalleBien)arg0;
					return new SelectItem(detalleBien.getId().getDetBienNivel1(), detalleBien.getDetBienDescripcion());
				}
			}, listSelectItem);
		}
		
		return listSelectItem;
		
	}
	
	public List<SelectItem> cargarSelectItemProveedor (ServicioInventario servicioInventario) throws SeguridadesException {
		slf4jLogger.info("cargarSelectItemProveedor");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		Proveedor proveedor = new Proveedor();
		proveedor.setProvEstado(ESTADO_ACTIVO);
		
		List<Proveedor> listProveedor = new ArrayList<Proveedor>();
		listProveedor = servicioInventario.buscarProveedorCriterios(proveedor);
		
		if (CollectionUtils.isNotEmpty(listProveedor)) {
			CollectionUtils.collect(listProveedor, new Transformer() {
				@Override
				public Object transform(final Object arg0) {
					final Proveedor proveedor = (Proveedor)arg0;
					return new SelectItem(proveedor.getProvPk(), proveedor.getNpNombresCompletos());
				}
			}, listSelectItem);
		}
		
		return listSelectItem;
	}
	
	public List<SelectItem> cargarSelectItemCabeceraBien (ServicioInventario servicioInventario) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemInventarios");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		List<CabeceraBien> listCabeceraBien = servicioInventario.buscarCabeceraBien();
		
		if (CollectionUtils.isNotEmpty(listCabeceraBien)) {
			CollectionUtils.collect(listCabeceraBien, new Transformer() {
				@Override
				public Object transform(final Object arg0) {
					final CabeceraBien cabeceraBien = (CabeceraBien)arg0;
					return new SelectItem(cabeceraBien.getCabBienPk(), cabeceraBien.getCabBienDescripcion());
				}
			}, listSelectItem);
		}
		
		return listSelectItem;
	}
	
	public List<SelectItem> cargarSelectItemLineaBien (ServicioInventario servicioInventario) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemLineaBien");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		LineaBien lineaBien = new LineaBien();
		lineaBien.setLinBienEstado(ESTADO_ACTIVO);
		List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(lineaBien);
		
		if (CollectionUtils.isNotEmpty(listLineaBien)) {
			CollectionUtils.collect(listLineaBien, new Transformer() {
				@Override
				public Object transform(final Object arg0) {
					final LineaBien lineaBien = (LineaBien)arg0;
					return new SelectItem(lineaBien.getLinBienPk(), lineaBien.getLinBienNombre());
				}
			}, listSelectItem);
		}
		
		return listSelectItem;
	}
	
	public List<SelectItem> cargarSelectItemCategoriaBien (ServicioInventario servicioInventario) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemCategoriaBien");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		CategoriaBien categoriaBien = new CategoriaBien();
		categoriaBien.setCatBienEstado(ESTADO_ACTIVO);
		List<CategoriaBien> listCategoriaBien = servicioInventario.buscarCategoriaBienCriterios(categoriaBien);
		
		if (CollectionUtils.isNotEmpty(listCategoriaBien)) {
			listSelectItem = this.cargarSelectItemsGenerico(listCategoriaBien, "catBienPk", "catBienNombre");
		}
		
		return listSelectItem;
	}
	
	public List<SelectItem> cargarSelectItemMarcaBien (ServicioInventario servicioInventario) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemMarcaBien");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		MarcaBien marcaBien = new MarcaBien();
		marcaBien.setMarBienEstado(ESTADO_ACTIVO);
		List<MarcaBien> listMarcaBien = servicioInventario.buscarMarcaBienCriterios(marcaBien);
		
		if (CollectionUtils.isNotEmpty(listMarcaBien)) {
			listSelectItem = this.cargarSelectItemsGenerico(listMarcaBien, "marBienPk", "marBienNombre");
		}
		
		return listSelectItem;
	}
	
	public List<SelectItem> cargarSelectItemEmpleados (ServicioInventario servicioInventario, Integer emrPk) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItemEmpleados");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		EmpleadoListDTO empleadoListDTO = new EmpleadoListDTO();
		empleadoListDTO.setEmrPk(emrPk);
		
		List<EmpleadoListDTO> listEmpleadoList = servicioInventario.obtenerEmpleadoEmpresa(empleadoListDTO);
		if (CollectionUtils.isNotEmpty(listEmpleadoList)) {
			listSelectItem = this.cargarSelectItemsGenerico(listEmpleadoList, "empPk", "nombresCompletosEmpleado");
		}
		
		return listSelectItem;
	}
	
}
