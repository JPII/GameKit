package com.jpii.gamekit.debug;

import java.util.ArrayList;

public class Debugger {
	
	private static Debugger instance;
	private DebugWindow debugWindow;
	private boolean loggingPaused;
	
	private ArrayList<String> savedLogs;
	
	public Debugger(String title) {
		instance = this;
		debugWindow = new DebugWindow(title);
		loggingPaused = false;
		savedLogs = new ArrayList<String>();
	}
	
	public void showDebugWindow() {
		debugWindow.setVisible(true);
	}
	
	/**
	 * Prints a message without a tag. Next message does not go to a new line.
	 * @param message
	 */
	public void print(String message) {
		addText(message);
	}

	/**
	 * Prints a message without a tag. Next message goes to a new line.
	 * @param message
	 */
	public void println(String message) {
		addText(message + "\n");
	}

	/**
	 * Prints a message with an [INFO] tag.
	 * @param message
	 */
	public void printInfo(String message) {
		addText("[INFO] " + message + "\n");
	}

	/**
	 * Prints a message with a [WARN] tag.
	 * @param message
	 */
	public void printWarning(String message) {
		addText("[WARN] " + message + "\n");
	}

	/**
	 * Prints a message with an [ERROR] tag.
	 * @param message
	 */
	public void printError(String message) {
		addText("[ERROR] " + message + "\n");
	}

	/**
	 * Prints a message with a [OTHER] tag.
	 * @param message
	 */
	public void printOther(String message) {
		addText("[OTHER] " + message + "\n");
	}

	/**
	 * Clears the DebugWindow and prints message.
	 * @param message
	 */
	public void printNew(String message) {
		clearText();
		addText(message + "\n");
	}
	
	private void addText(String message){
		if(!loggingPaused)
			debugWindow.getDebugPrinter().setText(debugWindow.getDebugPrinter().getText() + message);
		else {
			savedLogs.add(message);
		}
	}
	
	private void clearText(){
		debugWindow.getDebugPrinter().setText("");
	}

	/**
	 * Pause logging of tagged messages.
	 */
	public void pause() {
		loggingPaused  = true;
	}
	
	/**
	 * Resume logging of tagged messages.
	 */
	public void resume() {
		loggingPaused = false;
		
		for(int index = 0; index < savedLogs.size(); index=0){
			print(savedLogs.get(index));
			savedLogs.remove(index);
		}
	}
	
	public DebugWindow getDebugWindow() {
		return debugWindow;
	}
	
	public CommandHandler getCommandHandler() {
		return debugWindow.getCommandHandler();
	}
	
	public static Debugger getInstance() {
		return instance;
	}
}
