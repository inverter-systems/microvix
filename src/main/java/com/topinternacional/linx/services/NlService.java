package com.topinternacional.linx.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.bean.Cliente;
import com.topinternacional.linx.bean.FormaPagamento;
import com.topinternacional.linx.bean.NotaFiscal;
import com.topinternacional.linx.bean.NotaFiscalItem;
import com.topinternacional.linx.exception.ClienteException;
import com.topinternacional.linx.exception.ConfigException;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.OperacoesConfig;
import com.topinternacional.linx.model.admin.UnidadeConfig;
import com.topinternacional.linx.model.admin.repo.LogRepository;
import com.topinternacional.linx.model.admin.repo.OperacoesConfigRepository;
import com.topinternacional.linx.model.nl.ai.AiCeDiario;
import com.topinternacional.linx.model.nl.ai.AiCrHistoricos;
import com.topinternacional.linx.model.nl.ai.AiCrTitulos;
import com.topinternacional.linx.model.nl.ai.AiNsNota;
import com.topinternacional.linx.model.nl.ai.AiNsNotasIcms;
import com.topinternacional.linx.model.nl.ai.AiNsNotasObservacoes;
import com.topinternacional.linx.model.nl.ai.AiNsNotasOperacoes;
import com.topinternacional.linx.model.nl.ai.AiNsNotasParcelas;
import com.topinternacional.linx.model.nl.ai.AiNsNotasSaidas;
import com.topinternacional.linx.model.nl.ai.repo.AiCeDiarioRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiCrHistoricosRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiCrTitulosRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsItensRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasIcmsRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasObservacoesRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasOperacoesRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasParcelasRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasRepository;
import com.topinternacional.linx.model.nl.ai.repo.AiNsNotasSaidasRepository;
import com.topinternacional.linx.model.nl.core.PsFisicas;
import com.topinternacional.linx.model.nl.core.PsJuridicas;
import com.topinternacional.linx.model.nl.core.PsPessoas;
import com.topinternacional.linx.model.nl.core.PsTelefones;
import com.topinternacional.linx.model.nl.core.PsTelefonesPK;
import com.topinternacional.linx.model.nl.core.repo.G1CidadesRepository;
import com.topinternacional.linx.model.nl.core.repo.PsFisicasRepository;
import com.topinternacional.linx.model.nl.core.repo.PsJuridicasRepository;
import com.topinternacional.linx.model.nl.core.repo.PsPessoasRepository;
import com.topinternacional.linx.model.nl.core.repo.PsTelefonesRepository;
import com.topinternacional.linx.services.util.SOAPConnector;
import com.topinternacional.linx.services.util.Util;

@Service
public class NlService {
	
	@Autowired
	private SOAPConnector soap;
	@Autowired
	private LogRepository log;
	@Autowired
	private AiCeDiarioRepository aiCeDiarioRep;
	@Autowired
	private AiNsItensRepository aiNsItenRep;
	@Autowired
	private AiNsNotasRepository aiNsNotaRep;
	@Autowired
	private AiNsNotasIcmsRepository aiNsNotasIcmsRep;
	@Autowired
	private AiNsNotasOperacoesRepository aiNsNotasOperacoesRep;
	@Autowired
	private AiNsNotasObservacoesRepository aiNsNotasObservacoesRep;
	@Autowired
	private AiNsNotasParcelasRepository aiNsNotasParcelasRep;
	@Autowired
	private AiNsNotasSaidasRepository aiNsNotasSaidasRep;
	@Autowired
	private AiCrTitulosRepository aiCrTitulosRep;
	@Autowired
	private AiCrHistoricosRepository aiCrHistoricosRep; 
	@Autowired
	private PsJuridicasRepository psJuridicasRep;
	@Autowired
	private PsFisicasRepository psFisicasRep;
	@Autowired
	private PsPessoasRepository psPessoasRep;
	@Autowired
	private G1CidadesRepository g1CidadesRep;
	@Autowired
	private PsTelefonesRepository psTelefonesRep;
	@Autowired
	private OperacoesConfigRepository operacoesConfig;
	
	public void limpaTabelaAiNota(Integer codUnidade, Integer numDocumento) {
		
		aiCeDiarioRep.deleteAll(aiCeDiarioRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsItenRep.deleteAll(aiNsItenRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsNotaRep.deleteAll(aiNsNotaRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsNotasOperacoesRep.deleteAll(aiNsNotasOperacoesRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsNotasIcmsRep.deleteAll(aiNsNotasIcmsRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		
		aiNsNotasObservacoesRep.deleteAll(aiNsNotasObservacoesRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsNotasParcelasRep.deleteAll(aiNsNotasParcelasRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiNsNotasSaidasRep.deleteAll(aiNsNotasSaidasRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiCrTitulosRep.deleteAll(aiCrTitulosRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		aiCrHistoricosRep.deleteAll(aiCrHistoricosRep.findByCodUnidadeAndNumDocumento(codUnidade, numDocumento));
		 
	}
	
	public Long getCodPessoa(Long idExec, NotaFiscal nf) throws ClienteException {
		Long codPessoa = null;
		
		Cliente cli = soap.getCliente(idExec, nf);
		Long cnpjCpf = Long.parseLong(cli.getDocCliente());
		Boolean isJuridica = cnpjCpf > Long.valueOf("9999999999999");
				
		// Verifica se a pessoa existe e caso não exista cria
		if (isJuridica) {
			codPessoa = psJuridicasRep.findByNumCgc(cnpjCpf);
			if (codPessoa ==  null) {
				log(idExec, "Pessoa não encontrada, sistema tentará cria o cadastro da pessoa física ("+cnpjCpf+") com as informações do Microvix");
				codPessoa = criarPessoaJuridica(idExec, cli);
			}
		} else {
			codPessoa = psFisicasRep.findByNumCpf(cnpjCpf);
			if (codPessoa ==  null) {
				log(idExec, "Pessoa não encontrada, sistema tentará cria o cadastro da pessoa juridica ("+cnpjCpf+") com as informações do Microvix");
				try {
					codPessoa = criarPessoaFisica(idExec, cli);
				} catch (DataIntegrityViolationException e) {
					log(idExec, "Erro ao tentar criar a pessoa no N&L - "+e.getMessage());
					throw new ClienteException();
				}
			}
		}
		
		log(idExec, "Retornando o código da pessoa ("+codPessoa+") no sistema N&L");
		return codPessoa;
	}
	
	private PsPessoas criarPessoa (Long idExec, Cliente cli) {
		String[] codPessoa = psPessoasRep.getNovaPessoa().split(",");
		PsPessoas pessoa = new PsPessoas();
		
		PsTelefones fones = new PsTelefones();
		
		pessoa.setCodGu(1);
		pessoa.setCodPessoa(codPessoa[0]);
		pessoa.setDigPessoa(codPessoa[1]);;
		pessoa.setDesPessoa(cli.getNomeCliente());
		pessoa.setDesEndereco(cli.getEnderecoCliente());
		pessoa.setDesPontoReferencia(cli.getComplementEndCli());
		pessoa.setDesBairro(cli.getBairroCliente());
		pessoa.setNumCep(cli.getCelCliente());
		pessoa.setDesEmail(cli.getEmailCliente());		
		pessoa.setDesPessoaAscii(cli.getNomeCliente());
		pessoa.setDtaUltAlteracao(new Date());
		
		// Valores sempre fixos 
		pessoa.setIndTorpedos(0);
		pessoa.setCodAtividade(1);
		pessoa.setTipPessoa(2);
		pessoa.setIndInativo(0);
		pessoa.setIndMalaDireta(1);
		
		pessoa.setDtaCadastro(Util.getDataTimeXML(cli.getDataCadastro()));						

		Long codCidade = g1CidadesRep.getCodCidade(cli.getUfCliente(), cli.getCidadeCliente());
		Integer codRegiao = Util.codRegiao(cli.getUfCliente());
		
		// Validar
		if (codCidade == null) {
			log(idExec, "Erro ao validar a cidade do cliente no momento da criação da pessoa no N&L");
			return null;
		} else if (codRegiao ==  null) {
			log(idExec, "Erro ao validar a regiao do cliente no momento da criação da pessoa no N&L");
			return null;
		}
		
		pessoa.setCodCidade(codCidade);
		pessoa.setCodRegiao(codRegiao);
		
		psPessoasRep.save(pessoa);
		
		// Se existir telefone, registra na tabela de telefones
		if (cli.getFoneCliente() != null) {
			fones.setId(new PsTelefonesPK(pessoa.getCodPessoa(), 1));
			fones.setNumFone(cli.getFoneCliente());
			fones.setDesFone("TEL");
			psTelefonesRep.save(fones);
		}
		
		return pessoa;
	}

	private Long criarPessoaJuridica(Long idExec, Cliente cli) {
		
		PsPessoas ps = criarPessoa(idExec, cli);
		PsJuridicas pj = new PsJuridicas();
		
		pj.setCodPessoa(ps.getCodPessoa());
		pj.setNumCgc(cli.getDocCliente());
		pj.setNumInscEst(cli.getDocCliente());
		
		psJuridicasRep.save(pj);
		
		log(idExec, "Pessoa criada com sucesso no N&L");
		
		return ps.getCodPessoa();
	}

	private Long criarPessoaFisica(Long idExec, Cliente cli)  {
		
		PsPessoas ps = criarPessoa(idExec, cli);
		PsFisicas pf = new PsFisicas();
		
		pf.setCodPessoa(ps.getCodPessoa());
		
		try {
			pf.setDtaNasc(Util.getData(cli.getDataNascimento()));
		} catch (ParseException e) {
			log(idExec, "Erro ao converter a data do cadastro no momento da criação da pessoa no N&L");			
			e.printStackTrace();
			return null;
		}
		
		pf.setNumCpf(cli.getDocCliente());
		pf.setNumRg(cli.getIdentidadeCliente());
		pf.setTipSexo(cli.getSexo());
		pf.setCodCidade(g1CidadesRep.getCodCidade(cli.getUfCliente(),cli.getCidadeCliente()));
		
		pf.setTipCivil(cli.getIdEstadoCivil());
		
		// Valores sempre fixos
		pf.setTipResidencia(1); 
		pf.setDesNacionalidade("BRASIL");
		
		psFisicasRep.save(pf);  
		
		log(idExec, "Pessoa criada com sucesso no N&L");
		
		return ps.getCodPessoa();
	}
	
	private void log(Long idExec, String msg) {
		log.save(new Log(idExec, msg));
	}
	
	private BigDecimal getRepresentante(String cpf) {
		Long codigo = psFisicasRep.findByNumCpf(Long.valueOf(cpf));
		return new BigDecimal(codigo);
	}

	private AiCeDiario getAiCeDiario(UnidadeConfig uc, OperacoesConfig op, NotaFiscal nf, NotaFiscalItem item, Long codCliente) throws ConfigException {
		AiCeDiario aiCeDiarios = new AiCeDiario();		
		
		aiCeDiarios.getId().setCodEmp(4);
		aiCeDiarios.getId().setCodUnidade(uc.getCodUnidade());
		aiCeDiarios.getId().setNumNota(nf.getNumero());
		aiCeDiarios.getId().setDtaLancamento(nf.getDataInclusao());
		aiCeDiarios.getId().setCodItem(item.getCodNL());
		
		aiCeDiarios.setCodSerie(nf.getSerie());
		aiCeDiarios.setSeqItem(new BigDecimal(item.getNumItem()));
		aiCeDiarios.setCodOper(op.getCodOper());
		aiCeDiarios.setTipOperacao(new BigDecimal(1)); // 1-Compra/Venda, 2-Transferência, 3-Devolução
		aiCeDiarios.setNumSeqOperNs(op.getNumSeqOper());
		
		aiCeDiarios.setTipLancamento(new BigDecimal(2));
		aiCeDiarios.setQtdLancamento(new BigDecimal(item.getQuantidade()));
		aiCeDiarios.setDesEspecie(item.getCfop() == 5910 ? "BON" : "VEN");
		aiCeDiarios.setNumDocumento(nf.getNumero());
		aiCeDiarios.setDesSerie(nf.getSerie());
		
		aiCeDiarios.setIndEstoque(1);
		aiCeDiarios.setDtaSistema(new Date());
		aiCeDiarios.setCodCondPgtoVenda(90);
		aiCeDiarios.setCodDesconto(30); 
		aiCeDiarios.setCodPessoa(uc.getCodPessoa());
		
		aiCeDiarios.setCodRepresentante(psFisicasRep.findByNumCpf(nf.getVendedor().getCpf()));
		aiCeDiarios.setCodUnidadeNf(uc.getCodUnidade()); 
		aiCeDiarios.setCodUnidadeRetira(uc.getCodUnidade());
		aiCeDiarios.setCodLocal(uc.getCodLocal());
		
		// Impostos
		aiCeDiarios.setVlrIcms(item.getValorLiquido().multiply(item.getIcmsPercent()).divide(new BigDecimal(100)));
		aiCeDiarios.setVlrIcmsSubs(item.getValorSt());
		aiCeDiarios.setVlrBaseSubs(item.getValorSt() != null ? item.getValorLiquido() : null);
		
		// Valores
		aiCeDiarios.setVlrPresente(item.getValorLiquido());
		aiCeDiarios.setVlrImpresso(item.getValorLiquido());
		aiCeDiarios.setVlrTotal(item.getValorLiquido() == item.getDesconto() ? new BigDecimal(0.01) : item.getValorLiquido().subtract(item.getDesconto()));
		aiCeDiarios.setVlrDesconto(item.getValorLiquido() == item.getDesconto() ? item.getDesconto().subtract(new BigDecimal(1)) : item.getDesconto());

		// Diversos
		aiCeDiarios.setIndUltMvto(1);
		aiCeDiarios.setIndVenda(1);
		aiCeDiarios.setIndCustoReal(1);
		aiCeDiarios.setIndCustoPrev(0);
		aiCeDiarios.setIndCustoIdx(0);
		
		aiCeDiarios.setIndAvulso(0);
		aiCeDiarios.setIndDrawback(0);
		aiCeDiarios.setIndContabilidade(0);
		aiCeDiarios.setIndEntrega(0);
		aiCeDiarios.setDtaLocal(new Date());
		
		/* FIXME precisa rever o codigo da lista correta */
		aiCeDiarios.setCodLista(91);
		aiCeDiarios.setDtaTransacao(new Date());
		aiCeDiarios.setTipTransacao(1);
		aiCeDiarios.setTipStatusTransacao(1);

		return aiCeDiarios;
	}
	
	private AiNsNota getAiNsNotas(UnidadeConfig uc, NotaFiscal nf, Long codCliente) {
		AiNsNota nsNotas = new AiNsNota();
		Date dataEmissao  = Util.getDateTimeXMLMicrovix(nf.getNfe().getInfNFe().getIde().getDhEmi());
		
		// Chave da Tabela
		nsNotas.getId().setCodEmp(4); 
		nsNotas.getId().setCodUnidade(uc.getCodUnidade());
		nsNotas.getId().setNumNota(nf.getNumero());
		nsNotas.getId().setCodSerie(nf.getSerie());
		
		nsNotas.setDtaEmissao(dataEmissao);
		nsNotas.setDtaPreco(nf.getDataEmissao());		
		nsNotas.setIndDestaqueFrete(new BigDecimal(1));		
		nsNotas.setCodRepresentante(getRepresentante(nf.getVendedor().getCpf()));
		
		nsNotas.setIndIntegrada(new BigDecimal(0));
		nsNotas.setIndInventariada(new BigDecimal(0));
		nsNotas.setQtdPmv(new BigDecimal(0));
		nsNotas.setIndCreditaRep(new BigDecimal(0));
		nsNotas.setIndFaturada(new BigDecimal(1));
		
		nsNotas.setDesModelo(uc.getDesModelo());
		nsNotas.setIndMercEntregue(new BigDecimal(0));
		nsNotas.setCodPais(new BigDecimal(55));
		nsNotas.setCodUf(uc.getCodUf());
		
		nsNotas.setIndNfe(new BigDecimal(1));
		nsNotas.setCodResultadoNfe(new BigDecimal(nf.getCodSefaz()));
		nsNotas.setDesChaveNfe(nf.getChaveNf());
		nsNotas.setNumModelo(nf.getChaveNf().substring(20, 22));
		
		nsNotas.setDtaTransacao(new Date());
		nsNotas.setTipTransacao(new BigDecimal(1));
		nsNotas.setTipStatusTransacao(new BigDecimal(1));
		
		switch (nf.getCodSefaz()) {
			
			case 100: // 100 - Autorizado o uso da NFe/ NFCe
				nsNotas.setNumDocCob(new BigDecimal(nf.getNumero()));
				nsNotas.setCodCliente(new BigDecimal(codCliente));
				//nsNotas.setDtaSaida(nf.getDataEmissao());
				nsNotas.setCodDesconto(new BigDecimal(30));
				nsNotas.setDtaVenda(nf.getDataEmissao());
				
				nsNotas.setIndStatus(new BigDecimal(1));
				// é nfe cancelada = false		
				nsNotas.setCodCondPgto(new BigDecimal(nf.getFormaPagamento().get(0).getCodigo()));
				nsNotas.setCodLista(new BigDecimal(91)); // Revisar 
				nsNotas.setTipNota(new BigDecimal(2));
									    						    
				nsNotas.setIndProcessada(new BigDecimal(0));
				nsNotas.setIndOrigem(new BigDecimal(1)); // 1=Avulsa, 2=Pedido
				nsNotas.setDthSaida(dataEmissao);
				//new BigDecimal(nf.getNfe().getInfNFe().getIde().getDhEmi())
				nsNotas.setDthDanfe(dataEmissao);
				
				nsNotas.setNumNsuNf(new BigDecimal(nf.getNumero()));				
				nsNotas.setNumReciboNfe(new BigDecimal(0));
				
				break;
			case 101: // 101 - Cancelamento de NFe/NFCe homologado
				nsNotas.setNumDocCob(new BigDecimal(nf.getNumero()));
				nsNotas.setCodCliente(new BigDecimal(codCliente));
				nsNotas.setDtaSaida(nf.getDataEmissao());
				nsNotas.setCodDesconto(new BigDecimal(30));
				nsNotas.setDtaVenda(nf.getDataEmissao());
				
				nsNotas.setIndStatus(new BigDecimal(1));
				nsNotas.setDesEspecie("NF");
				// é nfe cancelada = true
				nsNotas.setCodCondPgto(new BigDecimal(nf.getFormaPagamento().get(0).getCodigo()));
				nsNotas.setCodLista(new BigDecimal(91)); // Revisar 
				nsNotas.setTipNota(new BigDecimal(2));
									    						    
				nsNotas.setIndProcessada(new BigDecimal(0));
				nsNotas.setIndOrigem(new BigDecimal(1)); // 1=Avulsa, 2=Pedido
				nsNotas.setDthSaida(dataEmissao);
				//new BigDecimal(nf.getNfe().getInfNFe().getIde().getDhEmi())
				nsNotas.setDthDanfe(dataEmissao);
				
				nsNotas.setNumNsuNf(new BigDecimal(nf.getNumero()));				
				nsNotas.setNumReciboNfe(new BigDecimal(0));
				
				break;
			case 102:	
				
				nsNotas.setTipNota(new BigDecimal(2));
				nsNotas.setIndProcessada(new BigDecimal(0));
				nsNotas.setIndOrigem(new BigDecimal(0)); // 1=Avulsa, 2=Pedido
				nsNotas.setCodCliente(new BigDecimal(codCliente));
				nsNotas.setIndStatus(new BigDecimal(2));
				// é nfe cancelada = true
				
				break;
			case 212: 
				
				break;
			case 301: // 301 - Uso Denegado
				
				nsNotas.setNumDocCob(new BigDecimal(nf.getNumero()));
				nsNotas.setCodCliente(new BigDecimal(codCliente));
				nsNotas.setDtaSaida(nf.getDataEmissao());
				nsNotas.setCodDesconto(new BigDecimal(30));
				nsNotas.setDtaVenda(nf.getDataEmissao());
				
				nsNotas.setIndStatus(new BigDecimal(2));
				nsNotas.setDesEspecie("NF");
				// é nfe cancelada = true
				nsNotas.setCodCondPgto(new BigDecimal(nf.getFormaPagamento().get(0).getCodigo()));
				nsNotas.setCodLista(new BigDecimal(91)); // FIXME precisa rever o codigo da lista correta
				nsNotas.setTipNota(new BigDecimal(2));
									    						    
				nsNotas.setIndProcessada(new BigDecimal(0));
				nsNotas.setIndOrigem(new BigDecimal(1)); // 1=Avulsa, 2=Pedido
				nsNotas.setNumNsuNf(new BigDecimal(nf.getNumero()));								
				
				break;	
			case 302: // 302 - Uso Denegado
				
				nsNotas.setNumDocCob(new BigDecimal(nf.getNumero()));
				nsNotas.setCodCliente(new BigDecimal(codCliente));
				nsNotas.setDtaSaida(dataEmissao);
				nsNotas.setCodDesconto(new BigDecimal(30));
				nsNotas.setDtaVenda(nf.getDataEmissao());
				
				nsNotas.setIndStatus(new BigDecimal(2));
				nsNotas.setDesEspecie("NF");
				// é nfe cancelada = true
				nsNotas.setCodCondPgto(new BigDecimal(nf.getFormaPagamento().get(0).getCodigo()));
				nsNotas.setCodLista(new BigDecimal(91)); // FIXME precisa rever o codigo da lista correta
				nsNotas.setTipNota(new BigDecimal(2));
									    						    
				nsNotas.setIndProcessada(new BigDecimal(0));
				nsNotas.setIndOrigem(new BigDecimal(1)); // 1=Avulsa, 2=Pedido
				
				nsNotas.setNumNsuNf(new BigDecimal(nf.getNumero()));								
				
				break;		
			default:
	            //caso não for nenhum desses casos	
		}
		
		
		return nsNotas;
	}

	private List<AiCeDiario> getAiCeDiarios(UnidadeConfig uc, NotaFiscal nf, Long codCliente) throws ConfigException {
		List<AiCeDiario> di = new ArrayList<AiCeDiario>();
		
		// Nota Fiscal não cancelada
		if (nf.getCodSefaz() == 100) {									
			for (NotaFiscalItem item: nf.getItens()) {
				di.add(getAiCeDiario(uc, getOperacao(item.getCfop()), nf, item, codCliente)); 
			}
		}
		return di;
	}
	
	private OperacoesConfig getOperacao(Integer cfop) throws ConfigException {
		OperacoesConfig op = operacoesConfig.findByCfop(cfop);
		
		if (op != null) {
			return op;
		} else {
			throw new ConfigException("Foi foi encontrada configuração para o cfop: " + cfop);
		}
	}

	public AiNsNota insertAiNsNotas(UnidadeConfig uc, NotaFiscal nf, Long codCliente) {
		AiNsNota aiNsNota = getAiNsNotas(uc, nf, codCliente);
		aiNsNotaRep.save(aiNsNota);
		return aiNsNota;
	}
	

	public List<AiCeDiario> insertAiCeDiarios(UnidadeConfig uc, NotaFiscal nf, Long codCliente) throws ConfigException {
		List<AiCeDiario> aiCeDiarios = getAiCeDiarios(uc, nf, codCliente); 
		aiCeDiarioRep.saveAll(aiCeDiarios);
		return aiCeDiarios;
	}
	

	public List<AiNsNotasOperacoes> insertAiCeDiarios(Integer codUnidade, Integer numero, String serie) {
		List<AiNsNotasOperacoes> aiNsNotasOperacoes = aiNsNotasOperacoesRep.findByDiarios(codUnidade, numero, serie);
		aiNsNotasOperacoesRep.saveAll(aiNsNotasOperacoes);
		return aiNsNotasOperacoes;
	}

	public List<AiNsNotasIcms> insertAiNsNotasIcms(UnidadeConfig uc, NotaFiscal nf, List<AiNsNotasOperacoes> operacoes) throws ConfigException {
		List<AiNsNotasIcms> list = new ArrayList<AiNsNotasIcms>();
		
		BigDecimal vlrBcIcms = new BigDecimal(0);
		BigDecimal vlrIcms = new BigDecimal(0);
		BigDecimal vlrBcIcmsSt = new BigDecimal(0);
		BigDecimal vlrTotal = new BigDecimal(0);
		
		for (AiNsNotasOperacoes op : operacoes) {
			AiNsNotasIcms aiNsNotasIcms = new AiNsNotasIcms();
			
			aiNsNotasIcms.getId().setCodEmp(4);
			aiNsNotasIcms.getId().setCodUnidade(uc.getCodUnidade());
			aiNsNotasIcms.getId().setNumNota(nf.getNumero());
			aiNsNotasIcms.getId().setCodSerie(nf.getSerie());
			aiNsNotasIcms.getId().setNumSeqOper(op.getId().getNumSeqOper());
			
			for (NotaFiscalItem item : nf.getItens()) {
				if (getOperacao(item.getCfop()).getNumSeqOper() == op.getId().getNumSeqOper()) {
					
					vlrBcIcms =  vlrBcIcms.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
					vlrIcms = vlrIcms.add(item.getValorLiquido().multiply(item.getIcmsPercent()).divide(new BigDecimal(100)));
					vlrBcIcmsSt = item.getValorSt() != null && item.getValorSt().doubleValue() > 0 ? item.getValorLiquido().subtract(item.getDesconto()) : new BigDecimal(0);
					vlrTotal = vlrTotal.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
					
					aiNsNotasIcms.getId().setPerIcms(item.getIcmsPercent());
					
					if (isTributado(item)) {
						// Se for aliquota 0 informar somente isentas
						if (item.getIcmsPercent() == new BigDecimal(0)) { 
							aiNsNotasIcms.setVlrIsIcms(item.getValorLiquido().subtract(item.getDesconto()));
						} else {
							aiNsNotasIcms.setVlrBcIcms(vlrBcIcms);
							aiNsNotasIcms.setVlrIcms(vlrIcms);
							
							aiNsNotasIcms.setVlrBcIcmsSt(vlrBcIcmsSt);
							aiNsNotasIcms.setVlrIcmsSt(item.getValorSt());
							aiNsNotasIcms.setVlrOuIcms(new BigDecimal(0));
							//aiNsNotasIcms.setVlrIsIcms(aiNsNotasIcms.getVlrIsIcms() != null ? aiNsNotasIcms.getVlrIsIcms().add(item.getValorLiquido().subtract(item.getDesconto()).subtract(aiNsNotasIcms.getVlrBcIcms())) : item.getValorLiquido().subtract(item.getDesconto()).subtract(aiNsNotasIcms.getVlrBcIcms()));
							aiNsNotasIcms.setVlrIsIcms(vlrTotal.subtract(vlrBcIcms));
						}
						aiNsNotasIcms.setVlrSt(new BigDecimal(0));
					} else {
						aiNsNotasIcms.setVlrSt(item.getValorLiquido().subtract(item.getDesconto()));
					}
					
					aiNsNotasIcms.setDtaTransacao(new Date());
					aiNsNotasIcms.setTipTransacao(new BigDecimal(1));
					aiNsNotasIcms.setTipStatusTransacao(new BigDecimal(1));
				}
				
			}
			
			list.add(aiNsNotasIcms);
		}
		
		aiNsNotasIcmsRep.saveAll(list);
		return list;
	}

	private boolean isTributado(NotaFiscalItem item) {
		
		if (item.getCst().equals("060") || item.getCst().equals("160") ) {
			return false;
		} else {
			return true;
		}
	}
	

	public AiNsNotasObservacoes insertAiNsNotasObservacoes(UnidadeConfig uc, NotaFiscal nf) {
		AiNsNotasObservacoes ai = new AiNsNotasObservacoes();
		
		ai.getId().setCodEmp(4);
		ai.getId().setCodUnidade(uc.getCodUnidade());
		ai.getId().setNumNota(nf.getNumero());
		ai.getId().setCodSerie(nf.getSerie());
		ai.getId().setNumSeqObs(100);
		
		ai.setTxtObs("Nota gerada no sistema Microvix");
		ai.setIndNf(new BigDecimal(0));
		ai.setIndRegistro(new BigDecimal(0));
		ai.setIndCr(new BigDecimal(0));
		ai.setDthObservacao(nf.getDataEmissao());
		ai.setDtaTransacao(new Date());
		ai.setTipTransacao(new BigDecimal(1));
		ai.setTipStatusTransacao(new BigDecimal(1));

		aiNsNotasObservacoesRep.save(ai);
		return ai;
	}

	@SuppressWarnings("deprecation")
	public List<AiNsNotasParcelas> insertAiNsNotasParcelas(UnidadeConfig uc, NotaFiscal nf) {
		
		List<AiNsNotasParcelas> parcelas = new ArrayList<AiNsNotasParcelas>();
		Integer numTitulo = 0;
		
		for (FormaPagamento pgto : nf.getFormaPagamento()) {
			numTitulo++;
			
			if (pgto.getPgtoConfig().getIndAvista() == new BigDecimal(1)) {
				AiNsNotasParcelas ai = new AiNsNotasParcelas();
				ai.getId().setCodEmp(4);
				ai.getId().setCodUnidade(uc.getCodUnidade());
				ai.getId().setNumNota(nf.getNumero());
				ai.getId().setCodSerie(nf.getSerie());
				ai.getId().setNumParcela(numTitulo);
				ai.getId().setNumParcelaAdm(1);	
				
				ai.setCodLancamento(pgto.getPgtoConfig().getTipTitulo());
				ai.setDtaVenc(nf.getDataEmissao());
				ai.setVlrParcela(pgto.getValor());
				ai.setDesForma(pgto.getPgtoConfig().getDesLancamento().substring(0, 19));
				ai.setDtaEmissao(nf.getDataEmissao());
				ai.setCodCondPgto(pgto.getPgtoConfig().getCodCondPgto());
				ai.setDtaTransacao(new Date());
				ai.setTipTransacao(new BigDecimal(1));
				ai.setTipStatusTransacao(new BigDecimal(1));
				
				parcelas.add(ai);
			} else {
				
				for (Integer x = 1; x <= pgto.getNumeroParcelas(); x++) {
					AiNsNotasParcelas ai = new AiNsNotasParcelas();
					
					Date vencimento = nf.getDataEmissao();
					vencimento.setDate(vencimento.getDate() + (x * 30));
					
					ai.getId().setCodEmp(4);
					ai.getId().setCodUnidade(uc.getCodUnidade());
					ai.getId().setNumNota(nf.getNumero());
					ai.getId().setCodSerie(nf.getSerie());
					ai.getId().setNumParcela(numTitulo);
					ai.getId().setNumParcelaAdm(x);	
					
					ai.setCodLancamento(pgto.getPgtoConfig().getTipTitulo());
					ai.setDtaVenc(vencimento); 
					ai.setVlrParcela(pgto.getValor().divide(new BigDecimal(pgto.getNumeroParcelas())));
					ai.setDesForma(pgto.getPgtoConfig().getDesLancamento().substring(0, 19));
					ai.setDtaEmissao(nf.getDataEmissao());
					ai.setCodCondPgto(pgto.getPgtoConfig().getCodCondPgto());
					ai.setDtaTransacao(new Date());
					ai.setTipTransacao(new BigDecimal(1));
					ai.setTipStatusTransacao(new BigDecimal(1));
					
					parcelas.add(ai);
				}
			}
		}
		aiNsNotasParcelasRep.saveAll(parcelas);
		return parcelas;
	}

	@SuppressWarnings("deprecation")
	public List<AiCrTitulos> insertAiCrTitulos(UnidadeConfig uc, NotaFiscal nf, Long codCliente) {
		List<AiCrTitulos> titulos = new ArrayList<AiCrTitulos>();
		Integer numTitulo = 0;
		
		for (FormaPagamento pgto : nf.getFormaPagamento()) {
			numTitulo++;
			
			if (pgto.getPgtoConfig().getIndAvista() == new BigDecimal(1)) {
				AiCrTitulos ai = new AiCrTitulos();
				ai.getId().setCodEmp(4);
				ai.getId().setCodUnidade(uc.getCodUnidade());
				ai.setNumNota(nf.getNumero());
				ai.setCodSerie(nf.getSerie());	
				ai.setCodEmpNf(4);	
				
				ai.getId().setCodPessoa(codCliente);
				ai.setCodUnidadeNf(uc.getCodUnidade());
				ai.getId().setNumParcela(1);
				ai.setDtaEmissao(nf.getDataEmissao());
				ai.setDtaVencimento(nf.getDataEmissao());
				ai.setDtaVencOrig(nf.getDataEmissao());
				
				ai.setTipTitulo(pgto.getPgtoConfig().getTipTitulo());
				ai.getId().setNumTitulo(nf.getNumero());			
				ai.setTipDctoEsp(1);
				ai.setIndPago(0);
				ai.getId().setCodCompl("S"+numTitulo);
				
				ai.setIndDc(1);
				ai.setCodCondPgto(pgto.getPgtoConfig().getCodCondPgto());
				ai.setDtaTransacao(new Date());
				ai.setTipTransacao(1); 
				ai.setTipStatusTransacao(1);
								
				titulos.add(ai);
			} else {
				
				for (Integer x = 1; x <= pgto.getNumeroParcelas(); x++) {
					AiCrTitulos ai = new AiCrTitulos();
					
					Date vencimento = nf.getDataEmissao();
					vencimento.setDate(vencimento.getDate() + (x * 30));
					
					ai.getId().setCodEmp(4);
					ai.getId().setCodUnidade(uc.getCodUnidade());
					ai.setNumNota(nf.getNumero());
					ai.setCodSerie(nf.getSerie());	
					ai.setCodEmpNf(4);	
					
					ai.getId().setCodPessoa(codCliente);
					ai.setCodUnidadeNf(uc.getCodUnidade());
					ai.getId().setNumParcela(x);
					ai.setDtaEmissao(nf.getDataEmissao());
					ai.setDtaVencimento(vencimento);
					ai.setDtaVencOrig(vencimento);
					
					ai.setTipTitulo(pgto.getPgtoConfig().getTipTitulo());
					ai.getId().setNumTitulo(nf.getNumero());			
					ai.setTipDctoEsp(1);
					ai.setIndPago(0);
					ai.getId().setCodCompl("S"+numTitulo);
					
					ai.setIndDc(1);
					ai.setCodCondPgto(pgto.getPgtoConfig().getCodCondPgto());
					ai.setDtaTransacao(new Date());
					ai.setTipTransacao(1); 
					ai.setTipStatusTransacao(1);
					
					titulos.add(ai);
				}
			}
		}
		aiCrTitulosRep.saveAll(titulos);
		return titulos;
	}

	@SuppressWarnings("deprecation")
	public List<AiCrHistoricos> insertAiCrHistoricos(UnidadeConfig uc, NotaFiscal nf, Long codCliente) {
		List<AiCrHistoricos> titulos = new ArrayList<AiCrHistoricos>();
		Integer numTitulo = 0;
		Date now = new Date();
		
		for (FormaPagamento pgto : nf.getFormaPagamento()) {
			numTitulo++;
			
			if (pgto.getPgtoConfig().getIndAvista() == new BigDecimal(1)) {  // Se for Venda à vista
				AiCrHistoricos ai = new AiCrHistoricos();
				
				ai.getId().setCodEmp(4);
				ai.getId().setCodUnidade(uc.getCodUnidade());
				ai.getId().setCodPessoa(codCliente);
				ai.getId().setNumTitulo(nf.getNumero());
				ai.getId().setNumParcela(1);
				ai.getId().setCodCompl("S"+numTitulo);
				ai.getId().setCodMaquina(0);
				ai.getId().setSeqLancamento(1);
				
				ai.setCodEmpNf(4);	
				ai.setCodUnidadeNf(uc.getCodUnidade());
				ai.setNumNota(nf.getNumero());
				ai.setCodSerie(nf.getSerie());	
				ai.setCodLancamento(1);
				ai.setIndDc(1);
				
				ai.setDtaSistema(nf.getDataInclusao());
				ai.setDtaPagamento(nf.getDataInclusao());
				ai.setDtaContabil(nf.getDataInclusao());
				ai.setVlrLancamento(pgto.getValor());
				ai.setCodUnidadePgto(uc.getCodUnidade());
				
				ai.setIndIntegrado(0);
				ai.setIndLancamento(1);
				ai.setTipLancamento(0);
				ai.setTxtObservacao("Integração Microvix Data: "+now);
				ai.setIndSituacao(1);
				ai.setDtaTransacao(now);
				ai.setTipStatusTransacao(1);
				
				titulos.add(ai);
			} else {
				for (Integer x = 1; x <= pgto.getNumeroParcelas(); x++) {
					AiCrHistoricos ai = new AiCrHistoricos();
					
					Date vencimento = nf.getDataEmissao();
					vencimento.setDate(vencimento.getDate() + (x * 30));
					
					ai.getId().setCodEmp(4);
					ai.getId().setCodUnidade(uc.getCodUnidade());
					ai.getId().setCodPessoa(codCliente);
					ai.getId().setNumTitulo(nf.getNumero());
					ai.getId().setNumParcela(x);
					ai.getId().setCodCompl("S"+numTitulo);
					ai.getId().setCodMaquina(0);
					ai.getId().setSeqLancamento(1);
					
					ai.setCodEmpNf(4);	
					ai.setCodUnidadeNf(uc.getCodUnidade());
					ai.setNumNota(nf.getNumero());
					ai.setCodSerie(nf.getSerie());	
					ai.setCodLancamento(1);
					ai.setIndDc(1);
					
					ai.setDtaSistema(nf.getDataInclusao());
					ai.setDtaPagamento(nf.getDataInclusao());
					ai.setDtaContabil(nf.getDataInclusao());
					ai.setVlrLancamento(pgto.getValor());
					ai.setCodUnidadePgto(uc.getCodUnidade());
					
					ai.setIndIntegrado(0);
					ai.setIndLancamento(1);
					ai.setTipLancamento(0);

					ai.setTxtObservacao("Integração Microvix Data: "+now);
					ai.setIndSituacao(1);
					ai.setDtaTransacao(now);
					ai.setTipTransacao(1);
					ai.setTipStatusTransacao(1);
					
					titulos.add(ai);
				}
			}
		}	
		
		aiCrHistoricosRep.saveAll(titulos);
		return titulos;
	}

	public AiNsNotasSaidas insertAiNsNotasSaidas(UnidadeConfig uc, NotaFiscal nf) {
		AiNsNotasSaidas ai = new AiNsNotasSaidas();
		ai.getId().setCodEmp(4);
		ai.getId().setCodUnidade(uc.getCodUnidade());
		ai.getId().setNumNota(nf.getNumero());
		ai.getId().setCodSerie(nf.getSerie());		
		
		aiNsNotasSaidasRep.save(ai);
		return ai;
	}

	public List<AiCeDiario> getErrosImportacao(UnidadeConfig uc, NotaFiscal nf) {
		List<AiCeDiario> ai = aiCeDiarioRep.findErrors(uc.getCodUnidade(), nf.getNumero(), nf.getSerie());
		return ai;
	}

	public void excluiNota(UnidadeConfig uc, NotaFiscal nf) {
		Integer numSeq = aiNsNotaRep.getNumSeqNota(uc.getCodUnidade(), nf.getNumero(), nf.getSerie());
		
		if (numSeq != null) {
			aiNsNotaRep.excluiNota(numSeq,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}	
	}
}
