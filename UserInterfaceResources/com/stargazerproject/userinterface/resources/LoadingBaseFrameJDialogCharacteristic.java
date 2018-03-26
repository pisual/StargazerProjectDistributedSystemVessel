package com.stargazerproject.userinterface.resources;

import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;
import com.sun.awt.AWTUtilities;

/**
 * 加载进度界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
@Component(value="loadingBaseFrameJDialogCharacteristic")
@Qualifier("loadingBaseFrameJDialogCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingBaseFrameJDialogCharacteristic extends JDialog implements BaseCharacteristic<JDialog>{
	private static final long serialVersionUID = 5171904966548890916L;
	
	/** @name 加载界面使用者头像 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Icon_Logo;
	
	public LoadingBaseFrameJDialogCharacteristic() {}
	
	@Override
	public Optional<JDialog> characteristic() {
		initLoadingBaseFrameJDialog();
		initializationJDialogIcon();
		return Optional.of(this);
	}
	
	private void initLoadingBaseFrameJDialog(){
		((JPanel) this.getContentPane()).setOpaque(false);
		this.setUndecorated(true);
		this.setSize(295,296);
		this.setBounds(839, 403, 296, 296);
		AWTUtilities.setWindowOpaque(this, false);
	}
	
	private void initializationJDialogIcon(){
		URL url = UserinterfaceResource.class.getResource(Kernel_UserInterface_LoadingFrame_Icon_Logo);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
	}
}
