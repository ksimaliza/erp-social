package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.DatosEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotasParcial;
import ec.edu.uce.erp.ejb.persistence.util.dto.MateriaEstadoPacialesDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.ReporteDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean
public class NotasDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;

	private Boolean estaInicializado = Boolean.FALSE;

	private PeriodoDTO anioLectivoVigente;
	private List<AsinacionDTO> listaAsinacionDTO;
	private List<AsinacionDTO> listaAsinacionesSeleccionadas;
	private NivelDTO nivelDTOSeleccionado;
	private List<MateriaDTO> listaMaterias = new ArrayList<MateriaDTO>();
	private List<NivelDTO> listaNiveles = new ArrayList<NivelDTO>();
	private List<ParaleloDTO> listaParalelo = new ArrayList<ParaleloDTO>();
	private ParaleloDTO paraleloDTOSeleccionado;
	private List<MateriaEstadoPacialesDTO> listaMateriaEstadoPacialesDTOs;

	private MateriaEstadoPacialesDTO materiaEstadoSeleccionado;
	private List<EstudianteNotasParcial> listaEstudianteNotasParcials;

	// PARA CONSULTA DEL ESTUDIANTE
	private String cedulaEstudiante;
	private DatosEstudianteDTO datosEstudianteDTO;

	// PARA REPORTES
	private Integer codQuimestreSeleccionada;
	private ReporteDTO reporteDTO;

	private List<PeriodoDTO> listaPeriodoDTOs;
	private Integer codPeriodoSeleccionado;
	private Integer codMateriaSeleccionada;
	private Integer codParcialSeleccionado;

	// para la edision de notas
	private Integer codTipoNota;

	public PeriodoDTO getAnioLectivoVigente() {
		return anioLectivoVigente;
	}

	public void setAnioLectivoVigente(PeriodoDTO anioLectivoVigente) {
		this.anioLectivoVigente = anioLectivoVigente;
	}

	public MateriaEstadoPacialesDTO getMateriaEstadoSeleccionado() {
		return materiaEstadoSeleccionado;
	}

	public void setMateriaEstadoSeleccionado(MateriaEstadoPacialesDTO materiaEstadoSeleccionado) {
		this.materiaEstadoSeleccionado = materiaEstadoSeleccionado;
	}

	public List<EstudianteNotasParcial> getListaEstudianteNotasParcials() {
		return listaEstudianteNotasParcials;
	}

	public void setListaEstudianteNotasParcials(List<EstudianteNotasParcial> listaEstudianteNotasParcials) {
		this.listaEstudianteNotasParcials = listaEstudianteNotasParcials;
	}

	public List<AsinacionDTO> getListaAsinacionDTO() {
		return listaAsinacionDTO;
	}

	public void setListaAsinacionDTO(List<AsinacionDTO> listaAsinacionDTO) {
		this.listaAsinacionDTO = listaAsinacionDTO;
	}

	public NivelDTO getNivelDTOSeleccionado() {
		return nivelDTOSeleccionado;
	}

	public void setNivelDTOSeleccionado(NivelDTO nivelDTOSeleccionado) {
		this.nivelDTOSeleccionado = nivelDTOSeleccionado;
	}

	public Boolean getEstaInicializado() {
		return estaInicializado;
	}

	public void setEstaInicializado(Boolean estaInicializado) {
		this.estaInicializado = estaInicializado;
	}

	public List<MateriaDTO> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<MateriaDTO> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	public List<NivelDTO> getListaNiveles() {
		return listaNiveles;
	}

	public void setListaNiveles(List<NivelDTO> listaNiveles) {
		this.listaNiveles = listaNiveles;
	}

	public List<ParaleloDTO> getListaParalelo() {
		return listaParalelo;
	}

	public void setListaParalelo(List<ParaleloDTO> listaParalelo) {
		this.listaParalelo = listaParalelo;
	}

	public ParaleloDTO getParaleloDTOSeleccionado() {
		return paraleloDTOSeleccionado;
	}

	public void setParaleloDTOSeleccionado(ParaleloDTO paraleloDTOSeleccionado) {
		this.paraleloDTOSeleccionado = paraleloDTOSeleccionado;
	}

	public List<AsinacionDTO> getListaAsinacionesSeleccionadas() {
		if (listaAsinacionesSeleccionadas == null) {
			listaAsinacionesSeleccionadas = new ArrayList<AsinacionDTO>();
			return listaAsinacionDTO;
		} else {
			return listaAsinacionesSeleccionadas;
		}
	}

	public void setListaAsinacionesSeleccionadas(List<AsinacionDTO> listaAsinacionesSeleccionadas) {
		this.listaAsinacionesSeleccionadas = listaAsinacionesSeleccionadas;
	}

	public List<MateriaEstadoPacialesDTO> getListaMateriaEstadoPacialesDTOs() {
		return listaMateriaEstadoPacialesDTOs;
	}

	public void setListaMateriaEstadoPacialesDTOs(List<MateriaEstadoPacialesDTO> listaMateriaEstadoPacialesDTOs) {
		this.listaMateriaEstadoPacialesDTOs = listaMateriaEstadoPacialesDTOs;
	}

	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public DatosEstudianteDTO getDatosEstudianteDTO() {
		return datosEstudianteDTO;
	}

	public void setDatosEstudianteDTO(DatosEstudianteDTO datosEstudianteDTO) {
		this.datosEstudianteDTO = datosEstudianteDTO;
	}

	public Integer getCodQuimestreSeleccionada() {
		return codQuimestreSeleccionada;
	}

	public void setCodQuimestreSeleccionada(Integer codQuimestreSeleccionada) {
		this.codQuimestreSeleccionada = codQuimestreSeleccionada;
	}

	public ReporteDTO getReporteDTO() {
		return reporteDTO;
	}

	public void setReporteDTO(ReporteDTO reporteDTO) {
		this.reporteDTO = reporteDTO;
	}

	public List<PeriodoDTO> getListaPeriodoDTOs() {
		return listaPeriodoDTOs;
	}

	public void setListaPeriodoDTOs(List<PeriodoDTO> listaPeriodoDTOs) {
		this.listaPeriodoDTOs = listaPeriodoDTOs;
	}

	public Integer getCodPeriodoSeleccionado() {
		return codPeriodoSeleccionado;
	}

	public void setCodPeriodoSeleccionado(Integer codPeriodoSeleccionado) {
		this.codPeriodoSeleccionado = codPeriodoSeleccionado;
	}

	public Integer getCodMateriaSeleccionada() {
		return codMateriaSeleccionada;
	}

	public void setCodMateriaSeleccionada(Integer codMateriaSeleccionada) {
		this.codMateriaSeleccionada = codMateriaSeleccionada;
	}

	public Integer getCodParcialSeleccionado() {
		return codParcialSeleccionado;
	}

	public void setCodParcialSeleccionado(Integer codParcialSeleccionado) {
		this.codParcialSeleccionado = codParcialSeleccionado;
	}

	public Integer getCodTipoNota() {
		return codTipoNota;
	}

	public void setCodTipoNota(Integer codTipoNota) {
		this.codTipoNota = codTipoNota;
	}

}
