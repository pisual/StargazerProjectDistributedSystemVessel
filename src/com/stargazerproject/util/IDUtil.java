package com.stargazerproject.util;

import java.util.UUID;

public class IDUtil {
	public static String ID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
}
