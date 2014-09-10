package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_mes_registro_vie database table.
 * 
 */
@Entity
@Table(name="asi_mes_registro_vie")
@NamedQuery(name="MesRegistroVieDTO.findAll", query="SELECT m FROM MesRegistroVieDTO m")
public class MesRegistroVieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private double mes;

	public MesRegistroVieDTO() {
	}

	public double getMes() {
		return this.mes;
	}

	public void setMes(double mes) {
		this.mes = mes;
	}

}