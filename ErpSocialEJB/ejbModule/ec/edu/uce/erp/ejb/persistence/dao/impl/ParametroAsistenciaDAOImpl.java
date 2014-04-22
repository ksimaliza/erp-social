package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroAsistenciaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;

public class ParametroAsistenciaDAOImpl extends AbstractFacadeImpl<ParametroDTO> implements ParametroAsistenciaDAO {

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);
	
	public ParametroAsistenciaDAOImpl() {
		super();
		
	}

	public ParametroAsistenciaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<ParametroDTO> findAll(ParametroDTO parametro) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<ParametroDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ParametroDTO> cq=cb.createQuery(ParametroDTO.class);
		Root<ParametroDTO> from = cq.from(ParametroDTO.class);
		
		CriteriaQuery<ParametroDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(parametro.getPasDescripcion())) {
			Expression<String> descripcion = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(parametro.getPasDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("pasDescripcion")), descripcion);
			criteriaList.add(predicate);
		}
						
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ParametroDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}

}
