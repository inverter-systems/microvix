package com.topinternacional.linx.controller.admin;

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
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.services.admin.LogService;
import com.topinternacional.linx.services.util.Util;

@Controller
public class LogController extends BasicController  {
	
	@Autowired
	private LogService service;
	
	@Autowired
	private ExecucoesController execController;
		
	@Autowired
	private ExecucoesRepository execucoesRepository;
	
	@GetMapping("/excluirlog")
	public String ecluirlog( @RequestParam(name="execId", required=false, defaultValue="none") Long execId			                
			                 , Model model) throws IOException {
		
		service.excluirExecucao(execId); 
		
		return execController.agendamento("none", 1, "SN", "SN", "SN", model);
	}
	
	@GetMapping("/log")
	public String agendamento( @RequestParam(name="action", required=false, defaultValue="none") String action
							 , @RequestParam(name="execId", required=false, defaultValue="none") String execId
			                 , @RequestParam(name="pag", required=false, defaultValue="1") int pag 
			                 , Model model) throws IOException {
		
		ajustaStatus();
		
		//Page<Log> log = (Page<Log>) logRepository.findByExecIdOrderById(Long.parseLong(execId), PageRequest.of(pag, 25));
		Page<Log> log = service.getLog(Long.parseLong(execId), Sort.by("id"), pag);
		
		//Execucoes exec = execucoesRepository.findById(Long.parseLong(execId));
		Execucoes exec = execucoesRepository.findById(Long.parseLong(execId)).get();
		

		if (!status.equals(Status.PROCESSANDO) && !action.equals("none")) {
			service.reprocesarErros(exec);
		}
		
		
		Integer totalPages = log.getTotalPages();
		Long totalItems = log.getTotalElements();
		Double sucessPercet = service.getLogSucess(Long.parseLong(execId), totalItems);
		double errorPercet = 100 - sucessPercet;
				 
		model.addAttribute("listLog", log.getContent());
		model.addAttribute("codigo", execId);
		model.addAttribute("totalPages", totalPages); 
		model.addAttribute("currentPage", pag);
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("sucess", Util.getPercent(sucessPercet));
		model.addAttribute("error", Util.getPercent(errorPercet));
		
		
		model.addAttribute("descricao", exec.getNome());
		model.addAttribute("origem", exec.getOrigemFmt());
		model.addAttribute("destino", exec.getDestinoFmt());
		model.addAttribute("cadastro", exec.getCadastro());
		model.addAttribute("data", exec.getDtaInicio());
		model.addAttribute("paginas", exec.getQtdPaginas());
		
		
		model.addAttribute("path", "log");		
		model.addAttribute("filter", "&execId="+execId);
		model.addAttribute("help", "<p>Esta tela permite visualizar os logs de execuções das integração realizadas.</p>"
						  );

		return "admin/log";
	}
	
	@GetMapping("/log/status")
	@ResponseBody
	public String getStatus(Model model) {
		return status.getDescricao()+":"+andamento;			
	}

}