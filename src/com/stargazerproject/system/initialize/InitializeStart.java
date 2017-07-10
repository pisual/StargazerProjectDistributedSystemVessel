package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.model.util.IDUtil;

/**
 *
 _________ __ __________ __ __ / _____// |______ _______ _________ ________
 * __________\______ \_______ ____ |__| ____ _____/ |_ \_____ \\ __\__ \\_ __ \/
 * ___\__ \ \___ // __ \_ __ \ ___/\_ __ \/ _ \ | |/ __ \_/ ___\ __\ / \| | / __
 * \| | \/ /_/ > __ \_/ /\ ___/| | \/ | | | \( <_> ) | \ ___/\ \___| | /_______
 * /|__| (____ /__| \___ (____ /_____ \\___ >__| |____| |__| \____/\__| |\___
 * >\___ >__| \/ \/ /_____/ \/ \/ \/ \______| \/ \/ StargazerProject 观星者计划
 * https://github.com/pisual Intelligence System
 * 
 **/
public class InitializeStart {
	private static InitializeStart initializeStart;

	public static final InitializeStart getInstance() {
		if (initializeStart == null) {
			initializeStart = new InitializeStart();
		}
		return initializeStart;
	}

	public void initialize() {
		System.out.println("Stargazer System Initialize Start");
		ParameterInitialize.getInstance().initialize();
//		UIInitialize.getInstance().initialize();
		ClusterInitialize.getInstance().initialize();
		SingletonInitialize.getInstance().initialize();
		CacheInitialize.getInstance().initialize();
	    NetworkInitialize.getInstance().initialize();
		DataCenterInitialize.getInstance().initialize();
		CellsInitialize.getInstance();
		LocalityLog.getInstance().INFO(InitializeStart.class,"Stargazer System Initialize End");
	}

	private InitializeStart() {
	}
}
