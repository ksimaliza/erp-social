package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class BautizoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Persona bautizado;
	private Persona padrino;
	private Persona madrina;
	private Persona sacerdotePersona;
	private Persona doctorVOPersona;
	private BautizoDTO bautizo;
	private SacerdoteDTO sacerdote;
	private DoctorDTO doctorVO;

	public BautizoVO() {
	
		bautizado=new Persona();
		padrino=new Persona();
		madrina=new Persona();
		bautizo=new BautizoDTO();
		sacerdote= new SacerdoteDTO();
		doctorVO=new DoctorDTO();
		sacerdotePersona=new Persona();
		doctorVOPersona=new Persona();
		
	}

	public Persona getBautizado() {
		return bautizado;
	}

	public void setBautizado(Persona bautizado) {
		this.bautizado = bautizado;
	}

	public Persona getPadrino() {
		return padrino;
	}

	public void setPadrino(Persona padrino) {
		this.padrino = padrino;
	}

	public Persona getMadrina() {
		return madrina;
	}

	public void setMadrina(Persona madrina) {
		this.madrina = madrina;
	}

	public BautizoDTO getBautizo() {
		return bautizo;
	}

	public void setBautizo(BautizoDTO bautizo) {
		this.bautizo = bautizo;
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

	public Persona getSacerdotePersona() {
		return sacerdotePersona;
	}

	public void setSacerdotePersona(Persona sacerdotePersona) {
		this.sacerdotePersona = sacerdotePersona;
	}

	public Persona getDoctorVOPersona() {
		return doctorVOPersona;
	}

	public void setDoctorVOPersona(Persona doctorVOPersona) {
		this.doctorVOPersona = doctorVOPersona;
	}


	
	
}
