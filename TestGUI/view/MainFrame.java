package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import constant.Constants;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	// User Value
	private boolean resizeAble = Constants.MAINFRAME_RESIZEABLE;
	private Dimension size = Constants.MAINFRAME_SIZE;
	private String title = Constants.MAINFRAME_TITLE;
	
	// System Value
	private MainPanel mainPanel;
	
	// Constructor
	public MainFrame() {
		// Initialize Private Attribute
		this.setTitle(this.title);
		this.setResizable(this.resizeAble);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.size);
		this.setLocationRelativeTo(null);
		
		// Create Components
		this.mainPanel = new MainPanel();
		this.add(this.mainPanel);
	}
	
	public void initialize() {
		this.mainPanel.initialize();
		this.setVisible(true);
	}
}
