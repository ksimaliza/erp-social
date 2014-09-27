package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class ComunionVO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	
	private Persona mad_pad;
	private Persona sacerdotePersona;
	private Persona doctorPersona;
	private Persona asignadoPersona;
	
	private PrimeraComunionDTO comunion;
	private SacerdoteDTO sacerdote;
	private DoctorDTO doctor;

	public ComunionVO() {
		mad_pad=new Persona();
		comunion=new PrimeraComunionDTO();
		sacerdote= new SacerdoteDTO();
		doctor=new DoctorDTO();
		doctorPersona=new Persona();
		sacerdotePersona=new Persona();
		asignadoPersona=new Persona();
	}

	public Persona getMad_pad() {
		return mad_pad;
	}

	public void setMad_pad(Persona mad_pad) {
		this.mad_pad = mad_pad;
	}

	public Persona getSacerdotePersona() {
		return sacerdotePersona;
	}

	public void setSacerdotePersona(Persona sacerdotePersona) {
		this.sacerdotePersona = sacerdotePersona;
	}

	public Persona getDoctorPersona() {
		return doctorPersona;
	}

	public void setDoctorPersona(Persona doctorPersona) {
		this.doctorPersona = doctorPersona;
	}

	public PrimeraComunionDTO getComunion() {
		return comunion;
	}

	public void setComunion(PrimeraComunionDTO comunion) {
		this.comunion = comunion;
	}

	public SacerdoteDTO getSacerdote() {
		return sacerdote;
	}

	public void setSacerdote(SacerdoteDTO sacerdote) {
		this.sacerdote = sacerdote;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	public Persona getAsignadoPersona() {
		return asignadoPersona;
	}

	public void setAsignadoPersona(Persona asignadoPersona) {
		this.asignadoPersona = asignadoPersona;
	}
	
	

}
