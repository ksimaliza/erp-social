package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cargo_tbl database table.
 * 
 */
@Entity
@Table(name="cargo_tbl")
public class CargoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="car_pk")
	private Integer carPk;

	@Column(name="car_codigo")
	private Integer carCodigo;

	@Column(name="car_nombre")
	private String carNombre;

    public CargoTbl() {
    }

	public Integer getCarPk() {
		return this.carPk;
	}

	public void setCarPk(Integer carPk) {
		this.carPk = carPk;
	}

	public Integer getCarCodigo() {
		return this.carCodigo;
	}

	public void setCarCodigo(Integer carCodigo) {
		this.carCodigo = carCodigo;
	}

	public String getCarNombre() {
		return this.carNombre;
	}

	public void setCarNombre(String carNombre) {
		this.carNombre = carNombre;
	}

}