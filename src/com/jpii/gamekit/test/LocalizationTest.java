package com.jpii.gamekit.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpii.gamekit.localization.LocalizationManager;

public class LocalizationTest {
	
	@Test
	public void testEnglishLocalization() {
		LocalizationManager localizationManager = new LocalizationManager(LocalizationTest.class, "/com/jpii/gamekit/test/res");
		
		assertEquals("Result", "Hello", localizationManager.getString("test_value"));
		assertEquals("Result", "This string is in English only", localizationManager.getString("en_only"));
		assertEquals("Result", "undefined_string", localizationManager.getString("undefined_string"));
	}
	
	@Test
	public void testForeignLocalization() {
		LocalizationManager localizationManager = new LocalizationManager(LocalizationTest.class, "/com/jpii/gamekit/test/res", "es");
		
		assertEquals("Result", "Hola", localizationManager.getString("test_value"));
		assertEquals("Result", "This string is in English only", localizationManager.getString("en_only"));
		assertEquals("Result", "undefined_string", localizationManager.getString("undefined_string"));
	}
	
	@Test
	public void testBadForeignLocalization() {
		LocalizationManager localizationManager = new LocalizationManager(LocalizationTest.class, "/com/jpii/gamekit/test/res", "fr");
		
		assertEquals("Result", "Hello", localizationManager.getString("test_value"));
		assertEquals("Result", "This string is in English only", localizationManager.getString("en_only"));
		assertEquals("Result", "undefined_string", localizationManager.getString("undefined_string"));
	}
}
