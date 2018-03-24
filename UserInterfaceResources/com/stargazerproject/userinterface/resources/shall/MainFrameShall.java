package com.stargazerproject.userinterface.resources.shall;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
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
	private BaseCharacteristic<MainFrameConsoleTextPaneCharacteristic> mainFrameConsoleTextPaneCharacteristic;
	
	/**主界面左控制台**/
	private MainFrameConsoleTextPaneCharacteristic mainFrameConsoleTextPane;
	
	/**主界面右控制台特征**/
	@Autowired
	@Qualifier("mainFrameRightConsoleTextPaneCharacteristic")
	private BaseCharacteristic<MainFrameRightConsoleTextPaneCharacteristic> mainFrameRightConsoleTextPaneCharacteristic;
	
	/**主界面右控制台**/
	private MainFrameRightConsoleTextPaneCharacteristic mainFrameRightConsoleTextPane;
	
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
	private JLabel mainFrameLogoJlabel;
	
	/**主界面系统构架拓扑特征 **/
	@Autowired
	@Qualifier("MainFrameStructureTopologyJlabelCharacteristic")
	private BaseCharacteristic<JLabel> mainFrameStructureTopologyJlabelCharacteristic;
	
	/**主界面系统构架拓扑**/
	private JLabel mainFrameStructureTopologyJlabel;
	
	/**主界面系统构架拓扑特征 **/
	@Autowired
	@Qualifier("mainFrameBackgroundJlabelCharacteristic")
	private BaseCharacteristic<JLabel> mainFrameBackgroundJlabelCharacteristic;
	
	/**主界面背景**/
	private JLabel mainFrameBackgroundJlabel;
	
	/**混合主界面布局特征 **/
	@Autowired
	@Qualifier("mainFrameLayoutCharacteristic")
	private BaseCharacteristic<MainFrameLayoutCharacteristic> mainFrameLayoutCharacteristic;
	
	/**混合主界面布局**/
	private MainFrameLayoutCharacteristic mainFrameLayout;
	
	/**主界面坐标点特征 **/
	@Autowired
	@Qualifier("mainFramePointCharacteristic")
	private BaseCharacteristic<Point> mainFramePointCharacteristic;
	
	/**主界面坐标点**/
	private Point mainFramePoint;
	
	/**主界面鼠标点击事件特征 **/
	@Autowired
	@Qualifier("mainFrameLogoClickListenerCharacteristic")
	private BaseCharacteristic<MouseAdapter> mainFrameLogoClickListenerCharacteristic;
	
	/**主界面鼠标点击事件**/
	private MouseAdapter mainFrameLogoClickListener;
	
	/**主界面鼠标点击位置特征 **/
	@Autowired
	@Qualifier("mainFrameMouseAdapterListenerCharacteristic")
	private BaseCharacteristic<MouseAdapter> mainFrameMouseAdapterListenerCharacteristic;
	
	/**主界面鼠标点击位置**/
	private MouseAdapter mainFrameMouseAdapterListener;
	
	/**主界面鼠标点击事件特征 **/
	@Autowired
	@Qualifier("mainFrameMouseMotionAdapterListenerCharacteristic")
	private BaseCharacteristic<MouseMotionAdapter> mainFrameMouseMotionAdapterListenerCharacteristic;
	
	/**主界面鼠标点击事件**/
	private MouseMotionAdapter mainFrameMouseMotionAdapterListener;


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
		mainFrameStructureTopologyJlabel = mainFrameStructureTopologyJlabelCharacteristic.characteristic().get();
		mainFrameBackgroundJlabel = mainFrameBackgroundJlabelCharacteristic.characteristic().get();
		mainFrameLayout = mainFrameLayoutCharacteristic.characteristic().get();
		mainFramePoint = mainFramePointCharacteristic.characteristic().get();
		mainFrameLogoClickListener = mainFrameLogoClickListenerCharacteristic.characteristic().get();
		mainFrameMouseAdapterListener = mainFrameMouseAdapterListenerCharacteristic.characteristic().get();
		mainFrameMouseMotionAdapterListener = mainFrameMouseMotionAdapterListenerCharacteristic.characteristic().get();
	}
	
	private void baseFrameInitialization(){
		mainFrameJFrame.getLayeredPane().add(mainFrameBackgroundJlabel, new Integer(Integer.MIN_VALUE));
		mainFrameJFrame.getLayeredPane().add(mainFrameLogoJlabel, new Integer(Integer.MIN_VALUE) + 1);
		mainFrameJFrame.getLayeredPane().add(mainFrameStructureTopologyJlabel, new Integer(Integer.MIN_VALUE) + 2);
		mainFrameLogoJlabel.addMouseListener(mainFrameLogoClickListener);
		mainFrameLogoJlabel.addMouseListener(mainFrameMouseAdapterListener);
		mainFrameLogoJlabel.addMouseMotionListener(mainFrameMouseMotionAdapterListener);	
	}
	
	@Override
	public void startMain() {
		mainFrameJFrame.setVisible(Boolean.TRUE);
		mainFrameLogoJlabel.setVisible(Boolean.TRUE);
		mainFrameStructureTopologyJlabel.setVisible(Boolean.TRUE);
	}

	@Override
	public void endMain() {
		mainFrameJFrame.setVisible(Boolean.FALSE);
		mainFrameLogoJlabel.setVisible(Boolean.FALSE);
		mainFrameStructureTopologyJlabel.setVisible(Boolean.FALSE);
	}

	@Override
	public void visualLeftTerminal() {
		mainFrameConsoleTextPane.setVisible(Boolean.TRUE);
	}

	@Override
	public void unvisualLeftTerminal() {
		mainFrameConsoleTextPane.setVisible(Boolean.FALSE);
	}

	@Override
	public void addLeftTerminalText(Optional<String> content) {
		mainFrameConsoleTextPane.insertMessage(content);
	}

	@Override
	public void visualRightTerminal() {
		mainFrameRightConsoleTextPane.setVisible(Boolean.TRUE);
	}

	@Override
	public void unvisualRightTerminal() {
		mainFrameRightConsoleTextPane.setVisible(Boolean.FALSE);
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		mainFrameRightConsoleTextPane.insertMessage(content);
	}

}
