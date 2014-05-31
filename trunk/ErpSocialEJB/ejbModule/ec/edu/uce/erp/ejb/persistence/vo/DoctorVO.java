package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;

public class DoctorVO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Persona persona;
	private DoctorDTO doctorDTO;
	
	public DoctorVO ()
	{
		persona=new Persona();
		doctorDTO=new DoctorDTO();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}

	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}

}
