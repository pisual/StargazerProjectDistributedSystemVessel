package com.stargazer.segmentation;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;


public interface LogExecute {
	public Boolean executeLog(Optional<LogData> logData);
}
