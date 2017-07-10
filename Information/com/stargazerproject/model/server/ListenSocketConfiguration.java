package com.stargazerproject.model.server;

public class ListenSocketConfiguration {
	private Integer port;
	private String host;
	private boolean SSL;
	private Integer parentGroupNumber;
	private Integer childGroupNumber;

	public ListenSocketConfiguration(Integer port, boolean SSL) {
		this.port = port;
		this.SSL = SSL;
	}
	
	public ListenSocketConfiguration(Integer port, String host, boolean SSL, Integer childGroupNumber) {
		this.port = port;
		this.host = host;
		this.SSL = SSL;
		this.childGroupNumber = childGroupNumber;
		this.parentGroupNumber = 1;
	}
	
	public ListenSocketConfiguration(Integer port, String host, boolean SSL, Integer parentGroupNumber, Integer childGroupNumber) {
		this.port = port;
		this.host = host;
		this.SSL = SSL;
		this.childGroupNumber = childGroupNumber;
		this.parentGroupNumber = parentGroupNumber;
	}
	
	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public boolean isSSL() {
		return SSL;
	}

	public Integer getParentGroupNumber() {
		return parentGroupNumber;
	}

	public Integer getChildGroupNumber() {
		return childGroupNumber;
	}
	
}