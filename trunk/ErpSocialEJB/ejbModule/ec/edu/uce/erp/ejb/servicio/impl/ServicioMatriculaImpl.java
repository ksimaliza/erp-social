package ec.edu.uce.erp.ejb.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParcialDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
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
		slf4jLogger.info("createOrUpdateMatricula");
		try {
		if(matriculaDTO.getRegCodigo()!=null)
			return matriculaFactoryDAO.getMatriculaDAOImpl().update(matriculaDTO);
		else
			return matriculaFactoryDAO.getMatriculaDAOImpl().create(matriculaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMatricula {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteMatricula(MatriculaDTO matriculaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteMatricula");
		try {
		if(matriculaDTO.getRegCodigo()!=null)
			matriculaFactoryDAO.getMatriculaDAOImpl().remove(matriculaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteMatricula {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public NivelDTO createOrUpdateNivel(NivelDTO nivelDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMatricula");
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
		slf4jLogger.info("deleteNivelParalelo");
		try {
		if(nivelParaleloDTO.getNpaCodigo()!=null)
			matriculaFactoryDAO.getNivelParaleloDAOImpl().remove(nivelParaleloDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteNivelParalelo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public NotaDTO createOrUpdateNota(NotaDTO notaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateNota");
		try {
		if(notaDTO.getNotCodigo()!=null)
			return matriculaFactoryDAO.getNotaDAOImpl().update(notaDTO);
		else
			return matriculaFactoryDAO.getNotaDAOImpl().create(notaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateNota {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteNota(NotaDTO notaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteNota");
		try {
		if(notaDTO.getNotCodigo()!=null)
			matriculaFactoryDAO.getNotaDAOImpl().remove(notaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteNota {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public ParaleloDTO createOrUpdateParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateParalelo");
		try {
		if(paraleloDTO.getParCodigo()!=null)
			return matriculaFactoryDAO.getParaleloDAOImpl().update(paraleloDTO);
		else
			return matriculaFactoryDAO.getParaleloDAOImpl().create(paraleloDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateParalelo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteParalelo");
		try {
		if(paraleloDTO.getParCodigo()!=null)
			matriculaFactoryDAO.getParaleloDAOImpl().remove(paraleloDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteParalelo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public ParcialDTO createOrUpdateParcial(ParcialDTO parcialDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateParcial");
		try {
		if(parcialDTO.getParCodigo()!=null)
			return matriculaFactoryDAO.getParcialDAOImpl().update(parcialDTO);
		else
			return matriculaFactoryDAO.getParcialDAOImpl().create(parcialDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateParcial {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteParcial(ParcialDTO parcialDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteParcial");
		try {
		if(parcialDTO.getParCodigo()!=null)
			matriculaFactoryDAO.getParcialDAOImpl().remove(parcialDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteParcial {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public PeriodoDTO createOrUpdatePeriodo(PeriodoDTO periodoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdatePeriodo");
		try {
		if(periodoDTO.getPerCodigo()!=null)
			return matriculaFactoryDAO.getPeriodoDAOImpl().update(periodoDTO);
		else
			return matriculaFactoryDAO.getPeriodoDAOImpl().create(periodoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdatePeriodo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deletePeriodo(PeriodoDTO periodoDTO) throws SeguridadesException
	{
		slf4jLogger.info("deletePeriodo");
		try {
		if(periodoDTO.getPerCodigo()!=null)
			matriculaFactoryDAO.getPeriodoDAOImpl().remove(periodoDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deletePeriodo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public ProfesorDTO createOrUpdateProfesor(ProfesorDTO profesorDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateProfesor");
		try {
		if(profesorDTO.getProCodigo()!=null)
			return matriculaFactoryDAO.getProfesorDAOImpl().update(profesorDTO);
		else
			return matriculaFactoryDAO.getProfesorDAOImpl().create(profesorDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateProfesor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteProfesor(ProfesorDTO profesorDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteProfesor");
		try {
		if(profesorDTO.getProCodigo()!=null)
			matriculaFactoryDAO.getProfesorDAOImpl().remove(profesorDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteProfesor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public RepresentanteDTO createOrUpdateRepresentante(RepresentanteDTO representanteDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateRepresentante");
		try {
		if(representanteDTO.getRepCodigo()!=null)
			return matriculaFactoryDAO.getRepresentanteDAOImpl().update(representanteDTO);
		else
			return matriculaFactoryDAO.getRepresentanteDAOImpl().create(representanteDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateRepresentante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteRepresentante(RepresentanteDTO representanteDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteRepresentante");
		try {
		if(representanteDTO.getRepCodigo()!=null)
			matriculaFactoryDAO.getRepresentanteDAOImpl().remove(representanteDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteRepresentante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
}
