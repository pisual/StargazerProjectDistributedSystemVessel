package com.stargazerproject.service;

import java.util.List;

import com.google.common.base.Optional;

public interface ServerInitialization {
	
	public Optional<List<String>> initializationFromSequenceFile(Optional<String> filePath);
	
	public Optional<List<String>> initializationFromAnnotationsScan();
	
}
