package com.jpii.gamekit.shutdown;

import java.util.ArrayList;

public class ShutdownManager {
	
	private ArrayList<ShutdownRoutine> routines = new ArrayList<ShutdownRoutine>();
	
	/**
	 * Run all <code>ShutdownRoutines</code> that have been registered, then exit the program.
	 * Calls <code>onRoutineRun</code> and <code>onShutdown</code> events.
	 */
	public void runRoutines() {
		for(ShutdownRoutine routine : routines) {
			onRoutineRun(routine);
			routine.run();
		}
		
		shutdown();
	}
	
	/**
	 * Register a <code>ShutdownRoutine</code>. Each routine is called when <code>runRoutines</code>
	 * method is called.
	 * Calls <code>onRoutineRegistered</code> event.</code>
	 * @param shutdownRoutine
	 */
	public void addRoutine(ShutdownRoutine shutdownRoutine) {
		onRoutineRegistered(shutdownRoutine);
		routines.add(shutdownRoutine);
	}
	
	/**
	 * Returns all registered <code>ShutdownRoutines</code>.
	 * @return
	 */
	public ArrayList<ShutdownRoutine> getRoutines() {
		return routines;
	}
	
	/**
	 * Close the program without running <code>ShutdownRoutines</code>.
	 * Calls <code>onShutdown</code> event.
	 */
	public void shutdown() {
		onShutdown();
		System.exit(0);
	}
	
	/**
	 * Event called when <code>shutdown</code> method is called.
	 */
	public void onShutdown() {
		
	}
	
	/**
	 * Event called when <code>runRoutines</code> method is called.
	 */
	public void onRoutineRun(ShutdownRoutine shutdownRoutine) {
		
	}
	
	/**
	 * Event called when <code>registerRoutine</code> method is called.
	 */
	public void onRoutineRegistered(ShutdownRoutine shutdownRoutine) {
		
	}
}