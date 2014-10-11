package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.ActaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.ActaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.TransaccionActaBien;

@Stateless
public class ActaBienDAOImpl extends AbstractFacadeImpl<ActaBien> implements ActaBienDAO {
	
private static final Logger slf4jLogger = LoggerFactory.getLogger(ActaBienDAOImpl.class);
	
	public ActaBienDAOImpl () {}
	
	public ActaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ActaBien> obtenerActaBienCriterios(ActaBien actaBien) throws SeguridadesException {
		
		slf4jLogger.info("obtenerActaBienCriterios");
		
		List<ActaBien> listActaBien = null;
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ActaBien> criteriaQuery = criteriaBuilder.createQuery(ActaBien.class);
			
			Root<ActaBien> fromActaBien = criteriaQuery.from(ActaBien.class);
			criteriaQuery.select(fromActaBien);
			
			Join<ActaBien, TransaccionActaBien> joinActaTransaccionActa = fromActaBien.join("transaccionActaBiens", JoinType.INNER);
			Join<TransaccionActaBien, Transaccion> joinTransaccionActa = joinActaTransaccionActa.join("transaccionTbl", JoinType.INNER);
			Join<Transaccion, Bien> joinTransaccionBien = joinTransaccionActa.join("bienTbl", JoinType.INNER);
			
			criteriaList = new ArrayList<Predicate>();
			
			// por id del bien asociado
			if (actaBien.getNpBiePk() != null && actaBien.getNpBiePk()>0) {
				predicate = criteriaBuilder.equal(joinTransaccionBien.get("biePk"), actaBien.getNpBiePk());
				criteriaList.add(predicate);
			}
			
			// por id de la empresa a la que pertenece el bien
			if (actaBien.getNpEmrPk() != null && actaBien.getNpEmrPk()>0) {
				predicate = criteriaBuilder.equal(joinTransaccionBien.get("emrPk"), actaBien.getNpEmrPk());
				criteriaList.add(predicate);
			}
			
			// se trae los bienes con estado transaccion activo
			predicate = criteriaBuilder.equal(joinTransaccionActa.get("traEstado"), ConstantesApplication.ESTADO_ACTIVO);
			criteriaList.add(predicate);
			predicate = criteriaBuilder.isNull(joinTransaccionActa.get("fechaFin"));
			criteriaList.add(predicate);
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<ActaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listActaBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al obtenerActaBienCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return listActaBien;
	}

}
