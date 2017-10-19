package com.stargazerproject.util;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class Sequence {
	/** 获取标准UUID **/
	public static String getUUID() {
		return Generators.randomBasedGenerator().generate().toString();
	}

	/** 获取UUID类型的序列 **/
	public static String getUUIDSequence() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
