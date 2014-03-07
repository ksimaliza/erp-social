package ec.edu.uce.erp.ejb.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.EstudianteRepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.ParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;

@Stateless
public class ServicioMatriculaImpl implements ServicioMatricula{

	@EJB
	private MatriculaFactoryDAO matriculaFactoryDAO;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioMatriculaImpl.class);
	
	@Override
	public AsinacionDTO createOrUpdateAsignacion(AsinacionDTO asinacionDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateAsignacion");
		try {
		if(asinacionDTO.getAsiCodigo()!=null)
			return matriculaFactoryDAO.getAsinacionDAOImpl().update(asinacionDTO);
		else
			return matriculaFactoryDAO.getAsinacionDAOImpl().create(asinacionDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateAsignacion {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteAsinacion(AsinacionDTO asinacionDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteEstudiante");
		try {
		if(asinacionDTO.getAsiCodigo()!=null)
			matriculaFactoryDAO.getAsinacionDAOImpl().remove(asinacionDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	
	public EstudianteDTO createOrUpdateEstudiante(EstudianteDTO estudianteDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateEstudiante");
		try {
		if(estudianteDTO.getEstCodigo()!=null)
			return matriculaFactoryDAO.getEstudianteDAOImpl().update(estudianteDTO);
		else
			return matriculaFactoryDAO.getEstudianteDAOImpl().create(estudianteDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteEstudiante(EstudianteDTO estudianteDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteEstudiante");
		try {
		if(estudianteDTO.getEstCodigo()!=null)
			matriculaFactoryDAO.getEstudianteDAOImpl().remove(estudianteDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public MateriaDTO createOrUpdateMateria(MateriaDTO materiaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(materiaDTO.getMtrCodigo()!=null)
			return matriculaFactoryDAO.getMateriaDAOImpl().update(materiaDTO);
		else
			return matriculaFactoryDAO.getMateriaDAOImpl().create(materiaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteMateria(MateriaDTO materiaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(materiaDTO.getMtrCodigo()!=null)
			matriculaFactoryDAO.getMateriaDAOImpl().remove(materiaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public MatriculaDTO createOrUpdateMatricula(MatriculaDTO matriculaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(matriculaDTO.getRegCodigo()!=null)
			return matriculaFactoryDAO.getMatriculaDAOImpl().update(matriculaDTO);
		else
			return matriculaFactoryDAO.getMatriculaDAOImpl().create(matriculaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteMatricula(MatriculaDTO matriculaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(matriculaDTO.getRegCodigo()!=null)
			matriculaFactoryDAO.getMatriculaDAOImpl().remove(matriculaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public NivelDTO createOrUpdateMatricula(NivelDTO nivelDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(nivelDTO.getNivCodigo()!=null)
			return matriculaFactoryDAO.getNivelDAOImpl().update(nivelDTO);
		else
			return matriculaFactoryDAO.getNivelDAOImpl().create(nivelDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteNivel(NivelDTO nivelDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(nivelDTO.getNivCodigo()!=null)
			matriculaFactoryDAO.getNivelDAOImpl().remove(nivelDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public NivelParaleloDTO createOrUpdateNivelParalelo(NivelParaleloDTO nivelParaleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(nivelParaleloDTO.getNpaCodigo()!=null)
			return matriculaFactoryDAO.getNivelParaleloDAOImpl().update(nivelParaleloDTO);
		else
			return matriculaFactoryDAO.getNivelParaleloDAOImpl().create(nivelParaleloDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteNivelParalelo(NivelParaleloDTO nivelParaleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(nivelParaleloDTO.getNpaCodigo()!=null)
			matriculaFactoryDAO.getNivelParaleloDAOImpl().remove(nivelParaleloDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public NotaDTO createOrUpdateNivelParalelo(NotaDTO notaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(notaDTO.getNotCodigo()!=null)
			return matriculaFactoryDAO.getNotaDAOImpl().update(notaDTO);
		else
			return matriculaFactoryDAO.getNotaDAOImpl().create(notaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteNota(NotaDTO notaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(notaDTO.getNotCodigo()!=null)
			matriculaFactoryDAO.getNotaDAOImpl().remove(notaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public ParaleloDTO createOrUpdateParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
		if(paraleloDTO.getParCodigo()!=null)
			return matriculaFactoryDAO.getParaleloDAOImpl().update(paraleloDTO);
		else
			return matriculaFactoryDAO.getParaleloDAOImpl().create(paraleloDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMateria");
		try {
		if(paraleloDTO.getParCodigo()!=null)
			matriculaFactoryDAO.getParaleloDAOImpl().remove(paraleloDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMateria {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
}
