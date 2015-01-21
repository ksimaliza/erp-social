package ec.edu.uce.erp.ejb.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
//import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.Defuncion1ListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;
import ec.edu.uce.erp.ejb.persistence.vo.AutorizacionExhumacionVO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.persistence.vo.ComunionVO;
import ec.edu.uce.erp.ejb.persistence.vo.ConfirmacionVO;
import ec.edu.uce.erp.ejb.persistence.vo.ContratoVO;
import ec.edu.uce.erp.ejb.persistence.vo.DefuncionVO;
import ec.edu.uce.erp.ejb.persistence.vo.DoctorVO;
import ec.edu.uce.erp.ejb.persistence.vo.EucaristiaVO;
import ec.edu.uce.erp.ejb.persistence.vo.ExhumacionVO;
import ec.edu.uce.erp.ejb.persistence.vo.MatrimonioVO;
import ec.edu.uce.erp.ejb.persistence.vo.PagoVO;
import ec.edu.uce.erp.ejb.persistence.vo.SacerdoteVO;
import ec.edu.uce.erp.ejb.persistence.vo.SepulturaVO;

@Local
public interface ServicioEucaristia {

	BautizoDTO createOrUpdateBautizo(BautizoVO bautizoVO)
			throws SeguridadesException;

	SacerdoteDTO createOrUpdateSacerdote(SacerdoteVO sacerdoteVO)
			throws SeguridadesException;

	List<SacerdoteListDTO> buscarSacerdote(SacerdoteListDTO sacerdoteListDTO)
			throws SeguridadesException;

	SacerdoteVO obtenerSacerdotePorId(Integer Idpersona, Integer Idsacerdote)
			throws SeguridadesException;

	DoctorDTO createOrUpdateDoctor(DoctorVO doctorVO)
			throws SeguridadesException;

	List<DoctorListDTO> buscarDoctor(DoctorListDTO doctorListDTO)
			throws SeguridadesException;

	DoctorVO obtenerDoctorPorId(Integer Idpersona, Integer Iddoctor)
			throws SeguridadesException;

	List<BautizoListDTO> buscarPartidaBautizo(BautizoListDTO bautizoListDTO)
			throws SeguridadesException;

	ConfirmacionDTO createOrUpdateConfirmacion(ConfirmacionVO confirmacionVO)
			throws SeguridadesException;

	List<ConfirmacionListDTO> buscarPartidaConfirmacion(
			ConfirmacionListDTO confirmacionListDTO)
			throws SeguridadesException;

	// busque de secciones de nicho
	List<CatalogoEucaristiaDTO> buscarCatalogo(
			CatalogoEucaristiaDTO catalogoEucaristiaDTO)
			throws SeguridadesException;

	List<CatalogoEucaristiaDTO> buscarCatalogoPorId(Integer catCodigo)
			throws SeguridadesException;

	PrimeraComunionDTO createOrUpdateComunion(ComunionVO comunionVO)
			throws SeguridadesException;

	List<ComunionListDTO> buscarPartidaComunion(ComunionListDTO comunionListDTO)
			throws SeguridadesException;

	MatrimonioDTO createOrUpdateMatrimonio(MatrimonioVO matrimonioVO)
			throws SeguridadesException;

	List<MatrimonioListDTO> buscarPartidaMatrimonio(
			MatrimonioListDTO matrimonioListDTO) throws SeguridadesException;

	MatrimonioVO obtenerMatrimonioPorId(MatrimonioListDTO matrimonioListDTO)
			throws SeguridadesException;

	DefuncionDTO createOrUpdateDefuncion(DefuncionVO defuncionVO)
			throws SeguridadesException;

	List<DefuncionListDTO> buscarDefuncion(DefuncionListDTO defuncionListDTO)
			throws SeguridadesException;

	DefuncionVO obtenerDefuncionPorId(DefuncionListDTO defuncionListDTO)
			throws SeguridadesException;

	TipoNichoDTO createOrUpdateTipoNicho(TipoNichoDTO tipoNichoDTO)
			throws SeguridadesException;

	List<TipoNichoDTO> buscarTipoNicho(TipoNichoDTO tipoNichoDTO)
			throws SeguridadesException;

	TipoNichoDTO obtenerTipoNichoPorId(Integer id) throws SeguridadesException;

	BautizoVO obtenerBautizoPorId(BautizoListDTO bautizoListDTO)
			throws SeguridadesException;

	ConfirmacionVO obtenerConfirmacionPorId(
			ConfirmacionListDTO confirmacionListDTO)
			throws SeguridadesException;

	ComunionVO obtenerComunionPorId(ComunionListDTO comunionListDTO)
			throws SeguridadesException;

	NivelNichoDTO createOrUpdateNivelNicho(NivelNichoDTO nivelNichoDTO)
			throws SeguridadesException;

	List<NivelNichoDTO> buscarNivelNicho(NivelNichoDTO nivelNichoDTO)
			throws SeguridadesException;

	NivelNichoDTO obtenerNivelNichoPorId(Integer id)
			throws SeguridadesException;

	CatalogoEucaristiaDTO createOrUpdateSeccionNicho(
			CatalogoEucaristiaDTO seccionNichoDTO) throws SeguridadesException;

	List<CatalogoEucaristiaDTO> buscarSeccionNicho(
			CatalogoEucaristiaDTO seccionNichoDTO) throws SeguridadesException;

	CatalogoEucaristiaDTO obtenerSeccionNichoPorId(Integer id)
			throws SeguridadesException;

	NichoDTO createOrUpdateNicho(NichoDTO nichoDTO) throws SeguridadesException;

	List<NichoListDTO> buscarNicho(NichoListDTO nichoListDTO)
			throws SeguridadesException;

	NichoDTO obtenerNichoPorId(NichoListDTO nichoListDTO)
			throws SeguridadesException;

	EucaristiaDTO createOrUpdateEucaristia(EucaristiaVO eucaristiaVO)
			throws SeguridadesException;

	EucaristiaVO obtenerEucaristiaPorId(EucaristiaListDTO eucaristiaListDTO)
			throws SeguridadesException;

	AutorizaExhumacionDTO createOrUpdateAutorizacion(
			AutorizacionExhumacionVO autorizacionExhumacionVO)
			throws SeguridadesException;

	List<AutorizaExhumacionListDTO> buscarAutorizacion(
			AutorizaExhumacionListDTO autorizaExhumacionListDTO)
			throws SeguridadesException;

	AutorizacionExhumacionVO obtenerAutorizacionPorId(Integer IdPersona,
			Integer IdAutorizacion) throws SeguridadesException;

	ExumacionDTO createOrUpdateExhumacion(ExhumacionVO exhumacionVO)
			throws SeguridadesException;

	List<ExumacionListDTO> buscarExhumacion(ExumacionListDTO exumacionListDTO)
			throws SeguridadesException;

	ExhumacionVO obtenerExhumacionPorId(ExumacionListDTO exumacionListDTO)
			throws SeguridadesException;

	List<ContratoListDTO> buscarContrato(ContratoListDTO contratoListDTO)
			throws SeguridadesException;

	ContratoVO obtenerContratoPorId(ContratoListDTO contratoListDTO)
			throws SeguridadesException;

	SepulturaDTO createOrUpdateSepultura(SepulturaVO sepulturaVO)
			throws SeguridadesException;

	List<SepulturaListDTO> readSepultura(SepulturaListDTO sepultura)
			throws SeguridadesException;

	List<SepulturaVO> obtenerSepulturasActivasPorIdEmpresa(Integer idEmpresa)
			throws SeguridadesException;

	SepulturaVO obtenerSepulturaPorId(SepulturaListDTO sepulturaListDTO)
			throws SeguridadesException;

	List<SepulturaVO> obtenerTodasSepulturas() throws SeguridadesException;

	List<NichoListDTO> readNicho(NichoListDTO nichoListDTO)
			throws SeguridadesException;

	List<PagoContratoListDTO> readPago(PagoContratoListDTO pago)
			throws SeguridadesException;

	PagoDTO createOrUpdatePagoContrato(PagoVO pagoVO)
			throws SeguridadesException;

	PagoVO obtenerPagoPorId(PagoContratoListDTO pagoContratoListDTO)
			throws SeguridadesException;

	PagoDTO updatePagoContrato(PagoDTO pago) throws SeguridadesException;

	List<EucaristiaListDTO> buscarEucaristia(
			EucaristiaListDTO eucaristiaListDTO, FiltroFechaDTO fecha)
			throws SeguridadesException;

	ContratoDTO createOrUpdateContrato(ContratoVO contratoVO)
			throws SeguridadesException;

	BigDecimal calcularSaldo(PagoDTO pago) throws SeguridadesException;

	Integer calcularMeses(PagoDTO pago) throws SeguridadesException;

	BigDecimal calcularValorPagar(PagoDTO pago, ContratoListDTO contrato)
			throws SeguridadesException;

	BigDecimal calcularValorTotal(ContratoDTO contrato)
			throws SeguridadesException;

	List<EucaristiaListDTO> readEucaristiaReport(EucaristiaListDTO eucaristia)
			throws SeguridadesException;

	List<BautizoListDTO> readBautizoReport(BautizoListDTO bautizo)
			throws SeguridadesException;

	List<ComunionListDTO> readComunionReport(ComunionListDTO comunion)
			throws SeguridadesException;

	List<ConfirmacionListDTO> readConfirmacionReport(
			ConfirmacionListDTO confirmacion) throws SeguridadesException;

	List<MatrimonioListDTO> readMatrimonioReport(MatrimonioListDTO matrimonio)
			throws SeguridadesException;

	List<DefuncionListDTO> readDefuncionReport(DefuncionListDTO defuncion)
			throws SeguridadesException;

	List<SepulturaListDTO> readSepulturaReport(SepulturaListDTO sepultura)
			throws SeguridadesException;

	List<ExumacionListDTO> readExhumacionReport(ExumacionListDTO exumacion)
			throws SeguridadesException;

}
