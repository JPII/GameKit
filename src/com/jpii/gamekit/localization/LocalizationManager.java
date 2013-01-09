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
	private boolean badFile = false;
	private InputStream defaultStringsInputStream;
	private InputStream currentStringsInputStream;
	private HashMap<String, String> defaultStrings = new HashMap<String, String>();
	private HashMap<String, String> currentStrings = new HashMap<String, String>();

	/**
	 * <code>LocalizationManager</code> constructor.
	 * @param reference
	 * @param dir
	 */
	public LocalizationManager(Class<?> reference, String dir) {
		currentLocale = Locale.getDefault();
		langCode = currentLocale.toString().substring(0, 2);

		defaultStringsInputStream = reference.getResourceAsStream(dir + "/strings.xml");
		currentStringsInputStream = reference.getResourceAsStream(dir + "/strings-" + langCode + ".xml");

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
		
		if(!langCode.equals("en") && !badFile) {
			Document currentStringDoc;

			try {
				currentStringDoc = newDocumentFromInputStream(currentStringsInputStream);
			} catch (Exception e) {
				badFile = true;
				return;
			}

			Element currentStringRootElement;
			
			try {
				currentStringRootElement = currentStringDoc.getDocumentElement();
			} catch (Exception e) {
				badFile = true;
				return;
			}

			NodeList currentStringNodes = currentStringRootElement.getChildNodes();

			for(int i = 0; i < currentStringNodes.getLength(); i++){
				Node node = currentStringNodes.item(i);

				if(node instanceof Element){
					Element currentStringChild = (Element) node;
					currentStrings.put(currentStringChild.getAttribute("name"), currentStringChild.getChildNodes().item(0).getNodeValue());
				}
			}
		}
	}
	
	/**
	 * <code>LocalizationManager</code> constructor.
	 * Used to override automatic locale.
	 * @param reference
	 * @param dir
	 * @param lang
	 */
	public LocalizationManager(Class<?> reference, String dir, String lang) {
		currentLocale = Locale.getDefault();
		langCode = currentLocale.toString().substring(0, 2);
		
		langCode = lang;

		defaultStringsInputStream = reference.getResourceAsStream(dir + "/strings.xml");
		currentStringsInputStream = reference.getResourceAsStream(dir + "/strings-" + langCode + ".xml");

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
		
		if(!langCode.equals("en")) {
			Document currentStringDoc;

			try {
				currentStringDoc = newDocumentFromInputStream(currentStringsInputStream);
			} catch (Exception e) {
				badFile = true;
				return;
			}
			
			Element currentStringRootElement;
			
			try {
				currentStringRootElement = currentStringDoc.getDocumentElement();
			} catch (Exception e) {
				badFile = true;
				return;
			}
			
			NodeList currentStringNodes = currentStringRootElement.getChildNodes();

			for(int i = 0; i < currentStringNodes.getLength(); i++){
				Node node = currentStringNodes.item(i);

				if(node instanceof Element){
					Element currentStringChild = (Element) node;
					currentStrings.put(currentStringChild.getAttribute("name"), currentStringChild.getChildNodes().item(0).getNodeValue());
				}
			}
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
		if(!langCode.equals("en") && !badFile) {
			if(currentStrings.get(key) != null)
				return currentStrings.get(key);
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

	/**
	 * Return <code>defaultStringsInputStream</code>.
	 * @return
	 */
	public InputStream getDefaultStringsInputStream() {
		return defaultStringsInputStream;
	}

	/**
	 * Return <code>currentStringsInputStream</code>.
	 * @return
	 */
	public InputStream getCurrentStringsInputStream() {
		return currentStringsInputStream;
	}

	/**
	 * Return <code>defaultStrings</code>.
	 * @return
	 */
	public HashMap<String, String> getDefaultStrings() {
		return defaultStrings;
	}

	/**
	 * Return <code>currentStrings</code>.
	 * @return
	 */
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
		} catch (ParserConfigurationException e) { }

		try {
			ret = builder.parse(new InputSource(in));
		} catch (SAXException e) {
		} catch (IOException e) { }
		
		return ret;
	}
}