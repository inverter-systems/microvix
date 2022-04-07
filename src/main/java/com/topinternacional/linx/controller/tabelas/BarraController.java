package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Barra;
import com.topinternacional.linx.services.tabelas.BarraService;

@Controller
public class BarraController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private BarraService barraService;
	
	
	@GetMapping("/barra")
	public String barra( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Barra> barras = barraService.getBarras(page); 
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listBarras", barras);
		} else {
			model.addAttribute("listBarras", barras);
			barraService.exportMicrovix(barras);
		}
		
		model.addAttribute("totalPages", barras.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", barras.getTotalElements());
		model.addAttribute("path", "barra");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar os código de barras cadastrados para os produtos no sistema N&L Gestão, enviar dados para inserir ou atualizar as informações do cadastro de codigo de barras no sistema Microvix.</p>"
						  );

		return "tabelas/barra";
	}
	
	@GetMapping("/barra/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
}