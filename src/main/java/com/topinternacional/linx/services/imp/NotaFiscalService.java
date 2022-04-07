package com.topinternacional.linx.services.imp;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.api.BasicService;
import com.topinternacional.linx.bean.Cliente;
import com.topinternacional.linx.bean.Coluna;
import com.topinternacional.linx.bean.FormaPagamento;
import com.topinternacional.linx.bean.NotaFiscal;
import com.topinternacional.linx.bean.NotaFiscalItem;
import com.topinternacional.linx.bean.Produto;
import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.bean.Vendedor;
import com.topinternacional.linx.controller.imp.NotaController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.exception.ClienteException;
import com.topinternacional.linx.exception.ComunicacaoException;
import com.topinternacional.linx.exception.ConfigException;
import com.topinternacional.linx.exception.SemNotasParaImportarException;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.FormasPgtoConfig;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.UnidadeConfig;
import com.topinternacional.linx.model.admin.repo.FormasPgtoConfigRepository;
import com.topinternacional.linx.model.admin.repo.LogRepository;
import com.topinternacional.linx.model.admin.repo.UnidadesConfigRepository;
import com.topinternacional.linx.model.nl.ai.AiCeDiario;
import com.topinternacional.linx.model.nl.ai.AiCrHistoricos;
import com.topinternacional.linx.model.nl.ai.AiCrTitulos;
import com.topinternacional.linx.model.nl.ai.AiNsNota;
import com.topinternacional.linx.model.nl.ai.AiNsNotasIcms;
import com.topinternacional.linx.model.nl.ai.AiNsNotasObservacoes;
import com.topinternacional.linx.model.nl.ai.AiNsNotasOperacoes;
import com.topinternacional.linx.model.nl.ai.AiNsNotasParcelas;
import com.topinternacional.linx.model.nl.ai.AiNsNotasSaidas;
import com.topinternacional.linx.model.nl.view.Unidade;
import com.topinternacional.linx.model.nl.view.repo.UnidadeRepository;
import com.topinternacional.linx.services.NlService;
import com.topinternacional.linx.services.util.Util;

@Service
public class NotaFiscalService extends BasicService {
	
	@Autowired
	private UnidadesConfigRepository unidadeConfig;
	
	@Autowired
	private FormasPgtoConfigRepository formasPgtoConfig;
	
	@Autowired
	private UnidadeRepository unidade;
	
	@Autowired
	private NlService nlService;
	
	@Autowired
	private LogRepository log;
	
	List<UnidadeConfig> uc = new ArrayList<UnidadeConfig>();
	
	public List<UnidadeConfig> getUnidadeConfig() {
		if (this.uc.isEmpty()) {
			this.uc = unidadeConfig.findAllOrderByCodUnidade(PageRequest.of(0, 1000)).getContent();
		}
		return this.uc;
	}

	@Async("taskExecutor")
	public void importarNota(NotaFiscal nf) {
		UnidadeConfig uc = getUnidadeConfig(nf.getCnpjCpf());
		Long codCliente = 0L;
		//List<FormaPagamento> formaPagamento = new ArrayList<FormaPagamento>();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Importação Nota Fiscal nº "+nf.getNumero()+" serie "+nf.getSerie()+" data "+nf.getDataEmissaoFmt()+" unidade "+uc.getCodUnidade(), Sistemas.MICROVIX.getId(),  Sistemas.NL.getId(), "Notas Fiscais", 1)); 
		
		try {
			
			//Atualiza o status de execução
			NotaController.setStatus(Status.PROCESSANDO, 0);
			
			// Verifica comunicação com a origem dos dados e o destino. Aboorta em caso de falha
			testeComunicacao();
			log.save(new Log(exec.getId(), "Teste de comunicação realizado com sucesso"));
		
			// Limpando tabelas AI da nota
			log.save(new Log(exec.getId(), "Limpando os dados de importações anteriores nas tabelas do AI do N&L"));
			nlService.limpaTabelaAiNota(uc.getCodUnidade(), nf.getNumero());	
			
			// Identificando o destinatário - Buscando o codigo da Pessoa no NL
			log.save(new Log(exec.getId(), "Localizando o cliente"));
			if (codCliente ==  0L) codCliente = nlService.getCodPessoa(exec.getId(), nf);
			
			// Identificando a forma de pagamento
			log.save(new Log(exec.getId(), "Identificando as formas de pagamento do documento"));
			nf.setFormaPagamento(getFormasPagamento(nf, exec));
			
			// Inserindo AI_NS_NOTAS
			AiNsNota aiNsNota = nlService.insertAiNsNotas(uc, nf, codCliente);
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS - Nota Fiscal nº "+aiNsNota.getId().getNumNota()+" serie "+aiNsNota.getId().getCodSerie()));
			
			// Inserindo AI_CE_DIARIOS
			List<AiCeDiario> aiCeDiarios = nlService.insertAiCeDiarios(uc, nf, codCliente); 
			log.save(new Log(exec.getId(), "Inserindo AI_CE_DIARIOS - Inserindo "+aiCeDiarios.size()+" itens da Nota Fiscal nº "+aiNsNota.getId().getNumNota()));		
			
			// Inserindo AI_NS_NOTAS_OPERACOES
			List<AiNsNotasOperacoes> aiNsNotasOperacoes = nlService.insertAiCeDiarios(uc.getCodUnidade(), nf.getNumero(), nf.getSerie());
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS_OPERACOES - Inserindo "+aiNsNotasOperacoes.size()+" operaçã(õe)s dos itens"));
			
			// Inserindo AI_NS_NOTAS_ICMS
			List<AiNsNotasIcms> aiNsNotasIcms = nlService.insertAiNsNotasIcms(uc, nf, aiNsNotasOperacoes);
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS_ICMS - Inserindo "+aiNsNotasIcms.size()+" dois registros de ICMS dos itens"));

			// Inserindo AI_NS_NOTAS_OBSERVACOES
			AiNsNotasObservacoes aiNsNotasObservacoes = nlService.insertAiNsNotasObservacoes(uc, nf);
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS_OBSERVACOES para a Nota Fiscal nº "+aiNsNotasObservacoes.getId().getNumNota()));
			
			// Inserindo AI_NS_NOTAS_PARCELAS
			List<AiNsNotasParcelas> aiNsNotasParcelas = nlService.insertAiNsNotasParcelas(uc, nf);
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS_PARCELAS para a Nota Fiscal nº "+aiNsNotasParcelas.get(0).getId().getNumNota()));
			
			// Inserindo AI_CR_TITULOS
			List<AiCrTitulos> aiCrTitulos = nlService.insertAiCrTitulos(uc, nf, codCliente);
			log.save(new Log(exec.getId(), "Inserindo AI_CR_TITULOS para a Nota Fiscal nº "+aiCrTitulos.get(0).getNumNota()));
			
			// Inserindo AI_CR_HISTORICOS
			List<AiCrHistoricos> aiCrTHistoricos = nlService.insertAiCrHistoricos(uc, nf, codCliente);
			log.save(new Log(exec.getId(), "Inserindo AI_CR_HISTORICOS para a Nota Fiscal nº "+aiCrTHistoricos.get(0).getNumNota()));
			
			// Inserindo AI_NS_NOTAS_SAIDAS - Disparando a trigger de processamento de importação
			AiNsNotasSaidas aiNsNotasSaidas = nlService.insertAiNsNotasSaidas(uc, nf);
			log.save(new Log(exec.getId(), "Inserindo AI_NS_NOTAS_SAIDAS para a Nota Fiscal nº "+aiNsNotasSaidas.getId().getNumNota()));
			
			// TODO implementar metodo para procura de erro no estoque
			List<AiCeDiario> erros = nlService.getErrosImportacao(uc, nf);
			log.save(new Log(exec.getId(), "Procurando erros na imporatação da Nota Fiscal nº "+aiNsNotasSaidas.getId().getNumNota()));
			
			if (erros != null && erros.size() > 0) {
				for (AiCeDiario erro : erros) {
					log.save(new Log(exec.getId(), "Erro: "+erro.getTxtErro()));
				}
				
				log.save(new Log(exec.getId(), "Cancelando a importação da Nota Fiscal nº "+nf.getNumero()+" da unidade "+uc.getCodUnidade()+"!"));
				nlService.excluiNota(uc, nf);
				
			} else {
				log.save(new Log(exec.getId(), "Importação da Nota Fiscal nº "+nf.getNumero()+" da unidade "+uc.getCodUnidade()+", foi concluída..."));
			}
			
			// TODO implementar metodo para procura de erro fiscais
			procuraErrosFiscais();
			
			//Atualiza o status de execução
			NotaController.setStatus(Status.CONCLUIDO, 100);		
		
		} catch (ComunicacaoException  e) {
			log.save(new Log(exec.getId(), "Erro de comunicação..."));
			NotaController.setStatus(Status.ERRO, 100, "Erro de comunicação - "+e.getMessage());		
			log.save(new Log(exec.getId(), "Abortando a integração..."));
			e.printStackTrace();
		} catch (ClienteException e) {
			NotaController.setStatus(Status.ERRO, 100, "Erro ao consultar/cadastrar o cliente - "+e.getMessage());
			log.save(new Log(exec.getId(), "Abortando a integração..."));
			e.printStackTrace();
		} catch (ConfigException e) {
			NotaController.setStatus(Status.ERRO, 100, "Erro por falta de configuração - "+e.getMessage());
			log.save(new Log(exec.getId(), "Abortando a integração..."));
			e.printStackTrace();
		} 
	}
	
	private void procuraErrosFiscais() {
		// TODO Auto-generated method stub
		
	}

	private List<FormaPagamento> getFormasPagamento(NotaFiscal nf, Execucoes exec) throws ConfigException {
		List<FormaPagamento> formaPagamento = nf.getFormaPagamento();
		
		for (FormaPagamento fpagto : formaPagamento) {
			
			// Verificando configuração das formas de pagamento
			FormasPgtoConfig fp = formasPgtoConfig.findCondPgtoMICROVIX(fpagto.getCodigo());
			
			if (fp != null) {
				fpagto.setPgtoConfig(fp);
				log.save(new Log(exec.getId(), "Forma verificada N&L nº"+fp.getCodCondPgto()+" - Linx "+fpagto.getCodigo()+" - "+fpagto.getDescricao()));
			} else {
				log.save(new Log(exec.getId(), "Parametro não encontrado (TOPT_LINX_COND_PGTO), condição nº "+fpagto.getCodigo()+" - "+fpagto.getDescricao()));
				throw new ConfigException("Parametro não encontrado (TOPT_LINX_COND_PGTO), condição nº "+fpagto.getCodigo()+" - "+fpagto.getDescricao());
			}
			
		}
		
		return formaPagamento;
	}
	
	private UnidadeConfig getUnidadeConfig(String cnpjCpf) {
		for (UnidadeConfig u : getUnidadeConfig()) {
			if (u.getCnpj().equals(cnpjCpf)) {
				return u;
			}
		}
		return null;
	}
	
	private UnidadeConfig getUnidadeConfig(Integer codUnidade) {
		for (UnidadeConfig u : getUnidadeConfig()) {
			if (u.getCodUnidade() == codUnidade) {
				return u;
			}
		}
		return null;
	}

	private void testeComunicacao() throws ComunicacaoException  {
		int num = 1;
		if (num > 1) {
			throw new ComunicacaoException();
		}
	}

	public Page<NotaFiscal> getNotaFiscaisMicrovix(String unidade, String dtaInicio, String dtaFim, Integer pag) throws IOException, ParseException, SemNotasParaImportarException {
		if (Util.isNullOrBlank(unidade) || Util.isNullOrBlank(dtaInicio) || Util.isNullOrBlank(dtaFim)) return null; 
		
		PageRequest pr = PageRequest.of(pag-1, 10);
		
		UnidadeConfig unidadeCfg = getUnidadeConfig(Integer.valueOf(unidade));

		List<NotaFiscal> notas = NotaFiscal.getNotasMicrovix(soap.consultar(Metodo.LinxXMLDocumentos, getRegistrosDocumento(unidadeCfg.getCnpj(), Util.getDataXMLMicrovsixSend(dtaInicio), Util.getDataXMLMicrovsixSend(dtaFim))));
			
		if (notas == null) throw new SemNotasParaImportarException();
		
		int start = (int) pr.getOffset();
		int end = (start + pr.getPageSize()) > notas.size() ? notas.size() : (start + pr.getPageSize());
		Page<NotaFiscal> nf =  new PageImpl<NotaFiscal>(notas.subList(start, end), pr, notas.size());
				
		return nf;
	}

	public NotaFiscal getNotaFiscal(String id, Integer codUnidade) throws IOException {
		UnidadeConfig uc = getUnidadeConfig(codUnidade);  
		
		// Cria uma nota fiscal a partir do xml de retono
		NotaFiscal nota = NotaFiscal.getNotaMicrovix(soap.consultar(Metodo.LinxXMLDocumentos, getRegistrosDocumento(uc.getCnpj(), id)));
		
		// Cria uma lista com os itens da nota
		List<NotaFiscalItem> itens = NotaFiscalItem.getItensNotaMicrovix(soap.consultar(Metodo.LinxMovimento, getRegistrosMovimento(uc.getCnpj(), id)));
				
		for (NotaFiscalItem nfi : itens) {
			Produto produto = Produto.getProduto(soap.consultar(Metodo.LinxProdutos, getRegistrosProduto(uc.getCnpj(), nfi.getCodProduto())));
			nfi.setCodNL(produto.getCodigoNL());
			nfi.setDescricao(produto.getDescricao());
		}
		
		nota.setCodClienteMicrovix(itens.get(0).getCodCliente());		
		nota.setModeloNf(itens.get(0).getModeloNF());
		nota.setOperacao(itens.get(0).getOperacao());
		
		// Cria uma lista com as formas de pagto do documento
		List<FormaPagamento> pgto = FormaPagamento.getFormasPagamento(soap.consultar(Metodo.LinxMovimentoPlanos, getRegistrosMovimento(uc.getCnpj(), id)));
				
		nota.setFormaPagamento(pgto);
		
		nota.setNaturezaOperacao(itens.get(0).getNaturezaOperacao());
		nota.setObservacoes(itens.get(0).getObservacoes());
		
		Cliente cliente = Cliente.getCliente(soap.consultar(Metodo.LinxClientesFornec, getRegistrosCliente(uc.getCnpj(), nota.getCodClienteMicrovix())));
		Vendedor vendedor = Vendedor.getVendedor(soap.consultar(Metodo.LinxVendedores, getRegistrosVendedor(uc.getCnpj(), itens.get(0).getCodVendedor())));
		
		nota.setItens(itens); 
		nota.setCliente(cliente);
		nota.setVendedor(vendedor);
				
		return nota;
	}

	private ArrayList<Registro> getRegistrosCliente(String cnpj, Integer codClienteMicrovix) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		Registro registro = new Registro();
		
		registro.getColunas().add(new Coluna("cnpjEmp", cnpj));					
		registro.getColunas().add(new Coluna("data_inicial", "NULL"));
		registro.getColunas().add(new Coluna("data_fim", "NULL"));
		registro.getColunas().add(new Coluna("cod_cliente", codClienteMicrovix.toString()));
				
		registros.add(registro);
		
		return registros;
	}	

	private ArrayList<Registro> getRegistrosVendedor(String cnpj, Integer codVendedor) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		Registro registro = new Registro();
		
		registro.getColunas().add(new Coluna("cnpjEmp", cnpj));					
		registro.getColunas().add(new Coluna("cod_vendedor", codVendedor.toString()));
				
		registros.add(registro);
		
		return registros;
	}

	private ArrayList<Registro> getRegistrosProduto(String cnpj, Integer codProduto) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		Registro registro = new Registro();
		
		registro.getColunas().add(new Coluna("cnpjEmp", cnpj));					
		registro.getColunas().add(new Coluna("dt_update_inicio", "2021-01-01"));
		registro.getColunas().add(new Coluna("dt_update_fim", "2221-11-29"));
		registro.getColunas().add(new Coluna("cod_produto", codProduto.toString()));		
				
		registros.add(registro);
		
		return registros;
	}

	private ArrayList<Registro> getRegistrosMovimento(String cnpj, String id) {
		return getRegistrosDocumento(cnpj,id);
	}

	private ArrayList<Registro> getRegistrosDocumento(String cnpj, String id) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		Registro registro = new Registro();
		
		registro.getColunas().add(new Coluna("cnpjEmp", cnpj));					
		registro.getColunas().add(new Coluna("data_inicial", "2021-01-01"));
		registro.getColunas().add(new Coluna("data_fim", "2221-11-29"));
		registro.getColunas().add(new Coluna("identificador", id));		
		registro.getColunas().add(new Coluna("timestamp", "0"));				
				
		registros.add(registro);
		
		return registros;
	}
	
	private ArrayList<Registro> getRegistrosDocumento(String cnpj, String dtaInicio, String dtaFim) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		Registro registro = new Registro();
		
		registro.getColunas().add(new Coluna("cnpjEmp", cnpj));					
		registro.getColunas().add(new Coluna("data_inicial", dtaInicio));
		registro.getColunas().add(new Coluna("data_fim", dtaFim));
		registro.getColunas().add(new Coluna("timestamp", "0"));				
				
		registros.add(registro);
		
		return registros;
	}

	public List<Unidade> getUnidades() {
		return (List<Unidade>) unidade.findAll();
	}

}
