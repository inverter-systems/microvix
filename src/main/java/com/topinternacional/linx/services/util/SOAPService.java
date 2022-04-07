package com.topinternacional.linx.services.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.bean.Response;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Submit;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.repo.LogRepository;

@Service
public class SOAPService {
	
	@Autowired
	private XMLServiceGenerico xmlservicer;
	
	@Autowired
	private LogRepository log;
	
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
	
	public Response sendXMLConsulta(byte[] xml) throws IOException {
		 String url = "https://webapi.microvix.com.br/1.0/api/integracao";
		 
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
	
	public void importar(Metodo metodo, ArrayList<Registro> arrayList, Integer pag, Integer totalPages, String className, Long id) {
		Response result = null;
		String envio = "<msg>Mensagen default</msg>";
		try {
			xml = xmlservicer.getXml(metodo, arrayList);
			result = sendXML(xml);
			envio = new String(xml, "UTF-8");
			
			if (result.getStatusEnvio().equals(Submit.PROBLEM)) {
				log.save(new Log(id, "Processamento do lote/página "+(pag+1)+" de "+totalPages+" - Status: "+result.getStatusEnvio().getDescricao(), new String(xml, "UTF-8"), result.getMsg()));
			} else {
				log.save(new Log(id, "Processamento do lote/página "+(pag+1)+" de "+totalPages+" - Status: "+result.getStatusEnvio().getDescricao()));
			}
			
		} catch (IOException e) {
			log.save(new Log(id, "Erro Java:: "+e.getMessage(), envio, result.getMsg()));
			e.printStackTrace();
		}
	}
	
	public Response consultar(Metodo metodo, ArrayList<Registro> arrayList) {
		Response result = null;
		try {			
			xml = xmlservicer.getXmlConsulta(metodo, arrayList, "xml");
			result = sendXMLConsulta(xml);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return result;
	}
		
	public void reprocessar(Metodo metodo, ArrayList<Registro> registros, Integer pag, int size, String string, Long id, Log logger) {
		Response result = null;
		String envio = "<msg>Mensagen default</msg>";
		try {
			xml = xmlservicer.getXml(metodo, registros);
			result = sendXML(xml);
			envio = new String(xml, "UTF-8");
			
			if (result.getStatusEnvio().equals(Submit.PROBLEM)) {
				logger.setErro(result.getMsg());				
				log.save(logger);
			} else {
				logger.setErro(null);
				logger.setXml(null);
				log.save(logger);
			}
			
		} catch (IOException e) {
			logger.setErro(result.getMsg());
			logger.setXml(envio);
			log.save(new Log(id, "Erro Java:: "+e.getMessage()));
			e.printStackTrace();
		}
	}
} 
