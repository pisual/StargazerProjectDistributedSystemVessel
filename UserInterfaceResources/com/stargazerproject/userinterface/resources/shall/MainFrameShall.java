package com.stargazerproject.userinterface.resources.shall;

import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;
import com.stargazerproject.userinterface.resources.GradientLoadInterface;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameRightConsoleTextPaneCharacteristic;

@SuppressWarnings("unused")
@Component(value="mainFrameShall")
@Qualifier("mainFrameShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameShall implements MainFrameAssaultLilysUserInterface, BaseCharacteristic<MainFrameAssaultLilysUserInterface>{
	
	/**混合主界面**/
	private Optional<JFrame> baseFrame;
	/**控制台**/
	private Optional<MainFrameConsoleTextPaneCharacteristic> consoleTextPane;
	/**控制台**/
	private Optional<MainFrameRightConsoleTextPaneCharacteristic> rightConsoleTextPane;
	/**主界面滚动条**/
	private Optional<JScrollPane> jScrollPane;
	/**主界面滚动条**/
	private Optional<JScrollPane> rightJScrollPane;
	/**操纵图标头像位置**/
	private Optional<JLabel> mainFrameLogoJlabel;
	/**系统构架拓扑**/
	private Optional<JLabel> structureTopologyJlabel;
	/**主界面背景**/
	private Optional<GradientLoadInterface> mainFrameBackgroundJlabel;
	/**混合主界面布局**/
	private Optional<MainFrameLayoutCharacteristic> mainFrameLayout;
	/**主界面坐标点，用于界面拖动**/
	private Optional<Point> mainFramePointCharacteristic;
	/**主界面鼠标点击事件**/
	private Optional<MouseAdapter> logoClickListenerCharacteristic;
	/**获取主界面鼠标点击位置**/
	private Optional<MouseAdapter> mainFrameMouseAdapterListenerCharacteristic;
	/**主界面鼠标点击事件**/
	private Optional<MouseMotionAdapter> mainFrameMouseMotionAdapterListenerCharacteristic;


	@Override
	@Bean(name="mainFrameShallCharacteristic")
	@Lazy(true)
	public Optional<MainFrameAssaultLilysUserInterface> characteristic() {
		assemblyInitialization();
		baseFrameInitialization();
		return Optional.of(this);
	}

	@SuppressWarnings("unchecked")
	private void assemblyInitialization(){
		baseFrame = BeanContainer.instance().getBean(Optional.of("mainFrameJFrameCharacteristic"), Optional.class);
		consoleTextPane = BeanContainer.instance().getBean(Optional.of("mainFrameConsoleTextPaneCharacteristic"), Optional.class);
		rightConsoleTextPane = BeanContainer.instance().getBean(Optional.of("mainFrameRightConsoleTextPaneCharacteristic"), Optional.class);
		jScrollPane = BeanContainer.instance().getBean(Optional.of("mainFrameJScrollPaneCharacteristic"), Optional.class);
		rightJScrollPane = BeanContainer.instance().getBean(Optional.of("mainFrameRightJScrollPaneCharacteristic"), Optional.class);
		mainFrameLogoJlabel = BeanContainer.instance().getBean(Optional.of("mainFrameLogoJlabelCharacteristic"), Optional.class);
		structureTopologyJlabel = BeanContainer.instance().getBean(Optional.of("mainFrameStructureTopologyJlabelCharacteristic"), Optional.class);
		mainFrameBackgroundJlabel = BeanContainer.instance().getBean(Optional.of("mainFrameBackgroundJlabelCharacteristic"), Optional.class);
		mainFrameLayout = BeanContainer.instance().getBean(Optional.of("mainFrameLayoutCharacteristic"), Optional.class);
		mainFramePointCharacteristic = BeanContainer.instance().getBean(Optional.of("mainFramePointCharacteristic"), Optional.class);
		logoClickListenerCharacteristic = BeanContainer.instance().getBean(Optional.of("logoClickListenerCharacteristic"), Optional.class);
		mainFrameMouseAdapterListenerCharacteristic = BeanContainer.instance().getBean(Optional.of("mainFrameMouseAdapterListenerCharacteristic"), Optional.class);
		mainFrameMouseMotionAdapterListenerCharacteristic = BeanContainer.instance().getBean(Optional.of("mainFrameMouseMotionAdapterListenerCharacteristic"), Optional.class);
	}
	
	private void baseFrameInitialization(){
		baseFrame.get().getLayeredPane().add(mainFrameBackgroundJlabel.get(),new Integer(Integer.MIN_VALUE));
		baseFrame.get().getLayeredPane().add(mainFrameLogoJlabel.get(),new Integer(Integer.MIN_VALUE)+1);
		baseFrame.get().getLayeredPane().add(structureTopologyJlabel.get(),new Integer(Integer.MIN_VALUE)+2);
		mainFrameLogoJlabel.get().addMouseListener(logoClickListenerCharacteristic.get());
		mainFrameLogoJlabel.get().addMouseListener(mainFrameMouseAdapterListenerCharacteristic.get());
		mainFrameLogoJlabel.get().addMouseMotionListener(mainFrameMouseMotionAdapterListenerCharacteristic.get());	
	}
	
	@Override
	public void startMain() {
		baseFrame.get().setVisible(Boolean.TRUE);
		mainFrameLogoJlabel.get().setVisible(Boolean.TRUE);
		structureTopologyJlabel.get().setVisible(Boolean.TRUE);
	}

	@Override
	public void endMain() {
		baseFrame.get().setVisible(Boolean.FALSE);
		mainFrameLogoJlabel.get().setVisible(Boolean.FALSE);
		structureTopologyJlabel.get().setVisible(Boolean.FALSE);
	}

	@Override
	public void visualLeftTerminal() {
		consoleTextPane.get().setVisible(Boolean.TRUE);
	}

	@Override
	public void unvisualLeftTerminal() {
		consoleTextPane.get().setVisible(Boolean.FALSE);
	}

	@Override
	public void addLeftTerminalText(Optional<String> content) {
		consoleTextPane.get().insertMessage(content);
	}

	@Override
	public void visualRightTerminal() {
		rightConsoleTextPane.get().setVisible(Boolean.TRUE);
	}

	@Override
	public void unvisualRightTerminal() {
		rightConsoleTextPane.get().setVisible(Boolean.FALSE);
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		rightConsoleTextPane.get().insertMessage(content);
	}

}
