package com.stargazerproject.model.util;

import java.util.UUID;

public class Sequence {
	/** 获取标准UUID **/
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/** 获取UUID类型的序列 **/
	public static String getUUIDSequence() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
