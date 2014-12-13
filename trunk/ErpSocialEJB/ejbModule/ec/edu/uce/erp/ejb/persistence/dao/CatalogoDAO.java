package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;

public interface CatalogoDAO extends AbstractFacade<CatalogoEucaristiaDTO>{

	List<CatalogoEucaristiaDTO> getAll(CatalogoEucaristiaDTO catalogo);
	List<CatalogoEucaristiaDTO> obtenerSeccionNicho(CatalogoEucaristiaDTO seccionNichoDTO) throws SeguridadesException;
}
