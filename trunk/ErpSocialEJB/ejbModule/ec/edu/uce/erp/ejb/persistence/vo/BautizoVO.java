package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class BautizoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Persona bautizado;
	private Persona madre;
	private Persona padre;
	private Persona padrino;
	private Persona madrina;
	private BautizoDTO bautizo;
	private SacerdoteDTO sacerdote;

	public BautizoVO() {
	
		bautizado=new Persona();
		padrino=new Persona();
		madrina=new Persona();
		bautizo=new BautizoDTO();
		madre=new Persona();
		padre=new Persona();
		sacerdote=new SacerdoteDTO();
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

	public SacerdoteDTO getSacerdote() {
		return sacerdote;
	}

	public void setSacerdote(SacerdoteDTO sacerdote) {
		this.sacerdote = sacerdote;
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

	public Persona getMadre() {
		return madre;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}


	
	
}
