package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Marca;
import com.topinternacional.linx.services.tabelas.MarcaService;

@Controller
public class MarcaController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private MarcaService marcaService;
	
	
	@GetMapping("/marca")
	public String marca( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Marca> marcas = marcaService.getMarcas(page); 
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listMarcas", marcas);
		} else {
			model.addAttribute("listMarcas", marcas);
			marcaService.exportMicrovix(marcas);
		}
		
		model.addAttribute("totalPages", marcas.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", marcas.getTotalElements());
		model.addAttribute("path", "marca");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar as marcas comerciais disponíveis no sistema N&L Gestão, enviar dados para inserir ou atualizar as informações do cadastro de marcas no sistema Microvix.</p>"
                                 + "<p>As marcas comerciais estão listadas de acordo com as informação registradas no sistema N&L Gestão, no código estruturado do produto(máscara 101) e de acordo com o setor comercial:</p>"
                                 + "<p>- Setor '20' retorna o nível 4 do código estruturado;</p>"
                                 + "<p>- Setor '21' retorna o nível 4 do código estruturado;</p>"
                                 + "<p>- Setor '22' retorna o nível 4 do código estruturado;</p>"
                                 + "<p>- Setor '23' retorna o nível 5 do código estruturado;</p>"
                                 + "<p>- Setor '27' retorna o nível 1 do código estruturado;</p>"
                                 + "<p>- Setor '30' retorna o nível 4 do código estruturado.</p>"
						  );

		return "tabelas/marca";
	}
	
	@GetMapping("/marca/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
}