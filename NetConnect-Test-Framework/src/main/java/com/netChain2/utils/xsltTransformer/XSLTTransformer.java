package com.netChain2.utils.xsltTransformer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

public class XSLTTransformer {
	
	public void xsltTransformer(String pathOfTestNGResultXML, String pathOfXSL, String pathToStoreTransformedHTML) {
		/*
		 * get xml document Parsing
		 * 
		 */
		try {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
		Document document = domBuilder.parse(pathOfTestNGResultXML);
		
		
		/*
		 * For Transformation from XML to XSL
		 */
		DOMSource domSource = new DOMSource(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer(new StreamSource(pathOfXSL));
		OutputStream outStream = new FileOutputStream(new File(pathToStoreTransformedHTML));
		StreamResult htmlResult = new StreamResult(outStream);
		transformer.transform(domSource, htmlResult);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
