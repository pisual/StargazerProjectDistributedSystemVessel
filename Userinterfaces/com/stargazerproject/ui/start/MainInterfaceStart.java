package com.stargazerproject.ui.start;

import com.stargazerproject.ui.assembly.impl.ConsoleTextPane;
import com.stargazerproject.ui.assembly.impl.MainFrame;
import com.stargazerproject.ui.assembly.impl.RightConsoleTextPane;

/**
 *
 _________ __ __________ __ __ / _____// |______ _______ _________ ________
 * __________\______ \_______ ____ |__| ____ _____/ |_ \_____ \\ __\__ \\_ __ \/
 * ___\__ \ \___ // __ \_ __ \ ___/\_ __ \/ _ \ | |/ __ \_/ ___\ __\ / \| | / __
 * \| | \/ /_/ > __ \_/ /\ ___/| | \/ | | | \( <_> ) | \ ___/\ \___| | /_______
 * /|__| (____ /__| \___ (____ /_____ \\___ >__| |____| |__| \____/\__| |\___
 * >\___ >__| \/ \/ /_____/ \/ \/ \/ \______| \/ \/ StargazerProject 观星者计划
 * https://github.com/pisual UserInterface
 *
 * StargazerProject时代的Swing User 基本构架 通过更改基本的一些参数就可以生成定制化的UI,通过运行
 * MainInterfaceStart 可以启动主界面
 *
 * @Web https://github.com/pisual http://www.pisual.com
 * @email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 * @author Felixerio FelixSion
 * **/
public class MainInterfaceStart {
	public static void main(String[] args) throws InterruptedException {
		MainFrame mainFrame = new MainFrame();
		mainFrame.VisualMainFrame();
		ConsoleTextPane.getInstance().insertMessage("Stargazer System Online Now Left");
		RightConsoleTextPane.getInstance().insertMessage("Stargazer System Online Now Right");

	}
}
