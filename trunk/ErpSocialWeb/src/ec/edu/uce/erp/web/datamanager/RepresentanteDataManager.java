package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.vo.RepresentanteVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "representanteDataManager")
public class RepresentanteDataManager extends BaseDataManager{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private RepresentanteVO representanteInstancia;
	private RepresentanteVO representanteEditar;
	private RepresentanteVO representanteBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.representanteInstancia = new RepresentanteVO();		
		this.representanteEditar = new RepresentanteVO();
		this.representanteBuscar = new RepresentanteVO();
			
	}

	public RepresentanteVO getRepresentanteInstancia() {
		return representanteInstancia;
	}

	public void setRepresentanteInstancia(RepresentanteVO representanteInstancia) {
		this.representanteInstancia = representanteInstancia;
	}

	public RepresentanteVO getRepresentanteEditar() {
		return representanteEditar;
	}

	public void setRepresentanteEditar(RepresentanteVO representanteEditar) {
		this.representanteEditar = representanteEditar;
	}

	public RepresentanteVO getRepresentanteBuscar() {
		return representanteBuscar;
	}

	public void setRepresentanteBuscar(RepresentanteVO representanteBuscar) {
		this.representanteBuscar = representanteBuscar;
	}
	
	
}
