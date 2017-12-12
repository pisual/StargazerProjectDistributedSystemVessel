package com.stargazerproject.inject.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.inject.AnnotationClassSequenceScanner;
import com.stargazerproject.service.baseinterface.Services;

@Component(value="annotationClassSequenceScannerImpl")
@Qualifier("annotationClassSequenceScannerImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnnotationClassSequenceScannerImpl extends AnnotationScannerImpl implements AnnotationClassSequenceScanner{
	private Multimap<Integer, String> specialAnnotationAttributes = LinkedHashMultimap.create();
	public AnnotationClassSequenceScannerImpl() {}

	@Override
	public List<String> sequenceClassName(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException{
		typeFilterInitialization(annotationArg.get());
		Resource[] resources = acquireResourceArray(packagesArg.get());
		for(Resource resource : resources){
			matchesEntityTypeFilterSpecialAttributes(resource);
		}
		List<String> AttributesValueSortedList = new ArrayList<String>();
		List<Integer> orderList = specialAnnotationAttributes.keySet().stream().sorted().collect(Collectors.toList());
		orderList.forEach(x -> specialAnnotationAttributes.get(x).forEach(z -> AttributesValueSortedList.add(z)));
		return AttributesValueSortedList;
	}
	
	private void matchesEntityTypeFilterSpecialAttributes(Resource resource) throws ClassNotFoundException, IOException {
		MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());
		MetadataReader reader = readerFactory.getMetadataReader(resource);
		if (typeFilters.match(reader, readerFactory)) {
			String value = reader.getAnnotationMetadata().getAllAnnotationAttributes(annotation.getName()).entrySet().stream().filter(x -> x.getKey().toString().equals("value")).findFirst().get().getValue().get(0).toString();
			String name = reader.getAnnotationMetadata().getAllAnnotationAttributes(annotation.getName()).entrySet().stream().filter(x -> x.getKey().toString().equals("order")).findFirst().get().getValue().get(0).toString();
			specialAnnotationAttributes.put(Integer.parseInt(name), value);
		}
	}
}