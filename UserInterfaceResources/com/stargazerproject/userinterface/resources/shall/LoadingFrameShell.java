package com.stargazerproject.userinterface.resources.shall;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.resources.LoadingFrameLayoutCharacteristic;

@Component(value="loadingFrameShell")
@Qualifier("loadingFrameShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameShell implements LoadingUserInterface, BaseCharacteristic<LoadingUserInterface>{

	/**加载进度界面特征**/
	@Autowired
	@Qualifier("loadingBaseFrameJDialogCharacteristic")
	private BaseCharacteristic<JDialog> loadingBaseFrameJDialogCharacteristic;
	
	/**加载进度界面**/
	private JDialog loadingBaseFrameJDialog;
	
	/**加载进度界面进度条特征**/
	@Autowired
	@Qualifier("loadingJProgressBarCharacteristic")
	private BaseCharacteristic<JProgressBar> loadingJProgressBarCharacteristic;
	
	/**加载进度界面进度条**/
	private JProgressBar loadingJProgressBar;

	/**加载界面进度条文字标识特征**/
	@Autowired
	@Qualifier("loadingProgressInfoCharacteristic")
	private BaseCharacteristic<JLabel> loadingProgressInfoCharacteristic;
	
	/**加载界面进度条文字标识**/
	private JLabel loadingProgressInfo;
	
	/**加载进度页面背景特征**/
	@Autowired
	@Qualifier("loadingFrameBackgroundJlabelCharacteristic")
	private BaseCharacteristic<JLabel> loadingFrameBackgroundJlabelCharacteristic;
	
	/**加载进度页面背景**/
	private JLabel loadingFrameBackgroundJlabel;
	
	/**加载进度界面布局特征**/
	@Autowired
	@Qualifier("loadingFrameLayoutCharacteristic")
	private BaseCharacteristic<LoadingFrameLayoutCharacteristic> loadingFrameLayoutCharacteristic;
	
	/**加载进度界面布局**/
	private LoadingFrameLayoutCharacteristic loadingFrameLayout;
	
	public LoadingFrameShell() {}
	
	@Override
	public Optional<LoadingUserInterface> characteristic() {
		assemblyInitialization();
		baseFrameInitialization();
		return Optional.of(this);
	}
	
	private void assemblyInitialization(){
		loadingBaseFrameJDialog = loadingBaseFrameJDialogCharacteristic.characteristic().get();
		loadingJProgressBar = loadingJProgressBarCharacteristic.characteristic().get();
		loadingProgressInfo = loadingProgressInfoCharacteristic.characteristic().get();
		loadingFrameBackgroundJlabel = loadingFrameBackgroundJlabelCharacteristic.characteristic().get();
		loadingFrameLayout = loadingFrameLayoutCharacteristic.characteristic().get();
	}
	
	private void baseFrameInitialization(){
		loadingBaseFrameJDialog.getLayeredPane().add(loadingFrameBackgroundJlabel, new Integer(Integer.MIN_VALUE));
		loadingBaseFrameJDialog.getLayeredPane().add(loadingJProgressBar, new Integer(Integer.MIN_VALUE) + 1);
		loadingBaseFrameJDialog.getLayeredPane().add(loadingProgressInfo, new Integer(Integer.MIN_VALUE) + 2);
	}

	@Override
	public void startLoading() {
		loadingBaseFrameJDialog.setVisible(Boolean.TRUE);
	}

	@Override
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent) {
		loadingProgressInfo.setText(title.get());
		loadingJProgressBar.setValue(increasePercent.get());
	}

	@Override
	public void endLoading() {
		loadingBaseFrameJDialog.setVisible(Boolean.FALSE);
	}

}
