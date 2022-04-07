package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Ncm;
import com.topinternacional.linx.services.tabelas.NcmService;

@Controller
public class NcmController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private NcmService ncmService;
	
	
	@GetMapping("/ncm")
	public String setor( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Ncm> ncms = ncmService.getNcms(page); 
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listNcms", ncms);
		} else {
			model.addAttribute("listNcms", ncms);
			ncmService.exportMicrovix(ncms);
		}
		
		model.addAttribute("totalPages", ncms.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", ncms.getTotalElements());
		model.addAttribute("path", "ncm");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar a tabela de códigos da Nomenclatura Comum do Mercosul (NCM)</p>"                                 
								 + "<p>Fonte: https://blog.oobj.com.br/tabela-ncm-2021/</p>"
								 + "<p>Pesquisada em: 24/01/2022</p>"

						  );

		return "tabelas/ncm";
	}
	
	@GetMapping("/ncm/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
}