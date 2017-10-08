package com.stargazer.ui.util;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.text.Document;


public class ConsoleTextArea extends JTextArea {
	private static final long serialVersionUID = 7309857723035362456L;
	public ConsoleTextArea(InputStream[] inStreams) {
	        for(int i = 0; i < inStreams.length; ++i)
	            startConsoleReaderThread(inStreams[i]);
	    }
	    public ConsoleTextArea() throws IOException {
	        final LoopedStreams ls = new LoopedStreams();
	        PrintStream ps = new PrintStream(ls.getOutputStream());
	        System.setOut(ps);
	        System.setErr(ps);
	        startConsoleReaderThread(ls.getInputStream());
			this.setOpaque(false);
			this.setFont(new Font("Copperplate", Font.BOLD, 15));
			this.setForeground(Color.white);
			this.setBorder(BorderFactory.createEmptyBorder());
	    }
	    private void startConsoleReaderThread(InputStream inStream) {
	            final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
	            new Thread(new Runnable() {
	                public void run() {
	                    StringBuffer sb = new StringBuffer();
	                    try {
	                        String s;
	                        Document doc = getDocument();
	                        while((s = br.readLine()) != null) {
	                            boolean caretAtEnd = false;
	                            caretAtEnd = getCaretPosition() == doc.getLength() ?
	                                true : false;
	                            sb.setLength(0);
	                            int lenght = s.length();
	                            if(lenght>90)
	                            {
	                            	String tempSOne = s.substring(0, 90);
	                            	String tempSTwo = s.substring(90, lenght);
	                            append(sb.append(" ").append(tempSOne).append('\n').toString());
	                            sb = new StringBuffer();
	                            	append(sb.append(" ").append(tempSTwo).append('\n').toString());
	                            }
	                            else
	                            {
		                        append(sb.append(" ").append(s).append('\n').toString());
	                            }

	                            if(caretAtEnd)
	                            setCaretPosition(doc.getLength());
	                        }
	                    }
	                    catch(IOException e) {
	                    	System.err.println(e.getMessage());
	                    }
	                }
	            }).start();
	        }
}
