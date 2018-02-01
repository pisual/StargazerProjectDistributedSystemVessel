package com.stargazerproject.interfaces.characteristic.shell;

/** 
 *  @name 预先注入特征壳式(®Cells System)领域对象(Shell Type Domain Objects)
 *  @illustrate 采用AOP注入特征的壳式(®Cells System)领域对象(Shell Type Domain Objects),
 *              针对于特征的复杂操作及初始化操作放置在AOP中进行操作。
 *  @param <K> 针对于一些需要预先注入的特征进行Spring扫描级别的注入
 *  @author Felixerio
 *  **/
public interface BeforehandCharacteristicShell<K> {
	
	/**
	* @name 预先注入初始化特征
	* @illustrate 通过切面来进行复杂特征的注入，使领域对象的局部特征剥离复杂操作
	* @param BaseCharacteristic<K> 特征
	* **/
	public void initialize(BaseCharacteristic<K> k);
}
