package com.jpii.gamekit.gui;

import java.util.ArrayList;
import com.jpii.gamekit.*;

public class WindowHandler {

	public int defaultx,defaulty;
	protected ArrayList<BaseWindow> containedWindows;
	protected ArrayList<BaseWindow> whiteList;
	
	public WindowHandler(int defaultx, int defaulty){
		containedWindows = new ArrayList<BaseWindow>();
		whiteList = new ArrayList<BaseWindow>();
		GlobalVariables.windows = this;
		this.defaultx=defaultx;
		this.defaulty=defaulty;
	}
	
	public void registerWindow(BaseWindow b){
		containedWindows.add(b);
		if(containedWindows.size()==1){
			containedWindows.get(0).setVisible(true);
		}
	}
	
	public void registerWhiteList(BaseWindow b){
		registerWindow(b);
		whiteList.add(b);
	}
	
	public void setNewWindow(BaseWindow next) {
		for(int index = 0; index<containedWindows.size(); index++){		
			BaseWindow temp = containedWindows.get(index);
			if(temp.name.equals(next.name)){
				temp.setVisible(true);
			}
			else{
				temp.setVisible(false);
			}
		}
	}
	
	public void disposeContained(){
		for(int index=0; index<containedWindows.size(); index++){
			boolean remove = true;
			BaseWindow temp = containedWindows.get(index);
			for(int count=0; count<whiteList.size(); count++){
				BaseWindow white = whiteList.get(count);
				if(temp.name.equals(white.name)){
					remove = false;
					break;
				}
			}
			if(remove){
				containedWindows.get(index).donewithMe();
				containedWindows.remove(index);
				index--;
			}
		}
	}
	
	public void disposeAll(){
		disposeContained();
		for(int index=0; index<whiteList.size(); index+=0){
			whiteList.get(index).donewithMe();
			whiteList.remove(index);
		}
	}
}
