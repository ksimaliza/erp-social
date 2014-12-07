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

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.ModuloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author 
 *
 */
@Stateless
public class ModuloDAOImpl extends AbstractFacadeImpl<Modulo> implements ModuloDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ModuloDAOImpl.class);
	public ModuloDAOImpl(){}
	
	public ModuloDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modulo> obtenerModuloUsuario(Usuario usuario) throws SeguridadesException {
		
		slf4jLogger.info("obtenerModuloUsuario");
		
		List<Modulo> modulosUsuario = null;
		
		try {
			
			Query query = entityManager.createQuery("select m from Modulo m " +
					" join m.segtPerfils p join m.empresaTbls e where p.idPerfil = ? and e.emrPk = ?");
			query.setParameter(1, usuario.getSegtPerfil().getIdPerfil());
			query.setParameter(2, usuario.getEmpresaTbl().getEmrPk());
			
			modulosUsuario = query.getResultList();;
			
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return modulosUsuario;
	}

	@Override
	public List<Modulo> obtenerModuloCriterios(Modulo modulo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerModuloCriterios");
		
		List<Modulo> moduloCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Modulo> criteriaQuery = criteriaBuilder.createQuery(Modulo.class);
			
			Root<Modulo> fromModulo = criteriaQuery.from(Modulo.class);
			criteriaQuery.select(fromModulo);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre de modulo
			if (StringUtils.isNotBlank(modulo.getNombreModulo())) {
				Expression<String> nombreModulo = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", modulo.getNombreModulo(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromModulo.<String>get("nombreModulo")), nombreModulo);
				criteriaList.add(predicate);
			}
			
			//por descripcion de modulo
			if (StringUtils.isNotBlank(modulo.getDescModulo())) {
				Expression<String> descModulo = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", modulo.getDescModulo(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromModulo.<String>get("descModulo")), descModulo);
				criteriaList.add(predicate);
			}
			
			//por id empresa
			if (modulo.getNpIdEmpresa() != null && modulo.getNpIdEmpresa()!=0) {
				predicate = criteriaBuilder.equal(fromModulo.joinList("empresaTbls").get("emrPk"), modulo.getNpIdEmpresa());
				criteriaList.add(predicate);
			}
			
			//por id perfil
			if (modulo.getNpIdPerfil() != null && modulo.getNpIdPerfil()!=0) {
				predicate = criteriaBuilder.equal(fromModulo.joinList("segtPerfils").get("idPerfil"), modulo.getNpIdPerfil());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(modulo.getEstado())) {
				predicate = criteriaBuilder.equal(fromModulo.get("estado"), modulo.getEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromModulo.get("fechaRegistro")));
			
			TypedQuery<Modulo> typedQuery = entityManager.createQuery(criteriaQuery);
			
			moduloCol = typedQuery.getResultList();
			
			//inicializar la coleccion de empresas
			if (CollectionUtils.isNotEmpty(moduloCol)) {
				CollectionUtils.select(moduloCol, new org.apache.commons.collections.Predicate() {
					
					@Override
					public boolean evaluate(Object arg0) {
						
						Modulo modulo = (Modulo)arg0;
						modulo.getEmpresaTbls().size();
						return true;
					}
				});
			}
			
		} catch (Exception e) {
			throw new SeguridadesException("Error al obtener modulos por criterios");
		} finally {
			predicate = null;
			criteriaList = null;
		}
		
		return moduloCol;
		
	}
	

}
