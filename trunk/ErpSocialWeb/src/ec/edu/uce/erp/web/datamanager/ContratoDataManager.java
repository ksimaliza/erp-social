package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "contratoDataManager")

public class ContratoDataManager extends BaseDataManager {
private static final Logger slf4jLogger = LoggerFactory.getLogger(ContratoDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona difuntoInsertar;
	private ContratoDTO contratoDTO;
	private List<ContratoListDTO> contratoListDTOs;
	private Date fechaInicio;
	private Date fechaFin;
	private int nichoCodigo;
	private ContratoListDTO contratoListDTO;
	private NichoListDTO nichoListDTO;
	private int formaPagoCodigo;
	private List<CatalogoEucaristiaDTO> formaPagoListDTOs;
	private DefuncionListDTO defuncionListDTO;
	private List<NichoListDTO>  nichoListDTOs;
	private Persona beneficiariInsertar;
	private ContratoListDTO contratoListDTOEditar;
	private NichoDTO nichoDTOEditar;
	private Boolean exportDesactivado;
	private CatalogoEucaristiaDTO formaPagoList;
	
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.difuntoInsertar = new Persona();		
		this.contratoDTO= new ContratoDTO();
		this.contratoListDTO= new ContratoListDTO();
		this.nichoListDTO = new NichoListDTO();
		fechaInicio=new Date();
		fechaFin=new Date();
		this.contratoListDTOs= new ArrayList<ContratoListDTO>();
		this.formaPagoListDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.defuncionListDTO=new DefuncionListDTO();
		this.nichoListDTOs=new ArrayList<NichoListDTO>();
		this.beneficiariInsertar=new Persona();
		this.contratoListDTOEditar=new ContratoListDTO();
		this.nichoDTOEditar=new NichoDTO();
		exportDesactivado=true;
		this.formaPagoList=new CatalogoEucaristiaDTO();
	}

	public Persona getDifuntoInsertar() {
		return difuntoInsertar;
	}


	public void setDifuntoInsertar(Persona difuntoInsertar) {
		this.difuntoInsertar = difuntoInsertar;
	}


	public ContratoDTO getContratoDTO() {
		return contratoDTO;
	}


	public ContratoListDTO getContratoListDTOEditar() {
		return contratoListDTOEditar;
	}

	public void setContratoListDTOEditar(ContratoListDTO contratoListDTOEditar) {
		this.contratoListDTOEditar = contratoListDTOEditar;
	}

	public void setContratoDTO(ContratoDTO contratoDTO) {
		this.contratoDTO = contratoDTO;
	}


	public List<ContratoListDTO> getContratoListDTOs() {
		return contratoListDTOs;
	}



	public void setContratoListDTOs(List<ContratoListDTO> contratoListDTOs) {
		this.contratoListDTOs = contratoListDTOs;
	}




	public Date getFechaInicio() {
		return fechaInicio;
	}




	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getNichoCodigo() {
		return nichoCodigo;
	}

	public void setNichoCodigo(int nichoCodigo) {
		this.nichoCodigo = nichoCodigo;
	}

	public ContratoListDTO getContratoListDTO() {
		return contratoListDTO;
	}

	public void setContratoListDTO(ContratoListDTO contratoListDTO) {
		this.contratoListDTO = contratoListDTO;
	}

	public NichoListDTO getNichoListDTO() {
		return nichoListDTO;
	}

	public void setNichoListDTO(NichoListDTO nichoListDTO) {
		this.nichoListDTO = nichoListDTO;
	}




	public int getFormaPagoCodigo() {
		return formaPagoCodigo;
	}




	public void setFormaPagoCodigo(int formaPagoCodigo) {
		this.formaPagoCodigo = formaPagoCodigo;
	}




	public List<CatalogoEucaristiaDTO> getFormaPagoListDTOs() {
		return formaPagoListDTOs;
	}




	public void setFormaPagoListDTOs(List<CatalogoEucaristiaDTO> formaPagoListDTOs) {
		this.formaPagoListDTOs = formaPagoListDTOs;
	}




	public DefuncionListDTO getDefuncionListDTO() {
		return defuncionListDTO;
	}




	public void setDefuncionListDTO(DefuncionListDTO defuncionListDTO) {
		this.defuncionListDTO = defuncionListDTO;
	}




	public List<NichoListDTO> getNichoListDTOs() {
		return nichoListDTOs;
	}




	public void setNichoListDTOs(List<NichoListDTO> nichoListDTOs) {
		this.nichoListDTOs = nichoListDTOs;
	}




	public Persona getBeneficiariInsertar() {
		return beneficiariInsertar;
	}




	public void setBeneficiariInsertar(Persona beneficiariInsertar) {
		this.beneficiariInsertar = beneficiariInsertar;
	}

	public NichoDTO getNichoDTOEditar() {
		return nichoDTOEditar;
	}

	public void setNichoDTOEditar(NichoDTO nichoDTOEditar) {
		this.nichoDTOEditar = nichoDTOEditar;
	}

	public Boolean getExportDesactivado() {
		return exportDesactivado;
	}

	public void setExportDesactivado(Boolean exportDesactivado) {
		this.exportDesactivado = exportDesactivado;
	}

	public CatalogoEucaristiaDTO getFormaPagoList() {
		return formaPagoList;
	}

	public void setFormaPagoList(CatalogoEucaristiaDTO formaPagoList) {
		this.formaPagoList = formaPagoList;
	}


	
	
	


}
