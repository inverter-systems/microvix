package com.topinternacional.linx.services.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.topinternacional.linx.bean.Cliente;
import com.topinternacional.linx.bean.NotaFiscal;
import com.topinternacional.linx.bean.NotaFiscalItem;
import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.bean.Response;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Submit;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.repo.LogRepository;

@Service
public class SOAPConnector {
	
	@Autowired
	private XMLServiceGenerico xmlservicer;
	
	@Autowired
	private LogRepository log;
	
	//private static final Logger log = LoggerFactory.getLogger(PessoaController.class);
	private byte[] xml;

	
	public Response sendXML(byte[] xml) throws IOException {
		 String url = "https://webapi.microvix.com.br/1.0/importador.svc";
		 //String url = "http://localhost:8081/";
		 URL obj = new URL(url);
		 
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setDoOutput(true);
		 con.setDoInput(true);
		 con.setRequestMethod("POST");
		 con.setRequestProperty("SOAPAction","\"http://tempuri.org/IImportador/Importar\"");
		 con.setRequestProperty("Accept","gzip,deflate");
		 con.setRequestProperty("Accept","text/xml");
		 con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
		 		 
		 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 wr.write(xml);
		 wr.flush();
		 wr.close();
		 
		 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while ((inputLine = in.readLine()) != null) {
			 response.append(inputLine);
		 }
		 
		 Submit statusEnvio = response.indexOf("<a:Succeeded>true</a:Succeeded>") != -1 ? Submit.SUCESS : Submit.PROBLEM;
		 
		 in.close();
	
		 return new Response(statusEnvio, response.toString());
	}
	
	public Response sendXmlWebServiceSaida(byte[] xml) throws IOException {
		 String url = "https://webapi.microvix.com.br/1.0/api/integracao";
		 //String url = "http://localhost:8081/";
		 URL obj = new URL(url);
		 
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setDoOutput(true);
		 con.setDoInput(true);
		 con.setRequestMethod("POST");
		 con.setRequestProperty("SOAPAction","\"http://tempuri.org/IImportador/Importar\"");
		 con.setRequestProperty("Accept","gzip,deflate");
		 con.setRequestProperty("Accept","text/xml");
		 con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
		 		 
		 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 wr.write(xml);
		 wr.flush();
		 wr.close();
		 
		 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while ((inputLine = in.readLine()) != null) {
			 response.append(inputLine);
		 }
		 
		 Submit statusEnvio = response.indexOf("<ResponseSuccess>True</ResponseSuccess>") != -1 ? Submit.SUCESS : Submit.PROBLEM;
		 
		 in.close();
	
		 return new Response(statusEnvio, response.toString());
	}
	
	public void importar(Metodo metodo, ArrayList<Registro> arrayList, Integer pag, Integer totalPages, String className, Long id) {
		Response result = null;
		try {
			xml = xmlservicer.getXml(metodo, arrayList);
			result = sendXML(xml);
			
			log.save(new Log(id, "Processamento do lote "+pag+"/"+(totalPages-1)+" - Status: "+result.getStatusEnvio().getDescricao()));
						
			if (result.getStatusEnvio().equals(Submit.PROBLEM)) {
				//if (result.getMsg().indexOf("Violation of UNIQUE KEY constraint") == -1) {
					writeXML(className, xml, pag, result);
				//}				
			}
			
		} catch (IOException e) {
			writeXML(className, xml, pag, result);
			//log.error("Top Internacional: "+e.getMessage());
			log.save(new Log(id, "Erro Java:: "+e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public Cliente getCliente(Long logId, NotaFiscal nf) {	
		if (nf == null) return null;
			 
		Cliente cli = new Cliente();
		xml = xmlservicer.getXmlConsultaClientes(nf.getCodClienteMicrovix());
		try {
			Response result = sendXmlWebServiceSaida(xml);
			
			writeXML("Notas", xml, 0, result);
			log.save(new Log(logId, "Buscando informações da pessoa pelo webservice do Microvix - Status: "+result.getStatusEnvio().getDescricao()));
						
			if (result.getStatusEnvio().equals(Submit.PROBLEM)) {
				// Colocar codigo aqui
			}
			
			cli = getCliente(result);
			
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		return cli;
	}
		
	private Cliente getCliente(Response res) throws SAXException, IOException, ParserConfigurationException {
		Cliente cli = new Cliente();
		Integer ini = res.getMsg().indexOf("<R>")+2;
		Integer fim = res.getMsg().lastIndexOf("</R>")-2;		
		
		List<String> rows = Arrays.asList(res.getMsg().substring(1).substring(ini, fim).replace("</R>", "").split("<R>"));
		
		for (String row : rows) {
			String[] col = (row.replace("<D />", "<D></D>")).replace("</D>", "").replace("</D", "").split("<D>");
			
			cli.setPortal(col[1]);
	    	cli.setCodCliente(col[2]);
	    	cli.setRazaoCliente(col[3]);
	    	cli.setNomeCliente(col[4]);
	    	cli.setDocCliente(col[5]);
	    	
	    	cli.setTipoCliente(col[6]);
	    	cli.setEnderecoCliente(col[7]);
	    	cli.setNumeroRuaCliente(col[8]);
	    	cli.setComplementEndCli(col[9]);
	    	cli.setBairroCliente(col[10]);
	    	
	    	cli.setCepCliente(col[11]);
	    	cli.setCidadeCliente(col[12]);
	    	cli.setUfCliente(col[13]);
	    	cli.setPais(col[14]);
	    	cli.setFoneCliente(col[15]);
	    	
	    	cli.setEmailCliente(col[16]);
	    	cli.setSexo(col[17]);
	    	cli.setDataCadastro(col[18]);
	    	cli.setDataNascimento(col[19]);
	    	cli.setCelCliente(col[20]);
	    	
	    	cli.setAtivo(col[21]);
	    	cli.setDtUpdate(col[22]);
	    	cli.setInscricaoEstadual(col[23]);
	    	cli.setIncricaoMunicipal(col[24]);
	    	cli.setIdentidadeCliente(col[25]);
	    	
	    	cli.setCartaoFidelidade(col[26]);
	    	cli.setCodIbgeMunicipio(col[27]);
	    	cli.setClasseCliente(col[28]);
	    	cli.setMatriculaConveniado(col[29]);
	    	cli.setTipoCadastro(col[30]);
	    	
	    	cli.setEmpresaCadastro(col[31]);
	    	cli.setIdEstadoCivil(col[32]);
	    	cli.setFaxCliente(col[33]);
	    	cli.setSiteCliente(col[34]); 
	    	cli.setTimestamp(col[35]);
	    	
	    	cli.setClienteAnonimo(col[36]);
		} 	
		
		return cli;
	}

	private void writeXML(String className, byte[] xml, Integer pag, Response result) { 
		try {
			FileOutputStream file = new FileOutputStream("erro-"+className+"-pag-"+pag+".xml");
			file.write(result.getMsg().getBytes("UTF-8"));
			file.write(xml);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			//log.error("Top Internacional: "+e.getMessage());
		}
	}

	public List<NotaFiscal> getNFPendentesMicrovix(String cpfcnpj, String dtaInicio, String dtaFim) throws IOException, ParseException {
		xml = xmlservicer.getLinxXMLDocumentos(cpfcnpj, Util.getDataXMLMicrovsixSend(dtaInicio), Util.getDataXMLMicrovsixSend(dtaFim));		
		Response xmlnotas = sendXmlWebServiceSaida(xml);
		
		//List<NotaFiscal> notas = getNotasMicrovix(xmlnotas, xmlitens);
		List<NotaFiscal> notas = NotaFiscal.getNotasMicrovix(xmlnotas);
		
		if (notas == null) return null;
		
		for (NotaFiscal nota : notas ) {
			xml = xmlservicer.getXmlConsultaMovimento(cpfcnpj, nota.getIdentificador());
			
			Response xmlitens = sendXmlWebServiceSaida(xml);
			
			nota.setItens(NotaFiscalItem.getItensNotaMicrovix(xmlitens));
		}
		
		return notas;
	}
	
	public List<NotaFiscal> getNotaFiscaisMicrovix(String cpfcnpj, String dtaInicio, String dtaFim) throws IOException, ParseException {
		// Prepara o XML para a consulta
		xml = xmlservicer.getLinxXMLDocumentos(cpfcnpj, Util.getDataXMLMicrovsixSend(dtaInicio), Util.getDataXMLMicrovsixSend(dtaFim));		
		
		// Reliza a chamamda ao Web service, passando o xml de consulta
		Response xmlnotas = sendXmlWebServiceSaida(xml);
		
		// Cria uma lista de objetos nota fiscal a partir do xml de retono
		List<NotaFiscal> notas = NotaFiscal.getNotasMicrovix(xmlnotas);
				
		return notas;
	}
	
	public Response getConsultaMovimento(String cnpj, String id) throws IOException {
		byte[] xmlMovimento = xmlservicer.getXmlConsultaMovimento(cnpj, id);	
		return sendXmlWebServiceSaida(xmlMovimento);
	}

	public Response getConsultaDocumento(String cnpj, String id) throws IOException {
		byte[] xmlDocumento = xmlservicer.getLinxXMLDocumentos(cnpj, id);		
		return sendXmlWebServiceSaida(xmlDocumento);
	}

	public Response getCliente(Integer id) throws IOException {
		byte[] xmlCliente = xmlservicer.getXmlConsultaClientes(id);
		return sendXmlWebServiceSaida(xmlCliente);
	}

	public Response getVendedor(String cnpj, Integer codVendedor) throws IOException {
		byte[] xmlVendedor = xmlservicer.getXmlConsultaVendedor(cnpj, codVendedor);
		return sendXmlWebServiceSaida(xmlVendedor);
	}

	public Response getProduto(String cnpj, Integer codProduto) throws IOException {
		byte[] xmlProduto = xmlservicer.getXmlConsultaProduto(cnpj, codProduto);
		return sendXmlWebServiceSaida(xmlProduto);
	}
} 
