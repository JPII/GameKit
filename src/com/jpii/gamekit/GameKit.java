/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.gamekit;

import com.jpii.gamekit.debug.Debugger;
import com.jpii.gamekit.exception.InvalidApiLevelException;
import com.jpii.gamekit.gui.WindowHandler;

public class GameKit {
	
	public static Debugger debug = null;
	public static WindowHandler windows = null;
	
	private static final String VERSION = "1.0-PREVIEW";
	private static final int API_LEVEL = 0;
	
	public static void checkVersion(int apiLevel) throws InvalidApiLevelException {
		if(apiLevel > API_LEVEL) {
			throw new InvalidApiLevelException("Invalid API level: " + apiLevel + " (currently implementing " + API_LEVEL + ")");
		}
	}
	
	public static void checkVersion(int minApiLevel, int maxApiLevel) throws InvalidApiLevelException {
		if(maxApiLevel < API_LEVEL) {
			throw new InvalidApiLevelException("Invalid max API level: " + maxApiLevel + " (currently implementing " + API_LEVEL + ")");
		} else if(minApiLevel > API_LEVEL) {
			throw new InvalidApiLevelException("Invalid min API level: " + maxApiLevel + " (currently implementing " + API_LEVEL + ")");
		}
	}
	
	/**
	 * Get <code>GameKit</code> version.
	 * @return
	 */
	public static String getVersion() {
		return VERSION;
	}
	
	/**
	 * Get <code>GameKit</code> API level.
	 * @return
	 */
	public static int getApiLevel() {
		return API_LEVEL;
	}
	
	/**
	 * Dummy method.
	 * @return
	 */
	public static boolean ping() {
		return true;
	}
	
	/**
	 * Dummy method.
	 * @return
	 */
	public static boolean pong() {
		return true;
	}
}
