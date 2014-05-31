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
import ec.edu.uce.erp.ejb.persistence.dao.DoctorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;

public class DoctorDAOImpl extends AbstractFacadeImpl<DoctorDTO> implements DoctorDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(DoctorDAOImpl.class);
	public DoctorDAOImpl() {
		super();
		
	}

	public DoctorDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<DoctorListDTO> obtenerDoctor(DoctorListDTO Doctor) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDoctor");
		List<DoctorListDTO> DoctoresEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<DoctorListDTO> cq=cb.createQuery(DoctorListDTO.class);
		Root<DoctorListDTO> from = cq.from(DoctorListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<DoctorListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(Doctor.getPerNombres())) {
			Expression<String> nombreDoctor = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(Doctor.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDoctor);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(Doctor.getPerApellidos())) {
					Expression<String> apellidoDoctor = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(Doctor.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoDoctor);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<DoctorListDTO> typedQuery = entityManager.createQuery(select);
		DoctoresEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return DoctoresEncontrados;
}

}
