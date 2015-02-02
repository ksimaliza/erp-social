package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;
import java.util.List;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaTutorDTO;

public class ReporteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String parametro1;
	private String parametro2;
	private String parametro3;
	private String parametro4;
	private String parametro5;
	private String parametro6;
	private String parametro7;

	private List<DatosReporteDTO> listaReporteDTOs;

	private Float promeditoTotal;
	private String observacionFinal;

	private NotaTutorDTO notaTutorDTO;

	public String getParametro1() {
		return parametro1;
	}

	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}

	public String getParametro2() {
		return parametro2;
	}

	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}

	public String getParametro3() {
		return parametro3;
	}

	public void setParametro3(String parametro3) {
		this.parametro3 = parametro3;
	}

	public String getParametro4() {
		return parametro4;
	}

	public void setParametro4(String parametro4) {
		this.parametro4 = parametro4;
	}

	public String getParametro5() {
		return parametro5;
	}

	public void setParametro5(String parametro5) {
		this.parametro5 = parametro5;
	}

	public String getParametro6() {
		return parametro6;
	}

	public void setParametro6(String parametro6) {
		this.parametro6 = parametro6;
	}

	public List<DatosReporteDTO> getListaReporteDTOs() {
		return listaReporteDTOs;
	}

	public void setListaReporteDTOs(List<DatosReporteDTO> listaReporteDTOs) {
		this.listaReporteDTOs = listaReporteDTOs;
	}

	public Float getPromeditoTotal() {
		return promeditoTotal;
	}

	public void setPromeditoTotal(Float promeditoTotal) {
		this.promeditoTotal = promeditoTotal;
	}

	public String getObservacionFinal() {
		return observacionFinal;
	}

	public void setObservacionFinal(String observacionFinal) {
		this.observacionFinal = observacionFinal;
	}

	public String getParametro7() {
		return parametro7;
	}

	public void setParametro7(String parametro7) {
		this.parametro7 = parametro7;
	}

	public NotaTutorDTO getNotaTutorDTO() {
		return notaTutorDTO;
	}

	public void setNotaTutorDTO(NotaTutorDTO notaTutorDTO) {
		this.notaTutorDTO = notaTutorDTO;
	}

}
