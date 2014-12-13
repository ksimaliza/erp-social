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
import ec.edu.uce.erp.ejb.persistence.dao.CatalogoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;

public class CatalogoDAOImpl extends AbstractFacadeImpl<CatalogoEucaristiaDTO> implements CatalogoDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelNichoDAOImpl.class);

	public CatalogoDAOImpl() {
		super();
		
	}

	public CatalogoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override 
	public List<CatalogoEucaristiaDTO> getAll(CatalogoEucaristiaDTO catalogo) 
	{ 
		List<CatalogoEucaristiaDTO> list = null; 
		
			Query query = entityManager.createQuery("select cat from CatalogoEucaristiaDTO cat inner join cat.eucCatalogo catP where catP.catCodigo= :codigo order by cat.catDescripcion");
			if(catalogo.getCatCodigo()!=null)
				query.setParameter("codigo", catalogo.getCatCodigo());
			else
				query.setParameter("codigo", 0);
			
			return list=query.getResultList(); 
	} 
	
	@Override
	public List<CatalogoEucaristiaDTO> obtenerSeccionNicho(CatalogoEucaristiaDTO seccionNichoDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerNivelNicho");
		List<CatalogoEucaristiaDTO> nivelNichoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<CatalogoEucaristiaDTO> cq=cb.createQuery(CatalogoEucaristiaDTO.class);
		Root<CatalogoEucaristiaDTO> from = cq.from(CatalogoEucaristiaDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<CatalogoEucaristiaDTO> select = cq.select(from);
		
		predicate = cb.equal(from.<Integer>get("eucCatalogo"), 29);
		criteriaList.add(predicate);
		//por descripcion
		if (!StringUtils.isEmpty(seccionNichoDTO.getCatDescripcion())) {
			Expression<String> descNivelNicho = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(seccionNichoDTO.getCatDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("catDescripcion")), descNivelNicho);
			criteriaList.add(predicate);
		}
		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<CatalogoEucaristiaDTO> typedQuery = entityManager.createQuery(select);
		nivelNichoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return nivelNichoEncontrado;
}
}	



