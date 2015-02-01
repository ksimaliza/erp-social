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
import ec.edu.uce.erp.ejb.persistence.dao.MateriaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;

public class MateriaDAOImpl extends AbstractFacadeImpl<MateriaDTO> implements MateriaDAO  {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(MateriaDAOImpl.class);
	
	public MateriaDAOImpl() {
		super();
	
	}

	public MateriaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<MateriaDTO> obtenerMateria(MateriaDTO materia) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMateria");
		List<MateriaDTO> materiasEncontradas = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MateriaDTO> cq=cb.createQuery(MateriaDTO.class);
		Root<MateriaDTO> from = cq.from(MateriaDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<MateriaDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(materia.getMtrNombe())) {
			Expression<String> nombreMateria = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(materia.getMtrNombe())));
			predicate = cb.like(cb.upper(from.<String>get("mtrNombe")), nombreMateria);
			criteriaList.add(predicate);
		}
		
		//por empresa
		if (materia.getMtrEmpresa()!=null) {
			predicate = cb.equal(from.get("mtrEmpresa"),materia.getMtrEmpresa());
			criteriaList.add(predicate);
		}
		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<MateriaDTO> typedQuery = entityManager.createQuery(select);
		materiasEncontradas = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return materiasEncontradas;
}
	
	
	public void eliminarMateria(Integer codMateria) throws SeguridadesException {
		Query query=entityManager.createNativeQuery("DELETE FROM mat_materia where mtr_codigo= :codMateria");
		query.setParameter("codMateria", codMateria);
		query.executeUpdate();
	}

}
