package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;

public class FiltroFechaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer anio;
	private Integer mes;
	private Integer dia;
	
	
	public FiltroFechaDTO() {
	
	}


	public Integer getAnio() {
		return anio;
	}


	public Integer getMes() {
		return mes;
	}


	public Integer getDia() {
		return dia;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	
	
	

	
}
