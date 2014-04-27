/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio.impl;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INACTIVO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoBien;
import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;
import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaDTO;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;

/**
 * @author 
 *
 */
@Stateless
public class ServicioInventarioImpl implements ServicioInventario {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioInventarioImpl.class);
	
	@EJB
	private InventarioFactory inventarioFactory;
	
	/*
	 * Servicio para administracion del catalogo usado en el modulo de inventario
	 */
	
	@Override
	public List<DetalleBien> buscarDetalleBienCriterios (DetalleBien detalleBien) throws SeguridadesException {
		slf4jLogger.info("buscarDetalleBienCriterios");
		
		List<DetalleBien> listaDetalleBien = null;
		
		try {
			listaDetalleBien = inventarioFactory.getDetalleBienDAOImpl().obtenerDetalleBienCriterios(detalleBien);
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarDetalleBienCriterios {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return listaDetalleBien;
	}
	
	@Override
	public List<CabeceraBien> buscarCabeceraBien () throws SeguridadesException {
		try {
			return inventarioFactory.getCabeceraBienDAOImpl().buscarTodos();
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
	}
	
	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public DetalleBien actualizarDetalleBien(DetalleBien detalleBien) throws SeguridadesException {
		
		DetalleBien detalleBienUpdate = null;
		
		try {
			detalleBienUpdate =  inventarioFactory.getDetalleBienDAOImpl().update(detalleBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(detalleBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarDetalleBien", EnumTipoTransaccion.UPDATE));
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		return detalleBienUpdate;
	}
	
	/*
	 * Servicio para administracion de proveedores
	 */
	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public Proveedor registrarProveedor(Proveedor proveedor) throws SeguridadesException {
		
		slf4jLogger.info("registrarProveedor");
		
		Proveedor proveedorNuevo = null;
		
		try {
			
			proveedor.setProvEstado(ESTADO_ACTIVO);
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

	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public Proveedor actualizarProveedor(Proveedor proveedor) throws SeguridadesException {
		
		slf4jLogger.info("actualizarProveedor");
		
		Proveedor proveedorActualizado = null;
		
		try {
			
			proveedorActualizado = inventarioFactory.getProveedorDAOImpl().update(proveedor);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(proveedor.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarProveedor", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al actualizarProveedor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return proveedorActualizado;
	}

	@Override
	public List<Proveedor> buscarProveedorCriterios(Proveedor proveedor) throws SeguridadesException {
		slf4jLogger.info("buscarProveedorCriterios");
		
		List<Proveedor> listaProveedor = null;
		
		try {
			listaProveedor = inventarioFactory.getProveedorDAOImpl().buscarProveedorCriterios(proveedor);
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarProveedorCriterios {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return listaProveedor;
	}

	/*
	 * Servicio para administracion de linea bien
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public LineaBien registrarLineaBien(LineaBien lineaBien) throws SeguridadesException {
		LineaBien lineaBienNuevo = null;
		try {
			lineaBien.setLinBienEstado(ESTADO_ACTIVO);
			lineaBienNuevo = inventarioFactory.getLineaBienDAOImpl().create(lineaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(lineaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "registrarLineaBien", EnumTipoTransaccion.CREATE));
		} catch (Exception e) {
			throw new SeguridadesException();
		}
		return lineaBienNuevo;
	}

	@Override
	public LineaBien actualizarLineaBien(LineaBien lineaBien) throws SeguridadesException {
		LineaBien lineaBienUpdate = null;
		try {
			lineaBienUpdate = inventarioFactory.getLineaBienDAOImpl().update(lineaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(lineaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarLineaBien", EnumTipoTransaccion.UPDATE));
		} catch (Exception e) {
			throw new SeguridadesException();
		}
		return lineaBienUpdate;
	}

	@Override
	public List<LineaBien> buscarLineaBienCriterios(LineaBien lineaBien) throws SeguridadesException {
		
		List<LineaBien> listLineaBien = null;
		
		try {
			listLineaBien = inventarioFactory.getLineaBienDAOImpl().obtenerLineaBienCriterios(lineaBien);
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return listLineaBien;
	}

	/*
	 * Servicio para administracion de categoria bien
	 */
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CategoriaBien registrarCategoriaBien(CategoriaBien categoriaBien) throws SeguridadesException {
		CategoriaBien categoriaBienNuevo = null;
		try {
			categoriaBien.setCatBienEstado(ESTADO_ACTIVO);
			categoriaBienNuevo = inventarioFactory.getCategoriaBienDAOImpl().create(categoriaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(categoriaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "registrarCategoriaBien", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al registrarCategoriaBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		return categoriaBienNuevo;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CategoriaBien actualizarCategoriaBien(CategoriaBien categoriaBien) throws SeguridadesException {
		CategoriaBien categoriaBienUpdate = null;
		try {
			categoriaBienUpdate = inventarioFactory.getCategoriaBienDAOImpl().update(categoriaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(categoriaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarCategoriaBien", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al actualizarCategoriaBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		return categoriaBienUpdate;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CategoriaBien> buscarCategoriaBienCriterios(CategoriaBien categoriaBien) throws SeguridadesException {
		List<CategoriaBien> listCategoriaBien = null;
		
		try {
			listCategoriaBien = inventarioFactory.getCategoriaBienDAOImpl().obtenerCategoriaBienCriterios(categoriaBien);
		} catch (Exception e) {
			slf4jLogger.info("error al buscarCategoriaBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listCategoriaBien;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public MarcaBien registrarMarcaBien(MarcaBien marcaBien) throws SeguridadesException {
		MarcaBien categoriaBienNuevo = null;
		try {
			marcaBien.setMarBienEstado(ESTADO_ACTIVO);
			categoriaBienNuevo = inventarioFactory.getMarcaBienDAOImpl().create(marcaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(marcaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "registrarMarcaBien", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al registrarCategoriaBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		return categoriaBienNuevo;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public MarcaBien actualizarMarcaBien(MarcaBien marcaBien) throws SeguridadesException {
		MarcaBien marcaBienUpdate = null;
		try {
			marcaBienUpdate = inventarioFactory.getMarcaBienDAOImpl().update(marcaBien);
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(marcaBien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarMarcaBien", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("error al actualizarMarcaBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		return marcaBienUpdate;
	}

	@Override
	public List<MarcaBien> buscarMarcaBienCriterios(MarcaBien marcaBien) throws SeguridadesException {
		
		List<MarcaBien> listMarcaBien = null;
		
		try {
			listMarcaBien = inventarioFactory.getMarcaBienDAOImpl().buscarMarcaBienCriterios(marcaBien);
		} catch (Exception e) {
			slf4jLogger.info("error al buscarMarcaBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listMarcaBien;
	}
	
	/*
	 * Servicio para administracion de bien
	 */
	
	@Override
	public List<EmpleadoListDTO> obtenerEmpleadosEmpresa(EmpleadoListDTO vistaEmpleado) throws SeguridadesException {
		List<EmpleadoListDTO> listVistaEmpleado = null;
		
		try {
			
			if (vistaEmpleado.getEmpPk() == null) {
				throw new SeguridadesException("El codigo de la empresa es necesario para obtener los empleados");
			} else {
				listVistaEmpleado = inventarioFactory.getEmpleadoDAOImpl().findAll(vistaEmpleado);
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarMarcaBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listVistaEmpleado;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public VistaBien registrarBien(Bien bien) throws SeguridadesException {
		
		try {
			
			bien.setBieEstado(ESTADO_ACTIVO);
			Bien bienNuevo = inventarioFactory.getBienDAOImpl().create(bien);
			
			if (bienNuevo != null) {
				Transaccion transaccion = new Transaccion();
				transaccion.setCabCatalogoTipoBien(ConstantesApplication.CAB_CAT_TIPO_BIEN);
				transaccion.setDetCatalogoTipoBien(EnumTipoBien.INGRESADO.getId());
				transaccion.setCabEstadoConservacion(ConstantesApplication.CAB_CAT_ESTADO_CONSERVACION);
				transaccion.setDetEstadoConservacion(bien.getNpIdDcEstadoConservacion());
				transaccion.setBienTbl(bienNuevo);
				transaccion.setFechaInicio(UtilAplication.obtenerFechaActual());
				transaccion.setTraEstado(ESTADO_ACTIVO);
				inventarioFactory.getTransaccionDAOImpl().create(transaccion);
			}
			
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(bien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "registrarBien", EnumTipoTransaccion.CREATE));
			
			VistaBien vistaBienBuscar = new VistaBien();
			vistaBienBuscar.setBiePk(bienNuevo.getBiePk());
			vistaBienBuscar.setEmrPk(bienNuevo.getEmrPk());
			
			List<VistaBien> listVistaBien = inventarioFactory.getVistaBienDAOImpl().obtenerVistaBienCriterios(vistaBienBuscar);
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				
				return listVistaBien.iterator().next();
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al registrarBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public VistaBien actualizarBien(Bien bien) throws SeguridadesException {
		
		try {
			
			Bien bienUpdate = inventarioFactory.getBienDAOImpl().update(bien);
			
			if (StringUtils.isNotBlank(bien.getNpIdDcEstadoConservacion())) {
				//Se busca el ultimo estado activo de la transaccion para actualizar el estado de conservacion
				Transaccion transaccion = new Transaccion();
				transaccion.setBieFk(bienUpdate.getBiePk());
				transaccion.setTraEstado(ESTADO_ACTIVO);
				List<Transaccion> listTransaccion = inventarioFactory.getTransaccionDAOImpl().obtenerTransaccionCriterios(transaccion);
				if (CollectionUtils.isNotEmpty(listTransaccion) && listTransaccion.size()==1) {
					Transaccion transaccionActual = listTransaccion.iterator().next();
					if (!transaccionActual.getDetEstadoConservacion().equals(bien.getNpIdDcEstadoConservacion())) {
						transaccionActual.setDetEstadoConservacion((bien.getNpIdDcEstadoConservacion()));
						inventarioFactory.getTransaccionDAOImpl().update(transaccionActual);
					}
				}
			}
			
			inventarioFactory.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(
							new AuditoriaDTO(bien.getUsuarioRegistro()
									.getIdUsuario(), ServicioInventarioImpl.class.getName(), "actualizarBien", EnumTipoTransaccion.UPDATE));
			
			VistaBien vistaBienBuscar = new VistaBien();
			vistaBienBuscar.setBiePk(bienUpdate.getBiePk());
			vistaBienBuscar.setEmrPk(bienUpdate.getEmrPk());
			
			List<VistaBien> listVistaBien = inventarioFactory.getVistaBienDAOImpl().obtenerVistaBienCriterios(vistaBienBuscar);
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				
				return listVistaBien.iterator().next();
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al actualizarBien {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return null;
	}

	@Override
	public List<Bien> buscarBienCriterios(Bien bien) throws SeguridadesException {
		List<Bien> listBien = null;
		
		try {
			
			listBien = inventarioFactory.getBienDAOImpl().buscarBienCriterios(bien);
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listBien;
	}

	@Override
	public List<VistaBien> buscarVistaBienCriterios(VistaBien vistaBien) throws SeguridadesException {
		List<VistaBien> listVistaBien = null;
		
		try {
			listVistaBien = inventarioFactory.getVistaBienDAOImpl().obtenerVistaBienCriterios(vistaBien);
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				for (VistaBien vista : listVistaBien) {
					if (vista.getDetBienTipBieNivel1().equals(EnumTipoBien.INGRESADO.getId())) {
						vista.setNpVerAsignarBien(Boolean.TRUE);
					}
					if (vista.getDetBienTipBieNivel1().equals(EnumTipoBien.ASIGNADO.getId())) {
						vista.setNpVerTrasladoBien(Boolean.TRUE);
					}
				}
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarVistaBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listVistaBien;
	}

	@Override
	public VistaBien asignarBien(VistaBien vistaBien) throws SeguridadesException {
		
		//obtener el estado actual de la tabla transaccion
		Transaccion transaccionBuscar = new Transaccion();
		transaccionBuscar.setBieFk(vistaBien.getBiePk());
		transaccionBuscar.setTraEstado(ESTADO_ACTIVO);
		List<Transaccion> listTransaccion = inventarioFactory.getTransaccionDAOImpl().obtenerTransaccionCriterios(transaccionBuscar);
		
		if (CollectionUtils.isNotEmpty(listTransaccion) && listTransaccion.size() == 1) {
			
			Transaccion transaccionActual = listTransaccion.iterator().next();
			
			// el estado del bien debe ser ingresado
			if (transaccionActual != null && transaccionActual.getDetCatalogoTipoBien().equals(EnumTipoBien.INGRESADO.getId())) {
				
				//inactivar el estado actual antes de crear el nuevo
				transaccionActual.setTraEstado(ESTADO_INACTIVO);
				transaccionActual.setFechaFin(UtilAplication.obtenerFechaActual());
				inventarioFactory.getTransaccionDAOImpl().update(transaccionActual);
				
				// crear el nuevo estado en la tabla transaccion
				Transaccion transaccionNuevo = new Transaccion();
				transaccionNuevo.setCabCatalogoTipoBien(ConstantesApplication.CAB_CAT_TIPO_BIEN);
				transaccionNuevo.setDetCatalogoTipoBien(EnumTipoBien.ASIGNADO.getId());
				transaccionNuevo.setCabEstadoConservacion(transaccionActual.getCabEstadoConservacion());
				transaccionNuevo.setDetEstadoConservacion(transaccionActual.getDetEstadoConservacion());
				transaccionNuevo.setBieFk(vistaBien.getBiePk());
				transaccionNuevo.setFechaInicio(UtilAplication.obtenerFechaActual());
				transaccionNuevo.setTraEstado(ESTADO_ACTIVO);
				inventarioFactory.getTransaccionDAOImpl().create(transaccionNuevo);
				
			}
		}
		
		return null;
	}

	

}
