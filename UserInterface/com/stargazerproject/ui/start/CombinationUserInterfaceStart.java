package com.stargazerproject.ui.start;

import java.util.concurrent.TimeUnit;

import com.stargazerproject.ui.assembly.impl.ConsoleTextPane;
import com.stargazerproject.ui.assembly.impl.LoadingFrame;
import com.stargazerproject.ui.assembly.impl.MainFrame;
import com.stargazerproject.ui.assembly.impl.RightConsoleTextPane;

public class CombinationUserInterfaceStart {
	public CombinationUserInterfaceStart() {
		LoadingFrame loadingFrame = new LoadingFrame();
		loadingFrame.visualLoadingFrame();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainFrame mainFrame = new MainFrame();
		mainFrame.VisualMainFrame();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		loadingFrame.unVisualLoadingFrame();
		loadingFrame.disposeLoadingFrame();
		ConsoleTextPane.getInstance().insertMessage("Stargazer System Online Now");
		RightConsoleTextPane.getInstance().insertMessage("Stargazer System Online Now");
	}
}
