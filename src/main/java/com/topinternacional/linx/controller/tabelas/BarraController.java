package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
	private BarraService service;
	
	@GetMapping("/barra")
	public String barra( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int pag
					   , @RequestParam(name="codigo", required=false) Integer codigo
					   , @RequestParam(name="setor", required=false, defaultValue="SN") String setor
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Barra> barras = service.getBarras(codigo, setor, Sort.by("cod_produto"), pag); 
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listBarras", barras);
		} else {
			model.addAttribute("listBarras", barras);
			service.exportMicrovix(codigo, setor, Sort.by("cod_produto"));
		}
		 
		model.addAttribute("totalPages", barras.getTotalPages());
		model.addAttribute("currentPage", pag);
		model.addAttribute("totalItems", barras.getTotalElements());
		model.addAttribute("setor", setor);
		model.addAttribute("codigo", codigo);
		model.addAttribute("path", "barra");
		model.addAttribute("filter", "&codigo="+codigo+"&setor="+setor);
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