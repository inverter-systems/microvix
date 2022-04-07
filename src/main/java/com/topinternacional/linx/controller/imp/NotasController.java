package com.topinternacional.linx.controller.imp;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.api.BasicController;
import com.topinternacional.linx.bean.NotaFiscal;
import com.topinternacional.linx.exception.SemNotasParaImportarException;
//import com.topinternacional.linx.exception.ComunicacaoException;
//import com.topinternacional.linx.exception.SemNotasParaImportarException;
import com.topinternacional.linx.services.imp.NotaFiscalService;
import com.topinternacional.linx.services.util.Util;

@Controller
public class NotasController extends BasicController {
	
	@Autowired
	private NotaFiscalService service;
	
	@GetMapping("/notas-fiscais")
	public String nota( @RequestParam(name="action", required=false, defaultValue="none") String action
						 , @RequestParam(name="pag", required=false, defaultValue="1") int pag
						 , @RequestParam(name="unidade", required=false, defaultValue="SN") String unidade
						 , @RequestParam(name="dtaInicio", required=false, defaultValue="SN") String dtaInicio
						 , @RequestParam(name="dtaFim", required=false, defaultValue="SN") String dtaFim
			             , Model model
			             ) throws IOException {
		
		ajustaStatus();
		Page<NotaFiscal> notas = null;
		try {
			
			notas = service.getNotaFiscaisMicrovix(unidade, dtaInicio, dtaFim, pag);
	
			// Caso a action seja importar, chama o serviço para importar as notas enviadas
			if (action.equalsIgnoreCase("importar") && notas != null ) {
				//service.importarNotasFiscais(notas.getContent());	
			}

			model.addAttribute("notasPendentes", notas);
			
		//} catch (ComunicacaoException | NumberFormatException | ParseException e) {
		} catch (NumberFormatException | ParseException e) {
			model.addAttribute("msg", "Ocorre uma falha! Informe ao administrador do sistema. erro:"+e.getMessage());
		} catch (SemNotasParaImportarException e) {
			model.addAttribute("msg", "Não foram encontradas notas com os parametros informados!" );
		} finally {
			model.addAttribute("totalPages", notas != null ? notas.getTotalPages() : "");
			model.addAttribute("currentPage", pag);
			model.addAttribute("totalItems", notas != null ? notas.getTotalElements() : "");
 
			model.addAttribute("unidades", service.getUnidades());
			model.addAttribute("unidade", unidade);
			model.addAttribute("dtaInicio", dtaInicio.equals("SN") ? Util.getData() : dtaInicio);
			model.addAttribute("dtaFim", dtaFim.equals("SN") ? Util.getData() : dtaFim);
			
			model.addAttribute("bloqueio", dtaInicio.equals("SN") ? "false" : "true" );
			
			model.addAttribute("path", "notas-fiscais");
			model.addAttribute("filter", "&unidade="+unidade+"&dtaInicio="+dtaInicio+"&dtaFim="+dtaFim);
			model.addAttribute("help", "<p>Esta tela permite visualizar as notas fiscais emitidas no sistema Microvix e que ainda estão pendentes de integração.</p>"
									 + "<p>A tela também permite realizar a integração das notas fiscais pendentes e solicitar os detalhes de uma nota especifica.</p>"
									 
							  );
		}
		return "imp/notas-fiscais";	
	}
	
	@GetMapping("/pci")
	public String pci( @RequestParam(name="action", required=false, defaultValue="none") String action
						 , @RequestParam(name="pag", required=false, defaultValue="1") int pag
						 , @RequestParam(name="unidade", required=false, defaultValue="SN") String unidade
						 , @RequestParam(name="dtaInicio", required=false, defaultValue="SN") String dtaInicio
						 , @RequestParam(name="dtaFim", required=false, defaultValue="SN") String dtaFim
			             , Model model
			             ) throws IOException {
		
		ajustaStatus();
		Page<NotaFiscal> notas = null;
		
		try {
						
			
		} catch (NumberFormatException e) {
			model.addAttribute("msg", "Ocorre uma falha! Informe ao administrador do sistema. erro:"+e.getMessage());
			model.addAttribute("msg", "Não foram encontradas notas com os parametros informados!" );
		} finally {
			model.addAttribute("totalPages", notas != null ? notas.getTotalPages() : "");
			model.addAttribute("currentPage", pag);
			model.addAttribute("totalItems", notas != null ? notas.getTotalElements() : "");
			model.addAttribute("path", "notas-fiscais-pci");
			model.addAttribute("filter", "&unidade="+unidade+"&dtaInicio="+dtaInicio+"&dtaFim="+dtaFim);
			model.addAttribute("help", "<p>Esta tela permite visualizar as notas fiscais emitidas no sistema PCI e que ainda estão pendentes de integração.</p>"
					 + "<p>A tela também permite realizar a integração das notas fiscais pendentes e solicitar os detalhes de uma nota especifica.</p>"
			                  );
		}
		return "imp/notas-fiscais-pci";	
	}
	
	@GetMapping("/notas-fiscais/status")  
	@ResponseBody
	public static String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
}