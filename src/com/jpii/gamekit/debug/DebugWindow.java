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

package com.jpii.gamekit.debug;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class DebugWindow extends JFrame {
	private JLabel lblNavalBattle;
	private JLabel lblDebugMode;
	private JTextPane debugPrinter;
	private JTextField commandField;
	private boolean paused = false;
	
	private CommandHandler commandHandler;
	private ArrayList<String> resume;
	
	/**
	 * Constructor for DebugWindow.
	 */
	public DebugWindow() {	
		setSize(465,365);
		getContentPane().setLayout(null);
		
		resume = new ArrayList<String>();
		pause();
		commandHandler = new CommandHandler();
		
		lblNavalBattle = new JLabel("NavalBattle");
		lblDebugMode = new JLabel("Debug Mode");
		JScrollPane scrollPane = new JScrollPane();
		debugPrinter = new JTextPane();
		commandField = new JTextField();
		final JButton btnSubmit = new JButton("Submit");
		
		lblNavalBattle.setBounds(10, 11, 120, 14);
		lblDebugMode.setBounds(95, 13, 120, 14);
		scrollPane.setBounds(10, 35, 439, 255);
		commandField.setBounds(10, 298, 337, 30);
		btnSubmit.setBounds(357, 298, 90, 30);
		
		lblNavalBattle.setFont(new Font("Tahoma", Font.BOLD, 14));
		debugPrinter.setFont(new Font("Consolas",0,12));
		debugPrinter.setEditable(false);
		scrollPane.setViewportView(debugPrinter);
		commandField.setColumns(10);
		btnSubmit.setFocusable(false);
		debugPrinter.setFocusable(false);
		
		getContentPane().add(lblNavalBattle);
		getContentPane().add(lblDebugMode);
		getContentPane().add(scrollPane);
		getContentPane().add(commandField);
		getContentPane().add(btnSubmit);

		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!commandField.getText().isEmpty()) {
					submitCommand();
					commandField.setText("");
				}
			}
		});
		printInfo("Debug mode enabled");
		
		commandField.grabFocus();
		resume();
	}
	
	/**
	 * Returns current instance of CommandHandler.
	 * 
	 * @return commandHandler
	 */
	public CommandHandler getCommandHandler() {
		return commandHandler;
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
		if(!paused)
			debugPrinter.setText(debugPrinter.getText() + message);
		else {
			resume.add(message);
		}
	}
	
	private void clearText(){
		debugPrinter.setText("");
	}

	/**
	 * Parses command.
	 * @param command
	 */
	public void submitCommand(String s) {
		getCommandHandler().parseCommand(s);
	}
	
	public void submitCommand() {
		if(!commandField.getText().isEmpty()) {
			getCommandHandler().parseCommand(commandField.getText());
			commandField.setText("");
		}
	}

	/**
	 * Pause logging of tagged messages.
	 */
	public void pause() {
		this.paused  = true;
	}
	
	/**
	 * Resume logging of tagged messages.
	 */
	public void resume() {
		this.paused = false;
		for(int index = 0; index<resume.size(); index=0){
			print(resume.get(index));
			resume.remove(index);
		}
	}

	/**
	 * Get current JFrame.
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return this;
	}
}