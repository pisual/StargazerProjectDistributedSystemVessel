package com.stargazerproject.inject.impl;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.inject.PackageSearchMethod;

public class PackageSearchMethodImpl implements PackageSearchMethod {

	private AnnotationScannerImpl annotationScanner = new AnnotationScannerImpl();;
	
	@Override
	public Optional<List<Class<?>>> searchFromPackage(Optional<String> absolutePath) throws IOException, ClassNotFoundException {
		
		return null;
	}

}
