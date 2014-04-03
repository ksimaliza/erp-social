package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean(name="marcaBienDataManager")
public class MarcaBienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	
	private MarcaBien marcaBienInstancia;
	private MarcaBien marcaBienEditar;
	private MarcaBien marcaBienBuscar;
	
	private List<MarcaBien> listMarcaBien;
	
	public MarcaBienDataManager () {
		super();
		this.marcaBienInstancia = new MarcaBien();
		this.marcaBienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.marcaBienEditar = new MarcaBien();
		this.marcaBienBuscar = new MarcaBien();
		this.listMarcaBien = new ArrayList<MarcaBien>();
	}

	/**
	 * @return the marcaBienInstancia
	 */
	public MarcaBien getMarcaBienInstancia() {
		return marcaBienInstancia;
	}

	/**
	 * @param marcaBienInstancia the marcaBienInstancia to set
	 */
	public void setMarcaBienInstancia(MarcaBien marcaBienInstancia) {
		this.marcaBienInstancia = marcaBienInstancia;
	}

	/**
	 * @return the marcaBienEditar
	 */
	public MarcaBien getMarcaBienEditar() {
		return marcaBienEditar;
	}

	/**
	 * @param marcaBienEditar the marcaBienEditar to set
	 */
	public void setMarcaBienEditar(MarcaBien marcaBienEditar) {
		this.marcaBienEditar = marcaBienEditar;
	}

	/**
	 * @return the marcaBienBuscar
	 */
	public MarcaBien getMarcaBienBuscar() {
		return marcaBienBuscar;
	}

	/**
	 * @param marcaBienBuscar the marcaBienBuscar to set
	 */
	public void setMarcaBienBuscar(MarcaBien marcaBienBuscar) {
		this.marcaBienBuscar = marcaBienBuscar;
	}

	/**
	 * @return the listMarcaBien
	 */
	public List<MarcaBien> getListMarcaBien() {
		return listMarcaBien;
	}

	/**
	 * @param listMarcaBien the listMarcaBien to set
	 */
	public void setListMarcaBien(List<MarcaBien> listMarcaBien) {
		this.listMarcaBien = listMarcaBien;
	}
	
}
