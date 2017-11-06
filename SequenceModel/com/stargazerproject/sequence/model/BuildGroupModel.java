package com.stargazerproject.sequence.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.util.Sequence;

@Component(value="buildGroupModel")
@Qualifier("buildGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BuildGroupModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public BuildGroupModel() {
		super();
		}
	
	@Override
	public Boolean method() {
		try {
			nodeNegotiate.creatPersistentNode(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/Master_Cells/List/"), Optional.absent());
				System.out.println("开始匹配");
				TimeUnit.SECONDS.sleep(randomInt(1,120));
				if(nodeNegotiate.creatLock(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/Master_Cells/List/"))){
					while(true){
					TimeUnit.SECONDS.sleep(randomInt(1,10));
					List<String> pathNodeList = nodeNegotiate.getPathNode(Optional.of(""), Optional.of("/Master_Cells/List"));
					if(pathNodeList.size() < 2){
						System.out.println("节点数目不足");
						continue;
					}
					String origin = Sequence.getUUID();	
					for(String pathNode : pathNodeList){
						if(matchingAlgorithm(origin, pathNode, 2).equals(Boolean.TRUE)){
							System.out.println("Find Mathcing Node : " + pathNode);
							if(nodeNegotiate.creatLock(Optional.of(""), Optional.of("/Master_Cells/List/" + pathNode))){
								System.out.println("尝试加锁成功");
								return true;
							}
							else{
								System.out.println("尝试加锁失败");
							}
						}
					}
					
					}
					
				}
				else{
					System.out.println("已经被加锁，进入建组模式");
				}
			return true;
			
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
	private int randomInt(int min, int max){
		return new Random().nextInt(max)%(max-min+1) + min;
	}
	
	private Boolean matchingAlgorithm(String origin, String target, int matchLenght){
		if(origin.substring(origin.length() - matchLenght + 1).equals(target.substring(target.length() - matchLenght +1))){
			System.out.println("匹配");
			return Boolean.TRUE;
		}
		else{
		//	System.out.println("不匹配 " + origin.substring(origin.length() - matchLenght + 1) + "   " + target.substring(target.length() - matchLenght +1));
			return Boolean.FALSE;
		}
	}
	
}
