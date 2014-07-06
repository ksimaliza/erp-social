/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.MenuUsuario;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;
import ec.edu.uce.erp.web.datamanager.UsuarioDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean (name = "usuarioController")
public class UsuarioController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UsuarioController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	
	/**
	 * @param usuarioDataManager the usuarioDataManager to set
	 */
	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}
	
	private Integer idEmpresaSeleccionada;
	private Integer idPerfilSeleccionado;
	private List<SelectItem> catalogoPerfiles;
	private TreeNode treeNodeModulos;
	private TreeNode[] selectedNodes;
//	private List<TreeNode> selectedNodes;
	
	public UsuarioController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.catalogoPerfiles = new ArrayList<SelectItem>();
	}
	
	/*
	 * Metodos
	 */
	
	/**
	 * Registrar un usuario en la BD
	 */
	public void registrarUsuario () {
		slf4jLogger.info("registrarUsuario");
		try {
			
			if (selectedNodes == null || selectedNodes.length==0) {
				MensajesWebController.aniadirMensajeInformacion("Debe seleccionar al menos una opcion de menu");
			} else {
				this.usuarioDataManager.getUsuarioInstancia().setSegtMenuUsuarios(asignarDatosMenuUsuario ());
				this.usuarioDataManager.getUsuarioInstancia().getSegtPerfil().setIdPerfil(idPerfilSeleccionado);
				this.usuarioDataManager.getUsuarioInstancia().setUsuarioRegistro(this.usuarioDataManager.getUsuarioSession());
				this.usuarioDataManager.getUsuarioInstancia().getEmpresaTbl().setEmrPk(idEmpresaSeleccionada);
				this.servicioAdministracion.registrarUsuario(usuarioDataManager.getUsuarioInstancia());
				this.usuarioDataManager.setUsuarioInstancia(new Usuario());
				this.usuarioDataManager.getUsuarioInstancia().setSegtPerfil(new Perfil());
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.registrar");
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	private List<MenuUsuario> asignarDatosMenuUsuario () {
		List<MenuUsuario> listMenuUsuario = new ArrayList<MenuUsuario>();
		for (TreeNode treeNode : selectedNodes) {
			
			if (treeNode.getData() instanceof Menu) {
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setIdMenu(((Menu)treeNode.getData()).getIdMenu());
				listMenuUsuario.add(menuUsuario);
			}
			
		}
		
		return listMenuUsuario;
	}
	
	public void buscarUsuario () {
		slf4jLogger.info("buscarUsuario");
		
		try {
			
			List<Usuario> usuariosCol = this.servicioAdministracion.buscarUsuarios(this.usuarioDataManager.getUsuarioBuscar());
			
			if (CollectionUtils.isEmpty(usuariosCol)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.usuarioDataManager.getListaUsuario().clear();
			} else {
				this.usuarioDataManager.setListaUsuario(usuariosCol);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarUsuario {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void actualizarUsuario () {
		
		slf4jLogger.info("actualizarUsuario");
		
		Usuario usuarioUpdate = null;
		Usuario usuarioClone = SerializationUtils.clone(this.usuarioDataManager.getUsuarioEditar());
		
		try {
			
			if (selectedNodes == null || selectedNodes.length==0) {
				MensajesWebController.aniadirMensajeInformacion("Debe seleccionar al menos una opcion de menu");
			} else {
				this.usuarioDataManager.getUsuarioEditar().setSegtMenuUsuarios(asignarDatosMenuUsuario ());
				this.usuarioDataManager.getUsuarioEditar().setUsuarioRegistro(this.usuarioDataManager.getUsuarioSession());
				this.usuarioDataManager.getUsuarioEditar().getEmpresaTbl().setEmrPk(idEmpresaSeleccionada);
				usuarioUpdate = this.servicioAdministracion.actualizarUsuario(usuarioDataManager.getUsuarioEditar());
				this.usuarioDataManager.setUsuarioEditar(new Usuario());
				this.usuarioDataManager.getUsuarioEditar().setSegtPerfil(new Perfil());
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarUsuario {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		} finally {
			if (usuarioUpdate == null) {
				this.usuarioDataManager.setUsuarioEditar(usuarioClone);
			}
		}
		
	}
	
	/**
	 * Obtener los perfiles asociados a una empresa
	 */
	public void obtenerPerfilesEmpresa () {
		
		if (idEmpresaSeleccionada != null) {
			
			Perfil perfilPlantilla = new Perfil();
			perfilPlantilla.setNpEmpresaDTO(new Empresa());
			perfilPlantilla.getNpEmpresaDTO().setEmrPk(idEmpresaSeleccionada);
			
			try {
				
				this.catalogoPerfiles.clear();
				
				List<Perfil> perfilCol = this.servicioAdministracion.buscarPerfileEmpresa(perfilPlantilla);
				
				if (CollectionUtils.isNotEmpty(perfilCol)) {
					
					catalogoPerfiles.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(perfilCol, "idPerfil", "nombrePerfil"));
					
				} else {
					MensajesWebController.aniadirMensajeAdvertencia("erp.usuario.mensaje.error.perfiles");
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al obtenerPerfilesEmpresa {}", e.toString());
				MensajesWebController.aniadirMensajeAdvertencia("erp.usuario.mensaje.error.perfiles");
			} finally {
				perfilPlantilla = null;
			}
			
		}
		
	}
	
	public void cargarDatosUsuario (Usuario usuario) {
		
		if (usuario != null) {
			this.idEmpresaSeleccionada = usuario.getEmpresaTbl().getEmrPk();
			this.idPerfilSeleccionado = usuario.getSegtPerfil().getIdPerfil();
			this.obtenerModuloMenu(idPerfilSeleccionado);
			this.obtenerPerfilesEmpresa();
			this.asignarSeleccionArbol(usuario.getSegtMenuUsuarios());
		}
		
	}
	
	public void desactivarUsuario () {
		
		slf4jLogger.info("desactivarUsuario");
		
		try {
			
			this.usuarioDataManager.getUsuarioEditar().setUsuarioRegistro(this.usuarioDataManager.getUsuarioSession());
			this.usuarioDataManager.getUsuarioEditar().setEstado(usuarioDataManager.getEstadoInactivo());
			Usuario usuario = servicioAdministracion.actualizarUsuario(this.usuarioDataManager.getUsuarioEditar());
			
			if (usuario != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void activarUsuario () {
		
		slf4jLogger.info("activarUsuario");
		
		try {
			
			this.usuarioDataManager.getUsuarioEditar().setUsuarioRegistro(this.usuarioDataManager.getUsuarioSession());
			this.usuarioDataManager.getUsuarioEditar().setEstado(usuarioDataManager.getEstadoActivo());
			Usuario usuario = servicioAdministracion.actualizarUsuario(this.usuarioDataManager.getUsuarioEditar());
			
			if (usuario != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al activarUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void resetClaveUsuario () {
		
		slf4jLogger.info("resetClaveUsuario");
		
		try {
			
//			this.usuarioDataManager.getUsuarioEditar().setEstado(usuarioDataManager.getEstadoActivo());
			this.usuarioDataManager.getUsuarioEditar().setUsuarioRegistro(this.usuarioDataManager.getUsuarioSession());
			Usuario usuario = servicioAdministracion.resetClaveUsuario(this.usuarioDataManager.getUsuarioEditar());
			
			if (usuario != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.reset.clave");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al resetClaveUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void obtenerModuloMenu (Integer idPerfil) {
		
		slf4jLogger.info("obtenerModuloMenu");
		
		try {
			if (idPerfil > 0) {
				
//				VistaModuloMenu vistaModuloMenu = new VistaModuloMenu();
//				vistaModuloMenu.setIdPerfil(idPerfil);
//				vistaModuloMenu.setPerfilEstado(this.usuarioDataManager.getEstadoActivo());
//				vistaModuloMenu.setModuloEstado(this.usuarioDataManager.getEstadoActivo());
//				List<VistaModuloMenu> listVistaModuloMenu = servicioAdministracion.buscarModuloMenu(vistaModuloMenu);
//				
//				if (CollectionUtils.isEmpty(listVistaModuloMenu)) {
//					MensajesWebController.aniadirMensajeAdvertencia("El perfil seleccionado no tiene modulos asignados");
////					setTreeNodeModulos(new );
//				} else {
//					this.setTreeNodeModulos(this.crearArbolModuloMenu(listVistaModuloMenu));
//				}
				
				Perfil perfilBuscar = new Perfil();
				perfilBuscar.setIdPerfil(idPerfil);
				perfilBuscar.setEstado(this.usuarioDataManager.getEstadoActivo());
				
				List<Perfil> pistPerfil = this.servicioAdministracion.buscarPerfilModuloMenu(perfilBuscar);
				if (CollectionUtils.isEmpty(pistPerfil)) {
					MensajesWebController.aniadirMensajeAdvertencia("El perfil seleccionado no tiene modulos asignados");
	//				setTreeNodeModulos(new );
				} else {
					this.setTreeNodeModulos(this.crearArbolPerfilModuloMenu(pistPerfil));
				}
				
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al resetClaveUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
		
	}
	
	private TreeNode crearArbolPerfilModuloMenu (List<Perfil> listPerfil) {
		TreeNode root = new DefaultTreeNode("", null);
		
		for (Perfil perfil : listPerfil) {
			if (CollectionUtils.isNotEmpty(perfil.getSegtModulos())) {
				//generar arbol
				for (Modulo modulo : perfil.getSegtModulos()) {
					TreeNode moduloNode = new DefaultTreeNode(modulo, root);
					
					if (CollectionUtils.isNotEmpty(modulo.getSegtMenus())) {
						
						for (Menu menu : modulo.getSegtMenus()) {
							new DefaultTreeNode(menu, moduloNode); 
						}
						
					}
					
				}
			}
		}
		
		return root;
	}
	
	private void asignarSeleccionArbol (List<MenuUsuario> listMenuUsuario) {
		
		TreeNode tree = getTreeNodeModulos();
		
		//primer nivel que son los modulos
		for (TreeNode nodeModulo : tree.getChildren()){
			
			int contador = 0;
			for (TreeNode nodeMenu : nodeModulo.getChildren()) {
				
				if (nodeMenu.getData() instanceof Menu) {
					Menu menuNode = (Menu)nodeMenu.getData();
					for (MenuUsuario menuUsuario : listMenuUsuario) {
						
						if (menuNode.getIdMenu().intValue() == menuUsuario.getIdMenu().intValue()) {
							nodeMenu.setSelected(Boolean.TRUE);
							contador ++;
							break;
						}
						
					}
				}
			}
			if (contador == nodeModulo.getChildren().size()) {
				nodeModulo.setSelected(Boolean.TRUE);
			}
			
		}
		
	}
	
//	private TreeNode crearArbolModuloMenu (List<VistaModuloMenu> listVistaModuloMenu) {
//		
//		Map<Integer, Modulo> mapModuloMenu = new HashMap<Integer, Modulo>();
//		
//		//obtener modulos
//		for (VistaModuloMenu vistaModuloMenu : listVistaModuloMenu) {
//			if (!mapModuloMenu.containsKey(vistaModuloMenu.getIdModulo())) {
//				Modulo modulo = new Modulo();
//				modulo.setIdModulo(vistaModuloMenu.getIdModulo());
//				modulo.setNombreModulo(vistaModuloMenu.getNombreModulo());
//				modulo.setDescModulo(vistaModuloMenu.getDescModulo());
//				mapModuloMenu.put(modulo.getIdModulo(), modulo);
//			}
//		}
//		
//		//obtener los menus de los modulos encontrados
//		for (Modulo modulo : mapModuloMenu.values()) {
//			
//		}
//		
//		TreeNode root = new DefaultTreeNode("", null);
//		
//		//generar arbol
//		for (Modulo modulo : mapModuloMenu.values()) {
//			TreeNode moduloNode = new DefaultTreeNode(modulo, root);
//		}
//		
//		return root;
//		
//	}
	
	/*
	 * Propiedades
	 */

	/**
	 * @return the idEmpresaSeleccionada
	 */
	public Integer getIdEmpresaSeleccionada() {
		return idEmpresaSeleccionada;
	}

	/**
	 * @param idEmpresaSeleccionada the idEmpresaSeleccionada to set
	 */
	public void setIdEmpresaSeleccionada(Integer idEmpresaSeleccionada) {
		this.idEmpresaSeleccionada = idEmpresaSeleccionada;
	}

	/**
	 * @return the catalogoPerfiles
	 */
	public List<SelectItem> getCatalogoPerfiles() {
		return catalogoPerfiles;
	}

	/**
	 * @param catalogoPerfiles the catalogoPerfiles to set
	 */
	public void setCatalogoPerfiles(List<SelectItem> catalogoPerfiles) {
		this.catalogoPerfiles = catalogoPerfiles;
	}

	/**
	 * @return the treeNodeModulos
	 */
	public TreeNode getTreeNodeModulos() {
		return treeNodeModulos;
	}

	/**
	 * @param treeNodeModulos the treeNodeModulos to set
	 */
	public void setTreeNodeModulos(TreeNode treeNodeModulos) {
		this.treeNodeModulos = treeNodeModulos;
	}

	/**
	 * @return the idPerfilSeleccionado
	 */
	public Integer getIdPerfilSeleccionado() {
		return idPerfilSeleccionado;
	}

	/**
	 * @param idPerfilSeleccionado the idPerfilSeleccionado to set
	 */
	public void setIdPerfilSeleccionado(Integer idPerfilSeleccionado) {
		this.idPerfilSeleccionado = idPerfilSeleccionado;
	}

//	/**
//	 * @return the selectedNodes
//	 */
//	public List<TreeNode> getSelectedNodes() {
//		return selectedNodes;
//	}
//
//	/**
//	 * @param selectedNodes the selectedNodes to set
//	 */
//	public void setSelectedNodes(List<TreeNode> selectedNodes) {
//		this.selectedNodes = selectedNodes;
//	}

	/**
	 * @return the selectedNodes
	 */
	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	/**
	 * @param selectedNodes the selectedNodes to set
	 */
	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

}
