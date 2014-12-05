package ec.edu.uce.erp.web.datamanager;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "seccionNichoDataManager")
public class SeccionNichoDataManager extends BaseDataManager{

private static final Logger slf4jLogger = LoggerFactory.getLogger(SeccionNichoDataManager.class);
	
private static final long serialVersionUID = 1L;

private CatalogoEucaristiaDTO seccionNichoInsertar;
private List<CatalogoEucaristiaDTO> seccionNichoDTOs;
private CatalogoEucaristiaDTO seccionNichoBuscar;

@PostConstruct
public void inicializarObjetos () {
	slf4jLogger.info("inicializarObjetos");
	this.seccionNichoDTOs=new ArrayList<CatalogoEucaristiaDTO>();
	this.seccionNichoInsertar=new CatalogoEucaristiaDTO();
	this.seccionNichoBuscar=new CatalogoEucaristiaDTO();

}

public CatalogoEucaristiaDTO getSeccionNichoInsertar() {
	return seccionNichoInsertar;
}

public void setSeccionNichoInsertar(CatalogoEucaristiaDTO seccionNichoInsertar) {
	this.seccionNichoInsertar = seccionNichoInsertar;
}

public List<CatalogoEucaristiaDTO> getSeccionNichoDTOs() {
	return seccionNichoDTOs;
}

public void setSeccionNichoDTOs(List<CatalogoEucaristiaDTO> seccionNichoDTOs) {
	this.seccionNichoDTOs = seccionNichoDTOs;
}

public CatalogoEucaristiaDTO getSeccionNichoBuscar() {
	return seccionNichoBuscar;
}

public void setSeccionNichoBuscar(CatalogoEucaristiaDTO seccionNichoBuscar) {
	this.seccionNichoBuscar = seccionNichoBuscar;
}
	
	

}
