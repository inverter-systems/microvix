package com.topinternacional.linx.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.dto.Coluna;
import com.topinternacional.linx.dto.Registro;
import com.topinternacional.linx.enun.Metodo;

@Service
public class XMLService {
	
	@Value("${microvix.client.chave}")
	private String chave;
	
	public byte[] getXml(Metodo metodo, ArrayList<Registro> registros) {
		byte[] xml = null;
		
		String txt = getXMLHeader()
				+ "<linx2:Comando>"+metodo+"</linx2:Comando>"				
				+ "<linx2:Registros>";
		
		for (Registro registro : registros) {
			txt +=  "<linx:Registros>"
					+ "<linx:Colunas>";
			
		    for (Coluna coluna: registro.getColunas()) {
		    	txt += "<linx1:CommandParameter>"
					+ "<linx1:Name>"+coluna.getName()+"</linx1:Name>"
					+ "<linx1:Value>"+coluna.getValue()+"</linx1:Value>"
					+ "</linx1:CommandParameter>";					
			}		
		    txt += "</linx:Colunas>"
				   + "</linx:Registros>";
		}		
		txt += "</linx2:Registros>"+getXMLFooter();
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	public byte[] getXmlConsulta(Metodo metodo, ArrayList<Registro> registros, String tipoRetorno) {
		byte[] xml = null;
		String tp_retorno = tipoRetorno != null ? tipoRetorno : "xml";
		try {
			
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
			 + "<LinxMicrovix>"
		     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
		     + "<ResponseFormat>"+tp_retorno+"</ResponseFormat>"
		     + "<IdPortal>14698</IdPortal>"
		     + "<Command>"
			 + "<Name>"+metodo+"</Name>";
			 
			 
			 for (Registro registro : registros) {
				 txt += "<Parameters>";
				 txt += "<Parameter id=\"chave\">"+this.chave+"</Parameter>";
				 
				 for (Coluna coluna: registro.getColunas()) {
					 txt +=  "<Parameter id=\""+coluna.getName()+"\">"+coluna.getValue()+"</Parameter>";
				 } 
				 
				 txt += "</Parameters>";
				 txt += "</Command>";
				 txt += "</LinxMicrovix>";
			}		
		
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	private String getXMLFooter() {
		return 	"</linx:Tabela>"
				+ "<linx:UserAuth>"
				+ "<linx2:Pass>linx_import</linx2:Pass>"
				+ "<linx2:User>linx_import</linx2:User>"
				+ "</linx:UserAuth>"
				+ "</tem:request>"
				+ "</tem:Importar>"
				+ "</soapenv:Body>"
				+ "</soapenv:Envelope>";
	}

	private String getXMLHeader() {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:linx=\"http://schemas.datacontract.org/2004/07/Linx.Microvix.WebApi.Importacao.Requests\" xmlns:linx1=\"http://schemas.datacontract.org/2004/07/Linx.Microvix.WebApi.Business.Api\" xmlns:linx2=\"http://schemas.datacontract.org/2004/07/Linx.Microvix.WebApi.Importacao\">\n"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<tem:Importar>"
				+ "<tem:request>"
				+ "<linx:ParamsSeletorDestino>"
				+ "<!--Zero or more repetitions:-->"
				+ "<linx1:CommandParameter>"
				+ "<linx1:Name>chave</linx1:Name>"
				+ "<linx1:Value>051BECF0-BB15-41A2-A430-8325376F8FA2</linx1:Value>"
				+ "</linx1:CommandParameter>"
				+ "<linx1:CommandParameter>"
				+ "<linx1:Name>cnpjEmp</linx1:Name>"
				+ "<linx1:Value>04387155000264</linx1:Value>"
				+ "</linx1:CommandParameter>"
				+ "<linx1:CommandParameter>"
				+ "<linx1:Name>IdPortal</linx1:Name>"
				+ "<linx1:Value>14698</linx1:Value>"
				+ "</linx1:CommandParameter>"
				+ "</linx:ParamsSeletorDestino>"
				+ "<linx:Tabela>";
	}

	public byte[] getXmlConsultaClientes(Integer id) {
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxClientesFornec</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">04387155000264</Parameter>"
				 + "<Parameter id=\"data_inicial\">NULL</Parameter>"
				 + "<Parameter id=\"data_fim\">NULL</Parameter>"
				 + "<Parameter id=\"cod_cliente\">"+id+"</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	public byte[] getXmlConsultaMovimento(String cpfCnpj, String identificador) {
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxMovimento</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">"+cpfCnpj+"</Parameter>"
				 + "<Parameter id=\"data_inicial\">2021-01-01</Parameter>"
				 + "<Parameter id=\"data_fim\">2221-11-29</Parameter>"
				 + "<Parameter id=\"identificador\">"+identificador+"</Parameter>"
				 + "<Parameter id=\"timestamp\">0</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	// Metodo LinxXMLDocumentos
	public byte[] getLinxXMLDocumentos(String cpfCnpj, String dtaInicio, String dtaFim) { 
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxXMLDocumentos</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">"+cpfCnpj+"</Parameter>"
				 + "<Parameter id=\"data_inicial\">"+dtaInicio+"</Parameter>"
				 + "<Parameter id=\"data_fim\">"+dtaFim+"</Parameter>"
				 + "<Parameter id=\"timestamp\">0</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	// Metodo LinxXMLDocumentos
	public byte[] getLinxXMLDocumentos(String cnpj, String identificador) {
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxXMLDocumentos</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">"+cnpj+"</Parameter>"
				 + "<Parameter id=\"identificador\">"+identificador+"</Parameter>"
				 + "<Parameter id=\"timestamp\">0</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	public byte[] getXmlConsultaVendedor(String cnpj, Integer codVendedor) {
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxVendedores</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">"+cnpj+"</Parameter>"
				 + "<Parameter id=\"cod_vendedor\">"+codVendedor+"</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	public byte[] getXmlConsultaProduto(String cnpj, Integer codProduto) {
		byte[] xml = null;
		
		String txt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
				 + "<LinxMicrovix>"
			     + "<Authentication user=\"linx_export\" password=\"linx_export\" />"
			     + "<ResponseFormat>xml</ResponseFormat>"
			     + "<IdPortal>14698</IdPortal>"
			     + "<Command>"
				 + "<Name>LinxProdutos</Name>"
				 + "<Parameters>"
				 + "<Parameter id=\"chave\">"+this.chave+"</Parameter>"
				 + "<Parameter id=\"cnpjEmp\">"+cnpj+"</Parameter>"
				 + "<Parameter id=\"dt_update_inicio\">2021-01-01</Parameter>"
				 + "<Parameter id=\"dt_update_fim\">2221-11-29</Parameter>"
				 + "<Parameter id=\"cod_produto\">"+codProduto+"</Parameter>"
				 + "</Parameters>"
			     + "</Command>"
		         + "</LinxMicrovix>";
		
		try {
			xml = txt.replaceAll("&","").replaceAll("'","").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xml;
	}
}	