package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;
import com.stargazerproject.util.ColorUtil;
import com.stargazerproject.util.FontUtil;
import com.stargazerproject.util.ParameterStringUtil;
import com.stargazerproject.util.UIUtil;

/**
 * 主界面控制台输出
 * 
 * @author Felixerio
 */
@Component(value="mainFrameConsoleTextPaneCharacteristic")
@Qualifier("mainFrameConsoleTextPaneCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameConsoleTextPaneCharacteristic implements BaseCharacteristic<JTextPane>{
	
	/** @name 主界面控制台字体的路径**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Font_Path_Console;
	
	/** @name 主界面控制台字体的名称 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Font_Console;
	
	/** @name 主界面控制台字体的RGB颜色  **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Font_Color_Console;
	
	/** @name 主界面控制台字体前端行装饰 每行字前端加上的装饰性图标**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Font_Icon_Line;
	
	private Style style;
	private StyledDocument styledDocument;
	private SimpleAttributeSet simpleAttributeSet;
	
	private JTextPane jTextPane = new JTextPane();
	
	@Override
	public Optional<JTextPane> characteristic() {
		initialization();
		return Optional.of(jTextPane);
	}
	
	private void initialization(){
		styleInitialization();
		jTextPane.setOpaque(false);
		jTextPane.setFont(fontInitialization());
		jTextPane.setForeground(fontColorInitialization());
		jTextPane.setBorder(BorderFactory.createEmptyBorder());
		UIUtil.startConsoleReaderThread(jTextPane);
	}
	
	private void styleInitialization(){
		styledDocument = jTextPane.getStyledDocument();
		style = styledDocument.addStyle("ConsoleTextPane", null);
		StyleConstants.setForeground(new SimpleAttributeSet(), fontColorInitialization());
		StyleConstants.setIcon(style, new ImageIcon(UserinterfaceResource.class.getResource(Kernel_UserInterface_MainFrame_Font_Icon_Line)));
	}
	
	private Font fontInitialization(){
		Font ConsoleTextFont = FontUtil.getConsoleFont(Kernel_UserInterface_MainFrame_Font_Console, Kernel_UserInterface_MainFrame_Font_Path_Console);
		return ConsoleTextFont;
	}
	
	private Color fontColorInitialization(){
		int[] colorArray = ParameterStringUtil.segmentationArray(Optional.of(Kernel_UserInterface_MainFrame_Font_Color_Console), decollator(","), arrayLength(3)).get();
		Color ConsoleText_FontColor = ColorUtil.getColorFromIntRGBParament(colorArray);
		return ConsoleText_FontColor;
	}


	public void insertMessage(Optional<String> text) {
		try {
			styledDocument.insertString(styledDocument.getLength(), text.get() + '\n', simpleAttributeSet);
			cursorLocation();
		} catch (BadLocationException badLocationException) {
			badLocationException.printStackTrace();
		}
	}
	
	public void setVisible(){
		jTextPane.setVisible(Boolean.TRUE);
	}
	
	public void unVisible(){
		jTextPane.setVisible(Boolean.FALSE);
	}

	public void insertLogo() {
		ImageIcon imageIcon = new ImageIcon(UserinterfaceResource.class.getResource(Kernel_UserInterface_MainFrame_Font_Icon_Line));
		StyleConstants.setIcon(style, imageIcon);
		try {
			styledDocument.insertString(styledDocument.getLength(), " ", style);
			cursorLocation();
		} catch (BadLocationException badLocationException) {
			badLocationException.printStackTrace();
		}
	}
	
	private void cursorLocation(){
	    Document consoleDocument = jTextPane.getDocument();;
		boolean caretAtEnd = jTextPane.getCaretPosition() == consoleDocument.getLength() ? true : false;
		  if(caretAtEnd){
			  jTextPane.setCaretPosition(consoleDocument.getLength());
		  }
	}
	
	private Optional<String> decollator(String decollator){
		return Optional.of(decollator);
	}
	
	private Optional<Integer> arrayLength(int arrayLength){
		return Optional.of(arrayLength);
	}

}