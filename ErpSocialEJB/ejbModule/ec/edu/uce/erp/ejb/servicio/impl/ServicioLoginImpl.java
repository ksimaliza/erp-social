/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;
import ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes;
import ec.edu.uce.erp.common.util.EncriptacionUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistorialClave;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistoricoTransaccione;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;
import ec.edu.uce.erp.ejb.persistence.vo.LoginVO;
import ec.edu.uce.erp.ejb.servicio.ServicioLogin;

/**
 * @author 
 *
 */
@Stateless
public class ServicioLoginImpl implements ServicioLogin{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioLoginImpl.class);
	
	@EJB
	private FactoryDAO factoryDAO;

	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public LoginVO autenticarUsuario(LoginVO loginVO) throws SeguridadesException {
		
		slf4jLogger.info("metodo autenticarUsuario");
		
		LoginVO loginVOUsuario = null;
		
		try {
			
			CredencialesDTO credencialesDTO = loginVO.getCredencialesDTO();
			
//			slf4jLogger.info("credencialesDTO.getPassword {}", credencialesDTO.getPassword());
			String claveEncriptada = EncriptacionUtil.getInstancia().encriptar(credencialesDTO.getPassword());
//			slf4jLogger.info("claveEncriptada {}", claveEncriptada);
			credencialesDTO.setPassword(claveEncriptada);
			
			Usuario usuarioLogueado = factoryDAO.getUsuarioDAOImpl().obtenerPorLogin(credencialesDTO);
			
			if (usuarioLogueado != null) {
				
				loginVOUsuario = new LoginVO();
				loginVOUsuario.setCredencialesDTO(loginVO.getCredencialesDTO());
				
				usuarioLogueado.setFechaUltimoIngreso(UtilAplication.obtenerFechaActual());
				factoryDAO.getUsuarioDAOImpl().update(usuarioLogueado);
				
				usuarioLogueado.setNpDebeCambiarClave(this.debeCambiarClave(usuarioLogueado));
				
				//obtener los modulos del usuario
				List<Modulo> modulosUsuario = factoryDAO.getModuloDAOImpl().obtenerModuloUsuario(usuarioLogueado);
				
				if (CollectionUtils.isNotEmpty(modulosUsuario)) {
					for (Modulo modulo: modulosUsuario) {
						modulo.getSegtMenus().size();
					}
					loginVOUsuario.setColModuloUsuario(modulosUsuario);
				}
				
				HistoricoTransaccione historicoTransacciones = new HistoricoTransaccione();
				historicoTransacciones.setNombreTransaccion(UtilAplication.appendStringBuilder(ServicioLoginImpl.class.getName(), " ", "autenticarUsuario").toString());
				historicoTransacciones.setFechaTransaccion(UtilAplication.obtenerFechaActual());
//				historicoTransacciones.setDcTipoTransaccion(new DetalleCatalogo());
//				historicoTransacciones.getDcTipoTransaccion().setId(new DetalleCatalogoPK());
//				historicoTransacciones.getDcTipoTransaccion().getId().setCabCatalogoFk(CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_TRANSACCION);
//				historicoTransacciones.getDcTipoTransaccion().getId().setDetCatalogoNivel1(EnumTipoTransaccion.LOGIN.getId());
				
				historicoTransacciones.setCabCatalogoFk(CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_TRANSACCION);
				historicoTransacciones.setDetCatalogoNivel1(EnumTipoTransaccion.LOGIN.getId());
				
				historicoTransacciones.setSegtUsuario(usuarioLogueado);
				
				factoryDAO.getHistoricoTransaccioneDAOImpl().create(historicoTransacciones);
				
				loginVOUsuario.setUsuario(usuarioLogueado);
				
			}
			
		} catch (Exception e) {
			throw new SeguridadesException(e.getMessage());
		}
		
		return loginVOUsuario;
	}

	@Override
	public Usuario cambiarClaveUsuario(Usuario usuario) throws SeguridadesException {
		
		slf4jLogger.info("metodo cambiarClaveUsuario");
		Usuario usuarioUpdate = null;
		try {
			
			usuario.setFechaCambioClave(UtilAplication.obtenerFechaActual());
			String claveEncriptada = EncriptacionUtil.getInstancia().encriptar(usuario.getPassUsuario());
			usuario.setPassUsuario(claveEncriptada);
			
			usuarioUpdate = factoryDAO.getUsuarioDAOImpl().update(usuario);
			
			if (usuarioUpdate != null) {
				usuarioUpdate.setNpDebeCambiarClave(Boolean.FALSE);
				
				HistorialClave historialClave = new HistorialClave();
				historialClave.setSegtUsuario(new Usuario());
				historialClave.getSegtUsuario().setIdUsuario(usuarioUpdate.getIdUsuario());
				historialClave.setPassUsuario(EncriptacionUtil.getInstancia().encriptar(usuarioUpdate.getNpPasswordTemp()));
				historialClave.setFechaRegistro(UtilAplication.obtenerFechaActual());
				factoryDAO.getHistorialClaveDAOImpl().create(historialClave);
				
				historialClave = null;
			}
			
			claveEncriptada = null;
			
		} catch (Exception e) {
			slf4jLogger.info("no se pudo cambiarClaveUsuario {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return usuarioUpdate;
	}

	@Override
	public Boolean validarClaveUsuario(Usuario usuario) throws SeguridadesException {
		
		HistorialClave historialClave = new HistorialClave();
		historialClave.setSegtUsuario(new Usuario());
		historialClave.getSegtUsuario().setIdUsuario(usuario.getIdUsuario());
		historialClave.setPassUsuario(EncriptacionUtil.getInstancia().encriptar(usuario.getNpPasswordTemp()));
		
		List<HistorialClave> listaHistoricoClaves = factoryDAO.getHistorialClaveDAOImpl().obtenerHistorialClaveCriterios(historialClave);
		
		if (!CollectionUtils.isEmpty(listaHistoricoClaves)) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	private Boolean debeCambiarClave (Usuario usuario) throws SeguridadesException{
		
		String userNameEncriptado = EncriptacionUtil.getInstancia().encriptar(usuario.getLoginUsuario());
		
		if (userNameEncriptado.equals(usuario.getPassUsuario())) {
			return Boolean.TRUE;
		} else {
			
//			ParametroDTO parametroPlantilla = new ParametroDTO();
//			List<ParametroDTO> parametroCol = factoryDAO.getParametroDAOImpl().buscarParametroCriterios(parametroPlantilla);
//			if (CollectionUtils.isEmpty(parametroCol)) {
//				return Boolean.FALSE;
//			} else {
//				
//			}
		}
		
		return Boolean.FALSE;
		
	}

}
