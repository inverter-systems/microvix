
package com.topinternacional.linx.controller.cadastros;

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
import com.topinternacional.linx.model.nl.view.Produto;
import com.topinternacional.linx.services.cadastros.ProdutoService;

@Controller
public class ProdutoController extends BasicController {
	
	public static final String ACTION_TODOS_ITENS = "todos_itens";
	public static final String ACTION_PRECO_VENDA = "preco_venda";
	public static final String ACTION_PRECO_CUSTO = "preco_custo";
	public static final String ACTION_TRIBUTARIOS = "tributarios";
	public static final String ACTION_CODAUXILIAR = "codauxiliar";

	@Autowired
	private ProdutoService service;
	
	
	@GetMapping("/produto")
	public String setor( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int pag	
			           , @RequestParam(name="codigo", required=false, defaultValue="SN") String codigo
					   , @RequestParam(name="setor", required=false, defaultValue="SN") String setor
					   , @RequestParam(name="marca", required=false, defaultValue="SN") String marca
					   , Model model
			           ) {

		ajustaStatus();
		
		Page<Produto> produtos = service.getProdutos(codigo, marca, setor, Sort.by("codigo"), pag);
		
		model.addAttribute("listProduto", produtos);
		
		if (!status.equals(Status.PROCESSANDO) && !action.equals("none")) {
			service.exportMicrovix(codigo, marca, setor, Sort.by("codigo"), action);
		}
	
		// Caso a pesquisa seja pelo codigo do item, apagar os demais parametros
		if (!codigo.equals("SN")) {
			marca = "";
			setor = "";
		}
		
		model.addAttribute("totalPages", produtos.getTotalPages());
		model.addAttribute("currentPage", pag);
		model.addAttribute("totalItems", produtos.getTotalElements());
		model.addAttribute("path", "produto");
		model.addAttribute("setor", setor);
		model.addAttribute("codigo", codigo);
		model.addAttribute("marca", marca);
		model.addAttribute("filter", "&codigo="+codigo+"&marca="+marca+"&setor="+setor);
		model.addAttribute("help", "<p>Esta tela permite visualizar os itens comerciais disponíveis no sistema N&L Gestão, de acordo com os filtros aplicados:</p>"
								 + "<p>Todos os itens ativos, cadastrados e um dos setores comercias(20, 21, 22, 23, 27 e 30), com estoque maior ou igual a 1(um) em"
								 + " qualquer unidade da zona franca na data da exportação ou que tenham tido movimentação de estoque com data superior a 01/01/2017, que possua preço"
								 + " de venda maior que 0(zero) e tributação identificada.</p>"
						  );

		return "produto";
	}

	@GetMapping("/produto/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
}