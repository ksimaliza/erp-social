package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class DefuncionVO implements Serializable {

private static final long serialVersionUID = 1L;
	
	
	private Persona difunto;
	private Persona conyuge;
	private Persona madre;
	private Persona padre;
	private DefuncionDTO defuncion;
	private SacerdoteDTO sacerdote;
	private DoctorDTO doctor;
	

	public DefuncionVO() {
		difunto=new Persona();
		conyuge=new Persona();
		madre=new Persona();
		padre=new Persona();
		sacerdote= new SacerdoteDTO();
		doctor=new DoctorDTO();
		
		}

	public Persona getDifunto() {
		return difunto;
	}

	public void setDifunto(Persona difunto) {
		this.difunto = difunto;
	}

	public Persona getConyuge() {
		return conyuge;
	}

	public void setConyuge(Persona conyuge) {
		this.conyuge = conyuge;
	}

	public DefuncionDTO getDefuncion() {
		return defuncion;
	}

	public void setDefuncion(DefuncionDTO defuncion) {
		this.defuncion = defuncion;
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

	
	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}
	
	

}
