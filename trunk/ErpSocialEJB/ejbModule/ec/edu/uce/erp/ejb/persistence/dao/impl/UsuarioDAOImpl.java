/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
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
			slf4jLogger.info("error al obtenerPorLogin {}", e.toString());
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

	@Override
	public List<Usuario> obtenerUsuarioCriterios(Usuario usuario) throws SeguridadesException {
		slf4jLogger.info("obtenerUsuarioCriterios");
		
		List<Usuario> usuarioCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
			
			Root<Usuario> fromModulo = criteriaQuery.from(Usuario.class);
			criteriaQuery.select(fromModulo);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre de usuario
			if (StringUtils.isNotBlank(usuario.getNombresUsuario())) {
				Expression<String> nombreUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getNombresUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromModulo.<String>get("nombresUsuario")), nombreUsuario);
				criteriaList.add(predicate);
			}
			
			//por apellido de usuario
			if (StringUtils.isNotBlank(usuario.getApellidosUsuario())) {
				Expression<String> apellidoUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getApellidosUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromModulo.<String>get("apellidosUsuario")), apellidoUsuario);
				criteriaList.add(predicate);
			}
			
			//por ci de usuario
			if (StringUtils.isNotBlank(usuario.getCiUsuario())) {
				Expression<String> ciUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getCiUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromModulo.<String>get("ciUsuario")), ciUsuario);
				criteriaList.add(predicate);
			}
			
			//por id empresa
			if (usuario.getNpIdEmpresa() != null && usuario.getNpIdEmpresa()!=0) {
				predicate = criteriaBuilder.equal(fromModulo.join("empresaTbl").get("emrPk"), usuario.getNpIdEmpresa());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(usuario.getEstado())) {
				predicate = criteriaBuilder.equal(fromModulo.get("estado"), usuario.getEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromModulo.get("fechaRegistro")));
			
			TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteriaQuery);
			
			usuarioCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al obtenerUsuarioCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return usuarioCol;
	}

}
