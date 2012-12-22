package com.jpii.gamekit.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LocalizationManager {

	private Locale currentLocale;
	private String langCode;
	private InputStream defaultStringsInputStream;
	private InputStream currentStringsInputStream;
	private HashMap<String, String> defaultStrings = new HashMap<String, String>();
	private HashMap<String, String> currentStrings = new HashMap<String, String>();

	public LocalizationManager(Class<?> reference, String dir) {
		currentLocale = Locale.getDefault();
		langCode = currentLocale.toString().substring(0, 2);

		defaultStringsInputStream = reference.getResourceAsStream(dir + "/strings.xml");
		currentStringsInputStream = reference.getResourceAsStream(dir + "/strings-" + langCode + ".xml");

		if(langCode.equalsIgnoreCase("en")) {
			currentStringsInputStream = defaultStringsInputStream;
		}

		Document doc = newDocumentFromInputStream(defaultStringsInputStream);

		Element rootElement = doc.getDocumentElement();
		NodeList nodes = rootElement.getChildNodes();

		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);

			if(node instanceof Element){
				Element child = (Element) node;
				String attribute = child.getAttribute("name");
				System.out.println(attribute);
			}
		}

		//importCurrentStrings(newDocumentFromInputStream(currentStringsInputStream));
		importDefaultStrings(newDocumentFromInputStream(defaultStringsInputStream));
	}

	public static Document newDocumentFromInputStream(InputStream in) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document ret = null;

		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {
			ret = builder.parse(new InputSource(in));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String getString(String key) {
		return "";
	}

	/**
	 * Get current <code>Locale</code> in use by the system.
	 * @return
	 */
	public Locale getLocale() {
		return currentLocale;
	}

	public String getLanguageCode() {
		return langCode;
	}

	public InputStream getDefaultStringsInputStream() {
		return defaultStringsInputStream;
	}

	public InputStream getCurrentStringsInputStream() {
		return currentStringsInputStream;
	}

	public HashMap<String, String> getDefaultStrings() {
		return defaultStrings;
	}

	public HashMap<String, String> getCurrentStrings() {
		return currentStrings;
	}

	private void importCurrentStrings(Document doc) {
		Element rootElement = doc.getDocumentElement();
		NodeList nodes = rootElement.getChildNodes();

		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);

			if(node instanceof Element){
				Element child = (Element) node;
				String attribute = child.getAttribute("name");
				System.out.println(attribute);
			}
		}
	}

	private void importDefaultStrings(Document doc) {
		Element rootElement = doc.getDocumentElement();
		NodeList nodes = rootElement.getChildNodes();

		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);

			if(node instanceof Element){
				Element child = (Element) node;
				String attribute = child.getAttribute("name");
				System.out.println(attribute);
			}
		}
	}
}