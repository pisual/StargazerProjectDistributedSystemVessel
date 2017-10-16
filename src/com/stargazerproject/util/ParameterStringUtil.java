package com.stargazerproject.util;

import com.google.common.base.Optional;

public class ParameterStringUtil {
	
	public ParameterStringUtil() {}

	public static Optional<int[]> parameterTransToNormallArray(Optional<String> parameter, Optional<String> split, Optional<Integer> size){
		String logoLocationString[] = parameter.get().split(split.get());
		int logoLocationInt[] = new int[size.get()];
		for(int i = 0; i<logoLocationString.length; i++){
			logoLocationInt[i] = Integer.parseInt(logoLocationString[i]);
		}
		return Optional.of(logoLocationInt);
	}
}
