package ec.edu.uce.erp.ejb.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.AsistenciaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;

@Stateless
public class ServicioAsistenciaImpl implements ServicioAsistencia{
	@EJB
	private AsistenciaFactoryDAO asistenciaFactoryDAO;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioAsistenciaImpl.class);
	
	
	
}
