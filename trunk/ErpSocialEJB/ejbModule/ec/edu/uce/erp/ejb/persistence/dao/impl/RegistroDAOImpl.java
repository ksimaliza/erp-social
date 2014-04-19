package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.RegistroDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;

public class RegistroDAOImpl extends AbstractFacadeImpl<RegistroDTO> implements RegistroDAO {

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(RegistroDAOImpl.class);
	
	public RegistroDAOImpl() {
		super();
	}

	public RegistroDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public RegistroDTO getUltimate(EmpleadoDTO empleado) throws SeguridadesException
	{
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		RegistroDTO result = null;

		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<RegistroDTO> cq=cb.createQuery(RegistroDTO.class);
			Root<RegistroDTO> from = cq.from(RegistroDTO.class);
		
			CriteriaQuery<RegistroDTO> select = cq.select(from);
			
			predicate=cb.equal(from.get("rasmpleado"), empleado);
			criteriaList.add(predicate);
	
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
	
			cq.orderBy(cb.desc(from.get("rasHoraInicio")));
			
			TypedQuery<RegistroDTO> typedQuery = entityManager.createQuery(select);
			
			result = typedQuery.getResultList().get(0);	

		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}		
		return result;
	}

}
