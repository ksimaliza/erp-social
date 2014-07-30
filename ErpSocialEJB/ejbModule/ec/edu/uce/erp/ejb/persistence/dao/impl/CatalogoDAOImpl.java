package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.uce.erp.ejb.persistence.dao.CatalogoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;

public class CatalogoDAOImpl extends AbstractFacadeImpl<CatalogoEucaristiaDTO> implements CatalogoDAO{

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
			
}	



