package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import ec.edu.uce.erp.common.util.UtilAplication;


/**
 * The persistent class for the rep_nivel_estudiante database table.
 * 
 */
@Entity
@Table(name="rep_nivel_estudiante")
@NamedQuery(name="RepNivelEstudianteDTO.findAll", query="SELECT r FROM RepNivelEstudianteDTO r")
public class RepNivelEstudianteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="npa_nivel")
	private Integer npaNivel;

	@Column(name="npa_paralelo")
	private Integer npaParalelo;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_nombres")
	private String perNombres;

	@Id
	@Column(name="reg_codigo")
	private Integer regCodigo;

	@Column(name="reg_foto")
	private String regFoto;

	@Column(name="reg_foto_byte")
	private byte[] regFotoByte;

	public RepNivelEstudianteDTO() {
	}

	public Integer getNpaNivel() {
		return this.npaNivel;
	}

	public void setNpaNivel(Integer npaNivel) {
		this.npaNivel = npaNivel;
	}

	public Integer getNpaParalelo() {
		return this.npaParalelo;
	}

	public void setNpaParalelo(Integer npaParalelo) {
		this.npaParalelo = npaParalelo;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public Integer getRegCodigo() {
		return this.regCodigo;
	}

	public void setRegCodigo(Integer regCodigo) {
		this.regCodigo = regCodigo;
	}

	public String getRegFoto() {
		if(this.regFotoByte!=null)
			this.regFoto=UtilAplication.saveToDisk(regFotoByte, regFoto);
		return this.regFoto;
	}

	public void setRegFoto(String regFoto) {
		this.regFoto = regFoto;
	}

	public byte[] getRegFotoByte() {
		return this.regFotoByte;
	}

	public void setRegFotoByte(byte[] regFotoByte) {
		this.regFotoByte = regFotoByte;
	}

}