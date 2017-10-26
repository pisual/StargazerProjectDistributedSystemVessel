package com.stargazerproject.inject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import com.google.common.base.Optional;

public interface AnnotationClassSequenceScanner {

	public List<String> sequenceClassName (Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException;
}
