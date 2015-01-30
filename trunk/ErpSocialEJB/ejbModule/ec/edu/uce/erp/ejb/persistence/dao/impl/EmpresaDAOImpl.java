/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;

/**
 * @author 
 *
 */
@Stateless
public class EmpresaDAOImpl extends AbstractFacadeImpl<Empresa> implements EmpresaDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public EmpresaDAOImpl(EntityManager entityManager){
		super(entityManager);
	}
	
	public EmpresaDAOImpl(){
	}
	
	@Override
	public List<Empresa> obtenerEmpresaCriterios (Empresa empresaDTO) throws SeguridadesException{
		
		slf4jLogger.info("obtenerEmpresaCriterios");
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
			
			Root<Empresa> fromEmpresa = criteriaQuery.from(Empresa.class);
			criteriaQuery.select(fromEmpresa);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre de empresa
			if (StringUtils.isNotBlank(empresaDTO.getEmrNombre())) {
				Expression<String> nombreEmpresa = 
						criteriaBuilder.upper(criteriaBuilder.literal(empresaDTO.getEmrNombre()));
				predicate = criteriaBuilder.equal(criteriaBuilder.upper(fromEmpresa.<String>get("emrNombre")), nombreEmpresa);
				criteriaList.add(predicate);
			}
			
			//por ruc empresa
			if (StringUtils.isNotBlank(empresaDTO.getEmrRuc())) {
				predicate = criteriaBuilder.equal(fromEmpresa.get("emrRuc"), empresaDTO.getEmrRuc());
				criteriaList.add(predicate);
			}
			
			//estado activo
			if (StringUtils.isNotBlank(empresaDTO.getEmrEstado())) {
				predicate = criteriaBuilder.equal(fromEmpresa.get("emrEstado"), empresaDTO.getEmrEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromEmpresa.get("emrNombre")));
			
			TypedQuery<Empresa> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
			
		} catch (Exception e) {
			throw new SeguridadesException("Error al obtener empresa por criterios");
		} finally {
			predicate = null; criteriaList = null;
		}
		
	}

}
