package com.jpii.gamekit.debug;

import java.util.ArrayList;

public class DefaultCommands {
	/**
	 * Commands loaded on game start
	 */
	@SuppressWarnings("serial")
	public static final ArrayList<Command> COMMANDS = new ArrayList<Command>() {{
	    add(new Command("help", "", "View all commands", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		Debugger.getInstance().println("----------------- GameKit Debug Help -----------------");
	    		for(Command cmd : Debugger.getInstance().getCommandHandler().getCommands()) {
	    			Debugger.getInstance().println(cmd.getCommand() + " " + cmd.getArgs() + " - " + cmd.getDescription());
	    		}
	    	}}
	    ));
	    
	    add(new Command("echo", "<message>", "Print specified message", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		if(args[0] == null) {
	    			Debugger.getInstance().printError("Missing or invalid arg: message");
	    		}
	    		
	    		for(String s : args) {
	    			Debugger.getInstance().print(s + " ");
	    		}
	    		
	    		Debugger.getInstance().println("");
	    	}}
	    ));
	    
	    add(new Command("clear", "", "Clear debug window", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		Debugger.getInstance().printNew("");
	    	}}
	    
	    ));
	    
	    add(new Command("cls", "", "Clear debug window", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		Debugger.getInstance().printNew("");
	    	}}
	    
	    ));
	    
	    add(new Command("sysinfo", "", "Get system info", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		Debugger.getInstance().println("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.version") + ")");
	    		Debugger.getInstance().println("Java Home: " + System.getProperty("java.home"));
	    		Debugger.getInstance().println("Java Version: " + System.getProperty("java.version"));
	    	}}
	    ));
	}};
}
