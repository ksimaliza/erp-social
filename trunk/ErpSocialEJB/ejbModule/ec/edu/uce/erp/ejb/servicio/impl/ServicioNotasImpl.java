package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.MessagesApplicacion;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.TipoNotaDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ComparadorTipoNotas;
import ec.edu.uce.erp.ejb.persistence.util.dto.DatosEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.DatosReporteDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotasParcial;
import ec.edu.uce.erp.ejb.persistence.util.dto.MateriaEstadoPacialesDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ReporteDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.persistence.vo.ProfesorVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.ejb.servicio.ServicioNotas;

/**
 * Session Bean implementation class ServicioNotasImpl
 */
@Stateless
public class ServicioNotasImpl implements ServicioNotas {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioNotasImpl.class);

	@EJB
	private MatriculaFactoryDAO matriculaFactoryDAO;

	@EJB
	private FactoryDAO factoryDAO;

	@EJB
	private ServicioMatricula servicioMatricula;

	/**
	 * Default constructor.
	 */
	public ServicioNotasImpl() {
		super();
	}

	/**
	 * Devuelve el anio lectivo que se encuentra activo. Se asume que solo un
	 * anio lectivo esta activo en un periodo de tiempo
	 * 
	 * @return PeriodoDTO
	 * @throws SeguridadesException
	 */
	public PeriodoDTO obtenerAnioLectivoVigente() throws SeguridadesException {
		slf4jLogger.info("obtenerAnioLectivoVigente");

		try {
			PeriodoDTO periodoDTO = matriculaFactoryDAO.getPeriodoDAOImpl().find(matriculaFactoryDAO.getPeriodoDAOImpl().obtenerIdUltimoPeriodo());

			return periodoDTO;
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerAnioLectivoVigente {}", e.toString());
			throw new SeguridadesException(e);
		}
	}

	public List<AsinacionDTO> obtenerAsignacionesPorPeriodoProfesor(Integer codPeriodo, Integer codProfesor) throws SeguridadesException {
		slf4jLogger.info("obtenerAsignacionesPorPeriodoProfesor");

		try {
			List<AsinacionDTO> asinacionDTOs = matriculaFactoryDAO.getAsinacionDAOImpl().asignacionesPorPeriodoProfesor(codPeriodo, codProfesor);

			for (AsinacionDTO asinacionDTO : asinacionDTOs) {
				asinacionDTO.getMatMateria();
				asinacionDTO.getMatMatriculaDetalles();
				asinacionDTO.getMatNivelParalelo();
				asinacionDTO.getMatNivelParalelo().getMatNivel();
				asinacionDTO.getMatNivelParalelo().getMatParalelo();
				List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();

				for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
					matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante().getEstPersona();
					List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
					for (NotaDTO notaDTO : notaDTOs) {
						notaDTO.getNotValor();
						notaDTO.getTipoNotaBean().getParCodigo();
					}
				}
			}

			return asinacionDTOs;
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerAsignacionesPorPeriodoProfesor {}", e.toString());
			throw new SeguridadesException(e);
		}
	}

	public List<MateriaEstadoPacialesDTO> establecerEstadosNotasPasadas(List<AsinacionDTO> asinacionDTOs) {
		List<MateriaEstadoPacialesDTO> materiaEstadoPacialesDTOs = new ArrayList<MateriaEstadoPacialesDTO>();

		for (AsinacionDTO asinacionDTO : asinacionDTOs) {
			List<MatriculaDetalleDTO> matriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();
			for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDetalleDTOs) {
				List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
				if (notaDTOs == null || notaDTOs.isEmpty()) {
					MateriaEstadoPacialesDTO materiaEstadoPacialesDTO = new MateriaEstadoPacialesDTO();
					materiaEstadoPacialesDTO.setAsinacionDTO(asinacionDTO);

					materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
							MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.primer.quimestre")));
					if (!materiaEstadoPacialesDTOs.contains(materiaEstadoPacialesDTO)) {
						materiaEstadoPacialesDTOs.add(materiaEstadoPacialesDTO);
					}
				} else {
					Collections.sort(notaDTOs, new ComparadorTipoNotas());

					MateriaEstadoPacialesDTO materiaEstadoPacialesDTO = new MateriaEstadoPacialesDTO();
					materiaEstadoPacialesDTO.setAsinacionDTO(asinacionDTO);

					switch (notaDTOs.size()) {
					case 1:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.segundo.parcial.primer.quimestre")));
						break;
					case 2:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.tercer.parcial.primer.quimestre")));
						break;
					case 3:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre")));
						break;
					case 5:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.segundo.quimestre")));
						break;
					case 6:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.segundo.parcial.segundo.quimestre")));
						break;
					case 7:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.tercer.parcial.segundo.quimestre")));
						break;
					case 8:
						materiaEstadoPacialesDTO.setTipoNotaDTO(matriculaFactoryDAO.getParcialDAOImpl().find(
								MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre")));
						break;
					default:
						materiaEstadoPacialesDTO.setTipoNotaDTO(null);
						break;
					}
					// materiaEstadoPacialesDTO.setTipoNotaDTO(notaDTOs.get(notaDTOs.size()
					// - 1).getTipoNotaBean());
					if (!materiaEstadoPacialesDTOs.contains(materiaEstadoPacialesDTO)) {
						materiaEstadoPacialesDTOs.add(materiaEstadoPacialesDTO);
					}

				}
			}
		}

		return materiaEstadoPacialesDTOs;
	}

	public List<EstudianteNotasParcial> obtenerEstudiantesNotasParcial(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO) {
		List<EstudianteNotasParcial> listaEstudianteNotasParcial = new ArrayList<EstudianteNotasParcial>();

		List<MatriculaDetalleDTO> matriculaDetalleDTOs = materiaEstadoPacialesDTO.getAsinacionDTO().getMatMatriculaDetalles();

		for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDetalleDTOs) {
			EstudianteNotasParcial estudianteNotasParcial = new EstudianteNotasParcial();
			estudianteNotasParcial.setMatriculaDetalleDTO(matriculaDetalleDTO);

			EstudianteDTO estudianteDTO = matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante();
			estudianteNotasParcial.setEstudianteDTO(estudianteDTO);

			Persona persona = factoryDAO.getPersonaDAOImpl().find(estudianteDTO.getEstPersona());
			estudianteNotasParcial.setPersona(persona);

			NotaDTO notaDTO = new NotaDTO();
			notaDTO.setNotasDTOComponentes(new ArrayList<NotaDTO>());

			TipoNotaDTO tipoNotaDTOTareas = matriculaFactoryDAO.getParcialDAOImpl().find(MessagesApplicacion.getInteger("erp.notas.tipo.tareas"));
			NotaDTO notaDTOTareas = new NotaDTO();
			notaDTOTareas.setNotValor(0f);
			notaDTOTareas.setTipoNotaBean(tipoNotaDTOTareas);
			notaDTO.addNotaDTO(notaDTOTareas);

			TipoNotaDTO tipoNotaDTOGrupales = matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.actividades.grupales"));
			NotaDTO notaDTOGrupales = new NotaDTO();
			notaDTOGrupales.setNotValor(0f);
			notaDTOGrupales.setTipoNotaBean(tipoNotaDTOGrupales);
			notaDTO.addNotaDTO(notaDTOGrupales);

			TipoNotaDTO tipoNotaDTOIndividuales = matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.actividades.individuales"));
			NotaDTO notaDTOIndividuales = new NotaDTO();
			notaDTOIndividuales.setNotValor(0f);
			notaDTOIndividuales.setTipoNotaBean(tipoNotaDTOIndividuales);
			notaDTO.addNotaDTO(notaDTOIndividuales);

			TipoNotaDTO tipoNotaDTOLecciones = matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.lecciones"));
			NotaDTO notaDTOLecciones = new NotaDTO();
			notaDTOLecciones.setNotValor(0f);
			notaDTOLecciones.setTipoNotaBean(tipoNotaDTOLecciones);
			notaDTO.addNotaDTO(notaDTOLecciones);

			TipoNotaDTO tipoNotaDTOEscrita = matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.leccion.escrita"));
			NotaDTO notaDTOEscrita = new NotaDTO();
			notaDTOEscrita.setNotValor(0f);
			notaDTOEscrita.setTipoNotaBean(tipoNotaDTOEscrita);
			notaDTO.addNotaDTO(notaDTOEscrita);

			if (materiaEstadoPacialesDTO.getTipoNotaDTO().equals(-1)) {
				TipoNotaDTO tipoNotaDTOPrimerParcial = matriculaFactoryDAO.getParcialDAOImpl().find(
						MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.primer.quimestre"));
				materiaEstadoPacialesDTO.setTipoNotaDTO(tipoNotaDTOPrimerParcial);
			}

			notaDTO.setTipoNotaBean(materiaEstadoPacialesDTO.getTipoNotaDTO());
			notaDTO.setNotValor(0f);
			estudianteNotasParcial.setNotaParcialDTO(notaDTO);

			listaEstudianteNotasParcial.add(estudianteNotasParcial);
		}

		return listaEstudianteNotasParcial;
	}

	public NotaDTO guardarNota(NotaDTO notaDTO) {
		return matriculaFactoryDAO.getNotaDAOImpl().update(notaDTO);
	}

	public NotaDTO generarNotaQuimestre(MatriculaDetalleDTO matriculaDetalleDTO, Integer quimestre, NotaDTO notaDTOExamen) {

		NotaDTO notaDTOQuimestre = new NotaDTO();

		// Para tomar la nota del quimestre en caso que ya exista
		if (matriculaDetalleDTO.getNotNotas().size() == 5) {
			notaDTOQuimestre = matriculaDetalleDTO.getNotNotas().get(4);
		} else if (matriculaDetalleDTO.getNotNotas().size() == 10) {
			notaDTOQuimestre = matriculaDetalleDTO.getNotNotas().get(9);
		}

		Float promedioParciales = 0f;
		Float examenQuimestre = notaDTOExamen.getNotValor();

		List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();

		// recorremos todas las notas de esta matricula
		for (NotaDTO notaDTO : notaDTOs) {

			switch (quimestre) {
			case 1:
				if (notaDTO.getTipoNotaBean().getParCodigo() >= MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.primer.quimestre")
						&& notaDTO.getTipoNotaBean().getParCodigo() <= MessagesApplicacion
								.getInteger("erp.notas.tipo.tercer.parcial.primer.quimestre")) {
					promedioParciales += notaDTO.getNotValor();
				}
				break;

			case 2:
				if (notaDTO.getTipoNotaBean().getParCodigo() >= MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.segundo.quimestre")
						&& notaDTO.getTipoNotaBean().getParCodigo() <= MessagesApplicacion
								.getInteger("erp.notas.tipo.tercer.parcial.segundo.quimestre")) {
					promedioParciales += notaDTO.getNotValor();
				}
				break;
			default:
				break;
			}
		}

		// calcula los procetajes equivalentes para la nota del quimestre
		promedioParciales = (promedioParciales / 3) * (0.8f);
		examenQuimestre = examenQuimestre * (0.2f);

		// LLena el objeto nota para guardarlo
		notaDTOQuimestre.setNotValor(promedioParciales + examenQuimestre);
		notaDTOQuimestre.setMatMatriculaDetalleBean(matriculaDetalleDTO);

		switch (quimestre) {
		case 1:
			notaDTOQuimestre.setTipoNotaBean(matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.primer.quimestre")));
			break;

		case 2:
			notaDTOQuimestre.setTipoNotaBean(matriculaFactoryDAO.getParcialDAOImpl().find(
					MessagesApplicacion.getInteger("erp.notas.tipo.segundo.quimestre")));
			break;
		default:
			break;
		}

		// guarda la nota del quimestre
		guardarNota(notaDTOQuimestre);

		return notaDTOQuimestre;
	}

	public DatosEstudianteDTO obtenerEstudianteMatriculas(String cedulaEstudiante, Integer codPeriodo) throws SeguridadesException {
		EstudianteListDTO estudianteListDTO = new EstudianteListDTO();
		estudianteListDTO.setPerCi(cedulaEstudiante);
		try {
			DatosEstudianteDTO datosEstudianteDTO = new DatosEstudianteDTO();

			List<EstudianteListDTO> estudianteListDTOs = matriculaFactoryDAO.getEstudianteDAOImpl().obtenerEstudiante(estudianteListDTO);
			if (estudianteListDTOs.isEmpty()) {
				slf4jLogger.info("El estudiante no esta matriculado en ningún periodo");
				throw new SeguridadesException("El estudiante no esta matriculado en ningún periodo");
			} else {
				estudianteListDTO = matriculaFactoryDAO.getEstudianteDAOImpl().obtenerEstudiante(estudianteListDTO).get(0);
				EstudianteDTO estudianteDTO = matriculaFactoryDAO.getEstudianteDAOImpl().find(estudianteListDTO.getEstCodigo());
				List<PeriodoDTO> periodoDTOsMatriculados = new ArrayList<PeriodoDTO>();
				List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = new ArrayList<MatriculaDetalleDTO>();

				// Obtengo las matriculas del estudiante
				List<MatriculaDTO> matriculaDTOs = estudianteDTO.getMatMatriculas();
				for (MatriculaDTO matriculaDTO : matriculaDTOs) {
					List<MatriculaDetalleDTO> matriculaDetalleDTOs = matriculaDTO.getMatMatriculaDetalles();
					for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDetalleDTOs) {

						PeriodoDTO periodoDTO = matriculaDetalleDTO.getMatAsinacion().getMatPeriodo();

						// Llena la dista de detalles de matricula
						if (periodoDTO.getPerCodigo().equals(codPeriodo)) {
							// Obtengo las notas
							List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
							Collections.sort(matriculaDetalleDTO.getNotNotas(), new ComparadorTipoNotas());
							for (NotaDTO notaDTO : notaDTOs) {
								notaDTO.getTipoNotaBean();
							}

							listaMatriculaDetalleDTOs.add(matriculaDetalleDTO);
							datosEstudianteDTO.setPeriodoDTO(periodoDTO);
							datosEstudianteDTO.setNivelDTO(matriculaDetalleDTO.getMatAsinacion().getMatNivelParalelo().getMatNivel());
							datosEstudianteDTO.setParaleloDTO(matriculaDetalleDTO.getMatAsinacion().getMatNivelParalelo().getMatParalelo());

						}

						// Llena la lista de periodo en los que esta matriculado
						if (!periodoDTOsMatriculados.contains(periodoDTO)) {
							periodoDTOsMatriculados.add(periodoDTO);
						}
					}

				}

				datosEstudianteDTO.setEstudianteDTO(estudianteDTO);
				datosEstudianteDTO.setEstudianteListDTO(estudianteListDTO);
				datosEstudianteDTO.setPeriodoDTOsMatriculados(periodoDTOsMatriculados);
				datosEstudianteDTO.setListaMatriculaDetalleDTOs(listaMatriculaDetalleDTOs);

				return datosEstudianteDTO;
			}
		} catch (SeguridadesException e) {
			slf4jLogger.error("error al obtenerEstudianteMatriculas {}", e.toString());
			throw new SeguridadesException(e);
		}
	}

	// ////////////////////////////
	// ////////PARA REPORTES//////
	// //////////////////////////
	public ReporteDTO obtenerDatosReporteQuimestralEstudiantes(DatosEstudianteDTO datosEstudianteDTO, Integer codQuimestre)
			throws SeguridadesException {
		ReporteDTO reporteDTO = new ReporteDTO();
		List<DatosReporteDTO> listaReporteDTOs = new ArrayList<DatosReporteDTO>();

		try {

			// Parametros
			// CI
			reporteDTO.setParametro1(datosEstudianteDTO.getEstudianteListDTO().getPerCi());
			// estudiante
			reporteDTO.setParametro2(datosEstudianteDTO.getEstudianteListDTO().getPerNombres()
					+ datosEstudianteDTO.getEstudianteListDTO().getPerApellidos());
			// Periodo
			reporteDTO.setParametro3(datosEstudianteDTO.getPeriodoDTO().getPerDescripcion());
			// Curso
			reporteDTO.setParametro4(datosEstudianteDTO.getNivelDTO().getNivDescaripcion());
			// Paralelo
			reporteDTO.setParametro5(datosEstudianteDTO.getParaleloDTO().getParDescripcion());
			// Quimestre
			reporteDTO.setParametro6(new Integer(1).equals(codQuimestre) ? "Primero" : new Integer(2).equals(codQuimestre) ? "Segundo" : "");

			// DATOS
			List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = datosEstudianteDTO.getListaMatriculaDetalleDTOs();
			Float promedio = 0f;
			for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
				DatosReporteDTO datosReporteDTO = new DatosReporteDTO();
				datosReporteDTO.setNombre(matriculaDetalleDTO.getMatAsinacion().getMatMateria().getMtrNombe());

				int numeroNotas = matriculaDetalleDTO.getNotNotas().size();
				if (new Integer(1).equals(codQuimestre)) {
					if (numeroNotas > 0)// 1er Parcial
						datosReporteDTO.setCalificacion1(matriculaDetalleDTO.getNotNotas().get(0).getNotValor());
					if (numeroNotas > 1)// 2doParcial
						datosReporteDTO.setCalificacion2(matriculaDetalleDTO.getNotNotas().get(1).getNotValor());
					if (numeroNotas > 2)// 3er Parcial
						datosReporteDTO.setCalificacion3(matriculaDetalleDTO.getNotNotas().get(2).getNotValor());

					if (numeroNotas > 3)// Examen
						datosReporteDTO.setCalificacion6(matriculaDetalleDTO.getNotNotas().get(3).getNotValor());
				} else if (new Integer(2).equals(codQuimestre)) {
					if (numeroNotas > 5)// 1er Parcial
						datosReporteDTO.setCalificacion1(matriculaDetalleDTO.getNotNotas().get(5).getNotValor());
					if (numeroNotas > 6)// 2doParcial
						datosReporteDTO.setCalificacion2(matriculaDetalleDTO.getNotNotas().get(6).getNotValor());
					if (numeroNotas > 7)// 3er Parcial
						datosReporteDTO.setCalificacion3(matriculaDetalleDTO.getNotNotas().get(7).getNotValor());

					if (numeroNotas > 8)// Examen
						datosReporteDTO.setCalificacion6(matriculaDetalleDTO.getNotNotas().get(8).getNotValor());
				}

				if (datosReporteDTO.getCalificacion1() != null && datosReporteDTO.getCalificacion2() != null
						&& datosReporteDTO.getCalificacion3() != null)// Promedio
					datosReporteDTO.setCalificacion4((datosReporteDTO.getCalificacion1() + datosReporteDTO.getCalificacion2() + datosReporteDTO
							.getCalificacion3()) / 3);
				if (datosReporteDTO.getCalificacion4() != null)// 80% promedio
					datosReporteDTO.setCalificacion5(datosReporteDTO.getCalificacion4() * 0.8f);

				if (datosReporteDTO.getCalificacion6() != null)// 20% Examen
					datosReporteDTO.setCalificacion7(datosReporteDTO.getCalificacion6() * 0.2f);
				if (datosReporteDTO.getCalificacion5() != null && datosReporteDTO.getCalificacion7() != null)// PromedioT
					datosReporteDTO.setCalificacion8(datosReporteDTO.getCalificacion5() + datosReporteDTO.getCalificacion7());

				if (datosReporteDTO.getCalificacion8() != null) {// Observacion
					datosReporteDTO.setObservacion(estableceNotaCualitativa(datosReporteDTO.getCalificacion8()));
					promedio += datosReporteDTO.getCalificacion8();
				}

				listaReporteDTOs.add(datosReporteDTO);

			}

			reporteDTO.setListaReporteDTOs(listaReporteDTOs);

			if (listaMatriculaDetalleDTOs.size() != 0) {// Promedios totales
				reporteDTO.setPromeditoTotal(promedio / listaMatriculaDetalleDTOs.size());
				reporteDTO.setObservacionFinal(estableceNotaCualitativa(reporteDTO.getPromeditoTotal()));
			}
		} catch (Exception e) {
			slf4jLogger.error("error al obtenerDatosreporteQuimestralEstudiantes {}", e.toString());
			throw new SeguridadesException(e);
		}

		return reporteDTO;
	}

	private String estableceNotaCualitativa(Float notaFinal) {
		if (notaFinal == 10) {
			return "Supera";
		} else if (notaFinal >= 9 && notaFinal <= 9.99) {
			return "Domina";
		} else if (notaFinal >= 7 && notaFinal <= 8.99) {
			return "Alcanza";
		} else if (notaFinal >= 5 && notaFinal <= 6.99) {
			return "Próximo Alc.";
		} else {
			return "No Alcanza";
		}
	}

	private String estableceNotaCualitativaUnaLetra(Float notaFinal) {
		if (notaFinal > 10) {
			return "A";
		} else if (notaFinal >= 9 && notaFinal <= 9.99) {
			return "B";
		} else if (notaFinal >= 7 && notaFinal <= 8.99) {
			return "C";
		} else if (notaFinal >= 5 && notaFinal <= 6.99) {
			return "D";
		} else {
			return "E";
		}
	}

	// PARA EL REPORTE GENERAL POR MATERIA

	public List<AsinacionDTO> obtenerAsignacionesPorPeriodo(Integer codPeriodo) throws SeguridadesException {
		try {

			List<AsinacionDTO> asinacionDTOs = matriculaFactoryDAO.getAsinacionDAOImpl().asignacionesPorPeriodo(codPeriodo);

			for (AsinacionDTO asinacionDTO : asinacionDTOs) {
				asinacionDTO.getMatMateria();
				asinacionDTO.getMatMatriculaDetalles();
				asinacionDTO.getMatNivelParalelo();
				asinacionDTO.getMatNivelParalelo().getMatNivel();
				asinacionDTO.getMatNivelParalelo().getMatParalelo();
				List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();

				for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
					matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante().getEstPersona();
					List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
					for (NotaDTO notaDTO : notaDTOs) {
						notaDTO.getNotValor();
						notaDTO.getTipoNotaBean().getParCodigo();
					}
				}
			}

			return asinacionDTOs;

		} catch (Exception e) {
			slf4jLogger.info("error al asignacionesPorPeriodo {}", e.toString());
			throw new SeguridadesException(e);
		}
	}

	public ReporteDTO generarReporteGeneralPorParalelo(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria)
			throws SeguridadesException {
		AsinacionDTO asinacionDTO = asignacionPorPeriodoNivelParaleloMateria(codPeriodo, codNivel, codParelelo, codMateria);
		ReporteDTO reporteDTO = generarReporteGeneralPorParalelo(asinacionDTO);
		return reporteDTO;
	}

	private AsinacionDTO asignacionPorPeriodoNivelParaleloMateria(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria)
			throws SeguridadesException {
		slf4jLogger.info("asignacionPorPeriodoNivelParaleloMateria");

		try {

			List<AsinacionDTO> asinacionDTOs = matriculaFactoryDAO.getAsinacionDAOImpl().asignacionesPorPeriodoNivelParaleloMateria(codPeriodo,
					codNivel, codParelelo, codMateria);

			if (asinacionDTOs.size() == 1) {
				AsinacionDTO asinacionDTO = asinacionDTOs.get(0);
				return asinacionDTO;
			} else if (asinacionDTOs.size() == 0) {
				throw new SeguridadesException("No hay matriculas registradas para los datos ingresados.");
			} else {
				throw new SeguridadesException("Existe un problema consistencia de datos en el ingreso de patriculas.");
			}

		} catch (Exception e) {
			slf4jLogger.info("error al asignacionesPorPeriodoNivelParalelo {}", e.toString());
			throw new SeguridadesException(e);
		}
	}

	private ReporteDTO generarReporteGeneralPorParalelo(AsinacionDTO asinacionDTO) throws SeguridadesException {

		try {
			ReporteDTO reporteDTO = new ReporteDTO();
			List<DatosReporteDTO> listaReporteDTOs = new ArrayList<DatosReporteDTO>();

			// pararmetros
			reporteDTO.setParametro1(asinacionDTO.getMatPeriodo().getPerDescripcion());
			reporteDTO.setParametro2(asinacionDTO.getMatNivelParalelo().getMatNivel().getNivDescaripcion());
			reporteDTO.setParametro3(asinacionDTO.getMatNivelParalelo().getMatParalelo().getParDescripcion());
			reporteDTO.setParametro4(asinacionDTO.getMatMateria().getMtrNombe());
			ProfesorVO profesorVO = servicioMatricula.obtenerDocentePorId(asinacionDTO.getMatProfesor().getProPersona(), asinacionDTO
					.getMatProfesor().getProCodigo());
			String docente = profesorVO.getPersona().getPerNombres() + " " + profesorVO.getPersona().getPerApellidos();
			reporteDTO.setParametro5(docente);

			// Datos
			List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();

			for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
				DatosReporteDTO datosReporteDTO = new DatosReporteDTO();
				EstudianteVO estudianteVO = servicioMatricula.obtenerEstudiantePorId(matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante()
						.getEstPersona(), matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante().getEstCodigo());
				String estudiante = estudianteVO.getPersona().getPerApellidos() + " " + estudianteVO.getPersona().getPerNombres();
				datosReporteDTO.setNombre(estudiante);

				List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
				Collections.sort(notaDTOs, new ComparadorTipoNotas());
				int numeroNotas = notaDTOs.size();

				if (numeroNotas > 0)// 1er Parcial
					datosReporteDTO.setCalificacion1(notaDTOs.get(0).getNotValor());
				if (numeroNotas > 1)// 2doParcial
					datosReporteDTO.setCalificacion2(notaDTOs.get(1).getNotValor());
				if (numeroNotas > 2)// 3er Parcial
					datosReporteDTO.setCalificacion3(notaDTOs.get(2).getNotValor());
				if (numeroNotas > 3)// Examen
					datosReporteDTO.setCalificacion4(notaDTOs.get(3).getNotValor());
				if (numeroNotas > 4)// PRIMER QUIMESTRE
					datosReporteDTO.setCalificacion5(notaDTOs.get(4).getNotValor());
				if (datosReporteDTO.getCalificacion5() != null)
					datosReporteDTO.setObservacion(estableceNotaCualitativaUnaLetra(datosReporteDTO.getCalificacion5()));

				if (numeroNotas > 5)// 1er Parcial
					datosReporteDTO.setCalificacion6(notaDTOs.get(5).getNotValor());
				if (numeroNotas > 6)// 2doParcial
					datosReporteDTO.setCalificacion7(notaDTOs.get(6).getNotValor());
				if (numeroNotas > 7)// 3erParcial
					datosReporteDTO.setCalificacion8(notaDTOs.get(7).getNotValor());
				if (numeroNotas > 8)// Examen
					datosReporteDTO.setCalificacion9(notaDTOs.get(8).getNotValor());
				if (numeroNotas > 9)// Segundo QUIMESTRE
					datosReporteDTO.setCalificacion10(notaDTOs.get(9).getNotValor());
				if (datosReporteDTO.getCalificacion10() != null)
					datosReporteDTO.setObservacion1(estableceNotaCualitativaUnaLetra(datosReporteDTO.getCalificacion10()));

				// PROMEDIO TOTAL
				if (datosReporteDTO.getCalificacion5() != null && datosReporteDTO.getCalificacion9() != null)
					datosReporteDTO.setCalificacion11((datosReporteDTO.getCalificacion5() + datosReporteDTO.getCalificacion9()) / 2);
				if (datosReporteDTO.getCalificacion11() != null)
					datosReporteDTO.setObservacion2(estableceNotaCualitativaUnaLetra(datosReporteDTO.getCalificacion11()));

				listaReporteDTOs.add(datosReporteDTO);

			}

			reporteDTO.setListaReporteDTOs(listaReporteDTOs);

			return reporteDTO;
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}

	}

	// REPORTE DETALLE DEL PARCIAL

	public ReporteDTO generarReporteDetalleParcial(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria,
			Integer codQuimestre, Integer codParcial) throws SeguridadesException {
		AsinacionDTO asinacionDTO = asignacionPorPeriodoNivelParaleloMateria(codPeriodo, codNivel, codParelelo, codMateria);
		ReporteDTO reporteDTO = generarReporteDetalleParcial(asinacionDTO, codQuimestre, codParcial);
		return reporteDTO;
	}

	private ReporteDTO generarReporteDetalleParcial(AsinacionDTO asinacionDTO, Integer codQuimestre, Integer codParcial) throws SeguridadesException {
		try {
			ReporteDTO reporteDTO = new ReporteDTO();
			List<DatosReporteDTO> listaReporteDTOs = new ArrayList<DatosReporteDTO>();

			// pararmetros
			reporteDTO.setParametro1(asinacionDTO.getMatPeriodo().getPerDescripcion());
			reporteDTO.setParametro2(asinacionDTO.getMatNivelParalelo().getMatNivel().getNivDescaripcion());
			reporteDTO.setParametro3(asinacionDTO.getMatNivelParalelo().getMatParalelo().getParDescripcion());
			reporteDTO.setParametro4(asinacionDTO.getMatMateria().getMtrNombe());
			ProfesorVO profesorVO = servicioMatricula.obtenerDocentePorId(asinacionDTO.getMatProfesor().getProPersona(), asinacionDTO
					.getMatProfesor().getProCodigo());
			String docente = profesorVO.getPersona().getPerNombres() + " " + profesorVO.getPersona().getPerApellidos();
			reporteDTO.setParametro5(docente);
			reporteDTO.setParametro6(new Integer(1).equals(codQuimestre) ? "Primer Quimestre"
					: new Integer(2).equals(codQuimestre) ? "Segundo Quimestre" : "");
			reporteDTO.setParametro7(new Integer(1).equals(codParcial) ? "Primer Parcial" : new Integer(2).equals(codParcial) ? "Segundo Parcial"
					: new Integer(3).equals(codParcial) ? "Tercer Parcial" : "");

			// Datos
			List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();

			for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
				DatosReporteDTO datosReporteDTO = new DatosReporteDTO();
				EstudianteVO estudianteVO = servicioMatricula.obtenerEstudiantePorId(matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante()
						.getEstPersona(), matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante().getEstCodigo());
				String estudiante = estudianteVO.getPersona().getPerApellidos() + " " + estudianteVO.getPersona().getPerNombres();
				datosReporteDTO.setNombre(estudiante);

				List<NotaDTO> listaNotaDTOs = matriculaDetalleDTO.getNotNotas();
				for (NotaDTO notaDTO : listaNotaDTOs) {
					if (new Integer(1).equals(codQuimestre) && new Integer(1).equals(codParcial)
							&& new Integer(1).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					} else if (new Integer(1).equals(codQuimestre) && new Integer(2).equals(codParcial)
							&& new Integer(2).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					} else if (new Integer(1).equals(codQuimestre) && new Integer(3).equals(codParcial)
							&& new Integer(3).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					} else if (new Integer(2).equals(codQuimestre) && new Integer(1).equals(codParcial)
							&& new Integer(5).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					} else if (new Integer(2).equals(codQuimestre) && new Integer(2).equals(codParcial)
							&& new Integer(6).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					} else if (new Integer(2).equals(codQuimestre) && new Integer(3).equals(codParcial)
							&& new Integer(7).equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						establecerNotasComponenteReporte(datosReporteDTO, notaDTO);
						break;
					}
				}

				listaReporteDTOs.add(datosReporteDTO);

			}

			reporteDTO.setListaReporteDTOs(listaReporteDTOs);

			return reporteDTO;
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
	}

	private void establecerNotasComponenteReporte(DatosReporteDTO datosReporteDTO, NotaDTO notaDTO) {
		List<NotaDTO> lisNotaDTOComponentes = notaDTO.getNotasDTOComponentes();
		Collections.sort(lisNotaDTOComponentes, new ComparadorTipoNotas());

		int numeroNotas = lisNotaDTOComponentes.size();
		if (numeroNotas > 0)
			datosReporteDTO.setCalificacion1(lisNotaDTOComponentes.get(0).getNotValor());
		if (numeroNotas > 1)
			datosReporteDTO.setCalificacion2(lisNotaDTOComponentes.get(1).getNotValor());
		if (numeroNotas > 2)
			datosReporteDTO.setCalificacion3(lisNotaDTOComponentes.get(2).getNotValor());
		if (numeroNotas > 3)
			datosReporteDTO.setCalificacion4(lisNotaDTOComponentes.get(3).getNotValor());
		if (numeroNotas > 4)
			datosReporteDTO.setCalificacion5(lisNotaDTOComponentes.get(4).getNotValor());

		datosReporteDTO.setCalificacion6(notaDTO.getNotValor());
		datosReporteDTO.setObservacion(estableceNotaCualitativa(datosReporteDTO.getCalificacion6()));

	}

	public ReporteDTO obtenerDatosReporteAnualEstudiantes(DatosEstudianteDTO datosEstudianteDTO) throws SeguridadesException {
		ReporteDTO reporteDTO = new ReporteDTO();
		List<DatosReporteDTO> listaReporteDTOs = new ArrayList<DatosReporteDTO>();

		try {

			// Parametros
			// CI
			reporteDTO.setParametro1(datosEstudianteDTO.getEstudianteListDTO().getPerCi());
			// estudiante
			reporteDTO.setParametro2(datosEstudianteDTO.getEstudianteListDTO().getPerNombres()
					+ datosEstudianteDTO.getEstudianteListDTO().getPerApellidos());
			// Periodo
			reporteDTO.setParametro3(datosEstudianteDTO.getPeriodoDTO().getPerDescripcion());
			// Curso
			reporteDTO.setParametro4(datosEstudianteDTO.getNivelDTO().getNivDescaripcion());
			// Paralelo
			reporteDTO.setParametro5(datosEstudianteDTO.getParaleloDTO().getParDescripcion());

			// DATOS
			List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs = datosEstudianteDTO.getListaMatriculaDetalleDTOs();

			Float promedio = 0f;
			for (MatriculaDetalleDTO matriculaDetalleDTO : listaMatriculaDetalleDTOs) {
				DatosReporteDTO datosReporteDTO = new DatosReporteDTO();
				datosReporteDTO.setNombre(matriculaDetalleDTO.getMatAsinacion().getMatMateria().getMtrNombe());

				List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();

				for (NotaDTO notaDTO : notaDTOs) {
					if (MessagesApplicacion.getInteger("erp.notas.tipo.primer.quimestre").equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						datosReporteDTO.setCalificacion1(notaDTO.getNotValor());
					} else if (MessagesApplicacion.getInteger("erp.notas.tipo.segundo.quimestre").equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						datosReporteDTO.setCalificacion2(notaDTO.getNotValor());
					}
				}

				if (datosReporteDTO.getCalificacion1() != null && datosReporteDTO.getCalificacion2() != null) {
					datosReporteDTO.setCalificacion3((datosReporteDTO.getCalificacion1() + datosReporteDTO.getCalificacion2()) / 2);
				}
				if (datosReporteDTO.getCalificacion3() != null) {
					datosReporteDTO.setObservacion(estableceNotaCualitativa(datosReporteDTO.getCalificacion3()));
					promedio += datosReporteDTO.getCalificacion3();
				}

				listaReporteDTOs.add(datosReporteDTO);

			}

			reporteDTO.setListaReporteDTOs(listaReporteDTOs);

			if (listaMatriculaDetalleDTOs.size() != 0) {// Promedios totales
				reporteDTO.setPromeditoTotal(promedio / listaMatriculaDetalleDTOs.size());
				reporteDTO.setObservacionFinal(estableceNotaCualitativa(reporteDTO.getPromeditoTotal()));
			}

		} catch (Exception e) {
			slf4jLogger.error("error al obtenerDatosReporteAnualEstudiantes {}", e.toString());
			throw new SeguridadesException(e);
		}

		return reporteDTO;
	}

	public List<EstudianteNotasParcial> obtenerEstudiantesNotasParcialCorreccion(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO, int codTipoNota) {
		List<EstudianteNotasParcial> listaEstudianteNotasParcial = new ArrayList<EstudianteNotasParcial>();

		List<MatriculaDetalleDTO> matriculaDetalleDTOs = materiaEstadoPacialesDTO.getAsinacionDTO().getMatMatriculaDetalles();
		NotaDTO notaDTOParcial = null;

		for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDetalleDTOs) {
			int totalNotas = matriculaDetalleDTO.getNotNotas().size();

			switch (codTipoNota) {
			case 1:
				if (totalNotas > 0)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(0);
				break;
			case 2:
				if (totalNotas > 1)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(1);
				break;
			case 3:
				if (totalNotas > 2)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(2);
				break;
			case 4:
				if (totalNotas > 3)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(3);
				break;

			case 6:
				if (totalNotas > 5)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(5);
				break;
			case 7:
				if (totalNotas > 6)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(6);
				break;
			case 8:
				if (totalNotas > 7)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(7);
				break;
			case 9:
				if (totalNotas > 8)
					notaDTOParcial = matriculaDetalleDTO.getNotNotas().get(8);
				break;

			default:
				break;
			}

			EstudianteNotasParcial estudianteNotasParcial = new EstudianteNotasParcial();
			estudianteNotasParcial.setMatriculaDetalleDTO(matriculaDetalleDTO);

			EstudianteDTO estudianteDTO = matriculaDetalleDTO.getMatMatriculaBean().getMatEstudiante();
			estudianteNotasParcial.setEstudianteDTO(estudianteDTO);

			Persona persona = factoryDAO.getPersonaDAOImpl().find(estudianteDTO.getEstPersona());
			estudianteNotasParcial.setPersona(persona);

			estudianteNotasParcial.setNotaParcialDTO(notaDTOParcial);

			listaEstudianteNotasParcial.add(estudianteNotasParcial);
		}

		return listaEstudianteNotasParcial;
	}

	public List<MateriaEstadoPacialesDTO> establecerEstadosNotasPasadasCorreccion(List<AsinacionDTO> asinacionDTOs) {
		List<MateriaEstadoPacialesDTO> materiaEstadoPacialesDTOs = new ArrayList<MateriaEstadoPacialesDTO>();

		for (AsinacionDTO asinacionDTO : asinacionDTOs) {
			List<MatriculaDetalleDTO> matriculaDetalleDTOs = asinacionDTO.getMatMatriculaDetalles();
			for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDetalleDTOs) {
				List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();
				Collections.sort(notaDTOs, new ComparadorTipoNotas());
				for (NotaDTO notaDTO : notaDTOs) {
					if (!MessagesApplicacion.getInteger("erp.notas.tipo.primer.quimestre").equals(notaDTO.getTipoNotaBean().getParCodigo())
							&& !MessagesApplicacion.getInteger("erp.notas.tipo.segundo.quimestre").equals(notaDTO.getTipoNotaBean().getParCodigo())) {
						MateriaEstadoPacialesDTO materiaEstadoPacialesDTO = new MateriaEstadoPacialesDTO();
						materiaEstadoPacialesDTO.setAsinacionDTO(asinacionDTO);

						materiaEstadoPacialesDTO.setTipoNotaDTO(notaDTO.getTipoNotaBean());
						if (!materiaEstadoPacialesDTOs.contains(materiaEstadoPacialesDTO)) {
							materiaEstadoPacialesDTOs.add(materiaEstadoPacialesDTO);
						}
					}
				}

			}
		}

		return materiaEstadoPacialesDTOs;
	}

	public NotaDTO generarNotaQuimestreEdicion(MatriculaDetalleDTO matriculaDetalleDTO, Integer codTipoNota, NotaDTO notaDTOParcialEditado) {

		NotaDTO notaDTOQuimestre = new NotaDTO();
		Float examenQuimestre = 0f;

		// Para tomar la nota del quimestre en caso que ya exista
		if (matriculaDetalleDTO.getNotNotas().size() >= 5 && matriculaDetalleDTO.getNotNotas().size() < 10) {

			if (codTipoNota == 1 || codTipoNota == 2 || codTipoNota == 3 || codTipoNota == 4) {
				notaDTOQuimestre = matriculaDetalleDTO.getNotNotas().get(4);
				examenQuimestre = matriculaDetalleDTO.getNotNotas().get(3).getNotValor();
			}
		} else if (matriculaDetalleDTO.getNotNotas().size() == 10) {
			notaDTOQuimestre = matriculaDetalleDTO.getNotNotas().get(9);
			examenQuimestre = matriculaDetalleDTO.getNotNotas().get(8).getNotValor();
		} else {
			return notaDTOQuimestre;
		}

		Float promedioParciales = 0f;		

		List<NotaDTO> notaDTOs = matriculaDetalleDTO.getNotNotas();

		// recorremos todas las notas de esta matricula
		for (NotaDTO notaDTO : notaDTOs) {
			int codTipoNotaIndex = notaDTO.getTipoNotaBean().getParCodigo();
			switch (codTipoNota) {
			case 1:
				if (codTipoNotaIndex >= MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.primer.quimestre")
						&& codTipoNotaIndex <= MessagesApplicacion.getInteger("erp.notas.tipo.tercer.parcial.primer.quimestre")) {

					if (codTipoNotaIndex == codTipoNota) {
						// agrega la nota que acaba de ser editada
						promedioParciales += notaDTOParcialEditado.getNotValor();
					} else {
						promedioParciales += notaDTO.getNotValor();
					}
				}
				break;

			case 2:
				if (codTipoNotaIndex >= MessagesApplicacion.getInteger("erp.notas.tipo.primer.parcial.segundo.quimestre")
						&& codTipoNotaIndex <= MessagesApplicacion.getInteger("erp.notas.tipo.tercer.parcial.segundo.quimestre")) {

					if (codTipoNotaIndex == codTipoNota) {
						// agrega la nota que acaba de ser editada
						promedioParciales += notaDTOParcialEditado.getNotValor();
					} else {
						promedioParciales += notaDTO.getNotValor();
					}
				}
				break;
			default:
				break;
			}
		}

		// calcula los procetajes equivalentes para la nota del quimestre
		promedioParciales = (promedioParciales / 3) * (0.8f);
		examenQuimestre = examenQuimestre * (0.2f);

		// LLena el objeto nota para guardarlo
		notaDTOQuimestre.setNotValor(promedioParciales + examenQuimestre);
		notaDTOQuimestre.setMatMatriculaDetalleBean(matriculaDetalleDTO);

		// guarda la nota del quimestre
		guardarNota(notaDTOQuimestre);

		return notaDTOQuimestre;
	}

}
