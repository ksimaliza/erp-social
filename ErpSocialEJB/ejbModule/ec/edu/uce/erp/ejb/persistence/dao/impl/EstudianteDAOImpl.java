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

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
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
		
		
		CriteriaQuery<EstudianteListDTO> select = cq.select(from);
		
		//Por Ci
		if (!StringUtils.isEmpty(estudiante.getPerCi())) {
			Expression<String> ciEstudiante = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(estudiante.getPerCi())));
			predicate = cb.like(cb.upper(from.<String>get("perCi")), ciEstudiante);
			criteriaList.add(predicate);
		}
		
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
		
		//por empresa
			if (estudiante.getEstEmpresa()!=null) {
					predicate = cb.equal(from.get("estEmpresa"),estudiante.getEstEmpresa());
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
	
	 /*Fecha 01/12/2012
	  * @param codEstudiante
	  * @throws Exception
	 */
		public void updateEstadoEstudiante(Integer codEstudiante) throws SeguridadesException {
			Query query=entityManager.createNativeQuery("Update mat_estudiante set est_estado='Matrículado' where est_codigo= :codEstudiante");
			query.setParameter("codEstudiante", codEstudiante);
			query.executeUpdate();
		}
		
		
	
	
	
}
