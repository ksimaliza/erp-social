package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the euc_doctor database table.
 * 
 */
@Entity
@Table(name="euc_doctor")
@NamedQuery(name="DoctorDTO.findAll", query="SELECT d FROM DoctorDTO d")
public class DoctorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_DOCTOR_DOCCODIGO_GENERATOR", sequenceName="EUC_DOCTOR_DOC_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_DOCTOR_DOCCODIGO_GENERATOR")
	@Column(name="doc_codigo")
	private Integer docCodigo;

	@Column(name="doc_persona")
	private Integer docPersona;
	
	@Column(name="doc_num_licencia_med")
	private String docNumLicenciaMed;
	
	@Column(name="doc_empresa")
	private Integer docEmpresa;

	public DoctorDTO() {
	}

	public Integer getDocCodigo() {
		return this.docCodigo;
	}

	public void setDocCodigo(Integer docCodigo) {
		this.docCodigo = docCodigo;
	}

	public Integer getDocPersona() {
		return this.docPersona;
	}

	public void setDocPersona(Integer docPersona) {
		this.docPersona = docPersona;
	}

	public String getDocNumLicenciaMed() {
		return docNumLicenciaMed;
	}

	public void setDocNumLicenciaMed(String docNumLicenciaMed) {
		this.docNumLicenciaMed = docNumLicenciaMed;
	}

	public Integer getDocEmpresa() {
		return docEmpresa;
	}

	public void setDocEmpresa(Integer docEmpresa) {
		this.docEmpresa = docEmpresa;
	}
    
    
}