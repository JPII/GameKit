package com.jpii.gamekit.shutdown;

import java.util.ArrayList;

public class ShutdownManager {
	
	ArrayList<ShutdownRoutine> routines = new ArrayList<ShutdownRoutine>();
	
	public void runRoutines() {
		for(ShutdownRoutine routine : routines) {
			onRoutineRun(routine);
			routine.run();
		}
		
		shutdown();
	}
	
	public void addRoutine(ShutdownRoutine shutdownRoutine) {
		
	}
	
	public ArrayList<ShutdownRoutine> getRoutines() {
		return routines;
	}
	
	public void shutdown() {
		onShutdown();
		System.exit(0);
	}
	
	public void onShutdown() {
		
	}
	
	public void onRoutineRun(ShutdownRoutine shutdownRoutine) {
		
	}
}