package main;

import javax.swing.JFrame;

public class Frame extends JFrame {

	public Frame(MainPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
