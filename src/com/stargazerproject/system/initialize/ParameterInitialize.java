package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.model.util.IDUtil;
import com.stargazerproject.parameter.impl.I18NParameter;
import com.stargazerproject.parameter.impl.SystemParameter;

public class ParameterInitialize {
	private static ParameterInitialize parameterInitialize;

	public static final ParameterInitialize getInstance() {
		if (parameterInitialize == null) {
			parameterInitialize = new ParameterInitialize();
		}
		return parameterInitialize;
	}

	public void initialize() {
		SystemParameterCahce.getInstance();
		I18NParameter.getInstance();
		LocalityLog.getInstance().INFO(SystemParameterCahce.class,"System Parameter Initialization");
		LocalityLog.getInstance().INFO(I18NParameter.class,"I18N Parameter Initialization");
		
		/**系统ID初始化**/
		SystemParameterCahce.getInstance().set("CellsID", IDUtil.ID());
	}

	private ParameterInitialize() {
	}
}
