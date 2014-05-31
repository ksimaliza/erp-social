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
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;

public class EstudianteDAOImpl extends AbstractFacadeImpl<EstudianteDTO> implements EstudianteDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteDAOImpl.class);
	
	public EstudianteDAOImpl() {
		super();
	}

	public EstudianteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<EstudianteListDTO> obtenerEstudiante(EstudianteListDTO estudiante) throws SeguridadesException {
		
		slf4jLogger.info("obtenerEstudiante");
		List<EstudianteListDTO> estudiantesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EstudianteListDTO> cq=cb.createQuery(EstudianteListDTO.class);
		Root<EstudianteListDTO> from = cq.from(EstudianteListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		/*if(estudiante.getPerNombres()!=null)
			cq.where(cb.like(cb.upper(from.get("perNombre").as(String.class)), "%"+estudiante.getPerNombres().toUpperCase()));
		
		
		cq.where(cb.or(cb.like(cb.upper(from.get("perNombre").as(String.class)), "%"+estudiante.getPerNombres().toUpperCase()),cb.like(cb.upper(from.get("perApellidos").as(String.class)), "%"+estudiante.getPerApellidos().toUpperCase())));
		
		List<EstudianteListDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;*/
		CriteriaQuery<EstudianteListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(estudiante.getPerNombres())) {
			Expression<String> nombreEstudiante = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(estudiante.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreEstudiante);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(estudiante.getPerApellidos())) {
					Expression<String> apellidoEstudiante = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(estudiante.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoEstudiante);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<EstudianteListDTO> typedQuery = entityManager.createQuery(select);
		estudiantesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return estudiantesEncontrados;
}
		
		
	
	
	
}
