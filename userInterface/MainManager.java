package main;

public class MainManager {
	
	private Frame frame;
	private MainPanel mainPanel;
	
	public MainManager() {
		mainPanel = new MainPanel(this);
		frame = new Frame(mainPanel);
	}
	
}
