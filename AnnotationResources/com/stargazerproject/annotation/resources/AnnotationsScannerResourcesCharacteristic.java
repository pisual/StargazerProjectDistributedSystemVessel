package com.stargazerproject.annotation.resources;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="annotationsScannerResourcesCharacteristic")
@Qualifier("annotationsScannerResourcesCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AnnotationsScannerResourcesCharacteristic implements AnnotationsScanner, BaseCharacteristic<AnnotationsScanner>{

	/**
	 * {class com.stargazerproject.annotations.server.manage.AnnotationsServerManage 
	 *         [value=[annotationsServerManage], order=[13]]
	 * **/
	private Multimap<Class<?>, Map.Entry<String, List<Object>>> scoreMultimap;
	
	private AnnotationsScannerResourcesCharacteristic() {}
	
	@Override
	public Optional<AnnotationsScanner> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<Multimap<Class<?>, Entry<String, List<Object>>>> scannerAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException {
		scoreMultimap = LinkedHashMultimap.create();
		Resource[] resources = acquireResourceArray(packagesArg.get());
		for(Resource resource : resources){
			matchesEntityTypeFilter(resource, annotationArg.get());
		}
		return Optional.of(scoreMultimap);
	}
	
	@Override
	public Optional<List<String>> acquireAppointAnnotationAttributeValue(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg, Optional<String> value) throws IOException, ClassNotFoundException {
		return Optional.of(attributeValueList(packagesArg, annotationArg, value));
	}
	
	private String packagesPattern(String packagesArg){
		return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(packagesArg) + "/**/*.class";
	}
	
	protected Resource[] acquireResourceArray(String packagesArg) throws IOException{
		return new PathMatchingResourcePatternResolver().getResources(packagesPattern(packagesArg));
	}
	
	private void matchesEntityTypeFilter(Resource resource, Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException{
		TypeFilter typeFilters = new AnnotationTypeFilter(annotation, false);
		MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());
		MetadataReader reader = readerFactory.getMetadataReader(resource);
		if (typeFilters.match(reader, readerFactory)) {
			Class<?> clazz = Class.forName(reader.getClassMetadata().getClassName());
			reader.getAnnotationMetadata().getAllAnnotationAttributes(annotation.getName()).entrySet().stream().forEach(x -> scoreMultimap.put(clazz, x));
			}
	}
	
	private List<String> attributeValueList(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg, Optional<String> value) throws ClassNotFoundException, IOException{
		List<String> parametersList = new ArrayList<String>();
		Map<Class<?>, Collection<Entry<String, List<Object>>>> mapList = scannerAnnotation(packagesArg, annotationArg).get().asMap();
		for(Entry<Class<?>, Collection<Entry<String, List<Object>>>> mapEntry : mapList.entrySet()){
			Optional<String> parametername = Optional.absent();
			for(Entry<String, List<Object>> valueMap : mapEntry.getValue()){
				if(valueMap.getKey().equals(value.get())){
					parametername = Optional.of(valueMap.getValue().get(0).toString());
				}
			}
			parametersList.add(parametername.get());
		}
		return parametersList;
	}

}
