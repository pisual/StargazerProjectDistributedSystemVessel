package com.stargazerproject.userinterface.resources;

import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
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
public class LoadingBaseFrameJDialogCharacteristic implements BaseCharacteristic<JDialog>{
	
	/** @name 加载界面使用者头像 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Icon_Logo;
	
	private JDialog jDialog = new JDialog();
	
	private Boolean init = Boolean.FALSE;
	
	public LoadingBaseFrameJDialogCharacteristic() {}
	
	@Override
	public Optional<JDialog> characteristic() {
		synchronized(init){
			if(init == Boolean.FALSE){
				initLoadingBaseFrameJDialog();
				initializationJDialogIcon();
				init = Boolean.TRUE;
			}
		}
		return Optional.of(jDialog);
	}
	
	private void initLoadingBaseFrameJDialog(){
		((JPanel) jDialog.getContentPane()).setOpaque(false);
		jDialog.setUndecorated(true);
		jDialog.setSize(295,296);
		jDialog.setBounds(839, 403, 296, 296);
		AWTUtilities.setWindowOpaque(jDialog, false);
	}
	
	private void initializationJDialogIcon(){
		URL url = UserinterfaceResource.class.getResource(Kernel_UserInterface_LoadingFrame_Icon_Logo);
		jDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
	}
}
