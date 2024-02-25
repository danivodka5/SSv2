package GuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Gui.InstagramLoginGui;

public class LoadingPanel extends JPanel{

	private JLabel nfl;
	
	// Constructor
	public LoadingPanel() {
		nfl = new JLabel();
		nfl.setVisible(true);
		nfl.setBackground(Color.red);
		//setPreferredSize(new Dimension(500,500));
		nfl.setHorizontalAlignment(SwingConstants.CENTER);
		nfl.setVerticalAlignment(SwingConstants.CENTER);
	
		//setIcon(new ImageIcon(LoadingPanel.class.getResource("/Images/Spinner.gif")));
        
        // Provisional
		nfl.setIcon(new ImageIcon(LoadingPanel.class.getResource("/Images/Spinner.gif")));
		//nfl.setVisible(false);
		
		add(nfl);
	}
	/*
	public void setLoadingPanel() {
		setIcon(new ImageIcon(LoadingPanel.class.getResource("/Images/Spinner.gif")));
		notFoundLabel.setVisible(false);
	}
	*/
	/*
	public void setUserNotFound(String label1) {
		setIcon(null);
        setLayout(new GridLayout(2, 1));
        
        // Create and customize the big label
        notFoundLabel = new JLabel();
        notFoundLabel.setVisible(true);
        notFoundLabel.setText(label1);
        notFoundLabel.setFont(new Font("Arial", Font.BOLD, 24));
        notFoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(notFoundLabel);
        revalidate();
      
	}
	public void setLoadingPanelNull() {
		setIcon(null);
	}
	*/
}
