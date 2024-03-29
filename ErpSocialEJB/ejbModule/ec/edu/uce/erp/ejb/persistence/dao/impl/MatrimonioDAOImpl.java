package ec.edu.uce.erp.ejb.persistence.dao.impl;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
				
						//por empresa
						if (matrimonioListDTO.getMatEmpresa()!=null) {
							predicate = cb.equal(from.get("matEmpresa"), matrimonioListDTO.getMatEmpresa());
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
	
	@Override
	public Date obtenerFechaMinMatrimonio(MatrimonioListDTO matrimonio) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMatrimonioPorFechaMin");
		Date fechaMin= null;
		
		try {
			fechaMin=(Date) entityManager.createQuery("select min(e.matFecha) from MatrimonioListDTO e where e.matEmpresa=:idEmpresa")
			  .setParameter("idEmpresa", matrimonio.getMatEmpresa())
			  .getSingleResult();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return fechaMin;
}
	@Override
	public Date obtenerFechaMaxMatrimonio(MatrimonioListDTO matrimonio) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMatrimonioPorFechaMin");
		Date fechaMax= null;
		
		try {
			fechaMax=(Date) entityManager.createQuery("select max(e.matFecha) from MatrimonioListDTO e where e.matEmpresa=:idEmpresa")
			  .setParameter("idEmpresa", matrimonio.getMatEmpresa())
			  .getSingleResult();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return fechaMax;
}
	
	
	@Override
	public List<MatrimonioListDTO> getDistinctReporteBautizoByAnd(MatrimonioListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<MatrimonioListDTO> cq;
		Root<MatrimonioListDTO> from;
		List<MatrimonioListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(MatrimonioListDTO.class);
			
			from= cq.from(MatrimonioListDTO.class);
			
			cq.multiselect(
					from.get("novioCedula"),
					from.get("novioApellidos"),
					from.get("novioNombres"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("sacNombres"),
					from.get("sacApellidos"),
					from.get("matFecha"),
					from.get("matFechaAprobacionCurso")
					).distinct(true);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID")&&!fieldName.equals("fechaDesde")&&!fieldName.equals("fechaHasta"))
				{
				    getter = objetoDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objetoDTO, new Object[0]);
				
				    if(value!=null && value!="")
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	        
	        if(objetoDTO.getFechaDesde()!=null && objetoDTO.getFechaHasta()!=null)
	        {
	        	predicateList.add(cb.between(from.get("matFecha").as(Timestamp.class), objetoDTO.getFechaDesde(), objetoDTO.getFechaHasta()));	        
	        }else if(objetoDTO.getFechaDesde()!=null)
	        	predicateList.add(cb.greaterThanOrEqualTo(from.get("matFecha").as(Timestamp.class), objetoDTO.getFechaDesde()));	        
	        else if(objetoDTO.getFechaHasta()!=null)
	        {
	        	
	        	predicateList.add(cb.lessThanOrEqualTo(from.get("matFecha").as(Timestamp.class),objetoDTO.getFechaHasta()));	        
	            
	        }
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<MatrimonioListDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SeguridadesException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}		
	}
	
}


