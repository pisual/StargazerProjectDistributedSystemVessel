package com.stargazerproject.system.start;

import java.io.File;
import java.net.MalformedURLException;

import com.stargazerproject.classLoader.LoadingJarFile;
import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.system.initialize.InitializeStart;
import com.stargazerproject.ui.assembly.impl.MainFrameLogoJlabel;
import com.stargazerproject.ui.assembly.impl.RightConsoleTextPane;
import com.stargazerproject.ui.assembly.impl.StructureTopologyJlabel;
import com.stargazerproject.ui.start.CombinationUserInterfaceStart;

/**
*
 _________ __                                               __________                   __               __   
/   _____//  |______ _______  _________  ________ __________\______   \_______  ____    |__| ____   _____/  |_ 
\_____  \\   __\__  \\_  __ \/ ___\__  \ \___   // __ \_  __ \     ___/\_  __ \/  _ \   |  |/ __ \_/ ___\   __\
/        \|  |  / __ \|  | \/ /_/  > __ \_/    /\  ___/|  | \/    |     |  | \(  <_> )  |  \  ___/\  \___|  |  
/_______  /|__| (____  /__|  \___  (____  /_____ \\___  >__|  |____|     |__|   \____/\__|  |\___  >\___  >__|  
       \/           \/     /_____/     \/      \/    \/                             \______|    \/     \/      
*                                             StargazerProject 观星者计划
*                                             https://github.com/pisual
*                                                Intelligence System
* 
**/
public class StargazerIntelligenceSystemStart {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(1000);
		InitializeStart.getInstance().initialize();
	}
}
