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
import ec.edu.uce.erp.ejb.persistence.dao.SacerdoteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;

public class SacerdoteDAOImpl extends AbstractFacadeImpl<SacerdoteDTO> implements SacerdoteDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(SacerdoteDAOImpl.class);
	public SacerdoteDAOImpl() {
		super();
		
	}

	public SacerdoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<SacerdoteListDTO> obtenerSacerdote(SacerdoteListDTO sacerdote) throws SeguridadesException {
		
		slf4jLogger.info("obtenerSacerdote");
		List<SacerdoteListDTO> sacerdotesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<SacerdoteListDTO> cq=cb.createQuery(SacerdoteListDTO.class);
		Root<SacerdoteListDTO> from = cq.from(SacerdoteListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<SacerdoteListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(sacerdote.getPerNombres())) {
			Expression<String> nombreSacerdote = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(sacerdote.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreSacerdote);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(sacerdote.getPerApellidos())) {
					Expression<String> apellidoSacerdote = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(sacerdote.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoSacerdote);
					criteriaList.add(predicate);
				}
				//por estado
				if (sacerdote.getSacEstado()!=null) {
					predicate = cb.equal(from.get("sacEstado"), sacerdote.getSacEstado());
					criteriaList.add(predicate);
				}
				//por empresa
				if (sacerdote.getSacEmpresa()!=null) {
					predicate = cb.equal(from.get("sacEmpresa"), sacerdote.getSacEmpresa());
					criteriaList.add(predicate);
				}		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<SacerdoteListDTO> typedQuery = entityManager.createQuery(select);
		sacerdotesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return sacerdotesEncontrados;
}

}
