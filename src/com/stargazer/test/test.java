package com.stargazer.test;

import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;
import com.stargazer.mainframe.StargazerMainFrame;
import com.stargazer.ui.loadingui.StargazerLoadingJDialog;

public class test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		System.out.println(System.getProperty("user.dir") + "/stargazerUI/"+"Loading.png");
	    String file = System.getProperty("user.dir") + "/stargazerUI/"+"Loading.png";
	    StargazerLoadingJDialog stargazerLoadingJDialog = new StargazerLoadingJDialog(file);
	    new Thread() {  
            @Override  
            public void run() {  
                try {  
                    for (int i = 0; i < 10; i++) {  
                    	stargazerLoadingJDialog.updateProcess("  loading " + i + " Data", i * 10);  
                        Thread.sleep(200);
                        System.out.println(i);
                    }  
                } catch (InterruptedException ex) {
                	ex.printStackTrace();
                }
				stargazerLoadingJDialog.setVisible(false);
				stargazerLoadingJDialog.dispose();
                try {
                	Thread.sleep(100);
                System.out.println("Stargazer Cells System Has start");
                	System.out.println(System.getProperty("user.dir") + "/stargazerUI/"+"BackGround.png");
            	    String file = System.getProperty("user.dir") + "/stargazerUI/"+"BackGround.png";
            		StargazerMainFrame stargazerMainFrame = new StargazerMainFrame(file,System.getProperty("user.dir") + "/stargazerUI/"+"logo.png");
            		stargazerMainFrame.setVisible(true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }  
        }.start();
        stargazerLoadingJDialog.setVisible(true);
	}
}
