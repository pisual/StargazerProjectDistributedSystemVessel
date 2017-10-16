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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
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
@Component(value="mainFrameConsoleTextPane")
@Qualifier("mainFrameConsoleTextPane")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameConsoleTextPaneCharacteristic extends JTextPane implements BaseCharacteristic<JTextPane>{
	private static final long serialVersionUID = 7309857723035362456L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	private static StyledDocument styledDocument;
	private static Style style;
	private static SimpleAttributeSet simpleAttributeSet;
    private static Document consoleDocument;
	
	@Override
	@Bean(name="mainFrameConsoleTextPaneCharacteristic")
	@Lazy(true)
	public Optional<JTextPane> characteristic() {
		initialization();
		return Optional.of(this);
	}
	
	private void initialization(){
		StyleConstants.setForeground(new SimpleAttributeSet(), fontColorInitialization());
		styledDocument = this.getStyledDocument();
		style = styledDocument.addStyle("ConsoleTextPane", null);
		StyleConstants.setIcon(style,new ImageIcon(UserinterfaceResource.class.getResource(systemParameter.get(Optional.of("ConsoleTextPane_Text_Circle")).get())));
		consoleDocument = this.getDocument();
		this.setOpaque(false);
		this.setFont(fontInitialization());
		this.setForeground(fontColorInitialization());
		this.setBorder(BorderFactory.createEmptyBorder());
		UIUtil.startConsoleReaderThread(this);
	}
	
	private Font fontInitialization(){
		String Main_Frame_Console_StandbyFontPath = systemParameter.get(Optional.of("Main_Frame_Console_StandbyFontPath")).get();
		String Main_Frame_Console_FontName = systemParameter.get(Optional.of("Main_Frame_Console_FontName")).get();
		Font ConsoleTextFont = FontUtil.getConsoleFont(Main_Frame_Console_FontName, Main_Frame_Console_StandbyFontPath);
		return ConsoleTextFont;
	}
	
	private Color fontColorInitialization(){
		Color ConsoleText_FontColor = ColorUtil.getColorFromIntRGBParament(ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("ConsoleText_FontColor")), Optional.of(","), Optional.of(3)).get());
		return ConsoleText_FontColor;
	}


	public void insertMessage(Optional<String> text) {
		try {
			boolean caretAtEnd = this.getCaretPosition() == consoleDocument.getLength() ? true : false;
			styledDocument.insertString(styledDocument.getLength(), text.get() + '\n', simpleAttributeSet);
			  if(caretAtEnd)
				  this.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException e) {
		}
	}

	public void insertLogo() {
		StyleConstants.setIcon(style,new ImageIcon(UserinterfaceResource.class.getResource(systemParameter.get(Optional.of("ConsoleTextPane_Text_Circle")).get())));
		try {
			styledDocument.insertString(styledDocument.getLength(), " ", style);
			boolean caretAtEnd = this.getCaretPosition() == consoleDocument.getLength() ? true : false;
			  if(caretAtEnd)
				  this.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException ex) {
		}
	}

	public void insertLine() {
		StyleConstants.setIcon(style,new ImageIcon("/Users/Felixerio/Workspaces/StargazerProject/StargazerProjectntelligenceSystem/StargazerUIAssaultLily/PisualCellsSmall.png"));
		try {
			styledDocument.insertString(styledDocument.getLength(), " ", style);
			boolean caretAtEnd = this.getCaretPosition() == consoleDocument.getLength() ? true : false;
			  if(caretAtEnd)
				  this.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException ex) {
		}
	}

}