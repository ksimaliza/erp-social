package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean(name = "estudianteDataManager")
public class EstudianteDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;

	private EstudianteDTO estudianteInstancia;
	private EstudianteVO estudianteEditar;
	private EstudianteListDTO estudianteBuscar;
	private List<EstudianteListDTO> listaEstudianteListDTOs;

	private Persona estudiantePersonaInsertar;
	private Persona estudiantePersonaEditar;
	private Persona estudiantePersonaBuscar;
	private int estadoCodigo;

	private Date fechaInsertar;

	private List<RepresentanteListDTO> representanteList;

	private Integer representanteCode;

	public EstudianteDataManager() {
		this.representanteList = new ArrayList<RepresentanteListDTO>();
		this.estudianteInstancia = new EstudianteDTO();
		this.estudianteEditar = new EstudianteVO();
		this.estudianteBuscar = new EstudianteListDTO();
		this.listaEstudianteListDTOs = new ArrayList<EstudianteListDTO>();

		this.estudiantePersonaInsertar = new Persona();
		this.estudiantePersonaEditar = new Persona();
		this.estudiantePersonaBuscar = new Persona();

		this.fechaInsertar = new Date();

	}
	
	
	@PostConstruct
	public void inicializarObjetos() {

		slf4jLogger.info("inicializarObjetos");

	}

	public EstudianteDTO getEstudianteInstancia() {
		return estudianteInstancia;
	}

	public void setEstudianteInstancia(EstudianteDTO estudianteInstancia) {
		this.estudianteInstancia = estudianteInstancia;
	}

	public EstudianteVO getEstudianteEditar() {
		return estudianteEditar;
	}

	public void setEstudianteEditar(EstudianteVO estudianteEditar) {
		this.estudianteEditar = estudianteEditar;
	}

	public Persona getEstudiantePersonaInsertar() {
		return estudiantePersonaInsertar;
	}

	public void setEstudiantePersonaInsertar(Persona estudiantePersonaInsertar) {
		this.estudiantePersonaInsertar = estudiantePersonaInsertar;
	}

	public Persona getEstudiantePersonaEditar() {
		return estudiantePersonaEditar;
	}

	public void setEstudiantePersonaEditar(Persona estudiantePersonaEditar) {
		this.estudiantePersonaEditar = estudiantePersonaEditar;
	}

	public Persona getEstudiantePersonaBuscar() {
		return estudiantePersonaBuscar;
	}

	public void setEstudiantePersonaBuscar(Persona estudiantePersonaBuscar) {
		this.estudiantePersonaBuscar = estudiantePersonaBuscar;
	}

	public EstudianteListDTO getEstudianteBuscar() {
		return estudianteBuscar;
	}

	public void setEstudianteBuscar(EstudianteListDTO estudianteBuscar) {
		this.estudianteBuscar = estudianteBuscar;
	}

	public List<EstudianteListDTO> getListaEstudianteListDTOs() {
		return listaEstudianteListDTOs;
	}

	public void setListaEstudianteListDTOs(
			List<EstudianteListDTO> listaEstudianteListDTOs) {
		this.listaEstudianteListDTOs = listaEstudianteListDTOs;
	}

	public int getEstadoCodigo() {
		return estadoCodigo;
	}

	public void setEstadoCodigo(int estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	public Date getFechaInsertar() {
		return fechaInsertar;
	}

	public void setFechaInsertar(Date fechaInsertar) {
		this.fechaInsertar = fechaInsertar;
	}

	public List<RepresentanteListDTO> getRepresentanteList() {
		return representanteList;
	}

	public void setRepresentanteList(
			List<RepresentanteListDTO> representanteList) {
		this.representanteList = representanteList;
	}

	public Integer getRepresentanteCode() {
		return representanteCode;
	}

	public void setRepresentanteCode(Integer representanteCode) {
		this.representanteCode = representanteCode;
	}

}
