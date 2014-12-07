/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.PerfilDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;

/**
 * @author 
 *
 */
public class PerfilDAOImpl extends AbstractFacadeImpl<Perfil> implements PerfilDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDAOImpl.class);
	
	public PerfilDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}
	
	public PerfilDAOImpl () {
	}

	@Override
	public List<Perfil> obtenerPerfilCriterios(Perfil perfilDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerPerfilCriterios");
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Perfil> criteriaQuery = criteriaBuilder.createQuery(Perfil.class);
			
			Root<Perfil> fromPerfil = criteriaQuery.from(Perfil.class);
			criteriaQuery.select(fromPerfil);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre de compania
			if (StringUtils.isNotBlank(perfilDTO.getNombrePerfil())) {
				Expression<String> nombrePerfil = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", perfilDTO.getNombrePerfil(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromPerfil.<String>get("nombrePerfil")), nombrePerfil);
				criteriaList.add(predicate);
			}
			
			//estado activo
			if (StringUtils.isNotBlank(perfilDTO.getEstado())) {
				predicate = criteriaBuilder.equal(fromPerfil.get("estado"), perfilDTO.getEstado());
				criteriaList.add(predicate);
			}
			
			//por id de perfil
			if (perfilDTO.getIdPerfil()!= null && perfilDTO.getIdPerfil().intValue()>0) {
				predicate = criteriaBuilder.equal(fromPerfil.get("idPerfil"), perfilDTO.getIdPerfil());
				criteriaList.add(predicate);
			}
			
			//por empresa
//			if (perfilDTO.getNpEmpresaDTO() != null && perfilDTO.getNpEmpresaDTO().getIdEmpresa()!=null) {
//				
//			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromPerfil.get("fechaRegistro")));
			
			TypedQuery<Perfil> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
			
		} catch (Exception e) {
			throw new SeguridadesException("Error al obtener perfil por criterios");
		} finally {
			predicate = null; criteriaList = null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> obtenerPerfilEmpresa(Perfil perfilDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerPerfilEmpresa");
		
		List<Perfil> perfilCol = null;
		
		try {
			
			if (perfilDTO.getNpEmpresaDTO() != null && perfilDTO.getNpEmpresaDTO().getEmrPk()!=null) {
				
				Query query = entityManager
						.createQuery("select distinct p from Perfil p join p.segtModulos m join m.empresaTbls e where e.emrPk = ? and p.estado = ? and m.estado = ? and e.emrEstado = ?");
				query.setParameter(1, perfilDTO.getNpEmpresaDTO().getEmrPk());
				query.setParameter(2, ConstantesApplication.ESTADO_ACTIVO);
				query.setParameter(3, ConstantesApplication.ESTADO_ACTIVO);
				query.setParameter(4, ConstantesApplication.ESTADO_ACTIVO);
				perfilCol = query.getResultList();
				
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerPerfilEmpresa {}", e);
			throw new SeguridadesException(e);
		}
		
		return perfilCol;
	}

}
