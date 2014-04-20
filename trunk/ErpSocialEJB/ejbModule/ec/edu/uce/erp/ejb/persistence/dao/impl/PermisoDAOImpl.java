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
import ec.edu.uce.erp.ejb.persistence.dao.PermisoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoListDTO;

public class PermisoDAOImpl extends AbstractFacadeImpl<PermisoDTO> implements PermisoDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PermisoDAOImpl.class);
	
	public PermisoDAOImpl() {
		super();
	}

	public PermisoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	@Override
	public List<PermisoListDTO> findAll(PermisoListDTO permiso) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<PermisoListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<PermisoListDTO> cq=cb.createQuery(PermisoListDTO.class);
		Root<PermisoListDTO> from = cq.from(PermisoListDTO.class);
		
		CriteriaQuery<PermisoListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(permiso.getPerNombres())) {
			Expression<String> nombre = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(permiso.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombre);
			criteriaList.add(predicate);
		}
		//por apellidos
		if (!StringUtils.isEmpty(permiso.getPerApellidos())) {
			Expression<String> apellido = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(permiso.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellido);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<PermisoListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}

	
}
