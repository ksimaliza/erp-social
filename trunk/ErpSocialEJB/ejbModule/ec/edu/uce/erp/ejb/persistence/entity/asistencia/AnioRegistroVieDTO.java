package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_anio_registro_vie database table.
 * 
 */
@Entity
@Table(name="asi_anio_registro_vie")
@NamedQuery(name="AnioRegistroVieDTO.findAll", query="SELECT a FROM AnioRegistroVieDTO a")
public class AnioRegistroVieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private double anio;

	public AnioRegistroVieDTO() {
	}

	public double getAnio() {
		return this.anio;
	}

	public void setAnio(double anio) {
		this.anio = anio;
	}

}