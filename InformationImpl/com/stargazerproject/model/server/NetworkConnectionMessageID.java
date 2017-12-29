package com.stargazerproject.model.server;

import java.io.Serializable;

import com.stargazerproject.util.SequenceUtil;

public class NetworkConnectionMessageID implements Serializable {
	private static final long serialVersionUID = -5439866570231398413L;
	
	protected String name;
	protected String id;

	public NetworkConnectionMessageID() {
		this.id = SequenceUtil.getUUIDSequence();
	}
	
	public NetworkConnectionMessageID(String id) {
		this.id = id;
	}
}
