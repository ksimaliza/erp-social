package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;


@SessionScoped
@ManagedBean (name = "doctorDataManager")
public class DoctorDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DoctorDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private DoctorDTO doctorInsertar;
		
	private Persona doctorPersonaInsertar;
	private DoctorListDTO doctorBuscar;
	private List<DoctorListDTO> doctorListDTOs;
	
			
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.doctorInsertar=new DoctorDTO();
		this.doctorPersonaInsertar=new Persona();
		this.doctorBuscar=new DoctorListDTO();
		this.doctorListDTOs=new ArrayList<DoctorListDTO>();	
	}


	public DoctorDTO getDoctorInsertar() {
		return doctorInsertar;
	}


	public void setDoctorInsertar(DoctorDTO doctorInsertar) {
		this.doctorInsertar = doctorInsertar;
	}


	public Persona getDoctorPersonaInsertar() {
		return doctorPersonaInsertar;
	}


	public void setDoctorPersonaInsertar(Persona doctorPersonaInsertar) {
		this.doctorPersonaInsertar = doctorPersonaInsertar;
	}


	public DoctorListDTO getDoctorBuscar() {
		return doctorBuscar;
	}


	public void setDoctorBuscar(DoctorListDTO doctorBuscar) {
		this.doctorBuscar = doctorBuscar;
	}


	public List<DoctorListDTO> getDoctorListDTOs() {
		return doctorListDTOs;
	}


	public void setDoctorListDTOs(List<DoctorListDTO> doctorListDTOs) {
		this.doctorListDTOs = doctorListDTOs;
	}

	
}
