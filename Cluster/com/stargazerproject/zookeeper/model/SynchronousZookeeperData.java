package com.stargazerproject.zookeeper.model;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;

public class SynchronousZookeeperData {
	/**节点名称**/
	private String nodeName;
	/**节点数据**/
	private byte[] nodeData;
	/**权限控制**/
	private List<ACL> acl;
	/**CreateMode:
		  * 	    PERSISTENT (持续的，相对于EPHEMERAL，不会随着client的断开而消失)
		  *		PERSISTENT_SEQUENTIAL（持久的且带顺序的）
		  *		EPHEMERAL (短暂的，生命周期依赖于client session)
		  *		EPHEMERAL_SEQUENTIAL  (短暂的，带顺序的)**/
	private CreateMode createMode;
	
	public SynchronousZookeeperData(String nodeName, byte[] nodeData, List<ACL> acl, CreateMode createMode) {
		this.nodeName = nodeName;
		this.nodeData = nodeData;
		this.acl = acl;
		this.createMode = createMode;
	}
	
	

	public String getNodeName() {
		return nodeName;
	}

	public byte[] getNodeData() {
		return nodeData;
	}

	public List<ACL> getIds() {
		return acl;
	}

	public CreateMode getCreateMode() {
		return createMode;
	}
	
}
