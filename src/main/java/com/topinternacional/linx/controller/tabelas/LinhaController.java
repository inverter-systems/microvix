package com.topinternacional.linx.controller.tabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.nl.view.Linha;
import com.topinternacional.linx.services.tabelas.LinhaService;

@Controller
public class LinhaController {
	
	static Status status = Status.AGUARDADO;
	static float andamento = 0;
	Integer totalPages;	

	@Autowired
	private LinhaService linhaService;
	
	
	@GetMapping("/linha")
	public String setor( @RequestParam(name="action", required=false, defaultValue="none") String action
					   , @RequestParam(name="pag", required=false, defaultValue="1") int page	
			           , Model model
			           ) {

		if (status.equals(Status.CONCLUIDO)) {
			status = Status.AGUARDADO;
		}
		
		Page<Linha> linhas = linhaService.getLinhas(page); 
		
		
		if (action.equalsIgnoreCase("none")) {
			model.addAttribute("listLinhas", linhas);
		} else {
			model.addAttribute("listLinhas", linhas);
			linhaService.exportMicrovix(linhas);
		}
		
		model.addAttribute("totalPages", linhas.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalItems", linhas.getTotalElements());
		model.addAttribute("path", "linha");
		model.addAttribute("filter", "");
		model.addAttribute("help", "<p>Esta tela permite visualizar as linhas comerciais disponíveis no sistema N&L Gestão, enviar dados para inserir ou atualizar as informações do cadastro de linhas no sistema Microvix.</p>"
                                 + "<p>As linhas comerciais estão listadas de acordo com as informação registradas no sistema N&L Gestão, no código estruturado do produto(máscara 101) e de acordo com o setor comercial:</p>"
                                 + "<p>- Setor '20' retorna o nível 6 do código estruturado;</p>"
                                 + "<p>- Setor '21' retorna o nível 5 do código estruturado;</p>"
                                 + "<p>- Setor '22' retorna o nível 3 do código estruturado;</p>"
                                 + "<p>- Setor '23' retorna o nível 3 do código estruturado;</p>"
                                 + "<p>- Setor '27' retorna o nível 2 do código estruturado;</p>"
                                 + "<p>- Setor '30' retorna o nível 5 do código estruturado.</p>"

						  );

		return "tabelas/linha";
	}
	
	@GetMapping("/linha/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
	
	
	
//	private static final Logger log = LoggerFactory.getLogger(LinhaController.class);
//	byte [] xml;
//
//	@Autowired
//	private LinhaRepository linhaRepository;
//	
//	@Autowired
//	private XMLServiceGenerico xmlservicer;
//	
//	@Autowired
//	private SOAPConnector soapConnector;
//	
//	@GetMapping("/linha")
//	public String linha(@RequestParam(name="action", required=false, defaultValue="none") String action, Model model) throws IOException {
//		
//		Page<Linha> linhas = (Page<Linha>) linhaRepository.findAll(PageRequest.of(0, 100));
//		
//		model.addAttribute("listLinha", linhas.getContent());
//     	
//     	if (!action.equalsIgnoreCase("none")) {
//     		importar(Metodo.LinxCadastraLinhas, getRegistros((List<Linha>) linhas.getContent()));
//     		
//     		for (int x=1; x < linhas.getTotalPages(); x++) {
//     			linhas = (Page<Linha>) linhaRepository.findAll(PageRequest.of(x, 100));
//     			importar(Metodo.LinxCadastraLinhas, getRegistros((List<Linha>) linhas.getContent()));	
//     		}
//		}
//		return "linha";
//	}
//	
//	private void importar(Metodo metodo, ArrayList<Registro> arrayList) {
//		try {
//			xml = xmlservicer.getXml(metodo, arrayList);
//			Response result = soapConnector.sendXML(xml);
//			log.info("Top Internacional: Processamento do lote "+result.getMsg());
//		} catch (IOException e) {
//			writeXML(xml);
//			log.error("Top Internacional: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//	
//	private ArrayList<Registro> getRegistros(List<Linha> list) {
//		ArrayList<Registro> registros = new ArrayList<Registro>();
//		for (Linha linha: list) {
//			Registro registro = new Registro();
//			registro.getColunas().add(new Coluna("codigo", linha.getCodigo()));			
//			registro.getColunas().add(new Coluna("nome_linha", linha.getNomeLinha()));		
//			registros.add(registro);
//		}
//		return registros;
//	}
//	
//	private void writeXML(byte[] xml) {
//		try {
//			FileOutputStream file = new FileOutputStream("xml_problema.xml");
//			file.write(xml);
//			file.close();
//		} catch (IOException e) {
//			log.error("Top Internacional: "+e.getMessage());
//		}
//		
//	}
}