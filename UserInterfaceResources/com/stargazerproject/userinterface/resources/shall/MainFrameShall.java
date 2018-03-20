package com.stargazerproject.userinterface.resources.shall;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;
import com.stargazerproject.userinterface.resources.GradientLoadInterface;
import com.stargazerproject.userinterface.resources.MainFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameRightConsoleTextPaneCharacteristic;

@Component(value="mainFrameShall")
@Qualifier("mainFrameShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameShall implements MainFrameAssaultLilysUserInterface, BaseCharacteristic<MainFrameAssaultLilysUserInterface>{
	
	/**混合主界面特征**/
	@Autowired
	@Qualifier("mainFrameJFrameCharacteristic")
	private BaseCharacteristic<JFrame> mainFrameJFrameCharacteristic;
	
	/**混合主界面**/
	private JFrame mainFrameJFrame;
	
	/**主界面左控制台特征**/
	@Autowired
	@Qualifier("mainFrameConsoleTextPaneCharacteristic")
	private BaseCharacteristic<JTextPane> mainFrameConsoleTextPaneCharacteristic;
	
	/**主界面左控制台**/
	private JTextPane mainFrameConsoleTextPane;
	
	/**主界面右控制台特征**/
	@Autowired
	@Qualifier("mainFrameRightConsoleTextPaneCharacteristic")
	private BaseCharacteristic<JTextPane> mainFrameRightConsoleTextPaneCharacteristic;
	
	/**主界面右控制台**/
	private JTextPane mainFrameRightConsoleTextPane;
	
	/**主界面左面滚动条特征**/
	@Autowired
	@Qualifier("mainFrameJScrollPaneCharacteristic")
	private BaseCharacteristic<JScrollPane> mainFrameJScrollPaneCharacteristic;
	
	/**主界面左面滚动条**/
	private JScrollPane mainFrameJScrollPane;
	
	/**主界面右面滚动条特征**/
	@Autowired
	@Qualifier("mainFrameRightJScrollPaneCharacteristic")
	private BaseCharacteristic<JScrollPane> mainFrameRightJScrollPaneCharacteristic;

	/**主界面右面滚动条**/
	private JScrollPane mainFramerightJScrollPane;
	
	/**主界面Logo特征 **/
	@Autowired
	@Qualifier("mainFrameLogoJlabelCharacteristic")
	private BaseCharacteristic<JLabel> mainFrameLogoJlabelCharacteristic;
	
	/**主界面Logo **/
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
		mainFrameJFrame = mainFrameJFrameCharacteristic.characteristic().get();
		mainFrameConsoleTextPane = mainFrameConsoleTextPaneCharacteristic.characteristic().get();
		mainFrameRightConsoleTextPane = mainFrameRightConsoleTextPaneCharacteristic.characteristic().get();
		mainFrameJScrollPane = mainFrameJScrollPaneCharacteristic.characteristic().get();
		mainFramerightJScrollPane = mainFrameRightJScrollPaneCharacteristic.characteristic().get();
		mainFrameLogoJlabel = mainFrameLogoJlabelCharacteristic.characteristic().get();
		
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
