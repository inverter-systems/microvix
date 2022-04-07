
package com.topinternacional.linx.controller.imp;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.api.BasicController;
import com.topinternacional.linx.bean.NotaFiscal;
import com.topinternacional.linx.services.imp.NotaFiscalService;

@Controller
public class NotaController extends BasicController {
			
	@Autowired
	private NotaFiscalService service; 
	
	@GetMapping("/nota")
	public String nota( @RequestParam(name="action", required=false, defaultValue="none") String action
			          , @RequestParam(name="id", required=true) String id
			          , @RequestParam(name="codUnidade", required=true) Integer codUnidade
					  , Model model
			          ) throws IOException {
		
		ajustaStatus();
		String pagto = "";
		NotaFiscal nota = service.getNotaFiscal(id, codUnidade);
		
		if (!action.equals("none")) {
			service.importarNota(nota);
		}
		
		// Pagando os valores do XML da nota
		BigDecimal pis = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVPIS());
		BigDecimal cofins = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVCOFINS());
		
		BigDecimal bcIcms = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVBC());
		BigDecimal vIcms = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVICMS());
		BigDecimal bcIcmsSt = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVBCST());
		BigDecimal vIcmsSt = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVST());
		BigDecimal vTotProdutos = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVProd());
		BigDecimal vFrete = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVFrete());
		BigDecimal vSeguro = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVSeg());
		BigDecimal vOutras = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVOutro());
		BigDecimal vTotIpi = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVIPI());
		BigDecimal vTotNota = new BigDecimal(nota.getNfe().getInfNFe().getTotal().getICMSTot().getVNF());
		
		
		for (int i = 0; i < nota.getFormaPagamento().size(); i++) {
			pagto += nota.getFormaPagamento().get(i).getCodigo()+" - "+nota.getFormaPagamento().get(i).getDescricao();
			if (nota.getFormaPagamento().size() > 1) pagto += ", "; 
		}	 
		
		model.addAttribute("nota", nota);
		model.addAttribute("pagto",  pagto);
		model.addAttribute("id", id);
		model.addAttribute("path", "nota");
		model.addAttribute("filtro", "id="+id+"&codUnidade="+codUnidade);
		model.addAttribute("codUnidade", codUnidade);
		model.addAttribute("microvix_url", "https://linx.microvix.com.br:8371/gestor_web/faturamento/imprime_doc.asp?listarNotas=V&identificador={"+id+"}");
		model.addAttribute("pisCofins", pis.add(cofins).toString().replace(".",","));
		model.addAttribute("bcIcms", bcIcms.toString().replace(".",","));
		model.addAttribute("vIcms", vIcms.toString().replace(".",","));
		model.addAttribute("bcIcmsSt", bcIcmsSt.toString().replace(".",","));
		model.addAttribute("vIcmsSt", vIcmsSt.toString().replace(".",","));
		model.addAttribute("vTotProdutos", vTotProdutos.toString().replace(".",","));
		model.addAttribute("vFrete", vFrete.toString().replace(".",","));
		model.addAttribute("vSeguro", vSeguro.toString().replace(".",","));
		model.addAttribute("vOutras", vOutras.toString().replace(".",","));
		model.addAttribute("vTotIpi", vTotIpi.toString().replace(".",","));
		model.addAttribute("vTotNota", vTotNota.toString().replace(".",","));
		
		return "imp/nota";	
	}	  
	
	@GetMapping("/nota/status")
	@ResponseBody
	public String getStatus(Model model) {			
		return status.getDescricao()+":"+andamento+":"+mensagem;			
	}
}
