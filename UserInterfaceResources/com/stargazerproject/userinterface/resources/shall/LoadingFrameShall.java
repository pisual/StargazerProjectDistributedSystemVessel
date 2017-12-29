package com.stargazerproject.userinterface.resources.shall;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.resources.LoadingFrameLayoutCharacteristic;

@SuppressWarnings("unused")
@Component(value="loadingFrameShall")
@Qualifier("loadingFrameShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameShall implements LoadingUserInterface, BaseCharacteristic<LoadingUserInterface>{

	/**加载进度界面**/
	private Optional<JDialog> loadingBaseFrameJDialog;
	/**加载进度界面进度条**/
	private Optional<JProgressBar> loadingJProgressBar;
	/**加载界面进度条文字标识**/
	private Optional<JLabel> loadingProgressInfo;
	/**加载进度页面背景**/
	private Optional<JLabel> loadingFrameBackgroundJlabel;
	/**加载进度界面布局**/
	private Optional<LoadingFrameLayoutCharacteristic> loadingFrameLayout;
	
	public LoadingFrameShall() {}
	
	@Override
	@Bean(name="loadingFrameShallCharacteristic")
	@Lazy(true)
	public Optional<LoadingUserInterface> characteristic() {
		assemblyInitialization();
		baseFrameInitialization();
		return Optional.of(this);
	}
	
	@SuppressWarnings("unchecked")
	private void assemblyInitialization(){
		loadingBaseFrameJDialog = BeanContainer.instance().getBean(Optional.of("loadingBaseFrameJDialogCharacteristic"), Optional.class);
		loadingJProgressBar = BeanContainer.instance().getBean(Optional.of("loadingJProgressBarCharacteristic"), Optional.class);
		loadingProgressInfo = BeanContainer.instance().getBean(Optional.of("loadingProgressInfoCharacteristic"), Optional.class);
		loadingFrameBackgroundJlabel = BeanContainer.instance().getBean(Optional.of("loadingFrameBackgroundJlabelCharacteristic"), Optional.class);
		loadingFrameLayout = BeanContainer.instance().getBean(Optional.of("loadingFrameLayoutCharacteristic"), Optional.class);
	}
	
	private void baseFrameInitialization(){
		loadingBaseFrameJDialog.get().getLayeredPane().add(loadingFrameBackgroundJlabel.get(), new Integer(Integer.MIN_VALUE));
		loadingBaseFrameJDialog.get().getLayeredPane().add(loadingJProgressBar.get(), new Integer(Integer.MIN_VALUE)+1);
		loadingBaseFrameJDialog.get().getLayeredPane().add(loadingProgressInfo.get(), new Integer(Integer.MIN_VALUE)+2);
	}

	@Override
	public void startLoading() {
		loadingBaseFrameJDialog.get().setVisible(Boolean.TRUE);
	}

	@Override
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent) {
		loadingProgressInfo.get().setText(title.get());
		loadingJProgressBar.get().setValue(increasePercent.get());
	}

	@Override
	public void endLoading() {
		loadingBaseFrameJDialog.get().setVisible(Boolean.FALSE);
	}

}
