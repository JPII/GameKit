package com.jpii.gamekit.localization;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LocalizationManager {

	private Locale currentLocale;
	private String[] files;

	public LocalizationManager(Class reference) {
		CodeSource src = reference.getProtectionDomain().getCodeSource();
		List<String> list = new ArrayList<String>();
		
		try {
			if(src != null) {
				URL jar = src.getLocation();
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				ZipEntry ze = null;
	
				while((ze = zip.getNextEntry()) != null ) {
					String entryName = ze.getName();
					if(entryName.startsWith("images") && entryName.endsWith(".png") ) {
						list.add(entryName);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		files = list.toArray(new String[list.size()]);
		
		System.out.println(files[0]);
		currentLocale = Locale.getDefault();
	}

	/**
	 * Get current <code>Locale</code> in use by the system.
	 * @return
	 */
	public Locale getLocale() {
		return currentLocale;
	}
}