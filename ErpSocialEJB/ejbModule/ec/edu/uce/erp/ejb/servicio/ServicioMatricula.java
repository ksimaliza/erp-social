package ec.edu.uce.erp.ejb.servicio;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParcialDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;

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

	void deleteMatricula(MatriculaDTO matriculaDTO) throws SeguridadesException;

	NivelDTO createOrUpdateNivel(NivelDTO nivelDTO) throws SeguridadesException;

	void deleteNivel(NivelDTO nivelDTO) throws SeguridadesException;

	NivelParaleloDTO createOrUpdateNivelParalelo(
			NivelParaleloDTO nivelParaleloDTO) throws SeguridadesException;

	void deleteNivelParalelo(NivelParaleloDTO nivelParaleloDTO)
			throws SeguridadesException;

	NotaDTO createOrUpdateNota(NotaDTO notaDTO) throws SeguridadesException;

	void deleteNota(NotaDTO notaDTO) throws SeguridadesException;

	ParaleloDTO createOrUpdateParalelo(ParaleloDTO paraleloDTO)
			throws SeguridadesException;

	void deleteParalelo(ParaleloDTO paraleloDTO) throws SeguridadesException;

	ParcialDTO createOrUpdateParcial(ParcialDTO parcialDTO)
			throws SeguridadesException;

	void deleteParcial(ParcialDTO parcialDTO) throws SeguridadesException;

	PeriodoDTO createOrUpdatePeriodo(PeriodoDTO periodoDTO)
			throws SeguridadesException;

	void deletePeriodo(PeriodoDTO periodoDTO) throws SeguridadesException;

	ProfesorDTO createOrUpdateProfesor(ProfesorDTO profesorDTO)
			throws SeguridadesException;

	void deleteProfesor(ProfesorDTO profesorDTO) throws SeguridadesException;

	RepresentanteDTO createOrUpdateRepresentante(
			RepresentanteDTO representanteDTO) throws SeguridadesException;

	void deleteRepresentante(RepresentanteDTO representanteDTO)
			throws SeguridadesException;



}
