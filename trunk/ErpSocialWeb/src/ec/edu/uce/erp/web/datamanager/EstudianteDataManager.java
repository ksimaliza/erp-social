package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "estudianteDataManager")
public class EstudianteDataManager extends BaseDataManager {

		private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
		private static final long serialVersionUID = 1L;
		
		private EstudianteDTO estudianteInstancia;
		private EstudianteDTO estudianteEditar;
		private EstudianteDTO estudianteBuscar;
		
		private Persona estudiantePersonaInsertar;
		private Persona estudiantePersonaEditar;
		private Persona estudiantePersonaBuscar;
		
		@PostConstruct
		public void inicializarObjetos () {
			
			slf4jLogger.info("inicializarObjetos");
			this.estudianteInstancia = new EstudianteDTO();		
			this.estudianteEditar = new EstudianteDTO();
			this.estudianteBuscar = new EstudianteDTO();
			
			this.estudiantePersonaInsertar=new Persona();
			this.estudiantePersonaEditar=new Persona();
			this.estudiantePersonaBuscar=new Persona();
				
		}

		public EstudianteDTO getEstudianteInstancia() {
			return estudianteInstancia;
		}

		public void setEstudianteInstancia(EstudianteDTO estudianteInstancia) {
			this.estudianteInstancia = estudianteInstancia;
		}

		public EstudianteDTO getEstudianteEditar() {
			return estudianteEditar;
		}

		public void setEstudianteEditar(EstudianteDTO estudianteEditar) {
			this.estudianteEditar = estudianteEditar;
		}

		public EstudianteDTO getEstudianteBuscar() {
			return estudianteBuscar;
		}

		public void setEstudianteBuscar(EstudianteDTO estudianteBuscar) {
			this.estudianteBuscar = estudianteBuscar;
		}

		public Persona getEstudiantePersonaInsertar() {
			return estudiantePersonaInsertar;
		}

		public void setEstudiantePersonaInsertar(Persona estudiantePersonaInsertar) {
			this.estudiantePersonaInsertar = estudiantePersonaInsertar;
		}

		public Persona getEstudiantePersonaEditar() {
			return estudiantePersonaEditar;
		}

		public void setEstudiantePersonaEditar(Persona estudiantePersonaEditar) {
			this.estudiantePersonaEditar = estudiantePersonaEditar;
		}

		public Persona getEstudiantePersonaBuscar() {
			return estudiantePersonaBuscar;
		}

		public void setEstudiantePersonaBuscar(Persona estudiantePersonaBuscar) {
			this.estudiantePersonaBuscar = estudiantePersonaBuscar;
		}

		
		
}
