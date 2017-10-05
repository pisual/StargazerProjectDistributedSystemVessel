package com.stargazerproject.userinterface;

import com.google.common.base.Optional;

public interface MainUserInterface {
	
	public void startMain();
	public void endMain();
	
	public void visualLeftTerminal();
	public void unvisualLeftTerminal();
	
	public void addLeftTerminalText(Optional<String> content);

}
