package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.persistence.vo.ProfesorVO;
import ec.edu.uce.erp.ejb.persistence.vo.RepresentanteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;

@Stateless
public class ServicioMatriculaImpl implements ServicioMatricula{

	@EJB
	private MatriculaFactoryDAO matriculaFactoryDAO;
	
	@EJB
	private FactoryDAO factoryDAO;
	
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
	public List<AsinacionDTO> buscarAsinacion(AsinacionDTO asinacion) throws SeguridadesException {
		slf4jLogger.info("buscarAsinacion");
		List<AsinacionDTO> listAsinacion = null;
		try {
			//listAsinacion = matriculaFactoryDAO.getAsinacionDAOImpl().obtenerAsinacion(asinacion);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarAsinacion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener las empresas de la base de datos");
		}
		return listAsinacion;
	}
	
	@Override
	public AsinacionDTO buscarAsinacionid(Integer id) throws SeguridadesException {
		slf4jLogger.info("buscarAsinacionid");
		AsinacionDTO asinacionencontrada;
		try {
			asinacionencontrada = matriculaFactoryDAO.getAsinacionDAOImpl().find(id);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarAsinacion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener las asinaciones de la base de datos");
		}
		return asinacionencontrada;
	}
	
	
	@Override
	public EstudianteDTO createOrUpdateEstudiante(EstudianteVO estudiantevo) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateEstudiante");
		Persona personanueva;
		try {
		if(estudiantevo.getEstudiante().getEstCodigo()!=null){
			personanueva= factoryDAO.getPersonaDAOImpl().update(estudiantevo.getPersona());
			return matriculaFactoryDAO.getEstudianteDAOImpl().update(estudiantevo.getEstudiante());
		}
		else{
			personanueva= factoryDAO.getPersonaDAOImpl().create(estudiantevo.getPersona());
			estudiantevo.getEstudiante().setEstPersona(personanueva.getPerPk());
			return matriculaFactoryDAO.getEstudianteDAOImpl().create(estudiantevo.getEstudiante());
		}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	
	@Override
	public void deleteEstudiante(EstudianteVO estudianteVO) throws SeguridadesException
	{
		slf4jLogger.info("deleteEstudiante");
		try {
		if(estudianteVO.getEstudiante().getEstCodigo()!=null){
			factoryDAO.getPersonaDAOImpl().remove(estudianteVO.getPersona());
			matriculaFactoryDAO.getEstudianteDAOImpl().remove(estudianteVO.getEstudiante());		
		}
			else {
			throw new SeguridadesException("no existe una coincidencia");
		}} catch (Exception e) {
			slf4jLogger.info("error al deleteEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public List<EstudianteListDTO> buscarEstudiante(EstudianteListDTO estudianteListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarEstudiante");
		List<EstudianteListDTO> listestudiantes = null;
		try {
			listestudiantes = matriculaFactoryDAO.getEstudianteDAOImpl().obtenerEstudiante(estudianteListDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarEstudiante {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los Estudiantes de la base de datos");
		}
		
		return listestudiantes;
	}
	
	@Override
	public EstudianteVO obtenerEstudiantePorId(Integer idPersona, Integer idEstudiante) throws SeguridadesException {
		slf4jLogger.info("obtenerEstudiantePorId");
		
		EstudianteVO est=new EstudianteVO();
		
		est.setEstudiante(matriculaFactoryDAO.getEstudianteDAOImpl().find(idEstudiante));
		est.setPersona(factoryDAO.getPersonaDAOImpl().find(idPersona));
		return est;
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
	public List<MateriaDTO> buscarMateria(MateriaDTO materiaDTO) throws SeguridadesException {
		slf4jLogger.info("buscarMateria");
		List<MateriaDTO> listmateria = null;
		try {
			listmateria = matriculaFactoryDAO.getMateriaDAOImpl().obtenerMateria(materiaDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarMateria {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener las Materias de la base de datos");
		}
		
		return listmateria;
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
	public ProfesorDTO createOrUpdateProfesor(ProfesorVO profesorVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateProfesor");
		
		try {
		Persona persona;
		if(profesorVO.getProfesor().getProCodigo()!=null){
			persona = factoryDAO.getPersonaDAOImpl().update(profesorVO.getPersona());
			return matriculaFactoryDAO.getProfesorDAOImpl().update(profesorVO.getProfesor());
		}
		else
		{
			persona = factoryDAO.getPersonaDAOImpl().create(profesorVO.getPersona());
			profesorVO.getProfesor().setProPersona(persona.getPerPk());	
			return matriculaFactoryDAO.getProfesorDAOImpl().create(profesorVO.getProfesor());
		}} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateProfesor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteProfesor(ProfesorVO profesorVO) throws SeguridadesException
	{
		slf4jLogger.info("deleteProfesor");
		try {
		if(profesorVO.getProfesor().getProCodigo()!=null){
			factoryDAO.getPersonaDAOImpl().remove(profesorVO.getPersona());
			matriculaFactoryDAO.getProfesorDAOImpl().remove(profesorVO.getProfesor());		
		}
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteProfesor {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public List<DocenteListDTO> buscarProfesor(DocenteListDTO docenteListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarProfesor");
		List<DocenteListDTO> listdocentes = null;
		try {
			listdocentes = matriculaFactoryDAO.getProfesorDAOImpl().obtenerDocente(docenteListDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarProfesor {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los Docentes de la base de datos");
		}
		
		return listdocentes;
	}
	
	
	@Override
	public RepresentanteDTO createOrUpdateRepresentante(RepresentanteVO representanteVO) throws SeguridadesException
	{
		Persona persona;
		slf4jLogger.info("createOrUpdateRepresentante");
		try {
		if(representanteVO.getRepresentante().getRepCodigo()!=null){
			persona = factoryDAO.getPersonaDAOImpl().update(representanteVO.getPersona());
			return matriculaFactoryDAO.getRepresentanteDAOImpl().update(representanteVO.getRepresentante());
		}
			else
		{
			persona = factoryDAO.getPersonaDAOImpl().create(representanteVO.getPersona());
			representanteVO.getRepresentante().setRepPersona(persona.getPerPk());
			return matriculaFactoryDAO.getRepresentanteDAOImpl().create(representanteVO.getRepresentante());
		}} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateRepresentante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public List<RepresentanteListDTO> buscarRepresentante(RepresentanteListDTO representanteListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarRepresentante");
		List<RepresentanteListDTO> listrepresentantes = null;
		try {
			listrepresentantes = matriculaFactoryDAO.getRepresentanteDAOImpl().obtenerRepresentante(representanteListDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarRepresentante {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los Estudiantes de la base de datos");
		}
		
		return listrepresentantes;
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
