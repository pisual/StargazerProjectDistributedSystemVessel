package com.stargazerproject.userinterface.extend;

import com.google.common.base.Optional;
import com.stargazerproject.userinterface.MainUserInterface;

public interface MainFrameAssaultLilysUserInterface extends MainUserInterface{
	
	public void visualRightTerminal();
	public void unvisualRightTerminal();
	
	public void addRightTerminalText(Optional<String> content);

}
