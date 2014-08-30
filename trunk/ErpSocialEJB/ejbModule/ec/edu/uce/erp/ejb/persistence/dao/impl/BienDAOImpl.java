/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.BienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;

/**
 * @author 
 *
 */
@Stateless
public class BienDAOImpl extends AbstractFacadeImpl<Bien> implements BienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienDAOImpl.class);
	
	public BienDAOImpl () {}
	
	public BienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Bien> buscarBienCriterios(Bien bien) throws SeguridadesException {
		
		slf4jLogger.info("buscarBienCriterios");
		
		List<Bien> listBien = null;
		
		try {
			
			Predicate predicate = null;
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bien> criteriaQuery = criteriaBuilder.createQuery(Bien.class);
			
			Root<Bien> fromBien = criteriaQuery.from(Bien.class);
			criteriaQuery.select(fromBien);
			
			if (bien.getEmrPk()==null) {
				throw new SeguridadesException("El codigo de la empresa es requerido para la busqueda");
			} else {
				//por id de empresa
				predicate = criteriaBuilder.equal(fromBien.get("emrPk"), bien.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//por pk
			if (bien.getBiePk()!=null) {
				predicate = criteriaBuilder.equal(fromBien.get("biePk"), bien.getBiePk());
				criteriaList.add(predicate);
			}
			
			//por estado del bien activo inactio
			if (StringUtils.isNotBlank(bien.getBieEstado())) {
				predicate = criteriaBuilder.equal(fromBien.get("bieEstado"), bien.getBieEstado());
				criteriaList.add(predicate);
			}
			
			//por estado de uso del bien bien asignado no asignado
			if (StringUtils.isNotBlank(bien.getBieEstadoUso())) {
				predicate = criteriaBuilder.equal(fromBien.get("bieEstadoUso"), bien.getBieEstadoUso());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<Bien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al buscarBienCriterios {}", e);
			throw new SeguridadesException(e);
		} 
		
		return listBien;
		
	}

	@Override
	public BigInteger generarNextValSecuenciaCodigo() throws SeguridadesException {
		
		try {
			Query query = entityManager.createNativeQuery("select nextval('bien_codigo_seq')");
			return (BigInteger) query.getSingleResult();
		} catch (Exception e) {
			slf4jLogger.info("Error el generar el valor para la secuencia bien_codigo_seq {}", e.toString());
			throw new SeguridadesException("Error el generar el valor para la secuencia bien_codigo_seq", e);
		}
		
	}

}
