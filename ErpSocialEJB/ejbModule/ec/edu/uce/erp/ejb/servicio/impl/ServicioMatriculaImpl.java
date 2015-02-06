package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteAsignadoVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteRepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepNivelEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.TipoNotaDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.persistence.vo.MatriculaVO;
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
	public AsinacionDTO createOrUpdateAsinacion(AsinacionDTO asinacionDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateAsinacion");
		
		try {
			//lleno el atribuno Nivel Paralelo para insertar asiganacion
			asinacionDTO.setMatNivelParalelo(matriculaFactoryDAO.getNivelParaleloDAOImpl().getAll(asinacionDTO.getMatNivelParalelo()).get(0));
			if(asinacionDTO.getAsiCodigo()!=null){
				return matriculaFactoryDAO.getAsinacionDAOImpl().update(asinacionDTO);									
			}
			else {
				return matriculaFactoryDAO.getAsinacionDAOImpl().create(asinacionDTO);				
			} 
		}		
		catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateAsinacion {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
		
	@Override
	public List<AsinacionListDTO> readAsinacion(AsinacionListDTO asinacion) throws SeguridadesException {
		slf4jLogger.info("readAsinacion");
		List<AsinacionListDTO> listResultado = null;
		try {
			listResultado = matriculaFactoryDAO.getAsinacionDAOImpl().findAll(asinacion);
		} catch (Exception e) {
			slf4jLogger.info("Error al readAsinacion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener readAsinacion de la base de datos");
		}
		return listResultado;
	}
	
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public EstudianteDTO createOrUpdateEstudiante(EstudianteVO estudiantevo) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateEstudiante");
		EstudianteDTO estudiante;
		Persona personanueva,personaRepresentante;
		Persona madrePersona;
		Persona padrePersona;
		Persona representantePersona;
		List<Persona> listPersona;
		
		try {
			
			madrePersona = estudiantevo.getMadre();
			if(madrePersona.getPerPk()!=null )
			{
				madrePersona=factoryDAO.getPersonaDAOImpl().update(madrePersona);
			}
				else	
				{
					listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(madrePersona);
					if(listPersona.size()<=0)
						madrePersona=factoryDAO.getPersonaDAOImpl().create(madrePersona);
					else
						madrePersona=factoryDAO.getPersonaDAOImpl().update(listPersona.get(0));
				}
			
			padrePersona = estudiantevo.getPadre();
			if(padrePersona.getPerPk()!=null )
			{
				padrePersona=factoryDAO.getPersonaDAOImpl().update(padrePersona);
			}
				else	
				{
					listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(padrePersona);
					if(listPersona.size()<=0)
						padrePersona=factoryDAO.getPersonaDAOImpl().create(padrePersona);
					else
						padrePersona=factoryDAO.getPersonaDAOImpl().update(listPersona.get(0));
				}
			
			
			
			representantePersona = estudiantevo.getRepresentanteEst();
			if(representantePersona.getPerPk()!=null )
			{
				representantePersona=factoryDAO.getPersonaDAOImpl().update(representantePersona);
			}
				else	
				{
					listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(representantePersona);
					if(listPersona.size()<=0)
						representantePersona=factoryDAO.getPersonaDAOImpl().create(representantePersona);
					else
						representantePersona=factoryDAO.getPersonaDAOImpl().update(listPersona.get(0));
				}
			
			
			estudiantevo.getEstudiante().setEstPadre(padrePersona.getPerPk());
			estudiantevo.getEstudiante().setEstMadre(madrePersona.getPerPk());
			estudiantevo.getEstudiante().setEstRepresentante(representantePersona.getPerPk());
			
			
			if(estudiantevo.getEstudiante().getEstCodigo()!=null){
				slf4jLogger.info("Update");
				personanueva= factoryDAO.getPersonaDAOImpl().update(estudiantevo.getPersona());
				estudiantevo.getEstudiante().setEstPersona(personanueva.getPerPk());
				return matriculaFactoryDAO.getEstudianteDAOImpl().update(estudiantevo.getEstudiante());
			}
			else{
				slf4jLogger.info("Insert");
				EstudianteListDTO est=new EstudianteListDTO();
				est.setPerCi(estudiantevo.getPersona().getPerCi());
				if(!matriculaFactoryDAO.getEstudianteListDAOImpl().getByAnd(est).isEmpty())
					throw new SeguridadesException("El estudiante ya existe");
				estudiantevo.getEstudiante().setEstEstado("Inscrito");
				if(factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(estudiantevo.getPersona()).isEmpty())
					personanueva= factoryDAO.getPersonaDAOImpl().create(estudiantevo.getPersona());
				else
					personanueva=factoryDAO.getPersonaDAOImpl().update(estudiantevo.getPersona());
				
				estudiantevo.getEstudiante().setEstPersona(personanueva.getPerPk());
				
				estudiante=matriculaFactoryDAO.getEstudianteDAOImpl().create(estudiantevo.getEstudiante());
				
				/*if(factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(estudiantevo.getPersonaRepresentante()).isEmpty())
					personaRepresentante= factoryDAO.getPersonaDAOImpl().create(estudiantevo.getPersonaRepresentante());
				else
					personaRepresentante= factoryDAO.getPersonaDAOImpl().update(estudiantevo.getPersonaRepresentante());
				
				RepresentanteListDTO repre=new RepresentanteListDTO();
				repre.setPerCi(personaRepresentante.getPerCi());
				RepresentanteDTO rep=new RepresentanteDTO();
				List<RepresentanteListDTO> repList=matriculaFactoryDAO.getRepresentanteDAOImpl().getByAnd(repre);
				if(repList.isEmpty())
				{
					rep.setRepEmpresa(estudiantevo.getEstudiante().getEstEmpresa());
					rep.setRepPersona(personaRepresentante.getPerPk());
					rep=matriculaFactoryDAO.getRepresentanteDAOImpl().create(rep);
				}
				else
				{
					repre=repList.get(0);
					rep=matriculaFactoryDAO.getRepresentanteDAOImpl().find(repre.getRepCodigo());
				}	
				
				

				EstudianteRepresentanteDTO estRep=new EstudianteRepresentanteDTO();
				estRep.setEsrEmpresa(estudiantevo.getEstudiante().getEstEmpresa());
				estRep.setMatEstudiante(estudiante);
				estRep.setMatRepresentante(rep);
				
				matriculaFactoryDAO.getEstudianteRepresentanteDAOImpl().create(estRep);
				*/
				
				return estudiante;
				
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
	public EstudianteVO obtenerEstudiantePorId(Integer Idpersona, Integer Idestudiante,Integer IdMadre, Integer IdPadre, Integer IdRepresentante ) throws SeguridadesException {
		slf4jLogger.info("obtenerEstudiantePorId");
		
		EstudianteVO est=new EstudianteVO();
		
		est.setEstudiante(matriculaFactoryDAO.getEstudianteDAOImpl().find(Idestudiante));
		est.setPersona(factoryDAO.getPersonaDAOImpl().find(Idpersona));
		
		est.setPadre(factoryDAO.getPersonaDAOImpl().find(IdPadre));
		est.setMadre(factoryDAO.getPersonaDAOImpl().find(IdMadre));
		est.setRepresentanteEst(factoryDAO.getPersonaDAOImpl().find(IdRepresentante));
		
		return est;
	}

	
	
	@Override
	public MateriaDTO createOrUpdateMateria(MateriaDTO materiaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMateria");
		try {
			
			if (existeMateria(materiaDTO)) {
				throw new SeguridadesException("Ya se encuentra registrada la Materia-"+materiaDTO.getMtrNombe());
			}	
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
	public MateriaDTO obtenerMateriaPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerMateriaPorId");
		
		MateriaDTO mat=new MateriaDTO();
		
		mat = matriculaFactoryDAO.getMateriaDAOImpl().find(id);
		
		return mat;
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
	public List<MatriculaDTO> readMatricula(MatriculaDTO matricula) throws SeguridadesException {
		slf4jLogger.info("readMatricula");
		List<MatriculaDTO> resultado = null;
		try {
			resultado = matriculaFactoryDAO.getMatriculaDAOImpl().getAll(matricula);
		} catch (Exception e) {
			slf4jLogger.info("Error al readAsinacion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener readMatricula de la base de datos");
		}
		return resultado;
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
	public List<NivelDTO> buscarNivel(NivelDTO nivelDTO) throws SeguridadesException {
		slf4jLogger.info("buscarNivel");
		List<NivelDTO> listnivel = null;
		try {
			listnivel = matriculaFactoryDAO.getNivelDAOImpl().obtenerNivel(nivelDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarNivel {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarNivel de la base de datos");
		}
		return listnivel;
	}
	
	@Override
	public NivelDTO obtenerNivelPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerNivelPorId");
		
		NivelDTO nivel=new NivelDTO();
		
		nivel = matriculaFactoryDAO.getNivelDAOImpl().find(id);
		
		return nivel;
	}
	
	@Override
	public NivelParaleloDTO createOrUpdateNivelParalelo(NivelParaleloDTO nivelParaleloDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateNivelParalelo");
		NivelDTO nivel;
		ParaleloDTO paralelo;
		
		try {
			if(nivelParaleloDTO.getNpaCodigo()!=null){
				nivel = matriculaFactoryDAO.getNivelDAOImpl().find(nivelParaleloDTO.getMatNivel().getNivCodigo());
				paralelo = matriculaFactoryDAO.getParaleloDAOImpl().find(nivelParaleloDTO.getMatParalelo().getParCodigo());
				nivelParaleloDTO.setMatNivel(nivel);
				nivelParaleloDTO.setMatParalelo(paralelo);
				return matriculaFactoryDAO.getNivelParaleloDAOImpl().update(nivelParaleloDTO);
			}
			else {
				nivel = matriculaFactoryDAO.getNivelDAOImpl().find(nivelParaleloDTO.getMatNivel().getNivCodigo());
				paralelo = matriculaFactoryDAO.getParaleloDAOImpl().find(nivelParaleloDTO.getMatParalelo().getParCodigo());
				nivelParaleloDTO.setMatNivel(nivel);
				nivelParaleloDTO.setMatParalelo(paralelo);
				return matriculaFactoryDAO.getNivelParaleloDAOImpl().create(nivelParaleloDTO);
				
			} 
		}		
		catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateNivelParalelo {}", e.toString());
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
	public List<NivelParaleloDTO> buscarNivelParalelo(NivelParaleloDTO nivelParalelo) throws SeguridadesException {
		slf4jLogger.info("buscarNivelParalelo");
		List<NivelParaleloDTO> listResultado = null;
		try {
			listResultado = matriculaFactoryDAO.getNivelParaleloDAOImpl().getAll(nivelParalelo);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarNivelParalelo de la base de datos");
		}
		return listResultado;
	}
	
	
	
	
	
	
	@Override
	public NivelParaleloDTO obtenerNivelParaleloPorId(Integer idNivel, Integer idParalelo) throws SeguridadesException {
		slf4jLogger.info("obtenerRepresentantePorId");
		
		NivelParaleloDTO niv=new NivelParaleloDTO();
		
		niv.setMatNivel(matriculaFactoryDAO.getNivelDAOImpl().find(idNivel));
		niv.setMatParalelo(matriculaFactoryDAO.getParaleloDAOImpl().find(idParalelo));
		
		return  niv;
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
	public List<ParaleloDTO> buscarParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException {
		slf4jLogger.info("buscarParalelo");
		List<ParaleloDTO> listparalelo = null;
		try {
			listparalelo = matriculaFactoryDAO.getParaleloDAOImpl().obtenerParalelo(paraleloDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarMateria {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener las Materias de la base de datos");
		}
		
		return listparalelo;
	}
	
	@Override
	public ParaleloDTO obtenerParaleloPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerParaleloPorId");
		
		ParaleloDTO par=new ParaleloDTO();
		
		par = matriculaFactoryDAO.getParaleloDAOImpl().find(id);
		
		return par;
	}
	
	@Override
	public  TipoNotaDTO createOrUpdateParcial(TipoNotaDTO tipoNotaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateParcial");
		try {
		if(tipoNotaDTO.getParCodigo()!=null)
			return matriculaFactoryDAO.getParcialDAOImpl().update(tipoNotaDTO);
		else
			return matriculaFactoryDAO.getParcialDAOImpl().create(tipoNotaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateParcial {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteParcial(TipoNotaDTO tipoNotaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteParcial");
		try {
		if(tipoNotaDTO.getParCodigo()!=null)
			matriculaFactoryDAO.getParcialDAOImpl().remove(tipoNotaDTO);		
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
	public List<PeriodoDTO> buscarPeriodo(PeriodoDTO periodoDTO) throws SeguridadesException {
		slf4jLogger.info("buscarPeriodo");
		List<PeriodoDTO> listperiodo = null;
		try {
			listperiodo = matriculaFactoryDAO.getPeriodoDAOImpl().obtenerPeriodo(periodoDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPeriodo {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarPeriodo de la base de datos");
		}
		
		return listperiodo;
	}
	
	@Override
	public PeriodoDTO obtenerPeriodoPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerPeriodoPorId");
		
		PeriodoDTO per=new PeriodoDTO();
		
		per = matriculaFactoryDAO.getPeriodoDAOImpl().find(id);
		
		return per;
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
			DocenteListDTO doc=new DocenteListDTO();
			doc.setPerCi(profesorVO.getPersona().getPerCi());
			if(!matriculaFactoryDAO.getProfesorDAOImpl().getByAnd(doc).isEmpty())
				throw new SeguridadesException("El profesor ya existe");

			if(factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(profesorVO.getPersona()).isEmpty())
				persona= factoryDAO.getPersonaDAOImpl().create(profesorVO.getPersona());
			else
				persona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(profesorVO.getPersona()).get(0);
			
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
	public ProfesorVO obtenerDocentePorId(Integer Idpersona, Integer Iddocente) throws SeguridadesException {
		slf4jLogger.info("obtenerDocentePorId");
		
		ProfesorVO pro=new ProfesorVO();
		
		pro.setProfesor(matriculaFactoryDAO.getProfesorDAOImpl().find(Iddocente));
		pro.setPersona(factoryDAO.getPersonaDAOImpl().find(Idpersona));
		return pro;
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
	
	
	@Override
	public RepresentanteVO obtenerRepresentantePorId(Integer Idpersona, Integer Idrepresentante) throws SeguridadesException {
		slf4jLogger.info("obtenerRepresentantePorId");
		
		RepresentanteVO rep=new RepresentanteVO();
		
		rep.setRepresentante(matriculaFactoryDAO.getRepresentanteDAOImpl().find(Idrepresentante));
		rep.setPersona(factoryDAO.getPersonaDAOImpl().find(Idpersona));
		return rep;
	}


	@Override
	public AsinacionDTO createOrUpdateAsignacion(AsinacionDTO asinacionDTO)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void deleteAsinacion(AsinacionDTO asinacionDTO)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<AsinacionDTO> buscarAsinacion(AsinacionDTO asinacion)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public AsinacionDTO buscarAsinacionid(Integer id)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void createOrUpdateMatricula(MatriculaVO matriculaVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMatricula");
			
		AsinacionDTO asinacion;
		MatriculaDetalleDTO matriculaDetalle;
		try {
			
			MatriculaDTO mat= matriculaFactoryDAO.getMatriculaDAOImpl().create(matriculaVO.getMatricula());
			for(AsinacionListDTO asig:matriculaVO.getAsignacion())
			{
				matriculaDetalle= new MatriculaDetalleDTO();
				asinacion=matriculaFactoryDAO.getAsinacionDAOImpl().find(asig.getAsiCodigo());
				matriculaDetalle.setMatAsinacion(asinacion);
				matriculaDetalle.setMatMatriculaBean(mat);
				matriculaFactoryDAO.getMatriculaDetalleDAOImpl().create(matriculaDetalle);
			}
		}		
		catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMatricula {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public List<RepNivelEstudianteDTO> readNivelEstudiante(RepNivelEstudianteDTO repNivelEstudianteDTO) throws SeguridadesException
	{
		slf4jLogger.info("buscarRepresentante");
		List<RepNivelEstudianteDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getRepNivelEstudianteDAOImpl().getByAnd(repNivelEstudianteDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarRepresentante {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
	@Override
	public List<MatriculaVieDTO> readCarnet(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readCarnet");
		List<MatriculaVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getMatriculaVieDAOImpl().getByAndDistinct(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readCarnet {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
	@Override
	public List<MatriculaVieDTO> readRepCarnetBuscar(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readRepNivelParalelo");
		List<MatriculaVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getMatriculaVieDAOImpl().getRepNivelParalelo(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readRepNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
	@Override
	public List<MatriculaVieDTO> readRepComprobanteMatricula(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readRepNivelParalelo");
		List<MatriculaVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getMatriculaVieDAOImpl().getRepNivelParalelo(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readRepNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
	
	
	@Override
	public List<MatriculaVieDTO> readRepNivelParalelo(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readRepNivelParalelo");
		List<MatriculaVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getMatriculaVieDAOImpl().getRepNivelParalelo(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readRepNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
	@Override
	public List<DocenteAsignadoVieDTO> readDocenteAsignado(DocenteAsignadoVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readRepNivelParalelo");
		List<DocenteAsignadoVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getDocenteAsignadoVieDAOImpl().getByAndDistinct(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readRepNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	@Override
	public List<EstudianteRepresentanteDTO> readRepresentante(EstudianteDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readRepNivelParalelo");
		List<EstudianteRepresentanteDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getEstudianteRepresentanteDAOImpl().getByStudent(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readRepNivelParalelo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	@Override
	public void updateEstadoEstudiante(Integer codEstudiante) throws SeguridadesException
	{
		slf4jLogger.info("updateEstadoEstudiante");
		
		try {
			matriculaFactoryDAO.getEstudianteDAOImpl().updateEstadoEstudiante(codEstudiante);
		} catch (Exception e) {
			slf4jLogger.info("Error al updateEstadoEstudiante {}", e.getMessage());
			throw new SeguridadesException("No se pudo actualizar la base de datos");
		}
		
		
	}
	
	@Override
	public void eliminarEstudiante(Integer codEstudiante) throws SeguridadesException
	{
		slf4jLogger.info("eliminarEstudiante");
		
		try {
			matriculaFactoryDAO.getEstudianteDAOImpl().eliminarEstudiante(codEstudiante);
		} catch (Exception e) {
			slf4jLogger.info("Error al eliminarEstudiante {}", e.getMessage());
			throw new SeguridadesException("No se pudo eliminar de la  base de datos");
		}
		
		
	}
	
	@Override
	public void eliminarMateria(Integer codMateria) throws SeguridadesException
	{
		slf4jLogger.info("eliminarMateria");
		
		try {
			matriculaFactoryDAO.getMateriaDAOImpl().eliminarMateria(codMateria);
		} catch (Exception e) {
			slf4jLogger.info("Error al eliminarMateria {}", e.getMessage());
			throw new SeguridadesException("No se pudo eliminar de la  base de datos");
		}
		
		
	}


	@Override
	public EstudianteVO obtenerEstudiantePorId(Integer Idpersona,
			Integer Idestudiante) throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Persona> readRepresentante(Persona objectDTO)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EstudianteVO obtenerEstudiantePorId(Integer Idpersona,
			Integer Idestudiante, Integer IdMadre, Integer IdPadre)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<MatriculaVieDTO> readFicha(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		slf4jLogger.info("readFicha");
		List<MatriculaVieDTO> lista = null;
		try {
			lista = matriculaFactoryDAO.getMatriculaVieDAOImpl().getFichaEstudiante(objectDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFicha {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener datos de la base de datos");
		}
		
		return lista;
	}
	
	
private Boolean existeMateria (MateriaDTO materia) throws SeguridadesException {
		MateriaDTO materiaFind = new MateriaDTO();
		materiaFind.setMtrEmpresa(materia.getMtrEmpresa());
		materiaFind.setMtrNombe(materia.getMtrNombe());
		
		List<MateriaDTO> listMateriaDTO = matriculaFactoryDAO.getMateriaDAOImpl().obtenerMateria(materiaFind);
		if (CollectionUtils.isEmpty(listMateriaDTO)) {
			return Boolean.FALSE;
		} 
		
		return Boolean.TRUE;
		
	}

/**
 * Devuelve el anio lectivo que se encuentra activo. Se asume que solo un
 * anio lectivo esta activo en un periodo de tiempo
 * 
 * @return PeriodoDTO
 * @throws SeguridadesException
 */
public PeriodoDTO obtenerAnioLectivoVigente(Integer idEmpresa) throws SeguridadesException {
	slf4jLogger.info("obtenerAnioLectivoVigente");

	try {
		PeriodoDTO periodoDTO = matriculaFactoryDAO.getPeriodoDAOImpl().find(matriculaFactoryDAO.getPeriodoDAOImpl().obtenerIdUltimoPeriodo(idEmpresa));

		return periodoDTO;
	} catch (Exception e) {
		slf4jLogger.info("error al obtenerAnioLectivoVigente {}", e.toString());
		throw new SeguridadesException(e);
	}
}


@Override
public PeriodoDTO obtenerAnioLectivoVigente() throws SeguridadesException {
	// TODO Auto-generated method stub
	return null;
}


	
	
}
