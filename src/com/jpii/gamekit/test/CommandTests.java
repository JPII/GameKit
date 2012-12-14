package com.jpii.gamekit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpii.gamekit.debug.Command;
import com.jpii.gamekit.debug.CommandAction;

public class CommandTests {
	@Test
	public void testCommandBase() {
		Command cmd = new Command("test", "", "testCommandBase", new CommandAction());
		assertEquals("Result", "test", cmd.getCommand());
	}
	
	@Test
	public void testCommandDescription() {
		Command cmd = new Command("test", "", "testCommandDescription", new CommandAction());
		assertEquals("Result", "testCommandDescription", cmd.getDescription());
	}
	
	@Test
	public void testCommandAction() {
		CommandAction cmdAction = new CommandAction() {
			public void onRun(Command c, String[] args) {
				System.out.println("testCommandAction");
			}
		};
		
		Command cmd = new Command("test", "", "testCommandAction", cmdAction);
		assertEquals("Result", cmdAction, cmd.getCommandAction());
	}
	
	@Test
	public void testCommandArgs() {
		Command cmd = new Command("test", "<required> [optional]", "testCommandArgs", new CommandAction());
		assertEquals("Result", "<required> [optional]", cmd.getArgs());
	}
	
	public int multiply(int x, int y) {
		return x * y;
	}
}
