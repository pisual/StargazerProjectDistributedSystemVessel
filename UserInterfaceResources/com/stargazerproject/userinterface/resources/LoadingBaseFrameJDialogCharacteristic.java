package com.stargazerproject.userinterface.resources;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;
import com.sun.awt.AWTUtilities;

/**
 * 加载进度界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
@SuppressWarnings("restriction")
@Component(value="loadingBaseFrameJDialog")
@Qualifier("loadingBaseFrameJDialog")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingBaseFrameJDialogCharacteristic extends JDialog implements BaseCharacteristic<JDialog>{
	private static final long serialVersionUID = 5171904966548890916L;
	
	public LoadingBaseFrameJDialogCharacteristic() {}
	
	@Override
	@Bean(name="loadingBaseFrameJDialogCharacteristic")
	@Lazy(true)
	public Optional<JDialog> characteristic() {
		initLoadingBaseFrameJDialog();
		return Optional.of(this);
	}
	
	private void initLoadingBaseFrameJDialog(){
		((JPanel) this.getContentPane()).setOpaque(false);
		this.setUndecorated(true);
		this.setSize(295,296);
		this.setBounds(839, 403, 296, 296);
		AWTUtilities.setWindowOpaque(this, false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(UserinterfaceResource.class.getResource("logo.png")));
	}
}
