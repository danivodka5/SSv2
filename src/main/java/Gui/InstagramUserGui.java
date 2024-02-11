package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import GuiElements.InstagramUserGuiSearchPanel;
import GuiElements.PanelSlide;

public class InstagramUserGui {

	private JFrame frame;
	
	private PanelSlide ps;
	private InstagramUserGuiSearchPanel iugsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstagramUserGui window = new InstagramUserGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InstagramUserGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ps = new PanelSlide();
		frame.getContentPane().add(ps);
		
		ps.setLayout(new BorderLayout());
		iugsp = new InstagramUserGuiSearchPanel();
		iugsp.setPreferredSize(new Dimension(100,100));
		ps.add(iugsp, BorderLayout.NORTH);
		
		
		
		
	}
}
