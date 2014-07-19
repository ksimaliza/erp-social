package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class MatrimonioVO  implements Serializable{
private static final long serialVersionUID = 1L;
	
	private Persona novio;
	private Persona novia;
	private Persona mad_novio;
	private Persona mad_novia;
	private Persona pad_novio;
	private Persona pad_novia;
	private Persona sacerdotePersona;
	
	private MatrimonioDTO matrimonio;
	private SacerdoteDTO sacerdote;
	

	public MatrimonioVO() {
	
		novio=new Persona();
		novia=new Persona();
		mad_novio=new Persona();
		mad_novia=new Persona();
		pad_novio=new Persona();
		pad_novia=new Persona();
		matrimonio=new MatrimonioDTO();
		sacerdote= new SacerdoteDTO();
		sacerdotePersona=new Persona();
	}


	public Persona getNovio() {
		return novio;
	}


	public void setNovio(Persona novio) {
		this.novio = novio;
	}


	public Persona getNovia() {
		return novia;
	}


	public void setNovia(Persona novia) {
		this.novia = novia;
	}


	public Persona getMad_novio() {
		return mad_novio;
	}


	public void setMad_novio(Persona mad_novio) {
		this.mad_novio = mad_novio;
	}


	public Persona getMad_novia() {
		return mad_novia;
	}


	public void setMad_novia(Persona mad_novia) {
		this.mad_novia = mad_novia;
	}


	public Persona getPad_novio() {
		return pad_novio;
	}


	public void setPad_novio(Persona pad_novio) {
		this.pad_novio = pad_novio;
	}


	public Persona getPad_novia() {
		return pad_novia;
	}


	public void setPad_novia(Persona pad_novia) {
		this.pad_novia = pad_novia;
	}


	public Persona getSacerdotePersona() {
		return sacerdotePersona;
	}


	public void setSacerdotePersona(Persona sacerdotePersona) {
		this.sacerdotePersona = sacerdotePersona;
	}


	public MatrimonioDTO getMatrimonio() {
		return matrimonio;
	}


	public void setMatrimonio(MatrimonioDTO matrimonio) {
		this.matrimonio = matrimonio;
	}


	public SacerdoteDTO getSacerdote() {
		return sacerdote;
	}


	public void setSacerdote(SacerdoteDTO sacerdote) {
		this.sacerdote = sacerdote;
	}
		
	
	

}
