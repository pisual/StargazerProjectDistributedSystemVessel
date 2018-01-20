package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.information.InformationMethod;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

import io.netty.channel.socket.SocketChannel;

@Component(value="cellsInformationMethodCharacteristic")
@Qualifier("cellsInformationMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformationMethodCharacteristic implements InformationMethod, BaseCharacteristic<InformationMethod>{

    /** @illustrate  SocketChannel命名**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_SocketChannel;
	
	@Autowired
	@Qualifier("socketChannelCache")
	private Cache<String, SocketChannel> socketChannelCache;
	
	public CellsInformationMethodCharacteristic() {}
	
	@Override
	public Optional<InformationMethod> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void push(Optional<Transmission> transmission) {
		socketChannelCache.get(Optional.of(Kernel_Information_Cells_SocketChannel)).get().writeAndFlush(transmission);
	}

}
