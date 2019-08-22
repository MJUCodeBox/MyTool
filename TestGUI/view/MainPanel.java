package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import constant.Constants;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	// User Value
	private Color panelBackGroundColor = Constants.MAINPANLE_BACKGROUNDCOLOR;
	
	// Constructor
	public MainPanel() {
		// Initialize Private Attribute
		this.setBackground(this.panelBackGroundColor);
	}
	
	public void initialize() {
	}

	public void paint(Graphics g) {
	}
}
