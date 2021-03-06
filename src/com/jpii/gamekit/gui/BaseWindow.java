package com.jpii.gamekit.gui;

import javax.swing.*;

import com.jpii.gamekit.GameKit;

@SuppressWarnings("serial")
public class BaseWindow extends JFrame {
	
	/**
	 * Constructor for Window. Superclass for all GUI windows that
	 * handles size, icon, etc. To redefine elements, use custom constructor.
	 * 
	 * Will log opening automatically, but closing (disposing) should be
	 * handled within each subclass.
	 */
	protected int width;
	protected int height;
	protected int xloc;
	protected int yloc;
	protected WindowHandler myHandler;
	public String name = getClass().getName().toString().substring((getClass().getName().toString().lastIndexOf(".")+1));
	
	/**
	 * Default <code>Window</code> constructor.
	 * Suggested Window Size
	 */
	public BaseWindow() {
		myHandler = GameKit.windows;
		setSize(myHandler.defaultx,myHandler.defaulty);
		setDefaults();
	}
	
	/**
	 * <code>Window</code> constructor which creates a <code>Window</code> with a specific size.
	 * @param x		width
	 * @param y		height
	 */
	public BaseWindow(int x, int y) {
		myHandler = GameKit.windows;
		setDefaults();
		setSize(x,y);
	}
	
	/**
	 * <code>Window</code> constructor which creates a <code>Window</code> at a specific location and size.
	 * @param x			width
	 * @param y			height
	 * @param xloc		x-coordinate
	 * @param yloc		y-coordinate
	 */
	public BaseWindow(int x, int y,int xloc,int yloc) {
		myHandler = GameKit.windows;
		setDefaults();
		setSize(x,y);
		setLocation(xloc,yloc);
	}
	
	/**
	 * Sets the size of the <code>Window</code>
	 * @param width		width
	 * @param height	height
	 */
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
		super.setSize(width, height);
		super.setLocation(xloc,yloc);
	}
	
	/**
	 * Sets the location of the <code>Window</code>
	 * @param x		x
	 * @param y		y
	 */
	public void setLocation(int x, int y){
		xloc = x;
		yloc = y;
		super.setLocation(xloc,yloc);
	}
	
	/**
	 * Set defaults for all <code>Windows</code>.
	 */
	protected void setDefaults(){
		setSize(width, height);
		setLocation(xloc,yloc);
		setResizable(false);
		setFocusable(true);
		setVisible(false);
		myHandler.registerWindow(this);
	}
	
	/**
	 * Set window to visible and log the event.
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible())
			setLocation(xloc,yloc);
	}
	
	/**
	 * Show next <code>Window</code> based on string supplied.
	 * @param name		Name of <code>Window</code> to open. Do not include <code>.java</code>.
	 */
	public void nextWindow(String name){
		GameKit.windows.setNewWindow(name);
	}
	
	/**
	 * Dispose of <code>Window</code>.
	 */
	public void donewithMe(){
		super.dispose();
	}
	
	/**
	 * Get method for <code>Window</code>.
	 * @return Window
	 */
	public JFrame getFrame() {
		return this;
	}
}
