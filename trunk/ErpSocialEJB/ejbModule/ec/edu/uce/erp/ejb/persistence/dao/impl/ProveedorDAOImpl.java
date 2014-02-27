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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;

/**
 * @author 
 *
 */
@Stateless
public class ProveedorDAOImpl extends AbstractFacadeImpl<Proveedor> implements ProveedorDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ProveedorDAOImpl.class);
	
	public ProveedorDAOImpl () {}
	
	public ProveedorDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Proveedor> buscarProveedorCriterios(Proveedor proveedor) throws SeguridadesException {
		slf4jLogger.info("buscarProveedorCriterios");
		
		List<Proveedor> listaProveedor = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Proveedor> criteriaQuery = criteriaBuilder.createQuery(Proveedor.class);
			
			Root<Proveedor> fromProveedor = criteriaQuery.from(Proveedor.class);
			criteriaQuery.select(fromProveedor);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre de proveedor
			if (StringUtils.isNotBlank(proveedor.getProvNombre())) {
				Expression<String> nombreProveedor = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", proveedor.getProvNombre(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromProveedor.<String>get("provNombre")), nombreProveedor);
				criteriaList.add(predicate);
			}
			
			//por apellido de proveedor
			if (StringUtils.isNotBlank(proveedor.getProvApellido())) {
				Expression<String> apellidoProveedor = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", proveedor.getProvApellido(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromProveedor.<String>get("provApellido")), apellidoProveedor);
				criteriaList.add(predicate);
			}
			
//			//por ci de proveedor
//			if (StringUtils.isNotBlank(usuario.getCiUsuario())) {
//				Expression<String> ciUsuario = 
//						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getCiUsuario(), "%").toString()));
//				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromUsuario.<String>get("ciUsuario")), ciUsuario);
//				criteriaList.add(predicate);
//			}
			
			//por estado
			if (StringUtils.isNotBlank(proveedor.getProvEstado())) {
				predicate = criteriaBuilder.equal(fromProveedor.get("provEstado"), proveedor.getProvEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
//			criteriaQuery.orderBy(criteriaBuilder.asc(fromProveedor.get("fechaRegistro")));
			
			TypedQuery<Proveedor> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listaProveedor = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarProveedorCriterios {}", e.toString());
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return listaProveedor;
	}

}
