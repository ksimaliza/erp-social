/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.VistaEmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;

/**
 * @author 
 *
 */
public class VistaEmpleadoDAOImpl extends AbstractFacadeImpl<VistaEmpleado> implements VistaEmpleadoDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaEmpleadoDAOImpl.class);
	
	public VistaEmpleadoDAOImpl () {}
	
	public VistaEmpleadoDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<VistaEmpleado> obtenerVistaEmpleadoCriterios (VistaEmpleado vistaEmpleado) throws SeguridadesException{
		
		slf4jLogger.info("obtenerVistaEmpleadoCriterios");
		
		List<VistaEmpleado> listVistaEmpleado = null;
		
		try {
			
			Predicate predicate = null;
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaEmpleado> criteriaQuery = criteriaBuilder.createQuery(VistaEmpleado.class);
			
			Root<VistaEmpleado> fromVistaEmpleado = criteriaQuery.from(VistaEmpleado.class);
			criteriaQuery.select(fromVistaEmpleado);
			
			//por id empresa
			if (vistaEmpleado.getEmrPk() != null && vistaEmpleado.getEmrPk()>0) {
				predicate = criteriaBuilder.equal(fromVistaEmpleado.get("emrPk"), vistaEmpleado.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//TODO aumentar estado del empleado en la vistaEmpleado
//			//por estado
//			if (StringUtils.isNotBlank(usuario.getEstado())) {
//				predicate = criteriaBuilder.equal(fromUsuario.get("estado"), usuario.getEstado());
//				criteriaList.add(predicate);
//			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
//			criteriaQuery.orderBy(criteriaBuilder.asc(fromUsuario.get("fechaRegistro")));
			
			TypedQuery<VistaEmpleado> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listVistaEmpleado = typedQuery.getResultList();
			
			
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return listVistaEmpleado;
	}

}
