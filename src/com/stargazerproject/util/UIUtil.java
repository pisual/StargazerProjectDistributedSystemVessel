package com.stargazerproject.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import com.google.common.base.Optional;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameRightConsoleTextPaneCharacteristic;


/**
 * 界面UI工具包
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class UIUtil {
	
	/**调整窗口居中**/
	public static void changeFrameToCenter(JFrame jsFrame){
		 Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
	     Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
	     int screenWidth = screenSize.width/2; // 获取屏幕的宽
	     int screenHeight = screenSize.height/2; // 获取屏幕的高
	     int height = jsFrame.getHeight(); 
	     int width = jsFrame.getWidth();
	     jsFrame.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
	
	/**调整窗口居中**/
	public static void changeJDialogToCenter(JDialog jDialog){
		 Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
	     Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
	     int screenWidth = screenSize.width/2; // 获取屏幕的宽
	     int screenHeight = screenSize.height/2; // 获取屏幕的高
	     int height = jDialog.getHeight(); 
	     int width = jDialog.getWidth();
	     jDialog.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
	
	/**调整窗口居中**/
	public static void changeJLabelToCenter(JLabel jLabel){
		 Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
	     Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
	     int screenWidth = screenSize.width/2; // 获取屏幕的宽
	     int screenHeight = screenSize.height/2; // 获取屏幕的高
	     int height = jLabel.getHeight(); 
	     int width = jLabel.getWidth();
	     jLabel.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
	
    public static void startConsoleReaderThread(MainFrameConsoleTextPaneCharacteristic mainFrameConsoleTextPaneCharacteristic) {
//    	JTextPane jTextPane = mainFrameConsoleTextPaneCharacteristic.getJTextPane().get();
//    	LoopedStreams ls = null;
//		try {
//			ls = new LoopedStreams();
//		} catch (IOException e) {
//			System.err.println("StargazerSystem Error Report : "+"控制台输出 LoopedStreams 异常");
//		}
//        PrintStream ps = new PrintStream(ls.getOutputStream());
//        System.setOut(ps);
//        final BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(ls.getInputStream()));
//        Document consoleDocument = jTextPane.getDocument();
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                	    String consoleContent;
//                    while((consoleContent = consoleBufferedReader.readLine()) != null) {
//                    	/**判断最大长度 清空**/
//                    	if(jTextPane.getText().length()>50000){
//                    		jTextPane.setText("");
//                    	}
//                        boolean caretAtEnd = jTextPane.getCaretPosition() == consoleDocument.getLength() ? true : false;
//                        mainFrameConsoleTextPaneCharacteristic.insertLogo();
//                        mainFrameConsoleTextPaneCharacteristic.insertMessage(Optional.of(consoleContent));
//                        if(caretAtEnd)
//                        	jTextPane.setCaretPosition(consoleDocument.getLength());
//                    }
//                }
//                catch(IOException e) {
//                	System.err.println("Stargazer System Report : Document 配置错误");
//                }
//            }
//        }).start();
    }
    
    public static void startRightConsoleReaderThread(MainFrameRightConsoleTextPaneCharacteristic mainFrameRightConsoleTextPaneCharacteristic) {
//    	JTextPane jTextPane = mainFrameRightConsoleTextPaneCharacteristic.getJTextPane().get();
//    	LoopedStreams ls = null;
//		try {
//			ls = new LoopedStreams();
//		} catch (IOException e) {
//			System.err.println("StargazerSystem Error Report : "+"控制台输出 LoopedStreams 异常");
//		}
//        PrintStream ps = new PrintStream(ls.getOutputStream());
//        System.setErr(ps);
//        final BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(ls.getInputStream()));
//        Document consoleDocument = jTextPane.getDocument();
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                	    String consoleContent;
//                    while((consoleContent = consoleBufferedReader.readLine()) != null) {
//                    	/**判断最大长度 清空**/
//                    	if(jTextPane.getText().length()>50000){
//                    		jTextPane.setText("");
//                    	}
//                        boolean caretAtEnd = jTextPane.getCaretPosition() == consoleDocument.getLength() ? true : false;
//                        mainFrameRightConsoleTextPaneCharacteristic.insertLogo();
//                        mainFrameRightConsoleTextPaneCharacteristic.insertMessage(Optional.of(consoleContent));
//                        if(caretAtEnd)
//                        	jTextPane.setCaretPosition(consoleDocument.getLength());
//                    }
//                }
//                catch(IOException e) {
//                	System.err.println("Stargazer System Report : Document 配置错误");
//                }
//            }
//        }).start();
    }

}
