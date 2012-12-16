package com.jpii.gamekit.localization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
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
	private InputStream defaultStrings;
	private InputStream currentStrings;

	public LocalizationManager(Class reference, String dir) {
		currentLocale = Locale.getDefault();
		langCode = currentLocale.toString().substring(0, 2);

		defaultStrings = reference.getResourceAsStream(dir + "/strings.xml");
		currentStrings = reference.getResourceAsStream(dir + "/strings-" + langCode + ".xml");

		if(langCode.equalsIgnoreCase("en")) {
			currentStrings = defaultStrings;
		}

		Document doc = newDocumentFromInputStream(currentStrings);

		NodeList resources = doc.getElementsByTagName("resources");

		for (int i = 0; i < resources.getLength(); i++) {
			Node firstNode = resources.item(i);

			if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) firstNode;
				NodeList stringElementList = element.getElementsByTagName("string");
				Element stringElement = (Element) stringElementList.item(0);
				NodeList string = stringElement.getChildNodes();
				System.out.println("Value: " + string.item(0).getNodeValue() + " at " + string.item(0));

			}
		}
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

	public InputStream getDefaultStringsUrl() {
		return defaultStrings;
	}

	public InputStream getCurrentStringsUrl() {
		return currentStrings;
	}
}