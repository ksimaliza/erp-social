/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INVALIDO;

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

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.VistaBienDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
/**
 * @author 
 *
 */
@Stateless
public class VistaBienDAOImpl extends AbstractFacadeImpl<VistaBien> implements VistaBienDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaBienDAOImpl.class);
	
	public VistaBienDAOImpl () {}
	
	public VistaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaBien> obtenerVistaBienCriterios(VistaBien vistaBien) throws SeguridadesException {
		
		slf4jLogger.info("obtenerVistaBienCriterios");
		
		List<VistaBien> colVistaBien = null;
		
		try {
			
			Predicate predicate = null;
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaBien> criteriaQuery = criteriaBuilder.createQuery(VistaBien.class);
			
			Root<VistaBien> fromVistaBien = criteriaQuery.from(VistaBien.class);
			criteriaQuery.select(fromVistaBien);
			
			if (vistaBien.getEmrPk()==null) {
				throw new SeguridadesException("El codigo de la empresa es requerido para la busqueda");
			} else {
				//por id de empresa
				predicate = criteriaBuilder.equal(fromVistaBien.get("emrPk"), vistaBien.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//por pk
			if (vistaBien.getBiePk()!=null) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("biePk"), vistaBien.getBiePk());
				criteriaList.add(predicate);
			}
			
			//por codigo del bien
			if (StringUtils.isNotBlank(vistaBien.getBieCodigo())) {
				Expression<String> codigoBien = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaBien.getBieCodigo(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVistaBien.<String>get("bieCodigo")), codigoBien);
				criteriaList.add(predicate);
			}
			
			//por nombre del bien
			if (StringUtils.isNotBlank(vistaBien.getBieNombre())) {
				Expression<String> nombreBien = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaBien.getBieNombre(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVistaBien.<String>get("bieNombre")), nombreBien);
				criteriaList.add(predicate);
			}
			
			//por tipo transaccion
			if (StringUtils.isNotBlank(vistaBien.getDetBienTipBieNivel1()) && !vistaBien.getDetBienTipBieNivel1().equals(ESTADO_INVALIDO)) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("detBienTipBieNivel1"), vistaBien.getDetBienTipBieNivel1());
				criteriaList.add(predicate);
				predicate = null;
				vistaBien.setCabBienTipBieFk(ConstantesApplication.CAB_CAT_TIPO_BIEN);
				predicate = criteriaBuilder.equal(fromVistaBien.get("cabBienTipBieFk"), vistaBien.getCabBienTipBieFk());
				criteriaList.add(predicate);
			}
			
			//por categoria
			if (vistaBien.getCatBienPk()!=null && vistaBien.getCatBienPk()>0) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("catBienPk"), vistaBien.getCatBienPk());
				criteriaList.add(predicate);
			}
			
			//por linea
			if (vistaBien.getLinBienPk()!=null && vistaBien.getLinBienPk()>0) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("linBienPk"), vistaBien.getLinBienPk());
				criteriaList.add(predicate);
			}
			
			//por fecha asignacion
			if (vistaBien.getBieFechaAsig()!=null) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("bieFechaAsig"), vistaBien.getBieFechaAsig());
				criteriaList.add(predicate);
				
			}
			
			//por ubicacion
			if (StringUtils.isNotBlank(vistaBien.getBieUbicacion())) {
				Expression<String> ubicacionBien = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaBien.getBieUbicacion(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVistaBien.<String>get("bieUbicacion")), ubicacionBien);
				criteriaList.add(predicate);
			}
			
			//por estado del bien
			if (StringUtils.isNotBlank(vistaBien.getBieEstado())) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("bieEstado"), vistaBien.getBieEstado());
				criteriaList.add(predicate);
			}
			
			//por estado de la tabla transaccion (estados del bien)
			if (StringUtils.isNotBlank(vistaBien.getTraEstado())) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("traEstado"), vistaBien.getTraEstado());
				criteriaList.add(predicate);
			}
			
			//por custodio asignado actual
			if (StringUtils.isNotBlank(vistaBien.getPerCi())) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("perCi"), vistaBien.getPerCi());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
//			criteriaQuery.orderBy(criteriaBuilder.asc(fromVistaBien.get("fechaRegistro")));
			
			TypedQuery<VistaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			colVistaBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerVistaBienCriterios {} - {}", e.toString(), e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return colVistaBien;
	}

}
