package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Setor;
import com.topinternacional.linx.services.tabelas.SetorService;

@Controller
public class SetorController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private SetorService setorService;
	
	
	@GetMapping("/setor")
	public String setor( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		Page<Setor> setores = setorService.getSetores();
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listSetores", setores);
		} else {
			model.addAttribute("listSetores", setores);
			setorService.exportMicrovix(setores);
		}
		
		model.addAttribute("totalPages", setores.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", setores.getTotalElements());
		model.addAttribute("path", "setor");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar os setores comerciais disponíveis no sistema N&L Gestão, enviar dados para inserir ou atualizar as informações do cadastro de setores no sistema Microvix.</p>"
				                 + "<p>Os setor comerciais estão listados de acordo com as informação registradas no sistema N&L Gestão, no código estruturado do produto(máscara 101) e no campo formato.</p>");
		return "tabelas/setor";
	}
	
	@GetMapping("/setor/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
	
}