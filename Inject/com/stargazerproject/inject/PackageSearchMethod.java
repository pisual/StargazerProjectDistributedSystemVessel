package com.stargazerproject.inject;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Optional;

public interface PackageSearchMethod {
	
	public Optional<List<Class<?>>> searchFromPackage(Optional<String> absolutePath) throws IOException, ClassNotFoundException;
	
}
