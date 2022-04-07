package com.topinternacional.linx.services.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.topinternacional.linx.bean.Response;

@Service
public final class Util {
	
	public Util() {}

	public static Date getDataMaisUmDia(Date dataInicioExecucao) {
		if (dataInicioExecucao == null) return null;
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dataInicioExecucao);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		return cal.getTime();
	}

	public static Date getData(String data) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(data);
	}
	
	public static String getData() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatador.format(data);
	}
	
	
	public static Date getDataTimeXML(String data) {
		data = data.replace("T", " ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static Date getDataXMLMicrovix(String data) {
		data = data.replace("T", " ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static Date getDateTimeXMLMicrovix(String data) {
		data = data.replace("T", " ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static String getDataXMLMicrovsixSend(String data) throws ParseException {
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);
		
		return ano+"-"+mes+"-"+dia;
	}
	
	public static boolean isNullOrBlank(String param) { 
	    return param == null || param.trim().length() == 0 || param.equalsIgnoreCase("none") || param.equalsIgnoreCase("SN") ;
	}

	public static Integer codRegiao(String uf) {
		String sul = "PR,RS,SC";
		String norte = "AC,AP,AM,PA,RO,RR,TO";
		String centroOeste = "DF,GO,MT,MS";
		String nordeste = "AL,BA,CE,MA,PB,PE,PI,RN,SE"; 
		String sudeste = "ES,MG,RJ,SP";

		if (sul.indexOf(uf) >= 0 ) {
			return 1;
		} else if (sudeste.indexOf(uf) >= 0 ) {
			return 2;
		} else if (centroOeste.indexOf(uf) >= 0 ) {
			return 3;
		} else if (nordeste.indexOf(uf) >= 0 ) {
			return 4;
		} else if (norte.indexOf(uf) >= 0 ) {
			return 5;
		}
		
		return null;
	}
	
	public static List<String> getRegistrosXML(Response res) {
		String xml = res.getMsg().substring(1);		
		
		Integer ini = xml.indexOf("<R>")+3;
		Integer fim = xml.lastIndexOf("</R>")-2;
		
		if (ini == 2 && fim == -3) return null; 
		
		String linhas = xml.substring(ini, fim).replace("</R>", "");
		
		return Arrays.asList(linhas.split("<R>"));
	}
	
	public static String[] getColunasXML(String row) {
		return (row.replace("<D />", "<D></D>")).replace("</D>", "").replace("</D", "").split("<D>");
	}
	
	public static Boolean isValid(String str) {
		if (str != null && !str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static String getXmlDaTag(String xml, String tag) {
		
		xml = HtmlUtils.htmlUnescape(xml);
		
		Integer tam = tag.length()+3;
		
		Integer inicio = xml.indexOf(("<"+tag));
		Integer fim = xml.indexOf(("</"+tag)) + tam;
		
		String ret = xml.substring(inicio, fim);
		
		return HtmlUtils.htmlUnescape(ret);
	}
	
	public static String prettyFormat(String input, int indent) {
		if (input == null) return null;
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "" + indent);
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}
	
	public static String getSetor(String codigo) {
		String cod = codigo.length() >= 2 ? codigo.substring(0, 2) : "";
		
		switch (cod) {
			case "20": 
				return "Perfumaria";
			case "21": 
				return "Casa e Decoração";
			case "22": 
				return "Bebidas";
			case "23": 
				return "Cosméticos";
			case "27": 
				return "Eletrônicos";
			case "30": 
				return "Acessórios";
			default:
				return "SN";
		}
	}

	public static String getValorBR(String precoCusto) {
		DecimalFormat doisDigitos = new DecimalFormat("###,##0.00");
		return doisDigitos.format(Double.parseDouble(precoCusto));
	}

	public static String getPercent(Double val) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		df.format(val); 
		return df.format(val);
	}

}
