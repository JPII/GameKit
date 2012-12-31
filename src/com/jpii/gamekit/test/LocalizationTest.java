package com.jpii.gamekit.test;

import com.jpii.gamekit.localization.LocalizationManager;

public class LocalizationTest {
	public static void main(String[] args) {
		LocalizationManager localizationManager = new LocalizationManager(LocalizationTest.class, "/com/jpii/gamekit/test/res");
		System.out.println(localizationManager.getString("test_value"));
		System.out.println(localizationManager.getString("en_only"));
	}
}
