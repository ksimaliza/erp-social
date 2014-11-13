package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.event.CellEditEvent;

import ec.edu.uce.erp.common.util.MessagesApplicacion;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ComparadorTipoNotas;
import ec.edu.uce.erp.ejb.persistence.util.dto.DatosEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotasParcial;
import ec.edu.uce.erp.ejb.persistence.util.dto.MateriaEstadoPacialesDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ReporteDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.ejb.servicio.ServicioNotas;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NotasDataManager;

@RequestScoped
@ManagedBean
public class NotasController extends BaseController {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioNotas servicioNotas;

	@EJB
	private ServicioMatricula servicioMatricula;

	@ManagedProperty(value = "#{notasDataManager}")
	private NotasDataManager notasDataManager;

	public void notasControllerPost() {

		// DOCENTE
		if (notasDataManager.getUsuarioSession().getSegtPerfil().getIdPerfil().equals(MessagesApplicacion.getInteger("erp.seg.perfil.docente"))) {
			obtenerAnioLectivoVigente();
			obtenerAsignacionesPorPeriodoProfesor();
			llenarNiveles();
			obtenerMaterias();

			notasDataManager.setNivelDTOSeleccionado(new NivelDTO());
			notasDataManager.setParaleloDTOSeleccionado(new ParaleloDTO());

			notasDataManager.setEstaInicializado(Boolean.TRUE);
			// ESTUDIANTE
		} else if (notasDataManager.getUsuarioSession().getSegtPerfil().getIdPerfil()
				.equals(MessagesApplicacion.getInteger("erp.seg.perfil.estudiante"))) {
			obtenerAnioLectivoVigente();
			notasDataManager.setDatosEstudianteDTO(new DatosEstudianteDTO());
			notasDataManager.setCedulaEstudiante("");

			notasDataManager.setEstaInicializado(Boolean.TRUE);
			// ADMINISTRADOR
		} else if (notasDataManager.getUsuarioSession().getSegtPerfil().getIdPerfil().equals(MessagesApplicacion.getInteger("erp.seg.perfil.admin"))) {
			obtenerAnioLectivoVigente();
			notasDataManager.setDatosEstudianteDTO(new DatosEstudianteDTO());
			notasDataManager.setCedulaEstudiante("");

			notasDataManager.setNivelDTOSeleccionado(new NivelDTO());
			notasDataManager.setParaleloDTOSeleccionado(new ParaleloDTO());
			notasDataManager.setEstaInicializado(Boolean.TRUE);
		}
	}

	public String getForm() {
		if (!notasDataManager.getEstaInicializado()) {
			notasControllerPost();
		}
		return "formNotas";
	}

	public void obtenerAnioLectivoVigente() {
		try {
			notasDataManager.setAnioLectivoVigente(servicioNotas.obtenerAnioLectivoVigente());
			notasDataManager.setListaPeriodoDTOs(servicioMatricula.buscarPeriodo(new PeriodoDTO()));

		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public NotasDataManager getNotasDataManager() {
		return notasDataManager;
	}

	public void setNotasDataManager(NotasDataManager notasDataManager) {
		this.notasDataManager = notasDataManager;
	}

	private void obtenerAsignacionesPorPeriodoProfesor() {
		try {
			notasDataManager.setListaAsinacionDTO(servicioNotas.obtenerAsignacionesPorPeriodoProfesor(notasDataManager.getAnioLectivoVigente()
					.getPerCodigo(), notasDataManager.getUsuarioSession().getPersonaTbls().get(0).getPerPk()));
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	/**
	 * Metodo que permite obtener los curso
	 * 
	 * @return
	 */
	public void llenarNiveles() {
		List<NivelDTO> nivelDTOs = new ArrayList<NivelDTO>();
		for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
			if (!nivelDTOs.contains(asinacionDTO.getMatNivelParalelo().getMatNivel())) {
				nivelDTOs.add(asinacionDTO.getMatNivelParalelo().getMatNivel());
			}
		}

		notasDataManager.setListaNiveles(nivelDTOs);
	}

	public void llenarParalelosPorNivel() {
		List<ParaleloDTO> paraleloDTOs = new ArrayList<ParaleloDTO>();
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();

		for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
			if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())
					&& !paraleloDTOs.contains(asinacionDTO.getMatNivelParalelo().getMatParalelo())) {

				paraleloDTOs.add(asinacionDTO.getMatNivelParalelo().getMatParalelo());

			}
		}

		notasDataManager.setListaParalelo(paraleloDTOs);

	}

	public void obtenerMaterias() {
		List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();
		List<AsinacionDTO> asinacionDTOs = notasDataManager.getListaAsinacionDTO();
		List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();

		for (AsinacionDTO asinacionDTO : asinacionDTOs) {
			listaMateriaDTOs.add(asinacionDTO.getMatMateria());

			asinacionDTOsSeleccionadas.add(asinacionDTO);
		}

		notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);

		notasDataManager.setListaMaterias(listaMateriaDTOs);

		notasDataManager.setListaMateriaEstadoPacialesDTOs(servicioNotas.establecerEstadosNotasPasadas(notasDataManager
				.getListaAsinacionesSeleccionadas()));
	}

	public void filtarMateriasPorNivel() {
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();

		if (nivelDTOSeleccionado.getNivCodigo() == 0) {
			obtenerMaterias();
		} else {
			List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();
			List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();
			for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
				if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())) {
					listaMateriaDTOs.add(asinacionDTO.getMatMateria());
					asinacionDTOsSeleccionadas.add(asinacionDTO);
				}
			}
			notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);
			llenarParalelosPorNivel();
			notasDataManager.setListaMaterias(listaMateriaDTOs);
			notasDataManager.setListaMateriaEstadoPacialesDTOs(servicioNotas.establecerEstadosNotasPasadas(notasDataManager
					.getListaAsinacionesSeleccionadas()));
		}

	}

	public void filtarMateriasPorNivelParalelo() {
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();
		ParaleloDTO paraleloDTOSeleccionado = notasDataManager.getParaleloDTOSeleccionado();
		List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();

		if (paraleloDTOSeleccionado.getParCodigo() == 0) {
			filtarMateriasPorNivel();
		} else {
			List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();
			for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
				if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())
						&& asinacionDTO.getMatNivelParalelo().getMatParalelo().getParCodigo().equals(paraleloDTOSeleccionado.getParCodigo())) {
					listaMateriaDTOs.add(asinacionDTO.getMatMateria());
					asinacionDTOsSeleccionadas.add(asinacionDTO);
				}
			}
			notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);
			notasDataManager.setListaMaterias(listaMateriaDTOs);
			notasDataManager.setListaMateriaEstadoPacialesDTOs(servicioNotas.establecerEstadosNotasPasadas(notasDataManager
					.getListaAsinacionesSeleccionadas()));
		}

	}

	/**
	 * M�todo que permite obtner la materia seleccionada por el profesor
	 * 
	 * @param materia
	 *            . objeto materia
	 */
	public String ingredarNotasMateria(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO) {
		notasDataManager.setMateriaEstadoSeleccionado(materiaEstadoPacialesDTO);

		List<EstudianteNotasParcial> listaEstudianteNotasParcials = servicioNotas.obtenerEstudiantesNotasParcial(notasDataManager
				.getMateriaEstadoSeleccionado());

		notasDataManager.setListaEstudianteNotasParcials(listaEstudianteNotasParcials);

		for (EstudianteNotasParcial estudianteNotasParcial : listaEstudianteNotasParcials) {
			if (!estudianteNotasParcial.getNotaParcialDTO().getTipoNotaBean().getParCodigo()
					.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre"))
					&& !estudianteNotasParcial.getNotaParcialDTO().getTipoNotaBean().getParCodigo()
							.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre"))) {
				List<NotaDTO> notaDTOsComponentes = estudianteNotasParcial.getNotaParcialDTO().getNotasDTOComponentes();
				estudianteNotasParcial.setNotaDTOTareas(notaDTOsComponentes.get(0));
				estudianteNotasParcial.setNotaDTOIndividuales(notaDTOsComponentes.get(1));
				estudianteNotasParcial.setNotaDTOGrupales(notaDTOsComponentes.get(2));
				estudianteNotasParcial.setNotaDTOOrales(notaDTOsComponentes.get(3));
				estudianteNotasParcial.setNotaDTOEscrita(notaDTOsComponentes.get(4));
			}
		}

		return "ingresoNotas.xhtml";
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void calcularNotaParcial(EstudianteNotasParcial estudianteNotasParcial) {
		try {
			List<NotaDTO> listaNotaDTOs = estudianteNotasParcial.getNotaParcialDTO().getNotasDTOComponentes();
			Float promedio = new Float(0);

			for (NotaDTO notaDTO : listaNotaDTOs) {
				promedio += notaDTO.getNotValor();
			}

			promedio = promedio / listaNotaDTOs.size();

			estudianteNotasParcial.getNotaParcialDTO().setNotValor(promedio);
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public String guardarNotas() {

		List<EstudianteNotasParcial> listaEstudianteNotasParcials = notasDataManager.getListaEstudianteNotasParcials();

		for (EstudianteNotasParcial estudianteNotasParcial : listaEstudianteNotasParcials) {
			NotaDTO notaDTOParcial = estudianteNotasParcial.getNotaParcialDTO();

			if (notaDTOParcial.getTipoNotaBean().getParCodigo()
					.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre"))
					|| notaDTOParcial.getTipoNotaBean().getParCodigo()
							.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre"))) {
				notaDTOParcial.setNotasDTOComponentes(null);

				notaDTOParcial.setMatMatriculaDetalleBean(estudianteNotasParcial.getMatriculaDetalleDTO());
				NotaDTO notaDTOExamen = servicioNotas.guardarNota(notaDTOParcial);

				// genera y guardar la nota del quimestre
				servicioNotas.generarNotaQuimestre(estudianteNotasParcial.getMatriculaDetalleDTO(), notaDTOParcial.getTipoNotaBean().getParCodigo()
						.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre")) ? 1 : 2, notaDTOExamen);

			} else {
				List<NotaDTO> notaDTOsComponentes = new ArrayList<NotaDTO>();
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOTareas());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOGrupales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOIndividuales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOOrales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOEscrita());
				notaDTOParcial.setNotasDTOComponentes(notaDTOsComponentes);

				notaDTOParcial.setMatMatriculaDetalleBean(estudianteNotasParcial.getMatriculaDetalleDTO());
				servicioNotas.guardarNota(notaDTOParcial);
			}

		}
		notasControllerPost();

		return "administracionNotas.xhtml";

	}

	public Boolean isExamen(Integer codigoTipoNota) {
		return codigoTipoNota.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre"))
				|| codigoTipoNota.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre"));
	}

	public void buscarNotasEstudiante() {
		try {
			DatosEstudianteDTO datosEstudianteDTO = servicioNotas.obtenerEstudianteMatriculas(notasDataManager.getCedulaEstudiante(),
					notasDataManager.getAnioLectivoVigente().getPerCodigo());
			notasDataManager.setDatosEstudianteDTO(datosEstudianteDTO);

		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void filtrarMatriculasPorPerido() {
		// TODO FALTA FILTAR POR PERIODO
	}

	// PARA REPORTES
	public void generarReporteQuimestralEstudiante() {
		try {
			ReporteDTO reporteDTO = servicioNotas.obtenerDatosReporteQuimestralEstudiantes(notasDataManager.getDatosEstudianteDTO(),
					notasDataManager.getCodQuimestreSeleccionada());
			notasDataManager.setReporteDTO(reporteDTO);
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void imprimirReporteQuimestralEstudiantePDF(ActionEvent actionEvent) {
		try {
			ReporteDTO reporteDTO = notasDataManager.getReporteDTO();
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reporteDTO.getListaReporteDTOs());

			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/paginas/admNotas/reportes/reporteQuimestralEstudiante.jasper");

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("parametro1", reporteDTO.getParametro1());
			parametros.put("parametro2", reporteDTO.getParametro2());
			parametros.put("parametro3", reporteDTO.getParametro3());
			parametros.put("parametro4", reporteDTO.getParametro4());
			parametros.put("parametro5", reporteDTO.getParametro5());
			parametros.put("parametro6", reporteDTO.getParametro6());
			parametros.put("promeditoTotal", reporteDTO.getPromeditoTotal());
			parametros.put("observacionFinal", reporteDTO.getObservacionFinal());

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, jrBeanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteQuimestralEstudiante.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	// PARA REPORTE DE GENERAL DE ESTUDIANTES POR MATERIA

	public void buscarAsignacionesPorPeriodo() {
		try {
			notasDataManager.setListaAsinacionDTO(servicioNotas.obtenerAsignacionesPorPeriodo(notasDataManager.getCodPeriodoSeleccionado()));
			llenarNiveles();
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void filtarMateriasPorNivelParaleloReporte() {
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();
		ParaleloDTO paraleloDTOSeleccionado = notasDataManager.getParaleloDTOSeleccionado();
		List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();

		if (paraleloDTOSeleccionado.getParCodigo() == 0) {
			filtarMateriasPorNivel();
		} else {
			List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();
			for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
				if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())
						&& asinacionDTO.getMatNivelParalelo().getMatParalelo().getParCodigo().equals(paraleloDTOSeleccionado.getParCodigo())) {
					listaMateriaDTOs.add(asinacionDTO.getMatMateria());
					asinacionDTOsSeleccionadas.add(asinacionDTO);
				}
			}
			notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);
			notasDataManager.setListaMaterias(listaMateriaDTOs);
		}

	}

	public void generarReporteGeneralPorParalelo() {
		try {
			ReporteDTO reporteDTO = servicioNotas.generarReporteGeneralPorParalelo(notasDataManager.getCodPeriodoSeleccionado(), notasDataManager
					.getNivelDTOSeleccionado().getNivCodigo(), notasDataManager.getParaleloDTOSeleccionado().getParCodigo(), notasDataManager
					.getCodMateriaSeleccionada());
			notasDataManager.setReporteDTO(reporteDTO);
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void generarReporteDetalleParcial() {
		try {
			ReporteDTO reporteDTO = servicioNotas.generarReporteDetalleParcial(notasDataManager.getCodPeriodoSeleccionado(), notasDataManager
					.getNivelDTOSeleccionado().getNivCodigo(), notasDataManager.getParaleloDTOSeleccionado().getParCodigo(), notasDataManager
					.getCodMateriaSeleccionada(), notasDataManager.getCodQuimestreSeleccionada(), notasDataManager.getCodParcialSeleccionado());
			notasDataManager.setReporteDTO(reporteDTO);
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void imprimirReporteGeneralPorParaleloPDF(ActionEvent actionEvent) {
		try {
			ReporteDTO reporteDTO = notasDataManager.getReporteDTO();
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reporteDTO.getListaReporteDTOs());

			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/paginas/admNotas/reportes/reporteGeneralPorParalelo.jasper");

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("parametro1", reporteDTO.getParametro1());
			parametros.put("parametro2", reporteDTO.getParametro2());
			parametros.put("parametro3", reporteDTO.getParametro3());
			parametros.put("parametro4", reporteDTO.getParametro4());
			parametros.put("parametro5", reporteDTO.getParametro5());

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, jrBeanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteGeneralPorParalelo.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void imprimirReporteDetalleParcialPDF(ActionEvent actionEvent) {
		try {
			ReporteDTO reporteDTO = notasDataManager.getReporteDTO();
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reporteDTO.getListaReporteDTOs());

			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/paginas/admNotas/reportes/reporteDetalleParcial.jasper");

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("parametro1", reporteDTO.getParametro1());
			parametros.put("parametro2", reporteDTO.getParametro2());
			parametros.put("parametro3", reporteDTO.getParametro3());
			parametros.put("parametro4", reporteDTO.getParametro4());
			parametros.put("parametro5", reporteDTO.getParametro5());
			parametros.put("parametro6", reporteDTO.getParametro6());
			parametros.put("parametro7", reporteDTO.getParametro7());

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, jrBeanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteDetalleParcial.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void generarReporteAnualEstudiante() {
		try {
			ReporteDTO reporteDTO = servicioNotas.obtenerDatosReporteAnualEstudiantes(notasDataManager.getDatosEstudianteDTO());
			notasDataManager.setReporteDTO(reporteDTO);
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void imprimirReporteAnualEstudiantePDF(ActionEvent actionEvent) {
		try {
			ReporteDTO reporteDTO = notasDataManager.getReporteDTO();
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reporteDTO.getListaReporteDTOs());

			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/paginas/admNotas/reportes/reporteAnualEstudiante.jasper");

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("parametro1", reporteDTO.getParametro1());
			parametros.put("parametro2", reporteDTO.getParametro2());
			parametros.put("parametro3", reporteDTO.getParametro3());
			parametros.put("parametro4", reporteDTO.getParametro4());
			parametros.put("parametro5", reporteDTO.getParametro5());
			parametros.put("promeditoTotal", reporteDTO.getPromeditoTotal());
			parametros.put("observacionFinal", reporteDTO.getObservacionFinal());

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, jrBeanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteAnualEstudiante.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	// PARA LA CORRECCION DE NOTAS
	public String corregirNotasMateria(MateriaEstadoPacialesDTO materiaEstadoPacialesDTO, long codTipoNota) {
		notasDataManager.setMateriaEstadoSeleccionado(materiaEstadoPacialesDTO);

		List<EstudianteNotasParcial> listaEstudianteNotasParcials = servicioNotas.obtenerEstudiantesNotasParcialCorreccion(
				notasDataManager.getMateriaEstadoSeleccionado(), (int) codTipoNota);
		notasDataManager.setCodTipoNota((int) codTipoNota);

		notasDataManager.setListaEstudianteNotasParcials(listaEstudianteNotasParcials);

		for (EstudianteNotasParcial estudianteNotasParcial : listaEstudianteNotasParcials) {
			if (estudianteNotasParcial.getNotaParcialDTO() != null) {
				if (!estudianteNotasParcial.getNotaParcialDTO().getTipoNotaBean().getParCodigo()
						.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre"))
						&& !estudianteNotasParcial.getNotaParcialDTO().getTipoNotaBean().getParCodigo()
								.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre"))) {

					List<NotaDTO> notaDTOsComponentes = estudianteNotasParcial.getNotaParcialDTO().getNotasDTOComponentes();					
					Collections.sort(notaDTOsComponentes, new ComparadorTipoNotas());
					
					estudianteNotasParcial.setNotaDTOTareas(notaDTOsComponentes.get(0));
					estudianteNotasParcial.setNotaDTOGrupales(notaDTOsComponentes.get(1));
					estudianteNotasParcial.setNotaDTOIndividuales(notaDTOsComponentes.get(2));					
					estudianteNotasParcial.setNotaDTOOrales(notaDTOsComponentes.get(3));
					estudianteNotasParcial.setNotaDTOEscrita(notaDTOsComponentes.get(4));

				}
			}
		}

		return "ingresoCorreccionNotas.xhtml";
	}

	public void filtarMateriasPorNivelCorrecion() {
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();

		if (nivelDTOSeleccionado.getNivCodigo() == 0) {
			obtenerMaterias();
		} else {
			List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();
			List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();
			for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
				if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())) {
					listaMateriaDTOs.add(asinacionDTO.getMatMateria());
					asinacionDTOsSeleccionadas.add(asinacionDTO);
				}
			}
			notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);
			llenarParalelosPorNivel();
			notasDataManager.setListaMaterias(listaMateriaDTOs);
			notasDataManager.setListaMateriaEstadoPacialesDTOs(servicioNotas.establecerEstadosNotasPasadasCorreccion(notasDataManager
					.getListaAsinacionesSeleccionadas()));
		}

	}

	public void filtarMateriasPorNivelParaleloCorrecion() {
		NivelDTO nivelDTOSeleccionado = notasDataManager.getNivelDTOSeleccionado();
		ParaleloDTO paraleloDTOSeleccionado = notasDataManager.getParaleloDTOSeleccionado();
		List<MateriaDTO> listaMateriaDTOs = new ArrayList<MateriaDTO>();

		if (paraleloDTOSeleccionado.getParCodigo() == 0) {
			filtarMateriasPorNivel();
		} else {
			List<AsinacionDTO> asinacionDTOsSeleccionadas = new ArrayList<AsinacionDTO>();
			for (AsinacionDTO asinacionDTO : notasDataManager.getListaAsinacionDTO()) {
				if (asinacionDTO.getMatNivelParalelo().getMatNivel().getNivCodigo().equals(nivelDTOSeleccionado.getNivCodigo())
						&& asinacionDTO.getMatNivelParalelo().getMatParalelo().getParCodigo().equals(paraleloDTOSeleccionado.getParCodigo())) {
					listaMateriaDTOs.add(asinacionDTO.getMatMateria());
					asinacionDTOsSeleccionadas.add(asinacionDTO);
				}
			}
			notasDataManager.setListaAsinacionesSeleccionadas(asinacionDTOsSeleccionadas);
			notasDataManager.setListaMaterias(listaMateriaDTOs);
			notasDataManager.setListaMateriaEstadoPacialesDTOs(servicioNotas.establecerEstadosNotasPasadasCorreccion(notasDataManager
					.getListaAsinacionesSeleccionadas()));
		}

	}

	public String editarNotas() {

		List<EstudianteNotasParcial> listaEstudianteNotasParcials = notasDataManager.getListaEstudianteNotasParcials();

		for (EstudianteNotasParcial estudianteNotasParcial : listaEstudianteNotasParcials) {
			NotaDTO notaDTOParcial = estudianteNotasParcial.getNotaParcialDTO();

			if (notaDTOParcial.getTipoNotaBean().getParCodigo()
					.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre"))
					|| notaDTOParcial.getTipoNotaBean().getParCodigo()
							.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.segundo.quimestre"))) {
				notaDTOParcial.setNotasDTOComponentes(null);

				notaDTOParcial.setMatMatriculaDetalleBean(estudianteNotasParcial.getMatriculaDetalleDTO());
				NotaDTO notaDTOExamen = servicioNotas.guardarNota(notaDTOParcial);

				// genera y guardar la nota del quimestre
				servicioNotas.generarNotaQuimestre(estudianteNotasParcial.getMatriculaDetalleDTO(), notaDTOParcial.getTipoNotaBean().getParCodigo()
						.equals(MessagesApplicacion.getInteger("erp.notas.tipo.examen.parcial.primer.quimestre")) ? 1 : 2, notaDTOExamen);

			} else {
				List<NotaDTO> notaDTOsComponentes = new ArrayList<NotaDTO>();
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOTareas());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOGrupales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOIndividuales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOOrales());
				notaDTOsComponentes.add(estudianteNotasParcial.getNotaDTOEscrita());
				notaDTOParcial.setNotasDTOComponentes(notaDTOsComponentes);

				notaDTOParcial.setMatMatriculaDetalleBean(estudianteNotasParcial.getMatriculaDetalleDTO());
				servicioNotas.guardarNota(notaDTOParcial);

				// genera y guardar la nota del quimestre
				servicioNotas.generarNotaQuimestreEdicion(estudianteNotasParcial.getMatriculaDetalleDTO(), notaDTOParcial.getTipoNotaBean().getParCodigo(),
						notaDTOParcial);

			}

		}
		notasControllerPost();

		return "correccionNotas.xhtml";

	}
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}