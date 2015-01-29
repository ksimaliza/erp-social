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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.SepulturaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;

public class SepulturaDAOImpl extends AbstractFacadeImpl<SepulturaDTO> implements SepulturaDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(SepulturaDAOImpl.class);
	
	public SepulturaDAOImpl() {
		super();
	
	}

	public SepulturaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<SepulturaListDTO> getByAnd(SepulturaListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<SepulturaListDTO> cq;
		Root<SepulturaListDTO> from;
		List<SepulturaListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(SepulturaListDTO.class);
			
			from= cq.from(SepulturaListDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = objetoDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objetoDTO, new Object[0]);
				
				    if(value!=null & value!="")
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<SepulturaListDTO> tq=entityManager.createQuery(cq);
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
	
	@Override
	public Date obtenerFechaMinSepultura(SepulturaListDTO sepultura) throws SeguridadesException {
		
		slf4jLogger.info("obtenerSepulturaPorFechaMin");
		Date fechaMin= null;
		
		try {
			fechaMin=(Date) entityManager.createQuery("select min(e.sepFecha) from SepulturaListDTO e where e.sepEmpresa=:idEmpresa")
			  .setParameter("idEmpresa", sepultura.getSepEmpresa())
			  .getSingleResult();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return fechaMin;
}
	@Override
	public Date obtenerFechaMaxSepultura(SepulturaListDTO sepultura) throws SeguridadesException {
		
		slf4jLogger.info("obtenerSepulturaPorFechaMax");
		Date fechaMax= null;
		
		try {
			fechaMax=(Date) entityManager.createQuery("select max(e.sepFecha) from SepulturaListDTO e where e.sepEmpresa=:idEmpresa")
			  .setParameter("idEmpresa", sepultura.getSepEmpresa())
			  .getSingleResult();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return fechaMax;
}
	
	@Override
	public List<SepulturaListDTO> getDistinctReporteSepulturaByAnd(SepulturaListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<SepulturaListDTO> cq;
		Root<SepulturaListDTO> from;
		List<SepulturaListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(SepulturaListDTO.class);
			
			from= cq.from(SepulturaListDTO.class);
			
			cq.multiselect(
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("seccion"),
					from.get("nniDescripcion"),
					from.get("tniDescripcion"),
					from.get("nicDescripcion"),
					from.get("sepFecha")
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
	        	predicateList.add(cb.between(from.get("sepFecha").as(Timestamp.class), objetoDTO.getFechaDesde(), objetoDTO.getFechaHasta()));	        
	        }else if(objetoDTO.getFechaDesde()!=null)
	        	predicateList.add(cb.greaterThanOrEqualTo(from.get("sepFecha").as(Timestamp.class), objetoDTO.getFechaDesde()));	        
	        else if(objetoDTO.getFechaHasta()!=null)
	        {
	        	
	        	predicateList.add(cb.lessThanOrEqualTo(from.get("sepFecha").as(Timestamp.class),objetoDTO.getFechaHasta()));	        
	            
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<SepulturaListDTO> tq=entityManager.createQuery(cq);
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
