/**
 * 
 */
package com.jpii.gamekit.debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.jpii.gamekit.GameKit;


public class HookStream extends PrintStream {

	public HookStream(OutputStream out) {
		super(out);
	}

	public HookStream(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public HookStream(File file) throws FileNotFoundException {
		super(file);
	}

	public HookStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public HookStream(String fileName, String csn)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	public HookStream(File file, String csn) throws FileNotFoundException,
			UnsupportedEncodingException {
		super(file, csn);
	}

	public HookStream(OutputStream out, boolean autoFlush, String encoding)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
	}
	
	public void println(String s) {
		super.println(s);
		
		if (GameKit.debug != null) {
			GameKit.debug.printInfo(s);
		}
	}
}