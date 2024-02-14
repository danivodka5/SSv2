package GuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Gui.InstagramLoginGui;

public class LoadingPanel extends JLabel{
	
	private Image image;
	
	public LoadingPanel() {
		setVisible(true);
		setBackground(null);
		//setPreferredSize(new Dimension(500,500));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
	
		setIcon(new ImageIcon(LoadingPanel.class.getResource("/Images/Spinner.gif")));
	
	}
}
