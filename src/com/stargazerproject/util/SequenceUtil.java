package com.stargazerproject.util;

import com.fasterxml.uuid.Generators;

public class SequenceUtil {
	/** 获取标准UUID **/
	public static String getUUID() {
		return Generators.randomBasedGenerator().generate().toString();
	}

	/** 获取UUID类型的序列 去除 “-” 符号**/
	public static String getUUIDSequence() {
		return Generators.randomBasedGenerator().generate().toString().replaceAll("-", "");
	}
}
