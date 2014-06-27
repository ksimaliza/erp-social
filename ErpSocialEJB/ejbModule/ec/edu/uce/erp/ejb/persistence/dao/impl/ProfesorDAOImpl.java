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
import ec.edu.uce.erp.ejb.persistence.dao.ProfesorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;

public class ProfesorDAOImpl extends AbstractFacadeImpl<ProfesorDTO> implements ProfesorDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public ProfesorDAOImpl() {
		super();
		
	}

	public ProfesorDAOImpl(EntityManager entityManager) {
		super(entityManager);

	}
	
	@Override
	public List<DocenteListDTO> obtenerDocente(DocenteListDTO docente) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDocente");
		List<DocenteListDTO> docentesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<DocenteListDTO> cq=cb.createQuery(DocenteListDTO.class);
		Root<DocenteListDTO> from = cq.from(DocenteListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<DocenteListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(docente.getPerNombres())) {
			Expression<String> nombreDocente = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(docente.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDocente);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(docente.getPerApellidos())) {
					Expression<String> apellidoEstudiante = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(docente.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoEstudiante);
					criteriaList.add(predicate);
				}
		
		//por empresa
				if (docente.getProEmpresa()!=null) {
					predicate = cb.equal(from.get("proEmpresa"),docente.getProEmpresa());
					criteriaList.add(predicate);
				}
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<DocenteListDTO> typedQuery = entityManager.createQuery(select);
		docentesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return docentesEncontrados;
}
		

}
