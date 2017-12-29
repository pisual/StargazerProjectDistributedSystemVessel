package com.stargazerproject.model.server;

import java.io.Serializable;

public class NetworkConnectionMessage implements Serializable {
	private static final long serialVersionUID = 7500720232777856295L;
	
	private NetworkConnectionMessageType networkConnectionMessageType;
	private NetworkConnectionMessageID networkConnectionMessageID;
	
	public NetworkConnectionMessage(NetworkConnectionMessageType networkConnectionMessageType) {
		this.networkConnectionMessageType = networkConnectionMessageType;
		this.networkConnectionMessageID = new NetworkConnectionMessageID();
	}
	
	public NetworkConnectionMessage(NetworkConnectionMessageType networkConnectionMessageType, NetworkConnectionMessageID networkConnectionMessageID) {
		this.networkConnectionMessageType = networkConnectionMessageType;
		this.networkConnectionMessageID = networkConnectionMessageID;
	}
	
	public NetworkConnectionMessageType getNetworkConnectionMessageType() {
		return networkConnectionMessageType;
	}

	public void setNetworkConnectionMessageType(NetworkConnectionMessageType networkConnectionMessageType) {
		this.networkConnectionMessageType = networkConnectionMessageType;
	}

	/**获取ID序列**/
	public String IDSequence(){
		return networkConnectionMessageID.id;
	}
}
