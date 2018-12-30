package com.stargazerproject.service;

import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Table;

public interface ServiceInitialization {
	
	public void initializationFromAnnotationsScan();
	
	public void initializationFromServerMenu(Optional<Map<String, Integer>> serverMenu);
	
	public Optional<Table<Integer, String, Boolean>> serviceMenu();
	
}
