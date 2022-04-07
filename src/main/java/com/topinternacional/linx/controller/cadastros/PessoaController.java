package com.topinternacional.linx.controller.cadastros;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.api.BasicController;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Pessoa;
import com.topinternacional.linx.services.cadastros.PessoaService;

@Controller
public class PessoaController extends BasicController {
	
	public static final String ACTION_TODOS = "todos";
	public static final String ACTION_CPFPJ = "cpfpj";
	
	@Autowired
	private PessoaService service;
	
	@GetMapping("/pessoa")
	public String produto( @RequestParam(name="action", required=false, defaultValue="none") String action
						 , @RequestParam(name="pag", required=false, defaultValue="1") int pag
						 , @RequestParam(name="codigo", required=false, defaultValue="SN") String codigo
						 , @RequestParam(name="nome", required=false, defaultValue="SN") String nome
						 , @RequestParam(name="tipo", required=false, defaultValue="SN") String tipo
						 , @RequestParam(name="tipopessoa", required=false, defaultValue="Fornecedor") String tipopessoa
			             , Model model
			             ) throws IOException {
		
		ajustaStatus();
		
		Page<Pessoa> pessoa = service.getPessoas(codigo, nome, tipo, tipopessoa, Sort.by("codigo"), pag);
	
		if (!status.equals(Status.PROCESSANDO) && !action.equals("none")) { 
			service.exportMicrovix(codigo, nome, tipo, tipopessoa, Sort.by("codigo"), action);
		}

		model.addAttribute("listPessoa", pessoa.getContent());
		model.addAttribute("totalPages", pessoa.getTotalPages());
		model.addAttribute("currentPage", pag);
		model.addAttribute("totalItems", pessoa.getTotalElements());
		model.addAttribute("codigo", codigo);
		model.addAttribute("nome", nome);
		model.addAttribute("tipo", tipo);
		model.addAttribute("tipopessoa", tipopessoa);
		
		model.addAttribute("path", "pessoa");
		model.addAttribute("filter", "&codigo="+codigo+"&nome="+nome+"&tipo="+tipo+"&tipopessoa="+tipopessoa);
		model.addAttribute("help", "<p>Esta tela permite visualizar os itens comerciais disponíveis no sistema N&L Gestão, de acordo com os filtros aplicados:</p>"
								 + "<p>Todos os itens ativos, cadastrados e um dos setores comercias(20, 21, 22, 23, 27 e 30), com estoque maior ou igual a 1(um) em"
								 + " qualquer unidade da zona franca na data da exportação ou que tenham tido movimentação de estoque com data superior a 01/01/2017, que possua preço"
								 + " de venda maior que 0(zero) e tributação identificada.</p>"
						  );

     	
		return "pessoa";
	}
	
	@GetMapping("/pessoa/status")
	@ResponseBody
	public static String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
}