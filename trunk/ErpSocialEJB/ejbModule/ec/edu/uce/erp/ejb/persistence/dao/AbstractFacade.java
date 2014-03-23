package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

/**
 * Interfaz generica para administrar la pricipales operaciones hacia la BD
 * @author 
 *
 * @param <T>
 */
public interface AbstractFacade<T> {
	
	/**
	 * Crear una entidad en la BD
	 * @param entity
	 * @return
	 */
	T create(T entity);
	
	/**
	 * Actualizar una entidad en la BD
	 * @param entity
	 * @return
	 */
	T update(T entity);
	
	/**
	 * Eliminar una entidad en la BD
	 * @param entity
	 */
	void remove(T entity);
	
	/**
	 * BUscar una entidad por id en la BD
	 * @param id
	 * @return
	 */
	T find(Object id);
	
	/**
	 * Busca todas las entidades disponibles de una entidad de la BD
	 * @return lista de entidades encontradas
	 */
	List<T> buscarTodos();

}
