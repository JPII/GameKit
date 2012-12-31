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

		// DEFAULT STRINGS
		Document defaultStringDoc = newDocumentFromInputStream(defaultStringsInputStream);

		Element defaultStringRootElement = defaultStringDoc.getDocumentElement();
		NodeList defaultStringNodes = defaultStringRootElement.getChildNodes();

		for(int i = 0; i < defaultStringNodes.getLength(); i++){
			Node defaultStringNode = defaultStringNodes.item(i);

			if(defaultStringNode instanceof Element){
				Element defaultStringChild = (Element) defaultStringNode;
				defaultStrings.put(defaultStringChild.getAttribute("name"), defaultStringChild.getChildNodes().item(0).getNodeValue());
			}
		}
		
		System.out.println("Imported " + defaultStrings.size() + " default Strings");
		
		// CURRENT STRINGS
		if(!langCode.equals("en")) {
			Document currentStringDoc = newDocumentFromInputStream(currentStringsInputStream);

			Element currentStringRootElement = currentStringDoc.getDocumentElement();
			NodeList currentStringNodes = currentStringRootElement.getChildNodes();

			for(int i = 0; i < currentStringNodes.getLength(); i++){
				Node node = currentStringNodes.item(i);

				if(node instanceof Element){
					Element currentStringChild = (Element) node;
					currentStrings.put(currentStringChild.getAttribute("name"), currentStringChild.getChildNodes().item(0).getNodeValue());
				}
			}
			
			System.out.println("Imported " + currentStrings.size() + " current Strings");
		} else {
			System.out.println("Current locale is en, so ignoring current strings");
		}
	}

	/**
	 * Searches through <code>currentStrings</code> and defaults to 
	 * <code>defaultStrings</code> if the String could not be found. 
	 * If the search fails completely, the key is returned. 
	 * Search is case sensitive.
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if(!langCode.equals("en")) {
			if(currentStrings.get(key) != null)
				return currentStrings.get(key);
		} else {
			System.out.println("Skipping currentStrings");
		}
		
		for(int i = 0; i < defaultStrings.size(); i++) {
			if(defaultStrings.get(key) != null)
				return defaultStrings.get(key);
		}
		
		return key;
	}

	/**
	 * Get current <code>Locale</code> in use by the system.
	 * @return
	 */
	public Locale getLocale() {
		return currentLocale;
	}

	/**
	 * Get current language code. The language code is a trimmed
	 * version of the <code>Locale</code>.
	 * @return
	 */
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
	
	/**
	 * Creates a <code>Document</code> from an <code>InputStream</code>.
	 * @param in
	 * @return
	 */
	public Document newDocumentFromInputStream(InputStream in) {
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
}