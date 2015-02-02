package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.DatosEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotaSuspensa;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotasParcial;
import ec.edu.uce.erp.ejb.persistence.util.dto.MateriaEstadoPacialesDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.MatriculaNotasTutorDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ReporteDTO;

@Local
public interface ServicioNotas {

	public PeriodoDTO obtenerAnioLectivoVigente() throws SeguridadesException;

	public List<AsinacionDTO> obtenerAsignacionesPorPeriodoProfesor(Integer codPeriodo, Integer codProfesor) throws SeguridadesException;

	public List<MateriaEstadoPacialesDTO> establecerEstadosNotasPasadas(List<AsinacionDTO> asinacionDTOs);

	public List<EstudianteNotasParcial> obtenerEstudiantesNotasParcial(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO);

	public NotaDTO guardarNota(NotaDTO notaDTO);

	public NotaDTO generarNotaQuimestre(MatriculaDetalleDTO matriculaDetalleDTO, Integer quimestre, NotaDTO notaDTOExamen);

	public DatosEstudianteDTO obtenerEstudianteMatriculas(String cedulaEstudiante, Integer codPeriodo) throws SeguridadesException;

	public ReporteDTO obtenerDatosReporteQuimestralEstudiantes(DatosEstudianteDTO datosEstudianteDTO, Integer codQuimestre) throws SeguridadesException;

	public List<AsinacionDTO> obtenerAsignacionesPorPeriodo(Integer codPeriodo) throws SeguridadesException;

	public ReporteDTO generarReporteGeneralPorParalelo(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria) throws SeguridadesException;

	public ReporteDTO generarReporteDetalleParcial(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria, Integer codQuimestre, Integer codParcial) throws SeguridadesException;

	public ReporteDTO obtenerDatosReporteAnualEstudiantes(DatosEstudianteDTO datosEstudianteDTO) throws SeguridadesException;

	public List<EstudianteNotasParcial> obtenerEstudiantesNotasParcialCorreccion(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO, int codTipoNota);

	public List<MateriaEstadoPacialesDTO> establecerEstadosNotasPasadasCorreccion(List<AsinacionDTO> asinacionDTOs);

	public NotaDTO generarNotaQuimestreEdicion(MatriculaDetalleDTO matriculaDetalleDTO, Integer quimestre, NotaDTO notaDTOExamen);

	// FASE 2
	public List<AsinacionDTO> obtenerAsignacionesPorPeriodoComAsi(Integer codPeriodo) throws SeguridadesException;

	public List<MatriculaNotasTutorDTO> generarListaNotasTutor(List<AsinacionDTO> listaAsinacionesDTO, Integer codQuimestre) throws SeguridadesException;

	public void guardarNotasTutor(List<MatriculaNotasTutorDTO> matriculaNotasTutorDTOs) throws SeguridadesException;

	public List<EstudianteNotaSuspensa> obtenerDatosEstudiantesSuspensos(NivelDTO nivelDTOSeleccionado, ParaleloDTO paraleloDTOSeleccionado, Integer codMateriaSeleccionada,
			List<AsinacionDTO> asinacionDTOs, Integer codTipoSuspenso) throws SeguridadesException;

	public void guardarNotasSuspensas(List<EstudianteNotaSuspensa> listaEstudianteNotaSuspensas, Integer codTipoSuspenso) throws SeguridadesException;

}
