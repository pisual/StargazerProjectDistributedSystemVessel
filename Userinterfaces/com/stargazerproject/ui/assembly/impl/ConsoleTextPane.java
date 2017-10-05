package com.stargazerproject.ui.assembly.impl;

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

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;
import com.stargazerproject.ui.exception.ExceptionDispose;
import com.stargazerproject.ui.util.ColorUtil;
import com.stargazerproject.ui.util.FontUtil;
import com.stargazerproject.ui.util.UIUtil;

/**
 * 主界面控制台输出
 * 
 * @Web https://github.com/pisual http://www.pisual.com
 * @email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 * @author Felixerio FelixSion
 */
public class ConsoleTextPane extends JTextPane {
	private static final long serialVersionUID = 7309857723035362456L;
	/** ConsoleTextArea单例 **/
	private static ConsoleTextPane consoleTextArea;
	/** 控制台输出字符字体类型 **/
	private static Font ConsoleTextFont;
	/** 控制台备用字体的绝对路径 在系统包含指定字体的情况下将不使用备用字体 **/
	public static String Main_Frame_Console_StandbyFontPath;
	/** 控制台指定字体名称 **/
	public static String Main_Frame_Console_FontName;
	/** 控制台输出字符字体颜色 **/
	private static Color ConsoleText_FontColor;

	private static StyledDocument styledDocument;

	private static Style style;

	private static SimpleAttributeSet simpleAttributeSet;
	
    private static Document consoleDocument;

	public static ConsoleTextPane getInstance() {
		if (consoleTextArea == null) {
			consoleTextArea = new ConsoleTextPane();
			consoleTextArea.initConsoleTextArea();
		}
		return consoleTextArea;
	}

	private ConsoleTextPane() {
		Main_Frame_Console_StandbyFontPath = SystemParameterCahce.getInstance().getString("Main_Frame_Console_StandbyFontPath");
		Main_Frame_Console_FontName = SystemParameterCahce.getInstance().getString("Main_Frame_Console_FontName");
		ConsoleText_FontColor = ColorUtil.getColorFromSystemmParanment("ConsoleText_FontColor");
		ConsoleTextFont = FontUtil.getConsoleFont(Main_Frame_Console_FontName,Main_Frame_Console_StandbyFontPath);
		simpleAttributeSet = new SimpleAttributeSet();
		StyleConstants.setForeground(simpleAttributeSet, ConsoleText_FontColor);
	}

	private void initConsoleTextArea() {
		styledDocument = consoleTextArea.getStyledDocument();
		style = styledDocument.addStyle("ConsoleTextPane", null);
		StyleConstants.setIcon(style,new ImageIcon(UserinterfaceResource.class.getResource(SystemParameterCahce.getInstance().getString("ConsoleTextPane_Text_Circle"))));
		consoleTextArea.setOpaque(false);
		consoleTextArea.setFont(ConsoleTextFont);
		consoleTextArea.setForeground(ConsoleText_FontColor);
		consoleTextArea.setBorder(BorderFactory.createEmptyBorder());
		UIUtil.startConsoleReaderThread(consoleTextArea);
		consoleDocument = consoleTextArea.getDocument();
	}

	public void insertMessage(String text) {
		try {
			boolean caretAtEnd = consoleTextArea.getCaretPosition() == consoleDocument.getLength() ? true : false;
			styledDocument.insertString(styledDocument.getLength(), text + '\n', simpleAttributeSet);
			  if(caretAtEnd)
				  consoleTextArea.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException e) {
			ExceptionDispose.catchExceptionAndSaveToDatabase("Document 配置错误");
		}
	}

	public void insertLogo() {
		StyleConstants.setIcon(style,new ImageIcon(UserinterfaceResource.class.getResource(SystemParameterCahce.getInstance().getString("ConsoleTextPane_Text_Circle"))));
		try {
			styledDocument.insertString(styledDocument.getLength(), " ", style);
			boolean caretAtEnd = consoleTextArea.getCaretPosition() == consoleDocument.getLength() ? true : false;
			  if(caretAtEnd)
				  consoleTextArea.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException ex) {
			ExceptionDispose.catchExceptionAndSaveToDatabase("Document 配置错误");
		}
	}

	public static void insertLine() {
		StyleConstants.setIcon(style,new ImageIcon("/Users/Felixerio/Workspaces/StargazerProject/StargazerProjectntelligenceSystem/StargazerUIAssaultLily/PisualCellsSmall.png"));
		try {
			styledDocument.insertString(styledDocument.getLength(), " ", style);
			boolean caretAtEnd = consoleTextArea.getCaretPosition() == consoleDocument.getLength() ? true : false;
			  if(caretAtEnd)
				  consoleTextArea.setCaretPosition(consoleDocument.getLength());
		} catch (BadLocationException ex) {
			ExceptionDispose.catchExceptionAndSaveToDatabase("Document 配置错误");
		}
	}

}