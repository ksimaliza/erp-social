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
import ec.edu.uce.erp.ejb.persistence.dao.MatrimonioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;

public class MatrimonioDAOImpl extends AbstractFacadeImpl<MatrimonioDTO> implements MatrimonioDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(MatrimonioDAOImpl.class);
	
	public MatrimonioDAOImpl() {
		super();
		
	}

	public MatrimonioDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<MatrimonioListDTO> obtenerMatrimonio(MatrimonioListDTO matrimonioListDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMatrimonio");
		List<MatrimonioListDTO> matrimonioEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MatrimonioListDTO> cq=cb.createQuery(MatrimonioListDTO.class);
		Root<MatrimonioListDTO> from = cq.from(MatrimonioListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<MatrimonioListDTO> select = cq.select(from);
		
		//por novia nombres
		if (!StringUtils.isEmpty(matrimonioListDTO.getPerNombres())) {
			Expression<String> nombreNovia = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(matrimonioListDTO.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreNovia);
			criteriaList.add(predicate);
		}
		//por novia apellidos
				if (!StringUtils.isEmpty(matrimonioListDTO.getPerApellidos())) {
					Expression<String> apellidoNovia = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(matrimonioListDTO.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoNovia);
					criteriaList.add(predicate);
				}
				
		//por novio nombres
				if (!StringUtils.isEmpty(matrimonioListDTO.getNovioNombres())) {
					Expression<String> nombreNovio = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(matrimonioListDTO.getNovioNombres())));
					predicate = cb.like(cb.upper(from.<String>get("novioNombres")), nombreNovio);
					criteriaList.add(predicate);
				}
				//por novio apellidos
						if (!StringUtils.isEmpty(matrimonioListDTO.getNovioApellidos())) {
							Expression<String> apellidoNovio = 
									cb.upper(cb.literal
											(UtilAplication.concatenarPorcenteje(matrimonioListDTO.getNovioApellidos())));
							predicate = cb.like(cb.upper(from.<String>get("novioApellidos")), apellidoNovio);
							criteriaList.add(predicate);
						}
				
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<MatrimonioListDTO> typedQuery = entityManager.createQuery(select);
		matrimonioEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return matrimonioEncontrado;
}
}


