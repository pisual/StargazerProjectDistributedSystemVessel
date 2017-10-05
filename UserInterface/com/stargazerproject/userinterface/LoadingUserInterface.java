package com.stargazerproject.userinterface;

import com.google.common.base.Optional;

public interface LoadingUserInterface {
	public void startLoading();
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent);
	public void endLoading();
}
