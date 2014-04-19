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
import ec.edu.uce.erp.ejb.persistence.dao.EmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;

public class EmpleadoDAOImpl extends AbstractFacadeImpl<EmpleadoDTO> implements EmpleadoDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpleadoDAOImpl.class);
	
	public EmpleadoDAOImpl() {
		super();
	}

	public EmpleadoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<EmpleadoListDTO> findAll(EmpleadoListDTO empleado) throws SeguridadesException
	{
		slf4jLogger.info("obtenerEstudiante");
		List<EmpleadoListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpleadoListDTO> cq=cb.createQuery(EmpleadoListDTO.class);
		Root<EmpleadoListDTO> from = cq.from(EmpleadoListDTO.class);
		
		CriteriaQuery<EmpleadoListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(empleado.getPerNombres())) {
			Expression<String> nombre = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(empleado.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombre);
			criteriaList.add(predicate);
		}
		//por apellidos
		if (!StringUtils.isEmpty(empleado.getPerApellidos())) {
			Expression<String> apellido = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(empleado.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellido);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<EmpleadoListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}
	
	@Override
	public EmpleadoDTO findByCredentials(EmpleadoDTO empleado) throws SeguridadesException
	{
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		EmpleadoDTO result;
		
		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<EmpleadoDTO> cq=cb.createQuery(EmpleadoDTO.class);
			Root<EmpleadoDTO> from = cq.from(EmpleadoDTO.class);
		
			CriteriaQuery<EmpleadoDTO> select = cq.select(from);
			
			if(!StringUtils.isEmpty(empleado.getAemUsuario())){
				predicate=cb.equal(from.get("aemUsuario"), empleado.getAemUsuario());
				criteriaList.add(predicate);
			}

			if(!StringUtils.isEmpty(empleado.getAemCodigoRegistro())){
				predicate=cb.equal(from.get("aemCodigoRegistro"), empleado.getAemCodigoRegistro());
				criteriaList.add(predicate);
			}
			
			if(!StringUtils.isEmpty(empleado.getAemClave())){
				predicate=cb.equal(from.get("aemClave"), empleado.getAemClave());
				criteriaList.add(predicate);
			}
			
			if(criteriaList!=null && criteriaList.size()>0)
				cq.where(cb.and(criteriaList.toArray(new Predicate[0])));

			TypedQuery<EmpleadoDTO> typedQuery = entityManager.createQuery(select);
			result = typedQuery.getSingleResult();

		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return result;
	}

	
	

}
