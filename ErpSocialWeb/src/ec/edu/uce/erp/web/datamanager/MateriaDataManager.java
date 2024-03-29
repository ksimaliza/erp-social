package ec.edu.uce.erp.web.datamanager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "materiaDataManager")
public class MateriaDataManager extends BaseDataManager{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MateriaDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private MateriaDTO materiaInstancia;
	private MateriaDTO materiaEditar;
	private MateriaDTO materiaBuscar;
	private List<MateriaDTO> materiaDTOs;
	
	private int materiaCod;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.materiaInstancia = new MateriaDTO();		
		this.materiaEditar = new MateriaDTO();
		this.materiaBuscar = new MateriaDTO();
			
	}

	public MateriaDTO getMateriaInstancia() {
		return materiaInstancia;
	}

	public void setMateriaInstancia(MateriaDTO materiaInstancia) {
		this.materiaInstancia = materiaInstancia;
	}

	public MateriaDTO getMateriaEditar() {
		return materiaEditar;
	}

	public void setMateriaEditar(MateriaDTO materiaEditar) {
		this.materiaEditar = materiaEditar;
	}

	public MateriaDTO getMateriaBuscar() {
		return materiaBuscar;
	}

	public void setMateriaBuscar(MateriaDTO materiaBuscar) {
		this.materiaBuscar = materiaBuscar;
	}

	public List<MateriaDTO> getMateriaDTOs() {
		return materiaDTOs;
	}

	public void setMateriaDTOs(List<MateriaDTO> materiaDTOs) {
		this.materiaDTOs = materiaDTOs;
	}

	public int getMateriaCod() {
		return materiaCod;
	}

	public void setMateriaCod(int materiaCod) {
		this.materiaCod = materiaCod;
	}
	
	
	

}
