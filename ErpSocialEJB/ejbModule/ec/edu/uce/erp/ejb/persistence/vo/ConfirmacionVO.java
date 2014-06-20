package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class ConfirmacionVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Persona confirmado;
	private Persona mad_pad;
	private Persona sacerdotePersona;
	
	private ConfirmacionDTO confirmacion;
	private SacerdoteDTO sacerdote;
	private DoctorDTO doctorVO;

	public ConfirmacionVO() {
		confirmado=new Persona();
		mad_pad=new Persona();
		confirmacion=new ConfirmacionDTO();
		sacerdote= new SacerdoteDTO();
		doctorVO=new DoctorDTO();
		sacerdotePersona=new Persona();
	
	}

	public Persona getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Persona confirmado) {
		this.confirmado = confirmado;
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


	public ConfirmacionDTO getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(ConfirmacionDTO confirmacion) {
		this.confirmacion = confirmacion;
	}

	public SacerdoteDTO getSacerdote() {
		return sacerdote;
	}

	public void setSacerdote(SacerdoteDTO sacerdote) {
		this.sacerdote = sacerdote;
	}

	public DoctorDTO getDoctorVO() {
		return doctorVO;
	}

	public void setDoctorVO(DoctorDTO doctorVO) {
		this.doctorVO = doctorVO;
	}
	
		
		
	
}
