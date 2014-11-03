/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.PersonaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;

/**
 * @author 
 *
 */
@Stateless
public class PersonaDAOImpl extends AbstractFacadeImpl<Persona> implements PersonaDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PersonaDAOImpl.class);
	
	public PersonaDAOImpl () {}
	
	public PersonaDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<Persona> buscarPersonaCriterios (Persona persona) throws SeguridadesException {
		
		slf4jLogger.info("buscarPersonaCriterios");
		
		List<Persona> personaCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Persona> criteriaQuery = criteriaBuilder.createQuery(Persona.class);
			
			Root<Persona> fromPerfil = criteriaQuery.from(Persona.class);
			criteriaQuery.select(fromPerfil);
			
			criteriaList = new ArrayList<Predicate>();
			
			//pk persona
			if (persona.getPerPk()!=null) {
				predicate = criteriaBuilder.equal(fromPerfil.get("perPk"), persona.getPerPk());
				criteriaList.add(predicate);
			}
			
			//ci persona
			if (StringUtils.isNotBlank(persona.getPerCi())) {
				predicate = criteriaBuilder.equal(fromPerfil.get("perCi"), persona.getPerCi());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
//			criteriaQuery.orderBy(criteriaBuilder.asc(fromPerfil.get("fechaRegistro")));
			
			TypedQuery<Persona> typedQuery = entityManager.createQuery(criteriaQuery);
			
			personaCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarPersonaCriterios {}", e.toString());
			throw new SeguridadesException(e);
		} finally {
			predicate = null;
			criteriaList = null;
		}
		
		return personaCol;
	}

}
