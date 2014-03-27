/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.LineaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;

/**
 * @author 
 *
 */
public class LineaBienDAOImpl extends AbstractFacadeImpl<LineaBien> implements LineaBienDAO{
	
	public LineaBienDAOImpl() {}
	
	public LineaBienDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
