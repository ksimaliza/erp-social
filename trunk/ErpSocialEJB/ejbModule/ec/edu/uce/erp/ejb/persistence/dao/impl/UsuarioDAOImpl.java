/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.UsuarioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;

/**
 * @author 
 *
 */
@Stateless
public class UsuarioDAOImpl extends AbstractFacadeImpl<Usuario> implements UsuarioDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	public UsuarioDAOImpl () {}
	
	public UsuarioDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Usuario obtenerPorLogin(CredencialesDTO credencialesDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerPorLogin");
		
		Usuario usuarioEncontrado = null;
		
		try {
			
			List<Usuario> usuarios = this.buscarPorLogin(credencialesDTO.getUsuario());
			
			if(!CollectionUtils.isEmpty(usuarios) && usuarios.size()==1){
				usuarioEncontrado = usuarios.get(0);
				
				slf4jLogger.info("Se encontro el usuario {}", usuarioEncontrado.getLoginUsuario());
				
				if(!usuarioEncontrado.getPassUsuario().equals(credencialesDTO.getPassword())){
					usuarioEncontrado = null;
					slf4jLogger.info("Password incorrecto para el usuario: {}", credencialesDTO.getUsuario());
					throw new SeguridadesException("Password incorrecto para el usuario");
					
				} else if(usuarioEncontrado.getEstado().equals(ConstantesApplication.ESTADO_INACTIVO)){
					usuarioEncontrado = null;
					slf4jLogger.info("El usuario: {} se encuentra inactivo en el sistema", credencialesDTO.getUsuario());
					throw new SeguridadesException("El usuario se encuentra inactivo en el sistema");
				}
			} else {
				throw new SeguridadesException("No se encontro al usuario");
			}
			
		} catch (Exception e) {
			throw new SeguridadesException("Error al obtenerPorLogin" + e);
		}
	
		return usuarioEncontrado;
	}
	
	@SuppressWarnings("unchecked")
	private List<Usuario> buscarPorLogin(String login) throws SeguridadesException{
		
		List<Usuario> usuarios = null;
		
		try {
			//buscamos el usario
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.loginUsuario = :login");
			query.setParameter("login", login);
			
			usuarios = (List<Usuario>)query.getResultList();
			query = null;
			
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener el usuario de la BD {}", e);
			throw new SeguridadesException(e);
		}
		
		return usuarios;
	}

}
