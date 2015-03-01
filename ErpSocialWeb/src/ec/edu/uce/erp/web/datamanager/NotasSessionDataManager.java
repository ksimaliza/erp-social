package ec.edu.uce.erp.web.datamanager;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.EstudianteNotasParcial;
import ec.edu.uce.erp.ejb.persistence.util.dto.MateriaEstadoPacialesDTO;

@SessionScoped
@ManagedBean
public class NotasSessionDataManager {

	private MateriaEstadoPacialesDTO materiaEstadoSeleccionado;
	private List<EstudianteNotasParcial> listaEstudianteNotasParcials;
	private PeriodoDTO anioLectivoVigente;
	private Integer codTipoNota;

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

	public PeriodoDTO getAnioLectivoVigente() {
		return anioLectivoVigente;
	}

	public void setAnioLectivoVigente(PeriodoDTO anioLectivoVigente) {
		this.anioLectivoVigente = anioLectivoVigente;
	}

	public Integer getCodTipoNota() {
		return codTipoNota;
	}

	public void setCodTipoNota(Integer codTipoNota) {
		this.codTipoNota = codTipoNota;
	}

}
