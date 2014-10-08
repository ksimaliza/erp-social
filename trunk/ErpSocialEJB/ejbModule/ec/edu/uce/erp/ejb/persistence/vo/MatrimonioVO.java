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
	private Persona padre_novia;
	private Persona padre_novio;
	private Persona madre_novia;
	private Persona madre_novio;
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
		padre_novia=new Persona();
		padre_novio=new Persona();
		madre_novia=new Persona();
		padre_novio=new Persona();
		matrimonio=new MatrimonioDTO();
		sacerdote= new SacerdoteDTO();
		sacerdotePersona=new Persona();
	}


	public Persona getPadre_novia() {
		return padre_novia;
	}


	public void setPadre_novia(Persona padre_novia) {
		this.padre_novia = padre_novia;
	}


	public Persona getPadre_novio() {
		return padre_novio;
	}


	public void setPadre_novio(Persona padre_novio) {
		this.padre_novio = padre_novio;
	}


	public Persona getMadre_novia() {
		return madre_novia;
	}


	public void setMadre_novia(Persona madre_novia) {
		this.madre_novia = madre_novia;
	}


	public Persona getMadre_novio() {
		return madre_novio;
	}


	public void setMadre_novio(Persona madre_novio) {
		this.madre_novio = madre_novio;
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
