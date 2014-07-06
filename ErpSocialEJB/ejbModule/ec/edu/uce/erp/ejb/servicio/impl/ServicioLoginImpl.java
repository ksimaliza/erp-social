/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio.impl;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;
import ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes;
import ec.edu.uce.erp.common.util.EncriptacionUtil;
import ec.edu.uce.erp.common.util.MessagesApplicacion;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistorialClave;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistoricoTransaccione;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;
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
				
				usuarioLogueado.setNpDebeCambiarClave(this.debeCambiarClave(usuarioLogueado));
				usuarioLogueado.setFechaUltimoIngreso(UtilAplication.obtenerFechaActual());
				
				factoryDAO.getUsuarioDAOImpl().update(usuarioLogueado);
				
				//obtener los modulos del usuario
				List<Modulo> modulosUsuario = factoryDAO.getModuloDAOImpl().obtenerModuloUsuario(usuarioLogueado);
				
				if (CollectionUtils.isNotEmpty(modulosUsuario)) {
					for (Modulo modulo: modulosUsuario) {
						modulo.getSegtMenus().size();
						Collections.sort(modulo.getSegtMenus());
					}
					loginVOUsuario.setColModuloUsuario(modulosUsuario);
				}
				
				HistoricoTransaccione historicoTransacciones = new HistoricoTransaccione();
				historicoTransacciones.setNombreTransaccion(UtilAplication.appendStringBuilder(ServicioLoginImpl.class.getName(), " ", "autenticarUsuario").toString());
				historicoTransacciones.setFechaTransaccion(UtilAplication.obtenerFechaActual());
				historicoTransacciones.setCabCatalogoTipoTransaccion(CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_TRANSACCION);
				historicoTransacciones.setDetCatalogoTipoTransaccion(EnumTipoTransaccion.LOGIN.getId());
				historicoTransacciones.setIdUsuario(usuarioLogueado.getIdUsuario());
				
				factoryDAO.getHistoricoTransaccioneDAOImpl().create(historicoTransacciones);
				
				usuarioLogueado.getSegtMenuUsuarios().size();
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
		
		// si es el primer ingreso se debe cambiar la clave
		if (usuario.getFechaUltimoIngreso()== null) {
			return Boolean.TRUE;
		}
		
		// si tiene la clave por defecto se debe cambiar la clave
		if (usuario.getPassUsuario().equals(EncriptacionUtil.getInstancia().encriptar(usuario.getLoginUsuario()))) {
			return Boolean.TRUE;
		}
		
		// se verifica la ultima ves que cambio la clave y se compara con el tiempo que se usa como parametro
		Parametro parametroPlantilla = new Parametro();
		parametroPlantilla.setEstado(ESTADO_ACTIVO);
		parametroPlantilla.setIdParametro(MessagesApplicacion.getInteger("erp.parametro.cambiar.clave.id"));
		List<Parametro> parametroCol = factoryDAO.getParametroDAOImpl().buscarParametroCriterios(parametroPlantilla);
		
		if (CollectionUtils.isNotEmpty(parametroCol) && parametroCol.size()==1) {
			Parametro parametro = parametroCol.get(0);
			
			if (StringUtils.isNotBlank(parametro.getValorParametro())) {
				
				Long numeroDias = Long.valueOf(parametro.getValorParametro());
				
				if (usuario.getFechaCambioClave() == null) { // todavia no realiza cambio de clave
					return Boolean.TRUE;
				} else {
					Timestamp fechaMaximoCambioClave = UtilAplication.sumarDiasTimestamp(usuario.getFechaCambioClave(),numeroDias);
					Timestamp fechaActual = UtilAplication.obtenerFechaActual();
					
					if (fechaActual.getTime() > fechaMaximoCambioClave.getTime()) {
						return Boolean.TRUE;
					}
				}
				
			}
		}
		
		return Boolean.FALSE;
		
	}

}
