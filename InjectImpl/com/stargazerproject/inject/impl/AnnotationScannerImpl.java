package com.stargazerproject.inject.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.inject.AnnotationScanner;

@Component(value="annotationScannerImpl")
@Qualifier("annotationScannerImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnnotationScannerImpl implements AnnotationScanner{

	private TypeFilter typeFilters;
	private Multimap<Class<?>, Map.Entry<String, List<Object>>> scoreMultimap = LinkedHashMultimap.create();
	private Class<? extends Annotation> annotation;

	public AnnotationScannerImpl() {}

	@Override
	public Multimap<Class<?>, Map.Entry<String, List<Object>>> getClassAnnotationContent(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException {
		annotation = annotationArg.get();
		typeFilterInitialization(annotationArg.get());
		Resource[] resource = acquireResourceArray(packagesArg.get()); 
		resourceTraversal(resource);
		return scoreMultimap;
	}
	
	private String packagesPattern(String packagesArg){
		return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(packagesArg) + "/**/*.class";
	}
	
	private void typeFilterInitialization(Class<? extends Annotation> annotationArg){
		typeFilters = new AnnotationTypeFilter(annotationArg, false);
	}
	
	private Resource[] acquireResourceArray(String packagesArg) throws IOException{
		return new PathMatchingResourcePatternResolver().getResources(packagesPattern(packagesArg));
	}
	
	private void resourceTraversal(Resource[] resources) throws ClassNotFoundException, IOException{
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				resourceMatching(resource);
			}
		}
	}
	
	private void resourceMatching(Resource resource) throws IOException, ClassNotFoundException{
		MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());
		MetadataReader reader = readerFactory.getMetadataReader(resource);
		matchesEntityTypeFilter(reader, readerFactory);
	}

	
	private void matchesEntityTypeFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws ClassNotFoundException, IOException {
		if (typeFilters.match(reader, readerFactory)) {
			Class<?> clazz = Class.forName(reader.getClassMetadata().getClassName());
			reader.getAnnotationMetadata().getAllAnnotationAttributes(annotation.getName()).entrySet().stream().forEach(x -> scoreMultimap.put(clazz, x));
			}
	}
	
}