package GuiElements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Gui.InstagramLoginGui;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramUserGuiSearchPanel extends JPanel {

	// Soy un simple panel
	private JPanel exit;
	private UserGuiSearchBar ugsb;
	private JPanel searchpanel;
	private JPanel center;
	public JLabel jlicon;
	public SearchGuiIcon ugi;
	
	
	// Constructor
	public InstagramUserGuiSearchPanel() {
		setLayout(new BorderLayout());
		
		// Exit panel
		exit = new JPanel();
		exit.setPreferredSize(new Dimension(70,50));
		exit.setLayout(new BoxLayout(exit, BoxLayout.Y_AXIS));
		ImageIcon icon = new ImageIcon(InstagramUserGuiSearchPanel.class.getResource("/Images/Back_Arrow.png"));
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);  // transform it back
		
		// Back Icon
		jlicon = new JLabel(icon);
		jlicon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlicon.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		exit.add(Box.createVerticalGlue());
		exit.add(jlicon);
		exit.add(Box.createVerticalGlue());
			
		// UserGuiSearchBar Panel
		center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(new EmptyBorder(20,10,20,5));
		
		center.add(Box.createVerticalGlue());
		ugsb = new UserGuiSearchBar();
		ugsb.setSize(new Dimension(30,30));
		
		center.setBackground(Color.white);
		center.add(ugsb);
		center.add(Box.createVerticalGlue());
		
		
		add(center, BorderLayout.CENTER);
		
		// Hay que meterle una imagen
		searchpanel = new JPanel(new GridBagLayout());
		searchpanel.setPreferredSize(new Dimension(80,100));
		searchpanel.setBackground(Color.white);
		
		// Añadir padding a searchpanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        ugi = new SearchGuiIcon();
        
        searchpanel.add(ugi,  gbc);
		
		exit.setBackground(Color.white);
		ugsb.setBackground(Color.BLUE);
		
		add(exit, BorderLayout.WEST);
		add(searchpanel, BorderLayout.EAST);
			
	};
	
	public String getCenterSize() {
		return center.getSize()+"";
	}
	public int getTextSize() {
		return ugsb.getText().length();
	}
	public String getText() {
		return ugsb.getText();
	}
	
}
