package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Cest;
import com.topinternacional.linx.services.tabelas.CestService;

@Controller
public class CestController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private CestService cestService;
	
	
	@GetMapping("/cest")
	public String setor( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Cest> cests = cestService.getCests(page); 
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listCests", cests);
		} else {
			model.addAttribute("listCests", cests);
			cestService.exportMicrovix(cests);
		}
		
		model.addAttribute("totalPages", cests.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", cests.getTotalElements());
		model.addAttribute("path", "cest");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar a tabela de Código Especificador da Substituição Tributária - CEST</p>"                                 
                                 + "<p>Fonte: https://blog.oobj.com.br/tabela-cest-atualizada/</p>"
                                 + "<p>Pesquisada em: 24/01/2022</p>"
						  );

		return "tabelas/cest";
	}
	
	@GetMapping("/cest/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
}	