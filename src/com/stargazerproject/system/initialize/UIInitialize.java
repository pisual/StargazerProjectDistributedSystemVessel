package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.system.start.StargazerIntelligenceSystemStart;
import com.stargazerproject.ui.assembly.impl.MainFrameLogoJlabel;
import com.stargazerproject.ui.assembly.impl.RightConsoleTextPane;
import com.stargazerproject.ui.assembly.impl.StructureTopologyJlabel;
import com.stargazerproject.ui.start.CombinationUserInterfaceStart;

public class UIInitialize {
	private static UIInitialize uiInitialize;

	public static final UIInitialize getInstance() {
		if (uiInitialize == null) {
			uiInitialize = new UIInitialize();
		}
		return uiInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(UIInitialize.class, "UIInitialize Initialize");
		
		new CombinationUserInterfaceStart();
		
		LocalityLog.getInstance().INFO(StargazerIntelligenceSystemStart.class,"Stargazer Cells System Has Start");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RightConsoleTextPane.getInstance().setVisible(false);
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainFrameLogoJlabel.getInstance("").setVisible(Boolean.TRUE);
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StructureTopologyJlabel.getInstance("").setVisible(true);
	}

	private UIInitialize() {
	}
}
