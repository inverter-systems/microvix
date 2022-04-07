package com.topinternacional.linx.services.admin;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.services.util.Util;

@Service
public class ExecucoesService {
	
	@Autowired
	private ExecucoesRepository repo;
	
	public Page<Execucoes> getExecucoes(String dtaInicio, String origem, String cadastro, Sort sort, int pag) throws ParseException {
		PageRequest pr = PageRequest.of(pag-1, 10, sort);
		
		Date dataInicioExecucao = dtaInicio.equals("SN") ? null : Util.getData(dtaInicio);
		Date dataFinalExecucao = Util.getDataMaisUmDia(dataInicioExecucao);
		Integer origemExecucao = origem.equals("SN") ? null: Integer.valueOf(origem);
		
		if (dtaInicio.equals("SN") && origem.equals("SN") && cadastro.equals("SN")) {
			return (Page<Execucoes>) repo.findAll(pr);
		} else if (!dtaInicio.equals("SN")) {	
			return (Page<Execucoes>) repo.findByDtaInicio(dataInicioExecucao, dataFinalExecucao, pr);
		} else if (!origem.equals("SN")) {	
			return (Page<Execucoes>) repo.findByOrigem(origemExecucao, pr);
		} else if (!cadastro.equals("SN")) {	
			return (Page<Execucoes>) repo.findByCadastro(cadastro, pr);
		}
		return null;
	}
	
	
	
	
}