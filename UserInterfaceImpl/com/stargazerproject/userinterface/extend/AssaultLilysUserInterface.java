package com.stargazerproject.userinterface.extend;

import com.google.common.base.Optional;
import com.stargazerproject.userinterface.UserInterface;

public interface AssaultLilysUserInterface extends UserInterface{
	
	public void visualRightTerminal();
	public void unvisualRightTerminal();
	
	public void addRightTerminalText(Optional<String> content);

}
