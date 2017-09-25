package com.stargazerproject.inject;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Optional;

public interface ClassSearchMethod {
	
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException;
	
}
