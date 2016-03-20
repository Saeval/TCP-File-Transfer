package client.gui;

import javax.swing.JFrame;

public class GUITest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("GUI Test");
		MainPanel mainPanel = new MainPanel();
		
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
	}

}
