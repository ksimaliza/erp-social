package ec.edu.uce.erp.ejb.servicio;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.ParaleloDTO;

@Local
public interface ServicioMatricula {

	AsinacionDTO createOrUpdateAsignacion(AsinacionDTO asinacionDTO)
			throws SeguridadesException;

		void deleteAsinacion(AsinacionDTO asinacionDTO) throws SeguridadesException;

		EstudianteDTO createOrUpdateEstudiante(EstudianteDTO estudianteDTO)
				throws SeguridadesException;

		void deleteEstudiante(EstudianteDTO estudianteDTO)
				throws SeguridadesException;

		MateriaDTO createOrUpdateMateria(MateriaDTO materiaDTO)
				throws SeguridadesException;

		void deleteMateria(MateriaDTO materiaDTO) throws SeguridadesException;

		MatriculaDTO createOrUpdateMatricula(MatriculaDTO matriculaDTO)
				throws SeguridadesException;

		void deleteMatricula(MatriculaDTO matriculaDTO)
				throws SeguridadesException;

		NivelDTO createOrUpdateMatricula(NivelDTO nivelDTO)
				throws SeguridadesException;

		void deleteNivel(NivelDTO nivelDTO) throws SeguridadesException;

		NivelParaleloDTO createOrUpdateNivelParalelo(
				NivelParaleloDTO nivelParaleloDTO) throws SeguridadesException;

		void deleteNivelParalelo(NivelParaleloDTO nivelParaleloDTO)
				throws SeguridadesException;

		NotaDTO createOrUpdateNivelParalelo(NotaDTO notaDTO)
				throws SeguridadesException;

		void deleteNota(NotaDTO notaDTO) throws SeguridadesException;

		void deleteParalelo(ParaleloDTO paraleloDTO)
				throws SeguridadesException;

		ParaleloDTO createOrUpdateParalelo(ParaleloDTO paraleloDTO)
				throws SeguridadesException;

}
