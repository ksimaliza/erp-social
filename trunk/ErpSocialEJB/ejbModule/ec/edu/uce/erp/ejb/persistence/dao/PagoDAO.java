package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;

@Local
public interface PagoDAO  extends AbstractFacade<PagoDTO>{

	List<PagoContratoListDTO> getByAnd(PagoContratoListDTO objetoDTO)
			throws SeguridadesException;

}
