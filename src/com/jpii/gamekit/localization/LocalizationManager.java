package com.jpii.gamekit.localization;

import java.net.URL;
import java.util.Locale;

public class LocalizationManager {

	private Locale currentLocale;
	private String langCode;
	private URL defaultStrings;
	private URL currentStrings;

	public LocalizationManager(Class reference, String dir) {
		currentLocale = Locale.getDefault();
		langCode = currentLocale.toString().substring(0, 2);
		
		defaultStrings = reference.getResource(dir + "/strings.xml");
		currentStrings = reference.getResource(dir + "/strings-" + langCode + ".xml");
		
		if(langCode == "en") {
			defaultStrings = currentStrings;
		}
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
	
	public URL getDefaultStringsUrl() {
		return defaultStrings;
	}
	
	public URL getCurrentStringsUrl() {
		return currentStrings;
	}
}