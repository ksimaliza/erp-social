package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "estudianteDataManager")
public class EstudianteDataManager extends BaseDataManager {

		private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
		private static final long serialVersionUID = 1L;
		
		private EstudianteVO estudianteInstancia;
		private EstudianteVO estudianteEditar;
		private EstudianteVO estudianteBuscar;
		
		@PostConstruct
		public void inicializarObjetos () {
			
			slf4jLogger.info("inicializarObjetos");
			this.estudianteInstancia = new EstudianteVO();		
			this.estudianteEditar = new EstudianteVO();
			this.estudianteBuscar = new EstudianteVO();
				
		}

		public EstudianteVO getEstudianteInstancia() {
			return estudianteInstancia;
		}

		public void setEstudianteInstancia(EstudianteVO estudianteInstancia) {
			this.estudianteInstancia = estudianteInstancia;
		}

		public EstudianteVO getEstudianteEditar() {
			return estudianteEditar;
		}

		public void setEstudianteEditar(EstudianteVO estudianteEditar) {
			this.estudianteEditar = estudianteEditar;
		}

		public EstudianteVO getEstudianteBuscar() {
			return estudianteBuscar;
		}

		public void setEstudianteBuscar(EstudianteVO estudianteBuscar) {
			this.estudianteBuscar = estudianteBuscar;
		}
		
		
}
