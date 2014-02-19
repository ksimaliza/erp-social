/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistoricoTransaccione;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaDTO;

/**
 * @author
 * 
 */
public class HistoricoTransaccioneDAOImpl extends AbstractFacadeImpl<HistoricoTransaccione> implements HistoricoTransaccioneDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(HistoricoTransaccioneDAOImpl.class);
	
	public HistoricoTransaccioneDAOImpl () {}
	
	public HistoricoTransaccioneDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void registrarHistoricoTransaccion(AuditoriaDTO auditoriaDTO) throws SeguridadesException {

		slf4jLogger.info("metodo registrarHistoricoTransaccion");
		
		if (auditoriaDTO != null) {
			HistoricoTransaccione historicoTransacciones = new HistoricoTransaccione();
			historicoTransacciones.setFechaTransaccion(UtilAplication.obtenerFechaActual());
			historicoTransacciones.setNombreTransaccion(auditoriaDTO.getAccionClase());
			historicoTransacciones.setCabCatalogoTipoTransaccion(CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_TRANSACCION);
			historicoTransacciones.setDetCatalogoTipoTransaccion(auditoriaDTO.getIdTipoTransaccion());
			historicoTransacciones.setSegtUsuario(new Usuario());
			historicoTransacciones.getSegtUsuario().setIdUsuario(auditoriaDTO.getIdUsuario());
			this.create(historicoTransacciones);
		}
		
	}

}
