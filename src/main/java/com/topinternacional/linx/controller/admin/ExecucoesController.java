package com.topinternacional.linx.controller.admin;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.topinternacional.linx.enun.Cadastro;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.services.admin.ExecucoesService;

@Controller
public class ExecucoesController {
	
	@Autowired
	private ExecucoesService service;
	
	Integer totalPages = 0;	
	
	@GetMapping("/execucoes")
	public String agendamento( @RequestParam(name="action", required=false, defaultValue="none") String action
			                 , @RequestParam(name="pag", required=false, defaultValue="1") int pag
			                 , @RequestParam(name="dtaInicio", required=false, defaultValue="SN") String dtaInicio
							 , @RequestParam(name="origem", required=false, defaultValue="SN") String origem
							 , @RequestParam(name="cadastro", required=false, defaultValue="SN") String cadastro
			                 , Model model) throws IOException {
		
		Page<Execucoes> execucao = null;
		
		try {
			execucao = service.getExecucoes(dtaInicio, origem, cadastro, Sort.by(Sort.Direction.DESC, "id"), pag);
			
			model.addAttribute("listAgendamento", execucao.getContent());
			model.addAttribute("totalItems", execucao.getTotalElements());		
			
			model.addAttribute("path", "execucoes");
			model.addAttribute("filter", "");
			model.addAttribute("help", "<p>Esta tela permite acompanhar e auditar as execuções de integração ocorridas no sistema.</p>"
							  );
     	
		} catch (ParseException e) {
			e.printStackTrace();
			model.addAttribute("erroMsg", "A data informada para a consulta não é válida!");
		} finally {
			model.addAttribute("totalItems", execucao.getTotalElements());
			model.addAttribute("totalPages", execucao.getTotalPages());
			model.addAttribute("currentPage", pag);
			model.addAttribute("dtaInicio", dtaInicio);
			model.addAttribute("origem", origem);
			model.addAttribute("listCadastro", Cadastro.values());
			model.addAttribute("cadastro", cadastro);
		}
		
		return "admin/execucoes";
	}
}

//Identifica o sistema origem da informação. Valores: 1=N&L, 2=MICROVIX, 3=PCI